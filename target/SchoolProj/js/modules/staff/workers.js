$(document).ready(function () {
    selectTitle();
    selectResponsibility();
    saveWorker();
    editWorker();
    createWorkersTbl();
    workerTblClick();
    $(".datepicker-input").each(function () {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    })
})
function staffImage(id){
    console.log(id)
    $("#avatar").fileinput({
        overwriteInitial: true,
        maxFileSize: 1500,
        showClose: false,
        showCaption: false,
        browseLabel: '',
        removeLabel: '',
        browseIcon: '<i class="fa fa-folder-open"></i>',
        removeIcon: '<i class="fa fa-times"></i>',
        removeTitle: 'Cancel or reset changes',
        elErrorContainer: '#kv-avatar-errors',
        msgErrorClass: 'alert alert-block alert-danger',
        defaultPreviewContent: '<img src="'+ctx+'/protected/academics/workerPhoto/'+ id +'"  style="height:15em;width:200px">',
        layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
        allowedFileExtensions: ["jpg", "png", "gif"]
    });
}
function selectTitle() {

    if($("#title-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "title-div",
            sort : 'titleName',
            change: function(e, a, v){
                $('#title-code').val(e.added.titleCode)
                $('#title-name').val(e.added.titleName)
            },
            formatResult : function(a)
            {
                return a.titleName;
            },
            formatSelection : function(a)
            {

                return a.titleName;
            },
            initSelection: function (element, callback) {
                var code = $("#title-code").val();
                var name = $("#title-name").val();
                var data = {titleName:name,titleCode:code};
                callback(data);
            },
            id: "titleCode",
            placeholder:"Select Title",
        });
    }}

function selectResponsibility() {

    if($("#res-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "res-div",
            sort : 'responsibilityName',
            change: function(e, a, v){
                $('#res-code').val(e.added.responsibilityCode)
                $('#res-name').val(e.added.responsibilityName)
            },
            formatResult : function(a)
            {
                return a.responsibilityName;
            },
            formatSelection : function(a)
            {

                return a.responsibilityName;
            },
            initSelection: function (element, callback) {
                var code = $("#res-code").val();
                var name = $("#res-name").val();
                var data = {responsibilityName:name,responsibilityCode:code};
                callback(data);
            },
            id: "responsibilityCode",
            placeholder:"Select Responsibility",
        });
    }}
function saveWorker() {
    var $form = $("#staff-form");
    var validator = $form.validate();

    $('#staff-form')
        .submit(function (e) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData(this);
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveStaff',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Staff Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#workers-tbl").DataTable().ajax.reload();
            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
}

function editWorker() {
    if (typeof staffCode !== 'undefined' || staffCode !== -2000) {

        $.ajax({
            type:'GET',
            url:'getWorker/'+staffCode

        }).done(function (s) {
                staffImage(s.staffCode);
                $('#staff-id').val(s.staffCode);
                $('#staff-name').val(s.name);

                if (s.startDate) {
                    $('#start').val(moment(s.startDate).format('DD/MM/YYYY'));
                }
                if (s.endDate) {
                    $('#end').val(moment(s.endDate).format('DD/MM/YYYY'));

                }

                $('#address').val(s.address);
                $('#phone-number').val(s.phoneNumber);
                $('#email').val(s.email);
                $('#id-no').val(s.idNo);
                $('#teach-code').val(s.teacherCode);
                $('#teach-name').val(s.name);
                if (s.gender === "Male") {
                    $('#id-male').prop('checked', true);
                } else if (s.gender === "Female") {
                    $('#id-female').prop('checked', true);
                }
                $('#status').val(s.status);
                if (s.titles !== null) {
                    $('#title-code').val(s.titles.titleCode);
                    $('#title-name').val(s.titles.titleName);
                    selectTitle();
                }
                if (s.responsibility !== null) {
                    $('#res-code').val(s.responsibility.responsibilityCode);
                    $('#res-name').val(s.responsibility.responsibilityName);
                    selectResponsibility();
                }
            }

        )


    }
}
function createWorkersTbl() {

    var table=$('#workers-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'findAllWorkers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "titles",
                render: function ( data, type, full, meta ) {
                    if (full.titles != null) {
                        return full.titles.titleName;
                    }
                }
            },
            { data: "name"},
            { data: "address" },
            { data: "phoneNumber"},
            { data: "responsibility" ,
                render: function ( data, type, full, meta ) {
                    if (full.responsibility != null) {
                        return full.responsibility.responsibilityName;
                    }
                }
            },
            { data: "staffCode"},

            { data: "startDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null) {
                        return moment(full.startDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }
            },
            { data: "email"},
            { data: "gender"},
            {data:"status"},
            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form action="'+ctx+'/protected/academics/editMyWorker" method="post"><input type="hidden" name="staff" value='+full.staffCode+'><input type="submit"  class="btn btn-success btn btn-info btn-xs" value="Edit" ></form>';
                }
            },
            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.staffCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teachers-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;


}
function workerTblClick(){
    $('#workers-tbl').on( 'click', '.btn-delete', function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteWorker/' + data;
        bootbox.confirm("Are you sure want to delete this Worker?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Worker Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#workers-tbl").DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                    new PNotify({
                        title: 'Error',
                        text: xhr.responseText,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                })

            }
        })
    })
}
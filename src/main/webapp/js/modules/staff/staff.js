$(document).ready(function () {
    clearAllPendingSubjects();
selectClass();
selectMyClass();
selectTitle();
selectResponsibility();
saveTeacher();
unAssignedSubjects();
assignedSubjects();
assignSub();
assignAll();
unAssignAll();
teacherTblClick();
createTeachersTbl();
    saveAssignedSubjects();
selectTeacher();
teachModal();
saveTransfers();
removeTeacherSubjects();
editTeacher();
searchTeFc();
    $('#muller').hide();
    $('#muller1').hide();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });

})
function teachModal() {
    $('#transfer-subjects').on('click',function () {
        console.log('clicked');
        if($('#teach-code').val()===''){
            bootbox.alert("Choose a teacher to transfer from first");
        }
        else {
            $.fn.modal.Constructor.prototype.enforceFocus = function () {
            };
            $("#teachModal").modal('show');
        }

    })
}
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function saveTransfers() {
    $('#btn-save-transfers').click(function () {
        var tFrom=$('#teach-code').val();
        var tTo=$('#tea-id').val();
        var classId=$('#myclass').val();
        if(tTo===''){
            bootbox.alert('Select a teacher to assign first');
        }
        else{
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'transferAssignedSubjects',
                data: {
                    'classId': classId,
                    'tFrom':tFrom,
                    'tTo':tTo
                },
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Success',
                        text: 'Subjects Transferred Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

                    $('#teachModal').modal('hide');
                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        }
    })

}
function removeTeacherSubjects() {
    $('#un-assign-subjects').click(function () {
        var teacher=$('#teacher-id').val();
        if(teacher===''|| teacher=== -2000){
            bootbox.alert('Select a teacher to un-assign first!');
        }
        else{
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'removeAllAssignedSubjects',
                data: {
                    'teacher':teacher
                },
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Success',
                        text: 'Subjects Removed Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

                    $('#teachModal').modal('hide');
                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        }
    })

}
function assignAll(){
    $('#btn-assign-all').on('click',function() {
        if ($('#teacher-id').val() === '' || $('#teacher-id').val() === -2000|| $('#status').val() !== 'Active') {
            bootbox.alert('Select teacher first to assign or check if the selected teacher is active')
        } else {

            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'setAllPending',
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    $('#unassigned-tbl').DataTable().ajax.reload();
                    $('#assigned-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#unassigned-tbl').DataTable().ajax.reload();
                $('#assigned-tbl').DataTable().ajax.reload();


            });

        }
    })
        }

function unAssignAll(){
    $('#btn-unassign-all').on('click',function() {
        if ($('#teacher-id').val() === '' || $('#teacher-id').val() === -2000 || $('#status').val() !== 'Active') {
            bootbox.alert('Select teacher first to unAssign or check if the selected teacher is active')
        } else {

            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'setAllAssigned',
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    $('#unassigned-tbl').DataTable().ajax.reload();
                    $('#assigned-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#unassigned-tbl').DataTable().ajax.reload();
                $('#assigned-tbl').DataTable().ajax.reload();


            });


        }
    })
}

function unAssignedSubjects() {

    var table=$('#unassigned-tbl').DataTable({
        processing: true,
        serverSide: true,
        destroy: true,
        "ajax": "getAllSubjects",
        "scrollY": "250px",
        "scrollCollapse": true,
        "paging": false,
        "dom": "t",
        "columns": [
            { "data": "name"
            }
        ],
        initComplete: function () {
            // enables multi selection extension
            $("#unassigned-tbl").enableExtendedSelection();
        }
    } );
    $("#unassigned-tbl").selectable(true);
    $('#btn-assign-subject').on('click',function () {
        if ($('#teacher-id').val() === '' || $('#teacher-id').val() === -2000 || $('#status').val()!=='Active'){
            bootbox.alert('Select teacher first to assign or check if the selected teacher is active')
        }
        else {
            var arr = $("#unassigned-tbl").data().selection.items;
            console.log(arr);
            var myArray = [];
            for (var j = 0; j < arr.length; j++) {

                myArray.push(arr[j].subjectCode);
                console.log(myArray);

            }
            //for (var i = 0; i < table.rows('.selected').data().length; i++) {
            //    if (table.rows('.selected').data()[i] === undefined || table.rows('.selected').data()[i] === null) {
            //    }
            //    else {
            //        arr.push(table.rows('.selected').data()[i].roleId);
            //    }
            //}

            if (myArray.length == 0) {
                bootbox.alert("No Subjects selected to move");
                return;
            }
            $('#myPleaseWait').modal('show');
            var $currForm = $('#subjects-form');
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "setPending";
            data.subjectCode = myArray;
            console.log(myArray)
            console.log(JSON.stringify(data));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    $('#unassigned-tbl').DataTable().ajax.reload();
                    $('#assigned-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#unassigned-tbl').DataTable().ajax.reload();
                $('#assigned-tbl').DataTable().ajax.reload();


            });
        }
    })
}
function saveAssignedSubjects() {
    $('#grid').on('click',function () {
        var id=$('#class').val();
        var t=$('#teacher-id').val();
        console.log(id)
        if(id==='' || t==='' || t=== -2000){
            bootbox.alert('Select a teacher and class first');
        }
        else{
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'saveAssigned',
                data: {
                    'classId': id,
                    'teacher':t
                },
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Success',
                        text: 'Subjects Assigned Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $('#unassigned-tbl').DataTable().ajax.reload();
                    $('#assigned-tbl').DataTable().ajax.reload();
                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#unassigned-tbl').DataTable().ajax.reload();
                $('#assigned-tbl').DataTable().ajax.reload();
            });
        }
    })
}
function assignedSubjects() {

    var table=$('#assigned-tbl').DataTable({
        processing: true,
        serverSide: true,
        destroy: true,
        "ajax": "getAssignedSubjects",
        "scrollY": "250px",
        "scrollCollapse": true,
        "paging": false,
        "dom": "t",
        "columns": [
            {
                "data": "name"
            }
        ],
        initComplete: function () {
            // enables multi selection extension
            $("#assigned-tbl").enableExtendedSelection();
        }
    })
    $("#assigned-tbl").selectable(true);
    $('#btn-unassign-subject').on('click',function () {
        if ($('#teacher-id').val() === ''|| $('#teacher-id').val() === -2000 || $('#status').val()!=='Active'){
            bootbox.alert('Select teacher first to assign or check if the selected teacher is active')
        }
        else {
            var arr = $("#assigned-tbl").data().selection.items;
            console.log(arr);
            var myArray = [];
            for (var j = 0; j < arr.length; j++) {

                myArray.push(arr[j].subjectCode);
                console.log(myArray);

            }
            //for (var i = 0; i < table.rows('.selected').data().length; i++) {
            //    if (table.rows('.selected').data()[i] === undefined || table.rows('.selected').data()[i] === null) {
            //    }
            //    else {
            //        arr.push(table.rows('.selected').data()[i].roleId);
            //    }
            //}

            if (myArray.length == 0) {
                bootbox.alert("No Subjects selected to move");
                return;
            }
            $('#myPleaseWait').modal('show');
            var $currForm = $('#subjects-form');
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "setAssigned";
            data.subjectCode = myArray;
            console.log(myArray)
            console.log(JSON.stringify(data));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    $('#unassigned-tbl').DataTable().ajax.reload();
                    $('#assigned-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#unassigned-tbl').DataTable().ajax.reload();
                $('#assigned-tbl').DataTable().ajax.reload();


            });
        }
    })
}
function assignSub() {
    $('#subjects').change(function(){
      if(this.checked)  {
          $('#muller').show();
          $('#muller1').show();
      }
      else{
          $('#muller').hide();
          $('#muller1').hide();

      }
    })
}
function clearAllPendingSubjects(){
    $.ajax({
        type: 'GET',
        url: 'clearAllPendingSubjects',

    }).done(function () {

    }).fail(function (xhr, error) {
        new PNotify({
            title: 'Error',
            text: xhr.responseText,
            type: 'error',
            styling: 'bootstrap3'
        });

    })
}
function selectClass() {

    if($("#class-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "class-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#class').val(e.added.classCode)
                $('#dm').val(e.added.abbr)
            },
            formatResult : function(a)
            {
                return a.abbr;
            },
            formatSelection : function(a)
            {

                return a.abbr;
            },
            initSelection: function (element, callback) {
                var code = $("#class").val();
                var name = $("#dm").val();
                var data = {abbr:name,classCode:code};
                callback(data);
            },
            id: "classCode",
            placeholder:"Class",
        });
    }}
function teacherImage(id){
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
        defaultPreviewContent: '<img src="'+getContextPath()+'/protected/academics/teacherPhoto/'+ id +'"  style="height:15em;width:200px">',
        layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
        allowedFileExtensions: ["jpg", "png", "gif"]
    });
}
function selectMyClass() {

    if($("#my-class-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "my-class-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#myclass').val(e.added.classCode)
                $('#mydm').val(e.added.abbr)
            },
            formatResult : function(a)
            {
                return a.abbr;
            },
            formatSelection : function(a)
            {

                return a.abbr;
            },
            initSelection: function (element, callback) {
                var code = $("#myclass").val();
                var name = $("#mydm").val();
                var data = {abbr:name,classCode:code};
                callback(data);
            },
            id: "classCode",
            placeholder:"Class",
        });
    }}

function selectTeacher() {

    if($("#teach-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "teach-div",
            sort : 'name',
            change: function(e, a, v){
                $('#tea-id').val(e.added.teacherCode)
                $('#tea-name').val(e.added.name)
            },
            formatResult : function(a)
            {
                return a.name;
            },
            formatSelection : function(a)
            {

                return a.name;
            },
            initSelection: function (element, callback) {
                var code = $("#tea-id").val();
                var name = $("#tea-name").val();
                var data = {name:name,teacherCode:code};
                callback(data);
            },
            id: "teacherCode",
            placeholder:"Teacher",
        });
    }}

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

function saveTeacher() {
    var $form = $("#teacher-form");
    var validator = $form.validate();

    $('#teacher-form')
        .submit(function (e) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData(this);
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveTeacher',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Teacher Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#teachers-tbl").DataTable().ajax.reload();
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
function editTeacher() {
    if (typeof tchCode !== 'undefined' || tchCode !== -2000) {

             $.ajax({
             type:'GET',
                 url:'getTeacher/'+tchCode

               }).done(function (s) {
                 teacherImage(s.teacherCode);
                 $('#teacher-id').val(s.teacherCode);
                 $('#teacher-name').val(s.name);

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
                 $('#tsc-no').val(s.tscNo);
                 $('#intials').val(s.intials);
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
function searchTeFc() {
    $('#search-id').on('keyup', function () {
        $('#teachers-tbl').DataTable()
            .columns(1)
            .search(this.value)
            .draw();
    });
}
function createTeachersTbl() {

    var table=$('#teachers-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'findAllTeachers',
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
            { data: "teacherCode"},

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
            { data: "intials"},
            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form action="'+getContextPath()+'/protected/academics/editMyTeacher" method="post"><input type="hidden" name="teacher" value='+full.teacherCode+'><input type="submit"  class="btn btn-success btn btn-info btn-xs" value="Edit" ></form>';
                }
            },
            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.teacherCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
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
function teacherTblClick(){
    $('#teachers-tbl').on( 'click', '.btn-delete', function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteTeacher/' + data;
        bootbox.confirm("Are you sure want to delete this Teacher?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Teacher Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#subjects-tbl").DataTable().ajax.reload();

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
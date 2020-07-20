// jshint ignore: start

$(document).ready(function () {
typeClick();
saveType();
editType();
deleteExamReg();
deleteType();
getTypeTab();
selectYear();
selectTerm();
selectType();
getExamRegTab()
saveExamRegister();

    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });

    $('#month').on('change',function () {
        setName();
    })
    $('#exam-reg-form').trigger("reset");
    jQuery('.select2-offscreen').select2('val', '');

})
function setName(){
    var w="";
    var x="";
    var y="";
    var z="";
    if($('#term-name').val()!==''){
        console.log($('#term-name').val());
        w="T"+$('#term-name').val()+"_";
    }
    else{
       w="";
    }
    if($('#month').val()!=='') {
        x = $('#month').val() + "_";
    }
    else{
        x="";
    }
    if($('#year-name').val()!=='') {
        y = $('#year-name').val() + "_";
    }
    else {
        y=""
    }
    if($('#type-name').val()!=='') {
         z = $('#type-name').val();
    }
    else{
        z="";
    }
    var p=w+x+y+z;
    $('#exam-name').val(p);
}
function typeClick() {
    $('#btn-exam-type').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#type-id').val('');
        $("#typeModal").modal('show');

    })
}
function saveType() {
    var $form= $("#type-form");
    var validator = $form.validate();

    $('#type-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveType',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Record Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#type-tbl").DataTable().ajax.reload();
                $('#typeModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#typeModal').on('hidden.bs.modal', function () {
        $('#type-form')[0].reset();
    });
}
function saveExamRegister() {
    var $form= $("#exam-reg-form");
    var validator = $form.validate();

    $('#exam-reg-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveExamReg',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Record Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#exams-reg-tbl").DataTable().ajax.reload();

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
function getTypeTab() {

    var table=$('#type-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getTypeTable',
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "typeName" },
            { data: "typeDesc" },
            { data: "typeCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.typeCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "typeCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.typeCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );

    return table;
}
function getExamRegTab() {

    var table=$('#exams-reg-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRegTable'
        },
        scrollX:true,
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examCode" },
            { data: "examName" },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                 if(full.year!=null){
                     return full.year.yearName
                 }
                }
                },
            { data: "month" },
            { data: "examType" ,
                render: function ( data, type, full, meta ) {
                    if(full.examType!=null){
                        return full.examType.typeName;
                    }
                }
            },
            { data: "term" ,
                render: function ( data, type, full, meta ) {
                    if(full.term!=null){
                        return full.term.termNumber;
                    }
                }
            },
            { data: "finalExam" ,
                render: function ( data, type, full, meta ) {
                    if(full.finalExam==="Y"){
                        return "Y";
                    }
                    else{
                        return "N";

                    }
                }
            },
            { data: "authorised" ,
                render: function ( data, type, full, meta ) {
                    if(full.authorised==="Y"){
                        return "Y";
                    }
                    else{
                        return "N";
                    }
                }
            },
            { data: "combinedExam" ,
                render: function ( data, type, full, meta ) {
                    if(full.combinedExam==="Y"){
                        return "Y";
                    }
                    else{
                        return "N";
                    }
                }
            },
            { data: "combinedExam" ,
                render: function ( data, type, full, meta ) {
                    if(full.combinedExam==="Y"){
                        return "Y";
                    }
                    else{
                        return "N";
                    }
                }
            },
            { data: "grading" },
            { data: "forms",
                render: function ( data, type, full, meta ) {

                    return "";
                }
            },
            { data: "examName" },
            { data: "effectiveDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.effectiveDate!==null) {
                        return moment(full.effectiveDate).format('DD/MM/YYYY');
                    }
                    else{
                        return "";
                    }
                }
            },
            { data: "lockDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.lockDate!==null) {
                        return moment(full.lockDate).format('DD/MM/YYYY');
                    }
                    else{
                        return "";
                    }
                }
            },


        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#exam-reg-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#exams-reg-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#exam-id').val(aData[0].examCode);
        if(aData[0].year!=null) {
            $('#year-code').val(aData[0].year.yearCode);
            $('#year-name').val(aData[0].year.yearName);
            selectYear();
        }
        if(aData[0].examType!=null) {
            $('#type-code').val(aData[0].examType.typeCode);
            $('#type-name').val(aData[0].examType.typeName);
            selectType();
        }
        if(aData[0].term!=null) {
            $('#term-code').val(aData[0].term.termCode);
            $('#term-name').val(aData[0].term.termNumber);
            selectTerm();
        }
        $('#month').val(aData[0].month);
        $('#exam-name').val(aData[0].examName);
        $('#disp-name').val(aData[0].examName);
        $('#grading').val(aData[0].grading);


        $('#start-date').val(moment(aData[0].effectiveDate).format('DD/MM/YYYY'))
        $('#lock-date').val(moment(aData[0].lockDate).format('DD/MM/YYYY'))


        $('#lab-name').val(aData[0].laboratoryName);
        $('#lab-com').val(aData[0].laboratoryComment);
        if (aData[0].combinedExam==="Y") {

            $('#combined-exam').prop('checked', true);
        }
        else{
            $('#combined-exam').prop('checked', false);

        }
        if (aData[0].finalExam==="Y") {

            $('#final-exam').prop('checked', true);
        }
        else{
            $('#final-exam').prop('checked', false);

        }

    })
    return table;
}
function editType() {
    $('#type-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'editType/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#type-id').val(s.typeCode);
            $('#type-name').val(s.typeName);
            $('#type-abbr').val(s.typeDesc);
            $('#typeModal').modal('show');

        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    })
}
function deleteType(){
    $('#type-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteType/' + data;
        bootbox.confirm("Are you sure want to delete this Exam Type?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#type-tbl").DataTable().ajax.reload();

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
function deleteExamReg(){
    $('#btn-exam-del').on('click',function () {
        var data = $('#exam-id').val();
        if(data===''){
            bootbox.alert('Select a record to delete');
        }
        else {
            var url = 'deleteExamReg/' + data;
            bootbox.confirm("Are you sure want to delete this Record?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: 'Record Deleted Successfully',
                            type: 'success',
                            styling: 'bootstrap3'
                        });
                        $("#exams-reg-tbl").DataTable().ajax.reload();

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
        }
    })

}
function selectYear() {
    if($("#year-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "year-div",
            sort : 'yearName',
            change: function(e, a, v){
                $('#year-code').val(e.added.yearCode)
                $('#year-name').val(e.added.yearName)
                setName()
            },
            formatResult : function(a)
            {
                return a.yearName
            },
            formatSelection : function(a)
            {
                return a.yearName
            },
            initSelection: function (element, callback) {
                var code = $('#year-code').val();
                var name = $('#year-name').val();
                var data = {yearName:name,yearCode:code};
                callback(data);
            },
            id: "yearCode",
            placeholder:"Year",
        });
    }
}
function selectTerm() {
    if($("#term-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "term-div",
            sort : 'termNumber',
            change: function(e, a, v){
                $('#term-code').val(e.added.termCode)
                $('#term-name').val(e.added.termNumber)
                console.log($('#term-name').val())
                setName()
            },
            formatResult : function(a)
            {
                return a.termNumber
            },
            formatSelection : function(a)
            {
                return a.termNumber
            },
            initSelection: function (element, callback) {
                var code = $("#term-code").val();
                var name = $("#term-name").val();
                var data = {termNumber:name,termCode:code};
                callback(data);
            },
            id: "termCode",
            placeholder:"Select Term",
        });
    }
}
function selectType() {
    if($("#type-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "type-div",
            sort : 'typeName',
            change: function(e, a, v){
                $('#type-code').val(e.added.typeCode)
                $('#type-name').val(e.added.typeName)
                setName();
            },
            formatResult : function(a)
            {
                return a.typeName
            },
            formatSelection : function(a)
            {
                return a.typeName
            },
            initSelection: function (element, callback) {
                var code = $("#type-code").val();
                var name = $("#type-name").val();
                var data = {typeName:name,typeCode:code};
                callback(data);
            },
            id: "typeCode",
            placeholder:"Exam Type",
        });
    }
}

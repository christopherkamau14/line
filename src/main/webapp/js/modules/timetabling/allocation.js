
// jshint ignore: start

$(document).ready(function () {
    selectClass()
    selectTerm()
    selectSubject()
    selectDay();
    selectLesson();
    selectForm()
    saveAllocation();
    tableClick()
    searchAllocTbl()
    searchTeFc1()
    searchTeFc2()
    searchTeFc3()
    searchTeFc4()
    deleteAllocation()
    classTT()
    teacherTT();
    selectTeacher()
})
function selectTeacher() {
    if($("#teacher-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "teacher-div",
            sort : 'name',
            change: function(e, a, v){
                $('#teacher-code').val(e.added.teacherCode)
                $('#teacher-name').val(e.added.name)

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
                var code = $("#teacher-code").val();
                var name = $("#teacher-name").val();
                var data = {name:name,teacherCode:code};
                callback(data);
            },
            id: "teacherCode",
            placeholder:"Teacher",
        });
    }}
function deleteAllocation(){
    $('#btn-alloc-del').on('click',function () {
        var data = $('#alloc-id').val();
        if(data===''){
            bootbox.alert("Select Allocation to delete");
        }
        else {
            var url = 'deleteAllocation/' + data;
            bootbox.confirm("Are you sure want to delete this Allocations?", function (result) {
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
                        $("#allocation-tbl").DataTable().ajax.reload();
                        $('#alloc-id').val("")

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
function classTT(){
    $('#btn-alloc-classtt').on('click',function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#form-id').val('');
        $("#classTableModal").modal('show');
    })
}
function teacherTT(){
    $('#btn-alloc-teachertt').on('click',function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#teacher-code').val('');
        $("#teacherTableModal").modal('show');
    })
}
function saveAllocation() {
    $('#btn-save-allocation').on('click',function () {
        if($("#class").val()==='' ||$("#subjects-id").val()===''){
            bootbox.alert("Input a record to save");
        }else {
            var $currForm = $('#allocation-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveAllocation";
            var request = $.post(url, data);
            $('#myPleaseWait').modal('show');
            request.success(function () {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Success',
                    text: 'Allocation created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#allocation-tbl').DataTable().ajax.reload();
                $('#alloc-id').val("")
                currValidator.resetForm();
            });
            request.error(function (jqXHR, textStatus, errorThrown) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: jqXHR.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
            });
            request.always(function () {
                //	$btn.button('reset');
            });
        }
    })
}
function populateTeacher(id) {
    var code=$('#class').val();

    if(code===''){

    }
    else{
        $.ajax({
            type: 'GET',
            url: 'findTeacher',
            data: {
                'subject':id,
                'className': code,

            }
        }).done(function (s) {
            console.log(s)
            $('#tea-id').val(s.teachers.teacherCode);
            $('#teacher').val(s.teacherName);

        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            })

        })

    }
}

function selectSubject() {


    if($("#subject-div").filter("div").html() !== undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "subject-div",
            sort : 'name',
            change: function(e, a, v){
                $('#subjects-id').val(e.added.subjectCode)
                $('#subject-name').val(e.added.name)
                 populateTeacher(e.added.subjectCode);
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
                var code = $("#subjects-id").val();
                var name = $("#subject-name").val();
                var data = {name:name,subjectCode:code};
                callback(data);
            },
            id: "subjectCode",
            placeholder:"Subject",
        });
    }}
function selectLesson() {

    if($("#lessons-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "lessons-div",
            sort : 'lessonName',
            change: function(e, a, v){
                $('#lesson-id').val(e.added.lessonId)
                $('#lesson-name').val(e.added.lessonName)

            },
            formatResult : function(a)
            {
                return a.lessonName;
            },
            formatSelection : function(a)
            {

                return a.lessonName;
            },
            initSelection: function (element, callback) {
                var code = $("#lesson-id").val();
                var name = $("#lesson-name").val();
                var data = {lessonName:name,lessonId:code};
                callback(data);
            },
            id: "lessonId",
            placeholder:"Lesson",
        });
    }}
function selectDay() {


    if($("#day-div").filter("div").html() !== undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "day-div",
            sort : 'name',
            change: function(e, a, v){
                $('#day-code').val(e.added.dayCode)
                $('#day-name').val(e.added.name)

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
                var code = $("#day-code").val();
                var name = $("#day-name").val();
                var data = {name:name,dayCode:code};
                callback(data);
            },
            id: "dayCode",
            placeholder:"Day",
        });
    }}
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
function selectClass() {
    var id=$('#reg-code').val();
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
            placeholder:"Select Class",
        });
    }}
function selectForm() {
    if($("#form-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "form-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#form-id').val(e.added.classCode)
                $('#form-name').val(e.added.abbr)
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
                var code = $("#form-id").val();
                var name = $("#form-name").val();
                var data = {abbr:name,classCode:code};
                callback(data);
            },
            id: "classCode",
            placeholder:"Select Class",
        });
    }}
function searchAllocTbl() {

    var table=$('#allocation-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        scrollX:true,
        ajax: {
            url:'getAllocationTbl',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "allocationCode"},
            { data: "lessons",
                render: function ( data, type, full, meta ) {
                    if (full.lessons != null) {
                        return full.lessons.lessonName;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.teacherCode;
                    }
                }
            },
            { data: "subjectName"},
            { data: "dayName"},
            { data: "className"},
            { data: "term",
                render: function ( data, type, full, meta ) {
                    if (full.term != null) {
                        return full.term.termNumber;
                    }
                }
            },
            { data: "allocationCode",
                render: function ( data, type, full, meta ) {
                    return "S";

                }
            },
            { data: "allocationCode",
                render: function ( data, type, full, meta ) {
                    return "N/A";

                }
            },
            { data: "stroked"},
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.titles.titleName;
                    }
                }
            },
            { data: "teacherName"},
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.address;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.phoneNumber;
                    }
                }
            },
            { data: "teachers" ,
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.responsibility.responsibilityName;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.teacherCode;
                    }
                }
            },

            { data: "teachers" ,
                render: function ( data, type, full, meta ) {
                    if(full.teachers!==null) {
                        return moment(full.teachers.startDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.gender;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.email;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.tscNo;
                    }
                }
            },

            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.status;
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.intials;
                    }
                }
            },
            { data: "teachers" ,
                render: function ( data, type, full, meta ) {
                    if(full.teachers!==null) {
                        if(full.teachers.endDate!==null) {
                            return moment(full.teachers.endDate).format('DD/MM/YYYY');
                        }
                        else{
                            return "";
                        }
                    }
                    else{
                        return "";
                    }
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    if (full.teachers != null) {
                        return full.teachers.idNo;
                    }
                }
            },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#allocation-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;


}
function searchTeFc1() {
    $('#subject-id').on('input', function () {
        $('#allocation-tbl').DataTable()
            .columns(3)
            .search(this.value)
            .draw();
    });
}
function searchTeFc2() {
    $('#day-id').on('input', function () {
        $('#allocation-tbl').DataTable()
            .columns(4)
            .search(this.value)
            .draw();
    });
}
function searchTeFc3() {
    $('#class-id').on('input', function () {
        $('#allocation-tbl').DataTable()
            .columns(5)
            .search(this.value)
            .draw();
    });
}
function searchTeFc4() {
    $('#teacher-id').on('input', function () {
        $('#allocation-tbl').DataTable()
            .columns(11)
            .search(this.value)
            .draw();
    });
}
function tableClick(){
    $('#allocation-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData =$('#allocation-tbl').DataTable().rows('.active').data();
        $('#alloc-id').val(aData[0].allocationCode)
        if(aData[0].term!=null) {
            $('#term-code').val(aData[0].term.termCode)
            $('#term-name').val(aData[0].term.termNumber)
            selectTerm();
        }
        if(aData[0].days!=null) {
            $('#day-code').val(aData[0].days.dayCode)
            $('#day-name').val(aData[0].days.name)
            selectDay();
        }
        if(aData[0].forms!=null) {
            $('#class').val(aData[0].forms.classCode)
            $('#dm').val(aData[0].forms.abbr)
            selectClass();
        }
        if(aData[0].lessons!=null) {
            $('#lesson-id').val(aData[0].lessons.lessonId)
            $('#lesson-name').val(aData[0].lessons.lessonName)
            selectLesson();
        }
        if(aData[0].subjects!=null) {
            $('#subjects-id').val(aData[0].subjects.subjectCode)
            $('#subject-name').val(aData[0].subjects.name)
            selectSubject();
            populateTeacher(aData[0].subjects.subjectCode)
        }
    })
}
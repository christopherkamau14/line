// jshint ignore: start

$(document).ready(function () {
    tableClick();
selectTerm();
selectYear();
selectReg();
selectMyStudent();
selectMySubject();
selectMyClass();
saveExamRecord();
clearPage();
missExam();
undoMissExam();
importExcel();
makeAndDownload();
    convertPerc();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });
})
function convertPerc() {
$('#convert').click(function (){
    new PNotify({
        title: 'Success',
        text: ' Converted Successfully',
        type: 'success',
        styling: 'bootstrap3'
    })
    }

)
}
function importExcel() {
    $("#import").change(function(e){
        var selectedFile= e.target.files[0];
        var reader=new FileReader();
        reader.onload= function(evt){
            //if(typeof require !== 'undefined') XLSX = require('xlsx');
            var data=evt.target.result;
            var examsheet= XLSX.read(data,{type : 'binary'});

            examsheet.SheetNames.forEach(function(sheetName) {

                var XL_row_object = XLSX.utils.sheet_to_row_object_array(examsheet.Sheets[sheetName]);
                var obj = JSON.stringify(XL_row_object);
                obj=JSON.parse(obj);
                //for(var i=0;i<json_object.length;i++){
                //   var obj=json_object[i];
                //  console.log(obj);

                // }

                //	document.getElementById("jsonObject").innerHTML = obj.NAME+" "+obj.PRODUCT;
                insertExcel(obj);
            })
        };
        reader.onerror = function(event) {
            console.error("File could not be read! Code " + event.target.error.code);
        };
        reader.readAsBinaryString(selectedFile);
    });
}
function insertExcel(obj) {
    console.log(obj);
    obj.forEach(function (m) {
        var teacher=m.TEACHER||m.Teacher||m.teacher;
        console.log(teacher)
        var form=m.FORM||m.Form||m.form;
        var subject=m.SUBJECT||m.Subject||m.subject;
        var exam=m.EXAM||m.exam||m.Exam;
        var student=m.STUDENT || m.student ||m.Student;
        $.ajax({
            type: 'GET',
            url: 'checkExcel',
            data: {
                'teacher':teacher,
                'form':form,
                'exam': exam,
                'subject':subject,
                'student':student,
            }
        }).done(function (s) {

            if(s){
                bootbox.confirm("Are you sure want to overwrite an existing record for  student "+ student +" subject"+subject, function (result) {
                    if (result) {
                        $.ajax({
                            type: 'GET',
                            url: 'saveExcel',
                            data: {
                                'teacher': m.TEACHER||m.Teacher||m.teacher,
                                'student': m.STUDENT || m.student ||m.Student,
                                'form': m.FORM||m.Form||m.form,
                                'subject': m.SUBJECT||m.Subject||m.subject,
                                'exam': m.EXAM||m.exam||m.Exam,
                                'term': m.TERM || m.Term || m.term,
                                'year': m.YEAR || m.year || m.Year,
                                'date': m.Date || m.DATE || m.date,
                                'remarks': m.REMARKS || m.Remarks || m.remarks || m.REMARK || m.Remark || m.remark,
                                'marks': m.MARKS || m.marks || m.Marks,
                                'total': m.Total || m.total || m.TOTAL,

                            }
                        }).done(function (s) {
                            new PNotify({
                                title: 'Success',
                                text: ' Added Successfully',
                                type: 'success',
                                styling: 'bootstrap3'
                            })

                        }).fail(function (xhr, error) {
                            new PNotify({
                                title: 'Error',
                                text: xhr.responseText,
                                type: 'error',
                                styling: 'bootstrap3'
                            })

                        })

                    }
                })
            }

            else{
                $.ajax({
                    type: 'GET',
                    url: 'saveExcel',
                    data: {
                        'teacher': m.TEACHER||m.Teacher||m.teacher,
                        'student': m.STUDENT || m.student ||m.Student,
                        'form': m.FORM||m.Form||m.form,
                        'subject': m.SUBJECT||m.Subject||m.subject,
                        'exam': m.EXAM||m.exam||m.Exam,
                        'term': m.TERM || m.Term || m.term,
                        'year': m.YEAR || m.year || m.Year,
                        'date': m.Date || m.DATE || m.date,
                        'remarks': m.REMARKS || m.Remarks || m.remarks || m.REMARK || m.Remark || m.remark,
                        'marks': m.MARKS || m.marks || m.Marks,
                        'total': m.Total || m.total || m.TOTAL
                    }
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: ' Added Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    })

                }).fail(function (xhr, error) {
                    new PNotify({
                        title: 'Error',
                        text: xhr.responseText,
                        type: 'error',
                        styling: 'bootstrap3'
                    })

                })



            }
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            })

        })
    })
}
function makeAndDownload(){
    $('#export').click(function() {
        var fCode=$('#class').val();
        var subject=$('#subject-id').val();
        var reg=$('#reg-code').val();;
        if(fCode==='' || subject===''||reg===''){
            bootbox.alert("Select class and subject and exam to export");
        }
        else {
            $.ajax({
                type: 'GET',
                url: "drawRecords",
                data: {
                    'code': fCode,
                    'subject': subject,
                    'exam':reg
                }
            }).done(function (s) {
                var q = s;

                var wb = XLSX.utils.book_new();
                wb.Props = {
                    Title: "Exam Records",
                    Subject: "Exam",
                    Author: "Gabu Dev",
                };


                //var obj = s[i];
                //console.log(obj);

                var result = q.map(function (e) {

                    return Object.keys(e).map(function (k) {

                        return e[k]
                    })
                });

                console.log(result);
                wb.SheetNames.push("Exam Marks");

                var ws = XLSX.utils.aoa_to_sheet(result);
                wb.Sheets["Exam Marks"] = ws;


                var wbout = XLSX.write(wb, {bookType: 'xlsx', type: 'binary'});


                saveAs(new Blob([s2ab(wbout)],{type:"application/vnd.ms-excel;charset=utf-8"}), 'marks.xlsx');
            });
        }
    })

}

function s2ab(s) {
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;
}
function clearPage() {
    $('#exam-rec-form').trigger("reset");
    jQuery('.select2-offscreen').select2('val', '');


    $('#reg-code').val('');
    $('#reg-name').val('');
    $('#class').val('');
    $('#dm').val('');
    $('#tea-id').val('');
    $('#tea-name').val('');
    $('#subject-id').val('');
    $('#subject-name').val('');
    $('#stud-id').val('');
    $('#stud-name').val('');


}
function studentImage(id){

    $("#avatar").fileinput('refresh',{
        overwriteInitial: true,
        maxFileSize: 1500,
        showClose: false,
        showCaption: false,
        showBrowse:false,
        showRemove:false,
        browseLabel: '',
        browseClass:'',
        removeLabel: '',
        browseIcon: '',
        removeIcon: '',
        removeTitle: 'Cancel or reset changes',
        elErrorContainer: '#kv-avatar-errors',
        msgErrorClass: 'alert alert-block alert-danger',
        defaultPreviewContent: '<img src="'+getContextPath()+'/protected/studentPhoto/' + id + '"  style="height:15em;width:200px">',
        layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
        allowedFileExtensions: ["jpg", "png", "gif"]
    });

}
function selectStudent(id) {
    $('#std-div').attr('select2-url',getContextPath()+'/protected/exams/pageStudent/'+id);
    var reg=$('#reg-code').val();
    var fCode=$('#class').val();
    var teacher=$('#tea-id').val();
    var subject=$('#subject-id').val();

    if($("#std-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "std-div",
            sort : 'admNo',
            change: function(e, a, v){
                $('#stud-id').val(e.added.stId)
                $('#stud-name').val(e.added.admNo)
                studentImage(e.added.stId);
                makeStudentTable(reg,fCode,teacher,subject,e.added.stId)
            },
            formatResult : function(a)
            {
                return a.admNo
            },
            formatSelection : function(a)
            {
                return a.admNo
            },
            initSelection: function (element, callback) {
                var code = $('#stud-id').val();
                var name = $('#stud-name').val();
                var data = {admNo:name,stId:code};
                callback(data);
            },
            id: "stId",
            placeholder:"Student",
        });
    }
}
function selectMyStudent() {
    if($("#mystd-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "mystd-div",
            sort : 'admNo',
            change: function(e, a, v){
                $('#mystud-id').val(e.added.stId)
                $('#mystud-name').val(e.added.admNo)
            },
            formatResult : function(a)
            {
                return a.admNo
            },
            formatSelection : function(a)
            {
                return a.admNo
            },
            initSelection: function (element, callback) {
                var code = $('#mystud-id').val();
                var name = $('#mystud-name').val();
                var data = {name:name,stId:code};
                callback(data);
            },
            id: "stId",
            placeholder:"Student",
        });
    }
}
function selectReg() {
    if($("#reg-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "reg-div",
            sort : 'examName',
            change: function(e, a, v){
                $('#reg-code').val(e.added.examCode)
                $('#reg-name').val(e.added.examName)
                makeExamRegisterTable(e.added.examCode);
                selectClass();
            },
            formatResult : function(a)
            {
                return a.examName
            },
            formatSelection : function(a)
            {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code').val();
                var name = $('#reg-name').val();
                var data = {examName:name,examCode:code};
                callback(data);
            },
            id: "examCode",
            placeholder:"Name",
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
                makeClassTable(id,e.added.classCode)
                selectTeacher(e.added.classCode);
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

function selectMyClass() {

    if($("#myclass-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "myclass-div",
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
            placeholder:"Select Class",
        });
    }}

function selectSubject(id) {

    var reg=$('#reg-code').val();
    var fCode=$('#class').val();
    var teacher=$('#tea-id').val();
    $('#subject-div').attr('select2-url',getContextPath()+'/protected/exams/pageSubject/'+id+'/'+fCode);

    if($("#subject-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "subject-div",
            sort : 'teacherSubjectCode',
            change: function(e, a, v){
                $('#subject-id').val(e.added.teacherSubjectCode)
                $('#subject-name').val(e.added.subjectName)
                makeSubjectTable(reg,fCode,teacher,e.added.teacherSubjectCode)
                selectStudent(fCode)
            },
            formatResult : function(a)
            {
                return a.subjectName;
            },
            formatSelection : function(a)
            {

                return a.subjectName;
            },
            initSelection: function (element, callback) {
                var code = $("#subject-id").val();
                var name = $("#subject-name").val();
                var data = {subjectName:name,teacherSubjectCode:code};
                callback(data);
            },
            id: "teacherSubjectCode",
            placeholder:"Subject",
        });
    }}

function selectMySubject() {

    if($("#mysubject-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "mysubject-div",
            sort : 'name',
            change: function(e, a, v){
                $('#mysubject-id').val(e.added.subjectCode)
                $('#mysubject-name').val(e.added.name)
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
                var code = $("#mysubject-id").val();
                var name = $("#mysubject-name").val();
                var data = {name:name,subjectCode:code};
                callback(data);
            },
            id: "subjectCode",
            placeholder:"Subject",
        });
    }}

function selectTeacher() {

    var id=$('#reg-code').val();
     var fCode=$('#class').val();
    if($("#teach-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "teach-div",
            sort : 'name',
            change: function(e, a, v){
                $('#tea-id').val(e.added.teacherCode)
                $('#tea-name').val(e.added.name)
                makeTeacherTable(id,fCode,e.added.teacherCode)
                selectSubject(e.added.teacherCode);
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
function selectYear() {
    if($("#year-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "year-div",
            sort : 'yearName',
            change: function(e, a, v){
                $('#year-code').val(e.added.yearCode)
                $('#year-name').val(e.added.yearName)
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
function makeExamRegisterTable(id) {
    var table=$('#exams-rec-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRecTable/'+id
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examRecordCode" },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.admNo
                    }
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.name
                    }
                }
            },
            { data: "examRecordCode",
                render: function ( data, type, full, meta ) {
                    return "0"
                }
            },
            { data: "teacherSubjects" ,
                render: function ( data, type, full, meta ) {
                    if(full.teacherSubjects!=null){
                        return full.teacherSubjects.subjects.name
                    }
                }
            },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                    if(full.year!=null){
                        return full.year.yearName
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
            { data: "mark"
            },
            { data: "outOff"
            },
            { data: "examRegister" ,
                render: function ( data, type, full, meta ) {
                    if(full.examRegister!==null){
                        return full.examRegister.examName;
                    }

                }
            }


        ]
    });
    $.fn.dataTable.ext.errMode = 'none';

    $('#exams-rec-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function makeClassTable(id,code) {
    var table=$('#exams-rec-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRecClassTable/'+id+'/'+code
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examRecordCode" },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.admNo
                    }
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.name
                    }
                }
            },
            { data: "examRecordCode",
                render: function ( data, type, full, meta ) {
                    return "0"
                }
            },
            { data: "teacherSubjects" ,
                render: function ( data, type, full, meta ) {
                    if(full.teacherSubjects!=null){
                        return full.teacherSubjects.subjects.name
                    }
                }
            },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                    if(full.year!=null){
                        return full.year.yearName
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
            { data: "mark"
            },
            { data: "outOff"
            },
            { data: "examRegister" ,
                render: function ( data, type, full, meta ) {
                    if(full.examRegister!==null){
                        return full.examRegister.examName;
                    }

                }
            }


        ]
    });
    $.fn.dataTable.ext.errMode = 'none';

    $('#exams-rec-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}

function makeTeacherTable(id,code,teacher) {
    var table=$('#exams-rec-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRecTeaTable/'+id+'/'+code+'/'+teacher
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examRecordCode" },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.admNo
                    }
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.name
                    }
                }
            },
            { data: "examRecordCode",
                render: function ( data, type, full, meta ) {
                    return "0"
                }
            },
            { data: "teacherSubjects" ,
                render: function ( data, type, full, meta ) {
                    if(full.teacherSubjects!=null){
                        return full.teacherSubjects.subjects.name
                    }
                }
            },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                    if(full.year!=null){
                        return full.year.yearName
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
            { data: "mark"
            },
            { data: "outOff"
            },
            { data: "examRegister" ,
                render: function ( data, type, full, meta ) {
                    if(full.examRegister!==null){
                        return full.examRegister.examName;
                    }

                }
            }


        ]
    });
    $.fn.dataTable.ext.errMode = 'none';

    $('#exams-rec-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function missExam(){

    $('#miss').click(function () {
        var reg=$('#reg-code').val();
        var fCode=$('#class').val();
        var std=$('#stud-id').val();
        var subject=$('#subject-id').val();
        console.log(reg+" "+fCode+" "+subject+" "+std);
        if (reg === '' || fCode === '' || std === '' || subject === '') {
            bootbox.alert('Select a student,exam,class and subject to Miss Exam');
        } else {
            $.ajax({
                type: 'GET',
                url: 'missExam',
                data:{
                    'student':std,
                    'subjects':subject,
                    'forms':fCode,
                    'exam':reg
                },
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Missed Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });

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
function undoMissExam(){

    $('#undo-miss').click(function () {
        var reg=$('#reg-code').val();
        var fCode=$('#class').val();
        var std=$('#stud-id').val();
        var subject=$('#subject-id').val();
        if (reg === '' || fCode === '' || std === '' || subject === '') {
            bootbox.alert('Select a student,exam,class and subject to Undo Missed Exam');
        } else {
            $.ajax({
                type: 'GET',
                url: 'undoMissExam',
                data:{
                    'student':std,
                    'subjects':subject,
                    'forms':fCode,
                    'exam':reg
                },
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Undone Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });

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

function tableClick(){
    $('#exams-rec-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData =$('#exams-rec-tbl').DataTable().rows('.active').data();
        $('#exam-rec-id').val(aData[0].examRecordCode)
        if(aData[0].term!=null) {
            $('#term-code').val(aData[0].term.termCode)
            $('#term-name').val(aData[0].term.termNumber)
            selectTerm();
        }
        if(aData[0].year!=null) {
            $('#year-code').val(aData[0].year.yearCode)
            $('#year-name').val(aData[0].year.yearName)
            selectYear();
        }
        if(aData[0].examRegister!=null) {
            $('#reg-code').val(aData[0].examRegister.examCode)
            $('#reg-name').val(aData[0].examRegister.examName)
            selectReg();
        }
        if(aData[0].forms!=null) {
            $('#class').val(aData[0].forms.classCode)
            $('#dm').val(aData[0].forms.abbr)
            selectClass()
        }
        if(aData[0].teachers!=null) {
            $('#tea-id').val(aData[0].teachers.teacherCode)
            $('#tea-name').val(aData[0].teachers.name)
            selectTeacher();
        }
        if(aData[0].teacherSubjects!=null) {
            $('#subject-id').val(aData[0].teacherSubjects.teacherSubjectCode)
            $('#subject-name').val(aData[0].teacherSubjects.subjectName)
            selectSubject(aData[0].teachers.teacherCode);
        }
        if(aData[0].student!=null) {
            studentImage(aData[0].student.stId)
            $('#stud-id').val(aData[0].student.stId)
            $('#stud-name').val(aData[0].student.admNo)
            selectStudent(aData[0].forms.classCode);

        }
        $('#exam-date').val(moment(aData[0].examDate).format('DD/MM/YYYY'))
        $('#marks').val(aData[0].mark)
        $('#out-off').val(aData[0].outOff)
        $('#grade').val(aData[0].gradingSystem.grade)
        $('#remarks').val(aData[0].remarks)

    })
}
function makeSubjectTable(id,code,teacher,subject) {
    var table=$('#exams-rec-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRecSubTable/'+id+'/'+code+'/'+teacher+'/'+subject
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examRecordCode" },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.admNo
                    }
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.name
                    }
                }
            },
            { data: "examRecordCode",
                render: function ( data, type, full, meta ) {
                    return "0"
                }
            },
            { data: "teacherSubjects" ,
                render: function ( data, type, full, meta ) {
                    if(full.teacherSubjects!=null){
                        return full.teacherSubjects.subjects.name
                    }
                }

            },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                    if(full.year!=null){
                        return full.year.yearName
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
            { data: "mark"
            },
            { data: "outOff"
            },
            { data: "examRegister" ,
                render: function ( data, type, full, meta ) {
                    if(full.examRegister!==null){
                        return full.examRegister.examName;
                    }

                }
            }


        ]
    });
    $.fn.dataTable.ext.errMode = 'none';

    $('#exams-rec-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    return table;
}

function makeStudentTable(id,code,teacher,subject,student) {
    var table=$('#exams-rec-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getExamRecStudTable/'+id+'/'+code+'/'+teacher+'/'+subject+'/'+student
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "examRecordCode" },

            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.admNo
                    }
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    if(full.student!=null){
                        return full.student.name
                    }
                }
            },
            { data: "examRecordCode",
                render: function ( data, type, full, meta ) {
                    return "0"
                }
            },
            { data: "teacherSubjects" ,
                render: function ( data, type, full, meta ) {
                    if(full.teacherSubjects!=null){
                        return full.teacherSubjects.subjects.name
                    }
                }
            },
            { data: "year" ,
                render: function ( data, type, full, meta ) {
                    if(full.year!=null){
                        return full.year.yearName
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
            { data: "mark"
            },
            { data: "outOff"
            },
            { data: "examRegister" ,
                render: function ( data, type, full, meta ) {
                    if(full.examRegister!==null){
                        return full.examRegister.examName;
                    }

                }
            }


        ]
    });
    $.fn.dataTable.ext.errMode = 'none';

    $('#exams-rec-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function saveExamRecord() {
    var $form= $("#exam-rec-form");
    var validator = $form.validate();

    $('#exam-rec-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveExamRecord',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
               $('#grade').val(s.gradingSystem.grade);
         $('#exams-rec-tbl').DataTable().ajax.reload();
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
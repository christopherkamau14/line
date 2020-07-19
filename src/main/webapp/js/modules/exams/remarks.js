// jshint ignore: start
$(document).ready(function () {
    selectReg();
    selectTerm();
    selectYear();
    selectClass();
    position();
    reportForm();
    $('#h-rpt').hide();
    });
    function position() {
        $('#s-position').click(function () {
            var forms=$('#class').val();
            var exam=$('#reg-code').val();
            var student=$('#stud-id').val();
            if(forms===''||exam===''||student===''){
                bootbox.alert('Select an exam,class and Student position first');
            }
            else{
                $('#myPleaseWait').modal('show');
                $.ajax({
                    type: 'GET',
                    url: 'position',
                    data: {
                        'forms': forms,
                        'exam':exam,
                        'student':student
                    },
                })
                    .done(function (s) {
                        $('#myPleaseWait').modal('hide');
                        $('#subjects').val(s.subjectsEntered);
                        $('#marks').val(s.marks);
                        $('#grade').val(s.grade);
                        $('#class-grade').val(s.classGrade);
                        $('#total').val(s.totalMarks);
                        $('#position').val(s.position);
                        $('#class-mean').val(s.classMarks);
                        $('#subjects').val(s.subjectsEntered);
                        $('#student-id').val($('#stud-id').val());

                        $('#for-id').val($('#class').val());

                        $('#exam-id').val($('#reg-code').val());

                        $('#h-rpt').show();


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
function reportForm() {

}
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function selectStudent(id) {
    $('#std-div').attr('select2-url',getContextPath()+'/protected/exams/pageStudent/'+id);
     console.log(id)

    if($("#std-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "std-div",
            sort : 'admNo',
            change: function(e, a, v){
                $('#stud-id').val(e.added.stId)
                $('#stud-name').val(e.added.admNo)

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
                selectStudent(e.added.classCode)
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
function selectReg() {
    if ($("#reg-div").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code').val(e.added.examCode)
                $('#reg-name').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code').val();
                var name = $('#reg-name').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
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


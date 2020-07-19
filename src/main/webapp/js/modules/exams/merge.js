// jshint ignore: start


$(document).ready(function () {
    selectReg()
    selectReg1()
    selectReg2()
    selectReg3()
    selectReg4()
    selectReg5()
    selectReg6()
    selectReg7()
    selectReg8()
    selectReg9()
    selectReg10()
    selectClass()
    selectSubject()
    selectYear()
    selectTerm()
    selectExamNumber()
    hideElements()
    mergeExam()

})
function selectExamNumber() {
$('#sel-no').on('change',function () {
if($("#sel-no").val()==="3"){
    $('#d-3').show();
}
else if($("#sel-no").val()==="4"){
    $('#d-3').show();
    $('#d-4').show();


}
else if($("#sel-no").val()==="5"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();


}
else if($("#sel-no").val()==="6"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();
    $('#d-6').show();


}
else if($("#sel-no").val()==="7"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();
    $('#d-6').show();
    $('#d-7').show();


}
else if($("#sel-no").val()==="8"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();
    $('#d-6').show();
    $('#d-7').show();
    $('#d-8').show();


}
else if($("#sel-no").val()==="9"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();
    $('#d-6').show();
    $('#d-7').show();
    $('#d-8').show();
    $('#d-9').show();

}
else if($("#sel-no").val()==="10"){
    $('#d-3').show();
    $('#d-4').show();
    $('#d-5').show();
    $('#d-6').show();
    $('#d-7').show();
    $('#d-8').show();
    $('#d-9').show();
    $('#d-10').show();

}
else if($("#sel-no").val()==="2"){
hideElements();
}
})
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
function hideElements() {
    $('#d-3').hide();
    $('#d-4').hide();
    $('#d-5').hide();
    $('#d-6').hide();
    $('#d-7').hide();
    $('#d-8').hide();
    $('#d-9').hide();
    $('#d-10').hide();
}
function selectSubject() {

    if($("#subject-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "subject-div",
            sort : 'name',
            change: function(e, a, v){
                $('#subject-id').val(e.added.subjectCode)
                $('#subject-name').val(e.added.name)
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
                var code = $("#subject-id").val();
                var name = $("#subject-name").val();
                var data = {name:name,subjectCode:code};
                callback(data);
            },
            id: "subjectCode",
            placeholder:"Subject",
        });
    }}
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
            placeholder:"Select Class",
        });
    }}
function selectReg() {
    if($("#reg-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "reg-div",
            sort : 'examName',
            change: function(e, a, v){
                $('#reg-code').val(e.added.examCode)
                $('#reg-name').val(e.added.examName)

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
function selectReg1() {
    if ($("#reg-div1").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div1",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code1').val(e.added.examCode)
                $('#reg-name1').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code1').val();
                var name = $('#reg-name1').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg2() {
    if ($("#reg-div2").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div2",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code2').val(e.added.examCode)
                $('#reg-name2').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code2').val();
                var name = $('#reg-name2').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg3() {
    if ($("#reg-div3").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div3",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code3').val(e.added.examCode)
                $('#reg-name3').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code3').val();
                var name = $('#reg-name3').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg4() {
    if ($("#reg-div4").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div4",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code4').val(e.added.examCode)
                $('#reg-name4').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code4').val();
                var name = $('#reg-name4').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg5() {
    if ($("#reg-div5").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div5",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code5').val(e.added.examCode)
                $('#reg-name5').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code5').val();
                var name = $('#reg-name5').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg6() {
    if($("#reg-div6").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "reg-div6",
            sort : 'examName',
            change: function(e, a, v){
                $('#reg-code6').val(e.added.examCode)
                $('#reg-name6').val(e.added.examName)

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
                var code = $('#reg-code6').val();
                var name = $('#reg-name6').val();
                var data = {examName:name,examCode:code};
                callback(data);
            },
            id: "examCode",
            placeholder:"Name",
        });
    }
}
function selectReg7() {
    if ($("#reg-div7").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div7",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code7').val(e.added.examCode)
                $('#reg-name7').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code7').val();
                var name = $('#reg-name7').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg8() {
    if ($("#reg-div8").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div8",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code8').val(e.added.examCode)
                $('#reg-name8').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code8').val();
                var name = $('#reg-name8').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg9() {
    if ($("#reg-div9").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div9",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code9').val(e.added.examCode)
                $('#reg-name9').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code9').val();
                var name = $('#reg-name9').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function selectReg10() {
    if ($("#reg-div10").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "reg-div10",
            sort: 'examName',
            change: function (e, a, v) {
                $('#reg-code10').val(e.added.examCode)
                $('#reg-name10').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code10').val();
                var name = $('#reg-name10').val();
                var data = {examName: name, examCode: code};
                callback(data);
            },
            id: "examCode",
            placeholder: "Name",
        });
    }
}
function mergeExam() {
    var $form= $("#merge-form");
    var validator = $form.validate();

    $('#merge-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }
           $('#myPleaseWait').modal('show');
            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'mergeExam',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                $('#myPleaseWait').modal('hide');

                new PNotify({
                    title: 'Success',
                    text: 'Record Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });

            }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });

}
// jshint ignore: start

$(document).ready(function () {
    hideFromUi();
selectTerm();
selectYear();
selectClass();
selectReg();
processExam();

})
function processExam() {
    $('#process').click(function () {
        var selected=$('#action').val()
        var exam=$('#reg-code').val()
        var forms=$('#class').val()
        if(exam===''||forms===''||selected===''){
          bootbox.alert('Select class and exam then process');
        }
        else{
            $('#myPleaseWait').modal('show');

            if(selected==='PO'){
                $.ajax({
                    type: 'GET',
                    url: 'positionExam',
                    data: {
                        'forms': forms,
                        'exam':exam,
                    },
                })
                    .done(function (s) {
                        $('#myPleaseWait').modal('hide');
                        new PNotify({
                            title: 'Success',
                            text: 'Positioned Successfully',
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
            }
            else if(selected==='LE'){
                $.ajax({
                    type: 'GET',
                    url: 'lockExam',
                    data: {
                        'exam':exam,
                    },
                })
                    .done(function (s) {
                        $('#myPleaseWait').modal('hide');
                        new PNotify({
                            title: 'Success',
                            text: 'Locked Successfully',
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
            }
            else if(selected==='UNLE'){
                $.ajax({
                    type: 'GET',
                    url: 'unLockExam',
                    data: {
                        'exam':exam,
                    },
                })
                    .done(function (s) {
                        $('#myPleaseWait').modal('hide');
                        new PNotify({
                            title: 'Success',
                            text: 'UnLocked Successfully',
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
            }
            else if (selected==='ER'){
                $.ajax({
                    type: 'GET',
                    url: 'emailExam',
                    data: {
                        'forms': forms,
                        'exam':exam,
                    },
                })
                    .done(function (s) {
                        $('#myPleaseWait').modal('hide');
                        new PNotify({
                            title: 'Success',
                            text: 'Emailed Successfully',
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
            }
            else if(selected==='SMS'){
               bootbox.alert('SMS still not worked on')
            }
        }
    })



}
function hideFromUi() {
    $('#hide-miss').hide();
    $('#hide-sub-analys').hide();
    $('#hide-class-analys').hide();
    $('#hide-gender-analys').hide();
    $('#hide-b-class-analys').hide();
    $('#hide-gender-merit').hide();
    $('#hide-subject-merit').hide();
    $('#hide-va-merit').hide();
    $('#hide-single-merit').hide();
    $('#hide-combined-merit').hide()
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
                $('#id-missed').val(e.added.examCode)
                $('#sub-analys').val(e.added.examCode)
                $('#class-analys').val(e.added.examCode)
                $('#gender-analys').val(e.added.examCode)
                $('#b-class-analys').val(e.added.examCode)
                $('#gender-merit').val(e.added.examCode)
                $('#subject-merit').val(e.added.examCode)
                $('#va-merit').val(e.added.examCode)
                $('#single-merit').val(e.added.examCode)
                $('#combined-merit').val(e.added.examCode)


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
                $('#id-forms').val(e.added.classCode)
                $('#sub-forms').val(e.added.classCode)
                $('#class-forms').val(e.added.classCode)
                $('#gender-forms').val(e.added.classCode)
                $('#b-class-forms').val(e.added.classCode)
                $('#gender-forms').val(e.added.classCode)
                $('#subject-merit-forms').val(e.added.classCode)
                $('#va-merit-forms').val(e.added.classCode)
                $('#single-merit-forms').val(e.added.classCode)
                $('#combined-merit-forms').val(e.added.classCode)
                $('#gender-merit-forms').val(e.added.classCode)
                $('#hide-miss').show();
                $('#hide-sub-analys').show();
                $('#hide-class-analys').show();
                $('#hide-gender-analys').show();
                $('#hide-b-class-analys').show();
                $('#hide-gender-merit').show();
                $('#hide-subject-merit').show();
                $('#hide-va-merit').show();
                $('#hide-single-merit').show();
                $('#hide-combined-merit').show()
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
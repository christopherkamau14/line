// jshint ignore: start

$(document).ready(function () {
    selectReg()
    selectTerm()
    selectYear()
    selectRegCombine()
    mergeExam()
})
function mergeExam() {
    var $form= $("#exam-form");
    var validator = $form.validate();

    $('#exam-form')
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
                url: 'combineExam',
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
function selectRegCombine() {
    if ($("#com-div").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "com-div",
            sort: 'examName',
            change: function (e, a, v) {
                $('#com-code').val(e.added.examCode)
                $('#com-name').val(e.added.examName)

            },
            formatResult: function (a) {
                return a.examName
            },
            formatSelection: function (a) {
                return a.examName
            },
            initSelection: function (element, callback) {
                var code = $('#com-code').val();
                var name = $('#com-name').val();
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


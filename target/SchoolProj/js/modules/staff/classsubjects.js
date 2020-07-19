$(document).ready(function () {

    selectMyClass();
    selectMySubject();
    pullSubModal();
    saveClassSubjects();
    createClassSubjectsTbl();
})
function saveClassSubjects() {
    var $form= $("#class-subject-form");
    var validator = $form.validate();

    $('#class-subject-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveClassSubjects',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#class-subjects-tbl").DataTable().ajax.reload();
                $('#classsubjectsModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#classsubjectsModal').on('hidden.bs.modal', function () {
        $('#class-subject-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function createClassSubjectsTbl() {
    var table=$('#class-subjects-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getClassSubjects',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "subjects",
                render: function ( data, type, full, meta ) {
                    if (full.subjects !== null) {
                        return full.subjects.name;
                    }
                    else {
                        "";
                    }
                }
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    if (full.forms !== null) {
                        return full.forms.abbr;
                    }
                    else {
                        "";
                    }
                }
            },
            { data: "weekLessons" },
            { data: "doubleLessons"},
            { data: "strokedWith"},
            { data: "strokedSubject"},

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#subjects-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
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
function selectMySubject() {

    if($("#my-subject-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "my-subject-div",
            sort : 'name',
            change: function(e, a, v){
                $('#mysubject').val(e.added.subjectCode)
                $('#mysm').val(e.added.name)
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
                var code = $("#mysubject").val();
                var name = $("#mysm").val();
                var data = {name:name,subjectCode:code};
                callback(data);
            },
            id: "subjectCode",
            placeholder:"Subject",
        });
    }}
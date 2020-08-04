// jshint ignore: start

$(document).ready(function () {
saveLesson();
getLessonsTab();
deleteLessons();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'LT',
            sideBySide: true
        });

    });
})
function saveLesson() {
    $('#btn-save-lesson').on('click',function () {
        if($("#lesson-code").val()==='' ||$("#lesson-name").val()===''||
            $("#lesson-start").val()===''||$("#lesson-end").val()===''
        ){
            bootbox.alert("Input a lesson to save");
        }else {
            var $currForm = $('#lessons-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveLesson";
            var request = $.post(url, data);
            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Lesson created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#lesson-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#lesson-id").val("");
                $("#lesson-name").val("");
                $("#lesson-code").val("");

            });
            request.error(function (jqXHR, textStatus, errorThrown) {
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
function getLessonsTab() {

    var table=$('#lesson-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getLessonTable'
        },
        "scrollY": "400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 20,
        destroy: true,
        columns: [
            { data: "lessonCode" },
            { data: "lessonName" },
            {data:"lessonStart",
                render: function ( data, type, full, meta ) {
                return moment(full.lessonStart).format('hh:mm');
                }

            },
            {data:"lessonEnd",
                render: function ( data, type, full, meta ) {
                    return moment(full.lessonEnd).format('hh:mm');
                }

            },
            { data: "lessonCode" }
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#exam-reg-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#lesson-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#lesson-id').val(aData[0].lessonId);
        $('#lesson-code').val(aData[0].lessonCode);
        $('#lesson-name').val(aData[0].lessonName);
        $('#lesson-start').val(moment(aData[0].lessonStart).format('hh:mm:ss A'));
        $('#lesson-end').val(moment(aData[0].lessonEnd).format('hh:mm:ss A'));
        if(aData[0].autoLesson==='Y'){
            $('#lesson-auto').prop('checked',true)
        }
        else{
            $('#lesson-auto').prop('checked',false)
        }

    })
    return table;
}
function deleteLessons(){
    $('#btn-delete-lesson').on('click',function () {
        var data = $('#lesson-id').val();
        if(data===''){
            bootbox.alert("Select Lesson to delete");
        }
        else {
            var url = 'deleteLessons/' + data;
            bootbox.confirm("Are you sure want to delete this Lesson?", function (result) {
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
                        $("#lesson-tbl").DataTable().ajax.reload();
                        $("#lesson-id").val("");
                        $("#lesson-name").val("");
                        $("#lesson-code").val("");

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
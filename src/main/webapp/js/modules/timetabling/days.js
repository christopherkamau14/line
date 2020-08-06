// jshint ignore: start

$(document).ready(function () {
 saveDay()
getDaysTab()
    deleteDays()
})
function saveDay() {
    $('#btn-save-day').on('click',function () {
        if($("#name").val()===''){
            bootbox.alert("Input a day to save");
        }else {
            var $currForm = $('#days-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveDay";
            var request = $.post(url, data);
            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Day created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#days-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#day-id").val("");
                $("#name").val("");
                $("#code").val("");

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
function getDaysTab() {

    var table=$('#days-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getDaysTable'
        },
        scrollX:true,
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "dayCode" },
            { data: "name" },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#exam-reg-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#days-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#day-id').val(aData[0].dayCode);
        $('#code').val(aData[0].dayCode);
        $('#name').val(aData[0].name);

    })
    return table;
}
function deleteDays(){
    $('#btn-delete-day').on('click',function () {
        var data = $('#day-id').val();
        if(data===''){
            bootbox.alert("Select Day to delete");
        }
        else {
            var url = 'deleteDays/' + data;
            bootbox.confirm("Are you sure want to delete this Day?", function (result) {
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
                        $("#days-tbl").DataTable().ajax.reload();
                        $("#day-id").val("");
                        $("#name").val("");
                        $("#code").val("");

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
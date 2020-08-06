// jshint ignore: start
$(function() {

    $(document).ready(function () {
       saveStore()
        deleteStores()
        getStoresTab()
        saveStationery()
        deleteStationery()
        getStationeryTab()
    })
});
function saveStore(){
    $('#btn-save-store').on('click',function () {
        if($("#name").val()===''){
            bootbox.alert("Input a store to save");
        }else {
            var $currForm = $('#stores-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveStore";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Store created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#stores-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#store-code").val("");
                $("#name").val("");
                $("#code").val("");
                $('#lesson-auto').prop('checked',false);

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
function getStoresTab() {

    var table=$('#stores-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getStoresTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "storeName" },
            { data: "stationery" },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#stores-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#stores-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#store-code').val(aData[0].storeCode);
        $('#code').val(aData[0].storeCode);
        $('#name').val(aData[0].storeName);
        if(aData[0].stationery=="Y"){
            $('#lesson-auto').prop('checked',true);
        }
        else{
            $('#lesson-auto').prop('checked',false);
        }
    })
    return table;
}
function deleteStores() {
    $('#btn-delete-store').on('click', function () {
        var data = $('#store-code').val();
        if (data === '') {
            bootbox.alert("Select Store to delete");
        } else {
            var url = 'deleteStore/' + data;
            bootbox.confirm("Are you sure want to delete this Store?", function (result) {
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
                        $("#stores-tbl").DataTable().ajax.reload();
                        $("#store-code").val("");
                        $("#name").val("");
                        $("#code").val("");
                        $('#lesson-auto').prop('checked',false);
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
function saveStationery(){
    $('#btn-save-stationery').on('click',function () {
        if($("#name").val()===''){
            bootbox.alert("Input a stationery to save");
        }else {
            var $currForm = $('#stationery-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveStationery";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Stationery created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#stationery-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#stationery-code").val("");
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
function getStationeryTab() {

    var table=$('#stationery-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getStationeryTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "stationeryName" },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#stationery-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#stationery-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#stationery-code').val(aData[0].stationeryCode);
        $('#code').val(aData[0].stationeryCode);
        $('#name').val(aData[0].stationeryName);
    })
    return table;
}
function deleteStationery() {
    $('#btn-delete-stationery').on('click', function () {
        var data = $('#stationery-code').val();
        if (data === '') {
            bootbox.alert("Select Stationery to delete");
        } else {
            var url = 'deleteStationery/' + data;
            bootbox.confirm("Are you sure want to delete this Stationery?", function (result) {
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
                        $("#stationery-tbl").DataTable().ajax.reload();
                        $("#stationery-code").val("");
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
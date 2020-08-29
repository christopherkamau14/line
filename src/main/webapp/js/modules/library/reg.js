// jshint ignore: start
$(function() {

    $(document).ready(function () {
        selectStore()
        selectStationery()
        getUser()
        saveStationeryRegister()
        getStatRegTab()
        deleteStatReg()
        $(".datepicker-input").each(function () {
            $(this).datetimepicker({
                format: 'DD/MM/YYYY',
                defaultDate: new Date()
            });
        })
    });
});
function getUser(){
    $.ajax({
        type: 'GET',
        url: 'getCurrentUser',
    }).done(function (s) {
        $("#user-name").val(s.username);
        $("#reg-by").val(s.id);
    }).fail(function (xhr, error) {
        new PNotify({
            title: 'Error',
            text: xhr.responseText,
            type: 'error',
            styling: 'bootstrap3'
        });
    })
}
function selectStore() {
    if($("#store-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "store-div",
            sort : 'storeName',
            change: function(e, a, v){
                $('#store-id').val(e.added.storeCode)
                $('#store-name').val(e.added.storeName)
            },
            formatResult : function(a)
            {
                return a.storeName
            },
            formatSelection : function(a)
            {
                return a.storeName
            },
            initSelection: function (element, callback) {
                var code = $('#store-id').val();
                var name = $('#store-name').val();
                var data = {storeName:name,storeCode:code};
                callback(data);
            },
            id: "storeCode",
            placeholder:"Store",
        });
    }
}
function selectStationery() {
    if($("#stationery-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "stationery-div",
            sort : 'stationeryName',
            change: function(e, a, v){
                $('#stationery-code').val(e.added.stationeryCode)
                $('#stationery-name').val(e.added.stationeryName)
            },
            formatResult : function(a)
            {
                return a.stationeryName
            },
            formatSelection : function(a)
            {
                return a.stationeryName
            },
            initSelection: function (element, callback) {
                var code = $('#stationery-code').val();
                var name = $('#stationery-name').val();
                var data = {stationeryName:name,stationeryCode:code};
                callback(data);
            },
            id: "stationeryCode",
            placeholder:"Stationery",
        });
    }
}
function newCategory(){
    $('#btn-new-cat').click(function (){
        window.location.reload();
    })
}

function saveStationeryRegister(){
    $('#btn-save-reg').on('click',function () {
        if($("#units").val()===''){
            bootbox.alert("Input a Record to save");
        }else {
            var $currForm = $('#stationery-reg-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveStationeryRegister";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Record created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#stationery-reg-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#stat-reg-code").val("");
                $("#units").val("");

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
function getStatRegTab() {

    var table=$('#stationery-reg-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getStatRegTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "stationery",
                render: function ( data, type, full, meta ) {
                if(full.stationery!==null){
                    return full.stationery.stationeryName
                }
                }
            },
            { data: "issueDate",
                render: function ( data, type, full, meta ) {
                    if (full.issueDate !== null) {

                        return moment(full.issueDate).format('DD/MM/YYYY');
                    }
                }
            },
            { data: "registeredBy",
                render: function ( data, type, full, meta ) {
                    if(full.registeredBy!==null){
                        return full.registeredBy.username
                    }
                }
            },
            { data: "units" },
            { data: "stores",
                render: function ( data, type, full, meta ) {
                    if(full.stores!==null){
                        return full.stores.storeName
                    }
                }
            },
            { data: "totalUnits" },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#stationery-reg-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#stationery-reg-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        if(aData[0].stationery!==null){
            $('#stationery-code').val(aData[0].stationery.stationeryCode)
            $('#stationery-name').val(aData[0].stationery.stationeryName)
            selectStationery()
        }
        if(aData[0].stores!==null){
            $('#store-id').val(aData[0].stores.storeCode)
            $('#store-name').val(aData[0].stores.storeName)
            selectStore()
        }
        $('#units').val(aData[0].units);
        $('#stat-reg-code').val(aData[0].stationeryRegCode);

        if(aData[0].issueDate!==null){
            $('#issue-date').val(moment(s.issueDate).format('DD/MM/YYYY'))
        }

    })
    return table;
}
function deleteStatReg() {
    $('#btn-delete-reg').on('click', function () {
        var data = $('#stat-reg-code').val();
        if (data === '') {
            bootbox.alert("Select Record to delete");
        } else {
            var url = 'deleteStatReg/' + data;
            bootbox.confirm("Are you sure want to delete this Record?", function (result) {
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
                        $("#stationery-reg-tbl").DataTable().ajax.reload();
                        $("#units").val("");
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
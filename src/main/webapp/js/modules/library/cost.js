// jshint ignore: start
$(function() {

    $(document).ready(function () {
        selectCost()
        saveBookCosts()
        getBookCostTab()
        deleteBookCosts()
        newIssue()
    });
});
function newIssue(){
    $('#btn-new-cost').click(function (){
        window.location.reload();
    })
}
function saveBookCosts(){
    $('#btn-save-costs').on('click',function () {
        if($('#cost').val()===''){
            bootbox.alert('No costs selected to imput')
        }
        else {
            var $currForm = $('#costs-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveBookCosts";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Costs added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#cost-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#cost-code").val("");

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
function getBookCostTab() {

    var table=$('#cost-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getBookCostTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "title"},
            { data: "cost"},
            { data: "issuedDate",
                render: function ( data, type, full, meta ) {
                    if (full.issuedDate !== null) {

                        return moment(full.issuedDate).format('DD/MM/YYYY');
                    }
                }
            },
            { data: "user",
                render: function ( data, type, full, meta ) {
                    if(full.user!==null){
                        return full.user.username
                    }
                }
            },
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#cost-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#cost-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#cost-code').val(aData[0].costCode)
        if(aData[0].bookRegister!=null) {
            $('#reg-code').val(aData[0].bookRegister.bookRegCode)
            $('#reg-name').val(aData[0].bookRegister.title)
            selectCost()
        }
        if(aData[0].cost!=null){
            $('#cost').val(aData[0].cost)
        }

    })
    return table;
}
function selectCost() {

    if($("#reg-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "reg-div",
            sort : 'title',
            change: function(e, a, v){
                $('#reg-code').val(e.added.bookRegCode)
                $('#reg-name').val(e.added.title)
                //selectTitle(e.added.callNo)
            },
            formatResult : function(a)
            {
                return a.title;
            },
            formatSelection : function(a)
            {

                return a.title;
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code').val();
                var name = $('#reg-name').val();
                var data = {title:name,bookRegCode:code};
                callback(data);
            },
            id: "bookRegCode",
            placeholder:"Call Number",
        });
    }}


function deleteBookCosts(){
    $('#btn-delete-costs').click(function (){
        if($('#cost-code').val()===''){
            bootbox.alert('No Cost selected to delete')
        }
        else{
            var code=$('#cost-code').val()
            $.ajax({
                type:"GET",
                url:"deleteBookCost/"+code

            }).done(function (){
                new PNotify({
                    title: 'Success',
                    text: 'Cost deleted Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#cost-code').val("");
                $('#cost-tbl').DataTable().ajax.reload();
            }).fail(function (jqXHR,errorThrown,errorStatus){
                new PNotify({
                    title: 'Error',
                    text: jqXHR.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
            })
        }
    })

}
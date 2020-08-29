// jshint ignore: start
$(function() {

    $(document).ready(function () {
        selectStore();
        selectClassification()
        selectShelves()
        saveBookRegister()
        getBookRegTab()
        deleteBookReg()
        newBookStore()
        drawCallNo()
        $(".datepicker-input").each(function () {
            $(this).datetimepicker({
                format: 'DD/MM/YYYY',
                defaultDate: new Date()
            });
        })
    });
});
function newBookStore(){
    $('#btn-new-bookreg').click(function (){
        window.location.reload();
    })
}
function drawCallNo() {
    $('#call-no').on('input', function () {
        $('#book-reg-tbl').DataTable()
            .columns(6)
            .search(this.value)
            .draw();
    });
}
function selectStore() {
    if($("#stores-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "stores-div",
            sort : 'storeName',
            change: function(e, a, v){
                $('#store-code').val(e.added.storeCode)
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
                var code = $('#store-code').val();
                var name = $('#store-name').val();
                var data = {storeName:name,storeCode:code};
                callback(data);
            },
            id: "storeCode",
            placeholder:"Store",
        });
    }
}
function selectClassification() {
    if($("#classify-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "classify-div",
            sort : 'classificationName',
            change: function(e, a, v){
                $('#classification-code').val(e.added.classificationCode)
                $('#classification-name').val(e.added.classificationName)
            },
            formatResult : function(a)
            {
                return a.classificationName
            },
            formatSelection : function(a)
            {
                return a.classificationName
            },
            initSelection: function (element, callback) {
                var code = $('#classification-code').val();
                var name = $('#classification-name').val();
                var data = {classificationName:name,classificationCode:code};
                callback(data);
            },
            id: "classificationCode",
            placeholder:"Classification",
        });
    }
}
function selectShelves() {
    if($("#shelves-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "shelves-div",
            sort : 'shelfName',
            change: function(e, a, v){
                $('#shelves-code').val(e.added.shelfId)
                $('#shelves-name').val(e.added.shelfName)
            },
            formatResult : function(a)
            {
                return a.shelfName
            },
            formatSelection : function(a)
            {
                return a.shelfName
            },
            initSelection: function (element, callback) {
                var code = $('#shelves-code').val();
                var name = $('#shelves-name').val();
                var data = {shelfName:name,shelfId:code};
                callback(data);
            },
            id: "shelfId",
            placeholder:"Shelves",
        });
    }
}

function saveBookRegister(){
    $('#btn-save-bookreg').on('click',function () {
        if($("#copies").val()===''){
            bootbox.alert("Input a Record to save");
        }else {
            var $currForm = $('#book-reg-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveBookRegister";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Record created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#book-reg-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#book-reg-code").val("");

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
function getBookRegTab() {

    var table=$('#book-reg-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getBookRegTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "bookRegCode"},
            { data: "title"},
            { data: "publisher"},
            { data: "author"},
            { data: "isbnNo"},
            { data: "year"},
            { data: "callNo"},
            { data: "issueDate",
                render: function ( data, type, full, meta ) {
                    if (full.issueDate !== null) {

                        return moment(full.issueDate).format('DD/MM/YYYY');
                    }
                }
            },
            { data: "units"}


        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#book-reg-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#book-reg-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#book-reg-code').val(aData[0].bookRegCode)
        if(aData[0].shelves!==null){
            $('#shelves-code').val(aData[0].shelves.shelfId)
            $('#shelves-name').val(aData[0].shelves.shelfName)
            selectShelves()
        }
        if(aData[0].stores!==null){
            $('#store-code').val(aData[0].stores.storeCode)
            $('#store-name').val(aData[0].stores.storeName)
            selectStore()
        }
        if(aData[0].bookClass!==null){
            $('#classification-code').val(aData[0].bookClass.classificationCode)
            $('#classification-name').val(aData[0].bookClass.classificationName)
            selectClassification()
        }
        $('#publisher').val(aData[0].publisher);
        $('#author').val(aData[0].author);
        $('#year').val(aData[0].year);
        $('#units').val(aData[0].units)
        $('#class-no').val(aData[0].classNo);
        $('#call-nos').val(aData[0].callNo);
        $('#title').val(aData[0].title)
        $('#isbn-no').val(aData[0].isbnNo);
        if(aData[0].shortLoan==="Y"){
            $('#short-loan').prop('checked',true);
        }
        else{
            $('#short-loan').prop('checked',false);
        }
        if(aData[0].issueDate!==null){
            $('#issue-date').val(moment(aData[0].issueDate).format('DD/MM/YYYY'))
        }

    })
    return table;
}
function deleteBookReg() {
    $('#btn-delete-bookreg').on('click', function () {
        var data = $('#book-reg-code').val();
        if (data === '') {
            bootbox.alert("Select Record to delete");
        } else {
            var url = 'deleteBookReg/' + data;
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
                        $("#book-reg-tbl").DataTable().ajax.reload();
                       $("#book-reg-code").val("");
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
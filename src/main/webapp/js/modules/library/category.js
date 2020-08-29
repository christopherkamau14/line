// jshint ignore: start
$(function() {

    $(document).ready(function () {
        saveCategory()
        getCategoryTab()
        deleteCategory()
        newCategory()
        newClassification()
        saveClassification()
        getClassificationTab()
        deleteClassification()
    })
});
function newCategory(){
    $('#btn-new-cat').click(function (){
        window.location.reload();
    })
}
function newClassification(){
    $('#btn-new-classication').click(function (){
        window.location.reload();
    })
}
function saveCategory(){
    $('#btn-save-cat').on('click',function () {
        if($("#name").val()==='' || $("#duration").val()===''|| $("#duration-type").val()===''|| $("#charges").val()==='' ){
            bootbox.alert("Input a store to save");
        }else {
            var $currForm = $('#category-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveCategory";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Category created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#category-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#category-id").val("");
                $("#name").val("");
                $("#code").val("");
                $("#book-number").val("");
                $("#duration").val("");
                $("#duration-type").val("");
                $("#charges").val("");
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
function getCategoryTab() {

    var table=$('#category-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getCategoryTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "categoryName" },
            { data: "duration" },
            { data: "durationType" },
            { data: "bookNumber" },
            { data: "charges" }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#category-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#category-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#category-id').val(aData[0].categoryId);
        $('#code').val(aData[0].categoryId);
        $('#name').val(aData[0].categoryName);
        $('#duration').val(aData[0].duration);
        $('#duration-type').val(aData[0].durationType);
        $("#book-number").val(aData[0].bookNumber);

        $('#charges').val(aData[0].charges);

    })
    return table;
}
function deleteCategory() {
    $('#btn-delete-cat').on('click', function () {
        var data = $('#category-id').val();
        if (data === '') {
            bootbox.alert("Select Category to delete");
        } else {
            var url = 'deleteCategory/' + data;
            bootbox.confirm("Are you sure want to delete this Category?", function (result) {
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
                        $("#category-tbl").DataTable().ajax.reload();
                        $("#category-id").val("");
                        $("#name").val("");
                        $("#code").val("");
                        $("#duration").val("");
                        $("#duration-type").val("");
                        $("#charges").val("");
                        $("#book-number").val("");
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

function saveClassification(){
    $('#btn-save-classication').on('click',function () {
        if($("#name").val()==='' ){
            bootbox.alert("Input a Book Class to save");
        }else {
            var $currForm = $('#classication-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveClassification";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Category created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#classication-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#classication-id").val("");
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
function getClassificationTab() {

    var table=$('#classication-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getClassificationTbl'
        },
        scrollY:"400px",
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "classificationCode" },
            { data: "classificationName" }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#classication-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#classication-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#classication-id').val(aData[0].classificationCode);
        $('#code').val(aData[0].classificationCode);
        $('#name').val(aData[0].classificationName);

    })
    return table;
}
function deleteClassification() {
    $('#btn-delete-classication').on('click', function () {
        var data = $('#classication-id').val();
        if (data === '') {
            bootbox.alert("Select Book Class to delete");
        } else {
            var url = 'deleteClassification/' + data;
            bootbox.confirm("Are you sure want to delete this Book class?", function (result) {
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
                        $("#classication-tbl").DataTable().ajax.reload();
                        $("#classication-id").val("");
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
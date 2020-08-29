// jshint ignore: start
$(function() {

    $(document).ready(function () {
       // $('#r-div').hide()
        $('#c-div').hide()
        $('#t-div').hide()
        $('#s-div').hide()
     selectUser()
        selectBorrower()
        getItemIssueTab()
        saveItemsIssue()
        drawCNo()
        deleteItemsIssue()
        returnItem()
newIssue()
        $(".datepicker-input").each(function () {
            $(this).datetimepicker({
                format: 'DD/MM/YYYY',
                defaultDate: new Date()
            });
        })
    });
});
function newIssue(){
    $('#btn-new-itemissue').click(function (){
        window.location.reload();
    })
}
function saveItemsIssue(){
    $('#btn-save-itemissue').on('click',function () {

        if($('#item-name').val()===''){
            bootbox.alert('No Item selected to issue')
        }
        else {
            if($('#tea-id').val()==''){
                $('#borrower-name').val($('#name').val())
            }
            else if($('#stud-id').val()==''){
                $('#borrower-name').val($('#tea-name').val())
            }
            else{
                $('#borrower-name').val("")
            }
            var $currForm = $('#item-issue-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveItemIssue";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Item Issued Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#items-table').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#item-code").val("");

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
function drawCNo() {
    $('#issued-to').on('input', function () {
        $('#items-table').DataTable()
            .columns(6)
            .search(this.value)
            .draw();
    });
}
function getItemIssueTab() {

    var table=$('#items-table').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getItemIssueTbl'
        },
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "itemCode"},
            { data: "itemName"},
            { data: "borrowerCat",
                render: function ( data, type, full, meta ) {
                    if (full.borrowerCat !== null) {
                      if(full.borrowerCat ==='T'){
                          return "Teacher"
                      }
                      else{
                          return "Student"
                      }

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
            { data: "description"},
            { data: "itemCode",
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null){
                        return "Y"
                    }else {
                        return "N"
                    }
                }
            },
            { data: "borrowerName"
            },
            { data: "issuerName"},
            { data: "returnDate",
                render: function ( data, type, full, meta ) {
                    if (full.returnDate !== null) {

                        return moment(full.returnDate).format('DD/MM/YYYY');
                    }
                }
            },
            { data: "dateReturned",
                render: function ( data, type, full, meta ) {
                    if (full.dateReturned !== null) {

                        return moment(full.dateReturned).format('DD/MM/YYYY');
                    }
                }
            },

            {data:"units"}
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#items-table').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#items-table').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#tea-id').val("");
        $('#stud-id').val("");
        $('#class').val("");
        $('#item-code').val(aData[0].itemCode)
        if(aData[0].issuerCode!==null && aData[0].issuerName!==null){
            $('#issued-by').val(aData[0].issuerCode)
            $('#issued-name').val(aData[0].issuerName)
            selectUser()
        }
        if(aData[0].forms!==null){
            $('#class').val(aData[0].forms.classCode)
            $('#dm').val(aData[0].forms.abbr)
            selectClass()
            $("#class-div").select2("enable", true);
            $('#c-div').hide();
        }
        if(aData[0].student!==null){
            $('#stud-id').val(aData[0].student.stId)
            $('#stud-name').val(aData[0].student.admNo)
            selectStudent($('#class').val())
            $('#borrower-cat').val('S')
            $("#class-div").select2("enable", true);

        }
        if(aData[0].teachers!==null){
            $('#tea-id').val(aData[0].teachers.teacherCode)
            $('#tea-name').val(aData[0].teachers.name)
            selectTeacher()
            $('#borrower-cat').val('T')
            $("#class-div").select2("enable", true);

        }
        if(aData[0].issueDate!==null) {
            $('#issue-date').val(moment(aData[0].issueDate).format('DD/MM/YYYY'));
        }
        if(aData[0].returnDate!==null) {
            $('#return-date').val(moment(aData[0].returnDate).format('DD/MM/YYYY'));
        }
        $('#units').val(aData[0].units)
        $('#desc-comments').val(aData[0].description)
        $('#item-name').val(aData[0].itemName)

    })
    return table;
}
function selectStudent(id) {
    $('#bo-div').hide();
    $('#c-div').hide();
    $('#tea-id').val('')
    $('#tea-name').val('')
    $("#teach-div").select2("enable", false);
    $('#t-div').hide();
    $('#s-div').show();
    $('#std-div').attr('select2-url',getContextPath()+'/protected/exams/pageStudent/'+id);

    if($("#std-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "std-div",
            sort : 'admNo',
            change: function(e, a, v){
                $('#stud-id').val(e.added.stId)
                $('#stud-name').val(e.added.admNo)
                $('#name').val(e.added.name)
            },
            formatResult : function(a)
            {
                return a.admNo
            },
            formatSelection : function(a)
            {
                return a.admNo
            },
            initSelection: function (element, callback) {
                var code = $('#stud-id').val();
                var name = $('#stud-name').val();
                var data = {admNo:name,stId:code};
                callback(data);
            },
            id: "stId",
            placeholder:"Student",
        });
    }
}
function selectTeacher() {
    $('#bo-div').hide();
    $('#s-div').hide();
    $('#class').val('')
    $('#dm').val('')
    $('#stud-id').val('')
    $('#stud-name').val('')
    $("#class-div").select2("enable", false);
    $('#c-div').hide();
    $('#t-div').show();
    if($("#teach-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "teach-div",
            sort : 'name',
            change: function(e, a, v){
                $('#tea-id').val(e.added.teacherCode)
                $('#tea-name').val(e.added.name)
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
                var code = $("#tea-id").val();
                var name = $("#tea-name").val();
                var data = {name:name,teacherCode:code};
                callback(data);
            },
            id: "teacherCode",
            placeholder:"Teacher",
        });
    }}
function selectClass() {
    $('#bo-div').hide();
    $('#s-div').hide();
    $('#tea-id').val('')
    $('#tea-name').val('')
    $("#teach-div").select2("enable", false);
    $('#t-div').hide();
    $('#c-div').show();
    if ($("#class-div").filter("div").html() != undefined) {
        Select2Builder.initAjaxSelect2({
            containerId: "class-div",
            sort: 'classNo',
            change: function (e, a, v) {
                $('#class').val(e.added.classCode)
                $('#dm').val(e.added.abbr)
                selectStudent(e.added.classCode)
            },
            formatResult: function (a) {
                return a.abbr;
            },
            formatSelection: function (a) {

                return a.abbr;
            },
            initSelection: function (element, callback) {
                var code = $("#class").val();
                var name = $("#dm").val();
                var data = {abbr: name, classCode: code};
                callback(data);
            },
            id: "classCode",
            placeholder: "Select Class",
        });
    }
}
function selectBorrower() {
    $('#borrower-cat').change(function () {
        if ($(this).val() === 'T') {
            selectTeacher()
        } else if ($(this).val() === 'S') {
            selectClass()
        }

    })
}
function selectUser(){
    if($("#issued-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "issued-div",
            sort : 'title',
            change: function(e, a, v){
                $('#issued-by').val(e.added.issuerCode)
                $('#issued-name').val(e.added.issuerName)
                //selectTitle(e.added.callNo)
            },
            formatResult : function(a)
            {
                return a.issuerName;
            },
            formatSelection : function(a)
            {

                return a.issuerName;
            },
            initSelection: function (element, callback) {
                var code = $('#issued-by').val();
                var name = $('#issued-name').val();
                var data = {issuerName:name,issuerCode:code};
                callback(data);
            },
            id: "issuerCode",
            placeholder:"Name",
        });
    }
}
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function returnItem(){
    $('#btn-return').click(function (){
        if($('#item-code').val()===''){
            bootbox.alert('No Item selected to return')
        }
        else{
            var code=$('#item-code').val()
            $.ajax({
                type:"GET",
                url:"returnItem/"+code

            }).done(function (){
                new PNotify({
                    title: 'Success',
                    text: 'Item Returned Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#items-table').DataTable().ajax.reload();
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
function deleteItemsIssue() {
    $('#btn-delete-itemissue').on('click', function () {
        var data = $('#item-code').val();
        if (data === '') {
            bootbox.alert("Select Item to delete");
        } else {
            var url = 'deleteItemIssue/' + data;
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
                        $("#items-table").DataTable().ajax.reload();
                        $("#item-code").val("");
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
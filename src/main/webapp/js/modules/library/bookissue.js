// jshint ignore: start
$(function() {

    $(document).ready(function () {
        $('#r-div').hide()
        $('#c-div').hide()
        $('#t-div').hide()
        $('#s-div').hide()
        newIssue()
        selectBorrower();
        selectCallNo()
         saveBookIssue()
        getBookIssueTab()
        getBookRegTab()
        assignSub()
        issueBook()
        returnBook()
        deleteBookIssue()
        updateBook()
        drawCallNo()
        drawCaNo()
        drawCNo()
        $('#search-ret').hide();
        $('#ret').hide();
        $(".datepicker-input").each(function () {
            $(this).datetimepicker({
                format: 'DD/MM/YYYY',
                defaultDate: new Date()
            });
        })
    });

});
function newIssue(){
    $('#btn-new-bookissue').click(function (){
        window.location.reload();
    })
}

function assignSub() {
    $('#view-issued').change(function(){
        if(this.checked)  {
            $('#search-ret').show();

            $('#ret').show();

            $('#r-div').show();
            $('#search-bor').hide();
            $('#bor').hide();
            $('#i-div').hide();

        }
        else{
            $('#search-bor').show();
            $('#bor').show();
            $('#search-ret').hide();
            $('#ret').hide();
            $('#r-div').hide();
            $('#i-div').show();

        }
    })
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
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
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
    function returnBook(){
    $('#btn-return').click(function (){
        if($('#book-reg-code').val()===''){
           bootbox.alert('No book selected to return')
        }
        else{
            var code=$('#book-reg-code').val()
            $.ajax({
                type:"GET",
                url:"returnBook/"+code

            }).done(function (){
                new PNotify({
                    title: 'Success',
                    text: 'Book Returned Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#book-issue-tbl').DataTable().ajax.reload();
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
function updateBook(){
    $('#update-status').click(function (){
        if($('#book-status').val()===''|| $('#reg-code').val()===''){
            bootbox.alert('No Book or status selected to update')
        }
        else{
           // var code=$('#book-reg-code').val()
            $.ajax({
                type:"GET",
                url:"updateBookStatus",
                data:{
                    'status':$('#book-status').val(),
                    'comment':$('#s-comments').val(),
                    'code':$('#reg-code').val()
                }

            }).done(function (){
                new PNotify({
                    title: 'Success',
                    text: 'Book Status Updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
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
function deleteBookIssue(){
    $('#btn-delete-bookissue').click(function (){
        if($('#book-reg-code').val()===''){
            bootbox.alert('No book selected to return')
        }
        else{
            var code=$('#book-reg-code').val()
            $.ajax({
                type:"GET",
                url:"returnBook/"+code

            }).done(function (){
                new PNotify({
                    title: 'Success',
                    text: 'Book Issue Revoked Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#book-issue-tbl').DataTable().ajax.reload();
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
function saveBookIssue(){
    $('#btn-save-bookissue').on('click',function () {
        if($('#title').val()===''){
          bootbox.alert('No book selected to issue')
           }
        else {
            var $currForm = $('#book-issue-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveBookIssue";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Book Issued Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#book-issue-tbl').DataTable().ajax.reload();
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
function drawCNo() {
    $('#call-no').on('input', function () {
        $('#book-issue-tbl').DataTable()
            .columns(9)
            .search(this.value)
            .draw();
    });
}
function drawCaNo() {
    $('#tt-no').on('input', function () {
        $('#book-tbl').DataTable()
            .columns(0)
            .search(this.value)
            .draw();
    });
}
function drawCallNo() {
    $('#book-no').on('input', function () {
        $('#book-issue-tbl').DataTable()
            .columns(10)
            .search(this.value)
            .draw();
    });
}
function issueBook(){
    $('#btn-issue').on('click',function () {
        if($('#title').val()===''){
            bootbox.alert('No book selected to issue')
        }
        else {
            var $currForm = $('#book-issue-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveBookIssue";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Book Issued Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#book-issue-tbl').DataTable().ajax.reload();
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
function selectCallNo() {

    if($("#reg-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "reg-div",
            sort : 'callNo',
            change: function(e, a, v){
                $('#reg-code').val(e.added.bookRegCode)
                $('#reg-name').val(e.added.callNo)
                selectTitle(e.added.callNo)
            },
            formatResult : function(a)
            {
                return a.callNo;
            },
            formatSelection : function(a)
            {

                return a.callNo;
            },
            initSelection: function (element, callback) {
                var code = $('#reg-code').val();
                var name = $('#reg-name').val();
                var data = {callNo:name,bookRegCode:code};
                callback(data);
            },
            id: "bookRegCode",
            placeholder:"Call Number",
        });
    }}
    function selectTitle(callNo){
    var url="bookTitle/"+callNo;
    $.ajax({
        url:url,
        type:"GET"
    }).done(function (s){
        $('#title').val(s.title)
        $('#title-save').val(s.title)
    }).fail(function (jqXHR, textStatus, errorThrown){
        new PNotify({
            title: 'Error',
            text: jqXHR.responseText,
            type: 'error',
            styling: 'bootstrap3'
        });
    })
}
function getBookIssueTab() {

    var table=$('#book-issue-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getBookIssueTbl'
        },
        dom:'t',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 15,
        destroy: true,
        columns: [
            { data: "bookRegister",
                render: function ( data, type, full, meta ) {
                    if(full.bookRegister!==null){
                        return full.bookRegister.bookRegCode
                    }
                }
            },
            { data: "borrowerCat"},
            { data: "bookIssueCode",
                render: function ( data, type, full, meta ) {
                    if(full.teachers!==null){
                        return full.teachers.name+" "+full.teachers.teacherCode
                    }
                    else if(full.student!=null){
                        return full.student.name+" "+full.student.admNo
                    }
                    else{
                        return "";
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
            { data: "returnDate",
                render: function ( data, type, full, meta ) {
                    if (full.returnDate !== null) {

                        return moment(full.returnDate).format('DD/MM/YYYY');
                    }
                }
            },
            { data: "bookIssueCode",
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null){
                        return "Y"
                    }else {
                        return "N"
                    }
                }
            },
            { data: "bookIssueCode",
                render: function ( data, type, full, meta ) {

                    var admission = moment( full.returnDate);
                    var today = moment();
                    console.log(today.format())
                    console.log(moment.duration(today.diff(admission)).asDays());

                    //discharge.diff(admission, 'days');
                    if(moment(today.diff(admission)>0)){
                       return "Y"
                    }else {
                        return "N"
                    }
                }
            },
            { data: "bookIssueCode",
                render: function ( data, type, full, meta ) {
                    var admission = moment(full.returnDate);
                    var today = moment();
                    //discharge.diff(admission, 'days');
                    if(moment(today.diff(admission))>0){
                        return moment.duration(today.diff(admission)).asDays().toFixed(1)+" "+"days";
                    }else {
                        return "0"
                    }
                }
            },
            { data: "bookIssueCode",
                render: function ( data, type, full, meta ) {
                    var admission = moment(full.returnDate);
                    var today = moment();
                    //discharge.diff(admission, 'days');
                    if(moment(today.diff(admission))>0){
                        return moment.duration(today.diff(admission)).asDays().toFixed(1)+" "+"days";
                    }else {
                        return "0"
                    }
                }
            },
            {data:"callNo"},
            {data:"title"}
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#book-issue-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#book-issue-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#tea-id').val("");
        $('#stud-id').val("");
        $('#class').val("");
        $('#book-reg-code').val(aData[0].bookIssueCode)
        if(aData[0].bookRegister!==null){
            $('#reg-code').val(aData[0].bookRegister.bookRegCode)
            $('#reg-name').val(aData[0].bookRegister.callNo)
            selectCallNo()
            selectTitle(aData[0].bookRegister.callNo)
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
        $('#issue-date').val(moment(aData[0].issueDate).format('DD/MM/YYYY'));
        $('#return-date').val(moment(aData[0].returnDate).format('DD/MM/YYYY'));
    })
    return table;
}
function getBookRegTab() {

    var table=$('#book-tbl').DataTable( {
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
            { data: "title"},
            { data: "publisher"},
            { data: "author"},
            { data: "isbnNo"},
            { data: "callNo"},
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#book-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#book-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#reg-code').val(aData[0].bookRegCode)
        $('#reg-name').val(aData[0].callNo)
        selectCallNo()
        selectTitle(aData[0].callNo)
        if(aData[0].status!=null){
          $('#book-status').val(aData[0].status)
        }
        if(aData[0].statusComments!=null){
            $('#s-comments').val(aData[0].statusComments)
        }

    })
    return table;
}
function deleteStatIssueReg() {
    $('#btn-delete-stationery').on('click', function () {
        var data = $('#stat-issue-code').val();
        if (data === '') {
            bootbox.alert("Select Record to delete");
        } else {
            var url = 'deleteStationeryIssue/' + data;
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
                        $("#stat-issue-tbl").DataTable().ajax.reload();
                        $("#stat-issue-code").val("");
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
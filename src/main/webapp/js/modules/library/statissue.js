// jshint ignore: start
$(function() {

    $(document).ready(function () {
        selectBorrower();
        selectStore()
        selectStationery()
        getUser()
        saveStationeryIssue()
        getStatIssueTab()
        deleteStatIssueReg()
        newCategory()
        $('#t-div').hide()
        $('#s-div').hide()
        $('#w-div').hide()
        $(".datepicker-input").each(function () {
            $(this).datetimepicker({
                format: 'DD/MM/YYYY',
                defaultDate: new Date()
            });
        })
    });
});
function selectBorrower(){
    $('#borrower-cat').change(function (){
        if($(this).val()==='T'){
            selectTeacher()
        }
        else if($(this).val()==='S'){
            selectClass()
        }
        else if($(this).val()==='W'){
          selectWorker()
        }
    })
}
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
function selectClass() {
    if($("#class-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "class-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#class').val(e.added.classCode)
                $('#dm').val(e.added.abbr)
                  selectStudent(e.added.classCode)
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
                var code = $("#class").val();
                var name = $("#dm").val();
                var data = {abbr:name,classCode:code};
                callback(data);
            },
            id: "classCode",
            placeholder:"Select Class",
        });
    }}
function selectStudent(id) {
    $('#bo-div').hide();
    $('#t-div').hide();
    $('#w-div').hide();
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
    $('#w-div').hide();
    $('#class').val('')
    $('#dm').val('')
    selectClass()
    $("#class-div").select2("enable", false);
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
function newCategory(){
    $('#btn-new-stationery').click(function (){
        window.location.reload();
    })
}
function selectWorker() {
    $('#bo-div').hide();
    $('#s-div').hide();
    $('#t-div').hide();
    $('#class').val('')
    $('#dm').val('')
    selectClass()
    $("#class-div").select2("enable", false);
    $('#w-div').show();
    if($("#worker-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "worker-div",
            sort : 'name',
            change: function(e, a, v){
                $('#worker-code').val(e.added.staffCode)
                $('#worker-name').val(e.added.name)
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
                var code = $("#worker-code").val();
                var name = $("#worker-name").val();
                var data = {name:name,staffCode:code};
                callback(data);
            },
            id: "staffCode",
            placeholder:"Worker",
        });
    }}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function saveStationeryIssue(){
    $('#btn-save-stationery').on('click',function () {
        if($("#units").val()===''){
            bootbox.alert("Input a Record to save");
        }else {
            var $currForm = $('#stat-issue-form');
            var currValidator = $currForm.validate();
            if (!$currForm.valid()) {
                return;
            }
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "saveStationeryIssue";
            var request = $.post(url, data);

            request.success(function () {
                new PNotify({
                    title: 'Success',
                    text: 'Record created/updated Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#stat-issue-tbl').DataTable().ajax.reload();
                currValidator.resetForm();
                $("#stat-issue-code").val("");

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
function getStatIssueTab() {

    var table=$('#stat-issue-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getStatIssueTbl'
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
            { data: "units"},
            { data: "registeredBy",
                render: function ( data, type, full, meta ) {
                    if(full.registeredBy!==null){
                        return full.registeredBy.username
                    }
                }
            },
            { data: "statIssueCode",
                render: function ( data, type, full, meta ) {
                    if(full.student!==null){
                        return full.student.admNo
                    }else if(full.teachers!==null){
                        return full.teachers.name
                    }
                    else if(full.staff!==null){
                        return full.staff.name
                    }
                    else{
                        return "";
                    }
                }
            },
        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#stat-issue-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#stat-issue-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#tea-id').val("");
        $('#worker-code').val("");
        $('#std-div').val("");
        $('#stat-issue-code').val(aData[0].statIssueCode)
        if(aData[0].stationery!==null){
            $('#stationery-code').val(aData[0].stationery.stationeryCode)
            $('#stationery-name').val(aData[0].stationery.stationeryName)
            selectStationery()
        }
        if(aData[0].stores!==null){
            $('#store-code').val(aData[0].stores.storeCode)
            $('#store-name').val(aData[0].stores.storeName)
            selectStore()
        }
        if(aData[0].forms!==null){
            $('#class').val(aData[0].forms.classCode)
            $('#dm').val(aData[0].forms.abbr)
            selectClass()
            $("#class-div").select2("enable", true);
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
        if(aData[0].staff!==null){
            $('#worker-code').val(aData[0].staff.staffCode)
            $('#worker-name').val(aData[0].staff.name)
            selectWorker()
            $('#borrower-cat').val('W')
            $("#class-div").select2("enable", true);

        }
        $('#units').val(aData[0].units);
        $('#reg-by').val(aData[0].registeredBy.id);
        $('#user-name').val(aData[0].registeredBy.username);
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
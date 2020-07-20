
$(document).ready(function () {

createStudentTbl();
statsClick();
parentClick();
reportClick();
    feeClick();
    selectFeeLov();
    selectDorm();
    selectClass();
    selectTerm();
    selectParent();
    selectSource();
    selectDenomination();
    selectYear();
    selectHealth();
    selectDocs();
    selectStatus();
    saveStudent();
    selectNation();
    selectCounty();
    selectSubCounty();
    selectLocation();
    selectSubLocation();
    selectVillage();
editStudent();
studDocsClick();
uploadStudentDocument();
loadDocs();
selectStudentCode();
    searStudClick();
saveFeeCategory();
deleteStudent();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });
})
function searStudClick(){
    $("#std-search").on('click',function () {
        searchStudent();
    })
}
function saveFeeCategory() {
    var $form= $("#fee-form");
    var validator = $form.validate();

    $('#fee-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveFeeCategory',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Category Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#fee-tbl").DataTable().ajax.reload();
                $('#feeModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#feeModal').modal('hide');

            });
        });
    $('#feeModal').on('hidden.bs.modal', function () {
        $('#fee-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function studentImage(id){

        $("#stud-avatar").fileinput({
            overwriteInitial: true,
            maxFileSize: 1500,
            showClose: false,
            showCaption: false,
            browseLabel: '',
            removeLabel: '',
            browseIcon: '<i class="fa fa-folder-open"></i>',
            removeIcon: '<i class="fa fa-times"></i>',
            removeTitle: 'Cancel or reset changes',
            elErrorContainer: '#kv-avatar-errors',
            msgErrorClass: 'alert alert-block alert-danger',
            defaultPreviewContent: '<img src="'+getContextPath()+'/protected/studentPhoto/' + id + '"  style="height:15em;width:200px">',
            layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
            allowedFileExtensions: ["jpg", "png", "gif"]
        });

}
function parentClick() {
    $('#btn-add-pr').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $("#parentsModal").modal('show');

    })
}
function statsClick() {
    $('#btnstats').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $("#statsModal").modal('show');

    })
}
function feeClick() {
    $('#btn-add-cat').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $("#feesModal").modal('show');

    })
}
function loadDocs() {
    $('#docs-per-stud').hide();
}
function studDocsClick() {
    $('#btn-upload-stdocs').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $("#studentdocModal").modal('show');
    })
}
function deleteBlock() {

}
function selectStudentCode() {
    if($("#std-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "std-div",
            sort : 'name',
            change: function(e, a, v){
                $('#stud-id').val(e.added.stId)
                $('#stud-name').val(e.added.name)
                createStdDocs(e.added.stId);
            },
            formatResult : function(a)
            {
                return a.name
            },
            formatSelection : function(a)
            {
                return a.name
            },
            initSelection: function (element, callback) {
                var code = $('#stud-id').val();
                var name = $('#stud-name').val();
                var data = {name:name,stId:code};
                callback(data);
            },
            id: "stId",
            placeholder:"Student",
        });
    }
}
function searchStudent(){
    console.log($('#admN').val())
    console.log($('#nMe').val())
    console.log($('#mobNo').val())
    var table=$('#students-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'searchStudents',
            data:{
                'name':$('#nMe').val(),
                'admNo':$('#admN').val(),
                'mobile':$('#mobNo').val(),
                'form':$('#classSea').val(),
                'suspended':$('#suspended').val(),
                'transfers':$('#transferred').val(),
                'expels':$('#expelled').val(),
                'graduated':$('#graduated').val(),
            }
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "admNo" },
            { data: "name" },
            { data: "birthDate" },
            { data: "admDate" },
            { data: "dorm",
                render: function ( data, type, full, meta ) {
                    return full.dorm.dormName;
                }
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.classNo;
                }
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.block.blockName;
                }
            },
            { data: "forms" ,
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },
            { data: "graduate",

                render: function ( data, type, full, meta ) {
                    if (full.graduate === null) {
                        return "No";
                    } else {
                        return "Yes";
                    }
                }
            },
            { data: "stId" ,
                render: function ( data, type, full, meta ) {
                    return '<form action="/protected/editStudentForm" method="post"><input type="hidden" name="id" value='+full.stId+'><input type="submit"  class="btn btn-success btn btn-info btn-xs" value="Edit" ></form>';
                }
            },
            { data: "stId" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="parentId" name="id" value='+full.stId+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
            { data: "stId" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="parentId" name="id" value='+full.stId+'></form><button class="btn btn-danger btn-xs btn-download" ><i class="fa fa-download"></button>';
                }
            },

        ]
    } );
    return table;
}
function createStdDocs(id) {
    var table=$('#std-docs-tbl').DataTable({
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url: 'getStdDocsTable/'+id,

        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    return full.student.name;
                }
                },
            { data: "document" ,
                render: function ( data, type, full, meta ) {
                if(full.document!==null) {
                    return full.document.docName;
                }
                else{
                    return "Not Set";
                }
                }
                },
            { data: "fileId"
            },
            {
                "data": "stdocCode",
                "render": function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-success btn btn-info btn-xs" type="button" data-docs=' + encodeURI(JSON.stringify(full)) + ' onclick="downloadClientDoc(this);"><i class="fa fa-file-archive-o"></button>';

                }

            },
            {
                "data": "stdocCode",
                "render": function ( data, type, full, meta ) {
                        return '<button type="button" class="btn btn-danger btn btn-info btn-xs" data-docs='+encodeURI(JSON.stringify(full)) + ' onclick="deleteClientDoc(this);"><i class="fa fa-trash-o"></button>';
                }

            },
        ]

    } );
$('#docs-per-stud').show();
    return table;
}
function downloadClientDoc(button){
    var docs = JSON.parse(decodeURI($(button).data("docs")));
    window.open("/school/protected/studentDocumentsView/"+docs['stdocCode']+"/"+docs['student'].stId,
        '_blank' // <- This is what makes it open in a new window.
    );
}
function reportClick() {
    $('#report').on('click',function () {
        if ($('#student-id').val() === '') {
            bootbox.alert("Select a student first to view report");
        } else {
            $.ajax({
                type: 'GET',
                url: 'details/report/' + $('#student-id').val() + "/" + "pdf",
                async: true,
                success: function (result) {
                    $('#myPleaseWait').modal('hide');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Error',
                        text: jqXHR.responseText,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                }
            });

        }
    })
}
function deleteClientDoc(button){
    var docs = JSON.parse(decodeURI($(button).data("docs")));
    bootbox.confirm("Are you sure want to delete document", function(result) {
        if(result){
            $('#myPleaseWait').modal({
                backdrop: 'static',
                keyboard: true
            });
            $.ajax({
                type: 'GET',
                url:  'deleteStudDoc/' + docs['stdocCode']+"/"+docs['student'].stId,
                dataType: 'json',
                async: true,
                success: function(result) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Success',
                        text: 'Record Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $('#std-docs-tbl').DataTable().ajax.reload();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $('#myPleaseWait').modal('hide');
                    new PNotify({
                        title: 'Error',
                        text: 'Foreign Key Constraint violation or Delete Id Parameter not provided',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                }
            });
        }

    });
}
function selectDenomination() {
    if($("#denomination-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "denomination-div",
            sort : 'relName',
            change: function(e, a, v){
                $('#denCode').val(e.added.relCode)
                $('#denName').val(e.added.relName)
            },
            formatResult : function(a)
            {
                return a.relName
            },
            formatSelection : function(a)
            {
                return a.relName
            },
            initSelection: function (element, callback) {
                var code = $('#denCode').val();
                var name = $('#denName').val();
                var data = {relName:name,relCode:code};
                callback(data);
            },
            id: "relCode",
            placeholder:"Denomination",
        });
    }
}
function selectSource() {
    if($("#source-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "source-div",
            sort : 'sourceName',
            change: function(e, a, v){
                $('#source').val(e.added.sourceCode)
                $('#souname').val(e.added.sourceName)
            },
            formatResult : function(a)
            {
                return a.sourceName
            },
            formatSelection : function(a)
            {
                return a.sourceName
            },
            initSelection: function (element, callback) {
                var code = $('#source').val();
                var name = $('#souname').val();
                var data = {sourceName:name,sourceCode:code};
                callback(data);
            },
            id: "sourceCode",
            placeholder:"Source",
        });
    }
}
function selectHealth() {
    if($("#health-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "health-div",
            sort : 'condName',
            change: function(e, a, v){
                $('#heacode').val(e.added.condCode)
                $('#heaname').val(e.added.condName)
            },
            formatResult : function(a)
            {
                return a.condName
            },
            formatSelection : function(a)
            {
                return a.condName
            },
            initSelection: function (element, callback) {
                var code = $('#heacode').val();
                var name = $('#heaname').val();
                var data = {condName:name,condCode:code};
                callback(data);
            },
            id: "condCode",
            placeholder:"Health Condition",
        });
    }
}

function selectYear() {
    if($("#year-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "year-div",
            sort : 'yearName',
            change: function(e, a, v){
                $('#yearCode').val(e.added.yearCode)
                $('#yearName').val(e.added.yearName)
            },
            formatResult : function(a)
            {
                return a.yearName
            },
            formatSelection : function(a)
            {
                return a.yearName
            },
            initSelection: function (element, callback) {
                var code = $('#yearCode').val();
                var name = $('#yearName').val();
                var data = {yearName:name,yearCode:code};
                callback(data);
            },
            id: "yearCode",
            placeholder:"Year",
        });
    }
}



function editStudent() {
    if(typeof stdCode!='undefined' || stdCode!==-2000) {
        $.ajax({
            type: 'GET',
            url: 'studentDetails/'+stdCode,
        }).done(function (s) {
            studentImage(s.stId);
            $('#student-doc-id').val(s.stId);
            $('#hid-stud-id').val(s.stId);
            $('#student-id').val(s.stId);

            if(s.admNo!=null) {
                $('#adm').prop('disabled', true);
                $('#adm').val(s.admNo);
            }


            if(s.feeCategory!==null) {
                $('#fee-cat').val(s.feeCategory.catCode);
                $('#fee-name').val(s.feeCategory.catName);
                selectFeeLov();
            }
            $('#upi').val(s.upi);
            if(s.dorm!==null) {
                $('#dorm').val(s.dorm.dormCode);
                $('#house').val(s.dorm.dormName);
                selectDorm()
            }

            $('#name').val(s.name);
            if(s.forms!==null) {
                $('#class').val(s.forms.classCode);
                $('#dm').val(s.forms.abbr);
                selectClass();
            }
            if(s.term!==null) {
                $('#term').val(s.term.termCode);
                $('#term-name').val(s.term.termNumber);
                selectTerm();
            }
            if(s.parent!==null) {
                $('#par').val(s.parent.parCode);
                $('#pr').val(s.parent.nameFather + s.parent.nameMother);
                selectParent();
            }
            if (s.country !== null){
                $('#country-id').val(s.country.couCode);
            $('#coun-desc').val(s.country.couName);
            selectNation();
        }
            if (s.gender === "Male"){
                $('#id-male').prop('checked',true);
            }else if(s.gender === "Female"){
                $('#id-female').prop('checked',true);
            }
        if (s.county !== null){
            $('#county-id').val(s.county.countyCode);
           $('#county-desc').val(s.county.countyName);
           selectCounty();
         }
        if (s.subCounty !== null){
        $('#sub-county-id').val(s.subCounty.subCode);
        $('#sub-count-desc').val(s.subCounty.subName);
        selectSubCounty();
        }
        if (s.location !== null){
          $('#loc-id').val(s.location.louCode);
          $('#location-desc').val(s.location.louName);
           selectLocation();
             }
         if (s.subLocation !== null){
          $('#sub-loc-id').val(s.subLocation.subLouCode);
           $('#sub-loc-desc').val(s.subLocation.subLouName);
            selectSubLocation();
             }
            if (s.village !== null){
           $('#village-id').val(s.village.vilCode);
            $('#village-desc').val(s.village.vilName);
             selectVillage();
           }
            $('#address').val(s.address);
            $('#email').val(s.studEmail);
            $('#parnumber').val(s.parentContact);
            $('#stdnumber').val(s.studentContact);
            $('#doa').val(moment(s.admDate).format('DD/MM/YYYY'));
            $('#docm').val(moment(s.completionDate).format('DD/MM/YYYY'));
            $('#dob').val(moment(s.birthDate).format('DD/MM/YYYY'));
            console.log(moment(s.birthDate).format('DD/MM/YYYY'));
            if (s.religion !== null) {
                $('#denCode').val(s.religion.relCode);
                $('#denName').val(s.religion.relName);
                selectDenomination();
            }
            $('#birthcert').val(s.birthCertNo);

            if(s.indexNo!=null) {
                $('#index').prop('disabled', true);
                $('#index').val(s.indexNo);
            }


            if (s.year !== null) {
                $('#yearCode').val(s.year.yearCode);
                $('#yearName').val(s.year.yearName);
                selectYear();
            }
            if (s.sources !== null) {
                $('#source').val(s.sources.sourceCode);
                $('#souname').val(s.sources.sourceName);
                selectSource();
            }
            $('#kcpe').val(s.kcpeMarks);
            $('#prischool').val(s.primarySchool);
            $('#grade').val(s.kcpeGrade);
            if (s.health !== null) {
                $('#heacode').val(s.health.condCode);
                $('#heaname').val(s.health.condName);
                selectHealth();
            }
            if (s.studStatus !== null) {
                $('#status').val(s.studStatus.statusCode);
                $('#statname').val(s.studStatus.statusName);
                selectStatus();
            }

            studentImage(s.stId);
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    }
}

function selectVillage() {
    if($("#village-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "village-div",
            sort : 'vilName',
            change: function(e, a, v){
                $('#village-id').val(e.added.vilCode)
                $('#village-desc').val(e.added.vilName)
            },
            formatResult : function(a)
            {
                return a.vilName
            },
            formatSelection : function(a)
            {
                return a.vilName
            },
            initSelection: function (element, callback) {
                var code = $('#village-id').val();
                var name = $('#village-desc').val();
                var data = {vilName:name,vilCode:code};
                callback(data);
            },
            id: "vilCode",
            placeholder:"Select Village",
        });
    }
}
function selectSubLocation() {
    if($("#sub-location-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "sub-location-div",
            sort : 'subLouName',
            change: function(e, a, v){
                $('#sub-loc-id').val(e.added.subLouCode)
                $('#sub-loc-desc').val(e.added.subLouName)
            },
            formatResult : function(a)
            {
                return a.subLouName
            },
            formatSelection : function(a)
            {
                return a.subLouName
            },
            initSelection: function (element, callback) {
                var code = $('#sub-loc-id').val();
                var name = $('#sub-loc-desc').val();
                var data = {subLouName:name,subLouCode:code};
                callback(data);
            },
            id: "subLouCode",
            placeholder:"Select Sub Location",
        });
    }
}
function selectLocation() {
    if($("#location-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "location-div",
            sort : 'louName',
            change: function(e, a, v){
                $('#loc-id').val(e.added.louCode)
                $('#location-desc').val(e.added.louName)
            },
            formatResult : function(a)
            {
                return a.louName
            },
            formatSelection : function(a)
            {
                return a.louName
            },
            initSelection: function (element, callback) {
                var code = $('#loc-id').val();
                var name = $('#location-desc').val();
                var data = {louName:name,louCode:code};
                callback(data);
            },
            id: "louCode",
            placeholder:"Select Location",
        });
    }
}
function selectSubCounty() {
    if($("#sub-county-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "sub-county-div",
            sort : 'subName',
            change: function(e, a, v){
                $('#sub-county-id').val(e.added.subCode)
                $('#sub-count-desc').val(e.added.subName)
            },
            formatResult : function(a)
            {
                return a.subName
            },
            formatSelection : function(a)
            {
                return a.subName
            },
            initSelection: function (element, callback) {
                var code = $('#sub-county-id').val();
                var name = $('#sub-count-desc').val();
                var data = {subName:name,subCode:code};
                callback(data);
            },
            id: "subCode",
            placeholder:"Select Sub County",
        });
    }
}
function selectCounty() {
    if($("#county-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "county-div",
            sort : 'countyName',
            change: function(e, a, v){
                $('#county-id').val(e.added.countyCode)
                $('#county-desc').val(e.added.countyName)

            },
            formatResult : function(a)
            {
                return a.countyName
            },
            formatSelection : function(a)
            {
                return a.countyName

            },
            initSelection: function (element, callback) {

                var code = $('#county-id').val();
                var name = $('#county-desc').val();
                var data = {countyName:name,countyCode:code};
                callback(data);
            },
            id: "countyCode",
            placeholder:"Select County",
        });
    }
}
function selectNation() {
    if($("#nation-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "nation-div",
            sort : 'couName',
            change: function(e, a, v){
                $("#country-id").val(e.added.couCode);
                $("#coun-desc").val(e.added.couName);
            },
            formatResult : function(a)
            {
                return a.couName
            },
            formatSelection : function(a)
            {
                return a.couName

            },
            initSelection: function (element, callback) {
                var code = $("#country-id").val();
                var name = $("#coun-desc").val();
                var data = {couName:name,couCode:code};
                callback(data);
            },
            id: "couCode",
            placeholder:"Select Nationality",
        });
    }
}

function selectFeeLov() {
    if($("#fee-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "fee-div",
            sort : 'catName',
            change: function(e, a, v){
               $('#fee-cat').val(e.added.catCode)
                $('#fee-name').val(e.added.catName)
            },
            formatResult : function(a)
            {
                return a.catName;
            },
            formatSelection : function(a)
            {
                return a.catName;

            },
            initSelection: function (element, callback) {
                var code = $("#fee-cat").val();
                var name = $("#fee-name").val();
                var data = {catName:name,catCode:code};
                callback(data);
            },
            id: "catCode",
            placeholder:"Select Fee",
        });
    }
}
function selectDorm() {
    if($("#house-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "house-div",
            sort : 'dormName',
            change: function(e, a, v){
                $('#dorm').val(e.added.dormCode)
                $('#house').val(e.added.dormName)
            },
            formatResult : function(a)
            {
                return a.dormName;
            },
            formatSelection : function(a)
            {
                return a.dormName;

            },
            initSelection: function (element, callback) {
                var code = $("#dorm").val();
                var name = $("#house").val();
                var data = {dormName:name,dormCode:code};
                callback(data);
            },
            id: "dormCode",
            placeholder:"Select House",
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
    }
}

function selectTerm() {
    if($("#term-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "term-div",
            sort : 'termNumber',
            change: function(e, a, v){
                $('#term').val(e.added.termCode)
                $('#term-name').val(e.added.termNumber)
            },
            formatResult : function(a)
            {
                return a.termNumber
            },
            formatSelection : function(a)
            {
                return a.termNumber
            },
            initSelection: function (element, callback) {
                var code = $("#term").val();
                var name = $("#term-name").val();
                var data = {termNumber:name,termCode:code};
                callback(data);
            },
            id: "termCode",
            placeholder:"Select Term",
        });
    }
}
function selectParent() {
    if($("#parent-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "parent-div",
            sort : 'nameFather',
            change: function(e, a, v){
                $('#par').val(e.added.parCode)
                $('#pr').val(e.added.nameFather)
            },
            formatResult : function(a)
            {
                return a.nameFather+" "+a.nameMother
            },
            formatSelection : function(a)
            {
                return a.nameFather+" "+a.nameMother
            },
            initSelection: function (element, callback) {
                var code = $('#par').val();
                var name =  $('#pr').val();
                var data = {nameFather:name,parCode:code};
                callback(data);
            },
            id: "parCode",
            placeholder:"Select Parent",
        });
    }
}
function uploadStudentDocument(){


        var $form = $("#student-doc-form");
        var validator = $form.validate();
        $('#student-doc-form')
            .submit(function (e) {
                e.preventDefault();
                if($('#student-id').val()===''){
                    bootbox.alert('Save a Student First then upload his documents on edit');
                }
                else {
                    if (!$form.valid()) {
                        return;
                    }
                    $('#myPleaseWait').modal({
                        backdrop: 'static',
                        keyboard: true
                    });
                    var data = new FormData(this);
                    data.append('file', $('#std-avatar')[0].files[0]);
                    $.ajax({
                        url: 'uploadStudentDocs',
                        type: 'POST',
                        data: data,
                        processData: false,
                        contentType: false,
                        success: function (s) {
                            $('#myPleaseWait').modal('hide');
                            new PNotify({
                                title: 'Success',
                                text: 'File Uploaded Successfully',
                                type: 'success',
                                styling: 'bootstrap3'
                            });
                            $('#studentdocModal').modal('hide');
                            var $el = $('#std-avatar');
                            $el.wrap('<form>').closest('form').get(0).reset();
                            $el.unwrap();
                            $('#std-docs.tbl').DataTable().ajax.reload();

                        },
                        error: function (xhr, error) {
                            $('#myPleaseWait').modal('hide');
                            new PNotify({
                                title: 'Error',
                                text: xhr.responseText,
                                type: 'error',
                                styling: 'bootstrap3'
                            });
                        }
                    });
                }
            });

}
function selectDocs() {
    if($("#doc-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "doc-div",
            sort : 'docName',
            change: function(e, a, v){
                $('#doccode').val(e.added.docCode)
                $('#docname').val(e.added.docName)
            },
            formatResult : function(a)
            {
                return a.docName
            },
            formatSelection : function(a)
            {
                return a.docName
            },
            initSelection: function (element, callback) {
                var code = $('#doccode').val();
                var name =  $('#docname').val();
                var data = {docName:name,docCode:code};
                callback(data);
            },
            id: "docCode",
            placeholder:"Document",
        });
    }
}
function selectStatus() {
    if($("#status-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "status-div",
            sort : 'statusName',
            change: function(e, a, v){
                $('#status').val(e.added.statusCode)
                $('#statname').val(e.added.statusName)
            },
            formatResult : function(a)
            {
                return a.statusName
            },
            formatSelection : function(a)
            {
                return a.statusName
            },
            initSelection: function (element, callback) {
                var code = $('#status').val();
                var name =  $('#statname').val();
                var data = {statusName:name,statusCode:code};
                callback(data);
            },
            id: "statusCode",
            placeholder:"Status",
        });
    }
}
function createStudentTbl() {
    var table=$('#students-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getUsers',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "admNo" },
            { data: "name" },
            { data: "birthDate",
                render: function ( data, type, full, meta ) {
                    if(full.birthDate!==null) {
                        return moment(full.birthDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }

            },
            { data: "admDate",
                render: function ( data, type, full, meta ) {
                    if(full.admDate!==null) {
                        return moment(full.admDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }

            },
            { data: "dorm",
                render: function ( data, type, full, meta ) {
                if(full.dorm!==null) {
                    return full.dorm.dormName;
                }
                else{
                    return "Not Set"
                }
                }
                },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    if(full.forms!==null) {
                        return full.forms.classNo;
                    }
                    else{
                        return "Not Set"
                    }
                }
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    if(full.forms!==null) {
                        return full.forms.block.blockName;
                    }
                    else{
                       return "Not Set"
                    }
                }
            },
            { data: "forms" ,
                render: function ( data, type, full, meta ) {
                    if(full.forms!==null) {
                        return full.forms.abbr;
                    }
                    else{
                        "Not Set"
                    }
                }
            },
            { data: "graduate",
                render: function ( data, type, full, meta ) {
                    if (full.graduate === null) {
                        return "No";
                    } else {
                        return "Yes";
                    }
                }  },
            { data: "stId" ,
                render: function ( data, type, full, meta ) {
                    return '<form action="'+getContextPath()+'/protected/editStudentForm" method="post"><input type="hidden" name="id" value='+full.stId+'><input type="submit"  class="btn btn-success btn btn-info btn-xs" value="Edit" ></form>';
                }
            },
            { data: "stId" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.stId+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }

        ]
    } );
return table;
}
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function deleteStudent(){
    $('#students-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteStudent/' + data;
        bootbox.confirm("Are you sure want to delete this Student?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Student Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#students-tbl").DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                    new PNotify({
                        title: 'Error',
                        text: 'Foreign Key Constraint violation or Delete Id Parameter not provided',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                })

            }
        })
    })

}
function saveStudent() {
    var $form= $("#stud-form");
    var validator = $form.validate();

    $('#stud-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            data.append( 'file', $( '#stud-avatar' )[0].files[0] );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'postStudent',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Student Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#students-tbl").DataTable().ajax.reload();

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
}
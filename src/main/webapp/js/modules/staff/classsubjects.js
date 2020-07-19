$(document).ready(function () {

    selectMyClass();
    selectMySubject();
    pullSubModal();
    pullDatesModal();
    saveClassSubjects();
    saveTermDates();
    createClassSubjectsTbl();
    classSubjectTblClick();
    saveSubjectsGrps();
    createSubjectGrpTbl();
    subjectGrpTblClick();
    pullGrpModal();
regPrSearch();
regGrSearch();
selectTerm();
createTermDatesTbl();
deleteTermTblClick()
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });
})
function regPrSearch() {
    $('#search-id').on('keyup', function () {
        $('#class-subjects-tbl').DataTable()
            .columns(0)
            .search(this.value)
            .draw();
    });
}
function regGrSearch() {
    $('#search-id').on('keyup', function () {
        $('#subject-groups-tbl').DataTable()
            .columns(0)
            .search(this.value)
            .draw();
    });
}
function saveTermDates() {
    var $form= $("#term-dates-form");
    var validator = $form.validate();

    $('#term-dates-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }

            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveTermDates',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#term-dates-tbl").DataTable().ajax.reload();
                $('#termDateModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#termDateModal').on('hidden.bs.modal', function () {
        $('#term-dates-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveSubjectsGrps() {
    var $form= $("#group-form");
    var validator = $form.validate();

    $('#group-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveSubjectGroup',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#subject-groups-tbl").DataTable().ajax.reload();
                $('#groupModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#groupModal').on('hidden.bs.modal', function () {
        $('#class-subject-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveClassSubjects() {
    var $form= $("#class-subject-form");
    var validator = $form.validate();

    $('#class-subject-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveClassSubjects',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#class-subjects-tbl").DataTable().ajax.reload();
                $('#classsubjectsModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#classsubjectsModal').on('hidden.bs.modal', function () {
        $('#class-subject-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function createClassSubjectsTbl() {
    var table=$('#class-subjects-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getClassSubjects',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "name"
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    if (full.forms !== null) {
                        return full.forms.abbr;
                    }
                    else {
                        "";
                    }
                }
            },
            { data: "weekLessons" },
            { data: "doubleLessons"},
            { data: "strokedWith"},
            { data: "strokedSubject"}

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#class-subjects-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#class-subjects-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
         $('#class-subject-id').val(aData[0].classSubjectCode)
        $('#myclass').val(aData[0].forms.classCode)
        $('#mydm').val(aData[0].forms.abbr)
        selectMyClass();
        $('#mysubject').val(aData[0].subjects.subjectCode)
        $('#mysm').val(aData[0].subjects.name)
        selectMySubject()
        $('#week-lessons').val(aData[0].weekLessons)
        $('#double-lessons').val(aData[0].doubleLessons)
        $('#status').val(aData[0].strokedWith)
        if(aData[0].strokedSubject==="Y") {
            $('#stroked').attr('checked',true)
        }else{
            $('#stroked').attr('checked',false)
        }
        $('#classsubjectsModal').modal('show');

    })
    return table;
}
function createTermDatesTbl() {
    var table=$('#term-dates-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getTermDatesTbl',
        scrollY: "750px",
        scrollCollapse: true,
        paging: false,
        dom: "t",
        destroy: true,
        columns: [
            { data: "term",
                render: function ( data, type, full, meta ) {
                    if (full.term !== null) {
                        return full.term.termNumber;
                    }
                    else {
                        "";
                    }
                }
            },

            { data: "startDate",
                render: function ( data, type, full, meta ) {
                    if(full.startDate!==null) {
                        return moment(full.startDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }

                }
            },

            { data: "endDate",
                render: function ( data, type, full, meta ) {
                    if(full.endDate!==null) {
                        return moment(full.endDate).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }

                }
            },
            { data: "currentTerm"}


        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#term-dates-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#term-dates-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#term-id').val(aData[0].termDateCode)
        $('#myterm').val(aData[0].term.termCode)
        $('#mytm').val(aData[0].term.termNumber)
        selectTerm();
        $('#start-date').val(moment(aData[0].startDate).format('DD/MM/YYYY'))
        $('#end-date').val(moment(aData[0].endDate).format('DD/MM/YYYY'))
        if(aData[0].currentTerm==="Y") {
            $('#current-term').attr('checked',true)
        }else{
            $('#current-term').attr('checked',false)
        }
        $('#termDateModal').modal('show');

    })
    return table;
}

function selectTerm() {
    if($("#my-term-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "my-term-div",
            sort : 'termNumber',
            change: function(e, a, v){
                $('#myterm').val(e.added.termCode)
                $('#mytm').val(e.added.termNumber)
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
                var code =  $('#myterm').val();
                var name =$('#mytm').val();
                var data = {termNumber:name,termCode:code};
                callback(data);
            },
            id: "termCode",
            placeholder:"Select Term",
        });
    }
}
function createSubjectGrpTbl() {
    var table=$('#subject-groups-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getSubjectGroups',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
      dom:"t",
        columns: [
            { data: "name"},
            { data: "groupNumber" },
            { data: "groupCode",
                render: function ( data, type, full, meta ) {

                return "Y"
                }
            },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#subject-groups-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#subject-groups-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#group-id').val(aData[0].groupCode)

        $('#mysubject').val(aData[0].subjects.subjectCode)
        $('#mysm').val(aData[0].subjects.name)
        selectMySubject()
        $('#group-number').val(aData[0].groupNumber)
        if(aData[0].compulsorySubject==="Y") {
            $('#compulsory').attr('checked',true)
        }else{
            $('#compulsory').attr('checked',false)
        }
        $('#groupModal').modal('show');

    })
    return table;
}
function pullSubModal() {
    $('#btn-class-subjects').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#class-subject-id').val('');
        $('#class-subject-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
        $('#stroked').prop('checked',false);
        $("#classsubjectsModal").modal('show');

    })
}

function pullDatesModal() {
    $('#btn-term-dates').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#term-id').val('');
        $('#term-dates-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
        $('#current-term').prop('checked',false);
        $("#termDateModal").modal('show');

    })
}

function pullGrpModal() {
    $('#btn-subject-groups').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#group-id').val('');
        $('#group-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
        $('#compulsory').prop('checked',false);
        $("#groupModal").modal('show');

    })
}

function selectMyClass() {

    if($("#my-class-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "my-class-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#myclass').val(e.added.classCode)
                $('#mydm').val(e.added.abbr)
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
                var code = $("#myclass").val();
                var name = $("#mydm").val();
                var data = {abbr:name,classCode:code};
                callback(data);
            },
            id: "classCode",
            placeholder:"Class",
        });
    }}
function selectMySubject() {

    if($("#my-subject-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "my-subject-div",
            sort : 'name',
            change: function(e, a, v){
                $('#mysubject').val(e.added.subjectCode)
                $('#mysm').val(e.added.name)
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
                var code = $("#mysubject").val();
                var name = $("#mysm").val();
                var data = {name:name,subjectCode:code};
                callback(data);
            },
            id: "subjectCode",
            placeholder:"Subject",
        });
    }}
function classSubjectTblClick(){
    $('#delete-subject-class').click( function () {
        var data = $('#class-subject-id').val();

        if(data===''){
            bootbox.alert('No Subject Selected to delete');
        }
        else {

            var url = 'deleteClassSubject/' + data;
            bootbox.confirm("Are you sure want to delete this Class Subject?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: 'Class Subject Deleted Successfully',
                            type: 'success',
                            styling: 'bootstrap3'
                        });
                        $("#class-subjects-tbl").DataTable().ajax.reload();

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
function subjectGrpTblClick(){
    $('#delete-subject-group').click( function () {
        var data = $('#group-id').val();
        if(data===''){
           bootbox.alert('No Group Selected to delete');
        }
        else {
            var url = 'deleteSubjectGroup/' + data;
            bootbox.confirm("Are you sure want to delete this Subject Group?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: ' Group Deleted Successfully',
                            type: 'success',
                            styling: 'bootstrap3'
                        });
                        $("#subject-groups-tbl").DataTable().ajax.reload();

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
function deleteTermTblClick(){
    $('#delete-term-dates').click( function () {
        var data = $('#term-id').val();
        if(data===''){
            bootbox.alert('No Dates Selected to delete');
        }
        else {
            var url = 'deleteTermDates/' + data;
            bootbox.confirm("Are you sure want to delete this Term Date?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: ' Dates Deleted Successfully',
                            type: 'success',
                            styling: 'bootstrap3'
                        });
                        $("#term-dates-tbl").DataTable().ajax.reload();

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
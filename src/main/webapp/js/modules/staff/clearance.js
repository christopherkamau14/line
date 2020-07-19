$(document).ready(function () {
    selectStudentCode();
    makeReadOnly();
    clickLib();
    clickLab();
    clickHod();
    clickExam();
    clickAccount();
    clickPrincipal();
    clickDeputy();
    clickCord();
    createClearanceTbl();
    saveClearance();
    regSearchCleared();
    deleteClearance();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });


})
function deleteClearance(){
    $('#btn-delete-clearance').on( 'click', function () {
        var data = $('#clear-code').val();
        if(data===''){
            bootbox.alert('Select a record to delete');
        }
        else {
            var url = 'deleteClearance/' + data;
            bootbox.confirm("Are you sure want to delete this Record?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: 'Record Deleted Successfully',
                            type: 'success',
                            styling: 'bootstrap3'
                        });
                        $("#clearance-tbl").DataTable().ajax.reload();

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
function regSearchCleared() {
    $('#search-id').on('keyup', function () {
        $('#clearance-tbl').DataTable()
            .columns(2)
            .search(this.value)
            .draw();
    });
}
function saveClearance() {

    var $form = $("#clearance-form");
    var validator = $form.validate();

    $('#clearance-form')
        .submit(function (e) {
            e.preventDefault();
            if($('#stud-id').val()=='') {
                bootbox.alert('Select a student to clear first!');
            }
            else {
                if (!$form.valid()) {
                    return;
                }


                var data = new FormData(this);
                console.log(data);
                $.ajax({
                    type: 'POST',
                    url: 'saveClearance',
                    data: data,
                    processData: false,
                    contentType: false,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Record Added Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#clearance-tbl").DataTable().ajax.reload();
                }).fail(function (xhr, error) {
                    new PNotify({
                        title: 'Error',
                        text: xhr.responseText,
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                });
            }
        });

}
function makeReadOnly(){
    $('#clearance-form').trigger("reset");
    jQuery('.select2-offscreen').select2('val', '');

    $('#lib-name').prop('readOnly',true);
    $('#lib-com').prop('readOnly',true);

    $('#lib-date').prop('readOnly',true);

    $('#lab-name').prop('readOnly',true);
    $('#lab-com').prop('readOnly',true);
    $('#lab-date').prop('readOnly',true);
    $('#hod-name').prop('readOnly',true);
    $('#hod-com').prop('readOnly',true);
    $('#hod-date').prop('readOnly',true);
    $('#acct-name').prop('readOnly',true);
    $('#acct-com').prop('readOnly',true);
    $('#acct-date').prop('readOnly',true);
    $('#exam-name').prop('readOnly',true);
    $('#exam-com').prop('readOnly',true);
    $('#exam-date').prop('readOnly',true);
    $('#principal-name').prop('readOnly',true);
    $('#principal-com').prop('readOnly',true);
    $('#principal-date').prop('readOnly',true);
    $('#deputy-name').prop('readOnly',true);
    $('#deputy-com').prop('readOnly',true);
    $('#deputy-date').prop('readOnly',true);
    $('#cord-name').prop('readOnly',true);
    $('#cord-com').prop('readOnly',true);
    $('#cord-date').prop('readOnly',true);
    $(':checkbox').prop('checked',false);

}
/*
$('#hod-date').datepicker().datepicker("setDate",new Date());
$('#exam-date').datepicker().datepicker("setDate",new Date());
$('#deputy-date').datepicker().datepicker("setDate",new Date());
$('#principal-date').datepicker().datepicker("setDate",new Date());
$('#acct-date').datepicker().datepicker("setDate",new Date());
$('#cord-date').datepicker().datepicker("setDate",new Date());
*/
function clickLib() {
    $('#lib-clear').change(function(){
        if(this.checked)  {
            $('#lib-name').removeAttr('readonly');
            $('#lib-com').removeAttr('readonly');
            $('#lib-date').removeAttr('readonly');
            $('#lib-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#lib-name').attr('readonly','readonly');
            $('#lib-com').attr('readonly','readonly');
            $('#lib-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#lib-date'))

        }
    })
}
function clickLab() {
    $('#lab-clear').change(function(){
        if(this.checked)  {
            $('#lab-name').removeAttr('readonly');
            $('#lab-com').removeAttr('readonly');
            $('#lab-date').removeAttr('readonly');
            $('#lab-date').datepicker().datepicker("setDate",new Date());
        }
        else{
            $('#lab-name').attr('readonly','readonly');
            $('#lab-com').attr('readonly','readonly');
            $('#lab-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#lab-date'))

        }
    })
}

function clickHod() {
    $('#hod-clear').change(function(){
        if(this.checked)  {
            $('#hod-name').removeAttr('readonly');
            $('#hod-com').removeAttr('readonly');
            $('#hod-date').removeAttr('readonly');
            $('#hod-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#hod-name').attr('readonly','readonly');
            $('#hod-com').attr('readonly','readonly');
            $('#hod-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#hod-date'))

        }
    })
}

function clickAccount() {
    $('#acct-clear').change(function(){
        if(this.checked)  {
            $('#acct-name').removeAttr('readonly');
            $('#acct-com').removeAttr('readonly');
            $('#acct-date').removeAttr('readonly');
            $('#acct-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#acct-name').attr('readonly','readonly');
            $('#acct-com').attr('readonly','readonly');
            $('#acct-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#acct-date'))

        }
    })
}

function clickExam() {
    $('#exam-clear').change(function(){
        if(this.checked)  {
            $('#exam-name').removeAttr('readonly');
            $('#exam-com').removeAttr('readonly');
            $('#exam-date').removeAttr('readonly');
            $('#exam-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#exam-name').attr('readonly','readonly');
            $('#exam-com').attr('readonly','readonly');
            $('#exam-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#exam-date'))

        }
    })
}
function clickPrincipal() {
    $('#principal-clear').change(function(){
        if(this.checked)  {
            $('#principal-name').removeAttr('readonly');
            $('#principal-com').removeAttr('readonly');
            $('#principal-date').removeAttr('readonly');
            $('#principal-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#principal-name').attr('readonly','readonly');
            $('#principal-com').attr('readonly','readonly');
            $('#principal-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#principal-date'))

        }
    })
}
function clickDeputy() {
    $('#deputy-clear').change(function(){
        if(this.checked)  {
            $('#deputy-name').removeAttr('readonly');
            $('#deputy-com').removeAttr('readonly');
            $('#deputy-date').removeAttr('readonly');
            $('#deputy-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#deputy-name').attr('readonly','readonly');
            $('#deputy-com').attr('readonly','readonly');
            $('#deputy-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#deputy-date'))

        }
    })
}
function clickCord() {
    $('#cord-clear').change(function(){
        if(this.checked)  {
            $('#cord-name').removeAttr('readonly');
            $('#cord-com').removeAttr('readonly');
            $('#cord-date').removeAttr('readonly');
            $('#cord-date').datepicker().datepicker("setDate",new Date());

        }
        else{
            $('#cord-name').attr('readonly','readonly');
            $('#cord-com').attr('readonly','readonly');
            $('#cord-date').attr('readonly','readonly');
            $.datepicker._clearDate($('#cord-date'))

        }
    })
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
function createClearanceTbl() {
    var table=$('#clearance-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'findAllClearances',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "saveDate",
                render: function ( data, type, full, meta ) {
                    if(full.saveDate!==null) {
                        return moment(full.saveDate).format('DD/MM/YYYY');
                    }
                    else{
                        return "";
                    }
                }

            },
            {data:"name"},
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    if (full.student != null) {
                        return full.student.admNo;
                    }
                }
            },

        { data: "student" ,
            render: function ( data, type, full, meta ) {
                if (full.student.forms != null) {
                    return full.student.forms.abbr;
                }
            }
        },

        { data: "examDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.examDate!==null) {
                      return "Yes"
                    }
                    else{
                       return "No"
                    }
                }
            },
        { data: "acctDate" ,
            render: function ( data, type, full, meta ) {
                if(full.acctDate!==null) {
                  return "Yes"
                }
                else{
                   return "No";
                }
            }
        },
        { data: "libraryDate" ,
            render: function ( data, type, full, meta ) {
                if(full.libraryDate!==null) {
                   return "Yes"
                }
                else{
                   return "No";
                }
            }
        },
            { data: "clearedGrad" ,
                render: function ( data, type, full, meta ) {
                    if(full.clearedGrad==="Y") {
                      return "Yes"
                    }
                    else{
                         return "No";
                    }
                }
            },
            { data: "hodDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.hodDate!==null) {
                       return "Yes"
                    }
                    else{
                       return "No";
                    }
                }
            },
            { data: "laboratoryDate" ,
                render: function ( data, type, full, meta ) {
                    if(full.laboratoryDate!==null) {
                       return "Yes"
                    }
                    else{
                      return "No";
                    }
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#clearance-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#clearance-tbl').on( 'click', 'tbody tr', function () {
        makeReadOnly();
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#clear-code').val(aData[0].clearanceCode);
        $('#stud-id').val(aData[0].student.stId);
        $('#stud-name').val(aData[0].student.name);
        selectStudentCode();

        $('#lib-name').val(aData[0].libraryName);
        $('#lib-com').val(aData[0].libraryComment);

        if(aData[0].libraryDate!=null) {
            $('#lib-date').val(moment(aData[0].libraryDate).format('DD/MM/YYYY'))
            $('#lib-name').removeAttr('readonly');
            $('#lib-com').removeAttr('readonly');
            $('#lib-date').removeAttr('readonly');
            $('#lib-clear').prop('checked',true);
        }

        $('#lab-name').val(aData[0].laboratoryName);
        $('#lab-com').val(aData[0].laboratoryComment);
        if(aData[0].laboratoryDate!=null) {
            $('#lab-date').val(moment(aData[0].laboratoryDate).format('DD/MM/YYYY'))
            $('#lab-name').removeAttr('readonly');
            $('#lab-com').removeAttr('readonly');
            $('#lab-date').removeAttr('readonly');
            $('#lab-clear').prop('checked',true);
        }
        $('#hod-name').val(aData[0].hodName);
        $('#hod-com').val(aData[0].hodComment);
        if(aData[0].hodDate!=null) {
            $('#hod-date').val(moment(aData[0].hodDate).format('DD/MM/YYYY'))
            $('#hod-name').removeAttr('readonly');
            $('#hod-com').removeAttr('readonly');
            $('#hod-date').removeAttr('readonly');
            $('#hod-clear').prop('checked',true);
        }
        $('#acct-name').val(aData[0].acctName);
        $('#acct-com').val(aData[0].acctComment);
        if(aData[0].acctDate!=null) {
            $('#acct-date').val(moment(aData[0].acctDate).format('DD/MM/YYYY'))
            $('#acct-name').removeAttr('readonly');
            $('#acct-com').removeAttr('readonly');
            $('#acct-date').removeAttr('readonly');
            $('#acct-clear').prop('checked',true);
        }
        $('#exam-name').val(aData[0].examName);
        $('#exam-com').val(aData[0].examComment);
        if(aData[0].examDate!=null) {
            $('#exam-date').val(moment(aData[0].examDate).format('DD/MM/YYYY'))
            $('#exam-name').removeAttr('readonly');
            $('#exam-com').removeAttr('readonly');
            $('#exam-date').removeAttr('readonly');
            $('#exam-clear').prop('checked',true);
        }
        $('#principal-name').val(aData[0].principalName);
        $('#principal-com').val(aData[0].principalComment);
        if(aData[0].principalDate!=null) {
            $('#principal-date').val(moment(aData[0].principalDate).format('DD/MM/YYYY'))
            $('#principal-name').removeAttr('readonly');
            $('#principal-com').removeAttr('readonly');
            $('#principal-date').removeAttr('readonly');
            $('#principal-clear').prop('checked',true);
        }
        $('#deputy-name').val(aData[0].deputyName);
        $('#deputy-com').val(aData[0].deputyComment);
        if(aData[0].deputyDate!=null) {
            $('#deputy-date').val(moment(aData[0].deputyDate).format('DD/MM/YYYY'))
            $('#deputy-name').removeAttr('readonly');
            $('#deputy-date').removeAttr('readonly');
            $('#deputy-com').removeAttr('readonly');
            $('#deputy-clear').prop('checked',true);
        }
        $('#cord-name').val(aData[0].cordName);
        $('#cord-com').val(aData[0].cordComment);
        if(aData[0].cordDate!=null) {
            $('#cord-date').val(moment(aData[0].cordDate).format('DD/MM/YYYY'))
            $('#cord-com').removeAttr('readonly');
            $('#cord-date').removeAttr('readonly');
            $('#cord-name').removeAttr('readonly');
            $('#cord-clear').prop('checked',true);
        }
        if(aData[0].clearedGrad=="Y"){
           $('#grad-clear').prop('checked',true);
        }
        else{
            $('#grad-clear').prop('checked',false);

        }

    })

    return table;


}

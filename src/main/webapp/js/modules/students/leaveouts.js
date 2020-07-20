$(document).ready(function () {
    clearAllPendingLeaves();
    selectClass();
grantLeave();
leaveTable();
returnLeave();
valueForSelect();
runSearch();
grantAllLeaves();
removeAllLeaves();
clearDat();
$(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });
})
function selectClass() {

    if($("#class-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "class-div",
            sort : 'classNo',
            change: function(e, a, v){
                $('#class').val(e.added.classCode)
                $('#dm').val(e.added.abbr)
                students();
                leaveOuts();
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



    function students() {
        var id=$('#class').val();

      var table=$('#students-tbl').DataTable({
            processing: true,
            serverSide: true,
            ajax: "getClassBlock/"+id,
            scrollY: "250px",
            scrollCollapse: true,
            paging: false,
            dom: "t",
           destroy: true,
            columns: [
                { "data": "admNo" }
            ],
          initComplete: function () {
              // enables multi selection extension
              $("#students-tbl").enableExtendedSelection();
          }
        } );
        $("#students-tbl").selectable(true);
        $('#btn-assign-leaveout').on('click',function () {
            var arr = $("#students-tbl").data().selection.items;
            console.log(arr);
            var myArray = [];
            for (var j = 0; j < arr.length; j++) {

                myArray.push(arr[j].admNo);
            }
            //for (var i = 0; i < table.rows('.selected').data().length; i++) {
            //    if (table.rows('.selected').data()[i] === undefined || table.rows('.selected').data()[i] === null) {
            //    }
            //    else {
            //        arr.push(table.rows('.selected').data()[i].roleId);
            //    }
            //}

            if (myArray.length == 0) {
                bootbox.alert("No Students selected to move");
                return;
            }
            $('#myPleaseWait').modal('show');
            var $currForm = $('#leaves-form');
            var data = {};
            $currForm.serializeArray().map(function (x) {
                data[x.name] = x.value;
            });
            var url = "inSession";
            data.admNo = myArray;
            console.log(myArray)
            console.log(JSON.stringify(data));
            $.ajax({
                type: 'POST',
                url: url,
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json"
            })
        .done(function(s){
            $('#myPleaseWait').modal('hide');

            $('#students-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();

        }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#students-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();


            });

        })
}
function grantAllLeaves() {
$('#btn-assign-batch-leaveout').on('click',function () {
    var id=$('#class').val();
    console.log(id)
    if(id===''){
        bootbox.alert('select class first');
    }
    else{
        $('#myPleaseWait').modal('show');
        $.ajax({
        type: 'POST',
        url: 'grantAll',
        data: {
            'classId': id
        },
    })
        .done(function (s) {
$('#myPleaseWait').modal('hide');
            $('#students-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();

        }).fail(function (xhr, error) {
        $('#myPleaseWait').modal('hide');
        $('#students-tbl').DataTable().ajax.reload();
        $('#leaveouts-tbl').DataTable().ajax.reload();
    });
}
})
}
function removeAllLeaves() {
    $('#btn-remove-batch-leaveout').on('click',function () {
        var id=$('#class').val();
        if(id===''){
            bootbox.alert('select class first');
        }
        else {
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'removeAllLeaves',
                data: {
                    'classId': id
                },
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');

                    $('#students-tbl').DataTable().ajax.reload();
                    $('#leaveouts-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#students-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
            });
        }
    })
}
function leaveOuts() {
    var id=$('#class').val();

    var table=$('#leaveouts-tbl').DataTable({
        processing: true,
        serverSide: true,
        destroy: true,
        "ajax": "getLeaveClassBlock/"+id,
        "scrollY": "250px",
        "scrollCollapse": true,
        "paging": false,
        "dom": "t",
        "columns": [
            { "data": "admNo"
            }
        ],
        initComplete: function () {
            // enables multi selection extension
            $("#leaveouts-tbl").enableExtendedSelection();
        }
    } );
    $("#leaveouts-tbl").selectable(true);
    $('#btn-remove-leaveout').on('click',function () {
        var arr = $("#leaveouts-tbl").data().selection.items;
        console.log(arr);
        var myArray = [];
        for (var j = 0; j < arr.length; j++) {

            myArray.push(arr[j].admNo);
            console.log(myArray);

        }
        //for (var i = 0; i < table.rows('.selected').data().length; i++) {
        //    if (table.rows('.selected').data()[i] === undefined || table.rows('.selected').data()[i] === null) {
        //    }
        //    else {
        //        arr.push(table.rows('.selected').data()[i].roleId);
        //    }
        //}

        if (myArray.length == 0) {
            bootbox.alert("No Students selected to move");
            return;
        }
        $('#myPleaseWait').modal('show');
        var $currForm = $('#leaves-form');
        var data = {};
        $currForm.serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        var url = "offSession";
        data.admNo = myArray;
        console.log(myArray)
        console.log(JSON.stringify(data));
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json"
        })
            .done(function(s){
                $('#myPleaseWait').modal('hide');
                $('#students-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();

            }).fail(function (xhr, error) {
            $('#myPleaseWait').modal('hide');

            $('#students-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();


        });
    })
}
function clearAllPendingLeaves(){
    $.ajax({
        type: 'GET',
        url: 'clearAllPendingLeaves',

    }).done(function () {

    }).fail(function (xhr, error) {
        $('#myPleaseWait').modal('hide');
        new PNotify({
            title: 'Error',
            text: xhr.responseText,
            type: 'error',
            styling: 'bootstrap3'
        });

    })
}
function grantLeave() {
    $('#leave').on('click', function () {
        var dateLeft = $('#dl').val();
        var dateReturn = $('#dtr').val();
        var reason = $('#reason').val();
        var classId= $('#class').val();
        if(dateLeft===''|| dateReturn===''|| classId===''){
            bootbox.alert('First set dates to proceed ');
        }
        else {
            $('#myPleaseWait').modal('show');

            $.ajax({
                type: 'GET',
                url: 'grantLeaveOut',
                data: {
                    'left': dateLeft,
                    'returnDate': dateReturn,
                    'reason': reason,
                    'classId':classId
                },
            }).done(function () {
                $('#myPleaseWait').modal('hide');

                new PNotify({
                    title: 'Success',
                    text: 'Granted leave Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#students-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
                $('#students-leave-tbl').DataTable().ajax.reload();
            }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#students-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
                $('#students-leave-tbl').DataTable().ajax.reload();

            })
        }
    })
}
function returnLeave() {
    $('#return').on('click', function () {
        var dateReturn = $('#dr').val();
        $('#myPleaseWait').modal('show');
        $.ajax({
            type: 'GET',
            url: 'returnLeaveOut',
            data: {

                'returnDate': dateReturn,

            },
        }).done(function () {
            $('#myPleaseWait').modal('hide');

            new PNotify({
                title: 'Success',
                text: 'Student resumed Successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            $('#students-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();
            $('#students-leave-tbl').DataTable().ajax.reload();
        }).fail(function (xhr, error) {
            $('#myPleaseWait').modal('hide');
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
            $('#students-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();
            $('#students-leave-tbl').DataTable().ajax.reload();

        })
    })
}
function leaveTable() {

    var table=$('#students-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'onLeaveStudents',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    return full.student.admNo;
                }
            },
            { data: "reason" },

            { data: "dateLeft" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateLeft).format('DD/MM/YYYY');
                }
            },
            { data: "dateExpected" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateExpected).format('DD/MM/YYYY');
                }
            },
            { data: "dateReturned" ,
                render: function ( data, type, full, meta ) {
                if(full.dateReturned!==null) {
                    return moment(full.dateReturned).format('DD/MM/YYYY');
                }
                else{
                    "";
                }
                }
            },
            { data: "grantedBy",
                render: function ( data, type, full, meta ) {
                    if (full.grantedBy.username !== null) {
                        return full.grantedBy.username;
                    } else {
                        return "user";
                    }
                }
            },
            { data: "dateReturned",
                render: function ( data, type, full, meta ) {
                if(full.dateReturned===null){
                    return "No"
                }
                else{
                    return "Yes"
                }
                }
            },
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    return full.student.name;
                }
            }

        ]
    } );

    $.fn.dataTable.ext.errMode = 'none';

    $('#students-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#students-leave-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#class').val(aData[0].forms.classCode);
        $('#dm').val(aData[0].forms.abbr);
        selectClass();
        console.log(aData[0].student.stId);
        myStud(aData[0].student.stId);
        students();
        $('#dl').val(moment(aData[0].dateLeft).format('DD/MM/YYYY'))
        $('#dtr').val(moment(aData[0].dateExpected).format('DD/MM/YYYY'))
        if(aData[0].dateReturned){
            $('#dr').val(moment(aData[0].dateReturned).format('DD/MM/YYYY'))
        }
        $('#reason').val(aData[0].reason);
    })
    return table;

}
function clearDat(){
    $('#clear').on('click',function () {
window.location.reload()
    })
}
function myStud(stId){
    console.log(stId);
    $.ajax({
        type: 'GET',
        url: 'setPending',
        data:{
            'stId':stId
        }
    }).done(function (s) {
        leaveOuts();
   }).fail(function (xhr, error) {
        new PNotify({
            title: 'Error',
            text: xhr.responseText,
            type: 'error',
            styling: 'bootstrap3'
        });

    })
}
function valueForSelect() {
    $('input:radio[name="budSet"]').change(function () {
        if(this.checked && this.value==='returned'){
            $('#valForSelect').val('re');
        }
        else if (this.checked && this.value==='pending'){
            $('#valForSelect').val('pe');
        }
        else if (this.checked && this.value==='name'){
            $('#valForSelect').val('name');
        }
    })
}
function runSearch() {
    $('#run').click( function () {
        var sel=$('#valForSelect').val();
        if(sel==='pe'){
            searchPending();
        }
        else if(sel==='re'){
            searchReturned();
        }
        else if(sel==='name'){
           searchName();
        }
    })
}
function searchPending() {

    var table=$('#students-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'pendingLeaveStudents',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    return full.student.admNo;
                }
            },
            { data: "reason" },

            { data: "dateLeft" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateLeft).format('DD/MM/YYYY');
                }
            },
            { data: "dateExpected" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateExpected).format('DD/MM/YYYY');
                }
            },
            { data: "dateReturned" ,
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null) {
                        return moment(full.dateReturned).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }
            },
            { data: "grantedBy",
                render: function ( data, type, full, meta ) {
                    if (full.grantedBy.username !== null) {
                        return full.grantedBy.username;
                    } else {
                        return "user";
                    }
                }
            },
            { data: "dateReturned",
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned===null){
                        return "No"
                    }
                    else{
                        return "Yes"
                    }
                }
            },
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    return full.student.name;
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#students-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#students-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#class').val(aData[0].forms.classCode);
        $('#dm').val(aData[0].forms.abbr);
        selectClass();
        myStud(aData[0].student.stId);
        students();
        $('#dl').val(moment(aData[0].dateLeft).format('DD/MM/YYYY'))
        $('#dtr').val(moment(aData[0].dateExpected).format('DD/MM/YYYY'))
        if(aData[0].dateReturned){
            $('#dr').val(moment(aData[0].dateReturned).format('DD/MM/YYYY'))

        }
        $('#reason').val(aData[0].reason);
    })
    return table;

}
function searchReturned() {

    var table=$('#students-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'returnedLeaveStudents',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    return full.student.admNo;
                }
            },
            { data: "reason" },

            { data: "dateLeft" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateLeft).format('DD/MM/YYYY');
                }
            },
            { data: "dateExpected" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateExpected).format('DD/MM/YYYY');
                }
            },
            { data: "dateReturned" ,
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null) {
                        return moment(full.dateReturned).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }
            },
            { data: "grantedBy",
                render: function ( data, type, full, meta ) {
                    if (full.grantedBy.username !== null) {
                        return full.grantedBy.username;
                    } else {
                        return "user";
                    }
                }
            },
            { data: "dateReturned",
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned===null){
                        return "No"
                    }
                    else{
                        return "Yes"
                    }
                }
            },
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    return full.student.name;
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#students-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#students-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#class').val(aData[0].forms.classCode);
        $('#dm').val(aData[0].forms.abbr);
        selectClass();
        myStud(aData[0].student.stId);
        students();
        $('#dl').val(moment(aData[0].dateLeft).format('DD/MM/YYYY'))
        $('#dtr').val(moment(aData[0].dateExpected).format('DD/MM/YYYY'))
        if(aData[0].dateReturned){
            $('#dr').val(moment(aData[0].dateReturned).format('DD/MM/YYYY'))

        }
        $('#reason').val(aData[0].reason);
    })
    return table;

}
function searchName() {
var name=$('#stds-id').val();
    var table=$('#students-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'nameLeaveStudents',
            data:{
                'name':name,
            }
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },
            { data: "student",
                render: function ( data, type, full, meta ) {
                    return full.student.admNo;
                }
            },
            { data: "reason" },

            { data: "dateLeft" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateLeft).format('DD/MM/YYYY');
                }
            },
            { data: "dateExpected" ,
                render: function ( data, type, full, meta ) {
                    return moment(full.dateExpected).format('DD/MM/YYYY');
                }
            },
            { data: "dateReturned" ,
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned!==null) {
                        return moment(full.dateReturned).format('DD/MM/YYYY');
                    }
                    else{
                        "";
                    }
                }
            },
            { data: "grantedBy",
                render: function ( data, type, full, meta ) {
                    if (full.grantedBy.username !== null) {
                        return full.grantedBy.username;
                    } else {
                        return "user";
                    }
                }
            },
            { data: "dateReturned",
                render: function ( data, type, full, meta ) {
                    if(full.dateReturned===null){
                        return "No"
                    }
                    else{
                        return "Yes"
                    }
                }
            },
            { data: "student" ,
                render: function ( data, type, full, meta ) {
                    return full.student.name;
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#students-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#students-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#class').val(aData[0].forms.classCode);
        $('#dm').val(aData[0].forms.abbr);
        selectClass();
        myStud(aData[0].student.stId);
        students();
        $('#dl').val(moment(aData[0].dateLeft).format('DD/MM/YYYY'))
        $('#dtr').val(moment(aData[0].dateExpected).format('DD/MM/YYYY'))
        if(aData[0].dateReturned){
            $('#dr').val(moment(aData[0].dateReturned).format('DD/MM/YYYY'))

        }
        $('#reason').val(aData[0].reason);
    })
    return table;

}

$(document).ready(function () {
    clearAllPendingLeaves();
    grantLeave();
    returnLeave();
    valueForSelect();
    runSearch();
    grantAllLeaves();
    removeAllLeaves();
    clearDat();
    teachersTbl();
    leaveOuts();
teacherLeaveTable();
    $(".datepicker-input").each(function() {
        $(this).datetimepicker({
            format: 'DD/MM/YYYY'
        });

    });
})


function teachersTbl() {

    var table=$('#teachers-tbl').DataTable({
        processing: true,
        serverSide: true,
        ajax: "getSchoolTeachers",
        scrollY: "250px",
        scrollCollapse: true,
        paging: false,
        dom: "t",
        destroy: true,
        columns: [
            { "data": "name" }
        ],
        initComplete: function () {
            // enables multi selection extension
            $("#teachers-tbl").enableExtendedSelection();
        }
    } );
    $("#teachers-tbl").selectable(true);
    $('#btn-assign-leaveout').on('click',function () {
        var arr = $("#teachers-tbl").data().selection.items;
        console.log(arr);
        var myArray = [];
        for (var j = 0; j < arr.length; j++) {

            myArray.push(arr[j].teacherCode);
        }
        //for (var i = 0; i < table.rows('.selected').data().length; i++) {
        //    if (table.rows('.selected').data()[i] === undefined || table.rows('.selected').data()[i] === null) {
        //    }
        //    else {
        //        arr.push(table.rows('.selected').data()[i].roleId);
        //    }
        //}

        if (myArray.length == 0) {
            bootbox.alert("No Teachers selected to move");
            return;
        }
        $('#myPleaseWait').modal('show');
        var $currForm = $('#leaves-form');
        var data = {};
        $currForm.serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        var url = "inSessionTeacher";
        data.teacherCode = myArray;
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

                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();

            }).fail(function (xhr, error) {
            $('#myPleaseWait').modal('hide');

            $('#teachers-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();


        });

    })
}
function grantAllLeaves() {
    $('#btn-assign-batch-leaveout').on('click',function () {
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'grantAllTeachers',
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');
                    $('#teachers-tbl').DataTable().ajax.reload();
                    $('#leaveouts-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
            });

    })
}
function removeAllLeaves() {
    $('#btn-remove-batch-leaveout').on('click',function () {
            $('#myPleaseWait').modal('show');
            $.ajax({
                type: 'POST',
                url: 'removeAllTeacherLeaves',
            })
                .done(function (s) {
                    $('#myPleaseWait').modal('hide');

                    $('#teachers-tbl').DataTable().ajax.reload();
                    $('#leaveouts-tbl').DataTable().ajax.reload();

                }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');

                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
            });

    })
}
function leaveOuts() {

    var table=$('#leaveouts-tbl').DataTable({
        processing: true,
        serverSide: true,
        destroy: true,
        "ajax": "getTeacherLeaves",
        "scrollY": "250px",
        "scrollCollapse": true,
        "paging": false,
        "dom": "t",
        "columns": [
            { "data": "name"
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

            myArray.push(arr[j].teacherCode);
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
            bootbox.alert("No Teachers selected to move");
            return;
        }
        $('#myPleaseWait').modal('show');
        var $currForm = $('#leaves-form');
        var data = {};
        $currForm.serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        var url = "offSessionTeacher";
        data.teacherCode = myArray;
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
                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();

            }).fail(function (xhr, error) {
            $('#myPleaseWait').modal('hide');

            $('#teachers-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();


        });
    })
}
function clearAllPendingLeaves(){
    $.ajax({
        type: 'GET',
        url: 'clearAllPendingTeacherLeaves',

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
                url: 'grantTeacherLeaveOut',
                data: {
                    'left': dateLeft,
                    'returnDate': dateReturn,
                    'reason': reason,
                },
            }).done(function () {
                $('#myPleaseWait').modal('hide');

                new PNotify({
                    title: 'Success',
                    text: 'Granted leave Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
                $('#teacher-leave-tbl').DataTable().ajax.reload();
            }).fail(function (xhr, error) {
                $('#myPleaseWait').modal('hide');
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#teachers-tbl').DataTable().ajax.reload();
                $('#leaveouts-tbl').DataTable().ajax.reload();
                $('#teacher-leave-tbl').DataTable().ajax.reload();

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
            url: 'returnTeacherLeaveOut',
            data: {

                'returnDate': dateReturn,

            },
        }).done(function () {
            $('#myPleaseWait').modal('hide');

            new PNotify({
                title: 'Success',
                text: 'Teacher resumed Successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            $('#teachers-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();
            $('#teacher-leave-tbl').DataTable().ajax.reload();
        }).fail(function (xhr, error) {
            $('#myPleaseWait').modal('hide');
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
            $('#teachers-tbl').DataTable().ajax.reload();
            $('#leaveouts-tbl').DataTable().ajax.reload();
            $('#teacher-leave-tbl').DataTable().ajax.reload();

        })
    })
}
function teacherLeaveTable() {

    var table=$('#teacher-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'onLeaveTeachers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.teacherCode;
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.name;
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

        ]
    } );

    $.fn.dataTable.ext.errMode = 'none';

    $('#teacher-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#teacher-leave-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();

        myStud(aData[0].teachers.teacherCode);
        teachersTbl();
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
function myStud(id){
    $.ajax({
        type: 'GET',
        url: 'setTeacherPending',
        data:{
            'teacher':id
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

    var table=$('#teacher-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'pendingLeaveTeachers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.teacherCode;
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.name;
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



        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teacher-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#teacher-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        console.log(aData[0].teachers.teacherCode)
        myStud(aData[0].teachers.teacherCode);
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

    var table=$('#teacher-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'returnedLeaveTeachers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.teacherCode;
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.name;
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

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teacher-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#teacher-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        myStud(aData[0].teachers.teacherCode);
        teachersTbl();
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
    var table=$('#teacher-leave-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'nameLeaveTeachers',
            data:{
                'name':name,
            }
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:'t',
        columns: [
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.teacherCode;
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.name;
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
            }


        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teacher-leave-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    $('#teacher-leave-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        myStud(aData[0].teachers.teacherCode);
        teachersTbl();
        $('#dl').val(moment(aData[0].dateLeft).format('DD/MM/YYYY'))
        $('#dtr').val(moment(aData[0].dateExpected).format('DD/MM/YYYY'))
        if(aData[0].dateReturned){
            $('#dr').val(moment(aData[0].dateReturned).format('DD/MM/YYYY'))

        }
        $('#reason').val(aData[0].reason);
    })
    return table;

}

$(document).ready(function () {
getStaffTbl()
    getTeacherTbl()
    $('.btn-trun').click(function () {

        getTeacherRegisterTbl()
    })
    $('.btn-srun').click(function () {
        getStaffRegisterTbl()
    })
})
function staffTimeIn(button) {
    var choice = JSON.parse(decodeURI($(button).data("choice")));
    bootbox.confirm("Record Report time for "+choice["name"] +" ?", function(result) {
        if (result) {
            $.ajax({
                type: 'GET',
                url: 'recordStaffTimeIn/' + choice["staffCode"],

            })
                .done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Reported Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

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
function teacherTimeIn(button) {


    var choice = JSON.parse(decodeURI($(button).data("choice")));
    bootbox.confirm("Record Report time for " + choice["name"] + " ?", function (result) {
        if (result) {
            $.ajax({
                type: 'GET',
                url: 'recordTimeIn/' + choice["teacherCode"],

            })
                .done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Reported Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

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

function teacherTimeOut(button) {

    var choice = JSON.parse(decodeURI($(button).data("choice")));
    bootbox.confirm("Record Leaving time for " + choice["name"] + " ?", function (result) {
        if (result) {
            $.ajax({
                type: 'GET',
                url: 'recordTimeOut/' + choice["teacherCode"],

            })
                .done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Left Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

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
function staffTimeOut(button) {
    var choice = JSON.parse(decodeURI($(button).data("choice")));
    bootbox.confirm("Record Leaving time for " + choice["name"] + " ?", function (result) {
        if (result) {
            $.ajax({
                type: 'GET',
                url: 'recordStaffTimeOut/' + choice["staffCode"],

            })
                .done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Left Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

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
function staffClick() {

    
}
function teacherClick(){

}
function getStaffTbl() {
    var table=$('#s-register-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'findRegisterWorkers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "titles",
                render: function ( data, type, full, meta ) {
                    if (full.titles != null) {
                        return full.titles.titleName;
                    }
                }
            },
            { data: "name"},
            { data: "responsibility" ,
                render: function ( data, type, full, meta ) {
                    if (full.responsibility != null) {
                        return full.responsibility.responsibilityName;
                    }
                }
            },

            { data: "idNo"},

            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="staffTimeIn(this);">Time-In<i class="fa fa-clock-o"></button>';
                }
            },
            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="staffTimeOut(this);">Time-Out<i class="fa fa-clock-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teachers-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;

}
function getTeacherTbl(){
    var table=$('#t-register-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'findRegisterTeachers',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "titles",
                render: function ( data, type, full, meta ) {
                    if (full.titles != null) {
                        return full.titles.titleName;
                    }
                }
            },
            { data: "name"},

            { data: "responsibility" ,
                render: function ( data, type, full, meta ) {
                    if (full.responsibility != null) {
                        return full.responsibility.responsibilityName;
                    }
                }
            },
            { data: "tscNo"},

            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="teacherTimeIn(this);">Time-In<i class="fa fa-clock-o"></button>';
                }
            },
            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="teacherTimeOut(this);">Time-Out<i class="fa fa-clock-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teachers-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;
}

function getStaffRegisterTbl() {
    console.log('reloading table...')
    var name=$('#s-name').val()
    console.log(name)
    var table=$('#s-register-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'nameRegisterStaff',
            data:{
                'name':name,
            }
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "titles",
                render: function ( data, type, full, meta ) {
                    if (full.titles != null) {
                        return full.titles.titleName;
                    }
                }
            },
            { data: "name"},
            { data: "responsibility" ,
                render: function ( data, type, full, meta ) {
                    if (full.responsibility != null) {
                        return full.responsibility.responsibilityName;
                    }
                }
            },

            { data: "idNo"},

            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="staffTimeIn(this);">Time-In<i class="fa fa-clock-o"></button>';
                }
            },
            { data: "staffCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="staffTimeOut(this);">Time-Out<i class="fa fa-clock-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teachers-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;

}
function getTeacherRegisterTbl(){
    console.log('reloading table...')
    var name=$('#t-name').val()
    console.log(name)
    var table=$('#t-register-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'nameRegisterTeachers',
            data:{
                'name':name,
            }
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "titles",
                render: function ( data, type, full, meta ) {
                    if (full.titles != null) {
                        return full.titles.titleName;
                    }
                }
            },
            { data: "name"},

            { data: "responsibility" ,
                render: function ( data, type, full, meta ) {
                    if (full.responsibility != null) {
                        return full.responsibility.responsibilityName;
                    }
                }
            },
            { data: "tscNo"},

            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="teacherTimeIn(this);">Time-In<i class="fa fa-clock-o"></button>';
                }
            },
            { data: "teacherCode" ,
                render: function ( data, type, full, meta ) {
                    return '<button type="button" class="btn btn-info btn-xs" data-choice='+encodeURI(JSON.stringify(full)) + ' onclick="teacherTimeOut(this);">Time-Out<i class="fa fa-clock-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#teachers-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;



    return table;
}
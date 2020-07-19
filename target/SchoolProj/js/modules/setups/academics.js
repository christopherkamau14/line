
$(document).ready(function () {
    selectDept();
    addSubject();
    addDept();
    addTitle();
    addResponsibility();
    createDeptTbl();
    createSubjectsTbl()
    createTitlesTbl();
    createResTbl();
    saveDepartment();
    saveSubject();
    saveTitles();
    saveResponsibility()
    editDept();
    editSubjects();
    editResponsibility();
    editTitles();
    deleteDepts();
    deleteSubjects();
    deleteTitles();
    deleteResponsibility();
})
function addResponsibility(){
    $('#btn-add-responsibility').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#res-id').val('');
        $("#resModal").modal('show');

    })
}
function addTitle(){
    $('#btn-add-titles').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#title-id').val('');
        $("#titleModal").modal('show');

    })
}
function addSubject(){
    $('#btn-add-dept').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#dept-id').val('');
        $("#deptModal").modal('show');

    })
}
function deleteSubjects(){
    $('#subjects-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteSubjects/' + data;
        bootbox.confirm("Are you sure want to delete this Subjects?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Subjects Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#subjects-tbl").DataTable().ajax.reload();

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
    })
}
function deleteDepts(){
    $('#dept-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        console.log(data);
        var url = 'deleteDepartment/' + data;
        bootbox.confirm("Are you sure want to delete this Departments?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Departments Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#dept-tbl").DataTable().ajax.reload();

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
    })
}
function deleteTitles(){
    $('#titles-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        console.log(data);
        var url = 'deleteTitle/' + data;
        bootbox.confirm("Are you sure want to delete this Title?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Title Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#titles-tbl").DataTable().ajax.reload();

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
    })
}
function deleteResponsibility(){
    $('#responsibility-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        console.log(data);
        var url = 'deleteRes/' + data;
        bootbox.confirm("Are you sure want to delete this Responsibility?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Responsibility Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#responsibility-tbl").DataTable().ajax.reload();

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
    })
}
function addDept(){
    $('#btn-add-subject').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#subject-id').val('');
        $("#subjectsModal").modal('show');

    })
}
function selectDept() {

    if($("#dept-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "dept-div",
            sort : 'departmentName',
            change: function(e, a, v){
                $('#dept-code').val(e.added.departmentCode)
                $('#dept-name').val(e.added.departmentName)
            },
            formatResult : function(a)
            {
                return a.departmentName;
            },
            formatSelection : function(a)
            {

                return a.departmentName;
            },
            initSelection: function (element, callback) {
                var code = $("#dept-code").val();
                var name = $("#dept-name").val();
                var data = {departmentName:name,departmentCode:code};
                callback(data);
            },
            id: "departmentCode",
            placeholder:"Department",
        });
    }}
function createDeptTbl() {
    var table=$('#dept-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getDepartment',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "departmentName" },
            { data: "departmentDesc" },
            { data: "departmentSeq"},
            { data: "departmentCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.departmentCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';
                }
            },
            { data: "departmentCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.departmentCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#dept-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function createSubjectsTbl() {
    var table=$('#subjects-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getSubjects',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "name" },
            { data: "subjectDesc" },
            { data: "order" },
            { data: "department",
                render: function ( data, type, full, meta ) {
                    if(full.department!==null) {
                        return full.department.departmentName;
                    }
                    else{
                        "";
                    }
                }

            },
            { data: "mainSubject"},
            { data: "combinedSubject"},
            { data: "prosSubject"},
            { data: "timeTableName"
            },
            { data: "subjectCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.subjectCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';
                }
            },
            { data: "subjectCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.subjectCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#subjects-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function createResTbl() {
    var table=$('#responsibility-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getResponsibilities',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "responsibilityName" },
            { data: "category" },
            { data: "responsibilityCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.responsibilityCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';
                }
            },
            { data: "responsibilityCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.responsibilityCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#titles-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function createTitlesTbl() {
    var table=$('#titles-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getTitles',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "titleName" },
            { data: "titleDesc" },
            { data: "titleCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.titleCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';
                }
            },
            { data: "titleCode" ,
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.titleCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            }


        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#titles-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;
    return table;
}
function saveDepartment() {
    var $form= $("#dept-form");
    var validator = $form.validate();

    $('#dept-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveDept',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Department Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#dept-tbl").DataTable().ajax.reload();
                $('#deptModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#deptModal').modal('hide');

            });
        });
    $('#deptModal').on('hidden.bs.modal', function () {
        $('#dept-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveSubject() {
    var $form= $("#subject-form");
    var validator = $form.validate();

    $('#subject-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveSubject',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Subject Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#subjects-tbl").DataTable().ajax.reload();
                $('#subjectsModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#subjectsModal').modal('hide');

            });
        });
    $('#subjectsModal').on('hidden.bs.modal', function () {
        $('#subject-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveTitles() {
    var $form= $("#title-form");
    var validator = $form.validate();

    $('#title-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveTitles',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Titles Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#titles-tbl").DataTable().ajax.reload();
                $('#titleModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#titleModal').modal('hide');

            });
        });
    $('#titleModal').on('hidden.bs.modal', function () {
        $('#title-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveResponsibility() {
    var $form= $("#res-form");
    var validator = $form.validate();

    $('#res-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveRes',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Responsibilities Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#responsibility-tbl").DataTable().ajax.reload();
                $('#resModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#resModal').modal('hide');

            });
        });
    $('#resModal').on('hidden.bs.modal', function () {
        $('#res-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function editDept(){
    $('#dept-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'deptEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#dept-id').val(s.departmentCode);
            $('#dept-code').val(s.code);
            $('#dept-desc').val(s.departmentDesc);
            $('#dept-name').val(s.departmentName);
            $('#dept-seq').val(s.departmentSeq);
            $('#deptModal').modal('show');
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    })
}
function editSubjects(){
    $('#subjects-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'subjectEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#subject-id').val(s.subjectCode);
            $('#order').val(s.order);
            $('#subject-name').val(s.name);
            $('#sub-desc').val(s.subjectDesc);
            if(s.department!=null){
                $('#dept-code').val(s.department.departmentCode);
                $('#dept-name').val(s.department.departmentName);
                selectDept();
            }
            if(s.mainSubject==="Y"){
                $('#main').attr('checked',true);
            }
            else{
                $('#main').attr('checked',false);

            }
            if(s.combinedSubject==="Y"){
                $('#combined').attr('checked',true);
            }
            else{
                $('#combined').attr('checked',false);

            }
            if(s.prosSubject==="Y"){
                $('#pros').attr('checked',true);
            }
            else{
                $('#pros').attr('checked',false);

            }
            $('#mult').val(s.multiplier);
            $('#t-name').val(s.timeTableName);

            $('#subjectsModal').modal('show');
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    })
}
function editResponsibility(){
    $('#responsibility-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'responsibilityEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#res-id').val(s.responsibilityCode);
            $('#res-code').val(s.code);
            $('#res-name').val(s.responsibilityName);
            $('#cat').val(s.category);
            $('#resModal').modal('show');
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    })
}
function editTitles(){
    $('#titles-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'titlesEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#title-id').val(s.titleCode);
            $('#title-name').val(s.titleName);
            $('#title-desc').val(s.titleDesc);
            $('#titleModal').modal('show');
        }).fail(function (xhr, error) {
            new PNotify({
                title: 'Error',
                text: xhr.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        })

    })
}
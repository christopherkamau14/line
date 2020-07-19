$(document).ready(function () {
    selectProffession();
    selectDept();
    selectFatherProffession();
    nationClick();
    countyClick();
    subCountyClick();
    locationClick();
    subLocationClick();
    villageClick();
    healthClick();
    classClick();
    termClick();
    feeClick();
    dormClick();
    religionClick();
    sourceClick();
    docsClick();
    gradeClick();
    ProffessionClick();
    yearClick();
    statusClick();
    blockClick();
    selectNation();
    selectCounty();
    selectSubCounty();
    selectLocation();
    selectSubLocation();
    selectVillage();
    selectBlock();
    selectTeacher();
    selectClass();
    saveParents();
    saveCountry();
    saveCounty();
    saveSubCounty();
    deleteClass();
    saveLocation();
    saveHealth();
    saveForms();
    saveTerm();
    saveDorm();
    saveReligion();
    saveProffession();
    saveSources();
    saveBlock();
    saveYear();
    saveDocuments();
    saveSubLocation();
    saveVillage();
    saveFeeCategory();
    saveStatus();
    saveGrades();
    loadLocality();
    contryTable();
    getHealthTable();
    getSourcesTable();
    getReligionTable();
    getDocumentsTable();
    getProffessionTable();
    getParentsTable();
    getBlockTable();
    getClassTable();
    getTermTable();
    createGradesTbl();
    getFeeTable();
    getDormTable();
    getYearTable();
    getStatusTable();
    editParent();
    deleteParent();
    deleteBlock();
    editForm();
    editMyBlock();
    editTerm();
    editFeeCat();
    editYear();
    editHouse();
    editStatus();
    editHealth();
    editSource();
    editReligion();
    editDocument();
    editProffession();
    editCountry();
    editCounty();
    editSubCounty();
    editLocation();
    editSubLocation();
    editVillage();
    deleteDorm();
    deleteHealth();
    deleteProffession();
    deleteSources();
    deleteTerm();
    deleteYear();
    deleteReligion();
    deleteStatus();
    deleteDocuments();
    deleteCountry();
    deleteCounty();
    deleteSubCounty();
    deleteLocation();
    deleteSubLocation();
    deleteVillage();
    deleteFeeCategory();
    regKmSearch();
    regPrSearch();
    regGradeSearch();
    deleteGrade();
})
function deleteGrade(){
    $('#delete-grade').click( function () {
        var data = $('#grade-id').val();

        if(data===''){
            bootbox.alert('No Grade Selected to delete');
        }
        else {

            var url = 'deleteGrade/' + data;
            bootbox.confirm("Are you sure want to delete this Grade?", function (result) {
                if (result) {
                    $.ajax({
                        type: 'GET',
                        url: url,
                    }).done(function (s) {
                        new PNotify({
                            title: 'Success',
                            text: 'Grade Deleted Successfully',
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
function editCountry() {
    $('#country-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'countryEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#c-id').val(s.couCode);
            $('#country-name').val(s.couName);
            $('#country-desc').val(s.couDesc);
            $('#continent-desc').val(s.couContinent);
            $('#countryModal').modal('show');
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
function editCounty() {
    $('#county-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'countyEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#co-id').val(s.countyCode);
            $('#county-name').val(s.countyName);
            $('#county-desc').val(s.countyDesc);
            if(s.country!==null){
                $('#country-id').val(s.country.couCode);
                $('#coun-desc').val(s.country.couName);
                selectNation();
            }
            $('#countyModal').modal('show');
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
function editSubCounty() {
    $('#sub-county-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'subcountyEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#sub-id').val(s.subCode);
            $('#sub-county-name').val(s.subName);
            $('#sub-country-desc').val(s.subDesc);
            if(s.county!==null){
                $('#county-id').val(s.county.countyCode);
                $('#count-desc').val(s.county.countyName);
                selectCounty();
            }
            $('#subcountyModal').modal('show');
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
function editLocation() {
    $('#location-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'locationEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#l-id').val(s.louCode);
            $('#location-name').val(s.louName);
            $('#loc-desc').val(s.louDesc);
            if (s.subCounty !== null) {
                $('#sub-county-id').val(s.subCounty.subCode);
                $('#sub-count-desc').val(s.subCounty.subName);
                selectSubCounty();
            }
            $('#locationModal').modal('show');
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
function editSubLocation() {
    $('#sub-loc-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'sublocationEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#sl-id').val(s.subLouCode);
            $('#sub-location-name').val(s.subLouName);
            $('#sub-location-desc').val(s.subLouDesc);
            if (s.location !== null) {
                $('#loc-id').val(s.location.louCode);
                $('#location-desc').val(s.location.louName);
                selectLocation();
            }
            $('#sublocationModal').modal('show');
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
function editVillage() {
    $('#village-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'villageEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#v-id').val(s.vilCode);
            $('#village-name').val(s.vilName);
            $('#village-desc').val(s.vilDesc);
            if (s.subLocation !== null) {
                $('#sub-loc-id').val(s.subLocation.subLouCode);
                $('#sub-loc-desc').val(s.subLocation.subLouName);
                selectSubLocation();
            }
            $('#villageModal').modal('show');
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
function editProffession() {
    $('#proffession-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'proffessionEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#prof-id').val(s.profCode);
            $('#prof-name').val(s.profName);
            $('#prof-desc').val(s.profDesc);
            $('#proffessionModal').modal('show');
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
function editForm() {
    $('#class-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'classEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#class-id').val(s.classCode);
            $('#class-name').val(s.classNo);
            $('#class-desc').val(s.classDesc);
            if (s.block !== null) {
                $('#block-id').val(s.block.blockCode);
                $('#block-name').val(s.block.blockName);
                selectBlock();
            }
            if (s.teachers !== null) {
                $('#tea-id').val(s.teachers.teacherCode);
                $('#tea-name').val(s.teachers.name);
                selectTeacher();
            }
            if(s.active==="Yes"){
                $('#active').attr('checked',true);
            }
            else{
                $('#active').attr('checked',false);

            }
            $('#capacity').val(s.capacity);
            $('#classModal').modal('show');
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
function editDocument() {
    $('#docs-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'docsEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#doc-id').val(s.docCode);
            $('#doc-name').val(s.docName);
            $('#doc-desc').val(s.docDesc);
            if(s.docRequired==="on"){
                $('#doc-required').attr('checked',true);
            }else{
                $('#doc-required').attr('checked',false);
            }
            $('#docsModal').modal('show');
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
function editReligion() {
    $('#religion-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'religionEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#rel-id').val(s.relCode);
            $('#rel-desc').val(s.relDesc);
            $('#rel-name').val(s.relName);
            $('#w-day').val(s.relDay);
            $('#religionModal').modal('show');
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
function editSource() {
    $('#sources-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'sourcesEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#source-id').val(s.sourceCode);
            $('#source-name').val(s.sourceName);
            $('#source-desc').val(s.sourceDesc);
            $('#sourceModal').modal('show');
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
function editMyBlock() {
    $('#block-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'blockEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#block-id').val(s.blockCode);
            $('#block-name').val(s.blockName);
            $('#block-abbr').val(s.blockDesc);

            $('#blockModal').modal('show');
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
function editHealth() {
    $('#health-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'healthEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#condition-name').val(s.condName);
            $('#condition-desc').val(s.condDesc);
            $('#condition-id').val(s.condCode);
            $('#condition-comments').val(s.condComments);
            $('#healthModal').modal('show');
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
function editTerm() {
    $('#term-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'termEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#term-number').val(s.termNumber);
            $('#term-name').val(s.termName);
            $('#term-id').val(s.termCode);

            $('#termModal').modal('show');
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
function editFeeCat() {
    $('#fee-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'feeEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#cat-name').val(s.catName);
            if(s.defaultCat==="on"){
                $('#fee-category').prop('checked',true);
            }
            $('#cat-desc').val(s.catDesc);
            $('#cat-successor').val(s.catSuccessor);
            $('#cat-id').val(s.catCode);

            $('#feeModal').modal('show');
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
function editYear() {
    $('#year-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'yearEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#year-name').val(s.yearName);
            $('#year-number').val(s.yearNumber);
            $('#year-id').val(s.yearCode);

            $('#yearModal').modal('show');
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
function editHouse() {
    $('#dorm-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        var url = 'dormEdit/' + data;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            $('#dorm-name').val(s.dormName);
            $('#dorm-desc').val(s.dormDesc);
            $('#dorm-id').val(s.dormCode);
            $('#dorm-capacity').val(s.capacity);
            $('#dormModal').modal('show');
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
function editStatus() {
    $('#status-tbl').on('click','.btn-edit',function () {
        var data = $(this).closest('tr').find('#editId').val();
        $.ajax({
            type: 'GET',
            url: 'mystudEdit/' + data,
        }).done(function (s) {
            $('#status-name').val(s.statusName);
            $('#status-desc').val(s.statusDesc);
            $('#status-id').val(s.statusCode);
            $('#statusModal').modal('show');
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
function deleteDocuments(){
    $('#docs-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteDocuments/' + data;
        bootbox.confirm("Are you sure want to delete this Documents?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Documents Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#docs-tbl").DataTable().ajax.reload();

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
function deleteCountry(){
    $('#country-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteCountry/' + data;
        bootbox.confirm("Are you sure want to delete this Country?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Country Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#country-tbl").DataTable().ajax.reload();

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
function deleteCounty(){
    $('#county-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteCounty/' + data;
        bootbox.confirm("Are you sure want to delete this County?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'County Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#county-tbl").DataTable().ajax.reload();

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
function deleteSubCounty(){
    $('#sub-county-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteSubCounty/' + data;
        bootbox.confirm("Are you sure want to delete this SubCounty?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'SubCounty Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#sub-county-tbl").DataTable().ajax.reload();

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
function deleteLocation(){
    $('#location-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteLocation/' + data;
        bootbox.confirm("Are you sure want to delete this Location?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Location Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#location-tbl").DataTable().ajax.reload();

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
function deleteSubLocation(){
    $('#sub-loc-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteSubLocation/' + data;
        bootbox.confirm("Are you sure want to delete this SubLocation?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'SubLocation Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#sub-loc-tbl").DataTable().ajax.reload();

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
function deleteVillage(){
    $('#village-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteVillage/' + data;
        bootbox.confirm("Are you sure want to delete this Village?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Village Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#village-tbl").DataTable().ajax.reload();

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
function deleteStatus(){
    $('#status-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteStatus/' + data;
        bootbox.confirm("Are you sure want to delete this Status?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Status Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#status-tbl").DataTable().ajax.reload();

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
function deleteParent(){
    $('#parents-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#parentId').val();
        var url = 'deleteParent/' + data;
        bootbox.confirm("Are you sure want to delete this parent?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Parent Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#parents-tbl").DataTable().ajax.reload();

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
function deleteBlock(){
    $('#block-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteBlock/' + data;
        bootbox.confirm("Are you sure want to delete this Block?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Block Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#block-tbl").DataTable().ajax.reload();

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
function deleteClass(){
    $('#class-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteClass/' + data;
        bootbox.confirm("Are you sure want to delete this Class?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Class Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#class-tbl").DataTable().ajax.reload();

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
function deleteProffession(){
    $('#proffession-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteProffession/' + data;
        bootbox.confirm("Are you sure want to delete this Proffession?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Proffession Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#proffession-tbl").DataTable().ajax.reload();

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
function deleteSources(){
    $('#sources-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteSources/' + data;
        bootbox.confirm("Are you sure want to delete this Sources?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Sources Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#sources-tbl").DataTable().ajax.reload();

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
function deleteHealth(){
    $('#health-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteHealth/' + data;
        bootbox.confirm("Are you sure want to delete this Health?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Health Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#health-tbl").DataTable().ajax.reload();

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
function deleteDorm(){
    $('#dorm-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteDorm/' + data;
        bootbox.confirm("Are you sure want to delete this Dorm?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Dorm Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#dorm-tbl").DataTable().ajax.reload();

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
function deleteFeeCategory(){
    $('#fee-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteFeeCategory/' + data;
        bootbox.confirm("Are you sure want to delete this Category?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Category Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#fee-tbl").DataTable().ajax.reload();

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
function deleteReligion(){
    $('#religion-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteReligion/' + data;
        bootbox.confirm("Are you sure want to delete this Religion?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Religion Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#religion-tbl").DataTable().ajax.reload();

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
function deleteTerm(){
    $('#term-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteTerm/' + data;
        bootbox.confirm("Are you sure want to delete this Term?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Term Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#term-tbl").DataTable().ajax.reload();

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
function deleteYear(){
    $('#year-tbl').on('click','.btn-delete',function () {
        var data = $(this).closest('tr').find('#deleteId').val();
        var url = 'deleteYear/' + data;
        bootbox.confirm("Are you sure want to delete this Year?", function (result) {
            if (result) {
                $.ajax({
                    type: 'GET',
                    url: url,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Year Deleted Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#year-tbl").DataTable().ajax.reload();

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
function editParent() {
    if(typeof parentCode!='undefined') {
        var url = 'parentDetails/' + parentCode;
        $.ajax({
            type: 'GET',
            url: url,
        }).done(function (s) {
            parentImage(s.parCode);
            $('#pare-id').val(s.parCode);
            $('#nameF').val(s.nameFather);
            $('#nameM').val(s.nameMother);
            $('#addressF').val(s.addressFather);
            $('#addressM').val(s.addressMother);
            $('#telF').val(s.telFather);
            $('#telM').val(s.telMother);
            $('#emailM').val(s.emailMother);
            $('#emailF').val(s.emailFather);
            $('#gurd').val(s.parGur);
            $('#id-par').val(s.idNo);
            $('#profCode').val(s.fatherProffession.profCode);
            $('#profName').val(s.fatherProffession.profName);
            selectFatherProffession();
            $('#proffCode').val(s.motherProffession.profCode);
            $('#proffName').val(s.motherProffession.profName);
            selectProffession();

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
function parentImage(){
    $("#avatar").fileinput({
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
        defaultPreviewContent: '<img src="'+getContextPath()+'/protected/parentPhoto/'+parentCode+'"  style="height:15em;width:200px">',
        layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
        allowedFileExtensions: ["jpg", "png", "gif"]
    });
}
function loadLocality() {
    $('#county').hide()
    $('#sub-county').hide()
    $('#location').hide()
    $('#sub-location').hide()
    $('#village').hide()

}
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname
        .indexOf("/", 2));
}
function docsClick() {
    $('#btn-add-docs').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#doc-id').val('');
        $("#docsModal").modal('show');

    })
}
function religionClick() {
    $('#btn-add-religion').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#rel-id').val('');
        $("#religionModal").modal('show');

    })
}
function yearClick() {
    $('#btn-add-year').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#year-id').val('');
        $("#yearModal").modal('show');

    })
}
function blockClick() {
    $('#btn-add-block').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#block-id').val('');
        $("#blockModal").modal('show');

    })
}
function sourceClick() {
    $('#btn-add-source').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#source-id').val('');
        $("#sourceModal").modal('show');

    })
}
function termClick() {
    $('#btn-add-term').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#term-id').val('');
        $("#termModal").modal('show');

    })
}
function dormClick() {
    $('#btn-add-dorm').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#dorm-id').val('');
        $("#dormModal").modal('show');

    })
}
function gradeClick() {
    $('#btn-grading-system').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#grade-id').val('');
        $('#grade-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
        $('#auto-increment').prop('checked',false);
        $("#gradeModal").modal('show');
    })
}
function feeClick() {
    $('#btn-add-fee').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#cat-id').val('');
        $("#feeModal").modal('show');

    })
}
function statusClick() {
    $('#btn-add-status').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#status-id').val('');
        $("#statusModal").modal('show');

    })
}

function classClick(){
    $('#btn-add-class').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#class-id').val('');
        $("#classModal").modal('show');

    })
}
function healthClick() {
    $('#btn-add-health').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#condition-id').val('');
        $("#healthModal").modal('show');

    })
}
function nationClick() {
    $('#btn-add-country').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#c-id').val('');
        $("#countryModal").modal('show');

    })
}
function countyClick() {
    $('#btn-add-county').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#co-id').val('');
        $("#countyModal").modal('show');

    })
}
function subCountyClick() {
    $('#btn-add-subcou').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#sub-id').val('');
        $("#subcountyModal").modal('show');

    })
}
function locationClick() {
    $('#btn-add-location').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#l-id').val('');
        $("#locationModal").modal('show');

    })
}
function subLocationClick() {
    $('#btn-add-subloc').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#sl-id').val('');
        $("#sublocationModal").modal('show');

    })
}
function villageClick() {
    $('#btn-add-vil').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#v-id').val('');
        $("#villageModal").modal('show');

    })
}
function ProffessionClick() {
    $('#btn-add-proffession').click(function () {
        $.fn.modal.Constructor.prototype.enforceFocus=function(){};
        $('#prof-id').val('');
        $("#proffessionModal").modal('show');

    })
}
function saveDocuments() {
    var $form= $("#docs-form");
    var validator = $form.validate();

    $('#docs-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveDocument',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Docs Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#docs-tbl").DataTable().ajax.reload();
                $('#docsModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#docsModal').modal('hide');

            });
        });
    $('#docsModal').on('hidden.bs.modal', function () {
        $('#docs-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveGrades() {
    var $form= $("#grade-form");
    var validator = $form.validate();

    $('#grade-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveGrades',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Grade Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#grading-tbl").DataTable().ajax.reload();
                $('#gradeModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });

            });
        });
    $('#gradeModal').on('hidden.bs.modal', function () {
        $('#grade-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}

function createGradesTbl() {
    var table=$('#grading-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getGradingSystem',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        dom:"t",
        columns: [
            { data: "gradeFrom"
            },
            { data: "gradeTo"
            },
            { data: "grade"
            },
            { data: "remarks"
            },
            { data: "department",
                render: function ( data, type, full, meta ) {
                    if (full.department !== null) {
                        return full.department.departmentName;
                    }
                    else {
                        "";
                    }
                }
            },
            { data: "applicableFor" },

        ]
    } );
    $.fn.dataTable.ext.errMode = 'none';

    $('#grading-tbl').on( 'error.dt', function ( e, settings, techNote, message ) {
        console.log( 'An error has been reported by DataTables: ', message );
    } ) ;

    $('#grading-tbl').on( 'click', 'tbody tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        $('#grade-id').val(aData[0].gradingCode)
        if(aData[0].department!=null) {
            $('#dept-code').val(aData[0].department.departmentCode);
            $('#dept-name').val(aData[0].department.departmentName);
            selectDept();
        }
        $('#from').val(aData[0].gradeFrom)
        $('#to').val(aData[0].gradeTo)
        $('#grade').val(aData[0].grade)
        $('#applicable-for').val(aData[0].applicableFor)
        $('#remarks').val(aData[0].remarks)
        if(aData[0].autoIncrement==="Y") {
            $('#auto-increment').attr('checked',true)
        }else{
            $('#auto-increment').attr('checked',false)
        }
        $('#gradeModal').modal('show');

    })
    return table;
}

function saveParents() {
    var $form= $("#parent-form");
    var validator = $form.validate();

    $('#parent-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }
                var data = new FormData( this );
                 data.append( 'file', $( '#avatar' )[0].files[0] );
                console.log(data);
                $.ajax({
                    type: 'POST',
                    url: 'parent',
                    data: data,
                    processData: false,
                    contentType: false,
                }).done(function (s) {
                    new PNotify({
                        title: 'Success',
                        text: 'Parent Added Successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                    $("#parents-tbl").DataTable().ajax.reload();
                    $('#parentsModal').modal('hide');

                }).fail(function (xhr, error) {
                    new PNotify({
                        title: 'Error',
                        text: xhr.responseText,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                    $('#parentsModal').modal('hide');

                });
        });
    $('#parentsModal').on('hidden.bs.modal', function () {
        $('#parent-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveProffession() {
    var $form= $("#proffession-form");
    var validator = $form.validate();

    $('#proffession-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveProffession',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Proffession Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#proffession-tbl").DataTable().ajax.reload();
                $('#proffessionModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#proffessionModal').modal('hide');

            });
        });
    $('#proffessionModal').on('hidden.bs.modal', function () {
        $('#proffession-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}

function saveSources() {
    var $form= $("#source-form");
    var validator = $form.validate();

    $('#source-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveSources',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Source Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#sources-tbl").DataTable().ajax.reload();
                $('#sourceModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#sourceModal').modal('hide');

            });
        });
    $('#sourceModal').on('hidden.bs.modal', function () {
        $('#source-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveHealth() {
    var $form= $("#health-form");
    var validator = $form.validate();

    $('#health-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveHealth',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Condition Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#health-tbl").DataTable().ajax.reload();
                $('#healthModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#healthModal').modal('hide');

            });
        });
    $('#healthModal').on('hidden.bs.modal', function () {
        $('#health-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveYear() {
    var $form= $("#year-form");
    var validator = $form.validate();

    $('#year-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveYear',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Year Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#year-tbl").DataTable().ajax.reload();
                $('#yearModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#yearModal').modal('hide');

            });
        });
    $('#yearModal').on('hidden.bs.modal', function () {
        $('#year-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveStatus() {
    var $form= $("#stud-status-form");
    var validator = $form.validate();

    $('#stud-status-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveStatus',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Status Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#status-tbl").DataTable().ajax.reload();
                $('#statusModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#statusModal').modal('hide');

            });
        });
    $('#statusModal').on('hidden.bs.modal', function () {
        $('#stud-status-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveBlock() {
    $('#btn-save-block').on('click',function () {
        var $currForm = $('#block-form');
        var currValidator = $currForm.validate();
        if(!$currForm.valid()){
            return;
        }
        var data = {};
        $currForm.serializeArray().map(function(x){data[x.name] = x.value;});
        var url = "saveBlock";
        var request = $.post(url, data );
        request.success(function(){
            new PNotify({
                title: 'Success',
                text: 'Block created/updated Successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            $('#block-tbl').DataTable().ajax.reload();
            currValidator.resetForm();
            $('#block-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
            $('#blockModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
            new PNotify({
                title: 'Error',
                text: jqXHR.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        });
        request.always(function(){
            //	$btn.button('reset');
        });
    })
}
function saveForms() {
    $('#btn-save-class').on('click',function () {
        var $currForm = $('#class-form');
        var currValidator = $currForm.validate();
        if(!$currForm.valid()){
            return;
        }
        var data = {};
        $currForm.serializeArray().map(function(x){data[x.name] = x.value;});
        var url = "saveClass";
        var request = $.post(url, data );
        request.success(function(){
            new PNotify({
                title: 'Success',
                text: 'Class created/updated Successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            $('#class-tbl').DataTable().ajax.reload();
            currValidator.resetForm();
            $('#class-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
            $('#classModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
            new PNotify({
                title: 'Error',
                text: jqXHR.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        });
        request.always(function(){
            //	$btn.button('reset');
        });
    })
    $('#classModal').on('hidden.bs.modal', function () {
        $('#class-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveTerm() {
    $('#btn-save-term').on('click',function () {
        var $currForm = $('#term-form');
        var currValidator = $currForm.validate();
        if(!$currForm.valid()){
            return;
        }
        var data = {};
        $currForm.serializeArray().map(function(x){data[x.name] = x.value;});
        var url = "saveTerm";
        var request = $.post(url, data );
        request.success(function(){
            new PNotify({
                title: 'Success',
                text: 'Term created/updated Successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            $('#term-tbl').DataTable().ajax.reload();
            currValidator.resetForm();
            $('#term-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
            $('#termModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
            new PNotify({
                title: 'Error',
                text: jqXHR.responseText,
                type: 'error',
                styling: 'bootstrap3'
            });
        });
        request.always(function(){
            //	$btn.button('reset');
        });
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
function saveDorm() {
    var $form= $("#dorm-form");
    var validator = $form.validate();

    $('#dorm-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveDorm',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'House Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#dorm-tbl").DataTable().ajax.reload();
                $('#dormModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#dormModal').modal('hide');

            });
        });
    $('#dormModal').on('hidden.bs.modal', function () {
        $('#dorm-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}

function saveReligion() {
    var $form= $("#religion-form");
    var validator = $form.validate();

    $('#religion-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveReligion',
                enctype: 'multipart/form-data',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Religion Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#religion-tbl").DataTable().ajax.reload();
                $('#religionModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#religionModal').modal('hide');

            });
        });
    $('#religionModal').on('hidden.bs.modal', function () {
        $('#religion-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveCountry() {
    var $form= $("#country-form");
    var validator = $form.validate();

    $('#country-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveCountry',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Country Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#country-tbl").DataTable().ajax.reload();
                $('#countryModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#countryModal').modal('hide');

            });
        });
    $('#countryModal').on('hidden.bs.modal', function () {
        $('#country-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveCounty() {
    var $form= $("#county-form");
    var validator = $form.validate();

    $('#county-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveCounty',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'County Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#county-tbl").DataTable().ajax.reload();
                $('#countyModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#countyModal').modal('hide');

            });
        });
    $('#countyModal').on('hidden.bs.modal', function () {
        $('#county-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveSubCounty() {
    var $form= $("#sub-county-form");
    var validator = $form.validate();

    $('#sub-county-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveSubCounty',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'SubCounty Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#sub-county-tbl").DataTable().ajax.reload();
                $('#subcountyModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#subcountyModal').modal('hide');

            });
        });
    $('#subcountyModal').on('hidden.bs.modal', function () {
        $('#sub-county-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveLocation() {
    var $form= $("#location-form");
    var validator = $form.validate();

    $('#location-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveLocation',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Location Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#location-tbl").DataTable().ajax.reload();
                $('#locationModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#locationModal').modal('hide');

            });
        });
    $('#locationModal').on('hidden.bs.modal', function () {
        $('#location-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveSubLocation() {
    var $form= $("#sub-location-form");
    var validator = $form.validate();

    $('#sub-location-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveSubLocation',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'SubLocation Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#sub-loc-tbl").DataTable().ajax.reload();
                $('#sublocationModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#sublocationModal').modal('hide');

            });
        });
    $('#sublocationModal').on('hidden.bs.modal', function () {
        $('#sub-location-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function saveVillage() {
    var $form= $("#village-form");
    var validator = $form.validate();

    $('#village-form')
        .submit( function( e ) {
            e.preventDefault();

            if (!$form.valid()) {
                return;
            }


            var data = new FormData( this );
            console.log(data);
            $.ajax({
                type: 'POST',
                url: 'saveVillage',
                data: data,
                processData: false,
                contentType: false,
            }).done(function (s) {
                new PNotify({
                    title: 'Success',
                    text: 'Village Added Successfully',
                    type: 'success',
                    styling: 'bootstrap3'
                });
                $("#village-tbl").DataTable().ajax.reload();
                $('#villageModal').modal('hide');

            }).fail(function (xhr, error) {
                new PNotify({
                    title: 'Error',
                    text: xhr.responseText,
                    type: 'error',
                    styling: 'bootstrap3'
                });
                $('#villageModal').modal('hide');

            });
        });
    $('#villageModal').on('hidden.bs.modal', function () {
        $('#village-form')[0].reset();
        jQuery('.select2-offscreen').select2('val', '');
    });
}
function selectProffession() {
    if($("#mother-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "mother-div",
            sort : 'profName',
            change: function(e, a, v){
            $('#proffCode').val(e.added.profCode);
            $('#proffName').val(e.added.profName);

            },
            formatResult : function(a)
            {
                return a.profName            },
            formatSelection : function(a)
            {
                return a.profName
            },
            initSelection: function (element, callback) {
                var code = $('#proffCode').val();
                var name = $('#proffName').val();
                var data = {profName:name,profCode:code};
                callback(data);
            },
            id: "profCode",
            placeholder:"Proffession",
        });
    }
}
function selectBlock() {
    if($("#block-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "block-div",
            sort : 'blockName',
            change: function(e, a, v){
            $('#block-id').val(e.added.blockCode);
            $('#block-name').val(e.added.blockName);

            },
            formatResult : function(a)
            {
                return a.blockName
            },
            formatSelection : function(a)
            {
                return a.blockName
            },
            initSelection: function (element, callback) {
                var code = $('#block-id').val();
                var name = $('#block-name').val();
                var data = {blockName:name,blockCode:code};
                callback(data);
            },
            id: "blockCode",
            placeholder:"Block",
        });
    }
}
function selectFatherProffession() {
    if($("#father-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "father-div",
            sort : 'profName',
            change: function(e, a, v){
                $('#profCode').val(e.added.profCode);
                $('#profName').val(e.added.profName);
            },
            formatResult : function(a)
            {
                return a.profName
            },
            formatSelection : function(a)
            {
                return a.profName
            },
            initSelection: function (element, callback) {
                var code = $('#profCode').val();
                var name = $('#profName').val();
                var data = {profName:name,profCode:code};
                callback(data);
            },
            id: "profCode",
            placeholder:"Proffession",
        });
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
                var data = {name:name,code:code};
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
                $('#location-desc').val(e.added.louCode)
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
function selectTeacher() {

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
            placeholder:"Class",
        });
    }}
function selectCounty() {
    if($("#county-div").filter("div").html() != undefined)
    {
        Select2Builder.initAjaxSelect2({
            containerId : "county-div",
            sort : 'countyName',
            change: function(e, a, v){
             $('#county-id').val(e.added.countyCode)
                $('#count-desc').val(e.added.countyName)

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
                var name = $('#count-desc').val();
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
                countryCode(e.added.couCode);
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
function contryTable() {
    var table=$('#country-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getCountryTable',
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "couName" },
            { data: "couDesc" },
            { data: "couContinent" },
            { data: "couCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.couCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "couCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.couCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }},
        ]
    } );
    var table = $('#country-tbl').DataTable();
    $('#country-tbl tbody').on( 'click', 'tr', function () {
        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        if(aData) {
            countyTable(aData[0].couCode);
            $('#country-id').val(aData[0].couCode)
        }
    } )

    return table;
}
function countyTable(country) {
console.log(country);
    var table=$('#county-tbl').DataTable({
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url: 'getCountyTable',
            data:{
                country:country
            },

        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "countyName" },
            { data: "countyDesc" },
            { data: "country" ,
                render: function ( data, type, full, meta ) {
                    return full.country.couName;

                }
            },
            { data: "countyCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.countyCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "countyCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.countyCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }},

        ]

    } );
    $('#county').show();
    var table = $('#county-tbl').DataTable();

    $('#county-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        console.log(aData);
        if(aData) {
            getSubTab(aData[0].countyCode);
            $('#county-id').val(aData[0].countyCode)
        }
    } )
    return table;

}

function getSubTab(county) {

    var table=$('#sub-county-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'getSubCountyTable/'+county,
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "subName" },
            { data: "subDesc" },
            { data: "county" ,
                render: function ( data, type, full, meta ) {
                if(full.county!==null) {
                    return full.county.countyName;
                }
                else{
                    return "Not Set";
                }

                }
            },
            { data: "subCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.subCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "subCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.subCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
                },
        ]
    } );
    $('#sub-county').show();
    var table = $('#sub-county-tbl').DataTable();

    $('#sub-county-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        console.log(aData);
        if(aData) {
            getLocTab(aData[0].subCode);
            $('#sub-county-id').val(aData[0].subCode)
        }
    } )
    return table;
}
function getLocTab(id) {

    var table=$('#location-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'getLocationTable/'+id,
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "louName" },
            { data: "louDesc" },
            { data: "subCounty" ,
                render: function ( data, type, full, meta ) {
                    if(full.subCounty!==null) {
                        return full.subCounty.subName;
                    }
                    else{
                        return "Not Set";
                    }

                }
            },
            { data: "louCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.louCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "louCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.louCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    $('#location').show();
    var table = $('#location-tbl').DataTable();


    $('#location-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        console.log(aData);
        if(aData) {
            getSubLocTab(aData[0].louCode);
            $('#loc-id').val(aData[0].louCode)
        }
    } )
    return table;
}
function getSubLocTab(louCode) {
    var table=$('#sub-loc-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'getSubLocationTable/'+louCode,
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "subLouName" },
            { data: "subLouDesc" },
            { data: "location" ,
                render: function ( data, type, full, meta ) {
                    if(full.location!==null) {
                        return full.location.louName;
                    }
                    else{
                        return "Not Set";
                    }

                }
            },
            { data: "subLouCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.subLouCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "subLouCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden"  id="deleteId"name="id" value='+full.subLouCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    $('#sub-location').show();
    var table = $('#sub-loc-tbl').DataTable();


    $('#sub-loc-tbl tbody').on( 'click', 'tr', function () {

        $(this).addClass('active').siblings().removeClass('active');
        var aData = table.rows('.active').data();
        console.log(aData);
        if(aData) {
            getVilTab(aData[0].subLouCode);
            $('#sub-loc-id').val(aData[0].subLouCode)
        }
    } )
    return table;
}
function regKmSearch() {
    $('#search-id').on('keyup', function () {
        $('#class-tbl').DataTable()
            .columns(1)
            .search(this.value)
            .draw();
    });
}

function regPrSearch() {
    $('#search-id').on('keyup', function () {
        $('#parents-tbl').DataTable()
            .columns(0)
            .search(this.value)
            .draw();
    });
}
function regGradeSearch() {
    $('#search-id').on('keyup', function () {
        $('#grading-tbl').DataTable()
            .columns(2)
            .search(this.value)
            .draw();
    });
}
function getVilTab(subloc) {
    var table=$('#village-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'getVillageTable/'+subloc,
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "vilName" },
            { data: "vilDesc" },
            { data: "subLocation" ,
                render: function ( data, type, full, meta ) {
                    if(full.subLocation!==null) {
                        return full.subLocation.subLouName;
                    }
                    else{
                        return "Not Set";
                    }

                }
            },
            { data: "vilCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.vilCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "vilCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.vilCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    $('#village').show();
    return table;
}
function getHealthTable(){
    var table=$('#health-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: 'getHealthTable',

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "condName" },
            { data: "condDesc" },
            { data: "condComments"},
            { data: "condCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.condCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "condCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.condCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getSourcesTable(){
    var table=$('#sources-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getSourcesTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "sourceName" },
            { data: "sourceDesc" },
            { data: "sourceCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.sourceCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "sourceCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.sourceCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getDocumentsTable(){
    var table=$('#docs-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getDocumentsTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "docName" },
            { data: "docDesc" },
            { data: "docRequired",
                render: function ( data, type, full, meta ) {
                if(full.docRequired==="on") {
                    return "True"
                }
                else{
                    return "False"
                }
                }

            },
            { data: "docCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.docCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "docCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.docCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getClassTable() {

    var table=$('#class-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getClassTable',

        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "classNo" },
            { data: "classDesc" },
            { data: "block" ,
                render: function ( data, type, full, meta ) {
                return full.block.blockName;
                }
            },
            { data: "teachers" ,
                render: function ( data, type, full, meta ) {
                    return full.teachers.titles.titleName+". "+full.teachers.name;
                }
            },
            {data:"classCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.classCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "classCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.classCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}

function getMyClassTable() {
    var search=$('#search-id').val();
    var table=$('#class-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        ajax: {
            url:'getMyClassTable',
            data:{
                'search':search
            }
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "classNo" },
            { data: "classDesc" },
            { data: "block" ,
                render: function ( data, type, full, meta ) {
                    return full.block.blockName;
                }
            },
            { data: "teachers" ,
                render: function ( data, type, full, meta ) {
                    return full.teachers.titles.titleName+". "+full.teachers.name;
                }
            },
            {data:"classCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.classCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "classCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.classCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}

function getTermTable() {
    var table=$('#term-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getTermTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "termNumber" },
            { data: "termName" },
            {data:"termCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.termCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "termCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.termCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getBlockTable() {
    var table=$('#block-tbl').DataTable( {
        processing: true,
        serverSide: true,
        ajax: {
            url:'getBlockTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "blockName" },
            { data: "blockDesc" },

            {data:"blockCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.blockCode+'></form><button id="block-edit" class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "blockCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.blockCode+'></form><button id="block-delete" class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getParentsTable() {
    var table=$('#parents-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:"t",
        scrollX:true,
        ajax: {
            url:'getParentsTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "nameFather" },
            { data: "addressFather" },
            { data: "telFather" },
            { data: "fatherProffession" ,
                render: function ( data, type, full, meta ) {
                    return full.fatherProffession.profName;

                }
            },

            { data: "emailFather" },
            { data: "parGur" },
            { data: "nameMother" },
            { data: "addressMother" },
            { data: "telMother" },
            { data: "emailMother" },
            { data: "parCode" ,
                render: function ( data, type, full, meta ) {
                    return "View on Edit";

                }
            },
            { data: "emailRequired" },
            { data: "idNo"},
            {data:"parCode",
                render: function ( data, type, full, meta ) {
                    return '<form action="'+getContextPath()+'/protected/editParentForm" method="post"><input type="hidden" name="id" value='+full.parCode+'><input type="submit"  class="btn btn-success btn btn-info btn-xs" value="Edit" ></form>';
                }

            },
            { data: "parCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="parentId" name="id" value='+full.parCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}

function getProffessionTable(){
    var table=$('#proffession-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getProffessionTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,

        columns: [
            { data: "profName" },
            { data: "profDesc" },
            {data:"profCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.profCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "profCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" name="id" id="deleteId" value='+full.profCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getFeeTable(){
    var table=$('#fee-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getFeeTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "catName" },
            { data: "defaultCat",
                render: function ( data, type, full, meta ) {
                    if (full.defaultCat == "on") {

                    return "True"
                }
                    else{
                        return "False"
                    }
                }
            },
            { data: "catDesc" },
            { data: "catSuccessor" },
            { data: "catCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.catCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "catCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.catCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getDormTable() {
    var table=$('#dorm-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getHouseTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "dormName" },
            { data: "dormDesc" },
            { data: "capacity" },
            { data: "dormCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.dormCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "dormCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.dormCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getYearTable() {
    var table=$('#year-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getYearTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "yearName" },
            { data: "yearNumber" },
            { data: "yearCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.yearCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "yearCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.yearCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getStatusTable() {
    var table=$('#status-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getStatusTable',
            cache:false
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "statusName" },
            { data: "statusDesc" },
            { data: "statusCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.statusCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "statusCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.statusCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
function getReligionTable(){
    var table=$('#religion-tbl').DataTable( {
        processing: true,
        serverSide: true,

        ajax: {
            url:'getReligionTable',
            cache:false
        },

        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 10,
        destroy: true,
        columns: [
            { data: "relName" },
            { data: "relDesc" },
            { data: "relDay" },

            { data: "relCode",
                render: function ( data, type, full, meta ) {
                    return '<form id="editForm" method="post" enctype="multipart/form-data"><input type="hidden" id="editId" name="id" value='+full.relCode+'></form><button class="btn btn-success btn-xs btn-edit" ><i class="fa fa-pencil-square-o"></button>';

                }

            },
            { data: "relCode",
                render: function ( data, type, full, meta ) {
                    return '<form method="post" enctype="multipart/form-data"><input type="hidden" id="deleteId" name="id" value='+full.relCode+'></form><button class="btn btn-danger btn-xs btn-delete" ><i class="fa fa-trash-o"></button>';
                }
            },
        ]
    } );
    return table;
}
$(document).ready(function () {
     createAssignedTbl()
    searchTeFc();
})

function createAssignedTbl() {
    var table=$('#teacher-subjects-tbl').DataTable( {
        processing: true,
        serverSide: true,
        dom:'t',
        ajax: {
            url:'findAllAssignedSubjects',
        },
        lengthMenu: [ [10, 15], [10, 15] ],
        pageLength: 36,
        destroy: true,

        columns: [
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.titles.titleName;
                }
            },
            { data: "teachers",
                render: function ( data, type, full, meta ) {
                    return full.teachers.name;
                }
            },
            { data: "forms",
                render: function ( data, type, full, meta ) {
                    return full.forms.abbr;
                }
            },

            { data: "subjects" ,
                render: function ( data, type, full, meta ) {
                    return full.subjects.name;
                }
            },
            { data: "subjects" ,
                render: function ( data, type, full, meta ) {
                    return full.subjects.timeTableName;
                }
            }

        ]
    } );

    return table;
}
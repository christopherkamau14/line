$(document).ready(function () {
    loadPage();
selectStudentCode();
})

function loadPage() {
$('#reports').hide();
}
function reloadRept(){
    $('#reports').show();
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
               $('#hid-stud-id').val(e.added.stId)
                reloadRept()
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
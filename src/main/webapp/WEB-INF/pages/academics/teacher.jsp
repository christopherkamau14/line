<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/staff.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<script>var tchCode = "${teacherId}"</script>
<div class="x_panel">
    <div class="x_title">
        Teacher Details
    </div>
    <form class="form-horizontal" id="teacher-form" style="margin-top: 2em">
        <input type="hidden" name="teacherCode" id="teacher-id">

        <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Title<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="title-code" name="titles"/>
                    <input type="hidden" id="title-name">
                    <div id="title-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTitle"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">WEF Date<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="startDate" id="start"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="name" id="teacher-name" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">WET Date<span class="required">*</span></label>
                <div class="col-md-6 col-xs-12">
                <div class='input-group date datepicker-input'>
                    <input type='text' class="form-control pull-right" name="endDate" id="end"/>
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </div>
                </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="houseId" class="control-label col-md-4">
                    Gender<span class="required">*</span></label>
                <div class="col-md-7 col-xs-12">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="id-male" name="gender" value="Male">
                        <label class="custom-control-label" for="id-male">Male</label>


                        <input type="radio" class="custom-control-input" id="id-female" name="gender" value="Female">
                        <label class="custom-control-label" for="id-female">Female</label>

                    </div>
            </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Address<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="address" id="address" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Responsibility<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="res-code" name="responsibility"/>
                    <input type="hidden" id="res-name">
                    <div id="res-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageResponsibility"/>" >

                    </div>
                </div>

            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Telephone<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="phoneNumber" id="phone-number" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">ID No.<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="idNo" id="id-no" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="email" id="email" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Tsc No/UPI<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="text" name="tscNo" id="tsc-no" class="form-control"
                           placeholder="" required>
                </div>

            </div>

            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Teacher Intials<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="text" id="intials" name="intials" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Status<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <select class="form-control" id="status" name="status">
                        <option>Select</option>
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </div>

            </div>

                <div class="col-md-offset-1 col-md-4 col-xs-12 form-required">
                    <label for="avatar" class="control-label col-md-1" id="client-photo">
                        Photo</label>
                        <div class="kv-avatar center-block" style="height:15em;width: 200px">
                            <input name="file" type="file" id="avatar" class="file-loading">

                        </div>
                </div>
        </div>






    </form>
        <div class="row" style="margin-top: 5em" >
            <div class="col-md-offset-4 col-md-6 col-xs-12">
                <div class="col-md-4">

                    <input type="checkbox" name="assign" class="form-check-input" id="subjects">

                <label for="subjects" class="control-label">Assign Subjects<span class="required">*</span></label>
                </div>
                <div class="col-md-4 col-xs-12" id="muller1">
                    <div class="col-md-10 col-xs-12">
                        <input type="hidden" id="class" name="forms"/>
                        <input type="hidden" id="dm">
                        <div id="class-div" class="form-control"
                             select2-url="<c:url value="/protected/pageClass"/>" >
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-xs-12">
                    <button type="button" class="btn btn-default" id="un-assign-subjects">Un-Assign All</button>
                </div>
                <div class="col-md-1 col-xs-12">
                    <button class="btn btn-default" id="transfer-subjects">Transfer Subjects</button>
                </div>


            </div>
        </div>
    <div class="row" style="margin-top: 2em" id="muller">
        <div class=" col-md-offset-4 col-md-2">
            <table id="unassigned-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Subjects</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-md-1 text-center">
            <p></p>
            <br>
            <p></p>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-assign-all">&gt;&gt;</button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-assign-subject">></button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-unassign-subject"><</button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-unassign-all">&lt;&lt;</button>

        </div>

        <div class="col-md-2">
            <table id="assigned-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Subjects</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
            <div class="row" style="margin-top: 3em">
                <div class="col-md-offset-5 col-md-6 col-xs-12">
                <div class="col-md-4 col-xs-12">
                    <a href="${pageContext.request.contextPath}/protected/staff/teacherleaveouts"  class="btn btn-default" id="leaves">Leave-Out</a>
                </div>
                <div class="col-md-4 col-xs-12">
                    <button type="button" class="btn btn-default" id="grid">Assign Subjects</button>
                </div>

                    <div class="col-md-4 col-xs-12">
                        <a href="<c:url value="/protected/academics/assignedTeacherSubjects"/>" class="btn btn-default" id="view">View Subjects Assigned</a>
                    </div>
                </div>
            </div>

    <div class="row" style="margin-bottom: 1em;margin-top: 1em">
        <div class="col-md-2 pull-right">

            <input type="text" name="name" id="search-id" class="form-control pull-right"
                   placeholder="Search">
        </div>
    </div>
    <div style="margin-top: 2em">
        <table id="teachers-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Courtesy</td>
                <td>Name</td>
                <td>Address</td>
                <td>TelNo</td>
                <td>Duty</td>
                <td>Staff No</td>
                <td>Adm Date</td>
                <td>Gender</td>
                <td>Email</td>
                <td>Teacher</td>
                <td></td>
                <td></td>

            </tr>
            </thead>
        </table>
    </div>

<form id="subjects-form">
    <input type="hidden" name="subjectCode" id="sub-code">
</form>
    <div class="modal fade" id="teachModal" tabindex="-1" role="dialog"
         aria-labelledby="teachModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="teachModalLabel">
                        Transfer Subjects
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="transfer-form">
                        <input type="hidden" name="teacherCode" id="teach-code">
                        <div class="row" style="margin-top: 1em">

                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Transfer From<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <input type="text" name="name" id="teach-name" class="form-control"
                                           placeholder="" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Transfer To<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <input type="hidden" id="tea-id" name="teachers"/>
                                    <input type="hidden" id="tea-name">
                                    <div id="teach-div" class="form-control"
                                         select2-url="<c:url value="/protected/academics/pageTeacher"/>" >
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <input type="hidden" id="myclass" name="forms"/>
                                    <input type="hidden" id="mydm">
                                    <div id="my-class-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageClass"/>" >
                                    </div>
                                </div>

                            </div>
                        </div>
                    </form>


                    <div class="modal-footer">
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-2">
                                <button id="btn-save-transfers" class="btn btn-success pull-right" style="margin-right: 10px">Save</button>

                            </div>
                            <div class="col-md-2">

                                <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                                    Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
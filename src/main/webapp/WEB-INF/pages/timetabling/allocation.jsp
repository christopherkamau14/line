<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/22/2020
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/xlsx.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/FileSaver.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/timetabling/allocation.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Timetable Allocation
    </div>
    <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <button type="button" id="btn-save-allocation" class="btn btn-success pull-right" style="margin-right: 10px">Save</button>
            </div>
            <div class="col-md-1">
                <button type="button" id="btn-alloc-del" class="btn btn-danger pull-right" style="margin-right: 10px">Delete</button>
            </div>
        <div class="col-md-1">
            <button type="button" id="btn-alloc-classtt" class="btn btn-dark pull-right" style="margin-right: 10px"><i class="fa fa-calendar-times-o"></i></button>
        </div>
        <div class="col-md-1">
            <button type="button" id="btn-alloc-teachertt" class="btn btn-dark pull-right" style="margin-right: 10px"><i class="fa fa-institution"></i></button>
        </div>
        </div>
    <form class="form-horizontal" id="allocation-form" style="margin-top: 2em">
<input type="hidden" name="allocationCode" id="alloc-id">
    <div class="row" style="margin-top: 2em">

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Day<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="day-code" name="days"/>
                    <input type="hidden" id="day-name">
                    <div id="day-div" class="form-control"
                         select2-url="<c:url value="/protected/timetable/pageDays"/>" >

                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Term<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="term-code" name="term"/>
                    <input type="hidden" id="term-name">
                    <div id="term-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageTerm"/>" >

                    </div>
                </div>
            </div>

        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="class" name="forms"/>
                    <input type="hidden" id="dm">
                    <div id="class-div" class="form-control"
                         select2-url="<c:url value="/protected/pageClass"/>" >

                    </div>
                </div>

            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Subject<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="subjects-id" name="subjects"/>
                    <input type="hidden" id="subject-name">
                    <div id="subject-div" class="form-control"
                         select2-url="<c:url value="/protected/timetable/pageSubject"/>" >

                    </div>
                </div>
            </div>

        </div>
        <div class="row" style="margin-top: 2em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Lesson<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="lesson-id" name="lessons"/>
                    <input type="hidden" id="lesson-name">
                    <div id="lessons-div" class="form-control"
                         select2-url="<c:url value="/protected/timetable/pageLessons"/>" >
                    </div>
                </div>

            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" name="teachers" id="tea-id">

                    <input type="text" name="teacher" class="form-control" id="teacher">

                </div>

            </div>

        </div>
        <div class="row" style="margin-top: 1em">


            <div class="col-md-offset-4 col-md-3 col-xs-12">
                <label for="show-tt" class="control-label">Show Timetable<span class="required">*</span></label>

                <input type="checkbox" name="showTT" class="form-check-input" id="show-tt">

            </div>

        </div>

    </form>

    <div class="row" style="margin-top: 2em">
        <div class="col-md-2 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="text" name="className" class="form-control" id="class-id">

            </div>

        </div>
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Subject<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="text" name="subject" class="form-control" id="subject-id">

            </div>

        </div>
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Day<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="text" name="day" class="form-control" id="day-id">

            </div>

        </div>
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="text" name="teacher" class="form-control" id="teacher-id" >
            </div>

        </div>
        <div class="col-md-1 col-xs-12">
            <button type="button" class="btn btn-default" id="search-id">Search</button>

        </div>
    </div>
    <div style="margin-top: 1em">

        <div class="row">
            <table id="allocation-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>TA_Code</td>
                    <td>TA_Lsn_Name</td>
                    <td>TA_Staff_No</td>
                    <td>TA_Subject_Name</td>
                    <td>TA_TT_Day_Desc</td>
                    <td>TA_Class_Name</td>
                    <td>TA_Term</td>
                    <td>TA_Double</td>
                    <td>TA_Room</td>
                    <td>TA_Joined_Class</td>
                    <td>Courtesy</td>
                    <td>Name</td>
                    <td>Address</td>
                    <td>TelNo</td>
                    <td>Duty</td>
                    <td>StaffNo</td>
                    <td>DateOfAdm</td>
                    <td>Gender</td>
                    <td>Email</td>
                    <td>Teacher_No</td>
                    <td>Status</td>
                    <td>Intials</td>
                    <td>wet</td>
                    <td>ID_NO</td>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<div>
    <select id="mySelect"></select>
</div>
<div class="modal fade" id="classTableModal" tabindex="-1" role="dialog"
     aria-labelledby="classTableModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="classTableModalLabel">
                   Class Timetable
                </h4>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/protected/reports/classtt"/>" class="form-horizontal" id="class-form">


                    <div class="row" style="margin-top: 1em">

                        <div class="col-md-6 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="form-id" name="forms"/>
                                <input type="hidden" id="form-name">
                                <div id="form-div" class="form-control"
                                     select2-url="<c:url value="/protected/pageClass"/>" >

                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-offset-2 col-md-4">
                            <input type="submit" id="btn-save-dorm" class="btn btn-info pull-right" style="margin-right: 10px" value="Timetable">

                        </div>

                    </div>
                </form>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="teacherTableModal" tabindex="-1" role="dialog"
     aria-labelledby="teacherTableModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="teacherTableModalLabel">
                    Teacher Timetable
                </h4>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/protected/reports/teachertt"/>" class="form-horizontal" id="t-form">


                    <div class="row" style="margin-top: 1em">

                        <div class="col-md-6 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="teacher-code" name="teachers"/>
                                <input type="hidden" id="teacher-name">
                                <div id="teacher-div" class="form-control"
                                     select2-url="<c:url value="/protected/academics/pageTeacher"/>" >

                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-offset-2 col-md-4">
                            <input type="submit" id="btn-save-teacher" class="btn btn-info pull-right" style="margin-right: 10px" value="Timetable">

                        </div>

                    </div>
                </form>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</div>

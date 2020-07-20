<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/6/2020
  Time: 9:16 PM
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
<script type="text/javascript" src="<c:url value="/js/modules/exams/record.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Exam Recording
    </div>
    <form class="form-horizontal" id="exam-rec-form" style="margin-top: 2em">
        <input type="hidden" name="examRecordCode" id="exam-rec-id">

        <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">
            </div>
            <div class="col-md-1">
                <button type="button" id="btn-exam-del" class="btn btn-danger pull-right" style="margin-right: 10px">Delete</button>
            </div>
        </div>
        <div class="row" style="margin-top: 2em">
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

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Year<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="year-code" name="year"/>
                    <input type="hidden" id="year-name">
                    <div id="year-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageYear"/>" >

                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <button type="button" class="btn btn-default" id="convert">Convert%</button>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="reg-code" name="examRegister"/>
                    <input type="hidden" id="reg-name">
                    <div id="reg-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Exam Date<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="examDate" id="exam-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <button type="button" class="btn btn-default" id="export">Export template</button>
            </div>
        </div>
        <div class="row" style="margin-top: 2em">
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
                <label for="noOfUnits" class="control-label col-md-4">Remarks<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <textarea name="remarks" id="remarks" class="form-control"></textarea>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label class="btn btn-default btn-file" id="prem">
                    Import Template <input type="file" id="import" style="display: none;">
                </label>            </div>
        </div>
        <div class="row" style="margin-top: 2em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="tea-id" name="teachers"/>
                    <input type="hidden" id="tea-name">
                    <div id="teach-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTeacher"/>" >
                    </div>
                </div>

            </div>
            <div class="col-md-4 col-xs-12">

                </div>
            <div class="col-md-4 col-xs-12">
                <button type="button" class="btn btn-default" id="reports">Email Reports</button>
            </div>
            </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Subject<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="subject-id" name="teacherSubjects"/>
                    <input type="hidden" id="subject-name">
                    <div id="subject-div" class="form-control"
                         select2-url="" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="stud-id" name="student"/>
                    <input type="hidden" id="stud-name">
                    <div id="std-div" class="form-control"
                         select2-url="" >

                    </div>
                </div>
            </div>
            <div class="col-md-2 col-xs-12">
                <button type="button" class="btn btn-default" id="miss">Miss Exam</button>
            </div>
            <div class="col-md-2 col-xs-12">
                <button type="button" class="btn btn-default" id="undo-miss">Undo Miss Exam</button>
            </div>
        </div>

        <div class="row" style="margin-top: 2em">
            <div class="col-md-2 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Marks<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="text" name="mark" class="form-control" id="marks">

                </div>

            </div>

            <div class="col-md-2 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Out of<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="text" name="outOff" class="form-control" id="out-off">

                </div>

            </div>
            <div class="col-md-2 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Grade<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="text" name="grade" class="form-control" id="grade">

                </div>

            </div>
            <div class="col-md-4 col-xs-12 form-required">
                <div class="kv-avatar center-block" style="height:15em;width: 200px">
                    <input name="file" type="file" id="avatar" class="file-loading">

                </div>
            </div>
            <button type="button" class="btn btn-default pull-right" id="ungraded">View Reason Ungraded</button>

        </div>

    </form>
<div class="row" style="margin-top: 2em">

</div>
    <div style="margin-top: 3em">

        <div class="row">
        <table id="exams-rec-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Serial No.</td>
                <td>Student No</td>
                <td>Names</td>
                <td>Position</td>
                <td>Subject</td>
                <td>Year</td>
                <td>Term</td>
                <td>Marks</td>
                <td>Out_Off</td>
                <td>Position_Out_Of</td>
            </tr>
            </thead>
        </table>
    </div>


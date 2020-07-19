<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/18/2020
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/exams/exam.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Exam Registration
    </div>
    <form class="form-horizontal" id="exam-reg-form" style="margin-top: 2em">
        <input type="hidden" name="examCode" id="exam-id">

        <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">
            </div>
            <div class="col-md-1">
                <button type="button" id="btn-exam-del" class="btn btn-danger pull-right" style="margin-right: 10px">Delete</button>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Type<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="type-code" name="examType"/>
                    <input type="hidden" id="type-name">
                    <div id="type-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageType"/>" >

                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/protected/exams/examtype" class="btn btn-info btn-xs">New</a>
            </div>

                    <div class="col-md-6 col-xs-12">
                        <label for="noOfUnits" class="control-label col-md-4">Year<span class="required">*</span></label>

                        <div class="col-md-6 col-xs-12">
                            <input type="hidden" id="year-code" name="year"/>
                            <input type="hidden" id="year-name">
                            <div id="year-div" class="form-control"
                                 select2-url="<c:url value="/protected/exams/pageYear"/>" >

                            </div>
                        </div>
                </div>
            </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Month<span class="required">*</span></label>
                <div class="col-md-6 col-xs-12">
                <select class="form-control" id="month" name="month">
                    <option value="JAN">Jan</option>
                    <option value="FEB">Feb</option>
                    <option value="MAR">Mar</option>
                    <option value="APR">Apr</option>
                    <option value="MAY">May</option>
                    <option value="JUN">Jun</option>
                    <option value="JUL">Jul</option>
                    <option value="AUG">Aug</option>
                    <option value="SEP">Sep</option>
                    <option value="OCT">Oct</option>
                    <option value="NOV">Nov</option>
                    <option value="DEC">Dec</option>
                </select>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Exam Name<span class="required">*</span></label>
                <div class="col-md-6 col-xs-12">
                        <input type="text" class="form-control pull-right" name="examName" id="exam-name"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Term<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="term-code" name="term"/>
                    <input type="hidden" id="term-name">
                    <div id="term-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageTerm"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Display Name<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="displayName" id="disp-name" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Grading<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <select class="form-control" id="grading" name="grading">
                        <option value="DEFAULT">DEFAULT</option>
                    </select>
                </div>

            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Effective Date<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="effectiveDate" id="start-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Final Exam<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="checkbox" name="finalExam" class="form-check-input" id="final-exam">

                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Lock Date<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="lockDate" id="lock-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Combined Exam<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="checkbox" name="combinedExam" class="form-check-input" id="combined-exam">

                </div>

            </div>
        </div>

    </form>



    <div style="margin-top: 5em">
        <div class="row" style="margin-bottom: 1em">
            <div class="col-md-2 pull-right">

                <input type="text" name="name" id="search-id" class="form-control pull-right"
                       placeholder="Search">
            </div>
        </div>
        <table id="exams-reg-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Exam No.</td>
                <td>Exam Name</td>
                <td>Year</td>
                <td>Month</td>
                <td>Type</td>
                <td>Term</td>
                <td>Exam Final</td>
                <td>Exam Authorised</td>
                <td>Exam Merged</td>
                <td>Exam National</td>
                <td>Grading</td>
                <td>Class</td>
                <td>Exam Label</td>
                <td>WEF</td>
                <td>WET</td>

            </tr>
            </thead>
        </table>
    </div>

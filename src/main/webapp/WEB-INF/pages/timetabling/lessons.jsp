<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/21/2020
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/timetabling/lesson.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Lessons Setups
    </div>
    <button type="button" class="btn btn-default pull-left" id="btn-new-lesson">New</button>

    <button type="button" class="btn btn-success pull-left" id="btn-save-lesson">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-lesson">Delete</button>

    <div class="col-md-offset-1 col-md-10" style="margin-top: 1em">
        <form id="lessons-form" class="form-horizontal">
            <input type="hidden" name="lessonId" id="lesson-id">

            <div class="row" style="margin-top: 1em">
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Code<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Lesson<span class="required">*</span></label>
                </div>
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Start<span class="required">*</span></label>
                </div>
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">End<span class="required">*</span></label>
                </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-2 col-xs-12">
                        <input name="lessonCode" id="lesson-code" class="form-control"
                               placeholder="" readonly>
                </div>
                <div class="col-md-2 col-xs-12">
                    <input name="lessonName" id="lesson-name" class="form-control"
                           placeholder="" required>
                </div>
                <div class="col-md-3 col-xs-12">

                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="lessonStart" id="lesson-start"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>

                </div>
                <div class="col-md-3 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="lessonEnd" id="lesson-end"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
                    <div class="col-md-2 col-xs-12">
                        <input type="checkbox" name="autoLesson" class="form-check-input" id="lesson-auto">

                        <label for="lesson-auto" class="control-label">Auto<span class="required">*</span></label>
                    </div>
            </div>
        </form>

        <table id="lesson-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Code</td>
                <td>Lesson</td>
                <td>Start Time</td>
                <td>End Time</td>
                <td>Lesson Number</td>

            </thead>
        </table>
    </div>
</div>
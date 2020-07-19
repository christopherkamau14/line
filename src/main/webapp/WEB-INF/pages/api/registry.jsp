<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/19/2020
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/api.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        <h3> Student Details</h3>
    </div>
    <form class="form-horizontal" id="student-form" style="margin-top: 2em">

        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="name" id="name" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Reg No.<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="regNo" id="regno" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Points<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="points" id="points" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Honours<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="honours" id="honours" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Phone<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="phone" id="phone" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Completion Date<span class="required">*</span></label>
                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="completionDate" id="end"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="email" id="email" class="form-control"
                           placeholder="" required>
                </div>
            </div>



        </div>


        <div class="form-group" style="margin-top: 1em">
            <div class="col-md-offset-8 col-md-4 col-xs-12 form-required">

                <div class="col-md-7 col-xs-12">
                    <div class="kv-avatar center-block" style="height:15em;width: 200px">
                        <input name="file" type="file" id="stud-avatar" class="file-loading">

                    </div>
                </div>
            </div>
        </div>



        <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

            </div>
            <div class="col-md-1">

                <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                    Cancel
                </button>
            </div>
        </div>
    </form>
</div>

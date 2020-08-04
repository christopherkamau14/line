<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/21/2020
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/5/2020
  Time: 7:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/timetabling/days.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Days Setups
    </div>

<button type="button" class="btn btn-success pull-left" id="btn-save-day">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-day">Delete</button>

    <div class="col-md-offset-1 col-md-8" style="margin-top: 1em">
            <form id="days-form" class="form-horizontal">
                <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                    <input type="hidden" name="dayCode" id="day-id">
                    <div class="col-md-5 col-xs-12">
                        <label for="noOfUnits" class="control-label col-md-4">Code<span class="required">*</span></label>

                        <div class="col-md-8 col-xs-12">
                            <input name="code" id="code" class="form-control"
                                   placeholder="" required>
                        </div>
                    </div>
                        <div class="col-md-6 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Day<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="name" id="name" class="form-control"
                                       placeholder="" required>
                            </div>
                    </div>
                </div>
            </form>

            <table id="days-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>TT_Day_Code</td>
                    <td>TT_Day_Name</td>

                </thead>
            </table>
        </div>
    </div>



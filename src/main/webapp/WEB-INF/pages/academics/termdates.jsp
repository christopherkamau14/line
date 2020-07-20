<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/29/2020
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/28/2020
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/classsubjects.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Subjects Groups
    </div>

    <div id="country" style="margin-bottom: 1em">

            <button id="btn-term-dates" class="btn btn-info pull-right">New</button>


        <div style="margin-top: 1em">
            <table id="term-dates-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Term</td>
                    <td>Start Date</td>
                    <td>End Date</td>
                    <td>Current Term</td>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="termDateModal" tabindex="-1" role="dialog"
     aria-labelledby="termDateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="termDateModalLabel">
                    Add Term Dates
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="term-dates-form">
                    <input type="hidden" name="termDateCode" id="term-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Term<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="myterm" name="term"/>
                                <input type="hidden" id="mytm">
                                <div id="my-term-div" class="form-control"
                                     select2-url="<c:url value="/protected/pageTerm"/>" >
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Start Date<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <div class='input-group date datepicker-input'>
                                    <input type='text' class="form-control pull-right" name="startDate" id="start-date"/>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">End Date<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <div class='input-group date datepicker-input'>
                                    <input type='text' class="form-control pull-right" name="endDate" id="end-date"/>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Current Term<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="currentTerm" class="form-check-input" id="current-term">
                            </div>
                        </div>
                    </div>


                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-sub" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

                        </div>
                        <div class="col-md-2">

                            <button type="button" id="delete-term-dates" class="btn btn-danger pull-right" data-dismiss="modal">Delete<i class="fa fa-trash"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</div>



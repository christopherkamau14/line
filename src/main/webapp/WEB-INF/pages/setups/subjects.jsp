<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/17/2020
  Time: 8:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/academics.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Subjects Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-subject" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="subjects-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Name</td>
                    <td>Desc</td>
                    <td>Order</td>
                    <td>Department</td>
                    <td>Main</td>
                    <td>Combined</td>
                    <td>Pros</td>
                    <td>Timetable Name</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="subjectsModal" tabindex="-1" role="dialog"
     aria-labelledby="subjectsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="subjectsModalLabel">
                    Add Subjects Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="subject-form">
                    <input type="hidden" name="subjectCode" id="subject-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Order<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="order" id="order" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="name" id="subject-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Desc<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="subjectDesc" id="sub-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Department<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="dept-code" name="department"/>
                                <input type="hidden" id="dept-name">
                                <div id="dept-div" class="form-control"
                                     select2-url="<c:url value="/protected/academics/pageDept"/>" >

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Main Subject<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="mainSubject" class="form-check-input" id="main">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Combined Subject<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="combinedSubject" class="form-check-input" id="combined">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Pros<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="prosSubject" class="form-check-input" id="pros">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Multiplier<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="multiplier" id="mult" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Timetable Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="timeTableName" id="t-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-dorm" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

                        </div>
                        <div class="col-md-2">

                            <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                                Cancel
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


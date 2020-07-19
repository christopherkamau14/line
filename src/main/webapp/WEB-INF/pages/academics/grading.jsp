<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/27/2020
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Grading System Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <div class="row">
            <button id="btn-grading-system" class="btn btn-info pull-right">New</button>
        </div>
        <div class="row" style="margin-bottom: 1em;margin-top: 1em">
            <div class="col-md-2 pull-right">

                <input type="text" name="name" id="search-id" class="form-control pull-right"
                       placeholder="Search">
            </div>
        </div>
        <div style="margin-top: 1em">
            <table id="grading-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>From</td>
                    <td>To</td>
                    <td>Grade</td>
                    <td>Remarks</td>
                    <td>Department</td>
                    <td>Category</td>
                    <td>Points</td>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="gradeModal" tabindex="-1" role="dialog"
     aria-labelledby="gradeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gradeModalLabel">
                    Add Grade
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="grade-form">
                    <input type="hidden" name="gradingCode" id="grade-id">

                    <div class="row" style="margin-top: 1em">

                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">From<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <input name="gradeFrom" id="from" class="form-control"
                                           placeholder="" required>
                                </div>
                            </div>

                    </div>
                    <div class="row" style="margin-top: 1em">

                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">To<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <input name="gradeTo" id="to" class="form-control"
                                           placeholder="" required>
                                </div>
                            </div>

                        </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Grade<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <select class="form-control" id="grade" name="grade">
                                    <option>Select</option>
                                    <option value="A">A</option>
                                    <option value="A-">A-</option>
                                    <option value="B+">B+</option>
                                    <option value="B">B</option>
                                    <option value="B-">B-</option>
                                    <option value="C+">C+</option>
                                    <option value="C">C</option>
                                    <option value="C-">C-</option>
                                    <option value="D+">D+</option>
                                    <option value="D">D</option>
                                    <option value="D-">D-</option>
                                    <option value="E">E</option>
                                </select>
                            </div>

                        </div>

                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Auto-Increment onUpdate<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="autoIncrement" class="form-check-input" id="auto-increment">
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
                            <label for="noOfUnits" class="control-label col-md-4">Applicable For<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <select class="form-control" id="applicable-for" name="applicableFor">
                                    <option>Select</option>
                                    <option value="ALL">ALL</option>
                                </select>
                            </div>

                        </div>

                    </div>
                    <div class="row" style="margin-top: 1em">

                            <div class="col-md-10 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Remarks<span class="required">*</span></label>

                                <div class="col-md-8 col-xs-12">
                                    <textarea name="remarks" id="remarks" class="form-control"></textarea>

                                </div>
                            </div>

                    </div>


                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-sub" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

                        </div>
                        <div class="col-md-2">

                            <button type="button" id="delete-grade" class="btn btn-danger pull-right" data-dismiss="modal">Delete<i class="fa fa-trash"></i>
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

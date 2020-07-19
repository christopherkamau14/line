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
<script type="text/javascript" src="<c:url value="/js/modules/staff/classsubjects.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Class Subjects Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-class-subjects" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="class-subjects-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Subject Name</td>
                    <td>Class</td>
                    <td>Lessons Per Week</td>
                    <td>Double Lessons</td>
                    <td>Stroked With</td>
                    <td>Stroked Subject</td>

                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="classsubjectsModal" tabindex="-1" role="dialog"
     aria-labelledby="classsubjectsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="classsubjectsModalLabel">
                    Add Class Subjects
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="class-subject-form">
                    <input type="hidden" name="classSubjectCode" id="class-subject-id">

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

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Subjects<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="mysubject" name="subjects"/>
                                <input type="hidden" id="mysm">
                                <div id="my-subject-div" class="form-control"
                                     select2-url="<c:url value="/protected/academics/pageSubjects"/>" >
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Weekly Lessons<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="weekLessons" id="week-lessons" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Double Lessons<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="doubleLessons" id="double-lessons" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Stroked Subject<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="strokedSubject" class="form-check-input" id="stroked">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-6 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Stroked With<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <select class="form-control" id="status" name="strokedWith">
                                    <option>Select</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                </select>
                            </div>

                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-sub" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

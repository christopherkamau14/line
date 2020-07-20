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
        <div class="row" style="margin-bottom: 1em">
            <button id="btn-subject-groups" class="btn btn-info pull-right">New</button>

        </div>
        <div class="row" style="margin-bottom: 1em">
            <div class="col-md-2 pull-right">

                <input type="text" name="name" id="search-id" class="form-control pull-right"
                       placeholder="Search">
            </div>
        </div>
        <div style="margin-top: 1em">
            <table id="subject-groups-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Subject Name</td>
                    <td>Subject Group Priority</td>
                    <td>Subject Group</td>


                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="groupModal" tabindex="-1" role="dialog"
     aria-labelledby="groupModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="groupModalLabel">
                    Add Subjects Group
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="group-form">
                    <input type="hidden" name="groupCode" id="group-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Subject<span class="required">*</span></label>

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
                            <label for="noOfUnits" class="control-label col-md-4">Subject Group<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="number" name="groupNumber" id="group-number" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>



                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Compulsory(F1&F2)<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="compulsorySubject" class="form-check-input" id="compulsory">
                            </div>
                        </div>
                    </div>


                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-sub" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

                        </div>
                        <div class="col-md-2">

                            <button type="button" id="delete-subject-group" class="btn btn-danger pull-right" data-dismiss="modal">Delete<i class="fa fa-trash"></i>
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


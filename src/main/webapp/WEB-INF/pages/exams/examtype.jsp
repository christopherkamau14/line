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
<script type="text/javascript" src="<c:url value="/js/modules/exams/exam.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Exam Type Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-exam-type" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="type-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Exam Type Name</td>
                    <td>Exam Type Desc</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="typeModal" tabindex="-1" role="dialog"
     aria-labelledby="typeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="typeModalLabel">
                    Add Exam Type Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="type-form">
                    <input type="hidden" id="type-id" name="typeCode">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Exam Type Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="typeName" id="type-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Exam Type Desc<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="typeDesc" id="type-abbr" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

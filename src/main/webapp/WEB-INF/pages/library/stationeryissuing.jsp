<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/17/2020
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/xlsx.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/FileSaver.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/library/statissue.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Stationery Issuing
    </div>
    <form class="form-horizontal" id="stat-issue-form" style="margin-top: 1em">
        <input type="hidden" name="statIssueCode" id="stat-issue-code">

        <div class="row" style="margin-top: 1em">
            <button type="button" class="btn btn-default pull-left" id="btn-new-stationery">New</button>

            <button type="button" class="btn btn-success pull-left" id="btn-save-stationery">Save</button>
            <button type="button" class="btn btn-danger pull-left" id="btn-delete-stationery">Delete</button>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Borrower Category<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <select class="form-control" id="borrower-cat" name="borrowerCat">
                        <option>Select</option>
                    <option value="S">Student</option>
                    <option value="T">Teacher</option>
                    <option value="W">Worker</option>


                </select>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Date<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="issueDate" id="issue-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="class" name="forms"/>
                    <input type="hidden" id="dm">
                    <div id="class-div" class="form-control"
                         select2-url="<c:url value="/protected/pageClass"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Store<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="store-code" name="stores"/>
                    <input type="hidden" id="store-name">
                    <div id="store-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageStore"/>" >

                    </div>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12" id="bo-div">
                <label for="noOfUnits" class="control-label col-md-4">Borrower Code<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="b-code" name="borrower"/>
                    <input type="hidden" id="b-name">
                    <div id="b-div" class="form-control"
                         select2-url="<c:url value=""/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-offset-1 col-md-4 col-xs-12" id="t-div">
                <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="tea-id" name="teachers"/>
                    <input type="hidden" id="tea-name">
                    <div id="teach-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTeacher"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-offset-1 col-md-4 col-xs-12" id="s-div">
                <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="stud-id" name="student"/>
                    <input type="hidden" id="stud-name">
                    <div id="std-div" class="form-control"
                         select2-url="" >

                    </div>
                </div>
            </div>
            <div class="col-md-offset-1 col-md-4 col-xs-12" id="w-div">
                <label for="noOfUnits" class="control-label col-md-4">Worker<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="worker-code" name="staff"/>
                    <input type="hidden" id="worker-name">
                    <div id="worker-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageWorker"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Units<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="units" id="units" class="form-control"
                           placeholder="" required>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Stationery<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="stationery-code" name="stationery"/>
                    <input type="hidden" id="stationery-name">
                    <div id="stationery-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageStationery"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Registered By<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" name="registeredBy" id="reg-by" class="form-control"
                           placeholder="">
                    <input type="text" name="user" id="user-name" class="form-control"
                           placeholder="" readonly>
                </div>
            </div>

        </div>
    </form>

    <div class="col-md-offset-1 col-md-10">

        <table id="stat-issue-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Stationery</td>
                <td>Date</td>
                <td>Units</td>
                <td>Issued By</td>
                <td>Issued To</td>
            </tr>
            </thead>
        </table>
    </div>
</div>

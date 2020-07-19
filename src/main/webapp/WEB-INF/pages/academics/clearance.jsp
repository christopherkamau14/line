<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/2/2020
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/clearance.js"/>"></script>
<div class="x_panel">
    <div class="x_title"><h4>Clearance</h4></div>
<form id="clearance-form">
    <input type="hidden" id="clear-code" name="clearanceCode">
    <div class="row" style="margin-bottom: 2em">
        <div class="col-md-6">
        <label for="noOfUnits" class="control-label col-md-2">Student<span class="required">*</span></label>

        <div class="col-md-7 col-xs-12">
            <input type="hidden" id="stud-id" name="student"/>
            <input type="hidden" id="stud-name" name="name">
            <div id="std-div" class="form-control"
                 select2-url="<c:url value="/protected/pageStudent"/>" >

            </div>
        </div>
        </div>
            <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">
        <button type="button" id="btn-delete-clearance" class="btn btn-danger pull-right" style="margin-right: 10px">Delete</button>

    </div>
<div class="row">
    <div class="col-md-offset-4">
        <label for="libclear" class="control-label  col-md-3">Named in Charge<span class="required">*</span></label>

    </div>
    <div class="col-md-offset-7">
        <label for="libclear" class="control-label col-md-2">Comments<span class="required">*</span></label>

    </div>
    <div class="col-md-offset-10">
        <label for="libclear" class="control-label col-md-2">Date<span class="required">*</span></label>

    </div>
</div>
    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="lib-clear">
            </div>

            <label for="lib-clear" class="control-label">Cleared in Library<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="libraryName" id="lib-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="libraryComment" id="lib-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="libraryDate" id="lib-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

</div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="lab-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared in Laboratory<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="laboratoryName" id="lab-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="laboratoryComment" id="lab-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="laboratoryDate" id="lab-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="hod-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared By H.O.D<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="hodName" id="hod-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="hodComment" id="hod-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="hodDate" id="hod-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>
    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="acct-clear">
            </div>

            <label for="lab-clear" class="control-label">Account Clearance<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="acctName" id="acct-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="acctComment" id="acct-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="acctDate" id="acct-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="exam-clear">
            </div>

            <label for="lab-clear" class="control-label">Exam Clearance<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="examName" id="exam-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="examComment" id="exam-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="examDate" id="exam-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>


    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="principal-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared By Principal<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="principalName" id="principal-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="principalComment" id="principal-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="principalDate" id="principal-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="deputy-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared By Deputy Principal<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="deputyName" id="deputy-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="deputyComment" id="deputy-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="deputyDate" id="deputy-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" class="form-check-input" id="cord-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared By Prog Cordinator<span class="required">*</span></label>

        </div>


        <div class="col-md-3">
            <input type='text' class="form-control" name="cordName" id="cord-name"/>
        </div>
        <div class="col-md-3">
            <input type='text' class="form-control" name="cordComment" id="cord-com"/>
        </div>
        <div class="col-md-3">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="cordDate" id="cord-date"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>        </div>

    </div>

    <div class="row">


        <div class="col-md-3">

            <div class="col-md-2 col-xs-12">
                <input type="checkbox" name="clearedGrad" class="form-check-input" id="grad-clear">
            </div>

            <label for="lab-clear" class="control-label">Cleared For Graduation<span class="required">*</span></label>

        </div>

    </div>
</form>
    <div class="row" style="margin-bottom: 1em;margin-top: 1em">
        <div class="col-md-2 pull-right">

            <input type="text" name="name" id="search-id" class="form-control pull-right"
                   placeholder="Search">
        </div>
    </div>

    <div style="margin-top: 2em">
        <table id="clearance-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Date</td>
                <td>Adm No.</td>
                <td>Name</td>
                <td>Class</td>
                <td>Passed Exam?</td>
                <td>Paid Fees?</td>
                <td>Returned Books?</td>
                <td>Cleared For Graduation?</td>
                <td>Cleared in Department?</td>
                <td>Cleared For Laboratory</td>
            </tr>
            </thead>
        </table>
    </div>

</div>

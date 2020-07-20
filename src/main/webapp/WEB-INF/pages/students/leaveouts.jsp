<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/13/2020
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/students/leaveouts.js"/>"></script>
<div class="x_panel">
    <div class="x_title"><h4>Leave Outs</h4></div>
    <form id="stud-leaves">
<div class="row">
<div class="col-md-4 col-xs-12">
    <label for="houseId" class="control-label col-md-3">
        Class<span class="required">*</span></label>
    <div class="col-md-9 col-xs-12">
        <input type="hidden" id="class" name="forms"/>
       <input type="hidden" id="dm">
    <div id="class-div" class="form-control"
         select2-url="<c:url value="/protected/pageClass"/>" >

    </div>
    </div>
    </div>
</div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-2">
            Students
        </div>
        <div class="col-md-2">
            <table id="students-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Adm No</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-md-1 text-center">
            <p></p>
            <br>
            <p></p>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-assign-batch-leaveout">&gt;&gt;</button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-assign-leaveout">></button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-remove-leaveout"><</button>
            <button type="button" class="btn btn-success btn btn-info pull-right" id="btn-remove-batch-leaveout">&lt;&lt;</button>

        </div>

        <div class="col-md-2">
            <table id="leaveouts-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>Adm No.</th>
                </tr>
                </thead>
            </table>
        </div>
        <div class="col-md-2">
            Leaves
        </div>
        <div class="col-md-4 col-xs-12">
            <div class="form-group">
                <label for="reg-wet" class="col-md-2 control-label" id="lbl-dl">Date Left<span class="required">*</span></label>

                <div class="col-md-9 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="dateLeft" id="dl"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-xs-12">

        </div>
        <div class="col-md-4 col-xs-12">
            <div class="form-group">
                <label for="reg-wet" class="col-md-2 control-label" id="lbl-dr">Return Date<span class="required">*</span></label>

                <div class="col-md-9 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="dateExpected" id="dtr"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">

        </div>
        <div class="col-md-4 col-xs-12 pull-right">
        <div class="form-group">

            <label for="reg-wet" class="col-md-2 control-label" id="lbl-reason">Reason<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <textarea name="reason" id="reason" class="form-control"></textarea>
            </div>
            </div>
    </div>

</div>
        <input type="hidden" id="valForSelect">
    </form>
    <div class="row" style="margin-top: 2em">
<div class="col-md-offset-6">
    <button type="button" id="print" class="btn btn-default">Print</button>
    <button type="button" id="clear" class="btn btn-default">Clear</button>
    <button type="button" id="leave" class="btn btn-default">Leave</button>
    <button type="button" id="return" class="btn btn-default">Return</button>
    <div class="col-md-5 col-xs-12 pull-right">
        <div class="form-group">

            <div class="col-md-10 col-xs-12">
                <div class='input-group date datepicker-input'>
                    <input type='text' class="form-control pull-right" name="dateReturn" id="dr"/>
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
    </div>

    <div class="row" style="margin-top: 2em">
        <div class="col-md-1">
            <button type="button" id="run" class="btn btn-default">Run</button>
            </div>

        <div class="col-md-2">
        <label class="custom-control-label" for="name">Search Students</label>

        <input type="radio" class="custom-control-input" id="name" name="budSet" value="name">
        </div>
        <div class="col-md-2">
            <input type="text" name="name" id="stds-id" class="form-control"
                   placeholder="" required>
        </div>
        <div class="col-md-2">
            <label class="custom-control-label" for="returned">Returned</label>

            <input type="radio" class="custom-control-input" id="returned" name="budSet" value="returned">
        </div>
        <div class="col-md-2">
            <label class="custom-control-label" for="pending">Pending</label>

            <input type="radio" class="custom-control-input" id="pending" name="budSet" value="pending">
        </div>
        <div class="col-md-1">
            <button type="button" class="btn btn-success" id="excel"><i class="fa fa-file-excel-o" ></i></button>
        </div>
    </div>
    <div style="margin-top: 2em">
        <table id="students-leave-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Class</td>
                <td>Adm No</td>
                <td>Reason</td>
                <td>Date Left</td>
                <td>Date Expected</td>
                <td>Return Date</td>
                <td>Authorised By</td>
                <td>Returned</td>
                <td>Name</td>

            </tr>
            </thead>
        </table>
    </div>
</div>
<form id="leaves-form">
    <input type="hidden" name="admNo" id="adm-id">
</form>
<form id="return-form">
    <input type="hidden" name="temp" id="rt-id">
</form>
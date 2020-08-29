<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/14/2020
  Time: 3:56 PM
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
<script type="text/javascript" src="<c:url value="/js/modules/library/reg.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Stationery Registration
    </div>
    <form class="form-horizontal" id="stationery-reg-form" style="margin-top: 1em">
        <input type="hidden" name="stationeryRegCode" id="stat-reg-code">

        <div class="row" style="margin-top: 1em">
            <button type="button" class="btn btn-default pull-left" id="btn-new-reg">New</button>

            <button type="button" class="btn btn-success pull-left" id="btn-save-reg">Save</button>
            <button type="button" class="btn btn-danger pull-left" id="btn-delete-reg">Delete</button>
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
                <label for="noOfUnits" class="control-label col-md-4">Units<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="units" id="units" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Store<span class="required">*</span></label>
                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="store-id" name="stores"/>
                    <input type="hidden" id="store-name">
                    <div id="store-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageStore"/>" >

                    </div>
                </div>

            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
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

    <div class="col-md-offset-1 col-md-10" style="margin-top: 1em">
        <button type="button" id="totals" class="btn btn-info btn-xs pull-right">Totals</button>

        <table id="stationery-reg-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Stationery Type</td>
                    <td>Reg Date</td>
                    <td>Registered By</td>
                    <td>Units</td>
                    <td>Store</td>
                    <td>Total</td>
                </tr>
                </thead>
            </table>
        </div>
</div>
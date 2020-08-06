<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/6/2020
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/library/stores.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Stationery Setups
    </div>

    <button type="button" class="btn btn-success pull-left" id="btn-save-stationery">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-stationery">Delete</button>

    <div class="col-md-offset-1 col-md-8" style="margin-top: 1em">
        <form id="stationery-form" class="form-horizontal">
            <input type="hidden" name="stationeryCode" id="stationery-code">
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Stationery Code<span class="required">*</span></label>
                </div>
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Stationery<span class="required">*</span></label>
                </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-4 col-xs-12">
                    <input name="statCode" id="code" class="form-control"
                           placeholder="" readonly>

                </div>
                <div class="col-md-4 col-xs-12">

                    <input name="stationeryName" id="name" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </form>

        <table id="stationery-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>STATIONERY</td>

            </thead>
        </table>
    </div>
</div>

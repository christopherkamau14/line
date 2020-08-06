<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/6/2020
  Time: 3:18 PM
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
        Stores Setups
    </div>

    <button type="button" class="btn btn-success pull-left" id="btn-save-store">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-store">Delete</button>

    <div class="col-md-offset-1 col-md-8" style="margin-top: 1em">
        <form id="stores-form" class="form-horizontal">
            <input type="hidden" name="storeCode" id="store-code">
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
            <div class="col-md-3 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-8">Store Code<span class="required">*</span></label>
            </div>
            <div class="col-md-3 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-8">Store<span class="required">*</span></label>
            </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <input type="hidden" name="dayCode" id="day-id">
                <div class="col-md-4 col-xs-12">
                        <input name="code" id="code" class="form-control"
                               placeholder="" readonly>

                </div>
                <div class="col-md-4 col-xs-12">

                        <input name="storeName" id="name" class="form-control"
                               placeholder="" required>
            </div>
            <div class="col-md-4 col-xs-12">
                <input type="checkbox" name="stationery" class="form-check-input" id="lesson-auto">

                <label for="lesson-auto" class="control-label">Stationery/Books<span class="required">*</span></label>
            </div>
            </div>
        </form>

        <table id="stores-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>STORE_NAME</td>
                <td>STORES</td>

            </thead>
        </table>
    </div>
</div>
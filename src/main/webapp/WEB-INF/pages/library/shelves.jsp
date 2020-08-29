<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/13/2020
  Time: 1:51 PM
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
        Shelves Setups
    </div>
    <button type="button" class="btn btn-default pull-left" id="btn-new-shelves">New</button>

    <button type="button" class="btn btn-success pull-left" id="btn-save-shelves">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-shelves">Delete</button>

    <div class="col-md-offset-1 col-md-6" style="margin-top: 1em">
        <form id="shelves-form" class="form-horizontal">
            <input type="hidden" name="shelfCode" id="shelf-code">
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Shelves Code<span class="required">*</span></label>
                </div>
                <div class="col-md-3 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Shelves<span class="required">*</span></label>
                </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-4 col-xs-12">
                    <input name="code" id="code" class="form-control"
                           placeholder="" readonly>

                </div>
                <div class="col-md-4 col-xs-12">

                    <input name="shelfName" id="name" class="form-control"
                           placeholder="" required>
                </div>

            </div>
        </form>

        <table id="shelves-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>SHELVES</td>

            </thead>
        </table>
    </div>
</div>

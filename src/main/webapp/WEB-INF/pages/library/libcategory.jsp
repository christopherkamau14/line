<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/13/2020
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/library/category.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Category Setups
    </div>
    <button type="button" class="btn btn-default pull-left" id="btn-new-cat">New</button>

    <button type="button" class="btn btn-success pull-left" id="btn-save-cat">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-cat">Delete</button>

    <div class="col-md-offset-1 col-md-11" style="margin-top: 1em">
        <form id="category-form" class="form-horizontal">
            <input type="hidden" name="categoryId" id="category-id">

            <div class="row" style="margin-top: 1em">
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Category Code<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Category<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Duration<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Duration Type<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">No.Of Books<span class="required">*</span></label>
                </div>
                <div class="col-md-2 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-8">Charges(P.D)<span class="required">*</span></label>
                </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-2 col-xs-12">
                    <input name="categoryCode" id="code" class="form-control"
                           placeholder="" readonly>
                </div>
                <div class="col-md-2 col-xs-12">
                    <input name="categoryName" id="name" class="form-control"
                           placeholder="" required>
                </div>
                <div class="col-md-2 col-xs-12">
                        <input type='text' class="form-control pull-right" name="duration" id="duration"/>

                </div>
                <div class="col-md-2 col-xs-12">
                    <select class="form-control" id="duration-type" name="durationType">
                        <option value="days">Days</option>
                        <option value="weeks">Weeks</option>
                        <option value="months">Months</option>

                    </select>
                </div>
                <div class="col-md-2 col-xs-12">
                    <input type='text' class="form-control pull-right" name="bookNumber" id="book-number"/>
                </div>
                <div class="col-md-2 col-xs-12">
                    <input type='text' class="form-control pull-right" name="charges" id="charges"/>
                </div>
            </div>
        </form>

        <table id="category-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Name</td>
                <td>Duration</td>
                <td>Duration Type</td>
                <td>No of Books</td>
                <td>Overdue Charges</td>

            </thead>
        </table>
    </div>
</div>

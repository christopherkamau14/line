<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/28/2020
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/library/cost.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Cost Setups
    </div>
    <div class="row" style="margin-bottom: 1em">
    <button type="button" class="btn btn-default pull-left" id="btn-new-cost">New</button>

    <button type="button" class="btn btn-success pull-left" id="btn-save-costs">Save</button>
    <button type="button" class="btn btn-danger pull-left" id="btn-delete-costs">Delete</button>
    </div>
        <form id="costs-form" class="form-horizontal">
            <input type="hidden" name="costCode" id="cost-code">
            <div class="row" style="margin-top: 1em;margin-bottom: 2em">
                <div class="col-md-offset-1 col-md-4 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-4">CallNo./Title<span class="required">*</span></label>

                    <div class="col-md-8 col-xs-12">
                        <input type="hidden" id="reg-code" name="bookRegister"/>
                        <input type="hidden" id="reg-name" name="title">
                        <div id="reg-div" class="form-control"
                             select2-url="<c:url value="/protected/library/pageBookRegister"/>" >

                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 1em;margin-bottom: 1em">
                <div class="col-md-offset-1 col-md-4 col-xs-12">
                    <label for="noOfUnits" class="control-label col-md-4">Cost<span class="required">*</span></label>
                    <div class="col-md-8 col-xs-12">
                    <input name="cost" id="cost" class="form-control"
                           placeholder="" >
                    </div>

                </div>
            </div>

        </form>

           <div class="col-md-offset-2 col-md-8" style="margin-top: 2em">
               <div class="row" >
                   <div class="col-md-4 col-xs-12 pull-right" style="margin-bottom: 1em">
                           <input  id="search-title" class="form-control"
                                   placeholder="Search Title" >


                   </div>
               </div>
        <table id="cost-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Title</td>
                <td>Cost</td>
                <td>Date</td>
                <td>Updated By</td>
            </thead>
        </table>
    </div>
</div>

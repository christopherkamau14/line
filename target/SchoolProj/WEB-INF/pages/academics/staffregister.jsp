<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/26/2020
  Time: 8:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/register.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Staff Register
    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-1">
            <button type="button" id="s-run" class="btn btn-default btn-srun">Run</button>
        </div>

        <div class="col-md-3">
            <label class="custom-control-label" for="name">Name</label>
            <input type="text" name="name" id="s-name" class="form-control"
                   placeholder="" required>
        </div>
    </div>
    <div style="margin-top: 2em">
        <table id="s-register-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Courtesy</td>
                <td>Name</td>
                <td>Duty</td>
                <td>IDNo.</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>

</div>

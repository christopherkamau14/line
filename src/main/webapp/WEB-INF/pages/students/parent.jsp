<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>

<div class="x_panel">
    <div class="x_title">
        Parent Details
    </div>
    <form action="<c:url value="/protected/getParentForm"/>">
    <input type="submit" class="btn btn-info pull-right" value="New">
    </form>
    <div class="row" style="margin-bottom: 1em">
        <div class="col-md-2 pull-right">

            <input type="text" name="name" id="search-id" class="form-control pull-right"
                   placeholder="Search">
        </div>
    </div>
    <div style="margin-top: 1em">
    <table id="parents-tbl" class="table table-hover table-bordered">
        <thead>
        <tr>
            <td>Parent Name</td>
            <td>Address</td>
            <td>TelNo</td>
            <td>Occupation</td>
            <td>Email</td>
            <td>Parent/Guardian</td>
            <td>Mother Name</td>
            <td>Mother Address</td>
            <td>Mother TelNo</td>
            <td>Mother Email</td>
            <td>Parent Pic</td>
            <td>Require Mail</td>
            <td>ID No.</td>
            <th></th>
            <th></th>
        </tr>
        </thead>
    </table>
    </div>
</div>

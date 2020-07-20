<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/22/2020
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/assigned.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Assigned Subjects
    </div>

        <div style="margin-top: 1em">
            <table id="teacher-subjects-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Salutation</td>
                    <td>Name</td>
                    <td>Class</td>
                    <td>Subject</td>
                    <td>Timetable</td>
                </thead>
            </table>
        </div>

</div>

<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/3/2020
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value='/js/collapse.js' /> "></script>
<div class="x_panel">
    <div class="x_title">
        <h2>Quick Access</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="row">

            <a href="<c:url value="/protected/student"/>"><img src="<c:url value="/images/index.jpg"/>"></a>
            <a href="<c:url value="/protected/setup"/>"><img src="<c:url value="/images/setup.png"/>"></a>
            <a href="<c:url value="/protected/academics/teachers"/>"><img src="<c:url value="/images/tpad.png"/>"></a>
            <a href="#" class="btn btn-inverse btn-lg"></a>
            <a href="<c:url value="/protected/details/leaveouts"/>"><img src="<c:url value="/images/leave.jpg"/>"></a>


        </div>
    </div>
</div>

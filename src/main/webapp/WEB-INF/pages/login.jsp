<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/2/2020
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>


<jsp:include page="./head.jsp"/>

<body class="login">

<div class="login_wrapper">
    <div class="animate form login_form">
        <section class="login_content">
            <c:set value="/login" var="login"/>
            <form action="<c:url value="/login"/>" method="post" novalidate="novalidate" autocomplete="off" >

                <%--<h1><spring:message code='project.name'/></h1>--%>
                <div class="kv-avatar center-block" style="width:200px">
                    <img  src="<c:url value='/images/student1.jpg'/> " style="width:200px">
                </div>
                <div class="clearfix"></div>

                <p></p>
                <c:if test="${param.error!=null}">
                    <div class="alert alert-danger alert-dismissible fade in" role="alert" id="login-error">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
                        </button>
                        <spring:message code="login.invalid.username.or.password" />
                    </div>
                </c:if>
                <div>
                    <input type="text" class="form-control" placeholder="Username"  name="username" />
                </div>
                <div>
                    <input type="password" class="form-control" placeholder="Password"  name="password"/>
                </div>
                    <div>
                    <input type="submit" class="btn btn-default submit" value="Login">
                    </div>

                <div class="clearfix"></div>

                <div class="separator">
                    <div class="col-md-6">
                        <button type="button" id="btn-forgot-password"  onclick="forgotPassword()" class="btn btn-link">Forgot Password</button>
                    </div>

                    <div class="clearfix"></div>
                    <br />

                    <div>

                        <p>©2020 All Rights Reserved</p>
                    </div>
                </div>
            </form>
        </section>
    </div>
</div>
<script  src="<c:url value='/libs/bootstrap/dist/js/bootstrap.min.js' /> "></script>
<script src="<c:url value="/libs/pnotify/dist/pnotify.js"/>"></script>
<script src="<c:url value="/libs/pnotify/dist/pnotify.buttons.js"/>"></script>
<script  src="<c:url value="/libs/pnotify/dist/pnotify.nonblock.js"/>"></script>
<script src="<c:url value="/libs/jquery-validation/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/libs/jquery-validation/additional-methods.min.js"/>"></script>

<div class="modal fade bs-example-modal-sm" id="myPleaseWait" tabindex="-1"
     role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-time">
                    </span>Please Wait
                </h4>
            </div>
            <div class="modal-body">
                <div class="progress">
                    <div class="progress-bar progress-bar-info
                    progress-bar-striped active"
                         style="width: 100%">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

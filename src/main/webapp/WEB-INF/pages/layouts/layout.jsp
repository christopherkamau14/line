<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/2/2020
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<tiles:insertAttribute name="header">
</tiles:insertAttribute>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <tiles:insertAttribute name="head" />
        <tiles:insertAttribute name="menu" />



        <!-- Main content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <tiles:insertAttribute name="body" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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

<script  src="<c:url value="/libs/js/jquery-ui.min.js"/>"></script>

<!-- Bootstrap -->
<script  src="<c:url value='/libs/bootstrap/dist/js/bootstrap.min.js' /> "></script>
<!-- NProgress -->
<script src="<c:url value='/libs/nprogress/nprogress.js' /> "></script>
<!-- Chart.js -->
<script src="<c:url value='/libs/Chart.js/dist/Chart.min.js' /> "></script>
<!-- gauge.js -->
<script  src="<c:url value='/libs/gauge.js/dist/gauge.min.js' /> "></script>
<!-- bootstrap-progressbar -->
<script   src="<c:url value='/libs/bootstrap-progressbar/bootstrap-progressbar.min.js' /> "></script>
<!-- iCheck -->
<script src="<c:url value='/libs/iCheck/icheck.min.js' /> "></script>

<script src="<c:url value='/libs/js/trumbowyg.min.js' /> "></script>

<!-- bootstrap-daterangepicker -->
<script  src="<c:url value='/libs/moment/min/moment.min.js' /> "></script>
<script  src="<c:url value='/libs/bootstrap-daterangepicker/daterangepicker.js' /> "></script>
<script src="<c:url value='/libs/js/bootstrap-datetimepicker.js' /> "></script>
<script src="<c:url value='/libs/js/timeago.js' /> "></script>

<script src="<c:url value='/libs/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js' /> "></script>

<!-- Custom Theme Scripts -->
<script  src="<c:url value='/libs/build/js/custom.min.js' /> "></script>
<script type="text/javascript" src="<c:url value="/libs/js/bootbox.min.js"/>"></script>
<script src="<c:url value="/libs/jquery-validation/jquery.validate.min.js"/>"></script>
<script src="<c:url value="/libs/jquery-validation/additional-methods.min.js"/>"></script>

<script src="<c:url value="/libs/pnotify/dist/pnotify.js"/>"></script>
<script src="<c:url value="/libs/pnotify/dist/pnotify.buttons.js"/>"></script>
<script  src="<c:url value="/libs/pnotify/dist/pnotify.nonblock.js"/>"></script>
<script  src="<c:url value="/libs/fullcalendar/dist/fullcalendar.min.js"/>"></script>
<script type="text/javascript">
    var SERVLET_CONTEXT = '${pageContext.request.contextPath}';
</script>
</body>


</html>
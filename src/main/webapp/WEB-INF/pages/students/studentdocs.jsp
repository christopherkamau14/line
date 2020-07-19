<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/students/students.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Student Docs
    </div>
    <div class="col-md-4">
    <div class="form-group">
        <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

        <div class="col-md-6 col-xs-12">
            <input type="hidden" id="stud-id" name="student"/>
            <input type="hidden" id="stud-name">
            <div id="std-div" class="form-control"
                 select2-url="<c:url value="/protected/pageStudent"/>" >

            </div>
        </div>

    </div>
    </div>
    <div id="docs-per-stud" style="margin-bottom: 1em">

        <div style="margin-top: 1em">
            <table id="std-docs-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Student Name</td>
                    <td>Document Name</td>
                    <td>File Name</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>

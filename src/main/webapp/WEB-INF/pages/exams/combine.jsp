<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/8/2020
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/exams/combine.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Combine Exams
    </div>
    <form class="form-horizontal" id="exam-form">
        <div class="row" style="margin-top: 2em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Year<span class="required">*</span></label>

                <div class="col-md-4 col-xs-12">
                    <input type="hidden" id="year-code" name="year"/>
                    <input type="hidden" id="year-name">
                    <div id="year-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageYear"/>" >

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Term<span class="required">*</span></label>

                <div class="col-md-4 col-xs-12">
                    <input type="hidden" id="term-code" name="term"/>
                    <input type="hidden" id="term-name">
                    <div id="term-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageTerm"/>" >

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Main Exam<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="reg-code" name="finalExam"/>
                    <input type="hidden" id="reg-name">
                    <div id="reg-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 2em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Combine Into<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="com-code" name="combineExam"/>
                    <input type="hidden" id="com-name">
                    <div id="com-div" class="form-control"
                         select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 3em">
            <div class="col-md-offset-2 col-md-10 col-xs-12">
                <input type="submit" class="btn btn-default" value="Combine">
            </div>
        </div>
    </form>
</div>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2020
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/xlsx.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/FileSaver.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/exams/process.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
       <h3>Exam Processing</h3>
    </div>
    <div class="row">
        <div class="col-md-offset-7">
        <h4>Reports</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Year<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="hidden" id="year-code" name="year"/>
                <input type="hidden" id="year-name">
                <div id="year-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageYear"/>" >

                </div>
            </div>

        </div>
        <div class="col-md-3 col-xs-12">
            <button type="button" class="btn  btn-default btn-large" id="process">Process</button>
        </div>
        <form action="${pageContext.request.contextPath}/protected/reports/missed">
        <div class="col-md-3 col-xs-12" id="hide-miss">
            <div class="custom-control custom-radio custom-control-inline">
            <input type="hidden" name="exam" id="id-missed">
                <input type="hidden" name="forms" id="id-forms">

                <input type="submit" class="btn btn-default" value="Missed Exams">
            </div>
        </div>
        </form>
    </div>

    <div class="row" style="margin-top: 1em">
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Term<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="hidden" id="term-code" name="term"/>
                <input type="hidden" id="term-name">
                <div id="term-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageTerm"/>" >

                </div>
            </div>

        </div>
<form action="${pageContext.request.contextPath}/protected/reports/subjectanalysis">
        <div class="col-md-offset-3 col-md-3 col-xs-12" id="hide-sub-analys">
                <input type="hidden" name="exam" id="sub-analys">
            <input type="hidden" name="forms" id="sub-forms">
                <input type="submit" class="btn btn-default" value="Subject Analysis">
        </div>
</form>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Exam<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="hidden" id="reg-code" name="examRegister"/>
                <input type="hidden" id="reg-name">
                <div id="reg-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>

        </div>
<form action="${pageContext.request.contextPath}/protected/reports/classanalysis">
        <div class="col-md-offset-3 col-md-3 col-xs-12" id="hide-class-analys">
                <input type="hidden" name="exam" id="class-analys">
            <input type="hidden" name="forms" id="class-forms">
            <input type="submit" class="btn btn-default" value="Class Analysis">
            </div>
</form>

    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Class<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="hidden" id="class" name="forms"/>
                <input type="hidden" id="dm">
                <div id="class-div" class="form-control"
                     select2-url="<c:url value="/protected/pageClass"/>" >

                </div>
            </div>

        </div>
<form action="${pageContext.request.contextPath}/protected/reports/genderanalysis">
        <div class="col-md-offset-3 col-md-3 col-xs-12" id="hide-gender-analys">
                <input type="hidden" name="exam" id="gender-analys">
            <input type="hidden" name="forms" id="gender-forms">
            <input type="submit" class="btn btn-default" value="Gender Analysis">
        </div>
</form>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-3 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Action<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <select class="form-control" id="action" name="action">
                    <option>Select</option>
                    <option value="ER">Email Report Forms</option>
                    <option value="LE">Lock Exam</option>
                    <option value="PO">POSITION</option>
                    <option value="SMS">SMS Marks</option>
                    <option value="UNLE">Unlock Exam</option>
                </select>
            </div>

        </div>
        <div class="col-md-offset-3 col-md-3 col-xs-12" id="hide-b-class-analys">
            <form action="${pageContext.request.contextPath}/protected/reports/broadstream">
            <input type="hidden" name="exam" id="b-class-analys">
            <input type="hidden" name="forms" id="b-class-forms">
            <input type="submit" class="btn btn-default" value="Broadsheet Stream">
            </form>

        </div>
    </div>
    <div class="row" style="margin-top: 1em">
<form action="${pageContext.request.contextPath}/protected/reports/singleexamlist">
        <div class="col-md-offset-6 col-md-2 col-xs-12" id="hide-subject-merit">
                <input type="hidden" name="exam" id="subject-merit">
            <input type="hidden" name="forms" id="subject-merit-forms">
            <input type="submit" class="btn btn-default" value="Subject Merit">
        </div>
</form>
        <form action="${pageContext.request.contextPath}/protected/reports/gendermeritlist">
        <div class="col-md-offset-6 col-md-2 col-xs-12" id="hide-gender-merit">
                <input type="hidden" name="exam" id="gender-merit">
            <input type="hidden" name="forms" id="gender-merit-forms">
            <input type="submit" class="btn btn-default" value="Gender Merit">
        </div>
        </form>
        <form action="${pageContext.request.contextPath}/protected/reports/singleexamlist">
        <div class="col-md-offset-6 col-md-2 col-xs-12" id="hide-va-merit">
                <input type="hidden" name="exam" id="va-merit">
            <input type="hidden" name="forms" id="va-merit-forms">

            <input type="submit" class="btn btn-default" value="V.A Merit">
        </div>
        </form>
    </div>
    <div class="row" style="margin-top: 1em">
<form action="${pageContext.request.contextPath}/protected/reports/singleexamlist">
        <div class="col-md-offset-6 col-md-4 col-xs-12" id="hide-single-merit">
                <input type="hidden" name="exam" id="single-merit">
            <input type="hidden" name="forms" id="single-merit-forms">
            <input type="submit" class="btn btn-default" value="Single Exam Report">
        </div>
</form>
    </div>
    <div class="row" style="margin-top: 1em">
<form action="${pageContext.request.contextPath}/protected/reports/combinedexam">
        <div class="col-md-offset-6 col-md-4 col-xs-12" id="hide-combined-merit">
                <input type="hidden" name="exam" id="combined-merit" >
            <input type="hidden" name="forms" id="combined-merit-forms">

            <input type="submit" class="btn btn-default" value="Combined Exam Report">
        </div>
</form>
    </div>
</div>
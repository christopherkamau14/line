<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/5/2020
  Time: 11:41 AM
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
<script type="text/javascript" src="<c:url value="/js/modules/exams/remarks.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        <h3>Custom Remarks</h3>
    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Year<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="year-code" name="year"/>
                <input type="hidden" id="year-name">
                <div id="year-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageYear"/>" >

                </div>
            </div>
        </div>
            <div class="col-md-4 col-xs-12">
                <label for="SclName" class="control-label col-md-5">Subjects Entered</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="subjects" id="subjects"/>
                </div>             </div>
            <div class="col-md-4 col-xs-12">
                <label for="SclName" class="control-label col-md-5">Mean marks</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="marks" id="marks"/>
                </div>             </div>
            </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-4 col-xs-12">

            <label for="noOfUnits" class="control-label col-md-4">Term<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="term-code" name="term"/>
                <input type="hidden" id="term-name">
                <div id="term-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageTerm"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12">
            <label for="SclName" class="control-label col-md-5">Grade</label>
            <div class="col-md-6">
                <input type="text" class="form-control" name="grade" id="grade"/>
            </div>         </div>
        <div class="col-md-4 col-xs-12">
            <label for="SclName" class="control-label col-md-5">Class Mean Grade</label>
            <div class="col-md-6">
                <input type="text" class="form-control" name="classGrade" id="class-grade"/>
            </div>         </div>
    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-4 col-xs-12">

            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code" name="examRegister"/>
                <input type="hidden" id="reg-name">
                <div id="reg-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12">
            <label for="SclName" class="control-label col-md-5">Total Marks</label>
            <div class="col-md-6">
                <input type="text" class="form-control" name="total" id="total"/>
            </div>         </div>
        <div class="col-md-4 col-xs-12">
            <label for="SclName" class="control-label col-md-5">Class Mean Marks</label>
            <div class="col-md-6">
                <input type="text" class="form-control" name="classMean" id="class-mean"/>
            </div>         </div>
    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="class" name="forms"/>
                <input type="hidden" id="dm">
                <div id="class-div" class="form-control"
                     select2-url="<c:url value="/protected/pageClass"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-4 col-xs-12">
            <label for="SclName" class="control-label col-md-5">Position</label>
            <div class="col-md-6">
                <input type="text" class="form-control" name="position" id="position"/>
            </div>         </div>

    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="stud-id" name="student"/>
                <input type="hidden" id="stud-name">
                <div id="std-div" class="form-control"
                     select2-url="" >

                </div>
            </div>
        </div>

    </div>
    <div class="row" style="margin-top: 2em">
        <div class="col-md-offset-1 col-md-1 col-xs-12">
            <button type="button" class="btn btn-default" id="s-position">Position</button>
        </div>
        <div class="col-md-1 col-xs-12" id="h-rpt">
            <form action="<c:url value="/protected/exams/reportForms"/>"  method="post">
               <input type="hidden" name="student" id="student-id">
                <input type="hidden" name="forms" id="for-id">
                <input type="hidden" name="exam" id="exam-id">

                <input type="submit" class="btn btn-default" value="Report Forms">
        </div>
        <div class="col-md-3 col-xs-12 pull-right">
                <textarea name="hRemarks" id="h-remarks" class="form-control" placeholder="Head Teacher Comments"></textarea>
            </div>
        <div class="col-md-offset-3 col-md-3 col-xs-12 ">

        <textarea name="remarks" id="t-remarks" class="form-control" placeholder="Teacher Comments"></textarea>
        </div>
        </form>

    </div>

</div>
    </div>

    </div>


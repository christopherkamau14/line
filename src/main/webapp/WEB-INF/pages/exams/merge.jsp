<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/29/2020
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/exams/merge.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Merge Exam
    </div>
<form id="merge-form">
<div class="row" style="margin-top: 2em">
    <div class="col-md-4 col-xs-12">
        <label for="noOfUnits" class="control-label col-md-4">Exams To Merge<span class="required">*</span></label>

        <div class="col-md-8 col-xs-12">
            <select class="form-control" id="sel-no" name="number">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>

            </select>
        </div>

    </div>
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
        <label for="noOfUnits" class="control-label col-md-4">Year<span class="required">*</span></label>

        <div class="col-md-8 col-xs-12">
            <input type="hidden" id="year-code" name="year"/>
            <input type="hidden" id="year-name">
            <div id="year-div" class="form-control"
                 select2-url="<c:url value="/protected/exams/pageYear"/>" >

            </div>
        </div>
    </div>
</div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code" name="exam"/>
                <input type="hidden" id="reg-name">
                <div id="reg-div" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark1" id="mark1-id" class="form-control">
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code1" name="exam1"/>
                <input type="hidden" id="reg-name1">
                <div id="reg-div1" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark2" id="mark2-id" class="form-control">
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-3">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code2" name="exam2"/>
                <input type="hidden" id="reg-name2">
                <div id="reg-div2" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark3" id="mark3-id" class="form-control">
        </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-4">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code3" name="exam3"/>
                <input type="hidden" id="reg-name3">
                <div id="reg-div3" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark4" id="mark4-id" class="form-control">
        </div>
        </div>
        <div class="col-md-offset-5 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Out Of<span class="required">*</span></label>

            <div class="col-md-3 col-xs-12">
                <input name="outOff" id="out-off" class="form-control"
                       placeholder="" required>
            </div>
        </div>

    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-5">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code4" name="exam4"/>
                <input type="hidden" id="reg-name4">
                <div id="reg-div4" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark5" id="mark5-id" class="form-control">
        </div>
        </div>
        <div class="col-md-offset-5 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Merge Into<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code5" name="examRegister"/>
                <input type="hidden" id="reg-name5">
                <div id="reg-div5" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-6">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code6" name="exam5"/>
                <input type="hidden" id="reg-name6">
                <div id="reg-div6" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark6" id="mark6-id" class="form-control">
        </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-7">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code7" name="exam6"/>
                <input type="hidden" id="reg-name7">
                <div id="reg-div7" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark7" id="mark7-id" class="form-control">
        </div>
        </div>
        <div class="col-md-offset-5 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="class" name="forms"/>
                <input type="hidden" id="dm">
                <div id="class-div" class="form-control"
                     select2-url="<c:url value="/protected/pageClass"/>" >

                </div>
            </div>

        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-8">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code8" name="exam7"/>
                <input type="hidden" id="reg-name8">
                <div id="reg-div8" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark8" id="mark8-id" class="form-control">
        </div>
        </div>
        <div class="col-md-offset-5 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Subject<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="subject-id" name="subjects"/>
                <input type="hidden" id="subject-name">
                <div id="subject-div" class="form-control"
                     select2-url="<c:url value="/protected/academics/pageSubjects"/>" >

                </div>
            </div>
        </div>

    </div>

    <div class="row" style="margin-top: 1em">
        <div id="d-9">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code9" name="exam8"/>
                <input type="hidden" id="reg-name9">
                <div id="reg-div9" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1">
            <input type="text" name="mark9" id="mark9-id" class="form-control">
        </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div id="d-10">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Exam<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                <input type="hidden" id="reg-code10" name="exam9"/>
                <input type="hidden" id="reg-name10">
                <div id="reg-div10" class="form-control"
                     select2-url="<c:url value="/protected/exams/pageRegister"/>" >

                </div>
            </div>
        </div>
        <div class="col-md-1 ">
            <input type="text" name="mark10" id="mark10-id" class="form-control">
        </div>
    </div>
    </div>

    <div class="row">
        <div class="col-md-offset-2 col-md-5">

            <input type="checkbox" name="deleteExams" class="form-check-input" id="del-exam">

            <label for="del-exam" class="control-label">Delete Existing Records<span class="required">*</span></label>
        </div>
        <div class="col-md-4 ">
            <input type="submit" class="btn btn-default" value="Merge">
        </div>
    </div>
</form>
</div>
<div class="modal fade" id="blockModal" tabindex="-1" role="dialog"
     aria-labelledby="blockModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="blockModalLabel">
                    Combine Exam
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="exam-form">
                    <div class="row" style="margin-top: 2em">
                        <div class="col-md-10 col-xs-12">
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
                        <div class="col-md-10 col-xs-12">
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
                        <div class="col-md-10 col-xs-12">
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
                        <div class="col-md-10 col-xs-12">
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
                        <div class="col-md-offset-4 col-md-10 col-xs-12">
                            <input type="submit" class="btn btn-default" value="Combine">
                        </div>
                    </div>
                </form>


                <div class="modal-footer" style="margin-top: 2em">

                </div>
            </div>
        </div>
    </div>
</div>


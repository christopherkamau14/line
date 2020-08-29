<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/29/2020
  Time: 1:05 PM
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
<script type="text/javascript" src="<c:url value="/js/modules/library/itemsgiven.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Items Issuing
    </div>
    <div class="row" style="margin-top: 1em">
        <button type="button" class="btn btn-default pull-left" id="btn-new-itemissue">New</button>

        <button type="button" class="btn btn-success pull-left" id="btn-save-itemissue">Save</button>
        <button type="button" class="btn btn-danger pull-left" id="btn-delete-itemissue">Delete</button>
    </div>
    <form class="form-horizontal" id="item-issue-form" style="margin-top: 1em">
        <input type="hidden" name="itemCode" id="item-code">


        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Item Name<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="text" id="item-name" class="form-control" name="itemName"/>

                </div>
            </div>

            <div class="col-md-offset-2 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Issue Date<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="issueDate" id="issue-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Units<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="units" id="units" class="form-control"
                           placeholder="">

                </div>
            </div>
            <div class="col-md-offset-2 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Return Date<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="returnDate" id="return-date"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Group Issued<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <select class="form-control" id="borrower-cat" name="borrowerCat">
                        <option>Select</option>
                        <option value="S">Student</option>
                        <option value="T">Teacher</option>
                    </select>
                </div>
            </div>
            <div class="col-md-offset-2 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Description</label>
                <div class="col-md-8 col-xs-12">

            <textarea name="description" id="desc-comments" class="form-control" placeholder="Description"></textarea>
                </div>
            </div>
        </div>
            <div class="row" style="margin-top: 1em ">
                <input type="hidden" name="borrowerName" id="borrower-name">
            <div class="col-md-4 col-xs-12" id="t-div">
                <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="tea-id" name="teachers"/>
                    <input type="hidden" id="tea-name" name="teacherName">
                    <div id="teach-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTeacher"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12" id="s-div">
                <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="stud-id" name="student"/>
                    <input type="hidden" id="stud-name" name="studentName">
                    <input type="hidden" id="name">
                    <div id="std-div" class="form-control"
                         select2-url="" >

                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12" id="c-div">
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


            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Issued By<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="issued-by" name="issuerCode"/>
                    <input type="hidden" id="issued-name" name="issuerName">
                    <div id="issued-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageUsers"/>" >

                    </div>
                </div>
            </div>

            <div class="col-md-offset-4 col-md-2 col-xs-12">
                <button type="button" class="btn btn-default btn-large" id="btn-return">Return</button>
            </div>

        </div>




    </form>

    <div class="col-md-12" id="ret">
        <div class="row" style="margin-top:2em;margin-bottom: 1em" id="search-ret">



            <div class="col-md-3 col-xs-12 pull-right">

                <input type="text"  class="form-control" id="issued-to" placeholder="Teacher/Student">

            </div>
        </div>
        <table id="items-table" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Item No</td>
                <td>Item Name</td>
                <td>Borrower</td>
                <td>Issue Date</td>
                <td>Description</td>
                <td>Returned</td>
                <td>Given To</td>
                <td>Given By</td>
                <td>Return Date</td>
                <td>Date Returned</td>
                <td>Units</td>
            </tr>
            </thead>
        </table>
    </div>
</div>

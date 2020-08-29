<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/18/2020
  Time: 2:12 PM
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
<script type="text/javascript" src="<c:url value="/js/modules/library/bookissue.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Books Issuing
    </div>
    <div class="row" style="margin-top: 1em">
        <button type="button" class="btn btn-default pull-left" id="btn-new-bookissue">New</button>

        <button type="button" class="btn btn-success pull-left" id="btn-save-bookissue">Save</button>
        <button type="button" class="btn btn-danger pull-left" id="btn-delete-bookissue">Delete</button>
    </div>
    <form class="form-horizontal" id="book-issue-form" style="margin-top: 1em">
        <input type="hidden" name="bookIssueCode" id="book-reg-code">


        <div class="row" style="margin-top: 1em">
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">CallNo./Title<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="reg-code" name="bookRegister"/>
                    <input type="hidden" id="reg-name" name="callNo">
                    <div id="reg-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageBookRegister"/>" >

                    </div>
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
                <label for="noOfUnits" class="control-label col-md-4">Title<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="title" id="title" class="form-control"
                           placeholder="" readonly>
                    <input type="hidden" name="title" id="title-save">
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
            <div class="col-md-4 col-xs-12" id="bo-div">
                <label for="noOfUnits" class="control-label col-md-4">Borrower Code<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <select class="form-control" id="borrower-cat" name="borrowerCat">
                        <option>Select</option>
                        <option value="S">Student</option>
                        <option value="T">Teacher</option>
                    </select>
                </div>
            </div>
            <div class="col-md-4 col-xs-12" id="t-div">
                <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="tea-id" name="teachers"/>
                    <input type="hidden" id="tea-name">
                    <div id="teach-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTeacher"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-4 col-xs-12" id="s-div">
                <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="stud-id" name="student"/>
                    <input type="hidden" id="stud-name">
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

            <div class="col-md-offset-2 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Status</label>
                <div class="col-md-8 col-xs-12">
                    <select class="form-control" id="book-status" name="bStat">
                        <option value="">Status</option>
                        <option value="torn">Torn</option>
                        <option value="repaired">Repaired</option>
                        <option value="bought">Bought</option>
                        <option value="missing">Missing</option>
                        <option value="reserved">Reserved</option>
                        <option value="unreserved">Unreserved</option>

                    </select>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">


            <div class="col-md-offset-4 col-md-2 col-xs-12" id="i-div">
                 <button type="button" class="btn btn-default btn-large" id="btn-issue">Issue</button>
            </div>

            <div class="col-md-offset-4 col-md-2 col-xs-12" id="r-div">
                <button type="button" class="btn btn-default btn-large" id="btn-return">Return</button>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Comments</label>                <div class="col-md-8 col-xs-12">

                <textarea name="comments" id="s-comments" class="form-control" placeholder="Status Comments"></textarea>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-4 col-md-2 col-xs-12" >
                <input type="checkbox" name="viewIssued" class="form-check-input" id="view-issued">

                <label for="view-issued" class="control-label">View Issued Books<span class="required">*</span></label>

                </div>
            <div class="col-md-offset-2 col-md-4 col-xs-12">
                <button type="button" class="btn btn-default btn-large" id="update-status">Update Status</button>

            </div>
            </div>


    </form>
    <div class="row" style="margin-top:2em;margin-bottom: 1em" id="search-ret">
        <div class="col-md-offset-1 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Book Title<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="text" name="bookName" class="form-control" id="book-no" >
            </div>

        </div>
        <div class="col-md-offset-1 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Call No<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="text" name="callNo" class="form-control" id="call-no" >
            </div>

        </div>
    </div>
    <div class="col-md-12" id="ret">

        <table id="book-issue-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Book No</td>
                <td>Borrower</td>
                <td>Name/AdmNo</td>
                <td>Issue Date</td>
                <td>Return Date</td>
                <td>Book Returned?</td>
                <td>Book Overdue?</td>
                <td>Days Held</td>
                <td>Days Overdue</td>
                <td>Call No.</td>
                <td>Title</td>
            </tr>
            </thead>
        </table>
    </div>
    <div class="row" style="margin-top:2em;margin-bottom: 1em" id="search-bor">
        <div class="col-md-offset-1 col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-3">Book Title<span class="required">*</span></label>

            <div class="col-md-9 col-xs-12">
                <input type="text" name="bookName" class="form-control" id="tt-no" >
            </div>

        </div>
    </div>
    <div class="col-md-11" id="bor">

        <table id="book-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Title</td>
                <td>Publisher</td>
                <td>Author</td>
                <td>ISBN</td>
                <td>CallNo</td>
            </tr>
            </thead>
        </table>
    </div>
</div>

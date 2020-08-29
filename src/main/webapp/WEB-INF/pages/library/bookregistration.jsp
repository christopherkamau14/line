<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/16/2020
  Time: 8:10 PM
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
<script type="text/javascript" src="<c:url value="/js/modules/library/breg.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Books Registration
    </div>
    <form class="form-horizontal" id="book-reg-form" style="margin-top: 1em">
        <input type="hidden" name="bookRegCode" id="book-reg-code">

        <div class="row" style="margin-top: 1em">
            <button type="button" class="btn btn-default pull-left" id="btn-new-bookreg">New</button>

            <button type="button" class="btn btn-success pull-left" id="btn-save-bookreg">Save</button>
            <button type="button" class="btn btn-danger pull-left" id="btn-delete-bookreg">Delete</button>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Classification<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="classification-code" name="bookClass"/>
                    <input type="hidden" id="classification-name">
                    <div id="classify-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageClassification"/>" >

                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Date<span class="required">*</span></label>

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
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Title<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="title" id="title" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">ISBN No.<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="isbnNo" id="isbn-no" class="form-control"
                           placeholder="" required>
                </div>
                </div>

            </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Publisher<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="publisher" id="publisher" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Publication Year<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="year" id="year" class="form-control"
                           placeholder="" required>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Author<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="author" id="author" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Call No.<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="callNo" id="call-nos" class="form-control"
                           placeholder="" required>
                </div>
            </div>

        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Class<span class="required">*</span></label>
               <div class="col-md-8 col-xs-1">
                <select class="form-control" id="class-no" name="classNo">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="77">77</option>

                </select>
               </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Copies<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="number" name="units" id="units" class="form-control"
                           placeholder="" required>
                </div>
            </div>

        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-offset-1 col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Store<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="store-code" name="stores"/>
                    <input type="hidden" id="store-name">
                    <div id="stores-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageStore"/>" >

                    </div>
                </div>
            </div>

            <div class="col-md-4 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Shelves<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input type="hidden" id="shelves-code" name="shelves"/>
                    <input type="hidden" id="shelves-name">
                    <div id="shelves-div" class="form-control"
                         select2-url="<c:url value="/protected/library/pageShelves"/>" >

                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 1em;margin-bottom: 2em">
            <div class="col-md-offset-3 col-md-4 col-xs-12">
                <label for="short-loan" class="control-label">Short Loan Book?<span class="required">*</span></label>

                <input type="checkbox" name="shortLoan" class="form-check-input" id="short-loan">

            </div>
        </div>
    </form>
<div class="row" style="margin-bottom: 1em">
    <div class="col-md-offset-1 col-md-4 col-xs-12">
        <label for="noOfUnits" class="control-label col-md-3">Call No<span class="required">*</span></label>

        <div class="col-md-9 col-xs-12">
            <input type="text" name="callNo" class="form-control" id="call-no" >
        </div>

    </div>
</div>
    <div class="col-md-offset-1 col-md-10">

        <table id="book-reg-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Book No</td>
                <td>Title</td>
                <td>Publisher</td>
                <td>Author</td>
                <td>ISBN</td>
                <td>Publication</td>
                <td>Call No</td>
                <td>Registered</td>
                <td>Copies</td>

            </tr>
            </thead>
        </table>
    </div>
</div>

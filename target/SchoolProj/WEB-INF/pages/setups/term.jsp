<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
        Term Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-term" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="term-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Term Name</td>
                    <td>Term Number</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="termModal" tabindex="-1" role="dialog"
     aria-labelledby="termModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="termModalLabel">
                    Add Term Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="term-form">
                <input type="hidden" name="termCode" id="term-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Term Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="number" name="termName" id="term-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Term Number<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="number" name="termNumber" id="term-number" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>


                </form>
                <div class="modal-footer">
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <button id="btn-save-term" class="btn btn-success pull-right" style="margin-right: 10px">Save</button>

                        </div>
                        <div class="col-md-2">

                            <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
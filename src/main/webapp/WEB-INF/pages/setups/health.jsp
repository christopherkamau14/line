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
        Health Conditions
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-health" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="health-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Condition Name</td>
                    <td>Condition Description</td>
                    <td>Recommendation</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="healthModal" tabindex="-1" role="dialog"
     aria-labelledby="healthModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="healthModalLabel">
                    Add Health Condition
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="health-form">
               <input type="hidden" name="condCode" id="condition-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Condition Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="text" name="condName" id="condition-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Condition Desc<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="text" name="condDesc" id="condition-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Recommendation<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <textarea name="condComments" id="condition-comments" class="form-control"
                                          placeholder="" required></textarea>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-condition" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

                        </div>
                        <div class="col-md-2">

                            <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                                Cancel
                            </button>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
</div>
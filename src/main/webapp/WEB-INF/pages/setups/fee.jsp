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
        Fee Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-fee" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="fee-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Category Name</td>
                    <td>Default Category</td>
                    <td>Category Description</td>
                    <td>Category Successor</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="feeModal" tabindex="-1" role="dialog"
     aria-labelledby="feeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="feeModalLabel">
                    Add Fee Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fee-form">
                <input type="hidden" id="cat-id" name="catCode">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Category Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="catName" id="cat-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Default Category<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                    <input type="checkbox" name="defaultCat" class="form-check-input" id="fee-category">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Category Desc<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="catDesc" id="cat-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Category Successor<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="catSuccessor" id="cat-successor" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-fee" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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
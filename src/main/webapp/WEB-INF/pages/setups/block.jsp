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
        Block Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-block" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="block-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Block Name</td>
                    <td>Block Abbreviation</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
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
                    Add Block Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="block-form">
<input type="hidden" id="block-id" name="blockCode">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Block Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="blockName" id="block-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Block Abbreviation<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="blockDesc" id="block-abbr" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                </form>


                <div class="modal-footer">
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <button id="btn-save-block" class="btn btn-success pull-right" style="margin-right: 10px">Save</button>

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
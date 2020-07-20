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
        Dorm Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <button id="btn-add-dorm" class="btn btn-info pull-right">New</button>

        <div style="margin-top: 1em">
            <table id="dorm-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Dorm Name</td>
                    <td>Dorm Desc</td>
                    <td>Dorm Capacity</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="dormModal" tabindex="-1" role="dialog"
     aria-labelledby="dormModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dormModalLabel">
                    Add Dorm Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="dorm-form">
               <input type="hidden" name="dormCode" id="dorm-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Dorm Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="dormName" id="dorm-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Dorm Desc<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="dormDesc" id="dorm-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Dorm Capacity<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input name="capacity" id="dorm-capacity" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-dorm" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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
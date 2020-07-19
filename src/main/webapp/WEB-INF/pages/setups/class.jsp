<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/utils/dtspringscript.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<div class="x_panel">
    <div class="x_title">
Class Setups
    </div>
    <div id="country" style="margin-bottom: 1em">
        <div class="row" style="margin-bottom: 1em">
        <button id="btn-add-class" class="btn btn-info pull-right">New</button>
        </div>
        <div class="row" style="margin-bottom: 1em">
            <div class="col-md-2 pull-right">

            <input type="text" name="name" id="search-id" class="form-control pull-right"
                   placeholder="Search">
            </div>
        </div>
        <div style="margin-top: 1em">
            <table id="class-tbl" class="table table-hover table-bordered">
                <thead>
                <tr>
                    <td>Class Name</td>
                    <td>Class Code</td>
                    <td>Block</td>
                    <td>Teacher</td>
                    <td></td>
                    <td></td>
                </thead>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="classModal" tabindex="-1" role="dialog"
     aria-labelledby="classModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="classModalLabel">
                    Add Class Description
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="class-form">
               <input type="hidden" id="class-id" name="classCode" >

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Class Number<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="text" name="classNo" id="class-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Class Name<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="text" name="classDesc" id="class-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Stream<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="block-id" name="block"/>
                                <input type="hidden" id="block-name">
                                <div id="block-div" class="form-control"
                                     select2-url="<c:url value="/protected/pageBlock"/>" >

                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Active<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="checkbox" name="active" class="form-check-input" id="active">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Capacity<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="text" name="capacity" id="capacity" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-10 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Teacher<span class="required">*</span></label>

                            <div class="col-md-8 col-xs-12">
                                <input type="hidden" id="tea-id" name="teachers"/>
                                <input type="hidden" id="tea-name">
                                <div id="teach-div" class="form-control"
                                     select2-url="<c:url value="/protected/academics/pageTeacher"/>" >
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <button id="btn-save-class" class="btn btn-success pull-right" style="margin-right: 10px">Save</button>

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
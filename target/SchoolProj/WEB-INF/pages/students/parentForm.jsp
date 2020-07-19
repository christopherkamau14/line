<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<script>var parentCode=${parentId}</script>
<div class="x_panel">
    <div class="x_title">
        Parent Details
    </div>
<form class="form-horizontal" id="parent-form" style="margin-top: 2em">
    <input type="hidden" name="parCode" id="pare-id">
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            Father/Guardian's Details
        </div>
        <div class="col-md-6 col-xs-12">
            Mother's Details
        </div>
    </div>

    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="nameFather" id="nameF" class="form-control"
                       placeholder="" required>
            </div>
        </div>
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="nameMother" id="nameM" class="form-control"
                       placeholder="" required>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Address<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="addressFather" id="addressF" class="form-control"
                       placeholder="" required>
            </div>
        </div>
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Address<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="addressMother" id="addressM" class="form-control"
                       placeholder="" required>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Telephone<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="telFather" id="telF" class="form-control"
                       placeholder="" required>
            </div>
        </div>
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Telephone<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="telMother" id="telM" class="form-control"
                       placeholder="" required>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="emailFather" id="emailF" class="form-control"
                       placeholder="" required>
            </div>
        </div>
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input name="emailMother" id="emailM" class="form-control"
                       placeholder="" required>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Proffession<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input type="hidden" id="profCode" name="fatherProffession"/>
                <input type="hidden" id="profName">
                <div id="father-div" class="form-control"
                     select2-url="<c:url value="/protected/pageProffession"/>" >

                </div>
            </div>

        </div>
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Proffession<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input type="hidden" id="proffCode" name="motherProffession"/>
                <input type="hidden" id="proffName">
                <div id="mother-div" class="form-control"
                     select2-url="<c:url value="/protected/pageProffession"/>" >

                </div>
            </div>

        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Parent/Guardian<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <select class="form-control" id="gurd" name="parGur">
                    <option value="Parent">Parent</option>
                    <option value="Guardian">Guardian</option>
                </select>
            </div>

        </div>

        <div class="col-md-6 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">ID<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <input type="text" id="id-par" name="idNo"  class="form-control"
                       placeholder="" required>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 1em">
        <div class="col-md-4 col-xs-12">
            <label for="noOfUnits" class="control-label col-md-4">Email Required<span class="required">*</span></label>

            <div class="col-md-8 col-xs-12">
                    <input type="checkbox" name="mailReq" class="form-check-input" id="emailAddress">
                </div>
            </div>


    </div>

    <div class='spacer'></div>

    <div class="form-group" style="margin-top: 2em">
        <div class="col-md-offset-1 col-md-6 col-xs-12 form-required">
            <label for="file" class="control-label col-md-5" id="client-photo">
                Photo</label>
            <div class="col-md-7 col-xs-12">
                <div class="kv-avatar center-block" style="height:15em;width: 200px">
                    <input name="file" type="file" id="avatar" class="file-loading">

                </div>
            </div>
        </div>
    </div>



    <div class="row" style="margin-top: 2em">
        <div class="col-md-1">
            <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

        </div>
        <div class="col-md-1">

            <button type="button" class="btn btn-default pull-right" data-dismiss="modal">
                Cancel
            </button>
        </div>
    </div>
</form>
</div>
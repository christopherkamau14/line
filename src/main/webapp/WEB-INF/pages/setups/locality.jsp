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
        Locale Details
    </div>
    <div id="country" style="margin-bottom: 1em">
    <button id="btn-add-country" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="country-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Country Name</td>
                <td>Country Description</td>
                <td>Continent</td>
                <td></td>
                <td></td>
            </thead>
        </table>
    </div>
    </div>
    <div id="county" style="margin-bottom: 1em">
    <button id="btn-add-county" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="county-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>County Name</td>
                <td>County Description</td>
                <td>Country</td>
           <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <div id="sub-county" style="margin-bottom: 1em">

    <button id="btn-add-subcou" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="sub-county-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Sub-County Name</td>
                <td>Sub-County Description</td>
                <td>County</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <div id="location" style="margin-bottom: 1em">

    <button id="btn-add-location" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="location-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Location Name</td>
                <td>Location Description</td>
                <td>Sub-County</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <div id="sub-location" style="margin-bottom: 1em">

    <button id="btn-add-subloc" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="sub-loc-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Sub-Location Name</td>
                <td>Sub-Location Description</td>
                <td>Sub-Location</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <div id="village" style="margin-bottom: 1em">

    <button id="btn-add-vil" class="btn btn-info pull-right">New</button>

    <div style="margin-top: 1em">
        <table id="village-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Village Name</td>
                <td>Village Description</td>
                <td>Sub-Location</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
</div>
</div>
<div class="modal fade" id="countryModal" tabindex="-1" role="dialog"
     aria-labelledby="countryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="countryModalLabel">
                    Add Country Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="country-form">
                <input type="hidden" name="couCode" id="c-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Country Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="couName" id="country-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                    <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Country Desc<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="couDesc" id="country-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Continent<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="couContinent" id="continent-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

            <div class="row" style="margin-top: 1em">
                <div class="col-md-2">
                    <input type="submit" id="btn-save-country" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

<div class="modal fade" id="countyModal" tabindex="-1" role="dialog"
     aria-labelledby="countyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="countyModalLabel">
                    Add County Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="county-form">
                 <input type="hidden" name="countyCode" id="co-id">
                    <input type="hidden" name="country" id="country-id">

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">County Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="countyName" id="county-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">County Description<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="countyDesc" id="county-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-county" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

<div class="modal fade" id="subcountyModal" tabindex="-1" role="dialog"
     aria-labelledby="subcountryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="subcountyModalLabel">
                    Add Sub-County Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="sub-county-form">
                    <input type="hidden" name="subCode" id="sub-id">
                    <input type="hidden" name="county" id="county-id">
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Sub-County Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="subName" id="sub-county-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Sub-County Desc<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="subDesc" id="sub-country-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-location" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

<div class="modal fade" id="locationModal" tabindex="-1" role="dialog"
     aria-labelledby="locationModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="locationModalLabel">
                    Add Location Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="location-form">
                  <input type="hidden" name="louCode" id="l-id">
                    <input type="hidden" name="subCounty" id="sub-county-id">
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Location Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="louName" id="location-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Location Desc<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="louDesc" id="loc-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

<div class="modal fade" id="sublocationModal" tabindex="-1" role="dialog"
     aria-labelledby="sublocationModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="sublocationModalLabel">
                    Add Sub-Location Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="sub-location-form">
                    <input type="hidden" name="subLouCode" id="sl-id">
                    <input type="hidden" name="location" id="loc-id">


                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Sub-Location Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="subLouName" id="sub-location-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Sub-Location Desc<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="subLouDesc" id="sub-location-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-sub-loc" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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
<div class="modal fade" id="villageModal" tabindex="-1" role="dialog"
     aria-labelledby="villageModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="villageModalLabel">
                    Add Village Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="village-form">
                  <input type="hidden" name="vilCode" id="v-id">
                    <input type="hidden" id="sub-loc-id" name="subLocation"/>

                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Village Name<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="vilName" id="village-name" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-9 col-xs-12">
                            <label for="noOfUnits" class="control-label col-md-4">Village Desc<span class="required">*</span></label>

                            <div class="col-md-6 col-xs-12">
                                <input name="vilDesc" id="village-desc" class="form-control"
                                       placeholder="" required>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 1em">
                        <div class="col-md-2">
                            <input type="submit" id="btn-save-village" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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

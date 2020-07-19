<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/18/2020
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/staff/workers.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<script>var staffCode = "${staffId}"</script>
<div class="x_panel">
        <div class="x_title">
            Staff Details
    </div>
    <form class="form-horizontal" id="staff-form" style="margin-top: 2em">
        <input type="hidden" name="staffCode" id="staff-id">

        <div class="row" style="margin-top: 2em">
            <div class="col-md-1">
                <input type="submit" id="btn-save-parent" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Title<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="title-code" name="titles"/>
                    <input type="hidden" id="title-name">
                    <div id="title-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageTitle"/>" >

                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">WEF Date<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="startDate" id="start"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Name<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="name" id="staff-name" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">WET Date<span class="required">*</span></label>
                <div class="col-md-6 col-xs-12">
                    <div class='input-group date datepicker-input'>
                        <input type='text' class="form-control pull-right" name="endDate" id="end"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="houseId" class="control-label col-md-4">
                    Gender<span class="required">*</span></label>
                <div class="col-md-7 col-xs-12">
                    <div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="id-male" name="gender" value="Male">
                        <label class="custom-control-label" for="id-male">Male</label>


                        <input type="radio" class="custom-control-input" id="id-female" name="gender" value="Female">
                        <label class="custom-control-label" for="id-female">Female</label>

                    </div>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Address<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="address" id="address" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Responsibility<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input type="hidden" id="res-code" name="responsibility"/>
                    <input type="hidden" id="res-name">
                    <div id="res-div" class="form-control"
                         select2-url="<c:url value="/protected/academics/pageWorkerResponsibility"/>" >

                    </div>
                </div>

            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Telephone<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="phoneNumber" id="phone-number" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">ID No.<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="idNo" id="id-no" class="form-control"
                           placeholder="" required>
                </div>
            </div>
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <input name="email" id="email" class="form-control"
                           placeholder="" required>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 1em">
            <div class="col-md-6 col-xs-12">
                <label for="noOfUnits" class="control-label col-md-4">Status<span class="required">*</span></label>

                <div class="col-md-6 col-xs-12">
                    <select class="form-control" id="status" name="status">
                        <option>Select</option>
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </div>

            </div>

            <div class="col-md-offset-1 col-md-4 col-xs-12 form-required">
                <label for="avatar" class="control-label col-md-1" id="client-photo">
                    Photo</label>
                <div class="kv-avatar center-block" style="height:15em;width: 200px">
                    <input name="file" type="file" id="avatar" class="file-loading">

                </div>
            </div>
        </div>

    </form>



    <div style="margin-top: 5em">
        <table id="workers-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Courtesy</td>
                <td>Name</td>
                <td>Address</td>
                <td>TelNo</td>
                <td>Duty</td>
                <td>Staff No</td>
                <td>Adm Date</td>
                <td>Gender</td>
                <td>Email</td>
                <td>Status</td>
                <td></td>
                <td></td>

            </tr>
            </thead>
        </table>
    </div>

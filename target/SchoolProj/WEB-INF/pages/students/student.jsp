<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/2/2020
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/students/students.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>
<script>var stdCode=${studentId}</script>


        <div class="x_panel">
            <form id="stud-form" style="margin-top: 1em">
                <input type="submit" class="btn btn-info pull-right" id="btn-save-student" style="margin-top: 1em" value="Save" style="margin-top: 1em">
                <input type="hidden" id="student-id" name="stId">
        <div class="" role="tabpanel" data-example-id="togglable-tabs">
            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab_content3"
                                                          role="tab" id="main" data-toggle="tab" aria-expanded="false">Main</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content1"
                                                    id="contacts" role="tab" data-toggle="tab" aria-expanded="true">Contact Details</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content4"
                                                          role="tab" id="details" data-toggle="tab" aria-expanded="true">Other Details</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content2"
                                                    id="docs" role="tab" data-toggle="tab" aria-expanded="true">Documents</a>
                </li>

            </ul>
            <div id="myTabContent" class="tab-content">

                <div role="tabpanel" class="tab-pane fade active in"
                     id="tab_content3" aria-labelledby="main">
                    <div class="x_panel">
                        <div class="row">

                        <div class="form-group form-required">
                            <div class="col-md-4 col-xs-12">
                                <label for="houseId" class="control-label col-md-3">
                                    Adm No.<span class="required">*</span></label>
                                <div class="col-md-9 col-xs-12">
                                    <input type="text" name="admNo" id="adm" class="form-control"
                                           placeholder="" required>
                                </div>
                            </div>

                        <div class="col-md-4 col-xs-12">


                            </div>

                            <div class="col-md-4 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Fee Category<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="fee-cat" name="feeCategory"/>
                                    <input type="hidden" id="fee-name">
                                    <div id="fee-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageFees"/>" >

                                    </div>


                                </div>
                                <div class="col-md-1 col-xs-12">
                                    <a href="<c:url value="/protected/fee"/>" class="btn btn-info btn-xs">New
                                    </a>
                                </div>
                            </div>
                        </div>
                        </div>
                        <div style="margin-top: 1em" class="row">

                            <div class="form-group form-required">
                                <div class="col-md-4 col-xs-12">
                                    <label for="houseId" class="control-label col-md-3">
                                        Ref(UPI)<span class="required">*</span></label>
                                    <div class="col-md-9 col-xs-12">
                                        <input type="text" name="upi" id="upi" class="form-control"
                                               placeholder="" required>
                                    </div>
                                </div>

                                <div class="col-md-4 col-xs-12">
                                    <div class="col-md-4 col-xs-12 clearfix">
                                        <a href="${pageContext.request.contextPath}/protected/details/reports" class="btn btn-inverse btn-lg">Detailed Reports</a>

                                    </div>
                                </div>

                                <div class="col-md-offset-8 col-md-4 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-4">Dorm/House<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                        <input type="hidden" id="dorm" name="dorm"/>
                                        <input type="hidden" id="house">
                                        <div id="house-div" class="form-control"
                                             select2-url="<c:url value="/protected/pageDorm"/>" >

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style="margin-top: 1em" class="row">

                            <div class="form-group form-required">
                                <div class="col-md-4 col-xs-12">
                                    <label for="houseId" class="control-label col-md-3">
                                        Name<span class="required">*</span></label>
                                    <div class="col-md-9 col-xs-12">
                                        <input type="text" name="name" id="name" class="form-control"
                                               placeholder="" required>
                                    </div>
                                </div>

                                <div class="col-md-4 col-xs-12">
                                    <div class="col-md-4 col-xs-12 clearfix">
                                        <button type="button" class="btn btn-inverse btn-lg" id="batch" >Batch Student Photo</button>

                                    </div>
                                </div>

                                <div class="col-md-4 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-4">Fee Balance<span class="required">*</span></label>
                                    <div>

                                    </div>

                                </div>
                            </div>
                        </div>
                        <div style="margin-top: 1em" class="row">

                            <div class="form-group form-required">
                                <div class="col-md-4 col-xs-12">
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

                                <div class="col-md-4 col-xs-12">
                                    <div class="col-md-4 col-xs-12 clearfix">
                                        <button type="button" class="btn btn-inverse btn-lg" id="student" >Export Photos</button>

                                    </div>
                                </div>

                                <div class="col-md-4 col-xs-12">
                                    <div class="col-md-4 col-xs-12 clearfix">
                                        <a href="<c:url value="/protected/details/leaveouts"/>" class="btn btn-inverse btn-lg" id="studen" >Issue Leaveouts</a>

                                    </div>

                                </div>
                            </div>
                        </div>
                        <div style="margin-top: 2em" class="row">

                            <div class="form-group form-required">

                                <div class="col-md-4 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-4">Class Admitted<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                        <input type="hidden" id="class" name="forms"/>
                                        <input type="hidden" id="dm">
                                        <div id="class-div" class="form-control"
                                             select2-url="<c:url value="/protected/pageClass"/>" >

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-2">Term<span class="required">*</span></label>

                                    <div class="col-md-5 col-xs-12">
                                        <input type="hidden" id="term" name="term"/>
                                        <input type="hidden" id="term-name">
                                        <div id="term-div" class="form-control"
                                             select2-url="<c:url value="/protected/pageTerm"/>" >

                                        </div>
                                    </div>
                                </div>

                                    <div class="col-md-4 col-xs-12">
                                        <label for="noOfUnits" class="control-label col-md-2">Parent<span class="required">*</span></label>

                                        <div class="col-md-5 col-xs-12">
                                            <input type="hidden" id="par" name="parent"/>
                                            <input type="hidden" id="pr">
                                            <div id="parent-div" class="form-control"
                                                 select2-url="<c:url value="/protected/pageParent"/>" >

                                            </div>


                                        </div>
                                        <div class="col-md-2 col-xs-12">
                                            <a href="<c:url value="/protected/getParentForm"/>" class="btn btn-info btn-xs">New
                                            </a>                                        </div>
                                        <div class="col-md-2 col-xs-12">

                                            <button type="button" class="btn btn-info btn-xs" id="btn-search-pr">Search</button>

                                        </div>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix">
                            <button type="button" class="btn btn-inverse pull-right" id="btnstats">View Stats</button>
                        </div>

                    </div>
                </div>

                <div role="tabpanel" class="tab-pane fade"
                     id="tab_content1" aria-labelledby="contacts">
                    <div class="x_panel">
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Nationality<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="country-id" name="country"/>
                                    <input type="hidden" id="coun-desc">
                                    <div id="nation-div" class="form-control"
                                         select2-url="<c:url value="/protected/getCountry"/>" >

                                    </div>
                                </div>
                            </div>
                                <div class="col-md-6 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-4">Address<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                        <textarea name="address" id="address" class="form-control"
                                                  placeholder="" required></textarea>
                                    </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">County<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="county-id" name="county"/>
                                    <input type="hidden" id="county-desc">
                                    <div id="county-div" class="form-control"
                                         select2-url="<c:url value="/protected/getCounty"/>" >

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Sub-County<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="sub-county-id" name="subCounty"/>
                                    <input type="hidden" id="sub-count-desc">
                                    <div id="sub-county-div" class="form-control"
                                         select2-url="<c:url value="/protected/getSubCounty"/>" >

                                    </div>
                            </div>
                            </div>
                                <div class="col-md-6 col-xs-12">
                                    <label for="noOfUnits" class="control-label col-md-4">Email<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                        <input name="studEmail" id="email" class="form-control"
                                                  placeholder="" required>
                                    </div>
                                </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Location<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="loc-id" name="location"/>
                                    <input type="hidden" id="location-desc">
                                    <div id="location-div" class="form-control"
                                         select2-url="<c:url value="/protected/getLocation"/>" >

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Parent Contact<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input name="parentContact" id="parnumber" class="form-control"
                                           placeholder="" required>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Sub-Location<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="sub-loc-id" name="subLocation"/>
                                    <input type="hidden" id="sub-loc-desc">
                                    <div id="sub-location-div" class="form-control"
                                         select2-url="<c:url value="/protected/getSubLocation"/>" >

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Student Contact<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input name="studentContact" id="stdnumber" class="form-control"
                                           placeholder="" required>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Village<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="village-id" name="village"/>
                                    <input type="hidden" id="village-desc">
                                    <div id="village-div" class="form-control"
                                         select2-url="<c:url value="/protected/getVillage"/>" >

                                    </div>
                                </div>
                            </div>

                        </div>
                </div>
                </div>
                <div role="tabpanel" class="tab-pane fade"
                     id="tab_content4" aria-labelledby="details">
                    <div class="x_panel">
<div class="row">

    <div class="col-md-6 col-xs-12">
    <div class="form-group">
        <label for="reg-wet" class="col-md-4 control-label" id="lbl-doa">Date of Admission<span class="required">*</span></label>

        <div class="col-md-6 col-xs-12">
            <div class='input-group date datepicker-input'>
                <input type='text' class="form-control pull-right" name="admDate" id="doa"/>
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="reg-wet" class="col-md-4 control-label" id="lbl-doc">Date of Completion<span class="required">*</span></label>

            <div class="col-md-6 col-xs-12">
                <div class='input-group date datepicker-input'>
                    <input type='text' class="form-control pull-right" name="completionDate" id="docm"/>
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </div>
                </div>
            </div>
                </div>
            </div>
        </div>
                        <div class="row" style="margin-top: 1em">

                            <div class="col-md-6 col-xs-12">
                                <div class="form-group">
                                    <label for="reg-wet" class="col-md-4 control-label" id="lbl-dob">Date of Birth<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                        <div class='input-group date datepicker-input'>
                                            <input type='text' class="form-control pull-right" name="birthDate" id="dob"/>
                                            <div class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Religion<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="denCode" name="religion"/>
                                    <input type="hidden" id="denName">
                                    <div id="denomination-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageReligion"/>" >

                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">

                            <div class="col-md-6 col-xs-12">
                                <div class="form-group">
                                    <label for="reg-wet" class="col-md-4 control-label" id="cert">Birth Cert No.<span class="required">*</span></label>

                                    <div class="col-md-6 col-xs-12">
                                            <input type='text' class="form-control" name="birthCertNo" id="birthcert"/>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-3">Index No.<span class="required">*</span></label>
                                <div class="col-md-4 col-xs-12">
                                    <input type='text' class="form-control" name="indexNo" id="index"/>

                                </div>
                                <div class="col-md-3 col-xs-12">
                                    <input type="hidden" id="yearCode" name="year"/>
                                    <input type="hidden" id="yearName">
                                    <div id="year-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageYear"/>" >

                                    </div>
                                </div>
                                <div class="col-md-2 col-xs-12">
                                    <button class="btn btn-info btn-xs" id="indexSearch">Search</button>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Source<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="source" name="sources"/>
                                    <input type="hidden" id="souname">
                                    <div id="source-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageSources"/>" >

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">KCPE Marks<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                        <input name="kcpeMarks" id="kcpe" class="form-control"
                                                  placeholder="" required>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Health<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="heacode" name="health"/>
                                    <input type="hidden" id="heaname">
                                    <div id="health-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageHealth"/>" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Primary School<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                        <input name="primarySchool" id="prischool" class="form-control"
                                                  placeholder="" required>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">

                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">KCPE Grade<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                        <input name="kcpeGrade" id="grade" class="form-control"
                                                  placeholder="" required>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12">
                                <label for="noOfUnits" class="control-label col-md-4">Student Status<span class="required">*</span></label>

                                <div class="col-md-6 col-xs-12">
                                    <input type="hidden" id="status" name="studStatus"/>
                                    <input type="hidden" id="statname">
                                    <div id="status-div" class="form-control"
                                         select2-url="<c:url value="/protected/pageStatus"/>" >

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-6 col-xs-1">
                            <label for="noOfUnits" class="control-label col-md-4">Health Details<span class="required">*</span></label>

                            <div>

                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade"
                     id="tab_content2" aria-labelledby="docs">
                    <div class="x_panel">
                        <div class="row" style="margin-top: 1em">

                            <div class="col-md-6 col-xs-12">

                                    <div class="col-md-4 col-xs-12">
                                        <button type="button" class="btn btn-inverse btn-lg" id="Load Batch Photo" >Load Batch Photo</button>

                                    </div>

                                </div>


                                <div class="col-md-6 col-xs-12 form-required">
                                    <label for="file" class="control-label col-md-5" id="client-photo">
                                        Photo</label>
                                    <div class="col-md-7 col-xs-12">
                                        <div class="kv-avatar center-block" style="height:15em;width: 200px">
                                            <input name="file" type="file" id="stud-avatar" class="file-loading">

                                        </div>
                                    </div>
                                </div>

                            </div>
                        <div class="row" style="margin-top: 1em">
                            <div class="col-md-4">
                            <button type="button" class="btn btn-inverse" id="btn-upload-stdocs" >Upload Documents</button>
                            </div>
                            <div class="col-md-4">
                                <a href="<c:url value='/protected/stddocs'/> " class="btn btn-inverse">View Documents</a>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>


    </form>
    <div style="margin-top: 1em" class="clearfix">
        <div class="row">
            <div class="col-md-2">
                <label for="nMe">
                    Name</label>

                <input type="text" name="" id="nMe" class="form-control"
                       placeholder="" required>
            </div><div class="col-md-2">
            <label for="admN">
                Adm No.</label>

            <input type="text" name="" id="admN" class="form-control"
                   placeholder="" required>
        </div>
            <div class="col-md-2">
                <label for="mobNo">
                    Mobile No.</label>

                <input type="text" name="" id="mobNo" class="form-control"
                       placeholder="" required>
            </div>
            <div class="col-md-2">
                <label for="classSea">
                    Class</label>

                <input type="text" name="" id="classSea" class="form-control"
                       placeholder="" required>
            </div>

            <div class="col-md-4">
                <div class="custom-control custom-radio custom-control-inline">
                    <label class="custom-control-label" for="suspended">Suspends</label>

                    <input type="radio" class="custom-control-input" id="suspended" name="budSet" value="suspended">

                    <label class="custom-control-label" for="transferred">Transfers</label>

                    <input type="radio" class="custom-control-input" id="transferred" name="budSet" value="transferred">

                    <label class="custom-control-label" for="expelled">Expels</label>

                    <input type="radio" class="custom-control-input" id="expelled" name="budSet" value="expelled">

                    <label class="custom-control-label" for="graduated">Graduated</label

                    <input type="radio" class="custom-control-input" id="graduated" name="budSet" value="graduated">
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 1em">
            <button type="button" id="std-search" class="btn btn-default">Search</button>
        </div>
    </div>
    <div style="margin-top: 1em">
        <table id="students-tbl" class="table table-hover table-bordered">
            <thead>
            <tr>
                <td>Adm No</td>
                <td>Name</td>
                <td>D.O.B</td>
                <td>D.O.A</td>
                <td>Resident Dorm</td>
                <td>Form</td>
                <td>Stream</td>
                <td>Current Class</td>
                <td>Graduate</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </thead>
        </table>
    </div>
    </div>
    <div class="modal fade" id="statsModal" tabindex="-1" role="dialog"
         aria-labelledby="statsModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content bg-orange">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="statsModalLabel">
                        Statistics
                    </h4>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <div class="col-md-4">
                            Total Students:
                        </div>
                        <div class="col-md-4">
                            Male:
                        </div>
                        <div class="col-md-4">
                            Female:
                        </div>
                    </div>
                    <div style="margin-top: 1em" class="row">
                        <div class="col-md-4">
                            Admitted: Today:
                        </div>
                        <div class="col-md-4">
                            This Month:
                        </div>
                        <div class="col-md-4">
                            Year:
                        </div>
                    </div>
                        <div style="margin-top: 1em" class="row">
                            <div class="col-md-6">
                                Documents Captured:
                            </div>
                            <div class="col-md-6">
                                Biometrics Captured:
                            </div>
                        </div>
                    </div>


                <div class="modal-footer">

                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        Cancel
                    </button>
                </div>
            </div>
        </div>
    </div>
<div class="modal fade" id="parentsModal" tabindex="-1" role="dialog"
     aria-labelledby="parentsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="parentsModalLabel">
                    Parents Details
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="parent-form">

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
                                 select2-url="<c:url value="/protected/home"/>" >

                            </div>
                        </div>

                    </div>
                    <div class="col-md-6 col-xs-12">
                        <label for="noOfUnits" class="control-label col-md-4">Proffession<span class="required">*</span></label>

                        <div class="col-md-6 col-xs-12">
                            <input type="hidden" id="proffCode" name="motherProffession"/>
                            <input type="hidden" id="proffName">
                            <div id="mother-div" class="form-control"
                                 select2-url="<c:url value="/protected/home"/>" >

                            </div>
                        </div>

                    </div>
                </div>
                <div class="row" style="margin-top: 1em">
                    <div class="col-md-6 col-xs-12">
                        <label for="noOfUnits" class="control-label col-md-4">Parent/Guardian<span class="required">*</span></label>

                        <div class="col-md-6 col-xs-12">
                            <select class="form-control" id="pargur" name="parGur">
                                <option value="Parent">Parent</option>
                                <option value="Guardian">Guardian</option>

                            </select>
                        </div>

                    </div>
                    <div class="col-md-4 col-xs-12">
                        <div class="col-md-8 col-xs-12">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="emailAddress">
                                <label class="form-check-label" for="emailAddress">Email Required</label>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-2 col-xs-12">

                    <div style="margin-top: 1em">
                        <a href="<c:url value='/protected/parent'/> " class="btn btn-default pull-right">Parents</a>

                    </div>
                    </div>
                </div>
                </div>
<div class="row" style="margin-top: 1em">
                    <div class="col-md-offset-4 col-md-4 col-xs-12 form-required">

                        <div class="col-md-7 col-xs-12">
                            <div class="" style="width: 200px">
                                <div class="kv-avatar center-block" style="width:200px">
                                    <img  src="<c:url value='/load'/> " style="height:15em;width:200px">
                                </div>
                                <div class="col-md-offset-4" style="margin-top: 0.5em">
                                <label class="btn btn-default btn-file clearfix" id="prem">
                                    Load <input type="file" id="btn-import-trans" style="display: none;">
                                </label>
                                </div>
                                </div>
                        </div>

                        </div>

                </div>
            <div class="row">
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
<div class="modal fade" id="feesModal" tabindex="-1" role="dialog"
     aria-labelledby="feesModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="feesModalLabel">
                    ADD FEE CATEGORY
                </h4>
            </div>
 <form class="form-horizontal" id="fee-form">

                    <div class="col-md-9 col-xs-12 form-group" style="margin-top: 1em">
                        <label for="noOfUnits" class="control-label col-md-4">Category Name<span class="required">*</span></label>

                        <div class="col-md-8 col-xs-12">
                            <input name="catName" id="catname" class="form-control"
                                   placeholder="" required>
                        </div>
                    </div>

         <div class="col-md-offset-3 col-md-6 col-xs-12 form-group">
             <div class="form-check">
                 <label for="noOfUnits" class="control-label col-md-6">Default Category<span class="required">*</span></label>
                 <div class="col-md-6 col-xs-12">

                 <input type="checkbox" name="defaultCat" class="form-check-input" id="default-cat">
                 </div>
             </div>
         </div>
                    <div class="col-md-9 col-xs-12 form-group">
                        <label for="noOfUnits" class="control-label col-md-4">Fee Description<span class="required">*</span></label>

                        <div class="col-md-8 col-xs-12">
                            <input name="feeDesc" id="fee-desc" class="form-control"
                                   placeholder="" required>
                        </div>
                    </div>

            <div class="col-md-9 col-xs-12 form-group">
                <label for="noOfUnits" class="control-label col-md-4">Successor Category<span class="required">*</span></label>

                <div class="col-md-8 col-xs-12">
                    <input name="catSuccessor" id="successor-cat" class="form-control"
                           placeholder="" required>
                </div>
            </div>


    </form>
            <div class="modal-footer">
                <div class="row">
                    <div class="col-md-2">
                        <input type="submit" id="btn-save-category" class="btn btn-success pull-right" style="margin-right: 10px" value="Save">

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
<div class="modal fade" id="studentdocModal" tabindex="-1" role="dialog"
     aria-labelledby="studentdocModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="student-doc-form" class="form-horizontal" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="studentdocModalLabel">Upload Student Document</h4>
                </div>
                <div class="modal-body">

                    <input type="hidden" id="student-doc-id" name="docId"/>

                    <div class="form-group">
                        <label for="cou-name" class="col-md-3 control-label">File Ref. No</label>

                        <div class="col-md-6">
                            <input type="text" class="form-control" id="upload-sht-id"
                                   name="fileId">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="noOfUnits" class="control-label col-md-4">Documents<span class="required">*</span></label>

                        <div class="col-md-6 col-xs-12">
                            <input type="hidden" id="doccode" name="document"/>
                            <input type="hidden" id="docname">
                            <div id="doc-div" class="form-control"
                                 select2-url="<c:url value="/protected/pageDocs"/>" >

                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="brn-id" class="col-md-4 control-label">Document</label>

                        <div class="col-md-8">
                            <div class="input-group col-xs-12">
                                <input name="file" type="file" id="std-avatar" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input  value="Upload"
                            type="submit" class="btn btn-success">

                    </input>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        Close</button>
                </div>
            </div>
        </form>
    </div>
</div>



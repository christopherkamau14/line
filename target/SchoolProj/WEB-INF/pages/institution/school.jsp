<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value='/js/modules/institution/institution.js' /> "></script>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/19/2020
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<div class="x_panel">
    <div class="x_title">
        School Definition
    </div>
    <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissable">
            <p>${error}</p>
        </div>
    </c:if>

    <c:if test="${empty fresh}">
        <form action="${pageContext.request.contextPath}/protected/institution/deleteSchool">
            <input type="hidden" id="id-school" name="school" value="${school.schoolCode}">
            <input type="submit" id="del-btn"  class="btn btn-danger" value="Remove">
        </form>
    </c:if>
    <form class="form-horizontal"
    method="post" action="${pageContext.request.contextPath}/protected/institution/createInstitution" name="myForm" id="inst-form" enctype="multipart/form-data">
    <hr>
<c:if test="${not empty fresh}">
    <input type="submit" id="school-btn"  class="btn btn-success" value="Save">
</c:if>


        <div class="form-group">
    <div class="col-md-6 col-xs-12">
        <label for="SclName" class="control-label col-md-5">School Name</label>
        <c:if test="${not empty fresh}">
        <div class="col-md-7 col-xs-12">
            <input type="text" id="sc-n" name="schoolName" class="form-control" placeholder="" required/>
        </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.schoolName}"></c:out></p>
        </div>
        </c:if>
    </div>
    <div class="col-md-6 col-xs-12">
        <label for="sclShtDesc" class="control-label col-md-5">Physical Address</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <input type="text" name="physicalAddress" id="sc-desc" class="form-control" placeholder="" required/>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.physicalAddress}"></c:out></p>
        </div>
        </c:if>
    </div>
        </div>
<div class="form-group" style="margin-top: 1em">
    <div class="col-md-6 col-xs-12">
        <label for="sclMobile" class="control-label col-md-5">Mobile</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <input type="text" name="schoolMobile" class="form-control" placeholder="" required/>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.schoolMobile}"></c:out></p>
        </div>
        </c:if>
    </div>

    <div class="col-md-6 col-xs-12">
        <label for="orgPhone" class="control-label col-xs-5">Phone</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <input type="text" name="schoolPhone" class="form-control" placeholder="" required/>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.schoolPhone}"></c:out></p>
        </div>
        </c:if>
    </div>

</div>

<div class="form-group">
    <div class="col-md-6 col-xs-12">
        <label for="schoolFax" class="control-label col-md-5">Postal Address</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <textarea name="address" class="form-control" placeholder="" required></textarea>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.address}"></c:out></p>
        </div>
        </c:if>
    </div>
</div>
    <div class="col-md-6 col-xs-12">
        <label for="orgWebsite" class="control-label col-md-5">WebSite</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <input type="text" name="schoolWebsite" class="form-control" placeholder="" required/>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.schoolWebsite}"></c:out></p>
        </div>
        </c:if>
    </div>

<div class="form-group">

    <div class="col-md-6 col-xs-12">
        <label for="currencyName" class="control-label col-md-5">Date of Incorporation</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <div class='input-group date datepicker-input'>
                    <input name="dateIncorp" type="text" class="form-control"/>
                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
                </div>
            </div>
        </c:if>
        <c:if test="${empty fresh}">
        <div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.dateIncorp}"></c:out></p>

        </div>
        </c:if>
    </div>
</div>
        <div class="form-group">
    <div class="col-md-6 col-xs-12">
        <label for="currencyName" class="control-label col-md-5">Mission</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                    <input name="mission" type="text" class="form-control"/>

            </div>
        </c:if>
        <c:if test="${empty fresh}">
            <div class="col-md-7 col-xs-12">
                <p class="form-control-static"> <c:out value="${school.mission}"></c:out></p>

            </div>
        </c:if>
    </div>
        </div>

        <div class="form-group">
            <div class="col-md-6 col-xs-12">
                <label for="currencyName" class="control-label col-md-5">Motto</label>
                <c:if test="${not empty fresh}">
                    <div class="col-md-7 col-xs-12">
                        <input name="motto" type="text" class="form-control"/>

                    </div>
                </c:if>
                <c:if test="${empty fresh}">
                    <div class="col-md-7 col-xs-12">
                        <p class="form-control-static"> <c:out value="${school.motto}"></c:out></p>

                    </div>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-6 col-xs-12">
                <label for="currencyName" class="control-label col-md-5">Vision</label>
                <c:if test="${not empty fresh}">
                    <div class="col-md-7 col-xs-12">
                        <input name="vision" type="text" class="form-control"/>

                    </div>
                </c:if>
                <c:if test="${empty fresh}">
                    <div class="col-md-7 col-xs-12">
                        <p class="form-control-static"> <c:out value="${school.vision}"></c:out></p>

                    </div>
                </c:if>
            </div>
        </div>

    <div class="col-md-6 col-xs-12">
        <label for="zipCode" class="control-label col-md-5">Email Address</label>
        <c:if test="${not empty fresh}">
            <div class="col-md-7 col-xs-12">
                <input type="text" name="emailAddress" class="form-control" placeholder="" required/>
            </div>
        </c:if>
<c:if test="${empty fresh}">
<div class="col-md-7 col-xs-12">
            <p class="form-control-static"> <c:out value="${school.emailAddress}"></c:out></p>
        </div>
    </c:if>
    </div>

<div class="col-md-6 col-xs-1"></div>


<div class="form-group" style="margin-top: 1em">

    <div class=" col-md-offset-4 col-md-6 col-xs-12">
        <label for="logo" class="control-label col-md-5">Logo</label>
            <div class="col-md-8 col-xs-12">
                <div class="kv-avatar center-block" style="width:200px">
                    <input name="file" type="file" id="avatar" cssClass="file-loading"/>
                </div>
            </div>

    </div>

</div>
    </form>
</div>

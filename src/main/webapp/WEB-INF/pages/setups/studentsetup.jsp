<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <ul style="list-style-type: none;">
                    <li><h5 style="font-weight: bolder;">Academic Setups</h5></li>
                    </a></li>
                    <li><a href="<c:url value="/protected/institution/school"/>">School Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/academics/department"/>">Department Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/academics/subjects"/>">Subject Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/academics/classsubjects"/>">Class Subject
                    </a></li>
                    <li><a href="<c:url value="/protected/block"/>">Block Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/class"/>">Class Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/term"/>">Term Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/academics/responsibility"/>">Responsibility Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/academics/titles"/>">Titles Setups
                    </a></li>

                </ul>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <ul style="list-style-type: none;">
                    <li><h5 style="font-weight: bolder;">Main Setups</h5></li>
                    <li><a href="<c:url value="/protected/fee"/>">Fee Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/house"/>">House Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/year"/>">Year Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/status"/>">Status Setups
                    </a></li>

                </ul>
            </div>

        </div>

        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <ul style="list-style-type: none;">
                    <li><h5 style="font-weight: bolder;">Other Setups</h5></li>
                    <li><a href="<c:url value="/protected/parent"/>">Parents Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/locale"/>">Locality Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/health"/>">Health Setups</a></li>
                    <li><a href="<c:url value="/protected/source"/>">Source Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/religion"/>">Religion Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/docs"/>">Document Setups
                    </a></li>
                    <li><a href="<c:url value="/protected/proffession"/>">Proffession Setups
                    </a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
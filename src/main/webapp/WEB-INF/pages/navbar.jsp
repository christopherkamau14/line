<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2/2/2020
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-3 left_col menu_fixed">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">
            <spring:message code='project.name'/>
        </div>

        <div class="clearfix"></div>

        <!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <li><a href="<c:url value="/protected/home"/>"><i class="fa fa-home"></i>Home</a></li>
                    <li><a><i class="fa fa-file"></i> File<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="#"/>">Enter Query</a></li>
                            <li><a href="<c:url value="#"/>">Back up</a></li>
                            <li><a href="<c:url value="#"/>">Restore</a></li>
                            <li><a href="<c:url value="#"/>">Change Password</a></li>
                            <li><a href="<c:url value="#"/>">Reset Password</a></li>
                        </ul>
                    </li>
                    <li><a ><i class="fa fa-cogs"></i>Settings<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="/protected/institution/school"/>">System Setups</a>
                            </li>
                            <li><a href="#"> Admin Setups</a>
                            </li>
                            <li><a href="#"> Account Setups</a>
                            </li>
                            <li><a href="<c:url value="/protected/setup"/>">Academic Setups</a>
                            <li><a href="<c:url value="#"/>">End of Term Process</a></li>


                        </ul>
                    </li>
                    <li><a><i class="fa fa-pencil"></i> Students Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="#"/>">Student Enquiry</a></li>
                            <li><a href="<c:url value="/protected/parent"/>">Parents</a></li>
                            <li><a href="<c:url value="/protected/student"/>">Student Details</a></li>
                            <li><a href="<c:url value="#"/>">Student Subjects</a></li>
                            <li><a href="<c:url value="#"/>">Student Movement</a></li>
                            <li><a href="<c:url value="#"/>">Daily Register</a></li>
                            <li><a><i class="fa fa-thumbs-up"></i>Trips<span class="fa fa-chevron-down"></span></a>
                                <ul>
                                    <li><a href="<c:url value="#"/>">Trip Setups</a></li>
                                    <li><a href="<c:url value="#"/>">Student Trips</a></li>

                                </ul>
                            </li>
                            <li><a href="<c:url value="#"/>">Calendar</a></li>
                            <li><a href="<c:url value="#"/>">Extra Curricullum</a></li>

                        </ul>
                    </li>
                    <li><a><i class="fa fa-users"></i> Staff Manager<span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a href="<c:url value="/protected/academics/teachers"/>">Teachers Details</a></li>
                            <li><a href="<c:url value="/protected/academics/workers"/>">Support Staff</a></li>
                            <li><a href="<c:url value="/protected/staff/teacherregister"/>">Teacher Attendance Register</a></li>
                            <li><a href="<c:url value="/protected/staff/staffregister"/>">Non Teaching Staff Register</a></li>
                            <li><a href="<c:url value="/protected/staff/teacherleaveouts"/>">Teacher's Leave Out</a></li>

                        </ul>
                    </li>

                    <li><a><i class="fa fa-certificate"></i>Exam Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a href="<c:url value="/protected/exams/examreg"/>">Exam Registration</a></li>
                            <li><a href="<c:url value="/protected/exams/examrec"/>">Marks Recording</a></li>
                            <li><a href="<c:url value="/protected/reports/process"/>">Exam Processing</a></li>
                            <li><a href="<c:url value="/protected/exams/merge"/>">Merge Exams</a></li>
                            <li><a a href="<c:url value="/protected/exams/combine"/>">Combine Subjects</a></li>
                            <li><a href="<c:url value="/protected/exams/comments"/>">Custom Teacher Comments</a></li>

                        </ul>
                    </li>
                    <li><a><i class="fa fa-hourglass"></i> Timetable Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a href="<c:url value="#"/>">Days Setup</a></li>
                            <li><a href="<c:url value="#"/>">Lessons Setup</a></li>
                            <li><a href="<c:url value="#"/>">Lessons Allocation</a></li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-book"></i> Library Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="#"/>">Stores</a></li>
                            <li><a href="<c:url value="#"/>">Stationery</a></li>
                            <li><a href="<c:url value="#"/>">Shelves</a></li>
                            <li><a href="<c:url value="#"/>">Library Category Setup</a></li>
                            <li><a href="<c:url value="#"/>">Book Classification</a></li>
                            <li><a href="<c:url value="#"/>">Stationery Registration</a></li>
                            <li><a href="<c:url value="#"/>">Book Registration</a></li>
                            <li><a href="<c:url value="#"/>">Stationery issuing</a></li>
                            <li><a href="<c:url value="#"/>">Book Issue</a></li>
                            <li><a href="<c:url value="#"/>">Book Cost</a></li>
                            <li><a href="<c:url value="#"/>">Items Given Out</a></li>
                            <%--<sec:authorize access="hasAnyAuthority('ACCESS_RECEIPT')">--%>
                            <%--<li><a href="<c:url value="/protected/uw/receipts/mobmoney"/>">Mobile Money Trans</a></li>--%>
                            <%--</sec:authorize>--%>
                            <%--<sec:authorize access="hasAnyAuthority('ACCESS_RECEIPT')">--%>
                            <%--<li><a href="<c:url value="/protected/uw/receipts/bankreceipts"/>">Bank Integration Receipts</a></li>--%>
                            <%--</sec:authorize>--%>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-money"></i>Fees Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a><i class="fa fa-cog"></i>Fees Setup<span class="fa fa-chevron-down"></span></a>
                                <ul>
                                    <li><a href="<c:url value="#"/>">Fees Categories</a></li>
                                    <li><a href="<c:url value="#"/>">Fees Grouping</a></li>
                                    <li><a href="<c:url value="#"/>">Fees Structure</a></li>
                                </ul>
                            </li>
                            <li><a href="<c:url value="#"/>">Recieve Fees Setups</a></li>
                            <li><a href="<c:url value="#"/>">Recieve External Funding</a></li>
                            <li><a><i class="fa fa-money"></i>Manage Receipts<span class="fa fa-chevron-down"></span></a>
                                <ul>
                                    <li><a href="<c:url value="#"/>">Cancel Receipts</a></li>
                                    <li><a href="<c:url value="#"/>">Refund Fees</a></li>
                                    <li><a href="<c:url value="#"/>">Refund Overpayment</a></li>
                                    <li><a href="<c:url value="#"/>">Bad Debt</a>></li>

                                </ul>
                            </li>
                            <li><a href="<c:url value="/gone"/>">Claims Reports</a></li>
                            <li><a href="<c:url value="/gone"/>">Manage Balances</a></li>
                            <li><a href="<c:url value="/gone"/>">Manage Pocket Money</a></li>
                            <li><a href="<c:url value="/gone"/>">Fees Reports</a></li>

                        </ul>
                    </li>
                    <li><a><i class="fa fa-dollar"></i>Finance Manager <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a><i class="fa fa-money"></i>Petty Cash<span class="fa fa-chevron-down"></span></a>
                                <ul>
                                    <li><a href="<c:url value="/gone"/>">Float Replenishment</a></li>
                                    <li><a href="<c:url value="/gone"/>">Petty Cash Payments</a></li>
                                    <li><a href="<c:url value="/gone"/>">Reports</a></li>
                                </ul>
                            </li>
                            <li><a href="<c:url value="/gone"/>">Budget</a></li>
                            <li><a href="<c:url value="/gone"/>">Supplier and Customers</a></li>
                            <li><a href="<c:url value="/gone"/>">Items and Service</a></li>
                            <li><a href="<c:url value="/gone"/>">Purchase Invoices</a></li>
                            <li><a href="<c:url value="/gone"/>">Supplier Payments</a></li>
                            <li><a href="<c:url value="/gone"/>">Supplier Balances</a></li>
                            <li><a href="<c:url value="/gone"/>">Sales</a></li>
                            <li><a href="<c:url value="/gone"/>">Customer Payments</a></li>
                            <li><a><i class="fa fa-suitcase"></i>Manage Assets<span class="fa fa-chevron-down"></span></a>
                                <ul>
                                    <li><a href="<c:url value="/gone"/>">Asset Registration</a></li>
                                    <li><a href="<c:url value="/gone"/>">Asset Issuing</a></li>
                                </ul>
                            </li>
                            <li><a href="<c:url value="/gone"/>">Period Close</a></li>


                        </ul>
                    </li>
                    <li><a><i class="fa fa-desktop"></i>Intergrations <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu nav-collapse">
                            <li><a href="<c:url value="/gone"/>">Letter and Memos</a></li>
                            <li><a href="<c:url value="/gone"/>">SMS</a></li>
                            <li><a href="<c:url value="/gone"/>">Email</a></li>
                            <li><a href="<c:url value="/gone"/>">Incidents</a></li>
                            <li><a href="<c:url value="/gone"/>">Open File</a></li>
                            <li><a href="<c:url value="/gone"/>">Notepad</a></li>
                            <li><a href="<c:url value="/gone"/>">Calculator</a></li>
                            <li><a href="<c:url value="/gone"/>">Sync Applications</a></li>
                            <li><a href="<c:url value="/gone"/>">Sync Allumni</a></li>
                            <li><a href="<c:url value="/gone"/>">Homepage Dashboard</a></li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-tachometer"></i>Reports <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="/gone"/>">Reports Setups</a></li>
                            <li><a href="<c:url value="/gone"/>">UW Reports</a></li>
                            <li><a href="<c:url value="/gone"/>">Accounts Reports</a></li>
                            <li><a href="<c:url value="/gone"/>">Claims Reports</a></li>
                            <li><a href="<c:url value="/gone"/>">Medical Reports</a></li>
                            <li><a href="<c:url value="/gone"/>">System Logs</a></li>
                        </ul>
                    </li>
                    <li><a><i class="fa fa-question"></i>Help <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="<c:url value="/gone"/>">Content</a></li>
                            <li><a href="<c:url value="/gone"/>">Tips</a></li>
                            <li><a href="<c:url value="/kilgor"/>">About the System</a></li>

                        </ul>
                    </li>
                </ul>
            </div>


        </div>
    </div>
</div>

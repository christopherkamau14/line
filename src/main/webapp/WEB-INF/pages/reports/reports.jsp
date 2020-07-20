<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value='/js/friendly.js' /> "></script>
<script type="text/javascript" src="<c:url value="/js/modules/reports/report.js"/>"></script>
<script>var ctx = "${pageContext.request.contextPath}"</script>

<div class="x_panel">
    <div class="x_title">
        Reports
    </div>
  <div class="row">
      <div class="col-md-4 col-xs-12">
          <div class="form-group">
              <label for="noOfUnits" class="control-label col-md-4">Student<span class="required">*</span></label>

              <div class="col-md-6 col-xs-12">
                  <input type="hidden" id="stud-id" name="student"/>
                  <input type="hidden" id="stud-name">
                  <div id="std-div" class="form-control"
                       select2-url="<c:url value="/protected/pageStudent"/>" >

                  </div>
              </div>

          </div>
      </div>
          <div id="reports">
          <div class="col-md-4 col-xs-12">

                      <div class="col-md-offset-4 col-md-4 col-xs-12 clearfix">
                          <form action="${pageContext.request.contextPath}/protected/details/report" method="get">
                              <input type="hidden" id="hid-stud-id" name="stdId">
                              <input type="submit" class="btn btn-inverse btn-lg" value="Detailed Report">
                          </form>
                      </div>

          </div>
          </div>
  </div>
</div>


<%@ include file="/common/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${!empty jsInfo}">
        <div class="text-center my-4">
            <h1 class="text-danger">JobSeeker Report</h1>
        </div>
        <table class="table table-bordered mx-auto" style="max-width: 800px;">
            <thead>
                <tr>
                    <th>JsId</th>
                    <th>JsName</th>
                    <th>JsAddress</th>
                    <th>Qualification</th>
                    <th>Download Resume</th>
                    <th>Download Photo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="js" items="${jsInfo}">
                    <tr>
                        <td>${js.jsId}</td>
                        <td>${js.jsName}</td>
                        <td>${js.jsAddrs}</td>
                        <td>${js.jsQlfy}</td>
                        <td><a href="download?id=${js.jsId}&type=resume">Download</a></td>
                        <td><a href="download?id=${js.jsId}&type=photo">Download</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="text-center my-4">
            <h1 class="text-danger">Records Not Found</h1>
        </div>
    </c:otherwise>
</c:choose>

<%@ include file="/common/footer.jsp" %>

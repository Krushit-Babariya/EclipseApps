<%@ include file="/common/header.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>

<div class="text-center my-4">
    <h1 class="text-primary">JobSeeker Registration Form</h1>
</div>

<frm:form modelAttribute="js" enctype="multipart/form-data" class="mx-auto" style="max-width: 600px;">
    <div class="form-group">
        <label for="jsName">JobSeeker Name:</label>
        <frm:input path="jsName" cssClass="form-control" id="jsName"/>
    </div>
    <div class="form-group">
        <label for="jsAddrs">JobSeeker Address:</label>
        <frm:input path="jsAddrs" cssClass="form-control" id="jsAddrs"/>
    </div>
    <div class="form-group">
        <label for="jsQlfy">JobSeeker Qualification:</label>
        <frm:input path="jsQlfy" cssClass="form-control" id="jsQlfy"/>
    </div>
    <div class="form-group">
        <label for="resume">Select Resume:</label>
        <frm:input type="file" path="resume" cssClass="form-control-file" id="resume"/>
    </div>
    <div class="form-group">
        <label for="photo">Select Photo:</label>
        <frm:input type="file" path="photo" cssClass="form-control-file" id="photo"/>
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
</frm:form>

<%@ include file="/common/footer.jsp" %>

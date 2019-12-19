<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<f:form action="${ctx}/book/endaddbook" method="post" class="text-center" modelAttribute="book">
    <%--书本名称:<f:input type="text" name="bookName"/><br/>--%>
    <%--书本价格<f:input type="text" name="price"/><br/>--%>
    书本名称: <f:input path="bookName"></f:input><f:errors path="bookName"></f:errors><br/>
    书本价格:<f:input path="price"></f:input><f:errors path="price"></f:errors><br/>
    <input type="submit" value="添加"/>
    <input type="reset">
</f:form>
</body>
</html>

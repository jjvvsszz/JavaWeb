<%--
  Created by IntelliJ IDEA.
  User: jjvvs
  Date: 21/11/22
  Time: PM 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String teste = "teste de variaveis";
%>
<html>
<head>
    <title>Fintech</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">
    <h1>Fintech</h1>
</a>
<a href="${pageContext.request.contextPath}/login.jsp">Entrar</a>
<br>
<a href="${pageContext.request.contextPath}/newaccount.jsp">Criar nova conta</a>
</body>
</html>

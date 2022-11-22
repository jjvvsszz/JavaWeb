<%@ page import="br.com.fintech.dao.LoginDao" %>
<%@ page import="br.com.fintech.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: jjvvs
  Date: 21/11/22
  Time: PM 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">
    <h1>Fintech</h1>
</a>
<form method="post" action="validate.jsp">
    <label>CPF: </label>
    <input type="text" name="cpf" >
    <label>Senha: </label>
    <input type="password" name="senha">
    <br>
    <input type="submit" value="Login">
</form>

<%
    LoginDao login = new LoginDao();
    String cpf = request.getParameter("cpf");
    String senha = request.getParameter("senha");
    if (cpf != null && cpf.length() == 11 && senha.length() > 8) {
        User user = login.login(cpf,senha);
        session.setAttribute("user", user);
    }
%>

</body>
</html>

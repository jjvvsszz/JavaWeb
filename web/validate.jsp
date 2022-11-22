<%@ page import="br.com.fintech.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: jjvvs
  Date: 22/11/22
  Time: PM 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    if (user != null) {
        response.sendRedirect("dashboard.jsp");
    } else {
        response.sendRedirect("erro_login.jsp");
    }
%>
</body>
</html>

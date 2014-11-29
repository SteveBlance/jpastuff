<%@ page
        import="com.codaconsultancy.dao.UseDAOImpl,com.codaconsultancy.dao.UserDAO,com.codaconsultancy.model.HibernateUtil"
        contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="com.codaconsultancy.entities.User" scope="request"/>
<jsp:setProperty name="user" property="*"/>
<%
    HibernateUtil.beginTransaction();
    UserDAO userDAO = new UseDAOImpl();
    java.util.List users = null;
    String command = request.getParameter("command");
    if (command != null) {
        if (command.equals("Create")) {
            userDAO.create(user);
        }
        if (command.equals("Update")) {
            userDAO.update(user);
        }
        if (command.equals("edit")) {
            user = userDAO.findByPrimaryKey(user.getId());
            request.setAttribute("user", user);
        }
        if (command.equals("delete")) {
            userDAO.delete(user);
        }
        if (command.equals("Fuzzy Search")) {
            users = userDAO.findByExample(user, true);
        }
        if (command.equals("Strict Search")) {
            users = userDAO.findByExample(user, false);
        }
        if (command.equals("Clear")) {
            request.setAttribute("user", user);
        }
        if (users == null) {
            users = userDAO.findAll();
        }
        request.setAttribute("users", users);
        HibernateUtil.commitTransaction();
    }

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Admin Page</title>
</head>
<body>
<form action="user.jsp">
    <input type="text" size="7" readonly name="id" value="${user.id}"/>id<br/>
    <input type="text" size="30" name="loginName" value="${user.loginName}"/>Name<br/>
    <input type="text" size="30" name="password" value="${user.password}"/>Password<br/>
    <input type="text" size="30" name="emailAddress" value="${user.emailAddress}"/>Email Address<br/>

    <input type="submit" name="command" value="Strict Search"/>
    <input type="submit" name="command" value="Fuzzy Search"/>
    <input type="submit" name="command" value="Update"/>
    <input type="submit" name="command" value="Create"/>
    <input type="submit" name="command" value="Clear"/>
    <br/>
    <c:forEach items="${users}" var="user">

        <c:url var="editurl" value="user.jsp">
            <c:param name="command" value="edit"/>
            <c:param name="id" value="${user.id}"/>
        </c:url>

        <c:url var="deleteurl" value="user.jsp">
            <c:param name="command" value="delete"/>
            <c:param name="id" value="${user.id}"/>
        </c:url>

        | <a href="${editurl}">edit</a>
        | <a href="${deleteurl}">delete</a>
        | <c:out value="${user.id}"/>
        | <c:out value="${user.loginName}"/>
        | <c:out value="${user.emailAddress}"/>
        <br/>

    </c:forEach>
</form>

</body>
</html>

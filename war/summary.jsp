<%@ page
        import="com.codaconsultancy.dao.DAOFactory"
        contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="client" class="com.codaconsultancy.entities.Client" scope="session"/>
<%
    DAOFactory daoFactory = DAOFactory.getFactory();
    daoFactory.newClientDAO().beginTransaction();
    client = daoFactory.newClientDAO().findByPrimaryKey(client.getId());
%>
<html>
<head>
    <title>summary</title>
</head>
<body>
<p>Congratulations! You are registered!</p>
<c:out value="${client.id}"/><br/>
<c:out value="${client.username}"/><br/>
<c:out value="${client.password}"/><br/>
<c:out value="${client.clientDetail.passwordHint}"/><br/>
<c:out value="${client.clientDetail.firstName}"/><br/>
<c:out value="${client.clientDetail.middleName}"/><br/>
<c:out value="${client.clientDetail.lastName}"/><br/>
<c:out value="${client.clientDetail.emailAddress}"/><br/>
<c:out value="${client.clientDetail.registrationDate}"/><br/>

<p>Skills:</p>
<c:forEach items="${client.skills}" var="skill">
    <c:out value="${skill.name}"/><br/>
</c:forEach>

<p>Addresses:</p>
<c:forEach items="${client.addresses}" var="address">
    <c:out value="${address.addressLine1}"/><br/>
    <c:out value="${address.addressLine2}"/><br/>
    <c:out value="${address.city}"/><br/>
    <c:out value="${address.state}"/><br/>
    <c:out value="${address.code}"/><br/>
    <c:out value="${address.country}"/><br/>
    <hr/>
</c:forEach>
</body>
</html>
<%
    daoFactory.newClientDAO().commitTransaction();
%>
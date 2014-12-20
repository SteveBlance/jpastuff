<%@ page
        import="com.codaconsultancy.dao.DAOFactory"
        contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="client" class="com.codaconsultancy.entities.Client" scope="session"/>
<jsp:useBean id="clientDetail" class="com.codaconsultancy.entities.ClientDetail" scope="request"/>
<jsp:useBean id="address" class="com.codaconsultancy.entities.Address" scope="request"/>

<jsp:setProperty name="client" property="*"/>
<jsp:setProperty name="clientDetail" property="*"/>
<jsp:setProperty name="address" property="*"/>
<%
    String command = request.getParameter("command");
    System.out.println("Command:" + command);
    DAOFactory daoFactory = DAOFactory.getFactory();
    System.out.println("0");
    if (command != null && command.equals("Next")) {
        System.out.println("1");
        client.setClientDetail(clientDetail);
        System.out.println("2");
        clientDetail.setClient(client);
        System.out.println("3");
        daoFactory.newClientDAO().beginTransaction();
        System.out.println("4");
        daoFactory.newClientDetailDAO().save(clientDetail);
        System.out.println("5");
        daoFactory.newClientDAO().save(client);
        System.out.println("6");
        daoFactory.newClientDAO().commitTransaction();
        System.out.println("6.5");
    }
    if (command != null && command.equals("Add address")) {
        System.out.println("7");
        daoFactory.newAddressDAO().beginTransaction();
        System.out.println("8");
        client.getAddresses().add(address);
        System.out.println("9");
        address.setClient(client);
        System.out.println("10");
        daoFactory.newAddressDAO().save(address);
        System.out.println("11");
        daoFactory.newClientDAO().save(client);
        System.out.println("12");
        daoFactory.newAddressDAO().commitTransaction();
        System.out.println("12.5");
    }
%>
<html>
<head>
    <title>address</title>
</head>
<body>
<form action="address.jsp" method="get">
    Address Line 1:
    <input type="text" name="addressLine1" size="30"><br/>
    Address Line 2:
    <input type="text" name="addressLine2" size="30"><br/>
    City:
    <input type="text" name="city" size="30"><br/>
    State:
    <input type="text" name="state" size="30"><br/>
    Country:
    <input type="text" name="country" size="30"><br/>
    Code:
    <input type="text" name="code" size="30"><br/>
    <input type="submit" name="command" value="Add address">
</form>
<a href="skills.jsp">Continue to Add Skills ++ </a>
<c:forEach items="${client.addresses}" var="a">
    <hr/>
    <c:out value="${a.addressLine1}"/><br/>
    <c:out value="${a.addressLine2}"/><br/>
    <c:out value="${a.city}"/><br/>
    <c:out value="${a.state}"/><br/>
    <c:out value="${a.code}"/><br/>
    <c:out value="${a.country}"/><br/>
</c:forEach>
</body>
</html>

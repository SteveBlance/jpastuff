<%@ page
        import="com.codaconsultancy.dao.DAOFactory,com.codaconsultancy.entities.Skill"
        contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="client" class="com.codaconsultancy.entities.Client" scope="session"/>
<%
    DAOFactory daoFactory = DAOFactory.getFactory();
    daoFactory.newClientDAO().beginTransaction();
    String[] clientSkills = request.getParameterValues("allskills");
    if (clientSkills != null) {
        client.getSkills().clear();
        for (int i = 0; i < clientSkills.length; i++) {
            Long id = new Long(clientSkills[i]);
            Skill skill = daoFactory.newSkillDAO().findByPrimaryKey(id);
            client.getSkills().add(skill);
        }
        daoFactory.newClientDAO().save(client);
    }
%>

<html>
<head>
    <title>skills</title>
</head>
<body>
<form action="skills.jsp" method="post">

    <select size="20" name="allskills" multiple>
        <c:forEach items="<%=daoFactory.newSkillDAO().findAll(0,100)%>" var="skill">
            <option value="${skill.id}" id="${skill.id}">
                <c:out value="${skill.name}"/>
            </option>
        </c:forEach>
    </select>

    <input type="submit" name="command" value="Update Skills">

    <select size="20" name="allskills" multiple>
        <c:forEach items="<%=client.getSkills()%>" var="skill">
            <option value="${skill.id}" id="${skill.id}">
                <c:out value="${skill.name}"/>
            </option>
        </c:forEach>
    </select>
</form>
<a href="summary.jsp">Finish </a>
</body>
</html>
<%
    daoFactory.newClientDAO().commitTransaction();
%>
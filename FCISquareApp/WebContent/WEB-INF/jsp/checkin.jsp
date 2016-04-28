<%@page import="org.apache.catalina.startup.HomesUserDatabase"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<%if(session.getAttribute("Placename")==null)
	response.sendRedirect("/FCISquareApp/app");%>
<form action="addNewcheckin" method="post">

<input type="text" name="placeName" value=<%=session.getAttribute("Placename")%> readonly>
<input type="hidden" name="userId" value=<%=session.getAttribute("userid") %>>
<input type="hidden" name="placeID" value=<%=session.getAttribute("Placeid")  %>>
<input type="text" name="description" placeholder="say any thing">
<input type="submit" value="Post">


</form>



</body>
</html>
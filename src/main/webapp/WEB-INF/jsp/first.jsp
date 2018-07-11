<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.yly.testboot.jsp.JspBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
   JspBean bean = new JspBean();
   String result1 = bean.metod1("aaaa");
   List<String> temp = new ArrayList<>();
    temp.add("vvv");
    temp.add("bbb");
   String[] result2 = bean.metod2(temp);
%>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<center>
    <%=result1%>
    <%=result2%>
    <h3> 欢迎 ${name },当前时间：${date } </h3>
</center>
</body>
</html>
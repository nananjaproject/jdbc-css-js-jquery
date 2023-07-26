<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<h3>ex04_02_ok.jsp</h3>
 <%
     String subject =  request.getParameter("selsubject"); 
 %> 
selsubject = <%= subject %><br> 
<br>
<br>
[ 선택된 과목 ] <br>
<input type="radio" name="subject"   value="kor"><label>국어</label><br>
<input type="radio"  name="subject"  value="eng"><label>영어</label><br>
<input type="radio"  name="subject"  value="mat"><label>수학</label><br>
<input type="radio"  name="subject"  value="pe"><label>체육</label><br>  
<br> 
<a href="javascript:history.back()">뒤로 가기</a>

<script>
  // :radio  jquery selector ==     input[type=radio]
  $(":radio[value='<%= subject %>']").prop("checked", true);
</script>

</body>
</html>











<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>ex04_ok.jsp</h3>
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
<!-- 
[BOM]
window 객체
document 객체
history 객체
 -->
<a href="javascript:history.back()">뒤로 가기</a>

<script>
  // ?selsubject=eng
  let subject = '<%= subject %>';
  // 
  let  rdos = document.getElementsByName("subject");
  // let  rdos = document.querySelector("[name=subject]");
  // alert( rdos.length );
  for (var i = 0; i < rdos.length; i++) {
	// console.log(  rdos[i].value );
	if(   rdos[i].value == subject    ){
		rdos[i].checked = true;
		break;
	}
 } // for
</script>

</body>
</html>











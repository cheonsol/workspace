<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Function</h1>
	<p>
		EL내에서 문자열, 배열, 컬렉션을 조작하는 함수 제공
	</p>
	
	<c:set var="str" value="How are you?" />
	
	문자열 길이 : ${str.length()} 또는 ${fn:length(str)} <br>
	"are" 시작 인덱스 : ${fn:indexOf(str, "are")} <br>
	대문자 변환 : ${fn:toUpperCase(str)}<br>
	소문자 변환 : ${fn:toLowerCase(str)}<br>
	
	공백제거 : ${fn:trim("   hello    ")} 또는<%-- ${"   hello    ".trim()} --%>
	<!-- 더 많은 jstl fuction기능이 있지만 대부분 자바의 기본메서드로 대체가 가능하므로 기본메서드를 사용하자! -->
</body>
</html>
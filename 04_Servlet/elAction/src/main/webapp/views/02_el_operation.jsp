<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h3>1. 산술연산</h3>
	기존 : 10 + 3 = <%= (int)request.getAttribute("big") + (int)request.getAttribute("small")%>
	
	<br><br>
	
	EL방식 <br>
	10 + 3 = ${big + small} <br>
	10 - 3 = ${big - small} <br>
	10 * 3 = ${big * small} <br>
	10 / 3 = ${big / small} 또는 ${big div small}<br>
	10 % 3 = ${big % small} 또는 ${big mod small}<br>
	
	<h3>2. 대소비교</h3>
	10 &gt; 3 = ${big > small} 또는 ${big gt small} <br>
	10 &lt; 3 = ${big < small} 또는 ${big lt small} <br>
	10 &gt;= 3 = ${big >= small} 또는 ${big ge small} <br>
	10 &lt;= 3 = ${big <= small} 또는 ${big le small} <br>
	
	<h3>3. 동등비교</h3>
	<p>el에서는 ==비교는 자바에서의 equals()와 같은 동작</p>
	strOne과 strTwo가 일치하는가? ${strOne == strTwo} 또는 ${strOne eq strTwo} <br>
	strOne과 strTwo가 일치하지 않는가? ${strOne != strTwo} 또는 ${strOne ne strTwo} <br>

	<%-- el구문 안에서 문자열 리터럴값은 '',""를 구분하지 않음. --%>
	strThree와 "hello"와 일치하는가? ${strThree == "hello"} 또는 ${strThree eq 'hello'} <br>

	<h3>4. 객체가 null인지 아닌지, list가 비어있는지 확인</h3>
	personOne이 null인가? ${ personOne == null } 또는 ${ personOne eq null } 또는 ${ empty personOne } <br>
	personTwo이 null인가? ${ personTwo == null } 또는 ${ personTwo eq null } 또는 ${ empty personTwo } <br>
	
	personTwo이 null이 아닌가? ${ personTwo != null } 또는 ${ personTwo ne null } 또는 ${not empty personTwo } <br><br>
	
	arrOne은 비어있는가? ${ empty arrOne } <br>
	arrTwo은 비어있는가? ${ empty arrTwo } <br>
	
	<h4>5. 논리연산자</h4>
	true && true : ${true && true} 또는 ${true and true} <br>
	false || flase : ${false || flase} 또는 ${false or flase} <br>
	
	big이 small보다 크고 arrOne은 비어있는가?
	${big > small && empty arrOne}
	${big gt small and empty arrOne}
</body>
</html>
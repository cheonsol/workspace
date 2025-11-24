<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.el.model.vo.Person" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Core Library</h1>
	
	<h3>1. 변수(속성 == attribute)</h3>
	<p>
		- 변수 선언과 동시에 초기화(c:set var="변수명" value="값" [scope="내장객체"])<br>
		- 변수를 만들고 초기값을 바로 대입해 준다. <br>
		- scope속성을 통해 저장범위(scope)를 지정할 수 있다. <br>
		=> 내장객체(scope를 결정)에 저장하기 때문에 el로 빠르게 가져와 사용할 수 있음.
	</p>
	
	<c:set var="num1" value="10" />
	<%-- pageContext.setAttribute("num1", 10); 와 동일한 결과 --%>
	
	<c:set var="num2" value="20" scope="request" />
	num1 : ${num1} <br>
	num2 : ${num2} <br>
	
	<p>
		변수를 삭제하고자 한다면(c:remove var="제거하고자하는 변수명" [scope="내장객체"]) <br>
		- 지정된 내장객체에서 해당 이름의 속성을 제거. <br>
		- scope속성을 생략하면 모든 scope에서 같은 이름을 찾아 제거함.
	</p>
	
	<c:set var="result" value="${num1 + num2}" scope="session" />
	삭제 전 : ${result} <br>
	
	<c:remove var="result" scope="request" />
	request영역에 result 삭제 후 : ${result} <br>
	
	<c:remove var="result" />
	모든영역에 result 삭제 후 : ${result} <br>
	
	<hr>
	<p>
		변수 출력 (c:out value="출력하고자하는 값" [default="기본값"] [escapeXml="true | false"]) <br>
		->값을 안전하게 출력할 수 있음, 값이 null이면 default를 출력하게 설정할 수 있음.
	</p>
	
	result : ${result} <br>
	result : <c:out value="${result}" /> <br>
	result : <c:out value="${result}" default="없음" /> <br>
	
	<c:set var="outTest" value="<b>출력테스트</b>" />
	outTest : ${outTest} <br>
	outTest : <c:out value="${outTest}" escapeXml="true"/> <br>
	outTest : <c:out value="${outTest}" escapeXml="false"/> <br>
	
	<br>
	
	<h3>2. 조건문 - if (c:if test="조건식")</h3>
	<p>
		- java에서의 if문과 같은 역할을 하는 태그  <br>
		- 조건식은 test속성안에 el구문으로 작성한다.
	</p>
	
	<% if(10 < 20){ %>
		<b>num2가 num1보다 큽니다.</b> <br>
	<% } %>
	
	<c:if test="${num1 > num2}">
		<b>num1가 num2보다 큽니다.</b> <br>
	</c:if>
	
	<c:if test="${num1 < num2}">
		<b>num2가 num1보다 큽니다.</b> <br>
	</c:if>
	
	<c:set var="str1" value="hello" />
	<c:if test="${str1 == 'hello'}">
		<b>${str1}</b> <br>
	</c:if>
	
	<h3>3. 조건문 - 다중분기(c:choose/c:when/c:otherwise)</h3>
	<c:choose>
		<c:when test="${num1 > 30}">
			<b>num1이 30보다 큽니다.</b> <br>
		</c:when>
		<c:when test="${num1 > 20}">
			<b>num1이 20보다 큽니다.</b> <br>
		</c:when>
		<c:when test="${num1 > 10}">
			<b>num1이 10보다 큽니다.</b> <br>
		</c:when>
		<c:otherwise>	
			<b>모든 조건이 맞지 않습니다.</b> <br>
		</c:otherwise>
	</c:choose>
	
	<h3>4. 반복문 - forEach</h3>
	<p>
		- 카운터 반복(for loop) : (c:forEach var="변수명" begin="초기값" end="끝값" [step="반복시 증가값"])
		- 배열/컬렉션 반복(forEach) : (c:forEach var="변수명" items="순차적으로 접근할 객체(배열/컬렉션)" [varStatus="현재 접근된 요소의 상태값"])
	</p>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		반복확인 : ${i} <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="5">
		<h${i}>태그안에서 사용</h${i}>
	</c:forEach>
	
	<c:set var="colors" value="red,yellow,green,pink" />
	colors : ${colors}
	
	<ul>
		<c:forEach var="color" items="${colors}">
			<li style="color: ${color}">${color}</li>
		</c:forEach>
	</ul>
	
	<%
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("최지원", 18, "남자"));
		list.add(new Person("최지투", 25, "여자"));
		list.add(new Person("최지삼", 40, "남자"));
	%>
	<c:set var="personList" value="<%=list%>" scope="request" />
	
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${personList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${p.name}</td>
					<td>${p.age}</td>
					<td>${p.gender}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h3>5. 토큰 분리 반복 - forTokens</h3>
	<p>
		- (c:forTokens var="변수명" items="분리하고싶은 문자열" delims="구분자")
		- 구분자를 통해서 분리된 각각의 문자열에 순차적으로 접근하며 반복을 수행
		- java의 문자열.split(...)와 비슷
	</p>
	
	<c:set var="device" value="컴퓨터,노트북.TV,핸드폰,냉장고/세탁기" />
	
	<ol>
		<c:forTokens var="d" items="${device}" delims=",/.">
			<li>${d}</li>
		</c:forTokens>
	</ol>
	
	<h3>6. URL 쿼리스트링</h3>
	<p>
		url경로를 생성하고, 쿼리스트링을 정의해 둘 수 있는 태그 <br>
		(c:url var="변수명" value="요청url")
			(c:param name="키" value="값")
			(c:param name="키" value="값")
			...
		(/c:url)
	</p>
	
	<a href="list.do?color=black&item=5">기존방식의 요청</a>
	
	<c:url var="listUrl" value="list.do">
		<c:param name="color" value="black"/>
		<c:param name="item" value="5"/>
	</c:url>
	
	<a href="${listUrl}">c:url이용 요청</a>
	
	<br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>














<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=h1, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>EL (Expression language)</h1>

    <p>
        기존 JSP에서 사용하던 표현식 &lt;%=name%&gt; <br>
        EL 구문을 사용하면 표현하고자하는 값을 ${name}의 형식으로 출력할 수 있다. <br>
        EL은 기존 표현식보다 간결하고 null처리에 안전하다.
    </p>

    <h4><a href="basic.do">01_EL의 기본구문</a></h4>
    <h4><a href="operation.do">02_EL의 연산자</a></h4>
    
    <h1>JSP Action Tag</h1>
    <%--
    	JSP구성요소
    	1. 스크립팅 원소
    	  - JSP에서 직접 java코드를 작성할 수 있는 영역
    	  - 예 : 스크립틀릿(<% %>), 표현식(<%= %>), 선언문(<%! %>...)
    	
    	2. 지시어(Directive)
    	  - JSP페이지의 메타데이터, 다른JSP 포함, 라이브러리 등록 등을 설정
    	  - 예 : <%@ page %>, <%@ include %>, <%@ taglib %>
    	
    	3. 액션태그(Action Tag)
    	  - JSP에서 특정 동작을 수행하도록 지시하는 태그
    	  - XML문법기반으로 JSP기능을 확장
    	  >> 표준액션태그 : <jsp:태그명></태그명>이런식으로 jsp:이라는 접두어를 사용
    	  >> 커스텀액션태그 : 별로 라이브러리로 등록하여 사용하는 태그(JSTL등...)
    --%>
    
    <h3>표준 액션 태그</h3>
    <a href="views/03_action_include.jsp">01_jsp:include</a> <br>
    <a href="views/04_action_forward.jsp">02_jsp:forward</a> <br>
    
    <h1>JSTL (JavaServer Pages Standard Tag Library)</h1>
    <p>
    	JSP에서 자주 사용하는 기능(반복문, 조건문, 포맷팅 등)을 태그형태로 제공하는 라이브러리 <br>
    	자바코드를 직접 작성하지 않고도 로직을 구현할 수 있어, 코드 가독성이 향상된다.
    </p>
    
    <h3>JSTL 라이브러리 추가 방법</h3>
    1) maven repository 사이트 접속
    2) jakarta.servlet.jsp.jstl jakarta.servlet.jsp.jstl-api 다운로드
    3) 2개의 .jar파일을 WEB-INF/lib폴더에 추가
    
    <h3>JSTL 선언방법</h3>
    <p>
    	JSTL을 사용하려는 JSP페이지 상단에 taglib지시어를 선언해야 한다. <br>
    	<%-- <%@ taglib prefix="접두어" url="라이브러리파일상의 uri주소" %> --%>
    </p>
    
    <h3>Custom Action Tag (JSTL 주요 라이브러리)</h3>
    
    <h4>1. JSTL Core Library</h4>
    <p>변수, 조건문, 반복문과 같은 로직과 관련된 문법을 태그로 제공</p>
    <a href="views/05_custom_core.jsp">01_core</a>
    
    <h4>2. JSTL Formatting Library</h4>
    <p>숫자/날짜/시간의 출력 형식을 지정해주는 태그를 제공</p>
    <a href="views/06_custom_fmt.jsp">02_fmt</a>
  
    <h4>3. JSTL function Library</h4> 
    <p>EL안에서 사용할 수 있는 메서드를 제공</p>
    <a href="views/07_custom_function.jsp">03_function</a>
</body>
</html>
package com.kh.textGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 어노테이션은 이 클래스가 Spring Boot 애플리케이션의 메인 클래스임을 나타냅니다.
 * 이 어노테이션 하나로 다음 세 가지 어노테이션의 기능을 모두 포함합니다.
 * 
 * 1. @SpringBootConfiguration:
 *    - 이 클래스가 Spring의 Java 기반 구성 클래스임을 나타냅니다.
 *    - @Configuration 어노테이션을 상속받아 사용되며, 애플리케이션의 빈(Bean) 설정을 담당합니다.
 *
 * 2. @EnableAutoConfiguration:
 *    - Spring Boot가 클래스패스 설정, 다른 빈, 다양한 프로퍼티 설정 등을 기반으로 자동 구성을 활성화하도록 지시합니다.
 *    - 예를 들어, 클래스패스에 `spring-webmvc`가 있다면, Spring Boot는 자동으로 `DispatcherServlet`을 설정하고 웹 애플리케이션으로 구성합니다.
 *    - 특정 자동 구성을 제외하고 싶다면 `exclude` 속성을 사용할 수 있습니다. (예: @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}))
 * 
 * 3. @ComponentScan:
 *    - Spring이 컴포넌트(예: @Component, @Service, @Repository, @Controller 등)를 찾기 위해 스캔할 패키지를 지정합니다.
 *    - 기본적으로 이 어노테이션이 선언된 클래스가 속한 패키지와 그 하위 패키지들을 모두 스캔합니다.
 *    - `com.kh.textGame` 패키지와 그 하위의 모든 컴포넌트들을 스캔하여 Spring 컨테이너에 빈으로 등록합니다.
 */
@SpringBootApplication
public class TextGameApplication {

	/**
	 * main 메소드는 Java 애플리케이션의 진입점(Entry Point)입니다.
	 * Spring Boot 애플리케이션은 이 main 메소드에서 시작됩니다.
	 * 
	 * @param args 커맨드 라인 인자(Command Line Arguments)를 받을 수 있습니다.
	 */
	public static void main(String[] args) {
		// SpringApplication.run() 메소드는 Spring Boot 애플리케이션을 실행하는 핵심적인 역할을 합니다.
		// 이 메소드가 호출되면 다음과 같은 과정이 진행됩니다.
		// 1. SpringApplication 인스턴스를 생성합니다.
		// 2. 애플리케이션 컨텍스트(ApplicationContext)를 생성하고 초기화합니다.
		//    - 이 과정에서 @EnableAutoConfiguration에 의해 자동 구성이 수행되고,
		//    - @ComponentScan에 의해 컴포넌트들이 스캔되어 빈으로 등록됩니다.
		// 3. 내장 웹 서버(기본적으로 Tomcat)를 시작하고, 애플리케이션을 배포합니다.
		
		SpringApplication.run(TextGameApplication.class, args);
	}

}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 * 
 * 1. @SpringBootApplication의 스캔 범위 명시적 지정:
 *    - 프로젝트 규모가 커지면 `@ComponentScan`의 범위를 명시적으로 지정하여 스캔 속도를 향상시키고, 의도치 않은 컴포넌트가 등록되는 것을 방지할 수 있습니다.
 *    - 예: @SpringBootApplication(scanBasePackages = {"com.kh.textGame.controller", "com.kh.textGame.service"})
 * 
 * 2. 프로파일(Profile) 활용:
 *    - 개발(dev), 테스트(test), 운영(prod) 환경에 따라 다른 설정(예: 데이터베이스 연결 정보)을 적용해야 할 때 프로파일을 사용하면 효율적입니다.
 *    - `application-dev.yml`, `application-prod.yml` 등의 파일을 만들고, 실행 시 `spring.profiles.active=dev`와 같이 활성화할 프로파일을 지정할 수 있습니다.
 * 
 * 3. Spring Boot Actuator 사용:
 *    - `spring-boot-starter-actuator` 의존성을 추가하면 애플리케이션의 상태 모니터링 및 관리를 위한 다양한 엔드포인트(/health, /metrics, /info 등)를 사용할 수 있습니다.
 *    - 이를 통해 애플리케이션의 동작 상태를 쉽게 확인하고 문제를 진단할 수 있습니다.
 *
 * 4. 조건부 자동 구성 활용:
 *    - @ConditionalOnProperty, @ConditionalOnClass 등의 어노테이션을 사용하여 특정 조건이 만족될 때만 빈이 등록되도록 구성할 수 있습니다.
 *    - 이는 라이브러리나 모듈을 개발할 때 유용하며, 유연한 구성을 가능하게 합니다.
 */

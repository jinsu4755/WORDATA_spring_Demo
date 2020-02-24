## JPA 4회차

[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)

### 표준 API?

java를 배우는 것은 먼저 문법을 배우고 후에 표준 라이브러리 클래스를 배우는 것을 말한다.

예를 들면 String 클래스와 ArrayList 클래스는 Java 표준 라이브러리의 클래스 이다.



안드로이드 앱 개발 사용시 String 클래스와 ArrayList 클래스는 Google이 만든 Java 표준 라이브러리에 들어있다.

<br>

Spring Web MVC를 개발 할때 사용하는 String 클래스와 ArrayList 클래스는 Oracle이 만든 Java 표준 라이브러리에 들어있다.

<br>

두 회사에서 만든 ArrayLIst 클래스 사용법이 동일한 이유는, 

두 회사가 라이브러리 API를 동일하게 구현하였기 때문.

<br>

라이브러리 API는 라이브러리의 public클래스와 메소드 목록을 말하고 두 라이브러리 public 클래스 목록과 public 메소드 목록이 동일하면 두 라이브러리 사용법도 동일하다.

<br>

각 회사마다 동일한 기능의 클래스가 모두 다른 역할을 하면 비효율적이므로 두 회사의 라이브러리 사용법이 통일되면 바람직하다.

그리고 사용법을 통일하기 위해서는, 그 라이브러리들의 API를 통일해야 한다.

또 이렇게 사용법이 통일된 라이브러리들의 API를 표준 API라고 하고 존재 이유는 여러 라이브러리 사용법을 통일하기 위해서 이다.

<br>

### JPA와 Hibernate

JPA (Java Persistence API)는 ORM 기술의 하나이다.

Java Persistence = Java 객체를 DB에 저장하거나, DB 데이터를 Java 객체로 조회하는 기술

JPA = Java Perssitence API = Java Persistence를 구현한 라이브러리에 대한 표준 API



JPA는 표준 API이므로 제품이 아님.

JPA는 Java Persistence 기능을 구현한 라이브러리들의 API를 통일하기 위해 만든 표준 API이고

JPA 표준 API를 구현한 대표적인 오픈소스 라이브러리로 Hibernate가 있다.



### Spring Data JPA

[Spring Data](http://projects.spring.io/spring-data/)는 Spring 프로젝트에서 사용되는 데이터베이스 라이브러리 개발 프로젝트이다.



Spring Data 라이브러리에는 JDBC 데이터 베이스 프로그래밍을 편하게 할 수 있게 도와주는 클래스들이 포함되어있다.



[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)
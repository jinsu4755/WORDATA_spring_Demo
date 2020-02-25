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



Spring Data 라이브러리에는 **JDBC 데이터 베이스 프로그래밍을 편하게 할 수 있게 도와주는 클래스**들이 포함되어있다.

**이 클래스들의 그룹을 Spring Data JPA라고 부른다.**

하지만 mybatis 데이터베이스 프로그래밍을 도와주는 클래스는 포함되지 않는다.

즉, Spring data는 JDBC와 JPA를 지원하지만 mybatis는 지원하지 않는다.

예제에서 사용하는 JPARepository 인터페이스는 JPA에 포함된 것이 아닌 Spring Data JPA에 포함되어 있음



### JPA Repository 기본 method

StudentRepository 인터페이스가 있다고 가정하면 아래 메소드가 기본으로 포함된다.

| Return type          | method                   | 기능                                                         |
| -------------------- | ------------------------ | ------------------------------------------------------------ |
| Optional<Student&gt; | findById(int id);        | Student 테이블에서 기본키로 레코드 하나를 조회               |
| List<Student&gt;     | findAll();               | Student 테이블의 전체 레코드를 조회                          |
| Student              | save(Student student);   | 파라미터 객체 student 를 student 테이블에 저장<br>student 객체의 id 값이 0이면 INSERT하고,<br>id 값에 해당하는 레코드가 있을 경우 UPDATA 한다. |
| void                 | delete(Student student); | studnet 객체의 id 값과 일치하는 레코드를 Student 테이블에서 삭제 |
| boolean              | exists(int id);          | id값과 일치하는 레코드가 Student 테이블에 있으면 true 리턴   |

JPARepository 인터페이스를 상속하여 StudentRepository 인터페이스를 정의하면,

Spring Data JPA가 다음과 같은 일들을 자동으로 해준다.

- StudentRepository 인터페이스를 구현한 클래스를 자동으로 구현.
- 구현된 클래스에 JPA repository 기본 메소드를 구현
- 해당 클래스 객체를 하나 자동 생성
- 생성된 객체를  @Autowired StudentRepository studentRepository;  멤버 변수에 자동 대입.



### 쿼리 메소드 자동 구현

Spring Data JPA는, JPA repository 기본 메소드 뿐만 아니라,

repository 인터페이스에 선언된 다른 메소드들도 자동으로 구현해 준다



### 쿼리 메소드 이름 규칙

| Keyword | Sample |
|------------|-------------|
| And  | findByLastNameAndFirstName(String lastName, String firstName) |
| Or   | findByLastNameOrFirstName(String lastName, String firstName) |
| Between | findByStartDateBetween(Date date1, Date date2) |
| LessThan | findByAgeLessThan(int age) |
| GreaterThan | findByAgeGreaterThan(int age) |
| After | findByStartDateAfter(Date date1) |
| Before | findByStartDateBefore(Date date1) |
| IsNull | findByAgeIsNull() |
| IsNotNull,NotNull | findByAgeNotNull(), findByAgeIsNotNull() |
| Like | findByFirstNameLike(String pattern) |
| NotLike | findByFirstNameNotLike(String pattern) |
| StartingWith | findByFirstNameStartingWith(String name) |
| EndingWith | findByFirstNameEndingWith(String name) |
| Containing | findByFirstNameContaining(String name) |
| OrderBy | findByAgeOrderByLastNameDesc(int age) |
| Not  | findByLastNameNot(String name) |
| In   | findByAgeIn(Collection<Integer&gt; ages) |
| NotIn | findByAgeNotIn(Collection<Integer&gt; age) |
| True | findByActiveTrue() |
| False | findByActiveFalse() |
| Top  | findTop10ByOrderByAge() |

파라미터 변수 이름은 중요하지 않고 파라미터 변수 순서가 중요하다.

ex) findByLastNameAndFirstName(String s1, String s2)

파라미터 변수 이름이 무엇이던 상관없이 첫 파라미터가 LastName이고 둘째 파라미터가 FirstName이어야 한다.

**파라미터 변수명이 무시되는 이유?**

Java는 바이트 코드로 컴파일 되는데(.class) 컴파일된 바이트 코드가 JVM에서 실행됨

컴파일된 *.class 파일에 Java 지역 변수 이름과 파라미터 변수 이름은 들어있지 않다.

그래서 JVM은 메소드 파라미터 변수 이름을 알 수 없으니,

바이트코드 JVM에서 실행될 때 메소드 파라미터 변수 이름은 무시될 수 밖에 없다.

**파라미터 변수명 무시 사례**

mybatis mapper method

JPA Repository method

Spring MVC Action method



**쿼리 메소드 이름 규칙 예시**

- Person Table
  | Field| Type|
  | --- | ---- |
  | firstName | NVARCHAR(50) |
  | lastName  | NVARCHAR(50) |
  | startDate | DATE |
  | age | int |
  | active | BOOLEAN |




findByLastNameAndFirstName(String lastName, String firstName)

SELECT * FROM Person WHERE lastName = #{lastName} AND firstName = #{firstName}



findByLastNameOrFirstName(String lastName, String firstName)

SELECT * FROM Person WHERE lastName = #{lastName} OR firstName = #{firstName}



findByStartDateBetween(Date date1, Date date2)

SELECT * FROM Person WHERE startDate BETWEEN #{date1} AND #{date2}



findByAgeLessThan(int age)

SELECT * FROM Person WHERE age < #{age}



findByAgeGreaterThan(int age)

SELECT * FROM Person WHERE age > #{age}



findByStartDateAfter(Date date1)

SELECT * FROM Person WHERE startDate > #{date1}



findByStartDateBefore(Date date1)

SELECT * FROM Person WHERE startDate < #{date1}



findByAgeIsNull()

SELECT * FROM Person WHERE age IS NULL



findByAgeNotNull(), findByAgeIsNotNull()

SELECT * FROM Person WHERE age IS NOT NULL



findByFirstNameLike(String pattern)

SELECT * FROM Person WHERE firstName LIKE #{pattern}



findByFirstNameNotLike(String pattern)

SELECT * FROM Person WHERE firstName NOT LIKE #{pattern}



findByFirstNameStartingWith(String name)

findByFirstNameStartsWith(String name)

SELECT * FROM Person WHERE firstName LIKE #{name} + '%'

예) SELECT * FROM Person WHERE firstName LIKE '김%'



findByFirstNameEndingWith(String name)

findByFirstNameEndsWith(String name)

SELECT * FROM Person WHERE firstName '%' + LIKE #{name}

예) SELECT * FROM Person WHERE firstName LIKE '%김'



findByFirstNameContaining(String name)

findByFirstNameContains(String name)

SELECT * FROM Person WHERE firstName '%' + LIKE #{name} + '%'

예) SELECT * FROM Person WHERE firstName LIKE '%김%'



findByAgeOrderByLastNameDesc(int age)

SELECT * FROM Person WHERE age IS NULL



findByLastNameNot(String name)

SELECT * FROM Person WHERE lastName <> #{name}



findByAgeIn(Collection<Age&gt; ages)

SELECT * FROM Person WHERE age IN #{ages}

/* 주의: mybatis는 Collection 타입 파라미터를 지원하지 않음 */



findByAgeNotIn(Collection<Age&gt; age)

SELECT * FROM Person WHERE age NOT IN #{ages}

/* 주의: mybatis는 Collection 타입 파라미터를 지원하지 않음 */



findByActiveTrue()

SELECT * FROM Person WHERE active

SELECT * FROM Person WHERE active = true



findByActiveFalse()

SELECT * FROM Person WHERE NOT active

SELECT * FROM Person WHERE active = false



findTop10ByOrderByAge

Top 10

조회 결과에서 선두 10 개 레코드만 리턴한다.



### countBy, deleteBy, removeBy

findBy 메소드 규칙은 위 메소드에도 적용된다.

countBy는 주어진 조건에 일치하는 레코드 수를 리턴한다.

deleteBy, removeBy 메소드는 주어진 조건에 일치하는 레코드를 삭제한다.







[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)
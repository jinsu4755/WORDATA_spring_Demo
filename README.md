# spring_Demo

### WORDATA 프로젝트를 위한 공부

| 프로젝트 폴더 이름 | 내용                                                         | 요약        | 비고     |
| ------------------ | ------------------------------------------------------------ | ------------- | ------------- |
| WORDATA_Demo       | - WORDATA 프젝 프론트에서 static, templates 확인             |               |               |
| test               | - thymeleaf, MySQL, Spring Security, mybatis로 간단한 로그인 구현 </br> - 로그인 화면 커스텀 필요 |               |               |
| Wordata_JPA        | - JPA 공부 1회차 [목표](#jpa-1)</br> 주제: JPA 기초 | [요약](/Wordata_JPA/README.md) | [DB](#jpa-db-1st) |
| Wordata_JPA2 | - JPA 공부 2회차 [목표](#jpa-2)</br> 주제: JPA relationship mapping 1 | [요약](/Wordata_JPA2/README.md) | [DB](#jpa-db-1st) |
| Wordata_JPA3 | - JPA 공부 3회차 [목표](#jpa-3)</br> 주제: JPA relationship mapping 2 | [요약](/Wordata_JPA3/README.md) | [DB](#jpa-db-2nd) |
| Wordata_JPA4 | - JPA 공부 4회차 [목표](#jpa-4)</br> 주제: JPA query creation | [요약](/Wordata_JPA4/README.md) | [DB](#jpa-db-2nd) |
| thymeleaf_JPA_pagination | - JPA&thymeleaf에서 pagination 하기<br>- thymeleaf,JPA,web,devtools,MySQL,lombok,h2,bootstrap,slf4j 환경 |  | [DB](#jpa-db-1st) |
| security | - security 구현<br>- thymeleaf, JPA, MySQL, web, devtools, lombok, security 환경 | |  |

---

### WORDATA_Demo

사용된 DB나 따로 설정할 것은 폴더 내부 [README](/WORDATA_Demo/README.md) 참조

WORDATA_Demo를 위해 Clone할 경우 이클립스에서 Import시에는 해당 폴더와 상위폴더 정도만 import하고 그 이외는 필요 없음

---

### properties

```properties
### server setting ###
#server.address=localhost
#server.port= 8080


### MySQL 연동 ###

spring.datasource.url=jdbc:mysql://{localhost:3306}/{DB_name}?useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
spring.datasource.username=user1
spring.datasource.password=test123
#위 두 설정은 계정 명 적용
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#com.mysql.jdbc.Driver가 있으나 해당 경우는 Deprecated 임. 더 이상 개발 하지 않으며 삭제될 예정이라함.


#
# JPA 설정
#
spring.jpa.database=mysql
# MySQL 을 사용
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
# 해당 설정은 DB 필드 이름이 testName 형태이면 필요함 (camel case)
# 만약 test_name 형식이면 필요하지 않음
spring.jpa.open-in-view=false
# OSIV(open session in view)는 웹 요청이 완료될 때까지 동일한 EntityManager를 가지게함.
# spring boot 기본값은 true인데 확장성 면에서 좋지 않다함.
spring.jpa.properties.hibernate.show_sql=true
# 콘솔에 JPA 쿼리 출력
spring.jpa.properties.hibernate.format_sql=true
# 콘솔에 출력되는 JPA 쿼리를 가독성 있게 표현

### logging ###
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
# SQL에서 물음표로 표기된 부분( bind parameter )을 로그로 출력해서 확인

```

[https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot](https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot)

---

## JPA DB 1st

Employee 관련 DB

- **department**

| Field | Type        | Null | Key | Default | Extra          |
|-------|-------------|------|-----|---------|----------------|
| id    | int         | NO   | PRI | NULL    | auto_increment |
| name  | varchar(50) | YES  |     | NULL    |                |

- **employee**


| Field        | Type        | Null | Key | Default | Extra          |
| ----- | ---- | ---- | ---- | ------- | -------------- |
| id           | int         | NO   | PRI | NULL    | auto_increment |
| name         | varchar(50) | YES  |     | NULL    |                |
| departmentId | int         | YES  | MUL | NULL    |                |


- **address**

| Field      | Type         | Null | Key | Default | Extra          |
|-----------|--------------|-----|-----|---------|----------------|
| id         | int          | NO   | PRI | NULL    | auto_increment |
| employeeId | int          | YES  | MUL | NULL    |                |
| phone      | varchar(50)  | YES  |     | NULL    |                |
| address    | varchar(500) | YES  |     | NULL    |                |



department 테이블은 부서 데이터를 저장한다.

employee 테이블은 직원 데이터를 저장한다.

address 테이블은 직원의 주소와 전화번호를 저장한다.

</br>

employee 테이블의 departmentId 필드는 직원 소속 부서를 가르키는 외래키(foreign key) 이다.

address 테이블의 employeeId 필드는 해당 주소의 직원을 가르키는 외래키(foreign key) 이다.

</br>

department 와 employee 관계는 1 대 다 관계이다.

employee 와 address 관계는 1 대 1 관계이다

[맨 위로](#)

---

## JPA DB 2nd

Student 관련 DB



- **department**

  학과 정보를 저장한다.

| Field          | Type        | Null | Key | Default | Extra          |
|---------------|------------|------|-----|-------|-----------|
| id             | int         | NO   | PRI | NULL    | auto_increment |
| departmentName | varchar(50) | NO   |     | NULL    |                |

- **student**

  학생 정보를 저장한다.

  departmentId 필드는 department 레코드를 가르키는 외래키(foreign key)이다.

  student와 department의 관계는 다 대 1 이다.

| Field         | Type        | Null | Key | Default | Extra          |
|--------------|------------|-----|---|---------|-------------|
| id            | int         | NO   | PRI | NULL    | auto_increment |
| studentNumber | varchar(50) | NO   |     | NULL    |                |
| name          | varchar(50) | NO   |     | NULL    |                |
| departmentId  | int         | NO   | MUL | NULL    |                |
| year          | int         | NO   |     | NULL    |                |

- **professor**

  교수 정보를 저장한다.

  departmentId 필드는 department 레코드를 가르키는 외래키이다.

  professor와 department의 관계는 다 대 1 이다.

  

| Field         | Type        | Null | Key | Default | Extra |
|---------------|-------------|------|-----|---------|-------|
| id            | int         | NO   | PRI | NULL    |       |
| professorName | varchar(50) | NO   |     | NULL    |       |
| departmentId  | int         | NO   | MUL | NULL    |       |

- **course**

  강좌 정보를 저장한다.

  departmentId 필드는 department 레코드를 가르키는 외래키이다.

  course와 department의 관계는 다 대 1 이다.

  prfessorId 필드는 professor 레코드를 가르키는 외래키이다.

  course와 professor의 관계는 다 대 1 이다.

| Field        | Type        | Null | Key | Default | Extra          |
|--------------|-------------|------|-----|---------|----------------|
| id           | int         | NO   | PRI | NULL    | auto_increment |
| courseName   | varchar(50) | NO   |     | NULL    |                |
| departmentId | int         | NO   | MUL | NULL    |                |
| unit         | int         | NO   |     | NULL    |                |
| professorId  | int         | NO   | MUL | NULL    |                |
| startDate    | date        | YES  |     | NULL    |                |

- **register**

  학생의 수강 신청 정보를 저장한다.

  studentId 필드는 student 레코드를 가르키는 외래키이다.

  register와 student의 관계는 다 대 1 이다.

  courseId 필드는 course 레코드를 가르키는 외래키이다.

  register와 course의 관계는 다 대 1 이다.

| Field      | Type | Null | Key | Default | Extra          |
|------------|------|------|----|--------|-------------|
| id         | int  | NO   | PRI | NULL    | auto_increment |
| studentId  | int  | NO   | MUL | NULL    |                |
| courseId   | int  | NO   | MUL | NULL    |                |
| grade      | int  | YES  |     | NULL    |                |
| createDate | date | YES  |     | NULL    |                |



[맨 위로](#)

---

### JPA 1

Employee 관련 DB 에서 Department와 Employee Entity class 구현 repository와 controller 구현

</br>

[맨 위로](#)

---

### JPA 2

- 1차

  Employee 엔터티 클래스에 Single-Valued Association은 이미 구현,

  Department 엔터티 클래스에 Collection-Valued Association을 구현하기

</br>

- 2차

  employee 테이블과 address 테이블은 1 대 1 관계이다.

  address 테이블의 employeeId 필드는 그 주소를 소유한 직원을 가르키는 외래키(foreign key) 이다.

  

  이 관계는 1 대 1 관계이기 때문에

  양쪽 모두 single valued assicication으로 구현된다.

  둘 다 @OneToOne 어노테이션으로 구현된다.

  

  그렇지만, 외래키가 있는 Address 클래스에는 @JoinColumn 어노테이션이 추가로 필요하고,

  외래키가 없는 Employee 클래스에는 mappedBy 속성이 추가로 필요하다.

  

  Infinite recursion 에러를 피하려면, 두 클래스 중 하나에 @JsonIgnore 어노테이션을 붙여야 한다.

  직원(employee) 객체에 주소(address)가 같이 포함되어 보이는 것이 좋기 때문에,

  Address 클래스에 @JsonIgnore 어노테이션을 붙이자.

</br>

[맨 위로](#)

---

### JPA 3

다대일 관계로 구현되는 관계의 도메인 모델을 구현

구현된 엔터티 클래스에서 @ManyToOne, @OneToMany 어노테이션이 붙은 멤버 변수들과

위의 ER 다이어그램에서 관계선을 비교하면서 읽어보기 바란다.

꼼꼼히 비교해 보면 일관된 구현 규칙을 발견할 수 있을 것이다.

구현 규칙만 파악하면, 엔터티 클래스 구현이 생각보다 쉅고 단순하다

</br>

[맨 위로](#)

------

### JPA 4

프로젝트 수정 및 JPA Repository 쿼리 메소드 자동 구현 기능 & JPA Repository 쿼리 메소드 이름 규칙 학습
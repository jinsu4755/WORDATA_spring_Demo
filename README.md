# WORDATA_spring_Demo

### WORDATA 프로젝트를 위한 공부

| 프로젝트 폴더 이름 | 내용                                                         | 요약        | 비고     |
| ------------------ | ------------------------------------------------------------ | ------------- | ------------- |
| WORDATA_Demo       | - WORDATA 프젝 프론트에서 static, templates 확인             |               |               |
| test               | - thymeleaf, MySQL, Spring Security, mybatis로 간단한 로그인 구현 </br> - 로그인 화면 커스텀 필요 |               |               |
| Wordata_JPA        | - JPA 공부 1회차 [목표](#jpa-1)</br> 주제: JPA 기초 | [요약](/Wordata_JPA/README.md) | [DB](#wordata-jpa-db(1st)) |
| Wordata_JPA2 | - JPA 공부 2회차 [목표](#jpa-2)</br> 주제: JPA relationship mapping 1 | [요약](/Wordata_JPA2/README.md) | [DB](#wordata-jpa-db(1st)) |
| Wordata_JPA3 | -JPA 공부 3회차 [목표](#jpa-3)</br> 주제: JPA relationship mapping 2 |  | [DB](#wordata-jpa-db(2nd)) |

---

### WORDATA_Demo

사용된 DB나 따로 설정할 것은 폴더 내부 [README](/WORDATA_Demo/README.md) 참조

WORDATA_Demo를 위해 Clone할 경우 이클립스에서 Import시에는 해당 폴더와 상위폴더 정도만 import하고 그 이외는 필요 없음

---

### test

간단하게 mybatis/MySQL 환경에서 Spring Security를 구현하기 위한 test DB로 진행하여 따로 설명은 없음.

---

## Wordata JPA DB(1st)

Wordata_JPA 를 위한 DB 페키지

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

---

## Wordata JPA DB(2nd)

Wordata JPA를 위한 DB





---

### JPA 1



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


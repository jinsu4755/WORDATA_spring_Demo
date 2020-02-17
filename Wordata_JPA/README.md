## JPA 1회차

[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)

- JPA 기초 배경 지식 정리

  - Domain Model

  - JPA Repository의 기본 메소드

  - JPA Entity class

    ```txt
    @Entity - JPA Entity class 앞에 선언
    
    @Id - PK에 해당하는 변수에 선언
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    기본키가 Auto Increment 필드이거나 Identity 필드인 경우에 선언
    
    @ManyToOne
    @JoinColumn(name = "bId")
    B b;
    해당 테이블(A)의 bId 필드가 FK이고, 해당 FK가 B 테이블의 레코드 1개를 가리키면,
    A테이블에 int bId를 만드는 대신 B 객체 b를 만들고 해당 어노테이션 선언
    두 테이블의 관계에 따라 상황에 맞게 사용
    ```

    

  - Eager Loading / Lazy Loading

    ```txt
    A와 B가 있고 두 테이블이 다대일 관계(A에서는 B가 1개 B는 A가 여러개 조회)일경우
    JPA Entity에서 설명처럼 bId대신 해당 객체 변수를 만들 경우 아래와 같은 일이 자동으로 일어난다.
    
    1. DB에서 A레코드 조회
    2. A Entity class Object 생성
    3. A Entity class Object에 조회된 A레코드가 채워진다.
    4. 여기서 A의 bId FK가 가리키는 B의 레코드도 같이 조회
    5. B Entity class Object 생성
    6. B Entity class Object에 조회된 B레코드가 채워짐
    7. 해당 B 객체가 A객체의 객체변수로 대입된다.
    
    DB에서 A레코드 조회시
    1~7 절차가 자동으로 실행되는 정책을
    Eager Loading 이라고 한다
    
    DB에서 A레코드 조회시
    1~3 절차만 즉시 일어나고 4~7은 A클래스의 getB()가 실행되기 까지 실행을 무시하는 정책이
    Lazy Loading이다
    
    ```

    

- JPA application.properties 설정

```
# 일반 설정
server.address=localhost
server.port=8080

# API 호출시, SQL 문을 콘솔에 출력한다.
spring.jpa.show-sql=true

# MySQL 을 사용
spring.jpa.database=mysql

# MySQL 설정
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
# 해당 설정은 DB 필드 이름이 testName 형태이면 필요함 (camel case)
# 만약 test_name 형식이면 필요하지 않음
spring.datasource.url=jdbc:mysql://localhost:3306/employee1?useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
spring.datasource.username=user1
spring.datasource.password=test123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# MySQL 상세 지정
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
```

- REST API와 RestController 

  - REST API URL시 주의점

    ```txt
    - query string 사용하지 않기
    ex) student?id=3 (X) | student/3 & Request Method = GET
    
    - 동사 사용하지 않기
    ex) studentDel?id=3 | student/3 & Request Method = DELETE
    ```

    

[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)
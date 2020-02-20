## JPA 2회차

[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)

> let) 다음과 같은 DB가 있다.
>
> A 테이블과 B테이블이 있음
>
> B와 A는 일대다 관계이고
>
> A 테이블의 bId필드는 B를 가리키는 FK

- Single-Valued Association 구현

  ```java
  // B와 A의 관계 구현은 외래키(bId)가 포함된 A객체에 b 맴버 변수를 구현하는 것이 기본
  //ex)
  @Entity public class A { 
      
      @Id 
      @GeneratedValue(strategy = GenerationType.IDENTITY) 
      int id; 
      String name; 
      
      @ManyToOne 
      @JoinColumn(name = "bId") 
      B b;
  }
  
  /*
  Entity class 사이의 일대다 관계를 구현할때 FK를 포함하는 Entity class에 @ManyToOne을 사용하여,
  상대측 Entity Object 하나를 구현하는 것이 기본
  */
  ```

  

- Collection-Valued Association 구현

  ```java
  /*
  선택적 구현 사항이며 B와 A관계를 구현할때 일대다 관계에서 일에 해당하는 B객체에 해당 테이블 목록에 해당하는 List<A> a; 맴버 변수를 구현 할 수 있다.
  B객체에서 A의 목록을 구할 필요가 있을 경우 구현
  */
  
  @Entity
  public class B {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      int id;
      String name;
      
      @OneToMany(mappedBy="b")
      List<A> a;
  }
  
  /*
  mappedBy="b"에서 b는 A Entity class에서 @JoinColumn이 붙은 멤버 변수를 가리킨다.
  
  B에는 A레코드를 가리키는 FK가 없다. A에서 B를 bId가리키는 FK가 있을 뿐임]
  
  B테이블 레코드 조회시 아래와 같은 절차 실행
  1. B 테이블에서 레코드 하나 조회
  2. B Object 하나 생성
  3. 조회된 레코드를 B Object에 할당
  4. 조회된 B레코드의 id 값과 bId 값이 일치하는 A레코드들이 조회됨
  5. A Object가 여러개 생성된다.
  6. 조회된 A레코드들이 A Object에 채워진다.
  7. A Object들이 List<A>에 add 된다
  8. List<A> Object가 B Object의 a 맴버 변수로 대입
  
  위 절차에서 1~3은 B레코드 조회하자마자 즉시 실행되지만, 4~8은 나중에 실행 될 수 있고 해당 부분은
  B Object의 getB() 메소드가 처음 호출 될 때 실행된다.
  */
  
  ```



### Eager Loading / Lazy Loading

- **Eager Loading의 장점**
  - 어짜피 DB에 연결해서 명령 실행후 데이터를 받아오니 필요할 것이라 생각되는 데이터들은 한번에 다 가져온다.
- **Eager Loading의 단점**
  - 한번에 가져온 데이터가 사용되지 않으면 자원 낭비
- **Lazy Loading의 장점**
  - 불필요하게 많은 데이터 조회를 줄일 수 있다
- **Lazy Loading의 단점**
  - DB에서 가져오지 않은 데이터가 필요한 것을 나중에 알면 다시 연결해서 가져와야함
  - 만약 후에 필요한 데이터가 많은 경우 DB에 자주 다시 연결하면서 시간이 걸림

#### 어떤 정책을 사용하는 것이 좋은가?

**Single-Valued Association 객체 생성은 Eager Loading일 가능성이 높다.**

**Collection-ValuedAssociation 객체 생성은 Lazy Loading일 가능 성이 높다.**

하지만 두 경우 모두 확실하게 해당 상황에서 실행되는 것은 아니며 **그럴 확률이 높다**는 것임. 

확실한건 JPA 엔진 구현하는 사람의 마음대로



어떤 정책을 사용하던 성능상의 차이가 있을 뿐 기능의 차이는 없다.

Lazy Loading / Eager Loading 정책의 차이는 언제 데이터 조회를 하는가 이며, Lazy Loading 정책은 데이터 조회를 가급적 미루는 것이므로 두 정책 모두 데이터를 사용할때는 언제나 그 데이터가 이미 조회되어 있으므로 기능 상의 차이는 없다.



#### 정책 설정하기

```java
@ManytoOne(fetch = FetchType.EAGER)

@OneToMany(fetch = FetchType.LAZY)

@OneToOne(fetch = FetchType.EAGER)
```

위와 같이 정책을 설정할 수 있으나 이 설정이 언제나 지켜진다는 보장은 없음

JPA엔진 구현자 마음대로



### Could not write JSON: Infinite recursion

```txt
s.e.ErrorMvcAutoConfiguration$StaticView : Cannot render error page for request [/api/departments] and exception [Could not write JSON: Infinite recursion (StackOverflowError); nested exception is com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion
```



**오류 발생 상황**

A class에서 @ManyToOne으로 B 맴버 변수 b를 구현

B class에서 @OneToMany로 List&lt;A&gt; 맴버 변수 a를 구현하면

A나 B 객체를 JSON 포멧으로 변환시 발생



**오류 발생 이유**

1. A객체를 JSON 포멧으로 변환시, b 멤버 변수도 포함
2. b멤버 변수 값은 B객체이고 이 값도 JSON 포멧으로 변환
3. B객체를 JSON으로 포맷할때 a 멤버 변수 값도 포함
4. a 멤버 변수는 List&lt;A&gt; 객체이고 해당 객체도 JSON 포멧으로 변환
5. List&lt;A&gt; 객체가 JSON으로 변환시 해당 객체에 포함된 A객체도 JSON포맷으로 변환
6. 1번 절차 다시 반복

따라서 1~6번 무한 재귀 이므로 Infinite recursion

```txt
com.wordata.test.domain.Department["employees"]
->org.hibernate.collection.internal.PersistentBag[0]
->com.wordata.test.domain.Employee["department"]
->com.wordata.test.domain.Department["employees"]
->org.hibernate.collection.internal.PersistentBag[0]
->com.wordata.test.domain.Employee["department"]
->com.wordata.test.domain.Department["employees"]
->org.hibernate.collection.internal.PersistentBag[0]
->com.wordata.test.domain.Employee["department"]
-> 생략...
->com.wordata.test.domain.Department["employees"]
->org.hibernate.collection.internal.PersistentBag[0]
->com.wordata.test.domain.Employee["department"]
->com.wordata.test.domain.Department["employees"]
->org.hibernate.collection.internal.PersistentBag[0]
->com.wordata.test.domain.Employee["department"]
->com.wordata.test.domain.Department["employees"])] as the response has already been committed. As a result, the response may have the wrong status code.

```





해당 에러를 피하기 위해서 B 객체를 JSON으로 변환할때,

a멤버 변수를 포함하지 않게 해야한다.

객체를 JSON으로 변환시 무시해야할 멤버 변수에 @JsonIgnore 어노테이션을 사용할 수 있다.

해당 어노테이션을 a멤버 변수에 붙이면 에러는 발생하지 않는다.



[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)
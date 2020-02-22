## JPA 3회차

[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)

### 다 대 1 관계

ER 다이어 그램에서 다대일 관계 DB테이블을 구현할때,

다에 해당하는 일의 외래키가 포함되어야 하며,

해당 외래키는 일에 해당하는 테이블 기본키를 가리킨다.

다에 해당하는 테이블

```java
public class Registration { 
    @ManyToOne 
    @JoinColumn(name = "studentId") 
    Student student; 
}
```

일에 해당하는 테이블

```java
public class Student { 
    @JsonIgnore
    @OneToMany(mappedBy="student", fetch = FetchType.LAZY) 
    List<Registration> registrations; 
}
```



### equals method & hashCode method Override

ArrayList,  HashMap 등 컬렉션 객체에, 어떤 클래스 객체를 보관하기 위해서는 그 클래스의 equals method와 hashCode method를 override 해야한다.

</br>

만약 override를 하지 않을경우 Object class에 정의된 equals method가 상속되고 

Object class의 equals method는 equality를 비교하지 않고 identity를 비교한다.

그렇기 때문에 내용이 동일한 두 객체를 비교해도 false가 된다

</br>

</br>

HashMap 같은 해시 테이블 자료구조를 활용하여 구현된 컬렉션 객체에 클래스 객체를 보관할 때, 그 클래스의 hashCode method가 호출 된다.

hashCode method를 override하지 않을 경우 Object class의 hashCode method가 상속되고 

Object class의 hashCode method는 객체의 내용을 사용하여 해시 값을 계산하지 않고,

객체의 identity 정보를 사용하여 해시 값을 계산한다.

그렇기 때문에 내용이 동일한 두 객체의 해시 값이 동일하지 않다

</br>

내용이 동일한 두 객체의 해시 값은 같아야 하므로 해당 메소드 들을 재정의 해야한다.

</br>

</br>

### Lombok

lombok은 getter, setter method를 자동으로 생성해주는 도구 이며 equals와hashCode method도 자동으로 구현해준다.

클래스 앞에 @Data 어노테이션을 사용하기만 하면 된다.

```java
@Data 
public class Student { 
    int id; 
    String name; 
}
```

equals, hashCode 메소드를 구현할 때

객체의 내용에 해당하지 않는 멤버 변수의 값은 제외해야 한다

```java
@Data 
@EqualsAndHashCode(exclude="temp") 
@ToString(exclude="temp") 
public class Student { 
    int id; 
    String name; 
    int temp; 
}
```

위의 경우 temp 변수를 무시하고 equals, hashCode, toString 메소드가 생성된다.



Entity class 사이의 관계를 구현한 멤버 변수는  equals, hashCode, toString method 구현시 제외해야 한다.

예를 들어 Student 클래스의 id, name, departmentId 멤버 변수는 Student 객체의 내용에 해당한다.

그런데 Student 테이블과 Department 테이블의 관계를 구현하면서

추가된 department 멤버 변수는 Student 객체의 내용이 아니다.

이 멤버 변수는 equals, hashCode, toString 메소드를 구현할 때 제외해야 한다.



### Lambda expression 문법과 Stream API를 이용해보기

Stream은 자바8 부터 추가된 기능

컬렉션, 배열등의 저장 요소를 하나씩 참조하며 함수형 인터페이스(람다식)을 적용하며 반복적 처리를 할 수 있게 해주는 기능

```java
@RequestMapping("student/{id}/courses") 
public List<Course> studentCourses(@PathVariable("id") int id) { 
    Student student = studentRepository.findById(id).get(); 
    List<Course> list = new ArrayList<Course>(); 
    for (Registration r : student.getRegistrations())
        list.add(r.getCourse()); 
    return list; 
}
```

다음과 같은 코드를 아래와 같이 바꾼다.

```java
@RequestMapping("student/{id}/courses") 
public Stream<Course> studentCourses(@PathVariable("id") int id) { 
    return studentRepository
        .findById(id).get() 
        .getRegistrations() 
        .stream() 
        .map(s -> s.getCourse()); 
}

/*
studentRepository.findById(id)

Optional<Student> 객체가 리턴된다.


.get()

Optional<Student> 객체 내부에 들어있는 Student 객체가 리턴된다.


.getRegistrations()

Student 클래스의 메소드이다. 학생의 수강신청 목록이 리턴된다. List<Registration>


.stream()

List<Registration> 객체로부터, Registration 객체 스트림(stream)을 생성하여 리턴한다.
리턴되는 객체는 Stream<Registration> 이다.
Stream API 메소드를 사용하려면, 객체 목록으로부터 객체 스트림(stream)을 생성해야 한다.


.map(r -> r.getCourse())

Registration 객체 스트림의 Registration 객체 각각에 대해서

람다식 r -> r.getCourse() 을 실행한다.

이 람다식이 리턴하는 Course 객체를 모아서 Stream<Course> 스트림을 리턴한다.

*/
```





[뒤로가기](https://github.com/jinsu4755/WORDATA_spring_Demo)
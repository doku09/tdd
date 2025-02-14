#  JUnit 정리
## 1. JUnit이란?
CRUD와 같은 작은 테스트 묶음을 수동으로 실행하는 것은 비교적 쉬운 일이지만, 같은 테스트를 반복적으로 실행하는 것은 비효율적이다.  
반복적인 작업을 자동화하기 위해 **켄트 벡(Kent Beck)** 과 **에릭 감마(Erich Gamma)** 는 장거리 비행 중에 `JUnit`이라는 단위 테스트 프레임워크를 만들었다.
---
## 단위 테스트(Unit Test)
단위 테스트는 **개별적인 작업 단위의 동작을 검사하는 테스트**를 의미한다.  
이때, **테스트 대상은 다른 작업의 완료에 직접적으로 의존하지 않아야 한다.**

### 단위 테스트 프레임워크 없이 테스트하는 경우
```java
public class CaculatorTest {
    private int errorCnt = 0;
    
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculaotr.add(10, 50);
				
        if (result != 60) {
            throw new Exception();
        }
    }
	
    public static void main(String[] agrs) {
        CalculatorTest test = new CalculatorTest();
        try {
            test.testAdd();
        } catch (Throwable e) {
            test.errorCnt++;
            e.printStackTrace();
        }
        if(test.errorCnt>0) {
            throw new Exception("에러 갯수" + errorCnt);
        }
    }
}

```
### 문제점
- `try catch`문이 많아지면서 코드가 복잡해진다. 
- 한눈에 어떤 테스트가 성공하고 실패 했는지, 어떤 에러가 발생했는지도 파악하기가 어렵다.

### **단위 테스트 프레임워크**가 따라야 하는 세가지 규칙
1. 단위 테스트는 다른 단위 테스트와 독립적으로 실행되어야 한다. 
2. 프레임워크는 각 단위 테스트의 오류를 파악하여 알려주어야 한다. 
3. 어떤 테스트를 실행할지 쉽게 정의할 수 있어야 한다. 


### JUnit을 사용한 단위 테스트

```java
    public class CalculatorTest {
	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		calculator.add(10,50);
		assertEquals(60, result, 0);
	}
}
```
### JUnit을 사용했을때 장점 
- main 메서드에서 실행하지 않고 클릭만 하면 자동으로 테스트가 실행된다. 
- 테스트 코드를 작성하기 쉽다. 
- 한눈에 테스트의 결과를 파악할 수 있고, 어떤 테스트가 통과했고 실패했는지, 왜 실패했는지를 파악할 수 있다.

### 테스트를 하는 이유 
- 다른 기능을 추가하거나 기능이 수정될때 기존에 만들어둔 테스트가 통과되는지 확인할 수 있다. 

### 궁금한점 
- 테스트 클래스는 왜 public과 디폴트 접근 제어자로 제한을 두었는가? 자바의 리플렉션떄문에??

***

## 2. JUnit 핵심
1. JUnit은 테스트 메서드의 격리성을 보장한다. 
2. 테스트 코드에서 의도치 않은 부수효과를 방지하기위해, @Test 메서드를 호출하기 전에 테스트 클래스 인스턴스를 매번 새로만든다. 
3. 그래서 각 테스트는 인스턴스 변수를 공유할 수 없다.

### 테스트의 생애주기
- @BeforeAll, @BeforeEach, @Test, @AfterEach, @AfterAll

### @DisplayName
- 어떤 테스트인지 작성하는데 사용된다. 
- 테스트 목적을 알려 줄수 있는 완전한 문장 수준으로 적는것이 일반적이다.
- 따로 적지 않으면 메서드명으로 리포트한다.

**그 외 어노테이션**<br>

Annotation| Description 
---|----
`@Disabled`| 테스트 하지 않을 메서드에 사용한다.<br> 테스트 하지 않을 이유에 대해 명시할 수 있다.<br>ex) @Disabled("기능 개발중")
`@Tag`| 태그를 사용해 테스트를 그룹으로 묶고 특정 카테고리만 수행할 수 있다.

### 단언문
결과값을 검증하기 위해서는 Assertions 클래스에서 제공하는 단언문 메서드를 사용한다. 
### 자주 사용하는 단언문 메서드
단언문 메서드|활용 목적
--|--
`assertAll`|인자로 전달된 여러 executable객체 중 어느것도 예외를 던지지 않는다고 단언한다.
`assertArraysEquals`|예상 배열과 실제 배열이 동등하다고 단언한다.
`assertEquals`|예상값과 실제값이 동등하다고 단언한다. 
`assertX(...,String message)`|실패했을 경우 meesage를 테스트 프레임워크에 전달하는 단언문
`assertX(..., Supplier<String> messageSupplier` | 실패했을때 messageSupplier를 테스트 프레임워크에 전달하는 단언문 

### `assertAll`
- 일부 단언문이 실패하더라도 모든 단언문을 항상 검증한다.
- 첫번째 파라미터인 heading은 assertAll 메서드 내에서 단언문이 어떤일을 하는지 알려준다. 또한 파라미터로 전달된 executable객체 중 하나가 예외를 던지는 경우 표시할 메시지이기도 하다.

### 람다식을 파라미터로
- `assertTrue` 메서드를 사용하면 조건이 참인지 검증한다. 
- `assertFalse` 메서드를 사용하면 조건이 거짓인지 검증한다.
- `assertNull` 메서드를 사용하여 객체가 존재하는지 검증할 수 있다. 

```java
assertTrue(userServiceTest.findItem(), // 만족하면
() -> "조회요청한 사용자가 있는지 확인") // 호출하지 않는다.
```
<br>

### 제한 시간내에 작업을 수행할수 있는지 확인하는 메서드

메서드|설명
|--|--|
|assertTimeout|시간을 초과해도 진행되며 초과시간을 메시지로 알려준다.
|assertTimeoutPreemptively|메서드 시간이 지나면 테스트 객체를 중지시키고 지정시간안에 완료되지 못했다고 알려준다.

### 예외확인 메서드 `assertThrows`
```java
Throwable thrwoable = assertThrows(NoJobException.class,
	() -> systemUnderTest.run(1000)); //검증 후 에러시 예외반환
assertEquals("테스트 대상 시스템은 현재 작업이 없는지 확인",throwable.getMessage()); //예외가 맞는지 검증

```
### 가정문 
- 외부환경이나 제어할 수 없는 시간대,날짜 문제탓에 테스트가 실패할 수도 있다. 전제조건이 충족이 되어야 테스트가 실행 되어야 할때는 **가정문**을 사용한다.

```java
@BeforeEach
void setup() {
	assumeTrue(environment.isWindow()); // OS가 윈도우 일때만 테스트를 실행한다.
}

@Test
void testNoJobToRun() {
	assumingThat(() -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),() -> assertFalse(systemUnderTest.hasJobToRun)); //자바 버전이 기대하는 버전 일때만 assertFalse 단언문을 실행한다.
}
```
### JUnit5의 의존성 주입 
- 이전버전의 JUnit과는 다르게 JUnit5부터는 생성자와 메서드 파라미터를 가질수 있도록 허용했지만 의존성 주입으로 해결해야 한다는 점이 다르다.
기본으로 사용하는 파라미터 리졸버 3개 
1. `TestInfoParameterResolver`
   - TestInfoParameterResolver를 사용하면 테스트 클래스 생성자나 테스트 메서드에서 TestInfo 객체를 파라미터로 사용할 수 있다. TestInfo는 실행할 테스트의 디스플레이 네임,테스트 클래스,테스트 메서드,관련 태그등에 관한 정보를 가져온다.
```java
@BeforeEach
	void setUp(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();
		assertTrue(displayName.equals("display name of the method") || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
	}
```

2. `TestReporterParameterResolver`
   - TestReporterParameterResolver는 TestReporter 객체를 파라미터로 사용할 수 있다. 
   - TestReporter는 쉽게 제공

3. 















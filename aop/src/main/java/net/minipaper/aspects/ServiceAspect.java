package net.minipaper.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pointcuts">aop-pointcuts</a>
 *
 * <p>
 * AspectJ Pointcut 표현식(aspectj expression), execution, within, bean
 * <p>
 * pointcut :  이란 충고를 받을 메소드를 정의한 것이다.
 * execution: 메소드 실행 결합점(join points)과 일치시키는데 사용된다.
 * within: 특정 타입에 속하는 결합점(join point)을 정의.
 * <p>
 * execution(public * *(..)) : public 메소드가 포인트 컷
 * <p>
 * execution(* min.spring.aop.*.*(..)) : min.spring.aop 패키지의 모든 메소드가 포인트 컷
 * <p>
 * execution(* min.spring.aop..*.*(..)) : min.spring.aop 패키지와 하위 패키지의 모든 메소드가 포인트 컷
 * <p>
 * execution(public void insert*(..)) : public 에 리턴값, 패키지명 없고 메서드 이름은 insert 로 시작, 인자값은 0개 이상인 메서드가 포인트 컷
 * <p>
 * execution(* min.spring.aop.*.*()) : 리턴형 관계없고 min.spring.aop 패키지의 모든 클래스, 인자값이 없는 모든 메서드가 포인트 컷
 * <p>
 * execution(* min.spring.aop..*.*(..)) : 리턴형 관계없고 min.spring.aop 패키지 및 하위 패키지에 있는 모든 클래스,  인자값이 0개 이상인 메서드가 포인트 컷
 * <p>
 * execution(* delete*(*)) : 메서드 이름이 delete 으로 시작하는 인자값이 1개인 메서드가 포인트 컷
 * <p>
 * execution(* delete*(*,*)) : 메서드 이름이 delete 로 시작하는 인자값이 2개인 메서드가 포인트 컷
 * <p>
 * execution(* onj*(Integer, ..)) : 메서드 이름이 onj 로 시작하고 첫번째 인자값의 타입이 Integer, 1개 이상의 매개변수를 갖는 메서드가 포인트 컷
 * <p>
 * within(min.spring.aop.*) : min.spring.aop 패키지 내의 모든 메소드가 포인트 컷
 * <p>
 * within(min.spring.aop..*) : min.spring.aop 패키지 및 하위 패키지의 모든 메소드가 포인트 컷
 *
 * @within : 주어진 어노테이션을 사용하는 타입으로 선언된 메소드
 * <p>
 * @annotation : 타겟 메소드에 특정 어노테이션이 지정된 경우, @annotation(org.springframework.transaction.annotation.Transactional) :
 * Transactional 어노테이션이 지정된 메소드 전부
 * <p>
 * bean(abcJava*) : 이름이 abcJava 로 시작되는 모든 빈의 메소드가 포인트 컷
 * <p>
 * bean(*dataSource) || bean(*DataSource) : 빈 이름이 “dataSource” 나 “DataSource” 으로 끝나는 모든 빈의 메소드가 포인트 컷
 * <p>
 * !bean(abcJava) : abcJava 빈을 제외한 모든 빈의 메소드가 포인트 컷
 */
@Component
@Aspect
@Slf4j
public class ServiceAspect {

  // net.minipaper 패키지 밑에
  @Pointcut("within(net.minipaper..*)")
  private void inPackage() {
  }

  // Service 이름의 빈을 가진 메서드
  @Pointcut("bean(*ServiceImpl)")
  private void serviceBean() {
  }

  // net.minipaper 패키지 밑에 Service 이름의 빈을 가진 모든 메서드
  // @Around("within(net.minipaper..*) && bean(*ServiceImpl)") // Expression 으로 바로 조합
  @Around("inPackage() && serviceBean()") // pointCut 을 등록해놓고 조합하는 방법
  public Object checkTime(ProceedingJoinPoint proceedingJoinPoint) {
    Object result = null;
    try {
      MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
      Method method = signature.getMethod();
      long startTime = System.currentTimeMillis();
      result = proceedingJoinPoint.proceed();

      log.info("[AOP] 작업 시간 {}.{} : {} ms", proceedingJoinPoint.getTarget().getClass().getSimpleName(), method.getName(),
          System.currentTimeMillis() - startTime);
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return result;
  }
}

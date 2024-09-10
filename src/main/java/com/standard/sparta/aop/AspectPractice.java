package com.standard.sparta.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Aspect : Advice 와 pointcut 을 하나로 묶은 모듈
 */
@Slf4j
@Aspect
public class AspectPractice {

    /**
     * 서비스(Service) 패키지 기반
     */
    @Pointcut("execution(* com.standard.sparta.service..*(..))")
    private void serviceLayer(){}

    /**
     * Annotation 범위 기반
     */
    @Pointcut("@annotation(com.standard.sparta.annotaion.TrackTime)")
    private void trackTimeAnnotation(){}


    @Around("trackTimeAnnotation()")
    public Object adviceAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            log.info("::: ExecutionTime: {}ms :::", executionTime);
        }
    }
//
//    @Around("serviceLayer()")
//    public Object advicePackageMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//
//        try {
//            Object result = joinPoint.proceed();
//            return result;
//        }finally {
//            long endTime = System.currentTimeMillis();
//            long executionTime = endTime - startTime;
//
//            log.info("::: ExecutionTime: {}ms :::", executionTime);
//
//        }
//    }
//
//    /**
//     * @Before
//     */
//    @Before("serviceLayer()")
//    public void beforeMethod(){
//        log.info("::: BEFORE :::");
//    }
//
//    /**
//     * @AfterReturning
//     * 메서드가 정상적으로 실행 완료된 후에 동작
//     */
//    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
//    public void afterReturningMethod(Object result){
//        // result 관련 로직 사용하면 됨.
//        log.info("::: AFTER RETURNING :::");
//    }
//
//    /**
//     * @AfterThrowing
//     * 메서드가 실행 중 예외가 발생한 경우 동작
//     */
//    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
//    public void afterThrowingMethod(Throwable ex){
//        // ex 예외 발생했을 때 필요한 로직 작성
//        log.info("::: AFTER THROWING :::");
//    }
//
//    /**
//     * @After
//     * 메서드 종료 후 무조건 동작
//     */
//    @After("serviceLayer()")
//    public void afterMethod(){
//        log.info("::: AFTER :::");
//    }
//
//    /**
//     * @Around
//     * 가장 강력함, 전체 흐름을 제어할 수 있음
//     */
//    @Around("serviceLayer()")
//    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("::: BEFORE :::");
//
//        try {
//            Object result = joinPoint.proceed();
//            log.info("::: AFTER RETURNING :::");
//            return result;
//        } catch (Throwable e) {
//            log.info("::: AFTER THROWING :::");
//            throw e;
//        } finally {
//            log.info("::: AFTER :::");
//        }
//    }
}

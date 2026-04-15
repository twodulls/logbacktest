# logbacktest

Java 프로젝트에서 `System.out.println()` 대신 **SLF4J + Logback**을 사용한 로깅 샘플 코드입니다.

## 개요

콘솔 출력 시 `System.out.println()`을 직접 사용하는 대신, `logger.debug()` / `logger.info()` / `logger.error()` 등의 로거 메서드를 활용하는 방법을 보여주는 예제 프로젝트입니다.

## 기술 스택

| 항목 | 버전 |
|------|------|
| Java | 1.8 |
| SLF4J | 1.7.21 |
| Logback | 1.1.7 |
| JUnit | 4.12 |
| Maven | 빌드 도구 |

## 프로젝트 구조

```
logbacktest/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/test/log/
        │       └── LogSample.java       # 로깅 샘플 메인 클래스
        └── resources/
            └── logback.xml              # Logback 설정 파일
```

## 로깅 레벨

SLF4J는 다음 5가지 로그 레벨을 지원합니다 (낮은 순서 → 높은 순서):

| 레벨 | 메서드 | 용도 |
|------|--------|------|
| TRACE | `logger.trace()` | 가장 상세한 디버깅 정보 |
| DEBUG | `logger.debug()` | 개발 시 디버깅 정보 |
| INFO | `logger.info()` | 일반적인 정보 메시지 |
| WARN | `logger.warn()` | 경고 메시지 |
| ERROR | `logger.error()` | 오류 메시지 |

## 주요 코드

### LogSample.java

```java
private static final Logger logger = LoggerFactory.getLogger(LogSample.class);

logger.trace("trace");
logger.debug("debug");
logger.debug("debug {} {}", stringMsg, integerMsg);  // 파라미터 바인딩
logger.info("info");
logger.warn("warn");
logger.error("error");
```

> `{}` 플레이스홀더를 사용하면 문자열 연결(+) 없이 파라미터를 전달할 수 있어 성능상 유리합니다.

### logback.xml

```xml
<configuration scan="true" scanPeriod="30 seconds">
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}] [%-5p] %C.%M[%L] %m%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

**로그 출력 패턴 설명:**

| 패턴 | 설명 |
|------|------|
| `%d{yyyy-MM-dd HH:mm:ss}` | 날짜 및 시간 |
| `%-5p` | 로그 레벨 (5자리, 왼쪽 정렬) |
| `%C` | 클래스 전체 경로 |
| `%M` | 메서드명 |
| `%L` | 라인 번호 |
| `%m` | 로그 메시지 |
| `%n` | 줄바꿈 |

**출력 예시:**
```
[2026-04-15 10:30:00] [DEBUG] com.test.log.LogSample.main[15] debug
[2026-04-15 10:30:00] [DEBUG] com.test.log.LogSample.main[16] debug Test 123
[2026-04-15 10:30:00] [INFO ] com.test.log.LogSample.main[17] info
[2026-04-15 10:30:00] [WARN ] com.test.log.LogSample.main[18] warn
[2026-04-15 10:30:00] [ERROR] com.test.log.LogSample.main[19] error
```

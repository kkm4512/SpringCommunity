# 이런것을 멀티 스테이지 빌드 라고한다
# 빌더타임때 jar파일을 미리만들어두고, 런타임때 그저 사용하기만함
FROM gradle:7.6-jdk17-alpine as builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 호스트 머신의 현재 디렉토리 파일들을, 도커 컨테이너 내부 /build 에 저장
COPY . /build
# gradled을 사용하여 빌드하되 테스틑하지말고, build는 병렬로 사용하여 성능 항샹시키기
RUN gradle build -x test --parallel

# APP
FROM openjdk:17.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# Djava,Dsun 은 성능최적화를 위한 JVM 옵션
ENTRYPOINT [                                                \
    "java",                                                 \
    "-jar",                                                 \
    "-Djava.security.egd=file:/dev/./urandom",              \
    "-Dsun.net.inetaddr.ttl=0",                             \
    "app.jar"              \
]
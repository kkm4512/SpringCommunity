# APP
FROM openjdk:17.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# Djava,Dsun 은 성능최적화를 위한 JVM 옵션
ENTRYPOINT [                                                \
    "java",                                                 \
    "-jar",                                                 \
    "-Djava.security.egd=file:/dev/./urandom",              \
    "-Dsun.net.inetaddr.ttl=0",                             \
    "app.jar"              \
]
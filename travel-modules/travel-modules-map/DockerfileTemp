FROM openjdk:11.0.14
VOLUME /tmp
ARG VERSION

COPY travel-modules-map/target/travel-modules-map-${VERSION}.jar /ashes/travel-modules-map.jar

#定义时区参数
ENV TZ=Asia/Shanghai
#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone
ENV JAVA_OPTS "-Xms128m -Xmx256m -XX:PermSize=64M -XX:MaxPermSize=128M"
ENV NACOS_SERVER_ADDR "127.0.0.1:8848"
ENV NACOS_NAMESPACE "public"
ENV NACOS_USERNAME "nacos"
ENV NACOS_PASSWORD "nacos"
ENTRYPOINT java ${JAVA_OPTS} -Dspring.cloud.bootstrap.enabled=true -Djava.security.egd=file:/dev/./urandom -jar /ashes/travel-modules-map.jar

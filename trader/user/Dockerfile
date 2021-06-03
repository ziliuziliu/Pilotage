FROM digitalgenius/alpine-jdk
VOLUME /tmp
RUN mkdir /app
ADD target/user-1.0-SNAPSHOT.jar /app/app.jar
ADD runboot.sh /app
ENV TZ=Asia/Shanghai
RUN sh -c 'touch /app/app.jar' && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh

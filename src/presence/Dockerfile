FROM registry01.idc-sginfra.net/stove-platform/stove-tomcat:jdk11-t8.5.78-1.2.4

ENV JAR_PATH /stove/deploy/distove/presence.jar
ENV TZ="Asia/Seoul"

MAINTAINER Cloudtech-Team <sgs_ct_t@smilegate.com>

USER root

ADD build/libs/presence.jar /stove/deploy/distove/presence.jar

RUN chown -R stove:stove /stove && \
    chmod -R 744 /stove/deploy

USER stove

CMD ["/stove/apps/startup.sh"]

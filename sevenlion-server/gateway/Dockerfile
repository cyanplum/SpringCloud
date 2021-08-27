FROM  sevenlion-jdk1.8

MAINTAINER sevenlion

ADD /home/sevenlion/cloudSystem/back/gateway/gateway-1.0-SNAPSHOT.jar /home/sevenlion/cloudSystem/back/
#公开端口
EXPOSE 7777

#设置启动命令
CMD ["nohup","java","-jar","/home/sevenlion/cloudSystem/back/gateway-1.0-SNAPSHOT.jar","&"]




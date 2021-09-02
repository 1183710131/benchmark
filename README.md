# 裂变拉人benchmark

业务逻辑
有一个电商的互动活动，活动要求参加的用户A先完成3个任务才能有活动资格，达到资格条件后需要邀请3个其他玩家给他助力，助力人数达到活动要求后用户A有一次抽奖的机会，抽奖的奖品有红包和优惠券，可以不中奖。


工程说明
https://help.aliyun.com/document_detail/99943.html
配置说明：数据库采用的是MySQL 8 ，数据库管理工具采用DBeaver， IDE为IDEA 2019
首先按照链接说明完成2步前提条件配置，否则会导致启动失败

内容获取：数据库sql文件和源码工程在GitHub中可以获得
GitHub地址：github.com/1183710131/hellotest/tree/main

运行说明：
本地执行数据库文件：将SQL文件直接拖拽到Dbeaver中，设置好数据库存储位置，点击执行SQL脚本，执行完毕后刷新，可以在DBeaver中看到对应的数据库导入成功了新的表和数据。

当数据库用MySql 8，在URL处需要定义时区，推荐使用'?serverTimezone=GMT' 的时区设置，否则无法连接成功。

将项目拉取到本地环境，如果没有被识别为maven项目，需要先将项目识别为maven工程，等待导入依赖完成。
运行时先启动providerApplication,再启动customerApplication，可以在浏览器中通过 127.0.0.1:8080 查看服务列表，获知具体的服务名和详情。 

Q&A
Q1:如果项目没有被识别为maven项目怎么办？
A1:右键点击pom.xml文件，将文件设置为maven项目，等待导入依赖完成。
Q2:启动时如果出现diamond client can not connect to Diamond Server报错

A2:请检查是否成功启动了轻量级配置及注册中心。配置链接如下
启动轻量级配置及注册中心

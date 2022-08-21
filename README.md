模块说明：

    nacos
        common --系统公共模块，按需引入
            core --通用实体类
            dynamicmybatis --mybatis动态数据源切换，默认引入 aop + mybatis，（不切换数据源的时候不要引入）
            japidoc --japidoc生成离线接口文档，无需引入
            minio --minio分布式文件管理系统配置
            redis --redis分布式缓存配置
            openfeign --openfeign远程调用配置
            web --web全局返回。默认引入core依赖
            xxljob --xxl-job分布式任务调度平台配置
        gateway --gateway网关
        system --业务系统
            rabbitmq --消息队列系统
            ums --用户中心系统
---
spring cloud：nacos+gateway+loadbalancer+openfeign

半ORM框架：mybatis

关系型数据库：mysql8

数据库连接池：hikari

分布式缓存：redis

分布式文件系统：minio

分布式任务调度：xxl-job

消息队列：rabbitmq

日志分析：ELK（Elasticsearch、Logstash、Kibana）
# spring-read-write-splitting
spring + aop + mybatis 实现事务级别的读写分离

基于spring aop 及 org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource实现动态数据源切换

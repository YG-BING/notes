# Log4j2 笔记

## 1、配置

1. 引入依赖

   ```xml
   <!-- log4j2依赖 -->
   <dependency>
     <groupId>org.apache.logging.log4j</groupId>
     <artifactId>log4j-core</artifactId>
     <version>2.19.0</version>
   </dependency>
   <dependency>
     <groupId>org.apache.logging.log4j</groupId>
     <artifactId>log4j-slf4j2-impl</artifactId>
     <version>2.19.0</version>
   </dependency>
   ```

2. 配置文件

   <font color=red>log4j2的日志文件名需要指定为"log4j2.xml"，放在resources文件夹的根目录下</font>

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   
   <!--
       日志级别以及优先级排序: FATAL > ERROR > WARN > INFO > DEBUG > TRACE
       不同级别的日志文件中只能展示>=当前日志级别的日志。比如说设置的日志级别是info，那么日志文件中只能展示fatal，error，warn等级别的日志，而其他级别的日志则不会展示
   -->
   
   <!--
       configuration:
           （1）status：用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出
           （2）monitorInterval：自动检测配置文件中的配置项，并重新配置的间隔时间（单位：秒）
   -->
   <configuration monitorInterval="5">
   
       <!-- 定义全局变量 -->
       <Properties>
           <!-- 格式化输出：
               %date：日期
               %thread：线程名
               %level：日志级别
               %msg：日志消息
               %n；换行符
               %logger{1024}：名字最长1024个字符
           -->
           <property name="LOG_PATTERN" value="【日期】%date{yyyy-MM-dd HH:mm:ss.SSS} 【线程名】%thread 【日志级别】%level 【路径】%logger{1024} 【日志信息】%msg%n" />
           <!-- 定义日志存储的路径 -->
           <property name="FILE_PATH" value="./logs" /> <!-- 项目根目录的logs文件夹下 -->
           <property name="FILE_NAME" value="ssmTemplate" />
       </Properties>
   
       <appenders>
           <!-- 控制台日志输出配置 -->
           <console name="Console" target="SYSTEM_OUT">
               <!--输出日志的格式-->
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <!--控制台只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
           </console>
   
           <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用-->
           <File name="Filelog" fileName="${FILE_PATH}/test.log" append="false">
               <PatternLayout pattern="${LOG_PATTERN}"/>
           </File>
   
           <!-- 打印出所有的info及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileInfo" fileName="${FILE_PATH}/info.log" filePattern="${FILE_PATH}/backup/info/${FILE_NAME}-INFO-%d{yyyy-MM-dd}_%i.log.gz">
               <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，默认是1 hour-->
                   <TimeBasedTriggeringPolicy interval="1"/>
                   <SizeBasedTriggeringPolicy size="10MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
   
           <!-- 打印出所有的warn及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileWarn" fileName="${FILE_PATH}/warn.log" filePattern="${FILE_PATH}/backup/warn/${FILE_NAME}-WARN-%d{yyyy-MM-dd}_%i.log.gz">
               <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，默认是1 hour-->
                   <TimeBasedTriggeringPolicy interval="1"/>
                   <SizeBasedTriggeringPolicy size="10MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
   
           <!-- 打印出所有的error及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
           <RollingFile name="RollingFileError" fileName="${FILE_PATH}/error.log" filePattern="${FILE_PATH}/backup/error/${FILE_NAME}-ERROR-%d{yyyy-MM-dd}_%i.log.gz">
               <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
               <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
               <PatternLayout pattern="${LOG_PATTERN}"/>
               <Policies>
                   <!--interval属性用来指定多久滚动一次，默认是1 hour-->
                   <TimeBasedTriggeringPolicy interval="1"/>
                   <SizeBasedTriggeringPolicy size="10MB"/>
               </Policies>
               <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
               <DefaultRolloverStrategy max="15"/>
           </RollingFile>
   
       </appenders>
   
       <!--Logger节点用来单独指定日志的形式，比如要为指定包下的class指定不同的日志级别等。-->
       <!--然后定义loggers，只有定义了logger并引入的appender，appender才会生效-->
       <loggers>
   
           <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
           <logger name="org.mybatis" level="info" additivity="false">
               <AppenderRef ref="Console"/>
           </logger>
           <!--监控系统信息-->
           <!--若是additivity设为false，则 子Logger 只会在自己的appender里输出，而不会在 父Logger 的appender里输出。-->
           <Logger name="org.springframework" level="info" additivity="false">
               <AppenderRef ref="Console"/>
           </Logger>
   
           <root level="info">
               <appender-ref ref="Console"/>
               <appender-ref ref="Filelog"/>
               <appender-ref ref="RollingFileInfo"/>
               <appender-ref ref="RollingFileWarn"/>
               <appender-ref ref="RollingFileError"/>
           </root>
       </loggers>
   
   </configuration>
   ```



## 2、使用

```java
//创建日志对象
private Logger logger = LoggerFactory.getLogger(当前类的Class对象);
```

```java
// info等级日志
logger.info();

// warn等级日志
logger.warn();

// error等级日志
logger.error();
```



## 3、注意

在log4j2中设定日志等级之后，那么生成的日志文件中只有当前及以上等级的日志才会被添加

比方说：info.log文件的日志等级是log，warn.log文件的日志等级是warn，error.log文件的日志等级是error。那么info中只会出现info及以上的日志，warn中只会出现warn及以上的日志，error中只会出现error及以上的日志。
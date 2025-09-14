# SpringBoot-RocketMq



## 一、`SpringBoot`整合`RocketMq`

### 1、依赖

```xml
<!-- RocketMq场景启动器 -->
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>2.3.1</version>
</dependency>
```



### 2、配置文件

<font color=red>注意：必须要配置生产者，不然启动报无法注入`RocketMQTemplate`的错误。</font>

```yaml
rocketmq:
  name-server: 192.168.75.201:9876
  # 生产者配置
  producer:
    group: test-producer-group-1
    # 消息发送超时时间
    send-message-timeout: 3000
    # 消息最大长度4M
    max-message-size: 4096
    # 消息发送失败重试次数
    retry-times-when-send-failed: 2
    # 异步消息发送失败重试次数
    retry-times-when-send-async-failed: 2
```



### 3、生产者

配置完上述两步后，SpringBoot启动之后会自动的在容器中放入name为`rocketMQTemplate`的Bean，详见RocketMq自动配置类`org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration#rocketMQTemplate`。

```java
@Component
@Slf4j
public class SbDemoProducer {

    // 自动注入RocketMQTemplate
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendStringMsg(String topic, String msg) {
        // 使用rocketMQTemplate发送消息
        rocketMQTemplate.convertAndSend(topic, msg);
    }
}
```



### 4、消费者

- 消费者需要实现`RocketMQListener`接口实现`onMessage`方法。
  1. `RocketMQListener`的泛型：生产者发送消息时传入的`payload`的类型，即上述生产者`rocketMQTemplate.convertAndSend(topic, msg);`中的`msg`的类型。
  2. `onMessage`方法：消费者收到Broker消息推送后消费消息时需处理的逻辑。
- 消费者组件上添加`@RocketMQMessageListener`注解，其中的`topic`和`consumerGroup`是必填值。
  1. `topic`：消费者订阅消息的主题
  2. `consumerGroup`：当前消费者所在的消费者组
  3. `selectorType`：过滤消息的过滤类型，默认是`SelectorType.TAG`，可选`SelectorType.SQL92`
  4. `selectorExpression`：过滤消息的过滤表达式，例如`TAG`过滤的`tagA || tagB`，`SQL`过滤的`TAGS is not null and TAGS in ('tagA','tagB')`
  5. `consumeMode`：消费方式。默认是并发消息`ConsumeMode.CONCURRENTLY`，可选顺序消息`ConsumeMode.ORDERLY`
  6. `messageModel`：消费模式。默认是集群模式`MessageModel.CLUSTERING`，可选广播模式`MessageModel.BROADCASTING`



```java
@Component
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "test-consumer-group-1")
@Slf4j
public class SbDemoConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("received message: {}", s);
    }
}
```



### 5、整合测试

- 测试类

  ```java
  @SpringBootTest
  public class RocketmqTests {
  
      @Resource
      private SbDemoProducer sbDemoProducer;
  
      @Test
      public void test1() {
          sbDemoProducer.sendStringMsg("test-topic-1", "hello world");
          System.out.println(" send successfully~~ ");
      }
  }
  ```

- 测试结果

  | ![image-20241020121443876](./assets/image-20241020121443876.png) |      |      |
  | ------------------------------------------------------------ | ---- | ---- |


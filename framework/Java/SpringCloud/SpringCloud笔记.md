# SpringCloud 笔记

<font color=pink>以下所有内容基于如下版本~~</font>

| 技术栈                | 版本         |
| --------------------- | ------------ |
| `Java`                | `JDK 17`     |
| `SpringBoot`          | `3.3.0`      |
| `SpringCloud`         | `2023.0.3`   |
| `SpringCloud-Alibaba` | `2023.0.1.2` |

[<font color=pink>Spring_Cloud练习项目仓库</font>](https://gitee.com/yg-bing/sc_template.git)



## 一、SpringCloud 架构

1. 服务注册与发现

   - `Eureka`
   - <font color=red>`Consul`</font>
   - `Etcd`
   - <font color=red>`Nacos`</font><font color=pink>`(alibaba)`</font>
2. 服务调用和负载均衡

   - <font color=red>`OpenFeign`</font>

   - <font color=red>`Spring-Cloud-LoadBalancer`</font>

   - <font color=red>`Dubbon`</font>
3. 分布式事务

   - <font color=red>`Seata`</font><font color=pink>`(alibaba)`</font>
   - `LCN`
   - `Hmily`
4. 服务熔断和降级

   - <font color=red>`Resilience4j`</font>
   - <font color=red>`Sentinel`</font><font color=pink>`(alibaba)`</font>
5. 服务链路追踪

   - <font color=red>Micrometer Tracing</font>
6. 服务网关

   - <font color=red>`Spring Gateway`</font>
7. 分布式配置管理

   - <font color=red>`Nacos`</font><font color=pink>`(alibaba)`</font>
   - <font color=red>`Consul`</font>
8. 分布式锁
   - <font color=red>`Redission`</font>



## 二、SpringCloud 服务注册/分布式配置

### 1、`consul`

[consul笔记]: ./tech/Consul.md

### 2、`nacos`

[nacos笔记]: ./tech/Nacos.md




## 三、服务调用和负载均衡

### 1、`LoadBalancer`

[Spring-Cloud-LoadBalancer笔记]: ./tech/Spring-Cloud-LoadBalancer.md

### 2、`OpenFeign`

[Spring-Cloud-OpenFeign笔记]: ./tech/Spring-Cloud-OpenFeign.md

### 3、`Spring-Inteface-Clients`

| `Spring-Inteface-Clients`将对`OpenFeign`进行取代，官方不在提供`OpenFeign`更新，只提供基本的bug修复 |
| ------------------------------------------------------------ |
| ![image-20240822163931516](./assets/image-20240822163931516.png) |

### 4、`Dubbon`

[Dubbon笔记]: ./tech/Dubbon.md



## 四、服务熔断与降级

### 1、`Resilience4j`

[Resilience4j笔记]: ./tech/Resilience4j.md

### 2、`Sentinel`

[Sentinel笔记]: ./tech/Sentinel.md



## 五、服务链路追踪

### 1、`micrometer tracing`

[MicrometerTracing笔记]: ./tech/Micrometer-Tracing.md



## 六、服务网关

### 1、`Spring Gateway`

[SpringGateway笔记]: ./tech/SpringGateway.md



## 七、分布式事务

### 1、`Seata`

[Seata笔记]: ./tech/Seata.md



## 八、分布式锁

### 1、`Redission`

[Redission笔记]: ./tech/Redission.md


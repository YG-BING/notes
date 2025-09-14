# SpringCloud 知识点



## 1、`bootstrap.yaml`和`application.yaml`的区别

| 两种配置文件                                                 |
| ------------------------------------------------------------ |
| `bootstrap.yaml`                                             |
| ![image-20240819195243673](./assets/image-20240819195243673.png) |
| `application.yaml`                                           |
| ![image-20240819195303622](./assets/image-20240819195303622.png) |

- `bootstrap.yaml`优先级高于`application.yaml`。配置文件加载顺序如下：

  1. `bootstrap.properties `
  2. `bootstrap.yml`
  3. `application.properties`
  4. `application.yml`

  <font color=pink>由于存在如上的加载顺序问题，因此在`bootstrap.yaml`中无法使用`${}`获取`application.yaml`中的配置，反之则可以。</font>

- 当`bootstrap.yaml`和`application.yaml`存在相同的配置的时候，以`bootstrap.yaml`中的为准。`bootstrap.yaml`中的配置不会被覆盖，必定生效。

- 当使用`Spring cloud`配置中心(`consul`或`nacos`)的时候，配置中心的配置需要添加到`bootstrap.yaml`中以便在应用程序启动时提前获取远程配置用于启动项目。

  | `consul`官网提醒                                             |
  | ------------------------------------------------------------ |
  | ![image-20240819201250146](./assets/image-20240819201250146.png) |



## 2、客户端负载均衡与服务端负载均衡

- **客户端负载均衡**

  当客户端发起请求时，客户端首先会从远程的服务注册中心获取所有的服务列表，然后根据这个服务列表和负载均衡算法，筛选出一个服务来处理本次请求。

  也就是说此时负载均衡是在客户端处触发的，由客户端决定选择哪个服务来处理。

  常见技术`Spring Cloud LoadBalancer`

- **服务端负载均衡**

  当客户端发起请求时，请求会由安装在服务器中的负载均衡器进行统一处理，由服务器中的负载均衡器计算得出由哪个服务器处理本次请求。

  也就是说此时负载均衡是在服务器端触发的，由服务器决定客户端使用哪个服务。

  常见技术`Nginx`



## 3、
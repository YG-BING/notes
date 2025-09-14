# 	MyBatisPlus



## 一、`SpringBoot`整合`MyBatisPlus`

### 1、依赖

```xml
<!-- oracle依赖 -->
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <scope>runtime</scope>
</dependency>
<!-- lombok依赖 -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<!-- mybatis-plus 场景启动器 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.7</version>
</dependency>
```



### 2、配置文件

```yaml
mybatis-plus:
  # xml路径
  mapper-locations: classpath*:/mapper/**/*.xml
  global-config:
    db-config:
      # 全局默认主键策略，默认为雪花ID，若表中设置了自增，则生成的实体自动添加自增ID属性，参考 TestDelete
      id-type: ASSIGN_ID
      # 全局逻辑删除的实体字段名（不配置不启用）
      logic-delete-field: deleted
      # 逻辑已删除值(默认为 1)
      logic-delete-value: 1
      # 逻辑未删除值(默认为 0)
      logic-not-delete-value: 0
  configuration:
    # 驼峰转下划线（默认）
    map-underscore-to-camel-case: true
    # 日志输出
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 配置别名包路径
  type-aliases-package: com.yg.sb_template.entity,com.yg.sb_template.domain
```



### 3、配置类

详见``MybatisPlus`拦截器`



## 二、`MybatisPlus`常用注解

### 1、@TableName

用于映射表名



### 2、@TableId

用于声明主键，MybatisPlus默认会使用`id`作为主键



### 3、@TableFiled

用于声明普通字段



### 4、@TableLogic

用于声明逻辑删除字段



### 5、@Version

用于声明乐观锁字段

进行更新操作的时候会将该字段与数据库中的相应字段的值进行比较，值一样更新成功，并且原本的值加一。否则更新失败。



## 三、`MybatisPlus`主键策略

### 1、内置主键策略

- `@TableId(type = IdType.AUTO)`

  数据库有主键自增时使用

  添加数据后实体类中会自动装入数据库生成的ID

- `@TableId(type = IdType.INPUT)`

  必须手动添加`id`字段，不然报错

- `@TableId(type = IdType.NONE)`

  以手动填写的为准，不手动填写MybatisPlus自动生成雪花ID

- `@TableId(type = IdType.ASSIGN_ID)`

  MyabtisPlus默认主键策略。

  以手动填写的为准，不手动填写自动生成雪花ID

- `@TableId(type = IdType.ASSIGN_UUID)`

  以手动填写的为准，不手动填写自动生成`UUID`



### 2、自定义主键策略

<font color=pink>实现`IdentifierGenerator`j接口重写`nextId()`方法</font>

```java
// @Component
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {
    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        String bizKey = entity.getClass().getName();
        log.info("bizKey:{}", bizKey);
        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String name = (String)metaObject.getValue("name");
        final long id = al.getAndAdd(1);
        log.info("为{}生成主键值->:{}", name, id);
        return id;
    }
}
```



## 四、`MybatisPlus`拦截器

### 1、内置拦截器

```java
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加`分页`插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 添加`攻击SQL阻断解析器`（防止全表更新与删除）
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 添加`乐观锁`插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```



### 2、自定义拦截器

详见`Mybatis.md#拦截器`

[Mybatis.md#拦截器]: ../myBatis/MyBatis.md#六、Mybatis拦截器



## 五、`MybatisPlus`自动装填

- 定义字段填充处理器

  ```java
  /**
   * MyabtisPlus日期自动填充处理器
   */
  @Component
  public class DateAutoFillHandler implements MetaObjectHandler {
  
      /**
       * 创建时自动插入
       */
      @Override
      public void insertFill(MetaObject metaObject) {
          strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
          strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
      }
  
      /**
       * 更新时自动插入
       */
      @Override
      public void updateFill(MetaObject metaObject) {
          // MetaObjectHandler默认不覆盖已存在值的字段，所以需要手动设置为null
          metaObject.setValue("updateTime", null);
          strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
      }
  }
  ```

  

- 设置填充字段

  ```java
  @EqualsAndHashCode(callSuper = true)
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class DateEntity extends IdEntity {
  
      /**
       * 创建时间
       */
      @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  
      /**
       * 更新时间
       */
      @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
  }
  ```

  




# 	MyBatis_Plus 笔记

## 1、MyBatis_Plus 简介

```
（1）低侵入式：只做增强不做改变【当系统引入一个新的框架之后对原本的系统产生了较小的影响，并且引入的框架也比较容易移除，此时就成这样的框架具有低侵入式，反之则是高侵入式】
（2）支持主键自增：默认使用雪花算法生成主键
（3）mybatis_plus执行的时候先分析实体类，将实体类中的属性抽取出来作为表的字段。如果实体类中的属性名和数据库中的字段名不一致，就可能出现问题。所以可以使用@TableId和@TableField实现表字段名和实体类属性名的映射关系
```

<hr></hr>

## 2、MyBatis_Plus 安装

```xml
注意：
	·Jdk8及以上一般使用mybatis_plus3版本，因为3支持使用lambda表达式
	·Jdk7则可以考虑使用mybatis_plus2
	
<!--一、mybatis-plus启动器依赖-->

<!--（1）SpringBoot版-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>

<!--（2）Spring版-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.1</version>
</dependency>

<!--二、mysql 驱动依赖-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

<hr></hr>

## 3、MyBatis_Plus 配置

```yaml
#端口号
server:
  port: 8088

#数据源
spring:
  datasource:
  	#数据源类型：SpringBoot默认的数据源类型是hikari数据源（常用的还有阿里的druid数据源）
    type: com.zaxxer.hikari.HikariDataSource
    #数据源驱动类（注意：mysql5：com.mysql.jdbc.Driver mysql8：com.mysql.cj.jdbc.Driver）
    driver-class-name: com.mysql.cj.jdbc.Driver
    #mysql8 数据库连接url
    url: jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true
    #用户名
    username: root
    #密码
    password: 123456
```

<hr></hr>

## 4、mybatis_plus 日志

```yaml
*--springboot配置文件中添加--*

#sql日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

<hr></hr>

## 5、mybatis_plus XML扫描配置

```yaml
#XML扫描配置
mybatis-plus:
  mapper-locations: classpath*:/mapper/**/*.xml
```

<hr></hr>

## 6、mybatis_plus 别名配置

```yaml
#别名配置
mybatis-plus:
  type-aliases-package: Mybatis_Plus.entity

（注意）如果需要配置多个别名路径，多个路径之间用逗号分隔即可
```

<hr></hr>

## 7、mybatis_plus 条件构造器

```
*--条件构造器Wrapper的体系--*

1、wrapper
	1.1 AbstractWrapper
		1.1.1 LambdaQueryWrapper
		1.1.2 LambdaUpdateWrapper
		1.1.3 QueryWrapper（用于查询）
		1.1.4 UpdateWrapper（用于增删改）
```

```
注意
	·使用Wrapper的对象调用以下方法之后返回的仍是原来的wrapper对象，所以可以进行链式调用
	·链式调用多个下列方法没有特殊情况都是默认用and连接的。比方说wrapper.eq().ne()...所有添加的条件是用and连接的
```



```
1、allEq(Map map,boolean flag);
	作用：同时在where后添加多个and条件
	map：比如map=name：1，pass：2; 则转化后就是where name = 1 and pass = 2;
	flag:比如map=name：1，pass：null; 如果为true 则转化后为where name = 1 and pass is null;如果为false 则转化为where name = 1;也就是不将null作为条件.
```

```
2、eq(a,1); => where a=1;
	补充：
		·eq与eqallEq的区别就是一次只能添加一个条件
```

```
3、ne(a,1); => where a!=1;
```

```
4、gt(a,1); => where a>1;
```

```
5、ge(a,1); => where a>=1;
```

```
6、lt(a,1); => where a<1;
```

```
7、le(a,1); => where a<=1;
```

```
8、between(a,1,10); => where a between 1 and 10;
```

```
9、notBetween(a,1,10); => where a not between 1 and 10;
```

```
10、like(a,1); => where a like '%1%';
```

```
11、notLike(a,1); => where a not like '%1%';
```

```
12、likeLeft(a,1); => where a like '%1';
```

```
13、notLikeLeft(a,1); => where a not like '%1';
```

```
14、likeRight(a,1); => where a like '1%';
```

```
15、notLikeRight(a,1); => where a not like '1%';
```

```
16、isNull(a); => where a is null;
```

```
17、isNotNull(a); => where a is not null;
```

```
18、（1）in(a,1,2,3...);
	（2）in(a,Arrays.adList(1,2,3...));
	=> where a in (1,2,3);
	补充：
		·参数第二个参数可以是一个可变长参数，也可以是一个list集合
```

```
19、（1）notIn(a,1,2,3...);
	（2）notIn(a,Arrays.adList(1,2,3...));
	=> where a not in (1,2,3);
	补充：
		·参数第二个参数可以是一个可变长参数，也可以是一个list集合
```

```
20、inSql(a,sql语句); => where a in (sql);
	补充：
		·注意sql语句后面不能加分号
```

```
21、notInSql(a,sql语句); => where a not in (sql);
	补充：
		·注意sql语句后面不能加分号
```

```
22、groupBy(a,b,c...); => group by a,b,c...;
```

```
23、orderByAsc(a); => order by a asc;
```

```
24、orderByDesc(a); => order by a desc;	
```

```
25、last(sql); => 直接在sql的末尾拼接上sql。
	补充：
		·注意last只能使用一次，多次使用以最后一次为准
		·last有sql注入的危险，尽量减少使用
```

```
26、select(a,b,,c...); => select a,b,c... from table;
```

```
27、set(a,1); => update table set a = 1;
```

```
28、or(); => 使用or关键字拼接下一个条件
```

```
29、apply(sql语句,可变长参数列表); => 动态给sql传入参数

apply("a={0}",0,1,2); => a=0
apply("a=0 and b={2}",0,1,2); => a=0 and b=2
```



<hr></hr>

## 8、mybatis_plus 注解

```
（1）@TableName
	·添加在类上
	·value = 表名
	·设置实体类所对应的数据库中的表
```

```
（2）@TableId
	·添加在属性上
	.value = 主键名
	·mybatis_plus默认将"id"字段名作为主键（所以如果我们操作的表的主键不叫id那么就需要使用这个注解指定id）
```

```
（3）@TableFiled
	·添加在属性上
	·value = 字段名
	·mybatis_plus中对于含有下划线的字段名默认使用驼峰命名的方式转化成实体类的属性名，来达成字段名和属性名的映射关系
```

```
（4）@TableLogic
	·添加在属性上
	·将某个属性设置为逻辑删除字段，当使用删除操作的时候就不会真正的删除数据而是将该字段变为1（默认0代表未删除，1代表已删除）。当你进行查询的时候也会自动的将该字段等于1的数据排除
```



<hr></hr>

## 9、通用mapper方法

```
注意：
（1）以下方法来自mybatis_plus中的一个基础mapper：BaseMapper<T>。
（2）使用的时候只需要让自定义的类继承BaseMapper<T>，即可以使用这些方法
（3）泛型T就是实体类类型
```

```
（1）查询

a、根据id进行查询
mapper.selectById(id);

b、根据id集合批量查询
mapper.selectBatchIds(list);

c、根据map集合进行查询
mapper.selectByMap(map);

d、根据条件构造器查询满足条件的集合（查询结果不论是一个还是多个均以集合的形式返回）
mapper.selectList(wrapper);

c、根据条件构造器查询满足条件的一条数据（如果查询出多条数据会报错）
mapper.selectOne(wrapper);

d、根据条件构造器查询满足条件的数据的条数
mapper.selectCount(wrapper);
```

```
（2）插入

a、通过实体类插入一条数据
mapper.insert(entity);
```

```
（3）更新

a、通过实体类id更新数据
mapper.updateById(entity);

b、通过实体类和条件构造器更新数据
mapper.update(entity,wrapper);
```

```
（4）删除

a、通过id进行删除
mapper.deleteById(id);

b、通过id集合进行删除
mapper.deleteBatchIds(id集合);

c、通过map集合进行删除
解释：map集合多为<String,Object>,key就是表字段名，value就是字段值；比方说key="name"，value="123",意思就是删除name为123的那一条数据。map集合如果含有多个键值对的话，那么所有的键值对必须同时满足。
mapper.deleteByMap(map);

d、根据条件构造器进行删除
mapper.delete(wrapper);
```

<hr></hr>

## 10、通用service方法

```
注意
	·Mybatis_Plus中基础service接口为Iservice，基础service实现类为ServiceImpl
	·IService<T>:T为实体类型
	·ServiceImpl<M extends BaseMapper<T>,T>:M为实现BaseMapper接口的Mapper接口，T是实体类
	·使用：自定义service接口继承IService接口，自定义service接口的实现类要继承ServiceImpl以及实现自定义service接口
	比方说自定义UserService，则UserService要继承IService接口，继承IService接口就会继承接口中的所有方法。而UserServiceImpl要实现UserService所以就必然要实现IService接口中的所有方法，而ServiceImpl实现了IService接口中的所有方法，所以只要让UserServiceImpl继承ServiceImpl就相当于实现了IService接口中的所有方法
```



```
（1）插入

a、插入一条数据（插入成功返回true，失败则返回false）
save(entity);

b、批量插入数据（插入成功返回true，失败则返回false）
saveBatch(List<entity>);
注意：这种插入方式是一条sql插入多条数据。

c、批量插入数据（插入成功返回true，失败则返回false）
saveBatch(List<entity>,num);
注意：（1）这种插入方式是多条sql插入多条数据.（2）num的作用多条SQL插入时每条sql插入的条数
```

```
（2）删除

a、根据条件构造器删除（删除成功返回true，删除失败返回false）
remove(wrapper);

b、根据id进行删除（删除成功返回true，删除失败返回false）
removeById(id);

c、根据id进行批量删除（删除成功返回true，删除失败返回false）
removeByIds(List(id));

d、根据map集合进行删除
removeByMap(map);
```

```
（3）修改

a、根据条件构造器进行修改
update(wrapper);
注意：第一步通过条件构造器定位要修改的一条或者几条数据；第二步通过通过条件构造器中的set来设置要修改的字段值
```

```
（4）查询一条数据

a、根据id查询
getById(id);

b、根据条件构造器进行查询（如果查询出多条数据会报错）
getOne(wrapper);
```

```
（5）查询多条数据

a、查询所有数据
list();

b、根据条件构造器查询
list(wrapper);
```

```
（6）查询数量

a、查询所有条数
count();

b、根据条件构造器查询数据条数
count(wrapper);
```

<hr></hr>

## 11、mybatis_plus 五种主键策略

```
（1）主键自增
	a、数据库中主键必须自增
	b、@TableId(type = IdType.AUTO)
	c、如果用户手动设置了主键值，以设置的主键值为准，再次插入以手动插入值开始进行递增
```

```
（2）用户自定义
	a、用户输入的话以用户的为准，不输入如果字段设置了自增则自增，没有的话则报错
	b、@TableId(type = IdType.INPUT)
```

```
（3）无策略
	a、用户输入的话以用户输入的为准，没有输入的话不论字段是否设置主键自增，均自动生成雪花ID
	b、@TableId(type = IdType.NONE)
```

```
（4）雪花算法
	a、用户输入的话以用户输入的为准，没有输入的话以雪花算法生成的ID为主
	b、@TableId(type = IdType.ASSIGN_ID)
```

```
（5）UUID
	a、用户输入的话以用户输入的为准，没有输入的话以UUID生成的ID为主
	b、@TableId(type = IdType.ASSIGN_UUID)
```

<hr></hr>

## 12、mybatis_plus condition条件

```
使用场景：
	后台在接受前台传来的数据的时候，可能前台传来的是null或者空字符串，而对于null或者空字符串则不需要拼接条件
```

```
处理方式：

（1）通过if
if(字段值不为空){
	拼接条件构造器
}

（2）condition条件 
	condition其实和if一样就是一句返回为true或者false的判断语句
```

<hr></hr>

## 13、mybatis_plus 分页插件

（1）分页使用：

```
（1）构建配置类
@Configuration
public class MyBatisPlus_Config {
    @Bean
    public MybatisPlusInterceptor pageConfig(){
        //mybatis_plus拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //添加内部拦截器（数据库类型为mysql）
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }
}
```

```
（2）使用分页
·分页对象：Page page = new Page(1,2);		// 1：第几页 2：每页条数
·案例：userMapper.selectPage(page,null);	  // 最终的分页结果会被封装进page对象
```

（2）page对象的常用方法

```
（1）getPages();		获取所有页数
（2）getRecord();		获取当前页的所有记录
（3）getTotal();		获取总记录条数
（4）hasNext();		是否有下一页
（5）hasPrevious();	是否有前一页
```

（3）自定义SQL分页插件的使用

```
（1）书写自定义分页mapper方法的格式
Page<实体类> 方法名(Page<实体类名> page,其他自定义的参数);

（2）注意
	·返回值类型必须为Page<T>,而XML中的动态SQL的ResultType类型则为T类型
	·方法可以有多个参数，为了在XML动态SQL中不需要声明ParameterType可以使用@Para注解
	·方法的多个请求参数，第一个参数必须为Page<T>
```

<hr></hr>

## 14、mybatis_plus 代码生成器

```
（1）依赖
<!--mybatis_plus代码生成器依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.1</version>
        </dependency>
<!--freemarker模板依赖-->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.31</version>
        </dependency>
<!-- swagger依赖 （如果生成器中允许开启swagger则需要添加）-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.7.0</version>
        </dependency>
```

```
（2）代码生成器
注意：以下生成器只适用于mybatis-plus-generator版本为3.5.1及其以上

public class Mybatis_Plus_Generator {
    public static void main(String[] args) {
        //路径，用户名，密码
        FastAutoGenerator.create("jdbc:mysql://192.168.9.222:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "123456")
                .globalConfig(builder -> {
                    //作者
                    builder.author("Test")
                            // 开启 swagger 模式
                            .enableSwagger()
                            // 覆盖已生成文件
                            .fileOverride()
                            // 指定输出目录
                            .outputDir("D://");
                })
                .packageConfig(builder -> {
                    builder.parent("") // 设置父包名
                            .moduleName("Mybatis_Plus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("user")
                            // 设置带有以下前缀的表
                            .addTablePrefix("t_", "c_");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
```

<hr></hr>



## 常见问题

### 1、SpringBoot扫描mapper接口

```
（1）方法一
·启动类上添加@MapperScan()注解
.配置类上添加@MapperScan()注解
·@MapperScan使用：@MapperScan("包路径")；@MapperScan({"包路径1","包路径2"...})


（2）方法二
·mapper接口上添加@Mapper注解
```

### 2、自动装配mapper接口对象报红解决

```
情形：
@Autowired
private xxMapper mapper; //mapper报红

（1）@Autowired（require = false）

（2）在mapper接口上添加@Repository注解
```

### 3、update或insert返回主键ID

```
（1）在主键字段上添加@TableId主键
	作用：声明这个属性对应数据库中的主键。使用TableId注解主要是为了指明那些名字不是id的属性为注解
（2）Xml文件添加三个属性
	<insert id="insertTest" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
        insert into SYS_USER (user_name, user_pass, create_date)
        values (#{userName},#{userPass},#{createDate})
    </insert>
    a、useGeneratedKeys： 使用生成的主键
    b、keyColumn：主键字段名
    c、keyProperty：主键属性名
```

### 4、Java布尔类型数据存入数据库

```
Java中布尔类型的数据存入数据库：
	·· false：存入数据库时变成0
	·· true：存入数据库变成1
```

### 5、MybatisPlus指定数据库分页方言

```java
//mybatis-plus配置类

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

}
```


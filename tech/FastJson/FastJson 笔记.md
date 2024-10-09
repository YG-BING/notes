# FastJson 笔记

## 1、依赖

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.75</version>
</dependency>
```



## 2、对象和JSON之间的转换

```
*--对象转JSON字符串--*
String JSON字符串 = JSON.toJSONString(对象);

*--JOSN字符串转对象--*
类 对象 = JSON.parseObject(JSON字符串,类.class);
```



## 3、集合和JSON之间的转换

```
*--集合转JSON字符串--*
String JSON字符串 = JSON.toJSONString(集合);

*--JSON字符串转集合--*
集合类 集合 = JSON.parseArray(JSON字符串,集合元素类.class);
```


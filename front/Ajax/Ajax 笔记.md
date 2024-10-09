# Ajax 笔记













<font color=pink>`Ajax`全称`Asynchronous Javascript And XML`，意为异步的`JavaScript`和`XML`。</font>

<font color=red>**`Ajax`最主要的作用就是发送异步请求，在页面不刷新的情况下局部的更新页面。**</font>



## 一、Jquery版本

## （1）$.get();

> 结构

```javascript
$.get(
  url,
  data,
  function,
  dataType
);
```

> 说明

- `url`：请求路径

- `data`：请求参数（其中存放的数据相当于）

  1. `Json`对象：`{a:1,b:2}`，后台可使用`a`或`b`变量接收或者使用含有`a`和`b`成员的对象接收。

     <font color=red>注意：`Json`对象不等于`Json`字符串。在`JavaScript`中`{}`代表对象而`""`代表字符串。所以`{a:1,b:2}`属于`Json`对象，而`"{a:1,b:2}"`属于`Json`字符串。</font>

  2. 表单序列化字符串：`"a=1&b=2"`，后台可使用`a`或`b`变量接收或者使用含有`a`和`b`成员的对象接收。

     <font color=red>`a=1&b=2`可以从`jquery表单对象.serialize()`获取。</font>

- `function`：成功响应的回调函数

- `dataType`：返回数据类型。常见`json（json）`，`text（字符串）`，`blob（二进制）`

> 案例

```javascript
$.get(
  "http://localhost:8088/TestController1/getUserInfo",
  {"a":1},
  function (data) {
    
  },
  "json"
);
```



## （2）$.post();

<font color=pink>类比`(1)$.get()，但仅限于发送`post`请求。`</font>



## （3）$.ajax({});

<font color=pink>`$.ajax({})`是使用`jquery`发送`ajax`请求的一个通用方法。</font>

> 结构

```javascript
$.ajax({
	url:"http://localhost:8088/TestController1/getUserInfo",
	data:{},
	type:"POST",
	dataType:"json",
	contentType:"application/json",
	success:function (data) {
	
	},
	error:function (data) {
	
	},
	complete:function (data) {
	
	},
	async:true
});
```

> 说明

1. `url`：请求路径
2. `data`：请求数据
3. `type`：请求类型（get，post）
4. `contentType`：请求参数类型
5. `dataType`：后端返回数据类型
6. `success`：请求成功回调函数
7. `error`：请求错误回调函数
8. `complete`：请求完成回调函数（顺序在`success`或`error`之后）
9. `async`：同步请求/异步请求（默认是true异步请求，false同步请求）




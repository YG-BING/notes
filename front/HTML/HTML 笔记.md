# HTML 笔记

## 一、HTML简介

```
（1）html，超文本标记语言，本身并不属于编程语言，而是一种标记语言。
（2）html文件的后缀扩展名可以是html也可以是htm
（3）浏览器之所以可以解析html标签，主要是浏览器内核的存在。谷歌浏览器内核blink，safari是webkit。
（4）超文本的意思是超越文本的意思。不仅仅局限于文字，也可以是图片，音频，视频等等
（5）html的基本骨架
<!doctype>
<html>
	<head></head>
	<body></body>
</html>

***注意***
（1）<!doctype>本身并不属于html标签，它的主要作用就是告诉浏览器以什么版本的html去解析html文件
（2）body有默认宽度但是没有默认高度，高度是由其中的内容决定的
```



## 二、常用基本标签

### （1）h1-h6

```html
#标题标签

<h1>标题1</h1>
<h2>标题2</h2>
<h3>标题3</h3>
<h4>标题4</h4>
<h5>标题5</h5>
<h6>标题6</h6>
```

### （2）p

```html
#段落标签

<p>段落</p>
```

### （3）br

```html
#换行标签

<br/>
```

### （4）hr

```html
#水平线标签

<hr/>
```



## 三、特殊字符标签

### （1）空格

```
a、&nbsp;
	空格距离不固定，不推荐使用
b、&ensp;
	空格距离为一个字符或者半个汉字的距离，推荐使用
c、&emsp;
	空格距离为两个字符或者一个汉字的距离，推荐使用
```

```html
注意：
在html中直接使用空格，多个空格会被折叠成一个空格。
比方说：
 <p>多个空格        会被折叠为一个</p>
最终展示的是：多个空格 会被折叠为一个  发现多个空格，只剩下一个
```

### （2）大于小于

```html
#大于
&gt;

#小于
&lt;

#大于等于
&ge;

#小于等于
&le;
```



## 四、容器标签

### （1）div

```
特点：
（1）独占一行
（2）受宽高，内外边距影响
```

### （2）span

```
特点：
（1）一行可以存在多个span
（2）不受宽高和内外边距的影响，其大小取决于里面的内容
```



## 五、文本格式标签

### （1）加粗

```html
<b></b>

<strong></strong>
```

### （2）删除

```html
<s></s>

<del></del>
```

### （3）倾斜

```html
<i></i>

<em></em>
```

### （4）下划线

```html
<u></u>

<ins></ins>
```

### （5）下标

```html
<sub></sub>

比如：
2<sup>10</sup>  =>  2^10
```

### （6）上标

```html
<sup></sup>

比如：
log<sub>2</sub>
```



## 六、超链接标签

### （1）外连接

```html
<a href="https://www.baidu.com">外连接</a>
```

### （2）内连接

```html
<a href="../模板/模板.html">内连接</a>
```

### （3）空连接

```html
<a href="#">空连接</a>
```

### （4）target属性

```html
a、_blank（点击之后重新打开一个页面完成跳转）
<a href="https://www.baidu.com" target="_blank">跳转</a>

b、_self（点击后取代原来的界面实现跳转）
<a href="https://www.baidu.com" target="_self">跳转</a>
```

### （5）下载连接

```html
#通过download属性来实现

<a href="./img/blueloginbutton.png" download="图片.jpg">下载</a>

***注意***
*下载连接是通过href和download配合实现的。通过href指定下载文件，download指定下载后文件的名字*
```

### （6）title属性

```
#当鼠标经过连接的时候会有文字提示
```

### （7）图片/视频连接

```html
#使用a标签包裹图片或者视频，那么点击视频或者图片就是进行跳转

<a href="https://www.baidu.com"><img src="../../img/picture1.jfif"></a>
```

### （8）锚点连接

```html
#跳转页面后直接跳转到指定id的标签处

<a href="./锚点连接测试页面.html#test100">锚点连接</a>
```



## 七、图片标签

```
#图片标签的三个重要属性
（1）src
	添加图片的显示路径
（2）alt
	图片无法正常显示后的文字提示
（3）title
	鼠标经过图片后给的图片信息提示
```



## 八、表格标签

### （1）表格相关的标签

```html
1、<table></table>
表格标签

2、<tr></tr>
表格行标签

3、<td></td>
表格单元格标签
```

**补充**

```
#（1）去除表格单元格之间存在的空隙的方法
#（1）消除合并单元格之后的单元格边框变粗的情况

CSS:
table{
	border-collapse:collapse
}
```

### （2）合并单元格

1、行合并（rowspan）

```
a、找到需要合并的多个单元格，在最上边的单元格中写上
rowspan="合并单元格的数量"

b、删掉除最上边之外的所有单元格
```

2、列合并（colspan）

```
a、找到需要合并的多个单元格，在最左边的单元格中写上
colspan="合并单元格的数量"

b、删掉除最左边之外的所有单元格
```



## 九、列表标签

### （1）无序列表

```html
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
</ul>

***注意***
ul中只能放li，li中可以放其他标签
```

### （2）有序列表

```html
<ol>
  <li>1</li>
  <li>2</li>
  <li>3</li>
</ol>

***注意***
ol中只能放li，li中可以放其他标签
```

### （3）自定义列表

```html
<dl>
  <dt>文字</dt>
  <dd>说明1</dd>
  <dd>说明2</dd>
  <dd>说明3</dd>
</dl>

***注意***
dd解释说明dt
```



## 十、表单标签

### （1）form标签

```html
#三个属性#

示例：
<form action="./表单提交后页面.html" method="post" target="_blank">
  <input type="submit" value="登录">
</form>

1、action
表单数据提交到新页面的页面路径

2、method
表单数据提交的方式：get和post，默认为get

3、target
表单数据提交到新页面，新页面的打开方式：_blank和_self，默认是_self
```

### （2）input标签

```html
#不同的input标签#

1、普通文本框（text）
<input type="text">

2、密码框（password）
<input type="password">

3、单选框（radio）
<input type="radio" name="radio">
<input type="radio" name="radio">
注意：
a、单选框通过name属性将相同name的单选框化为一组，实现单选
b、通过checked实现默认选中

4、多选框（checkbox）
<input type="checkbox" name="checkbox">
<input type="checkbox" name="checkbox">
注意：
a、多选框通过name属性将相同name的多选框化为一组，实现多选
b、通过checked实现默认选中

5、普通按钮（button）
<input type="button" value="普通按钮">

6、重置按钮（reset）
<input type="reset" value="重置">

7、提交按钮（submit）
<input type="submit" value="提交">

8、图片提交按钮（image）
<input type="image" src="../../img/blueloginbutton.png" title="登录" alt="登录">
属性：
	type：image图片提交按钮
	src：图片路径
	title：鼠标经过图片文字提示
	alt：图片无法正常显示后的文字提示
```

```
***注意***
1、如果想进行提交操作，那么input就必须要有name，value可有可无。
2、通过placeholder属性可以实现在文本框中提前预设一部分内容。
```

### （3）label标签

```
#label标签的主要作用就是#
可以通过直接点击被label标签标注的文字来实现点击单选或者复选框

<label for="nan">男：</label><input type="radio" name="radio0" id="nan">
<label for="nv">女：</label><input type="radio" name="radio0" id="nv">

#注意：for属性指向的单选或者复选框的id#
```

### （4）select标签

```
#下拉菜单标签#

<select>
  <option selected>请选择</option>
  <option>下拉菜单1</option>
  <option>下拉菜单2</option>
  <option>下拉菜单3</option>
  <option>下拉菜单4</option>
</select>

#注意
1、select代表下拉菜单，option代表下拉菜单中的选项
2、通过selected实现默认选中某一个下拉菜单
```

### （5）textarea标签

```
#文本域标签#
<textarea></textarea>

#注意：可以通过resize:none;样式实现禁止文本域改变大小#
```



## 十一、其他标签

### （1）iframe标签

```
#iframe标签的主要作用就是在页面上单独开辟出一块区域显示一个网页#

###iframe的src属性控制iframe网页的显示内容###
```

```html
###示例###

<body>
<!--iframe-->
<div>
  <iframe src="" id="iframe"></iframe>
</div>
<!--操作按钮-->
<div id="operationBtn">
  <button id="btn1">测试页面1</button>
  <button id="btn2">测试页面2</button>
  <button id="btn3">测试页面3</button>
</div>

<script !src="">
  let btn1 = window.document.querySelector("#btn1");
  let btn2 = window.document.querySelector("#btn2");
  let btn3 = window.document.querySelector("#btn3");

  let frame = window.document.querySelector("#iframe");

  btn1.onclick = function () {
    frame.src = "./测试页面1.html"
  }

  btn2.onclick = function () {
    frame.src = "./测试页面2.html"
  }

  btn3.onclick = function () {
    frame.src = "./测试页面3.html"
  }
</script>
</body>
```

### （2）select标签

```html
*--作用--*
下拉菜单标签

*--使用--*
<select name="sel">
	<option value='1'>1</option>
    <option value='2'>2</option>
    <option value='3'>3</option>
</select>

*--注意--*
（1）select作为表单项来使用的时候，在数据提交时name值来自select，而value值来自option
（2）通过option中的selected来设定默认的下拉选项
（3）下拉菜单最常用的事件就是change事件
```


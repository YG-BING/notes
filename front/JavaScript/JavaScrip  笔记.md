# JavaScrip  笔记

## 一、JS基础

### 1、浏览器组成

```
#1、渲染引擎：内核
#2、JS引擎
```

### 2、Js代码的执行方式

```
#1、Js代码不需要编译
#2、Js代码由浏览器逐行解释执行

#注意：正是由于js这种逐行解释的特性，所以一旦某一行出现了错误，那么这行之后的所有语句都不会被执行
```

### 3、Js组成

```
#1、ECMA JavaScript（Js基础语法）
#2、DOM（文档对象模型）
#3、BOM（浏览器对象模型）
```

### 4、Js的三种书写位置

```
#1、行内
<button onclick="alert('点击了！')">点击</button>

#2、内部
a、第一种（head）
<!DOCTYPE html>
<html lang="en">
	<head>
		<script>
    		alert("开始了！");
  		</script>
	</head>
	<body>
	</body>
</html>
b、第二种（body）
<!DOCTYPE html>
<html lang="en">
	<head>
	</head>
	<body>
		<script>
    		alert("开始了！");
  		</script>
	</body>
</html>

###这两种方法需要注意的就是Js是逐行解释执行的，因此执行顺序是由上到下，所以第一种方法执行的优先级更高。但是如果是绑定事件的话，由于优先级较高的原因，执行到绑定事件的js代码时，还没有相应的节点，所以会导致事件绑定失败
###
###

#3、外部
a、第一种（head）
<!DOCTYPE html>
<html lang="en">
	<head>
		<script src='...'></script>
	</head>
	<body>
	</body>
</html>

b、第二种（body）
<!DOCTYPE html>
<html lang="en">
	<head>
	</head>
	<body>
		<script src='...'></script>
	</body>
</html>

###注意：
###
###
	..script标签既可以作为Js代码的书写标签使用（标签之间书写js代码），也可以作为外部js代码的引入标签使用
	..script标签一旦作为引入标签使用，那么此时再在其中书写js代码，其中的所有js代码全部失效；但是引入的外部js代码仍然有效
```

### 5、Js注释

```
#1、单行注释
//内容

#2、多行注释
/*内容*/
```

### 6、输入输出语句

```
#1、prompt('输入提示内容：');
	弹窗中输入的内容默认为String类型.用户输入值为函数返回值

#2、console.log();
	控制台打印
	补充：console.log();可以有多个参数，最终打印的就是用空格分隔的多个参数
	
#3、alert();
	弹窗警示
	
#4、console.dir(object);
	主要用来打印对象的信息（属性和方法）

#5、confirm("是否确定?");
	类似alert的浏览器弹窗，只不过confirm会有两个按钮（确定和取消），如果点击“确定”方法返回true，否则返回false
```

## 二、变量

### 1、变量的定义

```
#1、变量声明
var a;

#2、变量赋值
a = 1;

#3、变量定义
var a = 1;
```

### 2、同时声明多个变量

```
#1、
var a,b,c;

#2、
var a = 1,b = 2,c = 3;
```

3、变量声明的特殊情况

```
#1、只声明不赋值
var a；
console.log(a);			-- undefined

#2、不声明直接赋值
a = 10；					-- 变量晋升为全局变量

#3、直接使用未声明，未赋值的变量
报错
```

## 三、数据类型

### 1、数值型（Number）

```
#1、Js中数据前加0表示八进制

#2、Js中数据前加0x表示十六进制

#3、最大值和最小值
最大值：Number.MAX_VALUE
最小值：Number.MIN_VALUE

#4、无穷大和无穷小
无穷大：Infinity  -- 只要比Number.MAX_VALUE大，则最终全部输出Infinity
无穷小：-Infinity  -- 只要比Number.MIN_VALUE小，则最终全部输出-Infinity

#4、非数字（not a number）
NAN
引申：判断是否为非数字isNAN(值);
注意：
	（1）如果是数字字符串比如说'123',会被认为是数字，为false
	（2）空格会被认为数字，为false
	-- console.log(isNAN('123'));  -- false
	-- console.log(isNAN('123a'));  -- true
	-- console.log(isNAN(' '));  -- false
```

### 2、字符串型（String）

```
#1、外单内双，外双内单
情形：想输出（1'2'3），如果直接输入 "1"2"3"会报错
解决："1'2'3"

#2、转义字符
	a、\n：换行
	b、\"：一个双信号
	c、\'：一个单引号
	d、\\：一个单反斜杠
	
#3、字符串长度
	-- 属性length
	-- '123'.length -- 长度为3

#4、字符串拼接
	（1）+号拼接
	（2）开始拼接的字符串.concat(任意个数字符串);（因为JS中所有方法中都有一个默认的arguments数组储存入参）（使用concat拼接字符串需要注意,str.concat('a','b');拼接完之后是重新生成了一个新的拼接后的字符串，而str自始至终没有变化）
			-- js中任何一个方法都可以输入任意个数的实参
			-- 所有js方法中有一个arguments数组，可以获取输入的全部参数
```

### 3、布尔型（boolean）

```
#1、true进行运算的时候当1来使用
	false进行运算的时候当0来使用
	
#2、在JS中任何代表否定意味的值可以当作false来使用；任何代表肯定意味的词可以看作true来使用
```

### 4、undefined和null

```
#1、undefined+1   -- NAN
#2、null+1 		 -- 1
```

### 5、补充

#### （1）typeof判断数据的类型

<font color=red>typeof返回的是字符串。</font>

```
#1、console.log(typeof 12);   -- Number
#2、console.log(typeof '12'); -- String
...
```

#### （2）转化为字符串类型

```
#1、变量.toString();
注意：不能直接12.toString();,必须要使用对象a.tostring();因为字面量不是对象没有方法

#2、String(值);

#3、+拼接（拼接一个空字符串）
```

#### （3）转化为数值型

```
#1、Number(值);

#2、parseInt(值);
注意：parseInt在转变为数字的同时也会取整（不会四舍五入）

#3、parseFloat(值);
注意：parseInt在转变为数字的同时不会取整
```

## 四、运算符

### 1、算数运算符

```
#1.+
#2.-
#3.*
#4./	（不会和java中一样，如果双方如果全是整数结果也为整数的情况）
#5.%（取余）
```

### 2、自增，自减运算符

```
#1、++和--

#2、注意：
	++和--只能和变量进行使用，而不能和常量。比方说1++（错误），a++（正确）。
	因为a++相遇当a = a + 1；如果a是常量的话，那么是不可以给常量赋值的。
	
#3、使用：
	（1）a++或a--：先使用a后++或--
	（2）--a或++a：先++或--再使用a
```

### 3、比较运算符

```
特殊：
== 和 ===

（1）==
	18 == ‘18’   //true
（2）===（全等于）
	18 === ‘18’ //false
```

### 4、逻辑运算符

```
#1、短路且或
&& ||

#2、逻辑且或
& |

#3、非
！
```

### 5、三目运算符

```
#格式：
表达式?结果1：结果2；

#执行：
表达式为true则为结果1，否则为结果2
```



## 五、数组

### 1、数组创建

```
#1、new关键字
var ArrName = new Array();

#2、字面量创建
var arrName = [];
var arrName = [1,'2',true];   //Js数组中可以存放任意类型的数据
```

### 2、数组的长度

```
var arr = [];
console.log(arr.length);

#小知识：Js中数组的长度最大为2^32-1
```

### 3、数组转化为字符串

```
var arr = [1,2,3];
    arr.tostring();  //1,2,3

#常用
数组名.join('数组元素分隔符');
例子：var arr = [1,2,3];
	（1）arr.join('');			//123
	（2）arr.join('-');			//1-2-3
```

## 六、函数

### 1、创建函数

```
#第一种：
function fun(){}

#第二种：
var fun = function(){}

***注意***
（1）如果使用function fun(){}这种方式就不需要注意方法调用的顺序问题了。比方说
	node.onclick = fun;
	function fun(){}
	和
	function fun(){}
	node.onclick = fun;
	均可以正常调用
（2）如果使用var fun = function(){}这种方法则需要注意在调用函数的顺序问题。比方说
	node.onclick = fun;
	let fun = function(){}   -- 错误
	和
	let fun = function(){}
	node.onclick = fun;  -- 正确	
```

### 2、调用函数

```
#第一种：
function fun(){}

#第二种：
var fun = function(){}

#调用：
fun();
```

### 3、arguments

```
#arguments相当于一个数组，自动的存储所有传入的参数
```

### 4、函数默认值

Js中的函数可以为形式参数指定默认值。

如果有输入的话，那么就使用输入的值；如果没有输入的话，那么就使用默认值。

格式：function(a = 默认值){}

```javascript
function f(a = 100) {
    console.log('@',a);
}

//不输入参数。结果：打印 100
f();
//输入参数。结果：打印 1
f(1);
```

### 5、可变长参数

函数形参中可以使用...来将多个传入的参数封装到一个数组中

```javascript
/*
	定义一个带有可变长参数的函数
	name：普通参数
	params：可变长参数
	输入的第一个参数被name正常接受，之后不论输入多少个参数都会被收集到params数组中
*/
let fun = function (name,...params) {
    console.log(name,'@@@',params)
}
//结果：张三 @@@ [1,2,3]
fun('张三',1,2,3);
```



## 七、对象

### 1、创建对象

```
#1、第一种
var obj = {};

示例：
var obj = {
	a:1,
	b:'2',
	c:function(){
		console.log('c');
	}
}

#2、第二种
var obj = new Object();

示例：
var obj = new Object();
obj.a = 1;
obj.b = '2';
obj.c = function(){console.log('c');}
```

### 2、对象属性

<u>*访问对象属性*</u>

1. obj.a
2. obj['a']



## 八、DOM

### 1、DOM模型

```
#1、整个页面可以看作是文档节点（document）
#2、页面上的标签可以看作是元素节点（element）
#3、标签中的文本可以看作是文本节点
#4、标签的属性可以看作是属性节点
```

### 2、获取元素节点

#### （1）基本获取节点方法

```
#1、根据id
document.getElementById(id名);
某一个元素节点.getElementById(id名);

#2、根据标签名
document.getElementsByTagName(标签名);
某一个元素节点.getElementsByTagName(标签名);
	
#3、根据类名
document.getElementsByClassName(类名);
某一个元素节点.getElementsByClassName(类名);

#4、根据选择器
（1）#只能获取满足选择器的第一个元素节点
document.querySelector(选择器);
（2）#可以获取所有满足选择器的元素节点
document.querySelectorAll(选择器);

#5、获取特殊的body和html元素节点
（1）body：document.body;
（2）html：document.documentElement;
```

#### （2）获取父子节点

```
#1、获取子节点
节点.children;

#2、获取父节点
节点.parentElement; 
```

#### （3）获取兄弟节点

```
#1、获取弟弟节点
节点.nextElementSibling;

#2、获取哥哥节点
节点.previousElementSibling;
```

### 3、修改/获取节点内容

```
#1、innerText
修改：节点.innerText = '修改';
获取：console.log(节点.innerText);

#2、innerHTML
修改：节点.innerHTML = '修改';
获取：console.log(节点.innerHTML);

#注意：innerText和innerHTML最大的区别就是innerText不识别html标签，而innerHTML识别html标签
```

### 4、操作节点属性

```
#一、节点自带属性
#1、标签的直接属性（可以直接加在标签上的属性）
获取：节点.属性名;
赋值：节点.属性名;
示例：
	<input type="text" value="1" name="2" id="3">
	var inp = document.querySelector('input');
	inp.value;   //1
	inp.name;	 //2
	inp.id;		 //3
	inp.type;	 //'text'
	inp.style;

#2、标签的间接属性（style中的属性）
注意：
	（1）style本身属于标签的直接属性
	（2）通过节点和style修改的样式权重相当于行内样式表
获取：节点.style.style中的某个属性;
赋值：
	（1）节点.style.style中的某个属性 = '???';
	（2）节点.style = '???';
	
引申：
	每次通过style只能修改一个样式属性。如果想一下子添加多个样式属性，可采用以下两种方式：
	（1）节点.style = '整段的style属性值拼接的字符串';
	（2）节点.className = '类名';
		a、className是直接修改class类名
		b、className会将之前的类名覆盖，如果想保留之前的类名：节点.className(类名1 类名2 ...);
		
二、节点自定义属性
#1、获取自定义属性
节点.getAttribute(属性名);

#2、设置自定义属性
节点.setAttribute(属性名，属性值	);


***总结***
	（1）节点.属性的方法只能操作内置属性；不能操作自定义属性
	（2）节点.get/setAtrribute()的方法，不仅可以操作内置属性，也可以操作自定义属性（一种通用的方法）。
```

<font color='red'>注意：通过节点.style.属性名的方式只能获取行内style的属性（设置属性值不受行内还是行外的影响）</font>

```
比方说：
（1）<div id="cs" style=" background-color: red;">测试</div>
	此时console.log(cs.style.backgroundColor);   //red
	
（2）#cs{
	  background-color: red;
      width: 200px;
      height: 200px;
    }
    <div id="cs" style=" background-color: red;">测试</div>
    此时console.log(cs.style.backgroundColor);   //空 -> 代表无法获取非行内的style的属性值
```

### 5、节点的创建,追加,移除

```
#一、创建节点
document.createElement('标签名');	

#二、追加节点
父节点.appendChild(子节点);

#三、移除节点
节点.remove();   //该节点会被移除	
```

<hr></hr>

## 九、事件高级

### 1、事件注册

```
#第一种（传统方式）
	（1）静态事件注册：<button onclick='fun()'></button>
	（2）动态事件注册：节点.onclick = fun(){}

#第二种（事件监听）
节点.addEventListener(事件类型,事件方法)
	（1）事件类型：字符串；并且没有on；例如：click，blur，focus等
	（2）事件方法：func(){}
	
***注意***第一种和第二种方法的区别
（1）第一种方法一次只能添加一个事件方法。比方说a节点添加两个点击事件。
a.onclick = fun(){console.log(1);}
a.onclick = fun(){console.log(2);}
结果只有最后一个事件生效，也就是只打印了2
（2）第二种方法可以添加多个事件。比方说a节点添加两个点击事件。
a.addEventListener('click',func(){console.log(1);});
a.addEventListener('click',func(){console.log(2);});
结果是1和2均可以打印出来。
```

### 2、事件移除

```
#1.传统方式移除
	（1）添加：node.onclick = func(){}
	（2）移除：node.onclick = null;
#2、事件监听方式移除
	（1）添加：node.addEventListener('click',fun);
			let fun = function(){}
	（2）移除：node.removeEventListener('click',fun);
```

### 3、事件流

```
<html>
    <body>
        <button>按钮</button>
    </body>
</html>

#1、两个阶段
	（1）捕获阶段：从html->body->button（从上到下）为捕获阶段
	（2）冒泡阶段：从button->body->html（从下到上）为冒泡阶段
	注意：
		（1）Js中只能处于捕获或者冒泡其中的一个阶段，也就是说Js只在冒泡阶段才开始处理事件
		（2）传统事件只能处于冒泡阶段
		（3）事件监听即可处于捕获阶段也可以处于冒泡阶段，但是只能处于一个阶段。默认处于冒泡阶段
#2、事件监听的两个阶段
	（1）捕获阶段：node.addEventListener('click',function(){},true);
	（1）冒泡阶段：node.addEventListener('click',function(){},false);
```

### 4、事件对象

#### （1）获取事件对象

```
#1、传统事件
node.onclick = function(event){}

#2、事件监听
node.addEventListener('click',function(event){});
```

#### （2）事件对象的属性和方法

<font color = 'red'>注意以下的属性和方法均是事件对象的属性和方法</font>

​		A、属性	

```
#1.target
	target和this的区别：
	（1）target指的是触发事件的节点
	（2）this指的是绑定事件的节点
	例如
	<ul style="border: 1px solid black">
    	<li style="border: 1px solid black">1</li>
    	<li style="border: 1px solid black">2</li>
    	<li style="border: 1px solid black">3</li>
  	</ul>
  	
  	ul0.addEventListener('click',function (evt) {
      console.log(evt.target);
      console.log(this);
    });
    
    为ul绑定事件，当点击ul中的任何一个li的时候由于li没有绑定事件，向上冒泡，发现ul绑定了事件，此时就会执行ul的事件。此时this是绑定事件的节点的意思就是ul绑定了这个事件，所以this指的是ul。target指的是触发事件的节点的意思就是虽然事件是ul绑定的，但是是由li触发的事件，那么target指的就是li。
    
#2、type
	event.type返回的是事件的类型
	
#3、clientX和clientY（针对的是浏览器的显示区域）
	event.clientX
	event.clientY
	
#4、x和y（与clientX和clientY一样，针对的也是浏览器的显示区域）
	event.x
	event.y
```

​		B、方法

```
#1、阻止默认事件
	写法：event.preventDefault();
	理解：比方说：a标签天生带有点击事件（点击跳转），我们就可以为a标签添加一个点击事件，在这个点击事件中加上event.preventDefault();就可以阻止a标签点击之后的跳转。
	
#2、阻止冒泡行为
	写法：event.stopPropagation();
	理解：a包含b，a和b都添加了点击事件。这样我们在点击b之后就会进行冒泡触发a的事件。如果想阻断冒泡这种行为，只需要在想要阻断冒泡的节点的点击事件中写上event.stopPropagation();即可让事件传播到a时候停止传播。如果想在a节点上停止，就在a的事件中写上。（也就是说阻止冒泡的方法要加在要停止冒泡行为的节点上）
	
#3、***注意***
	不论是preventDefault()还是stopPropagation()，这两个方法均是事件对象的方法
```

### 5、事件委托

```
#1、事件委托原理：事件委托主要是利用了事件的冒泡阶段。主要应用于当一个容器有多个子节点的时候，为这多个子节点添加事件可以考虑只为父节点添加事件，这样通过冒泡就可以为每个子节点添加事件。避免通过循环添加事件效率低下的问题。

#2、例子：
	<ul style="border: 1px solid black">
    	<li style="border: 1px solid black">1</li>
    	<li style="border: 1px solid black">2</li>
    	<li style="border: 1px solid black">3</li>
  	</ul>
  	此时，为每个li添加事件的时候，可以只为ul添加事件，通过冒泡间接为li添加事件
  	
#3、***注意***：在为每一个字节点添加事件，为了体现不同节点的不同事件表现效果。可以通过event.target获取每次点击的事件，从而对事件进行区分。
```

### 6、常见鼠标事件

| 事件         | 作用                                   |
| ------------ | -------------------------------------- |
| onclick      | 鼠标点击事件                           |
| onblur       | 鼠标失去焦点事件                       |
| onfocus      | 鼠标获得焦点事件                       |
| onmouseover  | 鼠标经过事件                           |
| onmouseout   | 鼠标离开事件                           |
| onmousemove  | 鼠标移动事件（鼠标坐标发生改变后触发） |
| onmouseup    | 鼠标按下事件                           |
| onmousedown  | 鼠标弹起事件                           |
| onmouseenter | 鼠标经过事件                           |
| onmouseleave | 鼠标离开事件                           |

```
#注意：
（1）只有传统事件事件类型才需要加on，事件监听事件类型不需要加on
（2）同为鼠标经过事件的mouseover和mouseenter事件的区别：
比方说div2是一个大盒子，div1是一个小盒子，div2套了div1。此时如果为div2绑定了mouseover事件那么不论鼠标经过div2还是div2中的div1都会触发鼠标经过事件。（之所以会产生这种现象的主要原因就是事件冒泡，当经过div2中的div1的时候，发现div1没有绑定鼠标经过事件，然后事件向上冒泡，冒泡到div2之后发现有鼠标经过事件，然后就会触发事件）
而此时如果div2绑定的是mouseenter事件的话，当鼠标经过div2时候触发经过事件，而当经过div1的时候则不会触发鼠标经过事件，因为mouseenter会阻止冒泡事件
（3）同为鼠标离开事件的mouseout和mouseleave事件的区别（同上）
```



## 十、BOM

### 1、DOM和BOM总结

```
#1、DOM中的顶层对象是document，Bom中的顶层对象是window

#2、window中包含document。也就是说我们在dom中写的document.getElementById();相当于window.document.getElementById();

#3、在Js中声明的所有的全局变量和全局方法都可以看作是window的属性和方法。
注意：const和let声明的全局变量不属于window的属性，只有var定义的才属于

#4、在Js中一般在声明变量名的时候不适用name作为变量名字。主要是因为window对象中含有一个name属性，而由于window中的属性可以看作全局变量，再调用的时候可以直接写name，所有不建议起名为name。window中的name是一个空字符串。

#5、window在调用的时候可以省略
```

### 2、window事件

#### （1）页面加载事件（load）

```
#1、概念：
	html文件从上到下执行，如果我们为某个按钮添加一个点击事件，那么只有当先加载完这个按钮才可以使用js为它添加点击事件。如果在没有加载完按钮的情况下先为它添加点击事件，那么此时绑定的事件无效。
	***而页面加载事件就是在整个页面所有的节点元素加载完毕之后才会执行的一个事件。***
	
#2、使用:
	（1）传统事件注册
	window.onload = function(){}
	（2）事件监听注册
	window.addEventListener('load',function(){});
	
	传统事件注册和事件监听注册最大的区别是事件监听注册可以同时绑定多个事件；而传统事件注册同时只能绑定一个事件，绑定多个的话，最后绑定的会覆盖前面绑定的。
```

***补充***

```
#1、注意load是等着页面所有的元素（Dom节点，图片，flash动画）加载完毕之后才会执行的一个事件；如果页面上图片之类的元素很多加载事件较长就会影响用户体验。此时可以使用DOMContentLoaded事件就可以在页面的Dom节点加载完毕之后就可以执行的

示例：
（1）window.onDomContentLoaded = function(){}
（2）window.addEventListener('DOMContentLoaded',function(){});
```



#### （2）浏览器窗口大小变化事件（resize）

```
#1、概念：
	当浏览器窗口发生变化的时候就会触发事件
	常用于做响应式布局
	
#2、使用：
	（1）传统事件注册
	window.onresize = function(){}
	（2）事件监听注册
	window.addEventListener('resize',function(){});
	
#3、两个重要属性
	（1）window.innerWidth（浏览器宽）
	（2）window.innerHeight（浏览器高）
	通过这两个属性的变化进行响应式布局。
```

### 3、定时器

#### （1）setTimeout（）

```
#1、用法：
	window.setTimeout(方法，触发时长);
	执行：当经过‘触发时长’后，调用‘方法’（只执行一次）。

#2、注意：
	（1）触发时间可写可不写，如果不写默认就是0
	（2）触发时间的单位是毫秒
	（3）一个页面上往往有很多的定时器，可以通过为定时器起一个名字来进行区分。比方说
		var time1 = window.setTimeout(function(){},2000);
		var time2 = window.setTimeout(function(){},2000);
		通过time1和time2来进行区分不同的定时器
```

```
#定时器清除：
#1、用法：
	window.clearTimeout(定时器名字);
```

#### （2）setInterval（）

```
#1、用法：
	window.setInterval(方法,触发时间);
	执行：每经过‘触发时间’，就会去执行一次‘方法’（执行多次）。
	
#2、注意：
	（1）触发时间可以省略，默认为0
	（2）触发时间的单位是毫秒
	（3）一个页面上往往有很多的定时器，可以通过为定时器起一个名字来进行区分。
```

```
#定时器清除
#1、用法：
	window.clearInterval(定时器名字);
```

### 4、this的指向

```
#1、在全局作用域下this指向window对象
	（1）在全局直接console.log(this);  -- window
#2、谁调用this就指向谁
	（1）var a = function(){console.log(this);}
		a();						-- window
		在这里全局下a方法相当于window中的方法，也就是说a();相当于window.a();这样既然是window去调用，那么this就是window
```

### 5、异步

```
#1、结论
***在最新的Js中代码从上到下依次执行但不代表Js是同步执行，而实际上Js是异步执行的***

例子：
	console.log(1);
    window.setTimeout(function (){console.log(2);},2000);
    console.log(3);
解释：上面这段代码是从上到下依次执行的，只不过执行到第二句的时候会停顿2秒再打印。
	如果是在同步的情况下结果是123，在第二句等待的过程中，由于是同步的所以第三句不会执行。
	而现在的Js代码执行的时候是异步执行的，也就是说实际上的结果应该为132，执行第二句停顿的过程中继续往下执行
```

```
#2、异步任务
	回调函数属于异步任务。
	（1）window.setTimeout(fn1,1000);
	（2）dom.onclick = fn2;
	以上的fn1和fn2均属于回调函数，属于异步任务。
```

```
#3、Js的异步执行过程
	1 -- console.log(1);
    2 -- window.setTimeout(fun1,2000);
    3 -- console.log(3);
    4 -- doc.onclick = fun2
	（1）Js代码在执行的时候分为两个区域，同步执行区域和异步执行区域
	（2）上述代码执行中1，2，3,4均会进入同步执行区域，而fun1作为回调函数进入异步执行区域
	（3）执行中先将同步区域内的任务执行完毕，然后去检查异步执行区域有没有任务，有的话就拿到同步执行区域去执行
	（4）对于fun1这一类的回调函数同步任务执行到这会直接将fun1添加到异步执行区域；而对于fun2这样的回调函数，只有当鼠标点击之后才会将异步任务加入到异步执行区域
	（5）同步执行区域在将该区域内的所有任务都执行完毕之后，会时刻的去检查异步执行区域是否有待执行的异步任务
```

### 6、location对象

<font color = 'red'>location和document均属于window对象，也就是说也可以通过window.location来调用，不过window可以省略。</font>

#### （1）location对象的属性

以http://localhost:8080/a/b?a=1&b=2为例

| 属性              | 作用                                                         |
| ----------------- | ------------------------------------------------------------ |
| location.href     | (1)获取当前窗口的url地址<br>(2)设置当前窗口url地址<br>结果：http://localhost:8080/a/b?a=1&b=2 |
| location.host     | (1)获取主机（ip:端口）<br>结果：localhost:8080               |
| location.port     | (1) 获取端口<br>结果：8080                                   |
| location.pathname | (1)获取请求路径<br>结果：/a/b                                |
| location.search   | (1)获取请求参数<br>结果：?a=1&b=2                            |

#### （2）location对象的三个常用方法

| 方法                     | 作用                                                         |
| ------------------------ | ------------------------------------------------------------ |
| location.assign('url');  | 作用等同于location的href属性，用于页面跳转。<br>与replace的区别就是跳转之后可以返回原来界面 |
| location.replace('url'); | 作用等同于location的href属性，用于页面跳转。<br>与assign的区别就是跳转之后不可以返回原来界面 |
| location.reload();       | 重新加载页面                                                 |

## 十一、补充

### 1、立即执行函数

（1）第一种写法

```
#格式：(function(a,b...){})(a,b...)

#使用：
	(function(a,b){
		console.log(a+b);  //结果为3
	})(1，2);
```

（2）第二种写法

```
#格式：(function(a,b...){}(a,b...))

#使用：
	(function(a,b){
		console.log(a+b);  //结果为3
	}(1,2))
```

（3）总结

```
#含义：
立即执行函数：立即执行函数是一种不需要调用就可以自动执行的函数，执行顺序是从上到下，执行到它的时候自动执行

#注意：
a、当存在多个立即执行函数的时候，立即函数之间需要使用分号分开。
比方说：
(function(a,b...){})(a,b...)(function(a,b...){}(a,b...)) 错误
(function(a,b...){})(a,b...);(function(a,b...){}(a,b...)); 正确
b、立即执行函数是按照顺序自动执行的，不是打开页面最先执行的
```

### 2、本地数据存储

#### （1）sessionStorage

```
--特点--
（1）数据以键值对的形式存储在浏览器中
（2）存储数据的大小大约为5mb
（3）存储内容的生命周期是浏览器窗口关闭，只能在同一个窗口中使用
（4）sessionStorage对象属于window对象
```

```
--常用方法--
（1）增
window.sessionStorage.setItem(key,value);
（2）查
window.sessionStorage.getItem(key);
（3）删
window.sessionStorage.removeItem(key);
（4）删除所有
window.sessionStorage.clear();
```

#### （2）localStorage

```
--特点--
（1）数据以键值对的形式存储在浏览器中
（2）存储数据的大小约为20mb
（3）存储内容的生命周期是永久的，除非是手动删除
（4）localStorage对象属于window对象
```

```
--常用方法--
（1）增
window.localStorage.setItem(key,value);
（2）查
window.localStorage.getItem(key);
（3）删
window.localStorage.removeItem(key);
（4）删除所有
window.localStorage.clear();
```

### 3、Json转换

```
--Json对象--
1、{a:1,b:2}
2、{"a":1,"b":2}
3、{'a':1,'b':2}
以上三种形式均可

--Json字符串--
Json字符串本质上还是一个字符串，只不过具有自己专有的格式：JSON字符串中属性必须是双引号，属性和值之间是冒号，多个属性之间是逗号，外层是大括号，由于外双内单，外单内双的规则，那么作为一个字符串，最外面只能是单引号。

'{"a":1,"b":2}' : Json字符串只能有这一种格式
```

```
--转化函数--
1、对象转Json字符串
JSON.stringify(对象);

2、Json字符串转对象
第一种：JSON.parse(Json字符串);
第二种：eval('('+Json字符串+')'); 
注意：第二种方式并不推荐，因为eval方法会执行str字符串。比方说现在存在一个字符串"console.log('aaa');"
此时如果使用eval方法去解析这个字符串的话，就会看到在控制台中打印出来了aaa，说明eval在解析字符串的过程中会执行字符串，是不安全的。
```

### 4、事件补充

#### （1）change事件

```
*--应用场景--*
下拉菜单改变

*--案例--*
动态获取选中的下拉菜单的文本

Html:
<select id = "test">
	<option>1</option>
	<option>2</option>
	<option>3</option>
</select>
---------------------------------------------------------------
JavaScript:
$("#test").change(
	function(){
		$("#test").children().each(
			function(index,DOMObject){
				if(DOMObject.selected){
					console.log(DOMObject.innerText);
				}
			}
		);
	}
);

*--注意--*
select下拉菜单标签在使用静态事件注册的时候，onchange加在select上.
<button onclick="func()"></button>:这样的就是静态事件注册
```

#### （2）submit事件

```
*--作用--*
表单提交后的事件，多用于提交后表单项的验证以及返回数据的处理

*--使用--*
（阻止提交返回false，允许提交返回true，不写默认true）
1、静态事件注册
情形1：<form onsubmit="return false;"></form>	  √
情形2：<form onsubmit="return sub();"></form>
		function sub(){return false;}			√
情形3：<form onsubmit="sub()"></form>
		function sub(){return false;}			X
		
2、动态事件注册
<form id = "form"></form>
$("#form").submit(
	function(){return false;}
);												√
```

### 5、箭头函数

```
原方法：function(){}

箭头函数：() => {}
```

```
注意：箭头函数中没有属于自己的this，箭头函数中的this需要从箭头函数往外找
```

### 6、Object常用方法

```javascript
//（1）为对象添加属性
	Object.defineProperty(
        对象,   //要添加属性的对象
        属性名,   //要添加的属性名
        {
      		value:属性值,   //要添加的属性的值
      		enumerable:true,   //属性是否可以被枚举（默认不可以）
      		writable:true,   //属性是否可以被修改（默认不可以）
      		configurable:true,   //属性是否可以被删除（默认不可以）
            get(){
                
            },   //每当要访问age的属性的时候都会调用get方法，get方法的返回值就是最终我们能看到的age的值
            	//使用get和set时不能添加value和writable的配置
            set(){
                
            }   //当修改age属性值的时候都会调用set方法
            	//使用get和set时不能添加value和writable的配置
    });
```

```javascript
//（2）获取对象所有可枚举的属性的key
	Object.keys(对象);

// 演示
let obj = {
      name:"张三",
      sex:"男"
};
console.log(Object.keys(obj)); //此时获取的就是[name,sex],也就是obj对象的所有key的数组
```

```javascript
//（3）删除对象的属性
	delete 对象.属性;

// 演示
if (delete obj.sex){   //由此可见删除的返回值是一个布尔值
   console.log("success");
}
```

### 7、模板字符串

<u>**Js中使用反引号（``）包裹的内容就是模板字符串**</u>

> 模板字符串使用

****

1. 插入变量

   ```javascript
   let a = 1;
   let b = 2;
   let c = `${a}XXX${b}`;
   //结果：1XXX2
   console.log(c);
   ```

2. 表达式计算

   ```javascript
   let a = 1;
   let b = 2;
   let c = `${a+b}`;
   //结果：3
   console.log(c);
   ```

3. 多行字符串

   ```javascript
   let a = 
   `
   a
   b
   c
   `;
   /*
   结果:
   a
   b
   c
   */
   console.log(a);
   ```


### 8、原型对象

> 原型对象分类

****

1. 显式原型对象

   ```javascript
   function Demo(){
       this.a = 1;
       this.b = 2;
   }
   //显式原型对象
   console.log(Demo.prototype);
   ```

2. 隐式原型对象

   ```javascript
   function Demo(){
       this.a = 1;
       this.b = 2;
   }
   let d = new Demo();
   //隐式原型对象
   console.log(d.__proto__);
   ```

   

> 原型对象使用总结

*****

1. 同一个构造方法创建的多个对象的原型对象是同一个

   ```javascript
   function Demo(){
       this.a = 1;
       this.b = 2;
   }
   let a = new Demo();
   let b = new Demo();
   //结果为true，说明同一个构造方法的多个对象的原型对象是同一个
   console.log(a.__proto__ ===b.__proto__);
   ```

2. 隐式原型对象和显式原型对象是同一个

   ```javascript
   function Demo(){
       this.a = 1;
       this.b = 2;
   }
   let a = new Demo();
   //结果为true，说明隐式原型对象和显式原型对象是同一个
   console.log(a.__proto__ === Demo.prototype);
   ```

3. 作为程序员一般通过显示原型对象向原型对象中添加数据，通过隐式原型对象从原型获取数据

4. 如果想要从对象中获取某一个数据，如果找不到的话，就会从它的原型对象中查找

### 9、默认导出和命名导出

<u>***Js文件中可以使用export或者export default来使得其中的变量，方法，类等暴露出去，可以在其他的Js文件中引入其他Js文件中暴露出去的内容***</u>

#### （1）默认导出

<u>*默认导出的特点*</u>：

1. 一个Js文件中只能有一个默认导出
2. 被默认导出的内容在其他Js文件中引入的时候可以使用任何合法的名字引入

<u>*默认导出使用*</u>：

1. 导出变量

   ```javascript
   //myJs.js 文件
   let a = '100';
   export default a;
   ```

   ```vue
   <!-- Student.vue 文件 -->
   <script>
   	import aa from '../../js/myJs1';
       //结果：100
   	console.log(aa);
   </script>
   ```

2. 导出函数

   ```javascript
   //myJs.js 文件
   let a = function () {
       console.log("100");
   };
   export default a;
   ```

   ```vue
   <!-- Student.vue 文件 -->
   <script>
   	import aa from '../../js/myJs1';
       //调用aa函数，控制台打印100
   	aa();
   </script>
   ```

#### （2）命名导出

<u>*命名导出特点*</u>：

1. 一个Js文件中可以有多个命名导出
2. 引入命名导出的内容，名字不可随意指定

<u>*命名导出使用*</u>：

1. 导出变量

   ```javascript
   //myJs.js 文件
   export let a = '100';
   //或者
   let a = '100';
   export {a};
   ```

   ```vue
   <!-- Student.vue 文件 -->
   import {a} from '../../js/myJs1';
   console.log(a)
   ```

2. 导出函数

   ```javascript
   //myJs.js 文件
   export let a = function () {
       console.log(100);
   };
   //或者
   let a = function () {
       console.log(100);
   };
   export {a};
   ```
   
   ```vue
   <!-- Student.vue 文件 -->
   import {a} from '../../js/myJs1';
   a();
   
   <!-- 导出一个Js文件中的多个暴露的内容 -->
   import {a,b,c} from '../../js/myJs1';
   ```

### 10、解构赋值

#### （1）数组结构赋值

1. 基本赋值

   ```javascript
   const numbers = [1, 2, 3, 4, 5];
   const [a, b, c, d, e] = numbers;
   console.log(a); // 输出：1
   console.log(b); // 输出：2
   console.log(c); // 输出：3
   console.log(d); // 输出：4
   console.log(e); // 输出：5
   ```

2. 跳过某些数组元素赋值

   ```javascript
   /* 跳过数组中索引为1的元素 */
   const numbers = [1, 2, 3, 4, 5];
   const [a, , c] = numbers;
   console.log(a); // 输出：1
   console.log(c); // 输出：3
   ```

3. 赋值的对象超过数组长度会被赋值为undefined

   ```javascript
   const numbers = [1, 2, 3];
   const [a, b, c, d] = numbers;
   console.log(a); // 输出：1
   console.log(b); // 输出：2
   console.log(c); // 输出：3
   console.log(d); // 输出：undefined
   ```

#### （2）对象解构赋值

1. 基本赋值

   ```javascript
   /* 通过对象属性名实现赋值，并以原属性名创建一个新的变量 */
   const person = { name: 'John', age: 30 };
   const { name, age } = person;
   console.log(name); // 输出：'John'
   console.log(age); // 输出：30
   ```

2. 赋值后重新命名

   ```javascript
   /* 基本赋值中赋值完成会以原来的属性名作为新的变量 */
   const person = { name: 'John', age: 30 };
   // const { name, age } = person; 可以转换成 const { name:name, age:age } = person;
   const { name: personName, age: personAge } = person;
   console.log(personName); // 输出：'John'
   console.log(personAge); // 输出：30
   ```

3. 属性名不存在会被赋值undefined

   ```javascript
   const person = { name: 'John' };
   const { name, age } = person;
   console.log(name); // 输出：'John'
   console.log(age); // 输出：undefined
   ```

#### （3）连续解构赋值

```javascript
let obj = {a:{b:{c1:1,c2:2}}};
let {a:{b:{c2}}} = obj;
//结果：2
console.log(c2);
```



### 11、展开运算符

运算符：`...对象`

作用：将对象的所有成员变量展开并封入新的对象中

```javascript
let  a = {x:100,y:200};
//将对象a展开并将a的所有属性封装进b中
let  b = {a:'aaa',b:'bbb',...a};
//结果：{a: 'aaa', b: 'bbb', x: 100, y: 200}
console.log(b);
```



### 12、var、let、const区别

| 特性         | `var`                  | `let`                | `const`                |
| ------------ | ---------------------- | -------------------- | ---------------------- |
| 作用域       | 函数作用域             | 块级作用域           | 块级作用域             |
| 变量提升     | 有（值为 `undefined`） | 有，但存在暂时性死区 | 有，但存在暂时性死区   |
| 可重复声明   | 可以                   | 不可以               | 不可以                 |
| 重新赋值     | 可以                   | 可以                 | 不可以（对象属性可改） |
| 声明必须赋值 | 否                     | 否                   | 是                     |

   👉 一般开发中：

- 默认用 **`const`**（避免无意中修改变量）。
- 需要修改值时用 **`let`**。
- **不要再用 `var`**（历史遗留，容易出 bug）。

### 13、

   

   

   

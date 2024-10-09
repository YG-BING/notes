# Js常用方法

## 1.字符串常用方法

### （1）slice()

```
#作用：用作数组和字符串截取
#1.slice(startIndex，endIndex)；（startIndex包括，endIndex不包括）
#2.slice(stratIndex);（startIndex一直到字符串结尾）
```

### （2）substring()

```
#作用：用作字符串截取
#1.substring（startIndex，endIndex）；（startIndex包括，endIndex不包括）
#2.substring（startIndex）；（从startIndex到结尾）
```

### （3）trim()

```
#作用：去除字符串两端的空格
```

### （4）toLowerCase()，toUpperCase()

```
#作用：将字符串转换成大写或者小写
```

### （5）split()

```
#作用：将字符串以某个字符分割成一个字符数组
#使用：split("*");
```

### （6）replace()，replaceAll()

```
#作用：用于进行字符串的替换
#区别：replace只会替换第一次出现的字符，而replaceAll则会替换所有的字符
#使用：relace(要被替换的字符，替换成的字符)；
```

### （7）repeat()

```
#作用：重复拼接同一个字符串
#使用：repeat(重复次数)；
	"hellow".repeat(2); =>hellowhellow
```

### （8）charAt()

```
#作用：返回字符串指定位置的字符
#使用："hellow".charAt(3); => l
```

### （9）indexOf()

```
#作用：返回指定字符或者字符串在被查询字符串中*第一次出现*的位置，不存在返回值小于零
#使用："hellow".indexOf("llow"); => 2
```

### （10）repeat()

```
#作用：重复某个字符串指定次数
#使用："a".repeat(5); => "aaaaa"
#注意：不论重复多少次，都是生成了一个新的字符串，原来的字符串不变
```



<hr></hr>

## 2.Date常用方法

### （1）Date的四个构造方法

##### 		A、Date()

```
#作用：获取当前时间
#使用：new Date()；
```

##### 		B、Date(时间戳)

```
#作用：指定毫秒数转化成Date对象
#使用：new Date(123456789);
```

##### 		C、Date(时间字符串)

```
#作用：将指定的时间字符串转化成相应的date对象（底层会先将字符串通过parse转化成时间戳）
#使用：new Date(“2020-02-02”)；
```

##### 		D、Date(年，月，日，时，分，秒)

```
#使用：new Date(2020，1，1，1，1，1)；（其中至少要有年和月）
```

### （2）获取时间戳

##### 				A、parse()

```
Date.parse("2020-02-02");  
//parse方法可以将日期字符串，例如“2020-02-02”或者“2020/02/02”之类的日期字符串转化成于该日期对应的时间戳
```

##### 				B、Date.now()

```
Date.now();  //获取当前时间时间戳
```

##### 				C、getTime()

```
date.getTime();  //获取指定date对象的毫秒值
```

##### 				D、+date对象

```
+ new Date();
#注意：这个对象可以是任意的日期对象，比方说+new Date(123456789),+new Date("2020-02-02")...
```

### （3）年月日时分秒的获取

##### 		A、getFullYear()

```
#获取年份，从1970开始，四位年份
```

##### 		B、getMonth()

```
#获取月份，取值0-11，0代表1月，11代表12月
```

##### 		C、getDate()

```
#获取日，取值1-31
```

##### 		D、getHours()

```
#获取小时，取值0-23
```

##### 		E、getMinutes()

```
#获取分钟，取值0-59
```

##### 		F、getSeconds()

```
#获取秒，取值0-59
```

##### G、getDay()

```
#获取星期，取值0-6，0代表星期日，为一周的第一天
```

<hr></hr>

### 3、数组常用方法

##### A、join()	

```
#将数组中的元素以某一个间隔符，拼接成字符串
#示例：
var arr = [1,2,3,4,5];
arr.join('*');   //1*2*3*4*5
arr.join('');	//12345
```

##### B、indexOf()

```
#查询数组第一次出现某个元素的下标，不存在则返回-1

var arr = [1,2,3,4,5];
arr.indexOf(2);   //1
```

<hr></hr>

### 4、URL编码方法

#### （1）encodeURI

```
a、使用
	let 编码后的url = encodeURI(url字符串);

b、作用
	只会编码对应url来说的特殊字符
```

#### （2）encodeURIComponent

```
a、使用
	let 编码后的字符串 = encodeURIComponent(字符串);

b、作用
	编码字符串中的特殊字符
	不会编码的字符：字母，数字，- _ . ! ~ * ' ( )
	注意：url中常见的/会被encodeURIComponent编码，所以一般不会用于编码整个url
```


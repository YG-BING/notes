# 正则表达式-(JavaScript)

## 一、JavaScript正则常用方法

```
1、正则表达式.test(字符串);

*解释*：匹配字符串是否满足正则表达式，满足返回true，不满足返回false
*例子*：
	let zz = /abc/;
	let str1 = 'abc';
	let str2 = 'abbc';
	zz.test(str1);   //true
	zz.test(str2);   //false
```

```
2、字符串.match(正则表达式);

*解释*：返回一个在字符串中能正确匹配到正则表达式的字符串数组
*例子*：
	let zz = /ab{2}c/g;
	let str = 'abbc aaaaabbc abbbc';
	str.match(zz);   //['abbc','abbc']
```

```
3、字符串.search(正则表达式);
*解释*：返回在字符串中第一次能够成功匹配正则表达式的字符串的首字母的索引值
*例子*：
	let zz = /ab{2}c/g;
	let str = 'abc aaaaabbc abbbc';
	str.search(zz);   //8
```



## 二、修饰符

```
1、i

*解释*：不区分大小写

*例子*：
	let zz = /a/i;
	let str1 = 'a';
	let str2 = 'A';
	zz.test(str1);   //true
	zz.test(str2);   //true
```

```
2、g

*解释*：全局匹配

*例子*：
	let zz1 = /abc/g;
	let zz2 = /abc/;
	let str = 'abc abc';
	zz1.match(str);   //['abc','abc']
	zz2.match(str);   //['abc']
```



## 三、字符匹配

### 1、精确匹配

```
1、语法
/精确匹配值/
*解释*：只有完完整整的匹配到正则表达式才可以

2、使用
let zz = /aaa/;
let str1 = 'aaa';
let str2 = 'aaaa';
zz.test(str1);		//true
zz.test(str2);		//false
```

### 2、模糊匹配

```
1、{n}
let zz = /ab{2}c/;		//只有a开头,c结尾,并且字符串中含有两个b字符的字符串才满足（中间不仅仅可以只有b）
```

```
2、{m，n}
let zz = /ab{m，n}c/;	//只有开头是a，结尾是c,并且字符串中含有2至5个b才满足（中间不仅仅可以只有b）
```


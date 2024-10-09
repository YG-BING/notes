# Guava 笔记

## 1、依赖

```
<dependency>
	<groupId>com.google.guava</groupId>
	<artifactId>guava</artifactId>
	<version>23.0</version>
</dependency>
```

## 2、使用

```
//以下布隆过滤器仅适用于integer，如果是String可以修改Funnels.integerFunnel()

public static void main(String[] args) {
        //1、创建布隆过滤器
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),100,0.03);
        //2、判断某个数据在布隆过滤器中是否存在
        System.out.println(bloomFilter.mightContain(1));
        //3、往布隆过滤器中添加数据
        System.out.println(bloomFilter.put(1));
}
```

```
注意：
	（1）guava布隆过滤器的创建需要两个重要参数
		100：布隆过滤器预计存放的数据数量
		0.03：误判率：
			a、误判率越小，所需的hash函数越多，计算时间越久，响应时间越长
			b、误判率越小，所需的hash数组的位数越多，占用的存储空间越大
		
	（2）guava布隆过滤器的误判率默认为0.03，也就是说你创建布隆过滤器误判率可加可不加，不加默认为0.03
```

## 3、优势

```
guava的布隆过滤器不与redis耦合，可以脱离redis使用
```


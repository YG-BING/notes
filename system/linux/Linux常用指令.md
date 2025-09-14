# Linux  常用指令

## 1、--help

```shell
# 查询指令使用文档

# 查询ls的使用
ls --help
# 查询cd的使用
cd --help
```



## 2、history

```shell
# 展示最近的1000条指令
history

# 展示最近的n条指令
history n

# 清空所有指令历史
history -c
```



## 3、ls

```shell
# 查看当前目录下的文件和文件夹
ls

# 带详细信息的查看
ls -l

# 查看所有文件和文件夹（包含隐藏）
ls -a
```



## 4、cat

cat一般用于小文件查看

```shell
# 正向查看文件的所有内容
cat 文件名

# 合并多个文件内容
cat file1.txt file2.txt > merged.txt

# 追加文件内容
cat file1.txt >> file2.txt

# 配合grep进行正则过滤
cat file.txt | grep "ERROR"
```



## 5、tail

```shell
# （默认）查看文件的最后10行
tail -f a.txt

# 查看文件最后的5行
tail -n 5 a.txt

# 查看从文件第5行开始一直到文件最后
tail -n +5 a.txt

# 实时查看文件内容（常用于滚动日志的实时查看）
tail -f a.txt

# 配合grep进行内容过滤
tail -f a.txt | grep 2 # 将文件中含有2的内容查出
```



## 6、ps

```shell
# 查看当前会话中的所有进程
ps

# 查看所有进程
ps -e

# 查看所有进程的详细信息
ps -ef

# 使用grep进行正则过滤
ps -ef | grep "pro"
```



## 7、curl

curl请求主要用于发送请求，下载文件

```shell
# 发送get请求
curl "http://example.com/api?user=tom&id=1"

# 发送post表单
curl -X POST -d "user=tom&id=1" http://example.com/api

# 发送json数据
curl -X POST -H "Content-Type: application/json" \
     -d '{"name":"tom","age":18}' \
     http://example.com/api

# 下载文件并原名保存
curl -O http://example.com/file.zip
```



## 8、kill

```shell
kill 进程id;

# 强制杀死进程
kill -9 进程id
```









## 9、java

> 非后台运行

```shell
java -jar /path/to/your/jar/file.jar
```

- `/path/to/your/jar/file.jar`：路径名
- 这种启动方式当与`linux`会话关闭之后程序会终止，并且启动时启动日志会直接打印在命令行上

> 后台运行

```shell
nohup java -jar yourjarfile.jar > output.log 2>&1 &
```

- `yourjarfile.jar`：`jar`包路径
- `> output.log`：日志输出到当前目录的`output.log`的文件中
- `2>&1`：将标准错误输出重定向到标准输出
- `&`：指令将在后台执行，不会将启动日志打印在命令行上
- `nohup`：程序启动后即便关闭会话，程序也不会关闭



## 10、tail

```shell
# 动态查询日志out.log
tail -f out.log 
```

```shell
# 动态查询日志中指定内容excetion
tail -f out.log | grep exception
```

```shell
# 动态查询日志中指定内容之前10行
tail -f out.log | grep -B 10 exception
```

```shell
# 动态查询日志中指定内容之后10行
tail -f out.log | grep -A 10 exception
```

```shell
# 动态查询指定内容的上下10行
tail -f out.log | grep -C 10 exception
tail -f out.log | grep -A 10 -B 10 exception
```



## 11、pwd

显示当前所在目录

```shell
pwd
```



## 12、cd

```shell
# 切换到指定目录
cd /root

# 切换到上级目录
cd ..

# 切换到用户目录
cd ~
```



## 13、touch

```shell
# 新建文件
touch a.txt
```



## 14、cp

```shell
# 复制文件 
cp a.txt b.txt # 在当前目录下复制a.txt为b.txt

# 递归复制文件夹及文件夹中的文件
cp -r c d # 复制当前目录下的c目录为d目录，包括c目录下的所有目录和文件
```



## 14、mv

```shell
# 移动文件
mv a.txt ./a # 将当前目录下的a.txt移动到当前目录的a目录下

# 移动目录
mv a b # 将当前目录下的a目录移动到当前目录下的b目录里
```



## 15、rm

```shell
# 递归删除文件或者目录
rm -rf aaa
```



## 16、echo

```shell
# 打印信息
echo '123' #输出123

# 写入指定内容到指定文件
echo '123'>a.txt # 将'123'写入当前目录下的a.txt中

# 追加写入指定内容到指定文件
echo '123'>>a.txt # 将'123'写入当前目录下的a.txt中
```


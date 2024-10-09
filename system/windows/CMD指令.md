# CMD指令

### 1、查看计算机网络IP

```cmd
ipconfig
```

<hr></hr>

### 2、进入文件夹

```cmd
# 返回上一级
cd .. 	

# 返回根目录（盘符目录）
cd /

# 进入指的目录
cd d:/a/c		 
```

<hr></hr>

### 3、展示文件夹下的所有文件

```cmd
# 展示所有文件和文件夹
dir 

# 展示所有文件和文件夹(包括隐藏)
dir /a
```

<hr></hr>

### 4、打开文件或者文件夹

<font color=pink>这里的打开指的是，执行命令之后弹出新的窗口打开文件或者文件资源管理器。</font>

```cmd
# 打开文件
start 文件名

# 打开文件夹
start 文件夹
```

<hr></hr>

### 5、新建文件夹

<font color=pink>不仅可以创建一个文件夹，也可以创建多级路径</font>

```cmd
# 当前目录下创建a/b/c
mkdir a\b\c
```

<hr></hr>

### 6、查看文件

```cmd
type 1.txt 
```

<hr></hr>

### 7、创建非空文件

```cmd
# 格式
echo 文件内容>文件名

# 使用例子（创建a.txt文件并将123写入文件）
echo 123>a.txt
```

<hr></hr>

### 8、删除文件

<font color=pink>该指令只会删除文件，如果删除文件夹，那么会将文件夹下的所有文件删除</font>

```cmd
del 文件名
```

<hr></hr>

### 9、删除文件夹

```cmd
# 删除空文件夹
rd 文件夹名

# 删除多级文件夹
rd /s 文件夹一/文件夹二/文件夹三
```

<hr></hr>

### 10、清屏

```cmd
cls
```

<hr></hr>

### 11、ping

```cmd
ping www.baidu.com
```

<hr></hr>

### 12、查询占用某个端口的进程

```cmd
netstat -ano|findstr 端口号
```

<hr>

### 13、根据PID强制杀死某个进程

```cmd
taskkill /pid "PID号" /f 
```

<hr>





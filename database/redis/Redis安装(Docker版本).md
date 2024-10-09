# Redis安装 (Docker版本)

## 1、搜索镜像

```
docker search redis
```

## 2、拉取镜像

```
docker pull redis
```

## 3、配置文件

```
（1）/home/redis/config/redis.conf	//redis配置文件所在位置（具体配置文件见当前目录文件下的redis.conf）
（2）/home/redis/data				   //redis数据持久化的位置

注意：以上路径不是固定的，但是要确保一个data文件夹和一个配置文件
```

## 4、启动容器

```
*--启动指令--*
*--端口映射：主机端口:容器端口--*

docker run -p 6379:6379 --name redis -v /home/redis/config/redis.conf:/etc/redis/redis.conf  -v /home/redis/data:/data -d redis redis-server /etc/redis/redis.conf 

docker run -p 6380:6380 --name redis-s1 -v /home/redis-slave-1/config/redis1.conf:/etc/redis/redis.conf  -v /home/redis-slave-1/data:/data -d redis redis-server /etc/redis/redis.conf 
```

```
注意：
如果启动失败，进行如下操作

关闭selinux：
（1）临时关闭
[..]# getenforce
[..]# setenforce 0

（2）永久关闭
[..]# vi /etc/sysconfig/selinux
SELINUX=enforcing 改为 SELINUX=disabled
```

## 5、进入容器

```
*--进入redis客户端--*
docker exec -it 容器名/容器ID redis-cli

*--输入密码指令（redis容器中的指令，redis指令）--*
auth 密码
```


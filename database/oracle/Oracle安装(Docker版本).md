# Oracle安装(Docker版本)

### 1、拉取镜像

```
docker pull registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```



### 2、启动容器

```
docker run -itd -p 1521:1521 --name oracle --restart=always registry.aliyuncs.com/helowin/oracle_11g
```



### 3、环境变量配置

#### （1）进入容器

```
docker exec -it oracle(容器名) bash
```

#### （2）切换oracle用户

```
su root
-- 密码：helowin
```

#### （3）配置环境变量

```
vi /etc/profile
```

```
-- 在文件末尾加上

export ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/dbhome_2
export ORACLE_SID=helowin
export PATH=$ORACLEHOME/bin:PATH
```



### 4、创建软连接

#### （1）创建软连接

```
ln -s $ORACLE_HOME/bin/sqlplus /usr/bin
```

#### （2）切换oracle用户

```
su - oracle
```



### 5、修改SYS和SYSTEM的用户密码

```
--登录
sqlplus /nolog   
conn /as sysdba

--修改system用户账号密码
alter user system identified by system;

--修改sys用户账号密码
alter user sys identified by sys;
```



### 6、添加新的远程用户

```
-- 创建内部管理员账号密码
create user test(用户名) identified by test(用户密码); 

--将dba权限授权给内部管理员账号和密码
grant connect,resource,dba to test;

--设置密码永不过期
ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED; 

--修改数据库最大连接数据
alter system set processes=1000 scope=spfile; 
```



### 7、重启

```
--保存数据库
conn /as sysdba;

--关闭数据库
shutdown immediate; 

--启动数据库
startup; 
```


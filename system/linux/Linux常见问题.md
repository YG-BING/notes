# Linux常见问题



## 1、centos7无法安装curl

### （1）方法1(目前可用)

编辑 `/etc/yum.repos.d/CentOS-Base.repo`，把 `baseurl` 改成

```shell
http://mirrors.aliyun.com/centos-vault/7.9.2009/os/$basearch/
http://mirrors.aliyun.com/centos-vault/7.9.2009/updates/$basearch/
http://mirrors.aliyun.com/centos-vault/7.9.2009/extras/$basearch/
```

重新执行安装

```shell
sudo yum clean all
sudo yum makecache
sudo yum install -y curl
```


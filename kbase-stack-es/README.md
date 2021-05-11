# springboot2 es

## docker 安装常见报错

[Docker安装 elasticsearch 报错max virtual memory areas vm.max_map_count [65530] is too low](https://blog.csdn.net/xingfei_work/article/details/81463978)

[max virtual memory areas vm.max_map_count [65530] is too low](https://github.com/docker-library/elasticsearch/issues/111)

```
vim /etc/sysctl.conf

# 一个进程可以拥有的 VMA( 虚拟内存区域 的数量 默认值为 65536
vm.max_map_count=655360


vim /etc/security/limits.conf

# 每个进程可以打开文件数限制
elastic soft nofile 65536
elastic hard nofile 65536

vim /etc/security/limits.d/20-nproc.conf

# 每个进程可以打开文件数限制
elastic soft nofile 65536
elastic hard nofile 65536

# 操作系统级别对每个用户创建的进程数限制
elastic hard nproc 10240

# 重新加载
sysctl -p

```

[dockercompose部署ES遇到 Error opening log file ‘logs/gc.log’: Permission denied](https://blog.csdn.net/qq_18848239/article/details/108158741)

    elasticsearch-head 需要跨域访问
    
    application/x-www-form-urlencoded 不支持JSON格式的内容体，需要修改 _site/vendor.js 中的内容
    采用 `docker cp vendor.js container_id:/usr/src/app/_site` 的方法临时修改

[elasticsearch-head 6.x 问题application/x-www-form-urlencoded](https://blog.csdn.net/qq_30540299/article/details/84103282)

[java.lang.IncompatibleClassChangeError: Found class org.elasticsearch.common.bytes.BytesReference, but interface was expected...](https://www.cnblogs.com/dalianpai/p/13144584.html)

[Caused by: ElasticsearchException\[Elasticsearch exception \[type=illegal_argument_exception, reason=Fielddata is disabled on text fields by default...](https://www.jianshu.com/p/ab100d6d843f)

## 参考用例

[docker-compose搭建Elasticsearch集群和Kibana](https://blog.csdn.net/banmingi/article/details/102882197)

[docker-compose搭建elasticsearch集群](https://blog.csdn.net/epitomizelu/article/details/105592906)

[docker自定义elasticsearch镜像——集成IK分词器](https://blog.csdn.net/s1078229131/article/details/90036289)

## 配置项
```
# 集群名称，节点之间要保持一致
cluster.name: es-cluster

# 节点名称，集群内要唯一
node.name: node-9201
node.master: true
node.data: true

# ip 地址
network.host: localhost

# http 端口
http.port: 9201

# tcp 监听端口
transport.tcp.port: 9301

# 从节点配置
# discovery.seed_hosts: ["localhost:9301", "localhost:9302", "localhost:9303"]
# discovery.zen.fd.ping_timeout: 1m
# discovery.zen.fd.ping_retries: 5

# 集群内的可以被选为主节点的节点列表
# cluster.initial_master_nodes: ["node-9201", "node-9202", "node-9203"]

# 跨域配置
http.cors.enabled: true
http.cors.allow origin: "*"
```

## 读写流程
```
场景：
{
    number_of_shards: 3,
    number_of_replicas: 1
}

写流程：
1、客户端请求任意集群节点（协调节点）
2、协调节点将请求转换到指定节点
3、主分片保存数据
4、主分片将数据发送至副本（可通过配置修改，满足超过一半副本保存好数据就可以进行反馈）
5、副本保存后进行反馈
6、主分片进行反馈
7、客户端接收反馈

读流程：
1、客户端发送请求到协调节点
2、协调节点计算数据所在分片以及全部的副本位置
3、为了能够负载均衡，可以轮询所有节点
4、将请求转发给具体的节点
5、节点返回查询结果，将结果反馈给客户端

```

## script
```
POST /blog/_doc/1
{
  "title": "我的第一篇博客",
  "body": "我是博客的内容",
  "createUser": "占钊"
}

# 更新
PUT /blog/_doc/1
{
  "title": "我的第一篇博客",
  "body": "我是博客的内容111",
  "createUser": "占钊"
}

# 更新body字段
POST /blog/_update/1
{
  "script" : {
        "source": "ctx._source.body = '我是博客的内容222'"
        
    }
}

GET /blog/_doc/1

# 查询所有
GET /blog/_search
{
  "query":{
    "match_all" : {}
  }
}
GET /notice/_search
{
  "query":{
    "match_all" : {}
  }
}
GET /notice/_search
{
  "query":{
    "match": {
      "contentCN": "服"
    }
  }
}
GET /notice/_search
{
  "query":{
    "match": {
      "content": "服务"
    }
  },
  "explain": true
}

GET /notice/_analyze
{
   "text":"商品和服务",
   "analyzer":"ik_max_word"
}
GET /notice/_analyze
{
   "text":"商品和服务",
   "analyzer":"ik_smart"
}
GET /notice/_mapping
PUT /notice/_mapping
{
  "properties": {
    "content_cn" : {
      "type" : "text",
      "analyzer": "ik_max_word",
      "search_analyzer": "ik_smart"
    }
  }
}


# 删除
DELETE /blog/_doc/Ak-tvnEBIjNygBUDQ7pL
DELETE /notice
```
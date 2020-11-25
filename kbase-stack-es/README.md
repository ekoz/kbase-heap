# springboot2 es

## docker 安装常见报错

[Docker安装 elasticsearch 报错max virtual memory areas vm.max_map_count [65530] is too low](https://blog.csdn.net/xingfei_work/article/details/81463978)

[max virtual memory areas vm.max_map_count [65530] is too low](https://github.com/docker-library/elasticsearch/issues/111)

[dockercompose部署ES遇到 Error opening log file ‘logs/gc.log’: Permission denied](https://blog.csdn.net/qq_18848239/article/details/108158741)

elasticsearch-head 需要跨域访问

application/x-www-form-urlencoded 不支持JSON格式的内容体，需要修改 _site/vendor.js 中的内容
采用 docker cp 的方法临时修改

[elasticsearch-head 6.x 问题application/x-www-form-urlencoded](https://blog.csdn.net/qq_30540299/article/details/84103282)

## 参考用例

[docker-compose搭建Elasticsearch集群和Kibana](https://blog.csdn.net/banmingi/article/details/102882197)

[docker-compose搭建elasticsearch集群](https://blog.csdn.net/epitomizelu/article/details/105592906)

[docker自定义elasticsearch镜像——集成IK分词器](https://blog.csdn.net/s1078229131/article/details/90036289)

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
# springboot2 es

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
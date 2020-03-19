[参考](https://blog.csdn.net/bbwangj/article/details/79939269 "参考")

#### ORM框架

1. 模型类和表之间的对应关系
2. 根据设计的模型类生成数据库中的表格
3. 通过方便的配置切换数据库

#### 配置

```python
DATABASES = {
    'default':{
        'ENGINE':'django.db.backends.mysql',#表示使用的是mysql数据库的引擎
        'NAME': 'db1',      #数据库的名字，可以在mysql的提示符下先创建好
        'USER':'root',      #数据库用户名
        'PASSWORD':'',      #数据库密码
        'HOST':'',          #数据库主机，留空默认为"localhost"
        'PORT':'3306',      #数据库使用的端口
    }
}
```


基于web的自动管理工具，包括django.contrib（django代码的基本组成部分）

#### 创建超级用户

```cmd
D:\C\Django\BookManage>python3 manage.py createsuperuser
Username (leave blank to use 'hp'): admin
Email address: admin@bm.com
Password:
Password (again):
This password is too short. It must contain at least 8 characters.
This password is too common.
This password is entirely numeric.
Bypass password validation and create user anyway? [y/N]: y
Superuser created successfully.
```

#### 注册数据模型到admin

```python
/..Model/admin.py
from django.contrib import admin
from BookModel.models import Book

# Register your models here.
admin.site.register(Book)
```


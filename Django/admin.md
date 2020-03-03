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
#/..Model/admin.py
from django.contrib import admin
from BookModel.models import Book

# Register your models here.
admin.site.register(Book)
```

#### 添加复杂数据模型

```python
#/models.py
class Contact(models.Model):
    name = models.CharField(max_length=20)
    age = models.IntegerField(default=0)
    email = models.EmailField()
    def __unicode__(self):
        return self.name

class Tag(models.Model):
    contact = models.ForeignKey(Contact,on_delete=models.CASCADE,)
    name = models.CharField(max_length=50)
    def __unicode__(self):
        return self.name
```

#### 自定义表单

```python
#/..Model/admin.py
from django.contrib import admin
from BookModel.models import Book,Contact,Tag

# Register your models here.

class ContactAdmin(admin.ModelAdmin):
    fields = ("name","email")

admin.site.register(Contact,ContactAdmin)
admin.site.register([Book,Tag])

```

##### 输入栏分块

```python
#/..Model/admin.py
from django.contrib import admin
from BookModel.models import Book,Contact,Tag

# Register your models here.

class ContactAdmin(admin.ModelAdmin):
    # fields = ("name","email")
    fieldsets = (
        ['Main',{
            'fields':('name','email'),
        }],
        ['Advance',{
            'classes':('collapse',),
            'fields':('age',),
        }]
    )

admin.site.register(Contact,ContactAdmin)
admin.site.register([Book,Tag])
```

#### 内联 -  Inline

```python
#/..Model/admin.py
from django.contrib import admin
from BookModel.models import Book,Contact,Tag

# Register your models here.
class TagInline(admin.TabularInline):
    model = Tag
class ContactAdmin(admin.ModelAdmin):
    # fields = ("name","email")
    inlines = [TagInline]
    fieldsets = (
        ['Main',{
            'fields':('name','email'),
        }],
        ['Advance',{
            'classes':('collapse',),
            'fields':('age',),
        }]
    )

admin.site.register(Contact,ContactAdmin)
admin.site.register([Book])
```

#### 列表页的显示

```python
#/..Model/admin.py
...
class ContactAdmin(admin.ModelAdmin):
    list_display = ('name','age','email')
    ...
admin.site.register(Contact,ContactAdmin)
admin.site.register([Book])
```

##### 搜索

```python
#/..Model/admin.py
...
class ContactAdmin(admin.ModelAdmin):
    search_fields = ('name')
    ...
admin.site.register(Contact,ContactAdmin)
admin.site.register([Book])
```


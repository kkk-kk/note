```html
<!-- baidu CDN -->
<head>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
</head>
<!-- Google CDN -->
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
    </script>
</head>
```



## 语法

###### 基础语法：***$(selector).action()***

补充：CSS选择器 & XPath

#### 文档就绪事件(入口函数)

在html所有标签（DOM）都加载之后执行

```javascript
$(document).ready(function(){
    //...
})
//另一种写法
$(function(){
    //...
})
```

<img src="image-20200205113845330.png" alt="image-20200205113845330" style="zoom:100%;" />



## 选择器

##### 元素选择器 ：基于元素名选择元素

##### #id选择器：基于HTML元素的id属性选择元素

##### .class选择器：通过指定的class查找元素

![image-20200206100509211](image-20200206100509211.png)

#### 独立文件中使用jQuery函数

```html
<head>
    <script src="http://..."></script>
    <script src="xx.js"></script>
</head>
```



## 事件

页面对不同访问者的相应

|      鼠标事件       | 键盘事件 | 表单事件 | 文档/窗口事件 |
| :-----------------: | :------: | :------: | :-----------: |
|   <u>click</u>()    | keypress |  submit  |     load      |
|  <u>dblclick</u>()  | keydown  |  change  |    resize     |
| <u>mouseenter</u>() |  keyup   | focus()  |    scroll     |
| <u>mouseleave</u>() |          |  blur()  |    unload     |
|       hover()       |          |          |               |

mousedown()  mouseup()

## 效果

```
隐藏&显示：
hide(speed,callback)
show(speed,callback)
toggle(speed,callback)
淡入&淡出：
fadeIn(speed,callback)
fadeOut(speed,callback)
fadeToggle(speed,callback)
fadeTo(speed,callback)
滑动：
slideDown(speed,callback)
slideUp(speed,callback)
slideToggle(speed,callback)
动画：
animate({param},speed,callback)
stop(stopAll,gotoEnd)

```

## 回调 Callback

```javascript
$("button").click(function(){
    $("p").hide("slow",function(){
        alert("HIDE!");
    });
});
```

## 链 Chaining

在相同的元素上运行多条jQuery命令 [学校](..\学校) 

```javascript
$("#p1").css("color","red").slideUp(2000).slideDown(2000);
```

## HTML

#### 捕获 & 设置

```
.text(): 设置或返回文本内容
.html(): 设置或返回所选元素的内容
.val():  表单字段的值
.attr(): 获取属性值
.prop(): 获取属性值
```

#### 添加

```
.appeng()  追加文本
.prepend() 在开头追加文本
.after()  在后面添加文本
.before() 在前面添加文本
```

#### 删除

```
.remove() 过滤被删除的元素
.empty()  删除子元素
```

#### CSS

```
.addClass()
.removeClass()
.toggleClass()
.css()
```

#### 尺寸

```
.width()
.height()
.innerWidth()
.innerHeight()
.outerWidth()
.outerHeigth()
```


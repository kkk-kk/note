#### 捕获 & 设置

```
.text(function(i,origText){
	return ..;
}): 设置或返回文本内容
.html(): 设置或返回所选元素的内容
.val():  表单字段的值
.attr({
	"href":"...",
	"title":"..."
}): 获取属性值，attr("href");
.prop(): 获取属性值
```

<img src="image-20200209145014651.png" alt="image-20200209145014651" style="zoom:80%;" />

#### 添加

```
.appeng()  追加文本
.prepend() 在开头追加文本
function appendText()
{
    var txt1="<p>文本。</p>";           // 使用 HTML 标签创建文本
    var txt2=$("<p></p>").text("文本。");  // 使用 jQuery 创建文本
    var txt3=document.createElement("p");
    txt3.innerHTML="文本。";     // 使用 DOM 创建文本 text with DOM
    $("body").append(txt1,txt2,txt3);        // 追加新元素
}
```

```
.after()  在后面添加文本
.before() 在前面添加文本
```

#### CSS

```
$("button").click(function(){
  // 添加
  $("body div:first").addClass("important blue");
  $("h1,h2,p").addClass("blue");
});
.css({"propertyname":"value","propertyname":"value",...});
```


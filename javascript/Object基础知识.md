# Object  基础知识

## Plain Object（对象）

使用const生命的对象可以被修改，仅当我们尝试将 `user=...` 作为一个整体进行赋值时，`const` 会抛出错误。

计算属性，在对象字面量中使用方括号，`[fruit]` 含义是属性名应该从 `fruit` 变量中获取。 

方括号中可以使用表达式

**属性值缩写**：属性名跟变量名一样。

```
“key” in object // 通常用null来表示未知的或空值。几乎用不到in
```

**for ... in 循环**

```
for(let key in user)
```

**整数属性**会被进行排序，其他属性则按照创建的顺序显示。使用+1避免数字被排序。（“整数属性”指的是一个可以在不做任何更改的情况下与一个整数进行相互转换的字符串。）

#### 复制引用、克隆与合并

```javascript
Object.assign(dest, [src1, src2, src3...]) // 克隆

// 将 permissions1 和 permissions2 中的所有属性都拷贝到 user 中
Object.assign(user, permissions1, permissions2);
```

深拷贝：JavaScript 库 [lodash](https://lodash.com/) 中的 [_.cloneDeep(obj)](https://lodash.com/docs#cloneDeep)。

#### [垃圾回收机制](https://zh.javascript.info/garbage-collection)

垃圾回收的基本算法被称为 “mark-and-sweep”。

 [V8 之旅：垃圾回收](http://jayconrod.com/posts/55/a-tour-of-v8-garbage-collection)。

#### 对象方法、this

不同写法如下

+ ```javascript
  let user = {
    // ...
  };
  
  // 首先，声明函数
  function sayHi() {
    alert("Hello!");
  };
  
  // 然后将其作为一个方法添加
  user.sayHi = sayHi;
  
  ```

+ ```javascript
  // 这些对象作用一样，在对象继承方面有一些细微的差别
  user = {
    sayHi: function() {
      alert("Hello");
    }
  };
  
  // 方法简写看起来更好，对吧？
  let user = {
    sayHi() { // 与 "sayHi: function()" 一样
      alert("Hello");
    }
  };
  ```

JavaScript 中的 `this` 可以用于任何函数，即使它不是对象的方法。`this` 的值是在代码运行时计算出来的，它取决于代码上下文。在运行时对 `this` 求值的这个概念既有优点也有缺点。一方面，函数可以被重用于不同的对象。另一方面，更大的灵活性造成了更大的出错的可能。

[箭头函数](https://zh.javascript.info/arrow-functions)有些特别：它们没有自己的 `this`。如果我们在这样的函数中引用 `this`，`this` 值取决于外部“正常的”函数。

#### 构造方法

**new function() { … }**

如果我们有许多行用于创建单个复杂对象的代码，我们可以将它们封装在构造函数中，像这样：

```javascript
let user = new function() {
  this.name = "John";
  this.isAdmin = false;

  // ……用于用户创建的其他代码
  // 也许是复杂的逻辑和语句
  // 局部变量等
};
```

构造器不能被再次调用，因为它不保存在任何地方，只是被创建和调用。因此，这个技巧旨在封装构建单个对象的代码，而无需将来重用。

#### 可选链 短路效应

+ 我们还可以将 `?.` 跟 `delete` 一起使用

+ 可选链 `?.` 不能用在赋值语句的左侧。

+ 可选链 `?.` 语法有三种形式：

1. `obj?.prop` —— 如果 `obj` 存在则返回 `obj.prop`，否则返回 `undefined`。
2. `obj?.[prop]` —— 如果 `obj` 存在则返回 `obj[prop]`，否则返回 `undefined`。
3. `obj.method?.()` —— 如果 `obj.method` 存在则调用 `obj.method()`，否则返回 `undefined`。

### Symbol

symbol不会被自动转换为字符串
# javascript基础知识

#### let&var

`let`非常适合用于 `for`循环内部的块级作用域。JS中的for循环体比较特殊，每次执行都是一个全新的独立的块作用域，用let声明的变量传入到 for循环体的作用域后，不会发生改变，不受外界的影响。

JavaScript 的变量命名有两个限制：

1. 变量名称必须仅包含字母，数字，符号 `$` 和 `_`。
2. 首字符必须非数字。

常量用const声明  `const a = 1;`

大写形式的常数  `const COLOR_RED = "#F00";`

#### 数据类型

**Number类型**表示整数和浮点数、还包括所谓的“特殊数值（“special numeric values”）”也属于这种类型：`Infinity`、`-Infinity` 和 `NaN`。

`Infinity` 代表数学概念中的 无穷大∞。是一个比任何数字都大的特殊值。我们可以通过除以 0 来得到它。`NaN` 代表一个计算错误。它是一个不正确的或者一个未定义的数学操作所得到的结果。

“number” 类型无法表示大于 `(253-1)`（即 `9007199254740991`），或小于 `-(253-1)` 的整数。

#### BigInt类型

通过将 `n` 附加到整数字段的末尾来创建 `BigInt` 值。

#### String类型

双引号和单引号都是“简单”引用，在 JavaScript 中两者几乎没有什么差别。

反引号是 **功能扩展** 引号。它们允许我们通过将变量和表达式包装在 `${…}` 中，来将它们嵌入到字符串中。

#### Boolean类型、“null”值、“undefine”值

#### object类型和symbol类型

#### typeof操作

1. `typeof null` 的结果是 `"object"`。这是官方承认的 `typeof` 的行为上的错误，这个问题来自于 JavaScript 语言的早期，并为了兼容性而保留了下来。`null` 绝对不是一个 `object`。`null` 有自己的类型，它是一个特殊值。
2. `typeof alert` 的结果是 `"function"`，因为 `alert` 在 JavaScript 语言中是一个函数。我们会在下一章学习函数，那时我们会了解到，在 JavaScript 语言中没有一个特别的 “function” 类型。函数隶属于 `object` 类型。但是 `typeof` 会对函数区分对待，并返回 `"function"`。这也是来自于 JavaScript 语言早期的问题。从技术上讲，这种行为是不正确的，但在实际编程中却非常方便。

## 交互

`prompt` 将返回用户在 `input` 框内输入的文本，如果用户取消了输入，则返回 `null`。

```javascript
result = prompt(title, [default]);
```

**IE 浏览器会提供默认值**

## 类型转换

```javascript
alert( "6" / "2" ); // 3, string 类型的值被自动转换成 number 类型后进行计算
```

**字符串转换** —— 转换发生在输出内容的时候，也可以通过 `String(value)` 进行显式转换。原始类型值的 string 类型转换通常是很明显的。

**数字型转换** —— 转换发生在进行算术操作时，也可以通过 `Number(value)` 进行显式转换。

数字型转换遵循以下规则：

| 值             | 变成……                                                       |
| :------------- | :----------------------------------------------------------- |
| `undefined`    | `NaN`                                                        |
| `null`         | `0`                                                          |
| `true / false` | `1 / 0`                                                      |
| `string`       | “按原样读取”字符串，两端的空白会被忽略。空字符串变成 `0`。转换出错则输出 `NaN`。 |

**布尔型转换** —— 转换发生在进行逻辑操作时，也可以通过 `Boolean(value)` 进行显式转换。

布尔型转换遵循以下规则：

| 值                                    | 变成……  |
| :------------------------------------ | :------ |
| `0`, `null`, `undefined`, `NaN`, `""` | `false` |
| 其他值                                | `true`  |

上述的大多数规则都容易理解和记忆。人们通常会犯错误的值得注意的例子有以下几个：

- 对 `undefined` 进行数字型转换时，输出结果为 `NaN`，而非 `0`。
- 对 `"0"` 和只有空格的字符串（比如：`" "`）进行布尔型转换时，输出结果为 `true`。

## 运算符

求幂运算 `a ** b` 是 `a` 乘以自身 `b` 次。

```javascript
alert(2 + 2 + '1' ); // "41"，不是 "221"
```

一元运算符优先级高于二元运算符，赋值运算符会返回一个值

###### 逗号运算符

每个语句都运行了，但是只有最后的语句的结果会被返回，优先级低于赋值运算符，`let a = (1 + 2, 3 + 4);`

#### [值的比较](https://zh.javascript.info/comparison)

- 当对不同类型的值进行比较时，它们会先被转化为数字（不包括严格相等检查）再进行比较。
- 在非严格相等 `==` 下，`null` 和 `undefined` 相等且各自不等于任何其他的值。
- 在使用 `>` 或 `<` 进行比较时，需要注意变量可能为 `null/undefined` 的情况。比较好的方法是单独检查变量是否等于 `null/undefined`。

`let result = condition ? value1 : value2;`   =>    `let accessAllowed = age > 18;`  

一个或运算 `||` 的链，将返回第一个真值，如果不存在真值，就返回该链的最后一个值。与运算返回第一个假值，如果没有假值就返回最后一个值。

`alert( firstName || lastName || nickName || "Anonymous");`

两个非运算 `!!` 有时候用来将某个值转化为布尔类型，或使用内置的Boolean函数

值既不是 `null` 也不是 `undefined` 的表达式称为“已定义的（defined）”。

`a ?? b` 的结果是：

- 如果 `a` 是已定义的，则结果为 `a`，
- 如果 `a` 不是已定义的，则结果为 `b`。

`??` 在 `=` 和 `?` 之前计算，但在大多数其他运算符（例如，`+` 和 `*`）之后计算。如果没有明确添加括号，不能将其与 `||` 或 `&&` 一起使用。

## 循环

**标签** 是在循环之前带有冒号的标识符：

```javascript
labelName: for (...) {
  ...
}
```

`break <labelName>` 语句跳出循环至标签处：

```javascript
outer: for (let i = 0; i < 3; i++) {

  for (let j = 0; j < 3; j++) {

    let input = prompt(`Value at coords (${i},${j})`, '');

    // 如果是空字符串或被取消，则中断并跳出这两个循环。
    if (!input) break outer; // (*)

    // 用得到的值做些事……
  }
}
alert('Done!');
```

上述代码中，`break outer` 向上寻找名为 `outer` 的标签并跳出当前循环。

因此，控制权直接从 `(*)` 转至 `alert('Done!')`。

我们还可以将标签移至单独一行：

```javascript
outer:
for (let i = 0; i < 3; i++) { ... }
```

`continue` 指令也可以与标签一起使用。在这种情况下，执行跳转到标记循环的下一次迭代。

**标签并不允许“跳到”所有位置**

例如，这样做是不可能的：

```javascript
break label;  // 无法跳转到这个标签

label: for (...)
```

只有在循环内部才能调用 `break/continue`，并且标签必须位于指令上方的某个位置。

Switch比较时是严格相等，必须是相同类型

## 函数

局部变量只在函数内部可见、函数可以访问和修改外部变量

全局变量能够用于存储项目级别的数据。

```javascript
function showMessage(from, text = anotherFunction()) {
  // anotherFunction() 仅在没有给定 text 时执行
  // 其运行结果将成为 text 的值
}
```

每次 `showMessage()` 不带 `text` 参数被调用时，`anotherFunction()` 就会被调用。

```javascript
// 如果没有传入 "count" 参数，则显示 "unknown"
function showCount(count) {
  alert(count ?? "unknown");
}
```

**空值的** `return` **或没有** `return` **的函数返回值为** `undefined`

JavaScript 默认会在 `return` 之后加上分号。如果我们想要将返回的表达式写成跨多行的形式，那么应该在 `return` 的同一行开始写此表达式。或者至少放上左括号

命名

```java
showMessage(..)     // 显示信息
getAge(..)          // 返回 age（gets it somehow）
calcSum(..)         // 计算求和并返回结果
createForm(..)      // 创建表格（通常会返回它）
checkPermission(..) // 检查权限并返回 true/false
```

 函数是一种特殊的值

函数表达式

```javascript
let sayHi = function() {
  alert( "Hello" );
};
let func = sayHi;  //可以复制函数到其他变量
```

##### 回调函数 匿名函数

更细微的差别是，JavaScript 引擎会在 **什么时候** 创建函数。

**函数表达式是在代码执行到达时被创建，并且仅从那一刻起可用。**

一旦代码执行到赋值表达式 `let sum = function…` 的右侧，此时就会开始创建该函数，并且可以从现在开始使用（分配，调用等）。

函数声明则不同。

**在函数声明被定义之前，它就可以被调用。**

例如，一个全局函数声明对整个脚本来说都是可见的，无论它被写在这个脚本的哪个位置。

这是内部算法的原故。当 JavaScript **准备** 运行脚本时，首先会在脚本中寻找全局函数声明，并创建这些函数。我们可以将其视为“初始化阶段”。

在处理完所有函数声明后，代码才被执行。所以运行时能够使用这些函数。

**仅当函数声明不适合对应的任务时，才应使用函数表达式**

#### 箭头函数

```
let func = (arg1, arg2, ...argN) => expression
let double = n => n * 2;
let sayHi = () => alert("Hello!");
```

1. 不带花括号：`(...args) => expression` — 右侧是一个表达式：函数计算表达式并返回其结果。
2. 带花括号：`(...args) => { body }` — 花括号允许我们在函数中编写多个语句，但是我们需要显式地 `return` 来返回一些内容。
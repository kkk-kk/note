## REPL

使用下划线_获取上一个表达式的运算结果

#### 命令

- **ctrl + c** - 退出当前终端。
- **ctrl + c 按下两次** - 退出 Node REPL。
- **ctrl + d** - 退出 Node REPL.
- **向上/向下 键** - 查看输入的历史命令
- **tab 键** - 列出当前命令
- **.help** - 列出使用命令
- **.break** - 退出多行表达式
- **.clear** - 退出多行表达式
- **.save \*filename\*** - 保存当前的 Node REPL 会话到指定文件
- **.load \*filename\*** - 载入当前 Node REPL 会话的文件内容。

## 回调函数

阻塞&非阻塞

```javascript
var fs = require("fs");
// var data = fs.readFileSync('input.txt');
// console.log(data.toString());
fs.readFile('input.txt', function(err,data){
    if(err) return console.error(err);
    console.log(data.toString());
});
console.log("end");

```

## 事件循环

Node.js基本上所有的事件机制都是用设计模式中的观察者模式实现。

Node.js单线程类似进入一个while(true)的事件循环，直到没有事件观察者推出，每个异步事件都生成一个事件观察者，如果有事件发生就调用该回调函数。

#### 事件驱动程序

Node.js 使用事件驱动模型，当web server接收到请求，就把它关闭然后进行处理，然后去服务下一个web请求。

当这个请求完成，它被放回处理队列，当到达队列开头，这个结果被返回给用户。

这个模型非常高效可扩展性非常强，因为 webserver 一直接受请求而不等待任何读写操作。（这也称之为非阻塞式IO或者事件驱动IO）

在事件驱动模型中，会生成一个主循环来监听事件，当检测到事件时触发回调函数。

Node.js 有多个内置的事件，我们可以通过引入 events 模块，并通过实例化 EventEmitter 类来绑定和监听事件

```javascript
var events = require('events');                // 绑定事件
var eventEmitter = new events.EventEmitter();  // 创建eventEmitter对象
var connectHandler = function connected() {    // 创建事件处理程序
    console.log('connect successfully!');
    eventEmitter.emit('data_received');        // 触发 data_received 事件
}
eventEmitter.on('connection',connectHandler);  // 绑定connection事件的处理程序  
eventEmitter.on('data_received',function(){    // 绑定data_received事件
    console.log('received data ');
})  
eventEmitter.emit('connection');                // 触发事件

console.log('end');

/*  运行结果
PS D:\C\JS\node.js\modules> node events.js
connect successfully!
received data
end
*/
```

在 Node 应用程序中，执行异步操作的函数将回调函数作为最后一个参数， 回调函数接收错误对象作为第一个参数。

## EventEmitter

EventEmitter 的每个事件由一个事件名和若干个参数组成，事件名是一个字符串，通常表达一定的语义。对于每个事件，EventEmitter 支持 若干个事件监听器。

当事件触发时，注册到这个事件的事件监听器被依次调用，事件参数作为回调函数参数传递。

#### 方法

- addListener(event,listener)
- on(event,listener)
- once(eventmlistener)     单次监听器，最多只会触发一次，触发后立刻解除
- removeListener(event,listener)
- removeAllListeners([event])    （指定事件的）所有监听器
- setMaxListeners(n)        默认为10
- listeners(event)               返回事件的监听器数组
- emit(event,[arg1],[arg2].[...])        按监听器的顺序执行，如果事件有注册监听返回 true

#### 事件

- newListener   ( event  --  事件名称，listener  --  处理事件函数)    添加新监听器时被触发
- removeListener    ( ... )  从指定监听器数组中删除一个监听器。需要注意的是，此操作将会改变处于被删监听器之后的那些监听器的索引。 

```javascript
var events = require('events');
var eventEmitter = new events.EventEmitter();

var listener1 = function listener1(){
    console.log('listener1');
}

var listener2 = function listener2(){
    console.log('listener2');
}

eventEmitter.addListener('connection',listener1);
eventEmitter.on('connection',listener2);

var eventListeners = eventEmitter.listenerCount('connection');
console.log(eventListeners + ' listener.');

eventEmitter.emit('connection')

eventEmitter.removeListener('connection',listener1);
console.log('listener1 end');

eventEmitter.emit('connection');

eventListeners = eventEmitter.listenerCount('connection');
console.log(eventListeners + ' listener.');

console.log('end');

/*
PS D:\C\JS\node.js\modules> node listener.js
2 listener.
listener1
listener2
listener1 end
listener2
1 listener.
end
*/
```

#### error

遇到异常触发，EventEmitter 规定如果没有响 应的监听器，Node.js 会把它当作异常，退出程序并输出错误信息。我们一般要为会触发 error 事件的对象设置监听器。

#### 继承 EventEmitter

大多数时候我们不会直接使用 EventEmitter，而是在对象中继承它。包括 fs、net、 http 在内的，只要是支持事件响应的核心模块都是 EventEmitter 的子类。

为什么要这样做呢？原因有两点：

首先，具有某个实体功能的对象实现事件符合语义， 事件的监听和发生应该是一个对象的方法。

其次 JavaScript 的对象机制是基于原型的，支持 部分多重继承，继承 EventEmitter 不会打乱对象原有的继承关系。


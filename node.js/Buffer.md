JavaScript 语言自身只有字符串数据类型，没有二进制数据类型。

Buffer类用来创建一个专门存放二进制数据的缓冲区，处理I/O操作中移动的数据

使用Buffer.from()接口创建Buffer对象

```javascript
const buf = Buffer.from('kkk','ascii');
console.log(buf.toString('hex'));
```

Node.js目前支持的字符编码：

ASCII、UTF-8、base64

utf16le - 2 或 4 个字节，小字节序编码的 Unicode 字符。支持代理对（U+10000 至 U+10FFFF）。

ucs2   - utf16le 的别名。

latin1   - 一种把 Buffer 编码成一字节编码的字符串的方式。

binary  - **latin1** 的别名。

hex     - 将每个字节编码为两个十六进制字符

### Buffer.from

- **Buffer.from(array)：** 返回一个被 array 的值初始化的新的 Buffer 实例（传入的 array 的元素只能是数字，不然就会自动被 0 覆盖）
- **Buffer.from(arrayBuffer[, byteOffset[, length]])：** 返回一个新建的与给定的 ArrayBuffer 共享同一内存的 Buffer。
- **Buffer.from(buffer)：** 复制传入的 Buffer 实例的数据，并返回一个新的 Buffer 实例
- **Buffer.from(string[, encoding])：** 返回一个被 string 的值初始化的新的 Buffer 实例

### 写入缓冲区

```javascript
buf.write(String[,offset[,length]][,encoding])
```

- **string** - 写入缓冲区的字符串。
- **offset** - 缓冲区开始写入的索引值，默认为 0 。
- **length** - 写入的字节数，默认为 buffer.length
- **encoding** - 使用的编码。默认为 'utf8' 。

返回实际写入的大小，空间不足则写入一部分

### 从缓冲区读取数据


#### XSD - \<schema>元素

\<schema>元素是每一个XML Schema 的根元素

`xmlms:xs="http://..."` 

​			--  schema中用到的**元素和数据类型**来自命明空间 `“http://”` ，且这些元素和数据类型需要使用前缀`xs:`

`taegetNamespace="http://..."`

​			-- 此schema定义的**元素**（note，to，from，heading，body）来自命名空间`"http://.."`

`xmlns="http://..."`

​			-- 默认的命名空间是`"http://..."`

`elementFormDefault="qualified"`

​			-- 任何XML实例文档所使用的 且在此schema中声明过的**元素**必须==被命名空间限定==

```xml
<?xml version="1.0"?>
<xs:schema xmlns:xs="http://..."
           targetNamespace="http://..."
           xmlns="http://..."
           elementFormDefault="qulified">
    ...
</xs:schema>
```

##### 在XML文档中引用Schema

`xmlns="http://..."`

​			-- 默认命名空间，此声明会告知 schema 验证器，在此 XML 文档中使用的所有元素都被声明于 `http://...` 这个命名空间。

`xmlns:xsi="http://..."`可用的XML Schema 实例命名空间

`xsi:schemaLocation="http://... note.xsd"`

​			-- 使用 schemaLocation 属性。此属性有两个值。第一个值是需要使用的命名空间。第二个值是供命名空间使用的 XML schema 的位置：

```xml
<?xml version="1.0"?>
<note xmlns="http://..."
      xmlns:xsi="http://..."
      xsi:schemaLocation="http://... note.xsd">
	...
</note>
```

## 简易类型

常用数据类型：`xs:string xs:decimal xs:integer xs:boolean xs:date xs:time`

#### 简易元素

**定义：**`<xs:element name="_name" type="xs:string"/>`

**使用：**`<_name>...</_name>`

**默认值 & 固定值 ：**`<xs:element ... default / fixed ="_value"/>`

#### XSD属性

**定义：**`<xs:attribute name="_attr" type="xs:string"/>`

**使用：**`<_name _attr="...">...</_name>`

**默认值 & 固定值 ：**`<xs:attribute ... default / fixed ="_value"/>`

属性默认可选，规定为必选使用use：`<xs:attribute ... use="required"/>`

#### XSD限定 / Facets

用于为XML元素或者属性定义可接受的值

|      限定      | 描述                                                      |
| :------------: | :-------------------------------------------------------- |
|  enumeration   | 定义可接受值的一个列表                                    |
| fractionDigits | 定义所允许的最大的小数位数。必须大于等于0。               |
|     length     | 定义所允许的字符或者列表项目的精确数目。必须大于或等于0。 |
|  maxExclusive  | 定义数值的上限。所允许的值必须小于此值。                  |
|  maxInclusive  | 定义数值的上限。所允许的值必须小于或等于此值。            |
|   maxLength    | 定义所允许的字符或者列表项目的最大数目。必须大于或等于0。 |
|  minExclusive  | 定义数值的下限。所允许的值必需大于此值。                  |
|  minInclusive  | 定义数值的下限。所允许的值必需大于或等于此值。            |
|   minLength    | 定义所允许的字符或者列表项目的最小数目。必须大于或等于0。 |
|    pattern     | 定义可接受的字符的精确序列。                              |
|  totalDigits   | 定义所允许的阿拉伯数字的精确位数。必须大于0。             |
|   whiteSpace   | 定义空白字符（换行、回车、空格以及制表符）的处理方式。    |

```xml
<xs:element name="_name">
<xs:simpleType>
    <xs:restriction base="...">
        
    </xs:restriction>
</xs:simpleType>
</xs:element>
```

```xml
<xs:element name="_name" type="_type">
<xs:simpleType name="_type">
    <xs:restriction base="...">
        
    </xs:restriction>
</xs:simpleType>
</xs:element>
```

类型_type可被其他元素使用

```xml
<xs:element name="_name">
<xs:simpleType>
    <xs:restriction base="xs:string">
        <xs:pattern value="..."/>
    </xs:restriction>
</xs:simpleType>
</xs:element>
```

模式约束 pattern constraint

[常用限定](https://www.w3school.com.cn/schema/schema_facets.asp)

## 复合类型


#### Spring

##### 依赖注入（DI）

控制反转（IoC）的一个具体的例子

向构造函数传递参数、setter 方法 postconstruction

##### 面向方面的程序设计（AOP）

一个程序中跨越多个点的功能被称为**横切关注点**。

##### 体系结构

![Spring 体系结构](https://atts.w3cschool.cn/attachments/image/wk/wkspring/arch1.png)

##### 核心容器

spring-core : 框架的基本组成部分，IoC 和依赖注入

spring-beans : 提供BeanFactory

spring-context-support : 建立在 core 和 beans 模块的基础上，继承自 Bean 模块，ApplicationContext接口，提供了对第三方库继承到Spring上下文的支持

spring-expreesion : 表达式语言，在运行时查询和操作对象图

![Spring 体系结构](https://atts.w3cschool.cn/attachments/image/20181023/1540290875453691.png)


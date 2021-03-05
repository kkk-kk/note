## 安装NetBox

下载压缩包、解压缩，新建路径保存解压缩后的文件

[下载NetBox服务器](http://www.netbox.cn/)

安装netbox2.8，位置随意

在保存spasvo_crm的文件路径下，新建main.box，文件结构示例

```
D:\NetBox
├─spasvo_crm
└main.box
```

文件内容如下

```
Dim httpd     
Shell.Service.RunService "NBWeb", "NetBox Web Server", "NetBox Http Server Sample"      
Sub OnServiceStart()      
Set httpd = NetBox.CreateObject("NetBox.HttpServer")     
If httpd.Create("", 83) = 0 Then     
Set host = httpd.AddHost("", "\spasvo_crm")    
host.EnableScript = true      
host.AddDefault "Login.asp"         
httpd.Start    
else     
Shell.Quit 0      
end if      
End Sub      
Sub OnServiceStop()      
httpd.Close     
End Sub      
Sub OnServicePause()     
httpd.Stop      
End Sub     
Sub OnServiceResume()      
httpd.Start     
End Sub
```

第五行的83端口可以改，第六行的文件路径\spasvo_crm可以改

main.box 用NetBox打开，桌面右下角出现`.b`的图标，浏览器访问 http://localhost:83

出现登陆界面即为NetBox配置成功

## 导入sql文件

打开TestCenter，连接数据库，新建spasvo_crm数据库

在sql文件里加上 `use spasvo_crm;`

导入数据库文件spasvo_crm.sql

spasvo_crm 和 testcenter 放一起

## 安装ODBC

[官网下载msi](https://downloads.mysql.com/archives/c-odbc/)

最新版8.0.19 出现了`Unknown character set: 'utf8mb4'`

5.3版本连接数据库失败

5.2 64位的版本 `在指定的 DSN 中，驱动程序和应用程序之间的体系结构不匹配`

成功的版本：mysql-connector-odbc-5.2.5-win32.msi

下载完成后安装

打开控制面板->管理工具->ODBC Data Sources(32-bit)

用户DSN->添加->MySQL ODBC 5.2 ANSI Driver->完成

```
Data Source Name:conn
TCP/IP Server:localhost  Port:3306
user:root
Password:           // 数据库连接密码
DataBase:spasvo_crm // 点Test，出现连接成功后可以选择
```

修改..\spasvo_crm\Include\conn.asp文件

第三行更改为（改密码）

```
strconn = "driver={MySQL ODBC 5.2 ANSI Driver};server=127.0.0.1;database=spasvo_crm;uid=root;pwd=root;stmt=set names gbk"
```

浏览器打开 http://localhost:83

admin--admin 或 test--test 或 test1--test1 




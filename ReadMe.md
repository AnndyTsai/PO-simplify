# ExtentX结合ExtendReporter生成报告

** 注意：本项目有关ExtentX的源码请查考路径：PO_simplify.Listener.Report.ExtentXReport **

## ExtentX的简单介绍

* ExtendReporter只是面向前端的一个静态的HTML页面 每次运行会覆盖前一次的运行结果
* ExtendReporter结合ExtentX不仅可以提供静态展示的HTML页面还可以在服务端记录每次的运行情况
* ExtentX的使用需要在服务端安装node.js mongodb forever等

## 部署与安装ExtentX

* 安装部署node.js
* 下载安装mongodb (不要大于3.6版本)
* 配置mongodb (这些是基础 baidu就可以了)
* 该项目根目录下有一个extentx的文件夹 检出项目后 解压，然后进入extentx目录
* 进入解压目录中，使用命令 npm install, 安装extentx
* 生产部署 npm install -g forever
* 进入extentx 目录 forever start -l forever.log -o out.log -e err.log app.js
* 访问请使用：http://localhost:1337 (这个是localhost的默认配置 其他情况另当别论)

## 监听器的使用

> testng.xml 中增加listener

(```)
<listeners>	
	<listener class-name="cn.AnndyTsal.PO.PO_simplify.Listener.Report.ExtentXReport.ExtentTestNGITestListener"/>
</listeners>
(```)

## 效果展示

### ExtentX服务端的Report截图

![ext1](https://github.com/AnndyTsai/PO_simplify/ext/ext1 ''ext1-ExtentX服务端的Report截图1'')
![ext2](https://github.com/AnndyTsai/PO_simplify/ext/ext2 ''ext1-ExtentX服务端的Report截图2'')

### ExtendReporter静态HTML效果截图

![ext3](https://github.com/AnndyTsai/PO_simplify/ext/ext3 ''ExtendReporter静态HTML效果截图'')


## 温馨提示

* ExtentX再本项目中是服务于自动化测试的一个Report系统的一部分
* 请结合全文或者全部源码进行阅读
* GitHub链接如下：
[PO-simplify](https://github.com/AnndyTsai/PO-simplify "PO-simplify")
[CSND-Blog](https://blog.csdn.net/hujyhfwfh2/article/list/1 "细节介绍")






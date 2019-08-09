# PO-simplify介绍

*** PO-simplify项目是对传统的pageObject设计模式进行有大刀阔斧的改革,打破了传统PO设计模式的机械 呆板的设计风**
	
	* 1：优化传统PO设计模式的3层架构 PO-simplify采用传统PO设计模式的设计思想  减少Handle(业务层) 将业务层与Buss(操作层)合并,精简Page(页面层)代码，抽取共有方法 在Buss组装逻辑
	* 2：与传统PO设计模式相比PO-simplify直接的业务流程是pagBase->Buss->Case(传统PO模式pageBase->Page->Handle->Buss->Case)
	* 3:综上2点PO-simplify将会大幅度减少代码量，所有数据与业务处理集中在Buss层更加方便做数据驱动、更加方便修改数据来源(例如：Properties文件作为DB修改为MySQL作为数据来源 只用修改Buss层代码即可)
	
*** 本项目包含了再做自动化测试常用到的一些代码的重构(结合TestNG实现)**
	
	* 1：重写了TestNG的断言方法(Package路径：cn.AnndyTsal.PO.PO_simplify.Assert)
	* 2：实现了TestNG的监听器接口，例如：Assert、Report、Retry、shotScreen等(Package路径：cn.AnndyTsal.PO.PO_simplify.Listener)
	
** *请看下列介绍**

## ExtentX结合ExtendReporter生成报告

* 注意：本项目有关ExtentX的源码请查考路径：PO_simplify.Listener.Report.ExtentXReport
* 本项目是一个完整的PageObject设计模式的APP自动化测试基础框架

### ExtentX的简单介绍

* ExtendReporter只是面向前端的一个静态的HTML页面 每次运行会覆盖前一次的运行结果
* ExtendReporter结合ExtentX不仅可以提供静态展示的HTML页面还可以在服务端记录每次的运行情况
* ExtentX的使用需要在服务端安装node.js mongodb forever等

### 部署与安装ExtentX

* 安装部署node.js
* 下载安装mongodb (不要大于3.6版本)
* 配置mongodb (这些是基础 baidu就可以了)
* 该项目根目录下有一个extentx的文件夹 检出项目后 解压，然后进入extentx目录
* 进入解压目录中，使用命令 npm install, 安装extentx
* 生产部署 npm install -g forever
* 进入extentx 目录 forever start -l forever.log -o out.log -e err.log app.js
* 访问请使用：http://localhost:1337 (这个是localhost的默认配置 其他情况另当别论)

### 监听器的使用

> testng.xml 中增加listener

```
<listeners>	
	<listener class-name="cn.AnndyTsal.PO.PO_simplify.Listener.Report.ExtentXReport.ExtentTestNGITestListener"/>
</listeners>
```

### 效果展示

#### ExtentX服务端的Report截图

![ext1](https://github.com/AnndyTsai/PO-simplify/blob/master/ext/ext1.png "ext1-ExtentX服务端的Report截图1")
![ext2](https://github.com/AnndyTsai/PO-simplify/blob/master/ext/ext2.png "ext1-ExtentX服务端的Report截图2")

#### ExtendReporter静态HTML效果截图

![ext3](https://github.com/AnndyTsai/PO-simplify/blob/master/ext/ext3.png "ExtendReporter静态HTML效果截图")


### 温馨提示

* ExtentX再本项目中是服务于自动化测试的一个Report系统的一部分
* 请结合全文或者全部源码进行阅读
* GitHub链接和CSND博客链接如下：
[PO-simplify](https://github.com/AnndyTsai/PO-simplify "PO-simplify")
[CSND-Blog](https://blog.csdn.net/hujyhfwfh2/article/list/1 "细节介绍")

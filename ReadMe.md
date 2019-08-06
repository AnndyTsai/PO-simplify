# 代码的层级目录与介绍

## Utlis Package功能介绍

1：读取Properties文件根据对应的Key获取Value值
2：SSH远程执行命令（如果持续集成Jenkins服务器与Appium-Server分离 使用SSH执行一些启动Appium-Server的操作）方便持续集成

## Server Package功能介绍

1：启动Appium-Server

## Page Package功能介绍

1：页面成元素的class

## Buss Package功能介绍

1：执行逻辑顺序 完成一条用例的串联
2：进行断言

## Case Package功能介绍

1：执行测试用例

## Listener Package功能介绍

1：断言失败不中断执行的监听器实现
2：report的监听器实现方法（未写）

## Assert Package功能介绍

1：重构断言方法

# jerry-java-selenium-demo

## 环境搭建

IDEA+maven+testng+selenium

## demo 1： hello world!
基础例子： 使用selenium 代码实现页面操作
### 场景：
在百度首页点击新闻链接进入百度新闻页面。

## demo 2： 使用 testng 组织不同用例
### 场景：
分别从百度首页点击不同的链接进入不同的页面

## demo 3： 使用关键字驱动用例
### 场景：
以以下格式组织用例：
```
Chrome,前往|http://www.baidu.com,填写|id@@kw@@selenium,点击|id@@su,验证|Web Browser Automation,截图
```
步骤解析：
```
- Chrome： 调用 Chrome driver 进行测试。
- 前往|http://www.baidu.com ：  前往目标页。
- 填写|id@@kw@@selenium ：  在 id 为 kw 的元素中输入 selenium。
- 点击|id@@su ： 点击 id 为 su 的元素。
- 验证|Web Browser Automation ：  验证页面中是否出现 ‘Web Browser Automation’ 的文字。
- 截图： 对当前页面进行截图并保存。
```


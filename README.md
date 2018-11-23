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
从 case.txt 文件中读取用例列表：
```
Chrome,前往|http://www.baidu.com,填写|id@@kw@@selenium,验证文字|id@@su@@百度一下,点击|id@@su,验证|Web Browser Automation,截图
Chrome,前往|http://www.baidu.com,点击|name@@tj_trnews,验证标题|百度新闻
Chrome,前往|http://www.baidu.com,点击|name@@tj_trhao123,验证标题|hao123
Chrome,前往|http://www.baidu.com,点击|name@@tj_trmap,验证标题|百度地图
Chrome,前往|http://www.baidu.com,点击|name@@tj_trtieba,验证标题|百度贴吧
Chrome,前往|http://www.baidu.com,验证标题|百度一下,填写|id@@kw@@百度,点击|id@@su,验证文字|xpath@@//*[@id='1']/h3/a[1]@@百度
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

### 使用方法：
1. ide 中执行：
执行 jerry_selenium_demo3.JerryTestCase 文件，并指定参数为用例集文件的地址，如 D:\demo\jerry-java-selenium-demo\cases.txt。
2. jar 包执行：
- 下载 jar 包:jerry-java-demo-1.0-SNAPSHOT.jar
- 命令行执行：  java -jar /yourpath/jerry-java-demo-1.0-SNAPSHOT.jar /yourpath/case.txt

注意：
- 需要本地安装好jdk 和 chromedriver 驱动并配置好环境变量。
- 执行完成后，会在jar 包所在目录生成对应的 result.txt 结果文件。


## 打包命令： mvn package
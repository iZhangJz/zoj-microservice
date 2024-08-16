# ZOJ 微服务版本
 在 ZOJ 单体项目的基础上，将单体项目拆分为微服务项目，并使用 SpringCloud Alibaba 搭建微服务架构。

### 总览
- ***首页***，提供所有题目的浏览，支持进行标题或者标签的搜索功能 ![img_2.png](img_2.png)
- ***答题页面***，提供题目详情，支持提交代码，支持在线编译 ![img_1.png](img_1.png)
- ***提交窗口***，代码提交后将在左侧显示提交结果 
  1. Accepted：代码通过 ![img_4.png](img_4.png)
  2. Wrong Answer：代码未通过，显示错误信息 ![img_5.png](img_5.png) 
  3. Compile Error：代码编译错误，显示错误信息 ![img_7.png](img_7.png)

### SpringCloud Alibaba
- ```nacos``` 服务注册与发现
![img.png](img.png)
- 使用 ```SpringSession + Redis``` 实现分布式 session 登录


### 扩展点
- 使用 ```Jwt Token``` 实现登录 
- 在 ```gateway``` 中实现权限校验
- 在 ```gateway``` 实现 Sentinel 接口限流
- 是否适合使用 ```RabbitMq``` 做异步处理
- 完善用户个人中心
- 使用 ```OSS``` 进行对象存储
- 实现 ```C++``` 或 ```C``` 等其他语言的判题机制
- 实现查看提交记录
- 可实现统计功能（管理员使用）
### 待修复 bug
- 用户提交代码并通过后，通过数量没有增加 定位到 ```QuestionSubmitController.addQuestionSubmit```
- 错误信息显示错误，隐私信息未删除 ![img_6.png](img_6.png)
# ZOJ 微服务版本
 在 ZOJ 单体项目的基础上，将单体项目拆分为微服务项目，并使用 SpringCloud Alibaba 搭建微服务架构。
### 项目架构

<img src="https://github.com/user-attachments/assets/0b98930e-e937-4e4a-af88-9f52a4a73aec" width=400px>


### 总览
- ***首页***，提供所有题目的浏览，支持进行标题或者标签的搜索功能 <img src="https://github.com/user-attachments/assets/b8f0f15b-946c-4f04-a144-e6001ee6c836">

- ***答题页面***，提供题目详情，支持提交代码，支持在线编译 <img src="https://github.com/user-attachments/assets/fb65fdb0-9210-4d71-bf1f-bde43cce29e1">

- ***提交窗口***，代码提交后将在左侧显示提交结果 
  1. Accepted：代码通过 <img src="https://github.com/user-attachments/assets/35144726-5e59-407f-b6ce-9c4447ab6d8d">
 
  2. Wrong Answer：代码未通过，显示错误信息</br> <img src="https://github.com/user-attachments/assets/d8ec5578-086b-495f-9435-602f5a312b75" width=300px>
 
  3. Compile Error：代码编译错误，显示错误信息 </br> <img src="https://github.com/user-attachments/assets/ecaf9e87-6f7b-43a1-9714-47a38ca08a8c" width=300px>


### SpringCloud Alibaba
- ```nacos``` 服务注册与发现
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

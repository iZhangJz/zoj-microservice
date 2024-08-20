# ZOJ 微服务版本
 在 ZOJ 单体项目的基础上，将单体项目拆分为微服务项目，并使用 SpringCloud Alibaba 搭建微服务架构。</br>
 :star:[前端项目地址](https://github.com/iZhangJz/zoj-front)</br>
 :star:[代码沙箱服务地址](https://github.com/iZhangJz/code-sand-box)</br>
 :grey_exclamation:[后端单体项目地址(项目整体迁移至微服务版本)](https://github.com/iZhangJz/zoj-back)</br>
 
### 项目架构

<img src="https://github.com/user-attachments/assets/0b98930e-e937-4e4a-af88-9f52a4a73aec" width=400px>


### 总览
- ***首页***，提供所有题目的浏览，支持进行标题或者标签的搜索功能 <img src="https://github.com/user-attachments/assets/b8f0f15b-946c-4f04-a144-e6001ee6c836">

- ***答题页面***，提供题目详情，支持提交代码，支持在线编译 <img src="https://github.com/user-attachments/assets/fb65fdb0-9210-4d71-bf1f-bde43cce29e1">

- ***提交窗口***，代码提交后将在左侧显示提交结果 
  1. Accepted：代码通过 <img src="https://github.com/user-attachments/assets/35144726-5e59-407f-b6ce-9c4447ab6d8d">
 
  2. Wrong Answer：代码未通过，显示错误信息</br> <img src="https://github.com/user-attachments/assets/d8ec5578-086b-495f-9435-602f5a312b75" width=300px>
 
  3. Compile Error：代码编译错误，显示错误信息 </br> <img src="https://github.com/user-attachments/assets/ecaf9e87-6f7b-43a1-9714-47a38ca08a8c" width=300px>

### 功能点
- Jwt 用户登录，网关层统一鉴权
- 头像上传 :grey_exclamation:(暂时使用本地服务静态资源存储，可扩展为 ```oss``` 服务)
- star: 代码沙箱 Dokcer 判题 | Native 原生判题
- star: 支持多种语言
- star: ACM 模式答题，实现标准输入输出
- 每个测试用例独立内存、时间占用统计
- star: Markdown 富文本编辑器
- star: MonacoEditor 代码编辑器
### 支持语言
- star:```Java```
- star:```C++```

### SpringCloud Alibaba
- ```nacos``` 服务注册与发现
- 使用 ```SpringSession + Redis``` 实现分布式 session 登录
- ```openFeign``` 内部服务调用
- ```swagger + knife4j```  聚合接口文档
- ```sentinel dashboard``` 对提交服务进行流量控制


### 扩展点
- 完善用户个人中心
- 实现 ```C++``` 或 ```C``` 等其他语言的判题机制
- 实现查看提交记录
- 可实现统计功能（管理员使用）
- 用户管理

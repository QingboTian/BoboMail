# BoboMail
邮件客户端的简单实现
出于感兴趣，自己动手写了一个邮件的客户端
功能：
  1. 写邮件（不支持附件，支持正常文本内容的发送，包括暗送，抄送）
  2. 写邮件的内容允许html格式
  3. 必须拥有接收者，标题，其它可选
  4. 收邮件：功能不完善，只显示最近10条邮件（邮件数量过大导致反应特慢，也欢迎感兴趣的改进）
  5. 邮件的正文内容没有解析，是html格式的。
  6. 无账户登录模块，账户信息在配置文件当中。
启动方式：start.Start类中的main方法
运行截图：
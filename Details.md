## My Batis
### My Batis
XML 映射方式
1. 进来的parameter可以自己定义名字用@param,如果只有一个就不需要定义
2. variable 变量前要加 # 识别
3. 可定义结果类型比如 result map等
4. 可用 <where> <if>来检测穿入量为不为空再去做
5. 同上 <set> <if> 也可以
6. 可直接access product 里面的variables,比如定义了 parameter type 后 直接用里面的量
7. <foreach> 可以进行batch insert

### 一些编程设计
关于dto的返回
1. 会加上一些状态信息用enum来定义的 并设置不同的constructor供其使用。
2. 构建不同constructor去模拟成功或失败情况下的状态

关于 exceptions
1. 定义exceptions的方式
2. 其实也是run time exceptions就是不同的name再检查log file 的时候知道是什么错误

Service层设计
1.除了像dao层一样简单的提取后端，service层更多的是错误判断try catch
2. effectNum 做为插入后的判断指针

controller 层设计
1.负责转到 html 和 返还json格式的controller 一定要分开
2. 在controller层会去调当前session里的一些信息 做处理
3. 同理也可以在controller层存一些session 信息 做处理
4. 单独作为json返还格式需要加一些其他量比如成功与否， 或者直接使用 rest api
5. 在调用service层的时候可以把搜索信息实体类道一个temp object里再去操作。


### 小插件
1. codeutil从前端提取出kaptcha的session key想要的,用户实际输入的做对比
2. http servlet request util 就是从 string parameter转成 相关数据
3. image util 加水印，放到相关文件夹
4. path util的话就是找相关文件夹作为path





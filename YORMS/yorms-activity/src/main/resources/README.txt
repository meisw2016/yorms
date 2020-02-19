eclipse安装activiti插件
文档：eclipse安装activiti插件（离线安装）?..
链接：http://note.youdao.com/noteshare?id=7a3ac8da1236c2bbd1eaa837bc727053

yourms-activiey工程说明
1.eclipse配置activiti插件
配置方法见：
http://note.youdao.com/noteshare?id=7a3ac8da1236c2bbd1eaa837bc727053
2.本工程设计到es
启动服务时需连接到可用的es
es配置信息在bootstrap.yml文件中进行配置
spring:
  data:
    elasticsearch:
      cluster-name: docker-cluster
      cluster-nodes: 192.168.60.137:9300
      
3.本工程启动后可用访问swagger进行接口测试
访问地址：http://ip:port/swagger-ui.html

4.

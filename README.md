# sparkstream-parent
sparkstream+kafka实时流处理


1.建立scala项目时，不要使用idea提示的setup sdk,使用pom.xml文件中指定的scala库即可，否则可能会报错



项目运行步骤：
1.本地启动zookeeper
2.本地启动kafka
3.运行KafkaDataProducer
4.运行KafkaDataConsumer

运行结果：
在消费者的控制台会看到打印出来的数据

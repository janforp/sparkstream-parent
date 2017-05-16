package com.janita.producer.consts

/**
  * Created by Janita on 2017-05-16 09:30
  */
object ProducerConsts {

  val topic = "test"

  val broker = "127.0.0.1:9092"

  val zkConnect = "127.0.0.1:2181"

  val serializerClazz = "kafka.serializer.StringEncoder"

  val appName = "janita"

  val sparkMaster = "local[1]"

}

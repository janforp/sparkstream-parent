package com.janita.producer

import java.util.Properties

import com.alibaba.fastjson.JSONObject
import com.janita.producer.consts.ProducerConsts
import com.janita.producer.util.UserUtils
import kafka.producer.{KeyedMessage, Producer, ProducerConfig}



object KafkaDataProducer{

  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put("metadata.broker.list",ProducerConsts.broker)
    props.put("serializer.class", ProducerConsts.serializerClazz)
    props.put("zk.connect", ProducerConsts.zkConnect)

    val kafkaConfig = new ProducerConfig(props)
    val producer = new Producer[String, String](kafkaConfig)

    val arr = new Array[String](5)
    var size = 0

    while (true) {

      val userJson = new JSONObject()

      userJson.put("userId",UserUtils.getUserId)
      userJson.put("userName",UserUtils.getUserName)
      userJson.put("age", UserUtils.getAge)
      userJson.put("clazzId", UserUtils.getClazzId)

      arr(size) = userJson.toString

      size += 1

      if (size == 5) {
        val message = arr.mkString("[", ";", "]")
        producer.send(new KeyedMessage[String, String]("test", message))
        println(message)
        size = 0

        Thread.sleep(2000)
      }

    }

  }
}
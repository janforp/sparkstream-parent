package com.janita.consumer

import com.alibaba.fastjson.JSON
import com.janita.producer.consts.ProducerConsts
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * Created by Janita on 2017-05-16 14:46
  */
object KafkaDataConsumer{

  def main(args: Array[String]): Unit = {

    val topic = Set(ProducerConsts.topic)
    val kafkaParams = Map[String, String]("metadata.broker.list" -> ProducerConsts.broker, "serializer.class" -> ProducerConsts.serializerClazz)
    val sparkConf = new SparkConf(true).setAppName(ProducerConsts.appName).setMaster(ProducerConsts.sparkMaster)
    val streamingContext = new StreamingContext(sparkConf,Seconds(5))

    streamingContext.checkpoint("D:\\analysis-checkpoint")

    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamingContext,kafkaParams,topic)

    val events = kafkaStream.flatMap(line => {
      val endIndex = line._2.indexOf("]")
      line._2.substring(1,endIndex).split(";").flatMap(o => {
        val data = JSON.parseObject(o)
        Some(data)
      })
    })

    /**
      * 对每个json进行处理
      * x:每条记录
      * record:生成的对象
      */
    val record = events.map(x => (
      x.getLong("userId"),
      x.getString("userName"),
      x.getInteger("age"),
      x.getLong("clazzId"))
    )

    /**
      * 打印到控制台
      */
    record.foreachRDD( p => {
      p.foreachPartition(rdds => {
        rdds.foreach(println)
      })
    })


    streamingContext.start()
    streamingContext.awaitTermination()

  }
}

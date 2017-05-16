package com.janita.consumer.service

import org.apache.spark.streaming.dstream.DStream


/**
  * Created by Janita on 2017-05-16 15:06
  */
object UserService {
  /**
    * 把中间表分析成 班级知识点历程 表
    * @param record  原始数据的DStream,即 由睿智原始数据预处理之后生成的中间结果表 stu_test_knowledge_level_detail
    */
  def analysisUserRDD(record: DStream[(Long, String, Int, Long)]): Unit = {

  }
}

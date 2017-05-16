package com.janita.producer.util

import scala.util.Random

/**
  * Created by Janita on 2017-05-16 10:08
  */
object UserUtils {

  val random = new Random()


  def getUserId : Long = {

    val arr = Array(1,2,3,4,5)
    arr(random.nextInt(arr.length))
  }

  def getAge : Int = {

    random.nextInt(100)
  }

  def getUserName : String = {

    val arr = Array("张三", "李四", "王五", "赵六")
    arr(random.nextInt(arr.length))
  }

  def getClazzId : Long = {
    val arr = Array(1,2,3)
    arr(random.nextInt(arr.length))
  }
}

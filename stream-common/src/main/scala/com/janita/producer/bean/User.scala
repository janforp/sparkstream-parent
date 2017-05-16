package com.janita.producer.bean

import scala.beans.BeanProperty

/**
  * Created by Janita on 2017-05-16 09:37
  */
@SerialVersionUID(11L)
class User extends Serializable{

  @BeanProperty
  var userId = 0L
  @BeanProperty
  var userName = ""
  @BeanProperty
  var age = 0
  @BeanProperty
  var clazzId = 0L

  def this(userId: Long, userName: String, age: Int, clazzId: Long){
    this()
    this.userId = userId
    this.userName = userName
    this.age = age
    this.clazzId = clazzId
  }



  override def toString = s"User($userId, $userName, $age, $clazzId)"

  def canEqual(other: Any): Boolean = other.isInstanceOf[User]

  override def equals(other: Any): Boolean = other match {
    case that: User =>
      (that canEqual this) &&
        userId == that.userId
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(userId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

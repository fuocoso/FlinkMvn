package com.flink.table



import org.apache.hadoop.fs.Path
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.{FileOutputFormat, JobConf}

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat
import org.apache.flink.api.scala._
import org.apache.flink.api.scala.hadoop.mapred.HadoopOutputFormat

/**
  *  错误：could not find implicit value for evidence parameter of type org.apache.flink.api.common.typeinfo.TypeInformation[Int]
解决办法：  import org.apache.flink.api.scala._
  */

/**
  * Created by gjf36 on 2018-12-02.
  */
object OutPutTest {

  class UserDefTextOutputFormat[K, V] extends MultipleTextOutputFormat[K, V] {
    override def generateFileNameForKeyValue(key: K, value: V, name: String): String ={
      key.asInstanceOf[String]
    }

    override def generateActualValue(key: K, value: V): V = {
    NullWritable.get().asInstanceOf[V]
    }
  }

  def main(args: Array[String]): Unit = {
    val out = "data/saveText/"
    val env = ExecutionEnvironment.getExecutionEnvironment

 val  userTextOutputFrom =  new UserDefTextOutputFormat[String,String]()
    val conf = new JobConf()
    FileOutputFormat.setWorkOutputPath(conf,new Path(out))
    val format = new HadoopOutputFormat[String, String](userTextOutputFrom, conf)
    val ds = env.fromCollection(List(("java",12),("java",20),("hadoop",7),("hadoop",4),("hive",5)))
    //ds.output(format)
    env.execute()

  }
}

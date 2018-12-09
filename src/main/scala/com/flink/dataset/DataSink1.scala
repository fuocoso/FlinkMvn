package com.flink.dataset

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  * Created by gjf36 on 2018-12-06.
  */
object DataSink1 {
  def main(args: Array[String]): Unit = {
    //1.需要注意的是无论本地还是hdfs，如果parallelism >1，将会把path当成目录名，而如果为1，则是文件
    val  env  = ExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.api.scala._

    val stu = env.fromElements(
      (21,"Json",181.1),
      (17,"Lucy",166.5),
        (18,"Zora",176.4),
      (20,"Bob",180.2)
    )
    stu.sortPartition(0,Order.ASCENDING).print()
    stu.sortPartition(1,Order.DESCENDING).print()
    stu.sortPartition(0, Order.ASCENDING).sortPartition(2, Order.DESCENDING).print()
    stu.sortPartition("_",Order.ASCENDING).print()

    case class Worker(name:String,age:Int)

    val ds1  = env.fromElements(
      (Worker("Rose",23),180.2),
      (Worker("Allen",17),175.7),
      (Worker("Adam",26),187.1),
      (Worker("Smith",20),169.0)
    )
    println("==========复合元素==========")
    val ds2 = ds1.sortPartition("_1.age",Order.ASCENDING)

    val outPath1 = "data/output/datasink/sortOut1"
    val outPath2 = "data/output/datasink/sortOut2"

    ds2.writeAsText(outPath1,WriteMode.OVERWRITE)

    ds2.writeAsCsv(outPath2,rowDelimiter = "\n",fieldDelimiter = ",",WriteMode.OVERWRITE)

  //对于输出操作而言，要触发执行，就需要按以下操作
    env.execute()

  }

}

package com.flink.dataset

import org.apache.flink.api.scala.ExecutionEnvironment

import org.apache.flink.core.fs.FileSystem.WriteMode

/**
  * Created by gjf36 on 2018-12-06.
  */
object DataSink {
  def main(args: Array[String]): Unit = {
    //1.需要注意的是无论本地还是hdfs，如果parallelism >1，将会把path当成目录名，而如果为1，则是文件
    val  env  = ExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.api.scala._
    val ds = env.fromCollection(Map("hadoop" -> 7,"spark" -> 6 ))
    ds.setParallelism(1).writeAsText("data/output/datasink/test01",WriteMode.OVERWRITE)
  //  env.execute()

    //2.读取一个txt文件并以csv文件写出到hdfs或这本地
    val inpath = "data/emp.txt"
    case class Emp(eid:Int,ename:String,job:String,mgid:Int,hireDate:String,salary:Float,bonus:Float,did:Int)
    val ds1 = env.readTextFile(inpath)
   val ds2 = ds1.map(line =>{
      val arr = line.split("\t")
      val a = arr(0).toInt
      val b = arr(1)
      val c =arr(2)
      val d = if(arr(3).isEmpty) 0 else arr(3).toInt
      val e = arr(4)
      val f = if(arr(5).isEmpty) 0.0f else arr(5).toFloat
      val g = if(arr(6).isEmpty) 0.0f else arr(6).toFloat
      val h = arr(7).toInt
      Emp(a,b,c,d,e,f,g,h)
      //Tuple8(a,b,c,d,e,f,g,h)
    })
    ds2.print()
    val outpath = "data/output/datasink/test02"
    ds2.setParallelism(1).writeAsCsv(
      filePath = outpath,
      rowDelimiter = "\n",
      fieldDelimiter = ",",
      WriteMode.OVERWRITE
    )
    env.execute()

  }
}

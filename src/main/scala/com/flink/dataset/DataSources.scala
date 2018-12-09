package com.flink.dataset

import org.apache.flink.api.scala.ExecutionEnvironment

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by gjf36 on 2018-12-06.
  */
object DataSources {
  def main(args: Array[String]): Unit = {
    val  env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    //1.采用element创建Dataset(fromElements)
    val ds0 = env.fromElements("dataset","datastream")
    ds0.print()

    //2.采用Tuple创建Dataset(fromElements)
    val ds1 = env.fromElements(("java",10),("html",5))
    ds1.print()

    //3. 用Arraye创建Dataset(fromElements)
    val  ds2_1  = env.fromElements(Array("java","html","css"))
    val ds2_2 = env.fromCollection(Array("java","html","css"))
    ds2_1.print()
    ds2_2.print()

    //4.用List创建Dataset
    val ds3_1 = env.fromElements(List("mysql","oracle"))
    val ds3_2 = env.fromCollection(List("mysql","oracle"))
    ds3_1.print()
    ds3_2.print()

    //5.用ArrayBuffer创建Dataset
    val ds4_1 = env.fromElements(ArrayBuffer("hive","hbase","linux"))
    val ds4_2 = env.fromCollection(ArrayBuffer("hive","hbase","linux"))
    ds4_1.print()
    ds4_2.print()

    //6.用Vector创建Dataset
    val ds5_1 = env.fromElements(Vector("java","C++","dotnate"))
    val ds5_2 = env.fromCollection(Vector("java","C++","dotnate"))
    ds5_1.print()
    ds5_2.print()

    //7.用Queue创建Dataset
    val ds7_1: DataSet[mutable.Queue[String]] = env.fromElements(mutable.Queue("mongodb","postgresSQL"))
    val ds7_2: DataSet[String] = env.fromCollection(mutable.Queue("mongodb","postgresSQL"))
    ds7_1.print()
    ds7_2.print()

    //8.用Stack创建Dataset
    val ds8_1 = env.fromElements(mutable.Stack("flink","storm"))
    val ds8_2 = env.fromCollection(mutable.Stack("flink","storm"))
    ds8_1.print()
    ds8_2.print()

    //9.用Stream创建Dataset
    val ds9 = env.fromCollection(Stream("DevOp","input"))
    ds9.print()

    //10 用Set创建Dataset
    val ds10 = env.fromCollection(Set("class","object"))
    ds10.print()

    //11.用Seq创建DataSet
    val ds11: DataSet[String] = env.fromCollection(Seq("spark", "flink"))
    ds11.print()

    //12.用Iterable创建DataSet
    val ds12: DataSet[String] = env.fromCollection(Iterable("spark", "flink"))
    ds12.print()

    //13.用ArraySeq创建DataSet
    val ds13: DataSet[String] = env.fromCollection(mutable.ArraySeq("spark", "flink"))
    ds13.print()

    //14.用ArrayStack创建DataSet
    val ds14: DataSet[String] = env.fromCollection(mutable.ArrayStack("spark", "flink"))
    ds14.print()

    //15.用Map创建DataSet
    val ds15: DataSet[(Int, String)] = env.fromCollection(Map(1 -> "spark", 2 -> "flink"))
    println("=======用Map创建DataSet=========")
    ds15.print()

    //16.用Range创建DataSet
    val ds16: DataSet[Int] = env.fromCollection(Range(1, 9))
    println("=======用Range创建DataSet=========")
    ds16.print()

    //17.用generateSequence创建DataSet
    println("=======用generateSequence创建DataSet=========")
    val ds17: DataSet[Long] =  env.generateSequence(1,9)
    ds17.print()

  }
}

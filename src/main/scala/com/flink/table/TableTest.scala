package com.flink.table

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.api.scala._

/**
  * Created by gjf36 on 2018-12-02.
  */
object TableTest {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    // register the DataSet cust as table "Customers" with fields derived from the dataset

    val customers: DataSet[Customers] = env.fromElements(Customers("zhangsan", "18"), Customers("zhangsan", "19"))
    tableEnv.registerDataSet("Customers", customers)
    val result = tableEnv.sqlQuery("SELECT  *  FROM Customers ").select("age")
    val res = tableEnv.toDataSet[String](result)
    res.print()


  }

}
case class Customers(name: String, age: String)

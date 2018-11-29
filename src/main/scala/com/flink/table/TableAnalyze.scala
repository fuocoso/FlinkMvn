package com.flink.table


import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.table.api.{Table, TableEnvironment}

/**
  * Created by gjf36 on 2018-11-29.
  */
object TableAnalyze {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val  tableEnv = TableEnvironment.getTableEnvironment(env)
    import org.apache.flink.api.scala._
    val fields = Array("rank","player","club","apperances","goals","assits","shots","freeKicks","fouls","yellowCar","redCard")
    val  scorerDS: DataSet[Scorer] = env.readCsvFile[Scorer](
      "data/Scorers.csv",
      ignoreFirstLine = true
     // pojoFields = fields
    )
   // scorerDS.print()
   // val a: Table = tableEnv.fromDataSet(scorerDS)
    tableEnv.registerDataSet("A",scorerDS)
    //tableEnv.registerTable("b",a)

   val res: Table =  tableEnv.sqlQuery("select * from A")


    println(res)

  }
}

case class Scorer(rank:Int,player:String,club:String,apperances:Int,goals:Int,assits:Int,shots:Int,freeKicks:Int,fouls:Int,yellowCar:Int,redCard:Int)
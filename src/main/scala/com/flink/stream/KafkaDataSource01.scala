package com.flink.stream


import java.util.Properties


import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer09}
import org.apache.flink.api.scala._

/**
  * Created by gjf36 on 2018-12-09.
  */
object KafkaDataSource01 {
  def main(args: Array[String]): Unit = {
    //1 指定kfaka数据流相关信息
    val zkCluster  = "linux01:2181/kafka09"

    val broker = "linux01:9092"
    val topic  = "test"

    //2.创建流运行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //3.创建kafka数据流
    val prop = new Properties()
    prop.setProperty("bootstrap.servers",broker)
    prop.setProperty("zookeeper.connect",zkCluster)
    prop.setProperty("group.id",topic)


    val kafkaDS = new FlinkKafkaConsumer09[String](
      topic,
      new SimpleStringSchema(),
      prop
    )

    //4.填加数据源
    val lines = env.addSource(kafkaDS).setParallelism(1)
    lines.print()
    //触发计算
    env.execute()
  }
}

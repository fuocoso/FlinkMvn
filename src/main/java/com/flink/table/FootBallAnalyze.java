package com.flink.table;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;


import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.sources.BatchTableSource;

/**
 * Created by gjf36 on 2018-11-28.
 */
public class FootBallAnalyze {
    public static void main(String[] args) throws Exception {
        //获取Dataset的执行环境
        ExecutionEnvironment env  = ExecutionEnvironment.getExecutionEnvironment();
        //设置并行度为1
        env.setParallelism(1);
        //获取table api执行环境
        BatchTableEnvironment tableEnv = BatchTableEnvironment.getTableEnvironment(env);


        //source,读取测试的CSV文件，并因映射为对应的javabean
        DataSet<TopScorer> csvDataset = env.readCsvFile("data/Scorers.csv").ignoreFirstLine().pojoType(TopScorer.class, "rank", "player", "club", "appearances", "goals", "assists", "shots", "freeKicks", "fouls", "yellowCard", "readCard");
        //将dataset转成Table
        Table score =  tableEnv.fromDataSet(csvDataset);

        tableEnv.registerTable("score",score);
        Table res = tableEnv.sqlQuery("select club,sum(goals) as totalGoals from score group by club order by totalGoals desc");

        DataSet<QuryResult> resDataSet = tableEnv.toDataSet(res, QuryResult.class);

        resDataSet.map(new MapFunction<QuryResult, Tuple2<String,Integer>>() {
            public Tuple2<String, Integer> map(QuryResult quryResult) throws Exception {
                String club = quryResult.getClub();
                int total = quryResult.getTotalGoals();
                return new Tuple2<String, Integer>(club,total);
            }
        }).print();


    }
}

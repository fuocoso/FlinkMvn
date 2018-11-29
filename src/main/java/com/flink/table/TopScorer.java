package com.flink.table;

/**
 * Created by gjf36 on 2018-11-28.
 */
public class TopScorer {
    //排名
    private int rank;
    //球员
    private String player;
    //俱乐部
    private String club;
    //出场
    private int appearances;
    //进球
    private int goals;
    //助攻
    private int  assists;
    //射门
    private int shots;
    //任意球
    private  int freeKicks;
    //犯规
    private int fouls;
    //黄牌
    private int yellowCard;
    //红牌
    private int  readCard;


    public TopScorer() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getAppearances() {
        return appearances;
    }

    public void setAppearances(int appearances) {
        this.appearances = appearances;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getFreeKicks() {
        return freeKicks;
    }

    public void setFreeKicks(int freeKicks) {
        this.freeKicks = freeKicks;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public int getReadCard() {
        return readCard;
    }

    public void setReadCard(int readCard) {
        this.readCard = readCard;
    }
}

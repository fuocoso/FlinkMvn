package com.flink.table;

/**
 * Created by gjf36 on 2018-11-28.
 */
public class QuryResult {
    private  String club;
    private int totalGoals;

    public QuryResult() {
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }
}

package com.k24qlhs.service;

import com.k24qlhs.config.JDBCcon;
import com.k24qlhs.model.Score;
import com.k24qlhs.model.TypeScore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreService {
    public static ArrayList<Score> getList() {
        try {
            PreparedStatement statement =
                    JDBCcon.connection().prepareStatement("select s.scid,s.score,sj.sj_name,t.type_name\n" +
                            "from scores s,subjects sj,type_score t\n" +
                            "where s.sjid=sj.sjid\n" +
                            "and s.type_id = t.type_id");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Score> list = new ArrayList<>();
            while (resultSet.next()) {
                Score score = new Score();
                int id = resultSet.getInt("scid");
                score.setScid(id);
                String tName = resultSet.getString("type_name");
                String sjName = resultSet.getString("sj_name");
                score.setTypeName(tName);
                score.setSjName(sjName);
                int diem = resultSet.getInt("score");
                score.setScore(diem);
                list.add(score);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<TypeScore> getListTypeScore() {
        try {
            PreparedStatement statement =
                    JDBCcon.connection().prepareStatement("select  * from type_score");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<TypeScore> list = new ArrayList<>();
            while (resultSet.next()) {
                TypeScore typeScore = new TypeScore();
                int id = resultSet.getInt("type_id");
                typeScore.setTid(id);
                String tName = resultSet.getString("type_name");
                typeScore.setTName(tName);
                list.add(typeScore);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
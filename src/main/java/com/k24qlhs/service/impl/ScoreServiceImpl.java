package com.k24qlhs.service.impl;

import com.k24qlhs.config.JDBCConnect;
import com.k24qlhs.config.JDBCcon;
import com.k24qlhs.model.Score;
import com.k24qlhs.model.TypeScore;
import com.k24qlhs.service.ScoreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Service
public class ScoreServiceImpl implements ScoreServiceInterface {
    @Autowired
    JDBCConnect jdbcConnect;

    @Override
    public ArrayList<Score> getList(int studentId) {
        String query = "";
        query += "select st.sid,st.sname,s.scid,s.score,sj.sj_name," +
                "t.type_name\n" +
                "from scores s, \n" +
                "subjects sj, \n" +
                "type_score t , students st , score_details sd \n" +
                "where s.sjid=sj.sjid \n" +
                "and s.type_id = t.type_id and st.sid = sd.sid" +
                " and sd.scid = s.scid";
        if (studentId > 0){
            query += " and st.sid = " + studentId;
        }
        query += " order by s.scid";
        try {
            PreparedStatement statement =
                    jdbcConnect.connection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Score> list = new ArrayList<>();
            while (resultSet.next()) {
                Score score = new Score();
                int id = resultSet.getInt("scid");
                int sid = resultSet.getInt("sid");
                score.setScid(id);
                String tName = resultSet.getString("type_name");
                String sjName = resultSet.getString("sj_name");
                String sName = resultSet.getString("sname");
                score.setSName(sName);
                score.setSid(sid);
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

    @Override
    public ArrayList<TypeScore> getListTypeScore() {
        try {
            PreparedStatement statement =
                    jdbcConnect.connection().prepareStatement("select  * from type_score");
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

    @Override
    public int saveScore(Score score) {
        try {
            int id = (int) Math.floor(((Math.random() *899999) + 100000));
            PreparedStatement preparedStatement = jdbcConnect.connection().prepareStatement("insert into scores\n" + "value (?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,score.getSjId());
            preparedStatement.setInt(3,score.getTypeId());
            preparedStatement.setInt(4,score.getScore());
            preparedStatement.execute();
            return id;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveScoreDetail(int scid, int sid) {
        try {

            PreparedStatement preparedStatement = jdbcConnect.connection().prepareStatement(
                    "insert into score_details(sid, scid) \n" + "value (?,?)");
            preparedStatement.setInt(1,sid);
            preparedStatement.setInt(2,scid);
            preparedStatement.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Score findScoreById(int id) {
        try {
            PreparedStatement statement = jdbcConnect.connection().prepareStatement("" +
                    "select * from scores where scid = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Score score = new Score();
            while (resultSet.next()){
                int sjId = resultSet.getInt("sjid");
                int tId = resultSet.getInt("type_id");
                int sc = resultSet.getInt("score");
                int scid = resultSet.getInt("scid");
                score.setSjId(sjId);
                score.setTypeId(tId);
                score.setScore(sc);
                score.setScid(scid);
            }
            return score;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateScore(Score score) {
        try {
            PreparedStatement statement = jdbcConnect.connection().
                    prepareStatement("" + "update scores set sjid = ? ,type_id = ? ,score = ? where scid = ?");
            statement.setInt(1,score.getSjId());
            statement.setInt(2,score.getTypeId());
            statement.setInt(3,score.getScore());
            statement.setInt(4,score.getScid());
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

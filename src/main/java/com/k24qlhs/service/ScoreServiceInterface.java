package com.k24qlhs.service;

import com.k24qlhs.model.Score;
import com.k24qlhs.model.TypeScore;

import java.util.ArrayList;

public interface ScoreServiceInterface {
    ArrayList<Score> getList(int studentId);
    ArrayList<TypeScore> getListTypeScore();
    int saveScore(Score score);
    void saveScoreDetail(int scid,int sid);
    Score findScoreById(int id);
    void updateScore(Score score);
}

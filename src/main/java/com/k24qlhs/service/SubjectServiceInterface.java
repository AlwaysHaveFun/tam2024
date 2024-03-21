package com.k24qlhs.service;

import com.k24qlhs.model.Subject;

import java.util.ArrayList;

public interface SubjectServiceInterface {
    public void create (Subject subject);
    void update (Subject subject);
    ArrayList<Subject> getList();
    void delete();
}

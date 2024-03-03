package com.k24qlhs.service;

import com.k24qlhs.config.JDBCcon;
import com.k24qlhs.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentService {
    public static ArrayList<Student> getList(){
        try {
            PreparedStatement statement = JDBCcon.connection().
                    prepareStatement("select s.*,c.cname from students s,course c where c.cid = s.cid");
            ArrayList<Student> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("sid");
                String name = resultSet.getString("sname");
                int cid =  resultSet.getInt("cid");
                String cname = resultSet.getString("cname");

                Student student = new Student();
                student.setCid(cid);
                student.setSid(id);
                student.setSname(name);
                student.setCname(cname);
                list.add(student);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

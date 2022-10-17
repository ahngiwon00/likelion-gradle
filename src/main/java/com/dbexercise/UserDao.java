package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {
    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);//db연동

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO user(id,name,password) VALUES(?,?,?)");
        ps.setString(1, "1");
        ps.setString(2, "SoMin");
        ps.setString(3,"1123");
        //"INSERT INTO user(id,name,password) VALUES(1,SoMin,1123)" 형식으로 ps 입력이 됨.

        ps.executeUpdate(); //sql의 컨트롤 엔터 역할
        ps.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);//db연동

        PreparedStatement ps = conn.prepareStatement(
                "SELECT id, name, password FROM user WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),
                rs.getString("name"), rs.getString("password"));
        //SELECT된 id,name,password 받는 user 객체 생성


        rs.close();
        ps.close();
        conn.close();
        return user;

    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserDao userDao = new UserDao();
        //userDao.add();
        User user = userDao.get("1");
        System.out.println(user.getName());

    }
}

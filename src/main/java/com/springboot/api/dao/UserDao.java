package com.springboot.api.dao;

import com.springboot.api.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class UserDao {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from user");
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();
        Connection conn;
        try{
            conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from users where id = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            User user = null;
            if(rs.next()){
                user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
            }
            rs.close();
            pstmt.close();
            conn.close();

            if(user == null) throw new RuntimeException();

            return user;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}

package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:cheunyu
 * @date:2018/5/16 19:15
 */
@Repository //通过Spring注解定义一个DAO
public class UserDaoImpl implements IUserDao{

    private final static String MATCH_COUNT_SQL = "select count(*) from t_user where user_name = ? and password = ?";

    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit = ?, last_ip = ?, credits = ? where user_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired //自动注入JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        String sql = "select * from t_user where user_name = ?";
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId());
    }

}

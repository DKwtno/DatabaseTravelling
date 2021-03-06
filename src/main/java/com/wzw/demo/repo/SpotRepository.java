package com.wzw.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 游客查找景点<br>
 * 公司管理景点
 */
@Repository
public class SpotRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getSpotNameBySpotId(Integer id){
        List<String> strings = jdbcTemplate.query("select `name` from spot where spot_id = ?;"
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,id);
                    }
                }, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getString(1);
                    }
                });
        return strings.size()>0?strings.get(0):"ERROR";
    }
}

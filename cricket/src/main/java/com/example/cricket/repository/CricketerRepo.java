package com.example.cricket.repository;


import com.example.cricket.entity.Cricketer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CricketerRepo {


    String qry;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    CricketerMapper cricketerMapper;


    @Autowired
    List<Cricketer> cricketer;


    public List<Cricketer> getAllCricketerRepo()
    {

        qry="Select * from cricketer";
        cricketer=jdbcTemplate.query(qry, cricketerMapper);
        return cricketer;


    }

    public void addCricketerRepo(Cricketer cricketer)
    {
        qry="insert into cricketer(name,role) values(?,?)";
        if(jdbcTemplate.update(qry,cricketer.getName(),cricketer.getRole())>0) {
            System.out.println("Cricketer is added");
        }

    }


    public void updateCricketerRepo(String name,Cricketer cricketer)
    {
        qry="Update cricketer set role=? where name=?";
        if(jdbcTemplate.update(qry,cricketer.getRole(),name)>0)
        {
            System.out.println("Role changed to"+cricketer.getRole());
        }

    }

    public String deleteCricketerRepo(String name)
    {
        qry="Delete from cricketer where name=?";
        jdbcTemplate.update(qry,name);

        return "Cricketer deleted";
    }






}

@Component
class CricketerMapper implements RowMapper<Cricketer> {

    @Autowired
    Cricketer theCricketer;

    @Override
    public Cricketer mapRow(ResultSet rs, int rowNum) throws SQLException {

//	Map the fields of User to the columns of the table:userdetails
        theCricketer.setId(rs.getInt("id"));
        theCricketer.setName(rs.getString("name"));
        theCricketer.setRole(rs.getString("role"));


        return theCricketer;
    }

}


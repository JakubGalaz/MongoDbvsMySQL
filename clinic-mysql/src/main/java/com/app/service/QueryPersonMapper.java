package com.app.service;

import com.app.helper.PersonInfoHelper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class QueryPersonMapper implements RowMapper<PersonInfoHelper> {
    @Override
    public PersonInfoHelper mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonInfoHelper personInfoHelper = new PersonInfoHelper();

        personInfoHelper.setFirstName(rs.getString("doctor.first_name"));
        personInfoHelper.setLastName(rs.getString("doctor.last_name"));
        personInfoHelper.setValue(rs.getLong("visits"));

        return personInfoHelper;
    }

}
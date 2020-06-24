package com.app.service;

import com.app.helper.SumOfEveryMedicine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class QueryMedicineMapper implements RowMapper<SumOfEveryMedicine> {
    @Override
    public SumOfEveryMedicine mapRow(ResultSet rs, int rowNum) throws SQLException {
        SumOfEveryMedicine sumOfEveryMedicine = new SumOfEveryMedicine();

        sumOfEveryMedicine.setName(rs.getString("medicine.name"));
        sumOfEveryMedicine.setAmount(rs.getBigDecimal("sum_medicine"));

        return sumOfEveryMedicine;
    }

}
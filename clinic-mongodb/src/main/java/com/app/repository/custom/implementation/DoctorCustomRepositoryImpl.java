package com.app.repository.custom.implementation;

import com.app.helper.PersonInfoHelper;
import com.app.model.MedicalVisit;
import com.app.repository.custom.DoctorCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class DoctorCustomRepositoryImpl implements DoctorCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DoctorCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<PersonInfoHelper> countNumberOfVisitsByDoctors() {
        Aggregation aggregation = newAggregation(
                group("doctor.firstName", "doctor.lastName").count().as("value"),
                project("firstName", "lastName","value")
        );


        AggregationResults<PersonInfoHelper> groupResults = mongoTemplate.aggregate(
                aggregation, MedicalVisit.class, PersonInfoHelper.class);

        return groupResults.getMappedResults();
    }
}

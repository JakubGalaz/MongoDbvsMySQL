package com.app.repository.custom.implementation;

import com.app.helper.SumHelper;
import com.app.helper.SumOfEveryMedicine;
import com.app.model.MedicalVisit;
import com.app.repository.custom.MedicineCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class MedicineCustomRepositoryImpl implements MedicineCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MedicineCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public double getSumOfMedicines() {
        GroupOperation groupOperation = group()
                .sum("price").as("price");

        Aggregation aggregation = newAggregation(groupOperation);

        AggregationResults<SumHelper> result = mongoTemplate.aggregate(aggregation, "medicine", SumHelper.class);

        return result.getUniqueMappedResult().getPrice();
    }

    @Override
    public List<SumOfEveryMedicine> getNumberOfMedicinesByDoctor(String doctorId) {


        Aggregation aggregation = newAggregation(
                match(Criteria.where("doctor._id").is(doctorId)),
                unwind("prescriptionItems"),
                group("prescriptionItems.medicine.name").sum("prescriptionItems.amount").as("amount"),
                project("amount").and("name").previousOperation());


        AggregationResults<SumOfEveryMedicine> groupResults = mongoTemplate.aggregate(
                aggregation, MedicalVisit.class, SumOfEveryMedicine.class);

        return groupResults.getMappedResults();


    }

    @Override
    public List<SumOfEveryMedicine> geSumOfConcreteMedicines() {

        Aggregation aggregation = newAggregation(
                unwind("prescriptionItems"),
                project().and("prescriptionItems.medicine.name").as("name")
                        .and("prescriptionItems.amount").multiply("prescriptionItems.medicine.price").as("amountPhase1"),
                group("name").sum("amountPhase1").as("amount"),
                project("amount").and("name").previousOperation()
        );


        AggregationResults<SumOfEveryMedicine> groupResults = mongoTemplate.aggregate(
                aggregation, MedicalVisit.class, SumOfEveryMedicine.class);

        return groupResults.getMappedResults();
    }

    @Override
    public SumHelper calculatePrescriptionValue(String medicalVisitId) {

        Aggregation aggregation = newAggregation(
                match(Criteria.where("_id").is(medicalVisitId)),
                unwind("prescriptionItems"),
                project().and("prescriptionItems.medicine.name").as("name")
                        .and("prescriptionItems.amount").multiply("prescriptionItems.medicine.price").as("amountPhase1"),
                group().sum("amountPhase1").as("price"));


        AggregationResults<SumHelper> groupResults = mongoTemplate.aggregate(
                aggregation, MedicalVisit.class, SumHelper.class);

        return groupResults.getUniqueMappedResult();
    }
}

package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document
public class MedicalVisit {

    @Id
    private String id;
    private Doctor doctor;
    private Patient patient;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    private List <PrescriptionItem> prescriptionItems;
    private OutpatientClinic outpatientClinic;

    public MedicalVisit() {
    }

    public MedicalVisit(Doctor doctor, Patient patient, Date date, OutpatientClinic outpatientClinic) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.outpatientClinic = outpatientClinic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PrescriptionItem> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    public OutpatientClinic getOutpatientClinic() {
        return outpatientClinic;
    }

    public void setOutpatientClinic(OutpatientClinic outpatientClinic) {
        this.outpatientClinic = outpatientClinic;
    }

    @Override
    public String toString() {
        return "MedicalVisit{" +
                "id='" + id + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                ", prescriptionItems=" + prescriptionItems +
                ", outpatientClinic=" + outpatientClinic +
                '}';
    }
}

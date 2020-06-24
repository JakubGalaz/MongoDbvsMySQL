/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Wojtek
 */
@Entity
@Table(name = "medical_visit")
@XmlRootElement

public class MedicalVisit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    @JoinColumn(name = "id_doctor", referencedColumnName = "id")
    @ManyToOne
    private Doctor idDoctor;
    @JoinColumn(name = "id_patient", referencedColumnName = "id")
    @ManyToOne
    private Patient idPatient;
    @OneToMany(mappedBy = "idMedicalVisit")
    private List<PrescriptionItem> prescriptionItemList;
    @JoinColumn(name = "id_outpatient_clinic", referencedColumnName = "id")
    @ManyToOne
    private OutpatientClinic idOutpatientClinic;

    public MedicalVisit() {
    }

    public MedicalVisit(Doctor idDoctor, Patient idPatient, Date date, OutpatientClinic idOutpatientClinic) {
        this.idDoctor = idDoctor;
        this.idPatient = idPatient;
        this.date = date;
        this.idOutpatientClinic = idOutpatientClinic;
    }

    public MedicalVisit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public OutpatientClinic getIdOutpatientClinic() {
        return idOutpatientClinic;
    }

    public void setIdOutpatientClinic(OutpatientClinic idOutpatientClinic) {
        this.idOutpatientClinic = idOutpatientClinic;
    }

    @XmlTransient
    public List<PrescriptionItem> getPrescriptionItemList() {
        return prescriptionItemList;
    }

    public void setPrescriptionItemList(List<PrescriptionItem> prescriptionItemList) {
        this.prescriptionItemList = prescriptionItemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalVisit)) {
            return false;
        }
        MedicalVisit other = (MedicalVisit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MedicalVisit{" +
                "id=" + id +
                ", date=" + date +
                ", idDoctor=" + idDoctor +
                ", idPatient=" + idPatient +
                ", prescriptionItemList=" + prescriptionItemList +
                ", idOutpatientClinic=" + idOutpatientClinic +
                '}';
    }
}

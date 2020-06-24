package com.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "outpatient_clinic")
public class OutpatientClinic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @JoinColumn(name = "id_address", referencedColumnName = "id")
    @ManyToOne
    private Address idAddress;

    @OneToMany(mappedBy = "idOutpatientClinic")
    private List<MedicalVisit> medicalVisitList;

    public OutpatientClinic() {
    }

    public OutpatientClinic(String name, String type, Address idAddress) {
        this.name = name;
        this.type = type;
        this.idAddress = idAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }

    public List<MedicalVisit> getMedicalVisitList() {
        return medicalVisitList;
    }

    public void setMedicalVisitList(List<MedicalVisit> medicalVisitList) {
        this.medicalVisitList = medicalVisitList;
    }

    @Override
    public String toString() {
        return "OutpatientClinic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", idAddress=" + idAddress +
                '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Wojtek
 */
@Entity
@Table(name = "medicine")
@XmlRootElement

public class Medicine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "disease")
    private String disease;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @OneToMany(mappedBy = "idMedicine")
    private List<PrescriptionItem> prescriptionItemList;
    @JoinColumn(name = "id_pharmaceutical_company", referencedColumnName = "id")
    @ManyToOne
    private PharmaceuticalCompany idPharmaceuticalCompany;

    public Medicine() {
    }

    public Medicine(String name, String disease, BigDecimal price, PharmaceuticalCompany idPharmaceuticalCompany) {
        this.name = name;
        this.disease = disease;
        this.price = price;
        this.idPharmaceuticalCompany = idPharmaceuticalCompany;
    }

    public Medicine(Integer id) {
        this.id = id;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public List<PrescriptionItem> getPrescriptionItemList() {
        return prescriptionItemList;
    }

    public void setPrescriptionItemList(List<PrescriptionItem> prescriptionItemList) {
        this.prescriptionItemList = prescriptionItemList;
    }

    public PharmaceuticalCompany getIdPharmaceuticalCompany() {
        return idPharmaceuticalCompany;
    }

    public void setIdPharmaceuticalCompany(PharmaceuticalCompany idPharmaceuticalCompany) {
        this.idPharmaceuticalCompany = idPharmaceuticalCompany;
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
        if (!(object instanceof Medicine)) {
            return false;
        }
        Medicine other = (Medicine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", price=" + price +
                ", prescriptionItemList=" + prescriptionItemList +
                ", idPharmaceuticalCompany=" + idPharmaceuticalCompany +
                '}';
    }
}

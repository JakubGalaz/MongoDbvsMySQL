/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Wojtek
 */
@Entity
@Table(name = "prescription_item")
@XmlRootElement
public class PrescriptionItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private BigDecimal amount;
    @JoinColumn(name = "id_medicine", referencedColumnName = "id")
    @ManyToOne
    private Medicine idMedicine;
    @JoinColumn(name = "id_medical_visit", referencedColumnName = "id")
    @ManyToOne
    private MedicalVisit idMedicalVisit;

    public PrescriptionItem() {
    }

    public PrescriptionItem(Medicine idMedicine, BigDecimal amount) {
        this.idMedicine = idMedicine;
        this.amount = amount;
    }

    public PrescriptionItem(Medicine idMedicine, BigDecimal amount, MedicalVisit idMedicalVisit) {
        this.idMedicine = idMedicine;
        this.amount = amount;
        this.idMedicalVisit = idMedicalVisit;
    }

    public PrescriptionItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Medicine getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Medicine idMedicine) {
        this.idMedicine = idMedicine;
    }

    public MedicalVisit getIdMedicalVisit() {
        return idMedicalVisit;
    }

    public void setIdMedicalVisit(MedicalVisit idMedicalVisit) {
        this.idMedicalVisit = idMedicalVisit;
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
        if (!(object instanceof PrescriptionItem)) {
            return false;
        }
        PrescriptionItem other = (PrescriptionItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", idMedicine=" + idMedicine +
                ", idMedicalVisit=" + idMedicalVisit +
                '}';
    }
}

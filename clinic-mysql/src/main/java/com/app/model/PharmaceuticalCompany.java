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
import java.util.List;

/**
 *
 * @author Wojtek
 */
@Entity
@Table(name = "pharmaceutical_company")
@XmlRootElement

public class PharmaceuticalCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "company_name")
    private String companyName;
    @Size(max = 45)
    @Column(name = "nip")
    private String nip;
    @OneToMany(mappedBy = "idPharmaceuticalCompany")
    private List<Medicine> medicineList;
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    @ManyToOne
    private Address idAddress;

    public PharmaceuticalCompany() {
    }

    public PharmaceuticalCompany(String companyName, String nip, Address idAddress) {
        this.companyName = companyName;
        this.nip = nip;
        this.idAddress = idAddress;
    }
    public PharmaceuticalCompany(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @XmlTransient
    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
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
        if (!(object instanceof PharmaceuticalCompany)) {
            return false;
        }
        PharmaceuticalCompany other = (PharmaceuticalCompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.model.PharmaceuticalCompany[ id=" + id + " ]";
    }
    
}

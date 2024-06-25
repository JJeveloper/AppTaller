/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "propietario")
@NamedQueries({
    @NamedQuery(name = "Propietario.findAll", query = "SELECT p FROM Propietario p"),
    @NamedQuery(name = "Propietario.findByIdpropietario", query = "SELECT p FROM Propietario p WHERE p.idpropietario = :idpropietario"),
    @NamedQuery(name = "Propietario.findByCedula", query = "SELECT p FROM Propietario p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Propietario.findByNombres", query = "SELECT p FROM Propietario p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Propietario.findByTelefono", query = "SELECT p FROM Propietario p WHERE p.telefono = :telefono")})
public class Propietario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpropietario")
    private Integer idpropietario;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propietarioIdpropietario", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public Propietario() {
    }

    public Propietario(Integer idpropietario) {
        this.idpropietario = idpropietario;
    }

    public Propietario(Integer idpropietario, String cedula, String nombres, String telefono) {
        this.idpropietario = idpropietario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.telefono = telefono;
    }

    public Integer getIdpropietario() {
        return idpropietario;
    }

    public void setIdpropietario(Integer idpropietario) {
        this.idpropietario = idpropietario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpropietario != null ? idpropietario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propietario)) {
            return false;
        }
        Propietario other = (Propietario) object;
        if ((this.idpropietario == null && other.idpropietario != null) || (this.idpropietario != null && !this.idpropietario.equals(other.idpropietario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Propietario[ idpropietario=" + idpropietario + " ]";
    }
    
}

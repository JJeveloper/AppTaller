/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "repuestos")
@NamedQueries({
    @NamedQuery(name = "Repuestos.findAll", query = "SELECT r FROM Repuestos r"),
    @NamedQuery(name = "Repuestos.findByIdRepuestos", query = "SELECT r FROM Repuestos r WHERE r.idRepuestos = :idRepuestos"),
    @NamedQuery(name = "Repuestos.findByRepuesto", query = "SELECT r FROM Repuestos r WHERE r.repuesto = :repuesto"),
    @NamedQuery(name = "Repuestos.findByStock", query = "SELECT r FROM Repuestos r WHERE r.stock = :stock"),
    @NamedQuery(name = "Repuestos.findByPrecio", query = "SELECT r FROM Repuestos r WHERE r.precio = :precio")})
public class Repuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRepuestos")
    private Integer idRepuestos;
    @Basic(optional = false)
    @Column(name = "repuesto")
    private String repuesto;
    @Basic(optional = false)
    @Column(name = "stock")
    private int stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio")
    private BigDecimal precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repuestosidRepuestos", fetch = FetchType.LAZY)
    private List<EntregaRepuesto> entregaRepuestoList;
    @JoinColumn(name = "marca_idmarca", referencedColumnName = "idmarca")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Marca marcaIdmarca;

    public Repuestos() {
    }

    public Repuestos(Integer idRepuestos) {
        this.idRepuestos = idRepuestos;
    }

    public Repuestos(Integer idRepuestos, String repuesto, int stock, BigDecimal precio) {
        this.idRepuestos = idRepuestos;
        this.repuesto = repuesto;
        this.stock = stock;
        this.precio = precio;
    }

    public Integer getIdRepuestos() {
        return idRepuestos;
    }

    public void setIdRepuestos(Integer idRepuestos) {
        this.idRepuestos = idRepuestos;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<EntregaRepuesto> getEntregaRepuestoList() {
        return entregaRepuestoList;
    }

    public void setEntregaRepuestoList(List<EntregaRepuesto> entregaRepuestoList) {
        this.entregaRepuestoList = entregaRepuestoList;
    }

    public Marca getMarcaIdmarca() {
        return marcaIdmarca;
    }

    public void setMarcaIdmarca(Marca marcaIdmarca) {
        this.marcaIdmarca = marcaIdmarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepuestos != null ? idRepuestos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repuestos)) {
            return false;
        }
        Repuestos other = (Repuestos) object;
        if ((this.idRepuestos == null && other.idRepuestos != null) || (this.idRepuestos != null && !this.idRepuestos.equals(other.idRepuestos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Repuestos[ idRepuestos=" + idRepuestos + " ]";
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "entrega")
@NamedQueries({
    @NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e"),
    @NamedQuery(name = "Entrega.findByIdentrega", query = "SELECT e FROM Entrega e WHERE e.identrega = :identrega"),
    @NamedQuery(name = "Entrega.findByFechaentrega", query = "SELECT e FROM Entrega e WHERE e.fechaentrega = :fechaentrega"),
    @NamedQuery(name = "Entrega.findByTotal", query = "SELECT e FROM Entrega e WHERE e.total = :total")})
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identrega")
    private Integer identrega;
    @Basic(optional = false)
    @Column(name = "fechaentrega")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entregaIdentrega", fetch = FetchType.LAZY)
    private List<EntregaRepuesto> entregaRepuestoList;
    @JoinColumn(name = "producto_idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto productoIdproducto;
    @JoinColumn(name = "servicio_idservicio", referencedColumnName = "idservicio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Servicio servicioIdservicio;

    public Entrega() {
    }

    public Entrega(Integer identrega) {
        this.identrega = identrega;
    }

    public Entrega(Integer identrega, Date fechaentrega, BigDecimal total) {
        this.identrega = identrega;
        this.fechaentrega = fechaentrega;
        this.total = total;
    }

    public Integer getIdentrega() {
        return identrega;
    }

    public void setIdentrega(Integer identrega) {
        this.identrega = identrega;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<EntregaRepuesto> getEntregaRepuestoList() {
        return entregaRepuestoList;
    }

    public void setEntregaRepuestoList(List<EntregaRepuesto> entregaRepuestoList) {
        this.entregaRepuestoList = entregaRepuestoList;
    }

    public Producto getProductoIdproducto() {
        return productoIdproducto;
    }

    public void setProductoIdproducto(Producto productoIdproducto) {
        this.productoIdproducto = productoIdproducto;
    }

    public Servicio getServicioIdservicio() {
        return servicioIdservicio;
    }

    public void setServicioIdservicio(Servicio servicioIdservicio) {
        this.servicioIdservicio = servicioIdservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identrega != null ? identrega.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrega)) {
            return false;
        }
        Entrega other = (Entrega) object;
        if ((this.identrega == null && other.identrega != null) || (this.identrega != null && !this.identrega.equals(other.identrega))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Entrega[ identrega=" + identrega + " ]";
    }
    
}

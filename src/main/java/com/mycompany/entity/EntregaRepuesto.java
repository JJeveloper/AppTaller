/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "entrega_repuesto")
@NamedQueries({
    @NamedQuery(name = "EntregaRepuesto.findAll", query = "SELECT e FROM EntregaRepuesto e"),
    @NamedQuery(name = "EntregaRepuesto.findByIdentregaRepuesto", query = "SELECT e FROM EntregaRepuesto e WHERE e.identregaRepuesto = :identregaRepuesto"),
    @NamedQuery(name = "EntregaRepuesto.findByIdentrega", query = "SELECT e FROM EntregaRepuesto e WHERE e.entregaIdentrega = :entregaIdentrega")})
public class EntregaRepuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identrega_repuesto")
    private Integer identregaRepuesto;
    @JoinColumn(name = "entrega_identrega", referencedColumnName = "identrega")
    @ManyToOne(optional = false)
    private Entrega entregaIdentrega;
    @JoinColumn(name = "Repuestos_idRepuestos", referencedColumnName = "idRepuestos")
    @ManyToOne(optional = false)
    private Repuestos repuestosidRepuestos;

    public EntregaRepuesto() {
    }

    public EntregaRepuesto(Integer identregaRepuesto) {
        this.identregaRepuesto = identregaRepuesto;
    }

    public Integer getIdentregaRepuesto() {
        return identregaRepuesto;
    }

    public void setIdentregaRepuesto(Integer identregaRepuesto) {
        this.identregaRepuesto = identregaRepuesto;
    }

    public Entrega getEntregaIdentrega() {
        return entregaIdentrega;
    }

    public void setEntregaIdentrega(Entrega entregaIdentrega) {
        this.entregaIdentrega = entregaIdentrega;
    }

    public Repuestos getRepuestosidRepuestos() {
        return repuestosidRepuestos;
    }

    public void setRepuestosidRepuestos(Repuestos repuestosidRepuestos) {
        this.repuestosidRepuestos = repuestosidRepuestos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identregaRepuesto != null ? identregaRepuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntregaRepuesto)) {
            return false;
        }
        EntregaRepuesto other = (EntregaRepuesto) object;
        if ((this.identregaRepuesto == null && other.identregaRepuesto != null) || (this.identregaRepuesto != null && !this.identregaRepuesto.equals(other.identregaRepuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.EntregaRepuesto[ identregaRepuesto=" + identregaRepuesto + " ]";
    }

}

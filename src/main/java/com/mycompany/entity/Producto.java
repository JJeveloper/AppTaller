/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
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
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByFecharecibo", query = "SELECT p FROM Producto p WHERE p.fecharecibo = :fecharecibo"),
    @NamedQuery(name = "Producto.findByEstado", query = "SELECT p FROM Producto p WHERE p.estado = :estado"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproducto")
    private Integer idproducto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecharecibo")
    @Temporal(TemporalType.DATE)
    private Date fecharecibo;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoIdproducto", fetch = FetchType.LAZY)
    private List<Entrega> entregaList;
    @JoinColumn(name = "marca_idmarca", referencedColumnName = "idmarca")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Marca marcaIdmarca;
    @JoinColumn(name = "propietario_idpropietario", referencedColumnName = "idpropietario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Propietario propietarioIdpropietario;

    public Producto() {
    }

    public Producto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Producto(Integer idproducto, String nombre, Date fecharecibo, String estado, String descripcion) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.fecharecibo = fecharecibo;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecharecibo() {
        return fecharecibo;
    }

    public void setFecharecibo(Date fecharecibo) {
        this.fecharecibo = fecharecibo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Entrega> getEntregaList() {
        return entregaList;
    }

    public void setEntregaList(List<Entrega> entregaList) {
        this.entregaList = entregaList;
    }

    public Marca getMarcaIdmarca() {
        return marcaIdmarca;
    }

    public void setMarcaIdmarca(Marca marcaIdmarca) {
        this.marcaIdmarca = marcaIdmarca;
    }

    public Propietario getPropietarioIdpropietario() {
        return propietarioIdpropietario;
    }

    public void setPropietarioIdpropietario(Propietario propietarioIdpropietario) {
        this.propietarioIdpropietario = propietarioIdpropietario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproducto != null ? idproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idproducto == null && other.idproducto != null) || (this.idproducto != null && !this.idproducto.equals(other.idproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.Producto[ idproducto=" + idproducto + " ]";
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entity.Marca;
import com.mycompany.entity.Propietario;
import com.mycompany.entity.Entrega;
import com.mycompany.entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_AppTaller_jar_1.0-SNAPSHOTPU");

    public ProductoJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getEntregaList() == null) {
            producto.setEntregaList(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca marcaIdmarca = producto.getMarcaIdmarca();
            if (marcaIdmarca != null) {
                marcaIdmarca = em.getReference(marcaIdmarca.getClass(), marcaIdmarca.getIdmarca());
                producto.setMarcaIdmarca(marcaIdmarca);
            }
            Propietario propietarioIdpropietario = producto.getPropietarioIdpropietario();
            if (propietarioIdpropietario != null) {
                propietarioIdpropietario = em.getReference(propietarioIdpropietario.getClass(), propietarioIdpropietario.getIdpropietario());
                producto.setPropietarioIdpropietario(propietarioIdpropietario);
            }
            List<Entrega> attachedEntregaList = new ArrayList<Entrega>();
            for (Entrega entregaListEntregaToAttach : producto.getEntregaList()) {
                entregaListEntregaToAttach = em.getReference(entregaListEntregaToAttach.getClass(), entregaListEntregaToAttach.getIdentrega());
                attachedEntregaList.add(entregaListEntregaToAttach);
            }
            producto.setEntregaList(attachedEntregaList);
            em.persist(producto);
            if (marcaIdmarca != null) {
                marcaIdmarca.getProductoList().add(producto);
                marcaIdmarca = em.merge(marcaIdmarca);
            }
            if (propietarioIdpropietario != null) {
                propietarioIdpropietario.getProductoList().add(producto);
                propietarioIdpropietario = em.merge(propietarioIdpropietario);
            }
            for (Entrega entregaListEntrega : producto.getEntregaList()) {
                Producto oldProductoIdproductoOfEntregaListEntrega = entregaListEntrega.getProductoIdproducto();
                entregaListEntrega.setProductoIdproducto(producto);
                entregaListEntrega = em.merge(entregaListEntrega);
                if (oldProductoIdproductoOfEntregaListEntrega != null) {
                    oldProductoIdproductoOfEntregaListEntrega.getEntregaList().remove(entregaListEntrega);
                    oldProductoIdproductoOfEntregaListEntrega = em.merge(oldProductoIdproductoOfEntregaListEntrega);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdproducto());
            Marca marcaIdmarcaOld = persistentProducto.getMarcaIdmarca();
            Marca marcaIdmarcaNew = producto.getMarcaIdmarca();
            Propietario propietarioIdpropietarioOld = persistentProducto.getPropietarioIdpropietario();
            Propietario propietarioIdpropietarioNew = producto.getPropietarioIdpropietario();
            List<Entrega> entregaListOld = persistentProducto.getEntregaList();
            List<Entrega> entregaListNew = producto.getEntregaList();
            List<String> illegalOrphanMessages = null;
            for (Entrega entregaListOldEntrega : entregaListOld) {
                if (!entregaListNew.contains(entregaListOldEntrega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Entrega " + entregaListOldEntrega + " since its productoIdproducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (marcaIdmarcaNew != null) {
                marcaIdmarcaNew = em.getReference(marcaIdmarcaNew.getClass(), marcaIdmarcaNew.getIdmarca());
                producto.setMarcaIdmarca(marcaIdmarcaNew);
            }
            if (propietarioIdpropietarioNew != null) {
                propietarioIdpropietarioNew = em.getReference(propietarioIdpropietarioNew.getClass(), propietarioIdpropietarioNew.getIdpropietario());
                producto.setPropietarioIdpropietario(propietarioIdpropietarioNew);
            }
            List<Entrega> attachedEntregaListNew = new ArrayList<Entrega>();
            for (Entrega entregaListNewEntregaToAttach : entregaListNew) {
                entregaListNewEntregaToAttach = em.getReference(entregaListNewEntregaToAttach.getClass(), entregaListNewEntregaToAttach.getIdentrega());
                attachedEntregaListNew.add(entregaListNewEntregaToAttach);
            }
            entregaListNew = attachedEntregaListNew;
            producto.setEntregaList(entregaListNew);
            producto = em.merge(producto);
            if (marcaIdmarcaOld != null && !marcaIdmarcaOld.equals(marcaIdmarcaNew)) {
                marcaIdmarcaOld.getProductoList().remove(producto);
                marcaIdmarcaOld = em.merge(marcaIdmarcaOld);
            }
            if (marcaIdmarcaNew != null && !marcaIdmarcaNew.equals(marcaIdmarcaOld)) {
                marcaIdmarcaNew.getProductoList().add(producto);
                marcaIdmarcaNew = em.merge(marcaIdmarcaNew);
            }
            if (propietarioIdpropietarioOld != null && !propietarioIdpropietarioOld.equals(propietarioIdpropietarioNew)) {
                propietarioIdpropietarioOld.getProductoList().remove(producto);
                propietarioIdpropietarioOld = em.merge(propietarioIdpropietarioOld);
            }
            if (propietarioIdpropietarioNew != null && !propietarioIdpropietarioNew.equals(propietarioIdpropietarioOld)) {
                propietarioIdpropietarioNew.getProductoList().add(producto);
                propietarioIdpropietarioNew = em.merge(propietarioIdpropietarioNew);
            }
            for (Entrega entregaListNewEntrega : entregaListNew) {
                if (!entregaListOld.contains(entregaListNewEntrega)) {
                    Producto oldProductoIdproductoOfEntregaListNewEntrega = entregaListNewEntrega.getProductoIdproducto();
                    entregaListNewEntrega.setProductoIdproducto(producto);
                    entregaListNewEntrega = em.merge(entregaListNewEntrega);
                    if (oldProductoIdproductoOfEntregaListNewEntrega != null && !oldProductoIdproductoOfEntregaListNewEntrega.equals(producto)) {
                        oldProductoIdproductoOfEntregaListNewEntrega.getEntregaList().remove(entregaListNewEntrega);
                        oldProductoIdproductoOfEntregaListNewEntrega = em.merge(oldProductoIdproductoOfEntregaListNewEntrega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdproducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdproducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Entrega> entregaListOrphanCheck = producto.getEntregaList();
            for (Entrega entregaListOrphanCheckEntrega : entregaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Entrega " + entregaListOrphanCheckEntrega + " in its entregaList field has a non-nullable productoIdproducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Marca marcaIdmarca = producto.getMarcaIdmarca();
            if (marcaIdmarca != null) {
                marcaIdmarca.getProductoList().remove(producto);
                marcaIdmarca = em.merge(marcaIdmarca);
            }
            Propietario propietarioIdpropietario = producto.getPropietarioIdpropietario();
            if (propietarioIdpropietario != null) {
                propietarioIdpropietario.getProductoList().remove(producto);
                propietarioIdpropietario = em.merge(propietarioIdpropietario);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

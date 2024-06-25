/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.entity.Entrega;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entity.Producto;
import com.mycompany.entity.Servicio;
import com.mycompany.entity.EntregaRepuesto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class EntregaJpaController implements Serializable {

    public EntregaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entrega entrega) {
        if (entrega.getEntregaRepuestoList() == null) {
            entrega.setEntregaRepuestoList(new ArrayList<EntregaRepuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoIdproducto = entrega.getProductoIdproducto();
            if (productoIdproducto != null) {
                productoIdproducto = em.getReference(productoIdproducto.getClass(), productoIdproducto.getIdproducto());
                entrega.setProductoIdproducto(productoIdproducto);
            }
            Servicio servicioIdservicio = entrega.getServicioIdservicio();
            if (servicioIdservicio != null) {
                servicioIdservicio = em.getReference(servicioIdservicio.getClass(), servicioIdservicio.getIdservicio());
                entrega.setServicioIdservicio(servicioIdservicio);
            }
            List<EntregaRepuesto> attachedEntregaRepuestoList = new ArrayList<EntregaRepuesto>();
            for (EntregaRepuesto entregaRepuestoListEntregaRepuestoToAttach : entrega.getEntregaRepuestoList()) {
                entregaRepuestoListEntregaRepuestoToAttach = em.getReference(entregaRepuestoListEntregaRepuestoToAttach.getClass(), entregaRepuestoListEntregaRepuestoToAttach.getIdentregaRepuesto());
                attachedEntregaRepuestoList.add(entregaRepuestoListEntregaRepuestoToAttach);
            }
            entrega.setEntregaRepuestoList(attachedEntregaRepuestoList);
            em.persist(entrega);
            if (productoIdproducto != null) {
                productoIdproducto.getEntregaList().add(entrega);
                productoIdproducto = em.merge(productoIdproducto);
            }
            if (servicioIdservicio != null) {
                servicioIdservicio.getEntregaList().add(entrega);
                servicioIdservicio = em.merge(servicioIdservicio);
            }
            for (EntregaRepuesto entregaRepuestoListEntregaRepuesto : entrega.getEntregaRepuestoList()) {
                Entrega oldEntregaIdentregaOfEntregaRepuestoListEntregaRepuesto = entregaRepuestoListEntregaRepuesto.getEntregaIdentrega();
                entregaRepuestoListEntregaRepuesto.setEntregaIdentrega(entrega);
                entregaRepuestoListEntregaRepuesto = em.merge(entregaRepuestoListEntregaRepuesto);
                if (oldEntregaIdentregaOfEntregaRepuestoListEntregaRepuesto != null) {
                    oldEntregaIdentregaOfEntregaRepuestoListEntregaRepuesto.getEntregaRepuestoList().remove(entregaRepuestoListEntregaRepuesto);
                    oldEntregaIdentregaOfEntregaRepuestoListEntregaRepuesto = em.merge(oldEntregaIdentregaOfEntregaRepuestoListEntregaRepuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entrega entrega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega persistentEntrega = em.find(Entrega.class, entrega.getIdentrega());
            Producto productoIdproductoOld = persistentEntrega.getProductoIdproducto();
            Producto productoIdproductoNew = entrega.getProductoIdproducto();
            Servicio servicioIdservicioOld = persistentEntrega.getServicioIdservicio();
            Servicio servicioIdservicioNew = entrega.getServicioIdservicio();
            List<EntregaRepuesto> entregaRepuestoListOld = persistentEntrega.getEntregaRepuestoList();
            List<EntregaRepuesto> entregaRepuestoListNew = entrega.getEntregaRepuestoList();
            List<String> illegalOrphanMessages = null;
            for (EntregaRepuesto entregaRepuestoListOldEntregaRepuesto : entregaRepuestoListOld) {
                if (!entregaRepuestoListNew.contains(entregaRepuestoListOldEntregaRepuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EntregaRepuesto " + entregaRepuestoListOldEntregaRepuesto + " since its entregaIdentrega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productoIdproductoNew != null) {
                productoIdproductoNew = em.getReference(productoIdproductoNew.getClass(), productoIdproductoNew.getIdproducto());
                entrega.setProductoIdproducto(productoIdproductoNew);
            }
            if (servicioIdservicioNew != null) {
                servicioIdservicioNew = em.getReference(servicioIdservicioNew.getClass(), servicioIdservicioNew.getIdservicio());
                entrega.setServicioIdservicio(servicioIdservicioNew);
            }
            List<EntregaRepuesto> attachedEntregaRepuestoListNew = new ArrayList<EntregaRepuesto>();
            for (EntregaRepuesto entregaRepuestoListNewEntregaRepuestoToAttach : entregaRepuestoListNew) {
                entregaRepuestoListNewEntregaRepuestoToAttach = em.getReference(entregaRepuestoListNewEntregaRepuestoToAttach.getClass(), entregaRepuestoListNewEntregaRepuestoToAttach.getIdentregaRepuesto());
                attachedEntregaRepuestoListNew.add(entregaRepuestoListNewEntregaRepuestoToAttach);
            }
            entregaRepuestoListNew = attachedEntregaRepuestoListNew;
            entrega.setEntregaRepuestoList(entregaRepuestoListNew);
            entrega = em.merge(entrega);
            if (productoIdproductoOld != null && !productoIdproductoOld.equals(productoIdproductoNew)) {
                productoIdproductoOld.getEntregaList().remove(entrega);
                productoIdproductoOld = em.merge(productoIdproductoOld);
            }
            if (productoIdproductoNew != null && !productoIdproductoNew.equals(productoIdproductoOld)) {
                productoIdproductoNew.getEntregaList().add(entrega);
                productoIdproductoNew = em.merge(productoIdproductoNew);
            }
            if (servicioIdservicioOld != null && !servicioIdservicioOld.equals(servicioIdservicioNew)) {
                servicioIdservicioOld.getEntregaList().remove(entrega);
                servicioIdservicioOld = em.merge(servicioIdservicioOld);
            }
            if (servicioIdservicioNew != null && !servicioIdservicioNew.equals(servicioIdservicioOld)) {
                servicioIdservicioNew.getEntregaList().add(entrega);
                servicioIdservicioNew = em.merge(servicioIdservicioNew);
            }
            for (EntregaRepuesto entregaRepuestoListNewEntregaRepuesto : entregaRepuestoListNew) {
                if (!entregaRepuestoListOld.contains(entregaRepuestoListNewEntregaRepuesto)) {
                    Entrega oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto = entregaRepuestoListNewEntregaRepuesto.getEntregaIdentrega();
                    entregaRepuestoListNewEntregaRepuesto.setEntregaIdentrega(entrega);
                    entregaRepuestoListNewEntregaRepuesto = em.merge(entregaRepuestoListNewEntregaRepuesto);
                    if (oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto != null && !oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto.equals(entrega)) {
                        oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto.getEntregaRepuestoList().remove(entregaRepuestoListNewEntregaRepuesto);
                        oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto = em.merge(oldEntregaIdentregaOfEntregaRepuestoListNewEntregaRepuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entrega.getIdentrega();
                if (findEntrega(id) == null) {
                    throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.");
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
            Entrega entrega;
            try {
                entrega = em.getReference(Entrega.class, id);
                entrega.getIdentrega();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EntregaRepuesto> entregaRepuestoListOrphanCheck = entrega.getEntregaRepuestoList();
            for (EntregaRepuesto entregaRepuestoListOrphanCheckEntregaRepuesto : entregaRepuestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entrega (" + entrega + ") cannot be destroyed since the EntregaRepuesto " + entregaRepuestoListOrphanCheckEntregaRepuesto + " in its entregaRepuestoList field has a non-nullable entregaIdentrega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Producto productoIdproducto = entrega.getProductoIdproducto();
            if (productoIdproducto != null) {
                productoIdproducto.getEntregaList().remove(entrega);
                productoIdproducto = em.merge(productoIdproducto);
            }
            Servicio servicioIdservicio = entrega.getServicioIdservicio();
            if (servicioIdservicio != null) {
                servicioIdservicio.getEntregaList().remove(entrega);
                servicioIdservicio = em.merge(servicioIdservicio);
            }
            em.remove(entrega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entrega> findEntregaEntities() {
        return findEntregaEntities(true, -1, -1);
    }

    public List<Entrega> findEntregaEntities(int maxResults, int firstResult) {
        return findEntregaEntities(false, maxResults, firstResult);
    }

    private List<Entrega> findEntregaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entrega.class));
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

    public Entrega findEntrega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entrega.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entrega> rt = cq.from(Entrega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

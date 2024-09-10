/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entity.Entrega;
import com.mycompany.entity.EntregaRepuesto;
import com.mycompany.entity.Repuestos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class EntregaRepuestoJpaController implements Serializable {

    public EntregaRepuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_AppTaller_jar_1.0-SNAPSHOTPU");

    public EntregaRepuestoJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EntregaRepuesto entregaRepuesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega entregaIdentrega = entregaRepuesto.getEntregaIdentrega();
            if (entregaIdentrega != null) {
                entregaIdentrega = em.getReference(entregaIdentrega.getClass(), entregaIdentrega.getIdentrega());
                entregaRepuesto.setEntregaIdentrega(entregaIdentrega);
            }
            Repuestos repuestosidRepuestos = entregaRepuesto.getRepuestosidRepuestos();
            if (repuestosidRepuestos != null) {
                repuestosidRepuestos = em.getReference(repuestosidRepuestos.getClass(), repuestosidRepuestos.getIdRepuestos());
                entregaRepuesto.setRepuestosidRepuestos(repuestosidRepuestos);
            }
            em.persist(entregaRepuesto);
            if (entregaIdentrega != null) {
                entregaIdentrega.getEntregaRepuestoList().add(entregaRepuesto);
                entregaIdentrega = em.merge(entregaIdentrega);
            }
            if (repuestosidRepuestos != null) {
                repuestosidRepuestos.getEntregaRepuestoList().add(entregaRepuesto);
                repuestosidRepuestos = em.merge(repuestosidRepuestos);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EntregaRepuesto entregaRepuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntregaRepuesto persistentEntregaRepuesto = em.find(EntregaRepuesto.class, entregaRepuesto.getIdentregaRepuesto());
            Entrega entregaIdentregaOld = persistentEntregaRepuesto.getEntregaIdentrega();
            Entrega entregaIdentregaNew = entregaRepuesto.getEntregaIdentrega();
            Repuestos repuestosidRepuestosOld = persistentEntregaRepuesto.getRepuestosidRepuestos();
            Repuestos repuestosidRepuestosNew = entregaRepuesto.getRepuestosidRepuestos();
            if (entregaIdentregaNew != null) {
                entregaIdentregaNew = em.getReference(entregaIdentregaNew.getClass(), entregaIdentregaNew.getIdentrega());
                entregaRepuesto.setEntregaIdentrega(entregaIdentregaNew);
            }
            if (repuestosidRepuestosNew != null) {
                repuestosidRepuestosNew = em.getReference(repuestosidRepuestosNew.getClass(), repuestosidRepuestosNew.getIdRepuestos());
                entregaRepuesto.setRepuestosidRepuestos(repuestosidRepuestosNew);
            }
            entregaRepuesto = em.merge(entregaRepuesto);
            if (entregaIdentregaOld != null && !entregaIdentregaOld.equals(entregaIdentregaNew)) {
                entregaIdentregaOld.getEntregaRepuestoList().remove(entregaRepuesto);
                entregaIdentregaOld = em.merge(entregaIdentregaOld);
            }
            if (entregaIdentregaNew != null && !entregaIdentregaNew.equals(entregaIdentregaOld)) {
                entregaIdentregaNew.getEntregaRepuestoList().add(entregaRepuesto);
                entregaIdentregaNew = em.merge(entregaIdentregaNew);
            }
            if (repuestosidRepuestosOld != null && !repuestosidRepuestosOld.equals(repuestosidRepuestosNew)) {
                repuestosidRepuestosOld.getEntregaRepuestoList().remove(entregaRepuesto);
                repuestosidRepuestosOld = em.merge(repuestosidRepuestosOld);
            }
            if (repuestosidRepuestosNew != null && !repuestosidRepuestosNew.equals(repuestosidRepuestosOld)) {
                repuestosidRepuestosNew.getEntregaRepuestoList().add(entregaRepuesto);
                repuestosidRepuestosNew = em.merge(repuestosidRepuestosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entregaRepuesto.getIdentregaRepuesto();
                if (findEntregaRepuesto(id) == null) {
                    throw new NonexistentEntityException("The entregaRepuesto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EntregaRepuesto entregaRepuesto;
            try {
                entregaRepuesto = em.getReference(EntregaRepuesto.class, id);
                entregaRepuesto.getIdentregaRepuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregaRepuesto with id " + id + " no longer exists.", enfe);
            }
            Entrega entregaIdentrega = entregaRepuesto.getEntregaIdentrega();
            if (entregaIdentrega != null) {
                entregaIdentrega.getEntregaRepuestoList().remove(entregaRepuesto);
                entregaIdentrega = em.merge(entregaIdentrega);
            }
            Repuestos repuestosidRepuestos = entregaRepuesto.getRepuestosidRepuestos();
            if (repuestosidRepuestos != null) {
                repuestosidRepuestos.getEntregaRepuestoList().remove(entregaRepuesto);
                repuestosidRepuestos = em.merge(repuestosidRepuestos);
            }
            em.remove(entregaRepuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EntregaRepuesto> findEntregaRepuestoEntities() {
        return findEntregaRepuestoEntities(true, -1, -1);
    }

    public List<EntregaRepuesto> findEntregaRepuestoEntities(int maxResults, int firstResult) {
        return findEntregaRepuestoEntities(false, maxResults, firstResult);
    }

    private List<EntregaRepuesto> findEntregaRepuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EntregaRepuesto.class));
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

    public EntregaRepuesto findEntregaRepuesto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EntregaRepuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaRepuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EntregaRepuesto> rt = cq.from(EntregaRepuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

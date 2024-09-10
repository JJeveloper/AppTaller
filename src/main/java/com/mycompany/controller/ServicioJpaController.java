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
import com.mycompany.entity.Entrega;
import com.mycompany.entity.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_AppTaller_jar_1.0-SNAPSHOTPU");

    public ServicioJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getEntregaList() == null) {
            servicio.setEntregaList(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Entrega> attachedEntregaList = new ArrayList<Entrega>();
            for (Entrega entregaListEntregaToAttach : servicio.getEntregaList()) {
                entregaListEntregaToAttach = em.getReference(entregaListEntregaToAttach.getClass(), entregaListEntregaToAttach.getIdentrega());
                attachedEntregaList.add(entregaListEntregaToAttach);
            }
            servicio.setEntregaList(attachedEntregaList);
            em.persist(servicio);
            for (Entrega entregaListEntrega : servicio.getEntregaList()) {
                Servicio oldServicioIdservicioOfEntregaListEntrega = entregaListEntrega.getServicioIdservicio();
                entregaListEntrega.setServicioIdservicio(servicio);
                entregaListEntrega = em.merge(entregaListEntrega);
                if (oldServicioIdservicioOfEntregaListEntrega != null) {
                    oldServicioIdservicioOfEntregaListEntrega.getEntregaList().remove(entregaListEntrega);
                    oldServicioIdservicioOfEntregaListEntrega = em.merge(oldServicioIdservicioOfEntregaListEntrega);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getIdservicio());
            List<Entrega> entregaListOld = persistentServicio.getEntregaList();
            List<Entrega> entregaListNew = servicio.getEntregaList();
            List<String> illegalOrphanMessages = null;
            for (Entrega entregaListOldEntrega : entregaListOld) {
                if (!entregaListNew.contains(entregaListOldEntrega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Entrega " + entregaListOldEntrega + " since its servicioIdservicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Entrega> attachedEntregaListNew = new ArrayList<Entrega>();
            for (Entrega entregaListNewEntregaToAttach : entregaListNew) {
                entregaListNewEntregaToAttach = em.getReference(entregaListNewEntregaToAttach.getClass(), entregaListNewEntregaToAttach.getIdentrega());
                attachedEntregaListNew.add(entregaListNewEntregaToAttach);
            }
            entregaListNew = attachedEntregaListNew;
            servicio.setEntregaList(entregaListNew);
            servicio = em.merge(servicio);
            for (Entrega entregaListNewEntrega : entregaListNew) {
                if (!entregaListOld.contains(entregaListNewEntrega)) {
                    Servicio oldServicioIdservicioOfEntregaListNewEntrega = entregaListNewEntrega.getServicioIdservicio();
                    entregaListNewEntrega.setServicioIdservicio(servicio);
                    entregaListNewEntrega = em.merge(entregaListNewEntrega);
                    if (oldServicioIdservicioOfEntregaListNewEntrega != null && !oldServicioIdservicioOfEntregaListNewEntrega.equals(servicio)) {
                        oldServicioIdservicioOfEntregaListNewEntrega.getEntregaList().remove(entregaListNewEntrega);
                        oldServicioIdservicioOfEntregaListNewEntrega = em.merge(oldServicioIdservicioOfEntregaListNewEntrega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicio.getIdservicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getIdservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Entrega> entregaListOrphanCheck = servicio.getEntregaList();
            for (Entrega entregaListOrphanCheckEntrega : entregaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicio (" + servicio + ") cannot be destroyed since the Entrega " + entregaListOrphanCheckEntrega + " in its entregaList field has a non-nullable servicioIdservicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

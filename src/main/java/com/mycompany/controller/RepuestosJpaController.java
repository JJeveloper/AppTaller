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
import com.mycompany.entity.EntregaRepuesto;
import com.mycompany.entity.Repuestos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class RepuestosJpaController implements Serializable {

    public RepuestosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_AppTaller_jar_1.0-SNAPSHOTPU");

    public RepuestosJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Repuestos repuestos) {
        if (repuestos.getEntregaRepuestoList() == null) {
            repuestos.setEntregaRepuestoList(new ArrayList<EntregaRepuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca marcaIdmarca = repuestos.getMarcaIdmarca();
            if (marcaIdmarca != null) {
                marcaIdmarca = em.getReference(marcaIdmarca.getClass(), marcaIdmarca.getIdmarca());
                repuestos.setMarcaIdmarca(marcaIdmarca);
            }
            List<EntregaRepuesto> attachedEntregaRepuestoList = new ArrayList<EntregaRepuesto>();
            for (EntregaRepuesto entregaRepuestoListEntregaRepuestoToAttach : repuestos.getEntregaRepuestoList()) {
                entregaRepuestoListEntregaRepuestoToAttach = em.getReference(entregaRepuestoListEntregaRepuestoToAttach.getClass(), entregaRepuestoListEntregaRepuestoToAttach.getIdentregaRepuesto());
                attachedEntregaRepuestoList.add(entregaRepuestoListEntregaRepuestoToAttach);
            }
            repuestos.setEntregaRepuestoList(attachedEntregaRepuestoList);
            em.persist(repuestos);
            if (marcaIdmarca != null) {
                marcaIdmarca.getRepuestosList().add(repuestos);
                marcaIdmarca = em.merge(marcaIdmarca);
            }
            for (EntregaRepuesto entregaRepuestoListEntregaRepuesto : repuestos.getEntregaRepuestoList()) {
                Repuestos oldRepuestosidRepuestosOfEntregaRepuestoListEntregaRepuesto = entregaRepuestoListEntregaRepuesto.getRepuestosidRepuestos();
                entregaRepuestoListEntregaRepuesto.setRepuestosidRepuestos(repuestos);
                entregaRepuestoListEntregaRepuesto = em.merge(entregaRepuestoListEntregaRepuesto);
                if (oldRepuestosidRepuestosOfEntregaRepuestoListEntregaRepuesto != null) {
                    oldRepuestosidRepuestosOfEntregaRepuestoListEntregaRepuesto.getEntregaRepuestoList().remove(entregaRepuestoListEntregaRepuesto);
                    oldRepuestosidRepuestosOfEntregaRepuestoListEntregaRepuesto = em.merge(oldRepuestosidRepuestosOfEntregaRepuestoListEntregaRepuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Repuestos repuestos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Repuestos persistentRepuestos = em.find(Repuestos.class, repuestos.getIdRepuestos());
            Marca marcaIdmarcaOld = persistentRepuestos.getMarcaIdmarca();
            Marca marcaIdmarcaNew = repuestos.getMarcaIdmarca();
            List<EntregaRepuesto> entregaRepuestoListOld = persistentRepuestos.getEntregaRepuestoList();
            List<EntregaRepuesto> entregaRepuestoListNew = repuestos.getEntregaRepuestoList();
            List<String> illegalOrphanMessages = null;
            for (EntregaRepuesto entregaRepuestoListOldEntregaRepuesto : entregaRepuestoListOld) {
                if (!entregaRepuestoListNew.contains(entregaRepuestoListOldEntregaRepuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EntregaRepuesto " + entregaRepuestoListOldEntregaRepuesto + " since its repuestosidRepuestos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (marcaIdmarcaNew != null) {
                marcaIdmarcaNew = em.getReference(marcaIdmarcaNew.getClass(), marcaIdmarcaNew.getIdmarca());
                repuestos.setMarcaIdmarca(marcaIdmarcaNew);
            }
            List<EntregaRepuesto> attachedEntregaRepuestoListNew = new ArrayList<EntregaRepuesto>();
            for (EntregaRepuesto entregaRepuestoListNewEntregaRepuestoToAttach : entregaRepuestoListNew) {
                entregaRepuestoListNewEntregaRepuestoToAttach = em.getReference(entregaRepuestoListNewEntregaRepuestoToAttach.getClass(), entregaRepuestoListNewEntregaRepuestoToAttach.getIdentregaRepuesto());
                attachedEntregaRepuestoListNew.add(entregaRepuestoListNewEntregaRepuestoToAttach);
            }
            entregaRepuestoListNew = attachedEntregaRepuestoListNew;
            repuestos.setEntregaRepuestoList(entregaRepuestoListNew);
            repuestos = em.merge(repuestos);
            if (marcaIdmarcaOld != null && !marcaIdmarcaOld.equals(marcaIdmarcaNew)) {
                marcaIdmarcaOld.getRepuestosList().remove(repuestos);
                marcaIdmarcaOld = em.merge(marcaIdmarcaOld);
            }
            if (marcaIdmarcaNew != null && !marcaIdmarcaNew.equals(marcaIdmarcaOld)) {
                marcaIdmarcaNew.getRepuestosList().add(repuestos);
                marcaIdmarcaNew = em.merge(marcaIdmarcaNew);
            }
            for (EntregaRepuesto entregaRepuestoListNewEntregaRepuesto : entregaRepuestoListNew) {
                if (!entregaRepuestoListOld.contains(entregaRepuestoListNewEntregaRepuesto)) {
                    Repuestos oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto = entregaRepuestoListNewEntregaRepuesto.getRepuestosidRepuestos();
                    entregaRepuestoListNewEntregaRepuesto.setRepuestosidRepuestos(repuestos);
                    entregaRepuestoListNewEntregaRepuesto = em.merge(entregaRepuestoListNewEntregaRepuesto);
                    if (oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto != null && !oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto.equals(repuestos)) {
                        oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto.getEntregaRepuestoList().remove(entregaRepuestoListNewEntregaRepuesto);
                        oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto = em.merge(oldRepuestosidRepuestosOfEntregaRepuestoListNewEntregaRepuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = repuestos.getIdRepuestos();
                if (findRepuestos(id) == null) {
                    throw new NonexistentEntityException("The repuestos with id " + id + " no longer exists.");
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
            Repuestos repuestos;
            try {
                repuestos = em.getReference(Repuestos.class, id);
                repuestos.getIdRepuestos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The repuestos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EntregaRepuesto> entregaRepuestoListOrphanCheck = repuestos.getEntregaRepuestoList();
            for (EntregaRepuesto entregaRepuestoListOrphanCheckEntregaRepuesto : entregaRepuestoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Repuestos (" + repuestos + ") cannot be destroyed since the EntregaRepuesto " + entregaRepuestoListOrphanCheckEntregaRepuesto + " in its entregaRepuestoList field has a non-nullable repuestosidRepuestos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Marca marcaIdmarca = repuestos.getMarcaIdmarca();
            if (marcaIdmarca != null) {
                marcaIdmarca.getRepuestosList().remove(repuestos);
                marcaIdmarca = em.merge(marcaIdmarca);
            }
            em.remove(repuestos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Repuestos> findRepuestosEntities() {
        return findRepuestosEntities(true, -1, -1);
    }

    public List<Repuestos> findRepuestosEntities(int maxResults, int firstResult) {
        return findRepuestosEntities(false, maxResults, firstResult);
    }

    private List<Repuestos> findRepuestosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Repuestos.class));
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

    public Repuestos findRepuestos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Repuestos.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepuestosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Repuestos> rt = cq.from(Repuestos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

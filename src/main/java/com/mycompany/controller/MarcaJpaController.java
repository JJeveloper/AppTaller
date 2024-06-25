/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.entity.Marca;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entity.Repuestos;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entity.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class MarcaJpaController implements Serializable {

    public MarcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) {
        if (marca.getRepuestosList() == null) {
            marca.setRepuestosList(new ArrayList<Repuestos>());
        }
        if (marca.getProductoList() == null) {
            marca.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Repuestos> attachedRepuestosList = new ArrayList<Repuestos>();
            for (Repuestos repuestosListRepuestosToAttach : marca.getRepuestosList()) {
                repuestosListRepuestosToAttach = em.getReference(repuestosListRepuestosToAttach.getClass(), repuestosListRepuestosToAttach.getIdRepuestos());
                attachedRepuestosList.add(repuestosListRepuestosToAttach);
            }
            marca.setRepuestosList(attachedRepuestosList);
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : marca.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdproducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            marca.setProductoList(attachedProductoList);
            em.persist(marca);
            for (Repuestos repuestosListRepuestos : marca.getRepuestosList()) {
                Marca oldMarcaIdmarcaOfRepuestosListRepuestos = repuestosListRepuestos.getMarcaIdmarca();
                repuestosListRepuestos.setMarcaIdmarca(marca);
                repuestosListRepuestos = em.merge(repuestosListRepuestos);
                if (oldMarcaIdmarcaOfRepuestosListRepuestos != null) {
                    oldMarcaIdmarcaOfRepuestosListRepuestos.getRepuestosList().remove(repuestosListRepuestos);
                    oldMarcaIdmarcaOfRepuestosListRepuestos = em.merge(oldMarcaIdmarcaOfRepuestosListRepuestos);
                }
            }
            for (Producto productoListProducto : marca.getProductoList()) {
                Marca oldMarcaIdmarcaOfProductoListProducto = productoListProducto.getMarcaIdmarca();
                productoListProducto.setMarcaIdmarca(marca);
                productoListProducto = em.merge(productoListProducto);
                if (oldMarcaIdmarcaOfProductoListProducto != null) {
                    oldMarcaIdmarcaOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldMarcaIdmarcaOfProductoListProducto = em.merge(oldMarcaIdmarcaOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marca marca) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca persistentMarca = em.find(Marca.class, marca.getIdmarca());
            List<Repuestos> repuestosListOld = persistentMarca.getRepuestosList();
            List<Repuestos> repuestosListNew = marca.getRepuestosList();
            List<Producto> productoListOld = persistentMarca.getProductoList();
            List<Producto> productoListNew = marca.getProductoList();
            List<String> illegalOrphanMessages = null;
            for (Repuestos repuestosListOldRepuestos : repuestosListOld) {
                if (!repuestosListNew.contains(repuestosListOldRepuestos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Repuestos " + repuestosListOldRepuestos + " since its marcaIdmarca field is not nullable.");
                }
            }
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its marcaIdmarca field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Repuestos> attachedRepuestosListNew = new ArrayList<Repuestos>();
            for (Repuestos repuestosListNewRepuestosToAttach : repuestosListNew) {
                repuestosListNewRepuestosToAttach = em.getReference(repuestosListNewRepuestosToAttach.getClass(), repuestosListNewRepuestosToAttach.getIdRepuestos());
                attachedRepuestosListNew.add(repuestosListNewRepuestosToAttach);
            }
            repuestosListNew = attachedRepuestosListNew;
            marca.setRepuestosList(repuestosListNew);
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdproducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            marca.setProductoList(productoListNew);
            marca = em.merge(marca);
            for (Repuestos repuestosListNewRepuestos : repuestosListNew) {
                if (!repuestosListOld.contains(repuestosListNewRepuestos)) {
                    Marca oldMarcaIdmarcaOfRepuestosListNewRepuestos = repuestosListNewRepuestos.getMarcaIdmarca();
                    repuestosListNewRepuestos.setMarcaIdmarca(marca);
                    repuestosListNewRepuestos = em.merge(repuestosListNewRepuestos);
                    if (oldMarcaIdmarcaOfRepuestosListNewRepuestos != null && !oldMarcaIdmarcaOfRepuestosListNewRepuestos.equals(marca)) {
                        oldMarcaIdmarcaOfRepuestosListNewRepuestos.getRepuestosList().remove(repuestosListNewRepuestos);
                        oldMarcaIdmarcaOfRepuestosListNewRepuestos = em.merge(oldMarcaIdmarcaOfRepuestosListNewRepuestos);
                    }
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Marca oldMarcaIdmarcaOfProductoListNewProducto = productoListNewProducto.getMarcaIdmarca();
                    productoListNewProducto.setMarcaIdmarca(marca);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldMarcaIdmarcaOfProductoListNewProducto != null && !oldMarcaIdmarcaOfProductoListNewProducto.equals(marca)) {
                        oldMarcaIdmarcaOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldMarcaIdmarcaOfProductoListNewProducto = em.merge(oldMarcaIdmarcaOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marca.getIdmarca();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getIdmarca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Repuestos> repuestosListOrphanCheck = marca.getRepuestosList();
            for (Repuestos repuestosListOrphanCheckRepuestos : repuestosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Marca (" + marca + ") cannot be destroyed since the Repuestos " + repuestosListOrphanCheckRepuestos + " in its repuestosList field has a non-nullable marcaIdmarca field.");
            }
            List<Producto> productoListOrphanCheck = marca.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Marca (" + marca + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable marcaIdmarca field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(marca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

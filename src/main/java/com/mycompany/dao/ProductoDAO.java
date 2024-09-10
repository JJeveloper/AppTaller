package com.mycompany.dao;

import com.mycompany.controller.ProductoJpaController;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.entity.Producto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JJAB
 */
public class ProductoDAO {

    private ProductoJpaController pjc = new ProductoJpaController();

    public void guardarProducto(Producto p) {
        try {
            pjc.create(p);
        } catch (Exception e) {
            System.out.println("No se puede guardar el producto");
        }
    }

    public List<Producto> mostrarProductosPorEstado(char estado) {
        try {

            Query query = pjc.getEntityManager().createNamedQuery("Producto.findByEstado");
            query.setParameter("estado", estado);
            return query.getResultList();

        } catch (Exception e) {
            System.out.println("No se puede obtener los productos " + e);
        }
        return null;
    }

    public Producto obtenerObjetoProductoporId(int id) {
        try {
            return pjc.findProducto(id);

        } catch (Exception e) {
            System.out.println("No se pudo obtener el producto ");
        }
        return null;
    }

    public List<Producto> mostrarProductoPorId(int id) {
        Query query = pjc.getEntityManager().createNamedQuery("Producto.findByIdproducto");
        query.setParameter("idproducto", id);
        return query.getResultList();
    }

    public void actualizarEstado(int id) {
        try {

            Producto producto = pjc.findProducto(id);

            producto.setEstado('f');

            pjc.edit(producto);

        } catch (Exception e) {
            System.out.println("No se puedo actualizar el estado");
        }

    }

}

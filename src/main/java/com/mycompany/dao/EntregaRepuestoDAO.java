package com.mycompany.dao;

import com.mycompany.controller.EntregaRepuestoJpaController;
import com.mycompany.entity.Entrega;
import com.mycompany.entity.EntregaRepuesto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JJAB
 */
public class EntregaRepuestoDAO {

    private EntregaRepuestoJpaController entregaRepuestoJPA = new EntregaRepuestoJpaController();

    public void guardarEntregaRepuesto(EntregaRepuesto entregaRepuesto) {
        try {

            entregaRepuestoJPA.create(entregaRepuesto);

        } catch (Exception e) {
            System.out.println("No se pudo guardar la informacion");
        }

    }

    public List<EntregaRepuesto> obtenerRepuestosXIdEntrega(Entrega en) {

        try {

            Query query = entregaRepuestoJPA.getEntityManager().createNamedQuery("EntregaRepuesto.findByIdentrega");
            query.setParameter("entregaIdentrega", en);

            return query.getResultList();
        } catch (Exception e) {
            System.out.println("No se pudo obtener la informacion " + e);
        }
        return null;
    }

}

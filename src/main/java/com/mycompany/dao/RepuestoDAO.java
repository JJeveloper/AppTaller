package com.mycompany.dao;

import com.mycompany.controller.RepuestosJpaController;
import com.mycompany.entity.Repuestos;
import java.util.List;

/**
 *
 * @author JJAB
 */
public class RepuestoDAO {

    RepuestosJpaController rjc = new RepuestosJpaController();

    public List<Repuestos> obtenerRepuestos() {

        try {

            return rjc.findRepuestosEntities();

        } catch (Exception e) {
            System.out.println("No se puede obtener los registros");
        }

        return null;
    }

    public Repuestos obtenerRepuestoId(int x) {

        try {

            return rjc.findRepuestos(x);

        } catch (Exception e) {
            System.out.println("No se pudo obtener el objeto Repuestos");
            return null;
        }

    }

    public boolean crearRepuesto(Repuestos r) {

        try {
            rjc.create(r);
            return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar el registro DAO");
            return false;
        }
    }

    public boolean actualizarStock(int id, int stock) {

        try {

            Repuestos repuestos = rjc.findRepuestos(id);

            repuestos.setStock(stock);

            rjc.edit(repuestos);
            return true;

        } catch (Exception e) {
            System.out.println("No se pudo actualizar el registro DAO !!" + e.getMessage());
            return true;

        }
    }

}

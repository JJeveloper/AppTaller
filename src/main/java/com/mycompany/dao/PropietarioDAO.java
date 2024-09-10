package com.mycompany.dao;

import com.mycompany.controller.PropietarioJpaController;
import com.mycompany.entity.Propietario;
import java.util.List;

/**
 *
 * @author JJAB
 */
public class PropietarioDAO {

    private PropietarioJpaController pjc = new PropietarioJpaController();

    public boolean guardarPropietario(String cedula, String nombres, String telefono) {

        try {

            pjc.create(new Propietario(Integer.BYTES, cedula, nombres, telefono));

            return true;
        } catch (Exception e) {
            System.out.println("No se pudo guardar el registro");
        }
        return false;
    }

    public List<Propietario> obtenerPropietarios() {

        try {

            return pjc.findPropietarioEntities();

        } catch (Exception e) {
            System.out.println("No se puede obtener los registros");
        }
        return null;
    }

    public Propietario obtenerPropietarioId(int id) {

        try {
            return pjc.findPropietario(id);

        } catch (Exception e) {
            
        }
        return null;
    }

}

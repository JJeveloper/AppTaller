package com.mycompany.dao;

import com.mycompany.controller.ServicioJpaController;
import com.mycompany.entity.Servicio;

/**
 *
 * @author JJAB
 */
public class ServicioDAO {

    ServicioJpaController servicioJPA = new ServicioJpaController();

    public Servicio obtenerObjetoServicioporId(int id) {
        try {
            return servicioJPA.findServicio(id);
        } catch (Exception e) {
        }

        return null;

    }

}

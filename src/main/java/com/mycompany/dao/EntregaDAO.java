package com.mycompany.dao;

import com.mycompany.controller.EntregaJpaController;
import com.mycompany.entity.Entrega;

/**
 *
 * @author JJAB
 */
public class EntregaDAO {

    private EntregaJpaController entregaJPA = new EntregaJpaController();

    public void guardarEntrega(Entrega entrega) {
        try {

            entregaJPA.create(entrega);

        } catch (Exception e) {
            System.out.println("No se puede realizar la entrega");
        }

    }

}

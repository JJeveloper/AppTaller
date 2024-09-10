/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.controller.MarcaJpaController;
import com.mycompany.entity.Marca;
import java.util.List;

/**
 *
 * @author JJAB
 */
public class MarcaDAO {

    private MarcaJpaController mjc = new MarcaJpaController();

    public List<Marca> obtenerMarcas() {

        try {

            return mjc.findMarcaEntities();

        } catch (Exception e) {
            System.out.println("No se pudo guardar el registro");
        }

        return null;

    }

    public Marca obtenerMarcaId(int id) {
        try {
            return mjc.findMarca(id);
        } catch (Exception e) {
        }
        return null;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resttechsoutions.criteriawithhibernate.main;

import com.resttechsoutions.criteriawithhibernate.entities.Productos;
import com.resttechsoutions.criteriawithhibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Rafael Estrella
 */
public class SeleccionMultipleApiCriteria {
    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();
        
        //SELECT p.descripcion, p.categoriaid.nombrecat FROM Productos p
        
        CriteriaBuilder cb = s.getCriteriaBuilder(); //Se crea el bullder para crear el cliteria
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class); // Se le indica al citeria que el contendra varios tipos de datos
        Root<Productos> root = cq.from(Productos.class); // Se le indica al criteria de donde obtendra los datos
       
        
        // Campos que queremos obtener del objeto principal y de los objetos enlazados:
        //  1: Si queremos obtener los campos del objeto principal, solo debemos llamar el campo desde el root diretamente:
                // root.get("")
        //  2: Si queremos obtener los campos desde los objetos enlazados, solo debemos llamar al campo enlazado y luego el campo:
                // root.get("objeto dentro de la clase root").get("campo de este objeto")
        
        cq.select(cb            // Aca se crea el SELECT y como obtiene varios campos y de diferentes tipos, usamos el metodo array del CriteryaBuilder
                .array(
                        root.get("productoid"),
                        root.get("descripcion"), 
                        root.get("categoriaid")
                                .get("nombrecat"),
                        root.get("proveedorid")
                                .get("nombreprov")
                )
        );
        
        List<Object[]> resultados = s.createQuery(cq).getResultList(); //Se crea un List que guarge un array de tipo general "object" 
                                                                             //para albergar los distintos campos de distintos tipos
        resultados
                .stream()
                .forEach(
                p -> System.out.println("ID: " + p[0] + ", Producto: " + p[1] + ", Categoria: " + p[2] + ", Proveedor: " + p[3])
        );
    }
}

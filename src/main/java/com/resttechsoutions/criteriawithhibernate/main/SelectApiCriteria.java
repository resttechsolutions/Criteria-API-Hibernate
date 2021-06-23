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
import org.hibernate.query.Query;

/**
 *
 * @author Rafael Estrella
 */
public class SelectApiCriteria {
    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();
        
        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        //SELECT DISTINCT p.descripcion FROM Productos p
        CriteriaQuery<String> cq = cb.createQuery(String.class); //Aca se especifica el tipo de dato que retornaran los campos
        Root<Productos> root = cq.from(Productos.class); //Aca se especifica de que tabla se sacaran esos campos
        
        cq.select(root.get("descripcion")).distinct(true); // Aca se arma el query especificando los campos y las condiciones que conforman dicho query
        
        Query<String> query = s.createQuery(cq); // Aca se envia el query a la BD almacenando el resultado en la referencia Query
        
        List<String> productos = query.getResultList(); // Aca el resultado obtenido en la BD esta listo para ser procesado 
        
        productos.stream()
                .forEach(
                        p -> System.out.println("Descripcion: " + p)
                );
    }
}

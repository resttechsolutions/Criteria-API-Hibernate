/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resttechsoutions.criteriawithhibernate.main;

import com.resttechsoutions.criteriawithhibernate.entities.Productos;
import com.resttechsoutions.criteriawithhibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Rafael Estrella
 */
public class SeleccionMultipleTuple {
    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();
        
        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        //SELECT p.descripcionm p.categoriaid.nombrecat FROM Productos p
        
        CriteriaQuery<Tuple> cq = cb.createTupleQuery(); //Se le indica a la referencia CriteriaQuery que sera de tipo Tulpe
        Root<Productos> root = cq.from(Productos.class); // Se le indica al Root de que entidad se obtendra la info
        
        cq.select(  //Se crea el SELECT
                cb.tuple( // Pasandole el metodo tuple del CriteriaBuilder
                        root.get("descripcion"),  // Donde se llamaran los campos deseados de la entidad del root
                        root.get("categoriaid")
                                .get("nombrecat")));
        
        List<Tuple> resultados = 
                s.createQuery(cq)   //Se obtiene el resultado desde el CriteriaQuery
                        .getResultList(); //Solicitandolo como Lista
        
        resultados
                .stream()
                .forEach(
                        //Se llama cada campo mediante numeros segun la posicion indicada en el select
                        p -> System.out.println("Descripcion: " 
                                + p.get(0) 
                                + ", Categoria:" 
                                + p.get(1))
                );
    }
}

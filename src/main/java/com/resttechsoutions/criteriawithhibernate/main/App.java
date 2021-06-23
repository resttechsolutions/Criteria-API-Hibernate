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
public class App {
    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();
        
        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        CriteriaQuery<Productos> cq = cb.createQuery(Productos.class);
        Root<Productos> root = cq.from(Productos.class);
        
        cq.select(root);
        
        Query<Productos> query = s.createQuery(cq);
        List<Productos> productos = query.getResultList();
        
        productos.stream()
                .forEach(
                        p -> System.out.println(p.getDescripcion() + "\t\t\t" + p.getPreciounit())
                );
    }
}

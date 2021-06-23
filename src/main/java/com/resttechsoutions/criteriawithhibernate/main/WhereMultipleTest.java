/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resttechsoutions.criteriawithhibernate.main;

import com.resttechsoutions.criteriawithhibernate.entities.Clientes;
import com.resttechsoutions.criteriawithhibernate.entities.Ordenes;
import com.resttechsoutions.criteriawithhibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Rafael Estrella
 */
public class WhereMultipleTest {

    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        CriteriaQuery<Clientes> cq = cb.createQuery(Clientes.class);
        
        Root<Clientes> rootClientes = cq.from(Clientes.class);
        
        Join<Clientes, Ordenes> join = rootClientes.join("ordenesList");
        
        cq.select(rootClientes).distinct(true);
        cq.where(cb
                .and(cb
                        .like(rootClientes
                                .get("nombrecontacto"), "p%"),
                        cb.gt(join.get("descuento"), 3)
                )
        );
        
        List<Clientes> resultados = s.createQuery(cq).getResultList();
        
        resultados.stream()
                .forEach(
                        c -> System.out.println(c.getNombrecontacto())
                );
    }
}

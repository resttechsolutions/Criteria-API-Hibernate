/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resttechsoutions.criteriawithhibernate.main;

import com.resttechsoutions.criteriawithhibernate.entities.Ordenes;
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
public class WhereSimpleTest {

    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        CriteriaQuery<Ordenes> cq = cb.createQuery(Ordenes.class);
        Root<Ordenes> rootOrdenes = cq.from(Ordenes.class);
        
        cq.select(rootOrdenes);
        cq.where(cb.gt(rootOrdenes.get("descuento"), 3));
        
        List<Ordenes> resultados = s.createQuery(cq).getResultList();
        
        resultados.stream()
                .forEach(
                        o -> System.out.println(o.getClienteid().getNombrecontacto() + " Descuento: " + o.getDescuento() + "%")
                );
        
    }
}

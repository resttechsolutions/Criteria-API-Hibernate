/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resttechsoutions.criteriawithhibernate.main;

import com.resttechsoutions.criteriawithhibernate.entities.Categorias;
import com.resttechsoutions.criteriawithhibernate.entities.Productos;
import com.resttechsoutions.criteriawithhibernate.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Rafael Estrella
 */
public class JoinTest {
    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();
        
        CriteriaBuilder cb = s.getCriteriaBuilder();
        
        //SELECT c, p.descripcion FROM Categorias c INNER JOIN c.productosList p
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        
        Root<Categorias> root = cq .from(Categorias.class);
        
        Join<Categorias, Productos> join = root.join("productosList", JoinType.INNER);
        
        cq.multiselect(root, join);
//        cq.multiselect(root.get("nombrecat"), root.get("descripcion"), join);
        
        List<Object[]> resultados = s.createQuery(cq).getResultList();
        
        resultados.stream()
                .forEach(
                        c -> System.out.println("Categoria: " + ((Categorias)c[0]).getNombrecat()
                        + " Productos: " + ((Productos) c[1]).getDescripcion())
                );
    }
}

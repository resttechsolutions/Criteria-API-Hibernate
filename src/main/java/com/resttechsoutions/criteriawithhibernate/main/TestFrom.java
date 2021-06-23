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
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author Rafael Estrella
 */
public class TestFrom {

    public static void main(String[] args) {
        HibernateUtil hu = new HibernateUtil();
        Session s = hu.getSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();

        //SELECT p, c FROM Productos p Categorias c
        CriteriaQuery<Tuple> cq = cb.createTupleQuery(); //Se le indica a la referencia CriteriaQuery que sera de tipo Tulpe
        Root<Productos> rootProductos = cq.from(Productos.class); // Se crea un root de tipo Productos
        Root<Categorias> rootCategorias = cq.from(Categorias.class); // Se crea un root de tipo Categorias

        cq.multiselect(rootProductos, rootCategorias); // Se le pasa al select los roots creados para generar el query

        List<Tuple> resultados = s.createQuery(cq).getResultList(); // Se obtiene la data y se almacena en una lista

        resultados.forEach(
                r -> {
                    // Se obtienen los roots por individual haciendo un castimg a cada entidad con la que generamos el query
                    //  De esta manera los objetos no estan relacionados
                    Productos p = (Productos) r.get(0);     
                    Categorias c = (Categorias) r.get(1);
                    
                    System.out.println("Producto: " + p.getDescripcion() + " Precio: " + p.getPreciounit());
                    System.out.println("Categoria: " + c.getNombrecat());
                }
        );
    }
}

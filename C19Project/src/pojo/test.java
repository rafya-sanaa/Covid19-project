/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import hibernateUtile.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Leopard
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String l = "Africa";
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();
      org.hibernate.Transaction tx = session.beginTransaction();
      Query query=  session.createQuery("from Covid group by isoCode ");
      tx.commit();
      List results = query.list();
        System.out.println(results.size());
    }
    
}

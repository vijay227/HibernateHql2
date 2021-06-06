package com.src.HibernateHql2.HibernateHql2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.src.HibernateHql2.HibernateHql2.Model.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 Configuration config = new Configuration().configure().addAnnotatedClass(Student.class);    
         ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
         SessionFactory sf = config.buildSessionFactory(registry);   
         Session session = sf.openSession();
         int b = 60;
         
         session.beginTransaction();
         
//         Query q = session.createQuery("select rollno,name,marks from Student where rollno=7");
//         Query q = session.createQuery("select rollno,name,marks from Student");
           Query q = session.createQuery("select sum(marks) from Student s where s.marks> :b");
           q.setParameter("b", b);
           Long marks = (Long) q.uniqueResult();
         
//         for(Object o : student)
//         {
//         	System.out.println(o);
//         }
         
        
         System.out.println(marks);
   
         session.getTransaction().commit();
   

    }
}

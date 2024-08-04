package com.ducat;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil is a utility class that provides a singleton instance of Hibernate's SessionFactory.
 * It also handles the shutdown of the SessionFactory.
 */
public class HibernateUtil {

    // Singleton instance of SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds the SessionFactory instance based on Hibernate configuration.
     *
     * @return The SessionFactory instance.
     */
    private static SessionFactory buildSessionFactory() {
    	
        try {
        	
            // Create the SessionFactory from Hibernate configuration
            return new Configuration().configure().buildSessionFactory();
            
        } catch (Throwable ex) {
        	
            // Log the exception and rethrow it as an ExceptionInInitializerError
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the singleton instance of the SessionFactory.
     *
     * @return The SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {
    	
        return sessionFactory;
    }

    /**
     * Closes the SessionFactory, releasing any resources held.
     */
    public static void shutdown() {
    	
        if (sessionFactory != null && !sessionFactory.isClosed()) {
        	
            sessionFactory.close();
            System.out.println("SessionFactory closed.");
        }
    }
}

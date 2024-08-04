package com.ducat;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The EmployeeDAO class provides methods to perform CRUD operations on Employee entities.
 */
public class EmployeeDAO {

    /**
     * Adds a new employee to the database.
     *
     * @param employee The employee to be added.
     */
    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing employee in the database.
     *
     * @param employee The employee with updated details.
     */
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(employee); // Use merge instead of update
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Deletes an employee from the database.
     *
     * @param id The ID of the employee to be deleted.
     */
    public void deleteEmployee(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.find(Employee.class, id); // Use find instead of get
            if (employee != null) {
                session.remove(employee); // Use remove instead of delete
                System.out.println("Employee is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an employee from the database by ID.
     *
     * @param id The ID of the employee to be retrieved.
     * @return The employee with the specified ID, or null if not found.
     */
    public Employee getEmployee(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Employee.class, id); // Use find instead of get
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return A list of all employees.
     */
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.getResultList(); // Use getResultList instead of list
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

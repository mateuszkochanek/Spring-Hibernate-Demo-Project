package com.real.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.real.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
				
		@Override
		public List<Customer> getCustomers() {
			
			// get the current hibernate session
			Session currentSession = sessionFactory.getCurrentSession();
					
			// create a query
			Query<Customer> theQuery = 
					currentSession.createQuery("from Customer order by lastName", Customer.class);
			
			// execute query and get result list
			List<Customer> customers = theQuery.getResultList();
					
			// return the results		
			return customers;
		}

		@Override
		public void saveCustomer(Customer theCustomer) {
			
			Session session = sessionFactory.getCurrentSession();
			
			session.saveOrUpdate(theCustomer);
			
		}

		@Override
		public Customer getCustomer(int theId) {
			
			Session session = sessionFactory.getCurrentSession();
			
			Customer customer = session.get(Customer.class, theId);
			
			return customer;
		}

		@Override
		public void deleteCustomer(int theId) {
			
			Session session = sessionFactory.getCurrentSession();
			
			Query theQuery = session.createQuery("delete from Customer where id=:customerId");
			
			theQuery.setParameter("customerId", theId);
			
			theQuery.executeUpdate();
		}

}

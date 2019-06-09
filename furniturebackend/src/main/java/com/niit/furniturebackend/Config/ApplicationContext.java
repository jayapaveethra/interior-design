
package com.niit.furniturebackend.Config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.furniturebackend.Dao.AuthenticationDao;
import com.niit.furniturebackend.Dao.BillingDao;
import com.niit.furniturebackend.Dao.CardDao;
import com.niit.furniturebackend.Dao.CartDao;
import com.niit.furniturebackend.Dao.CartItemsDao;
import com.niit.furniturebackend.Dao.CategoryDao;
import com.niit.furniturebackend.Dao.OrderItemsDao;
import com.niit.furniturebackend.Dao.OrdersDao;
import com.niit.furniturebackend.Dao.PayDao;
import com.niit.furniturebackend.Dao.ProductDao;
import com.niit.furniturebackend.Dao.ShippingDao;
import com.niit.furniturebackend.Dao.SupplierDao;
import com.niit.furniturebackend.Dao.UsersDao;
import com.niit.furniturebackend.DaoImpl.AuthenticationDaoImpl;
import com.niit.furniturebackend.DaoImpl.BillingDaoImpl;
import com.niit.furniturebackend.DaoImpl.CardDaoImpl;
import com.niit.furniturebackend.DaoImpl.CartDaoImpl;
import com.niit.furniturebackend.DaoImpl.CartItemsDaoImpl;
import com.niit.furniturebackend.DaoImpl.CategoryDaoImpl;
import com.niit.furniturebackend.DaoImpl.OrderItemsDaoImpl;
import com.niit.furniturebackend.DaoImpl.OrdersDaoImpl;
import com.niit.furniturebackend.DaoImpl.PayDaoImpl;
import com.niit.furniturebackend.DaoImpl.ProductDaoImpl;
import com.niit.furniturebackend.DaoImpl.ShippingDaoImpl;
import com.niit.furniturebackend.DaoImpl.SupplierDaoImpl;
import com.niit.furniturebackend.DaoImpl.UsersDaoImpl;
import com.niit.furniturebackend.Model.Authentication;
import com.niit.furniturebackend.Model.Billing;
import com.niit.furniturebackend.Model.Card;
import com.niit.furniturebackend.Model.Cart;
import com.niit.furniturebackend.Model.CartItems;
import com.niit.furniturebackend.Model.Category;
import com.niit.furniturebackend.Model.OrderItems;
import com.niit.furniturebackend.Model.Orders;
import com.niit.furniturebackend.Model.Pay;
import com.niit.furniturebackend.Model.Product;
import com.niit.furniturebackend.Model.Shipping;
import com.niit.furniturebackend.Model.Supplier;
import com.niit.furniturebackend.Model.Users;

@Configuration
	@ComponentScan("com.niit.*")
	@EnableTransactionManagement
	public class ApplicationContext 
	{

		@Bean("dataSource")
		public DataSource getDataSource() 
		{
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:tcp://localhost/~/test/project2");
			dataSource.setUsername("sa");
			dataSource.setPassword("sa");

			// Properties connectionProperties=new Properties();
			// connectionProperties.setProperty("hibernate.show_sql", "true");
			// connectionProperties.setProperty("hibernate.dialect",
			// "org.hibernate.dialect");
			return dataSource;
		}

		
		private Properties getHibernateProperties()
		{
			Properties properties = new Properties();
			properties.put("hibernate.connection.pool_size", "10");
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			return properties;
		}

		@Autowired
		@Bean("sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource) 
		{
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.addAnnotatedClass(Billing.class);
			sessionBuilder.addAnnotatedClass(CartItems.class);
			sessionBuilder.addAnnotatedClass(Cart.class);
			sessionBuilder.addAnnotatedClass(OrderItems.class);
			sessionBuilder.addAnnotatedClass(Category.class);
			sessionBuilder.addAnnotatedClass(Authentication.class);
			sessionBuilder.addAnnotatedClass(Orders.class);
			sessionBuilder.addAnnotatedClass(Shipping.class);
			sessionBuilder.addAnnotatedClass(Pay.class);
			sessionBuilder.addAnnotatedClass(Product.class);
			sessionBuilder.addAnnotatedClass(Supplier.class);
			sessionBuilder.addAnnotatedClass(Users.class);
			sessionBuilder.addAnnotatedClass(Card.class);
			return sessionBuilder.buildSessionFactory();
		}

		
		@Autowired
		@Bean(name="transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			System.out.println("printing session factory here : "+sessionFactory);
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			System.out.println("printing transactionManager factory here : "+transactionManager);
			return transactionManager;
		}



	   	@Autowired
	   	@Bean("categoryDao")
	   	public CategoryDao getCategoryDao(SessionFactory sessionFactory) 
	   	{
	   		return new CategoryDaoImpl(sessionFactory);
	   	}

	   	@Autowired
	   	@Bean("authenticationDao")
	   	public AuthenticationDao getAuthenticationDao(SessionFactory sessionFactory)
	   	{
	   		return new AuthenticationDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("billingDao")
	   	public BillingDao getBillingDao(SessionFactory sessionFactory)
	   	{
	   		return new BillingDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("cardDao")
	   	public CardDao getCardDao(SessionFactory sessionFactory)
	   	{
	   		return new CardDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("cartDao")
	   	public CartDao getCartDao(SessionFactory sessionFactory)
	   	{
	   		return new CartDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("cartItemsDao")
	   	public CartItemsDao getCartItemsDao(SessionFactory sessionFactory)
	   	{
	   		return new CartItemsDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("orderItemsDao")
	   	public OrderItemsDao getOrderItemsDao(SessionFactory sessionFactory)
	   	{
	   		return new OrderItemsDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("ordersDao")
	   	public OrdersDao getOrdersDao(SessionFactory sessionFactory)
	   	{
	   		return new OrdersDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("payDao")
	   	public PayDao getPayDao(SessionFactory sessionFactory)
	   	{
	   		return new PayDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("productDao")
	   	public ProductDao getProductDao(SessionFactory sessionFactory)
	   	{
	   		return new ProductDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("shippingDao")
	   	public ShippingDao getShippingDao(SessionFactory sessionFactory)
	   	{
	   		return new ShippingDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("supplierDao")
	   	public SupplierDao getDao(SessionFactory sessionFactory)
	   	{
	   		return new SupplierDaoImpl(sessionFactory);
	   	}
	   	@Autowired
	   	@Bean("usersDao")
	   	public UsersDao getUsersDao(SessionFactory sessionFactory)
	   	{
	   		return new UsersDaoImpl(sessionFactory);
	   	}
	   	
	}


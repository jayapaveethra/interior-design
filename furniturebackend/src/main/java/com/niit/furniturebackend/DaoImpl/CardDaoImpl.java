package com.niit.furniturebackend.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.furniturebackend.Dao.CardDao;
import com.niit.furniturebackend.Model.Card;

@Repository("cardDao")
@Transactional
public class CardDaoImpl implements CardDao
{
	 @Autowired
	    SessionFactory sessionFactory;
	    public CardDaoImpl(SessionFactory sessionFactory) 
	    {
	    	this.sessionFactory=sessionFactory;
	    }
	    @Override
		public boolean saveorupdate( Card card) {
			sessionFactory.getCurrentSession().saveOrUpdate(card);
			return true;
		}
	    @Override
		public boolean delete( Card  card) {
			sessionFactory.getCurrentSession().delete( card);
			return true;
		}
	    @Override
		public Card getCard(String  cardId) {
			String q1="from  Card where cartId='"+cardId+"'";
			Query w= sessionFactory.getCurrentSession().createQuery(q1);
			List< Card> list= (List< Card>)w.list();
			if(list==null || list.isEmpty())
			{
			return null;
		}
			return list.get(0);
	    }
	    @Override
		public List<Card> cardlist() {
			List<Card> cards=(List<Card>)sessionFactory.getCurrentSession().createCriteria(Card.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return cards;
		}
}


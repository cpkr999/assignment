package com.java.spring.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showpage() {
		
		return "home-page";
	}
	
	@RequestMapping("/checkEligibility")
	public String validateData(HttpServletRequest request) {
		
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pan.class).buildSessionFactory();
		
		Session session= factory.openSession();
		
		String panNo= request.getParameter("PAN");
		
		Transaction tx= null;
		
		try {
			tx=session.beginTransaction();
			
			Pan pan= new Pan("AXSSP1122H", 3);
			session.save(pan);
			Pan pan1= new Pan("APPSA3355P", 8);
			session.save(pan1);
			Pan pan2= new Pan("APPXA2244X", 9);
			session.save(pan2);
			Pan pan3= new Pan("AXVPS7766V", 1);
			session.save(pan3);
			Pan pan4= new Pan("axssp1122h", 3);
			session.save(pan4);
			Pan pan5= new Pan("appsa3355p", 8);
			session.save(pan5);
			Pan pan6= new Pan("appxa2244x", 9);
			session.save(pan6);
			Pan pan7= new Pan("axvps7766v", 1);
			session.save(pan7);
			Pan pan8= new Pan("asxps2121s", 5);
			session.save(pan8);
			Pan pan9= new Pan("ASXPS2121S", 5);
			session.save(pan9);
			
		
			//Pan panId= session.get(Pan.class, pan.getPanId());
			
			List<Pan> panDetails= session.createQuery("SELECT panId FROM Pan").getResultList();
			
			Iterator iterator = panDetails.iterator();
			
	
	
			while(iterator.hasNext()) {
				
				String test= (String)iterator.next();
		
				int score_value= session.createQuery("SELECT score FROM Pan WHERE panId='test'").getFirstResult();
				
				System.out.println(score_value);
				
				
				if(test.equals(panNo) && score_value>=5) {
		        	  
		        	  return "Eligible";
		          }
		          
		          else if(test.equals(panNo) && score_value<5){
		        	  
		        	  return "NotEligible";
		          }
		         else if(iterator.hasNext()==false){
		        	  
		        	  return "Invalid";
		          }
		          
		          else {
		        	  
		        	  continue;
		          }
				
			}
			tx.commit();
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			//tx.rollback();
		}
		
		finally {
			
			factory.close();
		}
		return null;
	}

}

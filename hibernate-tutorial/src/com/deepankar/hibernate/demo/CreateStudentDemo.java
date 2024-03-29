package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Student;


public class CreateStudentDemo {

	public static void main(String[] args) 
	{
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try
		{

			
			//create a student object
			System.out.println("Creating a new student object");
			Student tempStudent =  new Student("Paul", "wall", "paul@mail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			session.save(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
	}

}

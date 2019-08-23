package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) 
	{

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create a session
		Session session = factory.getCurrentSession();
		
		try
		{
			//create 4 student objects
			Student tempStudent =  new Student("Paul", "wall", "paul@mail.com");
			System.out.println("Creating a new student object");
			Student tempStudent1 =  new Student("John", "Doe", "paul@mail.com");
			Student tempStudent2 =  new Student("Daffy", "Duck", "deepankar.pathak@mail.com");
			Student tempStudent3 =  new Student("Mary", "Jane", "MJ@mail.com");

			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the students");
			session.save(tempStudent);
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
		}
		finally {
			factory.close();
		}
	}
}

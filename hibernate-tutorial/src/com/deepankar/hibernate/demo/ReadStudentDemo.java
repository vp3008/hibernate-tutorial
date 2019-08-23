package com.deepankar.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Student;


public class ReadStudentDemo {

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
			Student tempStudent =  new Student("Donald", "Trump", "potus@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//fidn out the student's id:: primary key
			System.out.println("Saved Student. Generated id " + tempStudent.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id:: primary key
			System.out.println("Getting student with id " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			//printing student details
			System.out.println("Get Complete");
			System.out.println(myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
	}

}

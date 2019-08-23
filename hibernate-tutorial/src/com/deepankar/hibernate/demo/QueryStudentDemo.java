 package com.deepankar.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.deepankar.hibernate.demo.entity.Student;


public class QueryStudentDemo {

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

			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
				
			//display the students
			System.out.println("\n\n\nStudents");
			displayStudents(theStudents);
			
			// query students: lastName='wall'
			System.out.println("\n\n\nStudents with last name = 'wall'");
			theStudents = session.createQuery("from Student s where s.lastName='wall'").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastname stars with 'D'
			System.out.println("\n\n\nStudents with last name starting with 'D'");
			theStudents = session.createQuery("from Student s where s.lastName LIKE 'D%'") .getResultList();
			displayStudents(theStudents);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents)
		{
			System.out.println(tempStudent);
		}
	}

}

package edu.jackson.testing;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.jackson.domain.Author;
import edu.jackson.domain.Book;
import edu.jackson.hibernate.util.HibernateUtil;

public class BooksManager {
	public static void main(String[] args) {

		Session session = HibernateUtil.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			Book newBook = new Book();
			newBook.setTitle("Hibernate Made Easy");
			newBook.setDescription("Simplified Data Persistence with Hibernate and JPA");
			newBook.setPublishedDate(new Date());

			newBook.setAuthor(new Author("Cameron Wallace McKenzie",
					"Cameron@gmail.com"));

			Long bookId = (Long) session.save(newBook);

			Book book = (Book) session.get(Book.class, bookId);
			System.out.println("Book's Title: " + book.getTitle());
			System.out.println("Book's Description: " + book.getTitle());

			Author author = book.getAuthor();
			System.out.println("Author's Name: " + author.getName());
			System.out.println("Author's Email: " + author.getEmail());
			
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		session.close();
	}
}

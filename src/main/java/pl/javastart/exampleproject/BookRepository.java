package pl.javastart.exampleproject;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    public List<Book> findAll() {
        String jpql = "select b from Book b order by b.author desc";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        return query.getResultList();
    }

    public Book findByAuthor(String author) {
        String jpql = "select s from Book s where s.author like :author";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("author", author);
        return query.getSingleResult();

    }


    public List<Book> findByGenre(String genre) {
        String jpql = "select g from Book g where g.genre=:genre";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Transactional
    public void deleteByTitle(String title) {
        String jpql = "delete from Book d where d.title=:title";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("title", title);
        query.executeUpdate();
    }

    @Transactional
    public void editBook(Book book) {
        Book existingBook = entityManager.find(Book.class, book.getId());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        existingBook.setCover(book.getCover());
        entityManager.persist(existingBook);
    }
}


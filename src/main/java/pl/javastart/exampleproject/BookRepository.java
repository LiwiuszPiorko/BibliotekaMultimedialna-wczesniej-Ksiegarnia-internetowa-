package pl.javastart.exampleproject;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findByGenre(String genre) {
        String jpql = "select g from Book g where g.genre=:genre";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    public List<Book> deleteByTitle(String title) {
        String jpql = "delete from Book t where t.title=:title";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
    public List<Book> editList() {
        String jpql = "update e from Book e";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        return query.getResultList();
    }
}


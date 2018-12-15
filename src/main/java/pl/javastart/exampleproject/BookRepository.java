package pl.javastart.exampleproject;

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

    public Book findByGenre(String genre) {
        return entityManager.find(Book.class, genre);
    }

}

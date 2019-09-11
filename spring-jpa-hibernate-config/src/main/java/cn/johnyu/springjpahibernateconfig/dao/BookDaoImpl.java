package cn.johnyu.springjpahibernateconfig.dao;

import cn.johnyu.springjpahibernateconfig.pojo.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional  public void addBook(Book book) {
        entityManager.persist(book);
    }
}

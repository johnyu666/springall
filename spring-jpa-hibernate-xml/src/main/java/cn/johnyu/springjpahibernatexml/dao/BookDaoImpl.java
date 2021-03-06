package cn.johnyu.springjpahibernatexml.dao;

import cn.johnyu.springjpahibernatexml.pojo.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;


public class BookDaoImpl implements BookDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional  public void addBook(Book book) {
        System.out.println(entityManager.getClass().getName());
        entityManager.persist(book);
    }
}

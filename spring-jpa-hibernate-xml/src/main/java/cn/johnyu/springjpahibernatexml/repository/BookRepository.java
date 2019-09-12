package cn.johnyu.springjpahibernatexml.repository;

import cn.johnyu.springjpahibernatexml.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

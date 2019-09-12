package cn.johnyu.springjpahibernateconfig.repository;

import cn.johnyu.springjpahibernateconfig.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

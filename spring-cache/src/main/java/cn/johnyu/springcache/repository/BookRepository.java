package cn.johnyu.springcache.repository;

import cn.johnyu.springcache.pojo.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Cacheable("bookCache")//是使用以bookCache做为名字的缓存
public interface BookRepository extends JpaRepository<Book, Integer> {
//    此处必须明确指定需要缓存的方法
    //此时会直接使用id做为key做为缓存标识
    @Override
    Optional<Book> findById(Integer id);
}

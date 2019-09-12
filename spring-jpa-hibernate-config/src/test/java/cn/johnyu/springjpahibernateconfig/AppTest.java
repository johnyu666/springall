package cn.johnyu.springjpahibernateconfig;

import cn.johnyu.springjpahibernateconfig.dao.BookDao;
import cn.johnyu.springjpahibernateconfig.pojo.Book;
import cn.johnyu.springjpahibernateconfig.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConf.class})
public class AppTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    AbstractJpaVendorAdapter vendorAdapter;
    @Autowired
    AbstractEntityManagerFactoryBean managerFactoryBean;
    @Autowired
    BookDao bookDao;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testDataSource(){
        System.out.println(dataSource);
    }
    @Test
    public void testVendorAdapter(){
        System.out.println(vendorAdapter);
    }

    @Test
    public void testManagerFactoryBean(){
        System.out.println(managerFactoryBean);
    }

    @Test
    public void testBookDao(){
        Book book=new Book();
        book.setBookName("JavaScript权威指南第6版！");
        book.setPrice(139.00);
        bookDao.addBook(book);
    }

    @Test
    public void testRepository(){
        Book book=new Book();
        book.setBookName("JavaScript权威指南第6版.");
        book.setPrice(139.00);
        Book bk=bookRepository.save(book);
        System.out.println(bk.getId());
    }
}

package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();
    Book book = new Book(21,"测试book", "111", new BigDecimal(123), 5, 5, null);
    @Test
    void addBook() {

        bookDao.addBook(new Book(null, "测试bookdao", "111", new BigDecimal(123), 5, 5, null));

    }

    @Test
    void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    void updateBook() {
        bookDao.updateBook(book);
    }

    @Test
    void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }
    @Test
    void queryPageTotalCount() {
        System.out.println(bookDao.queryPageTotalCount());
    }

    @Test
    void queryForPageItems() {
        bookDao.queryForPageItems(11, 5).forEach(System.out::println);
    }

}
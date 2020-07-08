package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    BookService bookService = new BookServiceImpl();
    Book book = new Book(21,"测试book21", "111", new BigDecimal(123), 5, 5, null);
    @Test
    void addBook() {
        bookService.addBook(new Book(null,"测试book22", "111", new BigDecimal(123), 5, 5, null));
    }

    @Test
    void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    void updateBook() {
        bookService.updateBook(book);
    }

    @Test
    void queryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    @Test
    void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    void page(){
        System.out.println(bookService.page(1, 4));
    }
}
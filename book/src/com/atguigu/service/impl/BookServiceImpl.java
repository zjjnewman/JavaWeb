package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);

        // 总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        // 总页数
        Integer pageTotal = pageTotalCount / pageSize + (pageTotalCount % pageSize == 0 ? 0 : 1);
        page.setPageTotal(pageTotal);

        // 页码
        page.setPageNo(pageNo);

        // 当前页开始数据的索引
        Integer begin = (page.getPageNo() - 1) * page.getPageSize();
        // 当前页list
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize, Integer min, Integer max) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);

        // 总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);

        // 总页数
        Integer pageTotal = pageTotalCount / pageSize + (pageTotalCount % pageSize == 0 ? 0 : 1);
        page.setPageTotal(pageTotal);

        // 页码
        page.setPageNo(pageNo);

        // 当前页开始数据的索引
        Integer begin = (page.getPageNo() - 1) * page.getPageSize();
        // 当前页list
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(items);

        return page;
    }
}

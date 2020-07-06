package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @author jin
 */
public interface BookDao {

    /**
     * 添加
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 修改
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 查询
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询
     * @return
     */
    public List<Book> queryBooks();

}

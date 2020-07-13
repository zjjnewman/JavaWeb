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

    /**
     * 查询总记录数
     * @return
     */
    Integer queryPageTotalCount();

    /**
     * 查询 从begin 到begin+pageSize的记录，返回book对象的list
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(Integer begin, Integer pageSize);

    /**
     * 查询最大 最小价格区间的图书总记录
     * @param min
     * @param max
     * @return
     */
    Integer queryPageTotalCountByPrice(Integer min, Integer max);

    /**
     * 查询价格区间的记录，返回book对象的list
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max);
}

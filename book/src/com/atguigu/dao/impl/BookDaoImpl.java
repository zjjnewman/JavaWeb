package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 添加一本图书
     *
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        String sql1 = "insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`)\n" +
                "values(null , 'javaScript高级编程' , '国哥' , 69.15 , 210 , 810 , 'static/img/default.jpg')";
        String sql = "insert into t_book(`name`, `author`, `price`, `sales`, `stock`, `img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    /**
     * 删除某个id的书
     *
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";

        return update(sql, id);
    }

    /**
     * 修改某个id的图书
     *
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?, `author` = ?, `price` = ?, `sales` = ?, `stock` = ?, `img_path` = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    /**
     * 查询某个id的书
     *
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` as imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    /**
     * 查询所有的书，返回book对象list
     *
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    /**
     * 查询总共多少页
     * @return
     */
    @Override
    public Integer queryPageTotalCount() {
        String sql = "select count(*) from t_book";
        //-----------这里老师用了Number类型。。。弹幕：数据库返回的是long类型，为了避免溢出。。。(Object不能强转Integer)这条错的----------
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    /**
     * 每页记录，返回book对象的列表
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryForPageItems(Integer begin, Integer pageSize) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book limit ?, ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    /**
     * 查询最大 最小价格区间的图书总记录
     *
     * @param min
     * @param max
     * @return
     */
    @Override
    public Integer queryPageTotalCountByPrice(Integer min, Integer max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    /**
     * 查询价格区间的记录，返回book对象的list
     *
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book " +
                "where price between ? and ? order by price limit ?, ?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }


}

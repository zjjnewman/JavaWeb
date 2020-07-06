package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 添加
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
     * 删除
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
     * 修改
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
     * 查询
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
     * 查询
     *
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book";
        return queryForList(Book.class, sql);
    }
}

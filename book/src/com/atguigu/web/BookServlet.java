package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取请求参数 封装成book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用服务，添加图书
        bookService.addBook(book);

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0) + 1;
        // 转发请求到图书管理列表页面
        // 注意一个bug---表单重复提交
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);

        /**
         * 以下是当客户端 cookie 被禁用时，对重定向url的处理
         * 可通过下面的方式，把sessionID拼接在url后面
         */
        String url = resp.encodeRedirectURL(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

        System.out.println("拼接sessionId后的url：" + url);

        resp.sendRedirect(url);

        //resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        bookService.deleteBookById(i);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数，并封装book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        // 修改book
        bookService.updateBook(book);

        // 重定向到图书列表，刷新显示
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.通过bookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        // 2.把全部图书保存到request的域中
        req.setAttribute("books", books);

        // 3.请求转发到/pages/manager/book_manager.jsp页面 《对这个pages有疑问》
        //--------++++++重点 第一个"/"表示到工程名，也就是 映射到代码的前端web目录
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求的图书id
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);

        // 2.低啊用queryBookById查询图书
        Book book = bookService.queryBookById(i);

        // 3.保存图书到request域中
        req.setAttribute("book", book);

        // 4.请求转发到 pages/manager/book_edit.jsp页面
        // 第一个 "/"表示 host:port/project/这里跟路径
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求参数pageNo pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2.调用page获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        // 3.保存page对象到request域
        req.setAttribute("page", page);
        // 4.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}

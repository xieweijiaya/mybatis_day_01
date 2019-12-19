package com.zking.ssm.controller;

import com.github.pagehelper.Page;
import com.zking.ssm.mapper.BookMapper;
import com.zking.ssm.model.Book;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("WEB-INF/jsp/index.jsp");
        return "index";
    }

    @RequestMapping(value = "/hello2")
    public String hello2(@RequestParam String userName, Book book, @RequestParam Map map) {
        System.out.println("userName=" + userName);
        System.out.println("book=" + book);
        System.out.println("map" + map);
        return "index";
    }

    @RequestMapping(value = "/hello3")
    public ModelAndView hello3(@RequestParam String userName, Book book, @RequestParam Map map) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @RequestMapping(value = "/hello4")
    public String hello4(@RequestParam String userName, Book book, @RequestParam Map map, Model model) {
        model.addAttribute("book", book);
        System.out.println("model");
//        return "redirect:toIndex";/重定向2次请求调用toIndex方法（其实就是toIndex使用了逻辑视图名）
        return "forward:toIndex";//转发1次请求调用toIndex方法（其实就是toIndex使用了逻辑视图名）
//        return "index";//返回逻辑视图名
    }

    @RequestMapping(value = "/toIndex")
    public String toIndex() {
        System.out.println("toIndex.....");
        return "index";
    }

    //访问数据库
    @RequestMapping(value = "/listbook")
    public String listbook(Model model, HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.initPageBean(request, pageBean);
        List<Book> books = this.bookService.listBookByPager(pageBean);
        model.addAttribute("listbook", books);
        model.addAttribute("pageBean", pageBean);
        return "book/listbook";
    }

    @RequestMapping(value = "/addbook")
    public String addbook(Book book) {

        return "book/addbook";
    }

    @RequestMapping(value = "/endaddbook")
    public String endaddbook(@Validated(Book.ValidateGroups.Add.class) @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/addbook";
        } else {
            int insert = this.bookService.insert(book);
            return "redirect:listbook";
        }
    }

    @RequestMapping(value = "/delbook")
    public String delbook(@RequestParam Integer bookId) {
        int i = this.bookService.deleteByPrimaryKey(bookId);
        return "redirect:listbook";
    }

    @RequestMapping(value = "/editbook")
    public String editbook(@Validated(value = {Book.ValidateGroups.Edit.class}) @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:listbook";
        } else {
            this.bookService.updateByPrimaryKey(book);
            return "redirect:listbook";
        }

    }

    @RequestMapping(value = "/changeLocale")
    public String changeLocale(HttpSession session, String locale) {
        Locale locale1 = new Locale(locale);
        if (locale1.getLanguage().equals("zh")) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.CHINA);
        } else if (locale1.getLanguage().equals("us")) {
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.US);
        }
        return "redirect:listbook";
    }

    @RequestMapping(value = "/toUpload")
    public String toUpload() {
        return "book/addupload";
    }

    @RequestMapping(value = "/listJsonBook")
//    @RequiresRoles(value = "管理员")
    @RequiresPermissions(value = "bookmanager:book:add")
    public @ResponseBody
    List<Book> listJsonBook(HttpServletRequest request) {
        PageBean pageBean = new PageBean();
        pageBean.initPageBean(request, pageBean);
        List<Book> books = this.bookService.listBookByPager(pageBean);
        for (Book book : books) {
            book.setCreateDate(new Date());
        }
        return books;
    }
}

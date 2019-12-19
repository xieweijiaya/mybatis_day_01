package com.zking.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zking.ssm.model.Book;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class IBookServiceImplTest {
    Book book;
    @Autowired
    IBookService bookService;

    @Before
    public void setUp() throws Exception {
        book = new Book();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listBookByPager() {
        PageBean pageBean = new PageBean();
        List<Book> books = this.bookService.listBookByPager(pageBean);
        for (Book book1 : books) {
            System.out.println(book1);
        }
        System.out.println(pageBean.getTotal());
    }

    @Test
    public void selectByPrimaryKey() {
        Book books = this.bookService.selectByPrimaryKey(6);
        System.out.println(books);
        System.out.println("************************************");
        Book books2 = this.bookService.selectByPrimaryKey(6);
        System.out.println(books2);
    }
}

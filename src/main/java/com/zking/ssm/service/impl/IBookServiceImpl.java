package com.zking.ssm.service.impl;

import com.zking.ssm.mapper.BookMapper;
import com.zking.ssm.mapper.MyFileMapper;
import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.service.IBookService;
import com.zking.ssm.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IBookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private MyFileMapper myFileMapper;

    @Override
    public int deleteByPrimaryKey(Integer bookId) {
        return this.bookMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public int insert(Book record) {
        return this.bookMapper.insert(record);
    }

    @Override
    public int insertSelective(Book record) {
        return 0;
    }

    @Override
    public Book selectByPrimaryKey(Integer bookId) {
        return this.bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return this.bookMapper.updateByPrimaryKey(record);
    }

    //分页查询不带条件
    @Override
    public List<Book> listBookByPager(PageBean pageBean) {
        return this.bookMapper.listBookByPager(pageBean);
    }

    @Transactional
    @Override
    public void updImg(Book book, MyFile myFile) {
        myFileMapper.insert(myFile);
        bookMapper.updImg(book);
    }
}

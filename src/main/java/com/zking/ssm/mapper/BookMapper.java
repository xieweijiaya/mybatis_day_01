package com.zking.ssm.mapper;

import com.zking.ssm.model.Book;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.util.PageBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> listBookByPager(PageBean pageBean);

    void updImg(Book book);
}
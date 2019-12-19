package com.zking.ssm.service.impl;

import com.zking.ssm.mapper.BookMapper;
import com.zking.ssm.mapper.MyFileMapper;
import com.zking.ssm.model.MyFile;
import com.zking.ssm.service.IMyFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyFileServiceImpl implements IMyFileService {

    @Autowired
    private MyFileMapper myFileMapper;

    @Override
    public int insert(MyFile record) {
        return myFileMapper.insert(record);
    }

    @Override
    public MyFile selectByPrimaryKey(Long fileId) {
        return myFileMapper.selectByPrimaryKey(fileId);
    }
}

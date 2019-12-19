package com.zking.ssm.service;

import com.zking.ssm.model.MyFile;
import org.springframework.stereotype.Repository;

public interface IMyFileService {
    int insert(MyFile record);

    MyFile selectByPrimaryKey(Long fileId);
}
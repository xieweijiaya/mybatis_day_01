package com.zking.ssm.mapper;

import com.zking.ssm.model.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer perid);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer perid);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}
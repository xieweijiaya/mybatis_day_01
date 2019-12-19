package com.zking.ssm.mapper;

import com.zking.ssm.model.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}
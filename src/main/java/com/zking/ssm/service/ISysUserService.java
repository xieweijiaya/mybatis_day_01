package com.zking.ssm.service;

import com.zking.ssm.model.SysUser;

import java.util.Set;

public interface ISysUserService {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser sysUserByprimaryName(String username);


    Set<String> sysRoleByName(String username);

    Set<String>sysPermissionsByName(String username);

}
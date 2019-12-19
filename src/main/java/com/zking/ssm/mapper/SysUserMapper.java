package com.zking.ssm.mapper;

import com.zking.ssm.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser sysUserByprimaryName(String username);

    Set<String>sysRoleByName(String username);

    Set<String>sysPermissionsByName(String username);
}
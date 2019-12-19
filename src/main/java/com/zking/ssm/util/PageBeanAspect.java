package com.zking.ssm.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Aspect  //启用aop功能
public class PageBeanAspect {
    @Around(value = "execution(* *..*Service.*Pager(..))")
    public  Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        PageBean pageBean = null;
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if(arg instanceof PageBean){
                pageBean = (PageBean)arg;
                break;
            }
        }
        if(null!=pageBean && pageBean.isPagination()){
            PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
        }
        Object result = proceedingJoinPoint.proceed(args);
        if(null!=pageBean && pageBean.isPagination() && null!=result && result instanceof List){
            List alists = (List)result;
            PageInfo pageInfo = new PageInfo(alists);
            pageBean.setTotal(pageInfo.getTotal()+"");
            pageBean.setRows(pageInfo.getSize());
            pageBean.setPage(pageInfo.getPageNum());
        }
        return result;
    }

}

package com.zking.ssm.mapper;

import com.zking.ssm.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    Customer selectByPrimaryKey(Integer customerId);
}
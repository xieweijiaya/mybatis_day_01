package com.zking.ssm.service.impl;
import com.zking.ssm.model.Customer;
import com.zking.ssm.model.Order;
import com.zking.ssm.service.ICustomerService;
import com.zking.ssm.service.IOrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class CustomerAndOrderServiceImplTest {
    Customer customer;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IOrderService orderService;
    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() {
        Customer c = this.customerService.selectByPrimaryKey(1);
        System.out.println(c);

    }
    @Test
    public void selectByPrimaryKeyOrder() {
        Order o = this.orderService.selectByPrimaryKey(6);
        System.out.println(o);
    }

}

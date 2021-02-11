package com.number.validation.service;

import com.number.validation.dao.CustomerDao;
import com.number.validation.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private final String MOROCCO = "Morocco";

    private List<Customer> customerList = new ArrayList<>();

    @InjectMocks
    private CustomerServiceImpl customerService;

    @MockBean
    private CustomerDao customerDao;


    @BeforeEach
    public void setUp() {
        customerList.add(new Customer(1, "Yosaf Karrouch", "(212) 698054317")); // Valid Number
        customerList.add(new Customer(2, "Jokkene Richard", "(256) 7734127498")); // Not Valid Number
        customerList.add(new Customer(3, "Florencio Samuel", "(258) 847602609")); // Valid Number
    }

    @Test
    void getCustomersWithoutFilters() {
        Mockito.when(customerDao.getAll()).thenReturn(customerList);

        String country = null;
        Boolean valid = null;

        List<Customer> actual = customerService.getCustomers(country, valid);

        assertEquals(actual, customerList);
    }

    @Test
    void getCustomersWithValidNumberFilter() {
        Mockito.when(customerDao.getAll()).thenReturn(customerList);

        String country = null;
        Boolean valid = true;

        List<Customer> actual = customerService.getCustomers(country, valid);

        assertEquals(actual.size(), 2);
        assertEquals(actual.get(0).getId(), 1);
        assertEquals(actual.get(1).getId(), 3);
    }

    @Test
    void getCustomersWithCountryAndValidNumberFilter() {
        Mockito.when(customerDao.getAll()).thenReturn(customerList);

        String country = MOROCCO;
        Boolean valid = true;

        List<Customer> actual = customerService.getCustomers(country, valid);

        assertEquals(actual.size(), 1);
        assertEquals(actual.get(0).getId(), 1);
    }

    @Test
    void getCustomersWithCountryAndInvalidNumberFilter() {
        Mockito.when(customerDao.getAll()).thenReturn(customerList);

        String country = MOROCCO;
        Boolean valid = false;

        List<Customer> actual = customerService.getCustomers(country, valid);

        assertEquals(actual.size(), 0);
    }

}
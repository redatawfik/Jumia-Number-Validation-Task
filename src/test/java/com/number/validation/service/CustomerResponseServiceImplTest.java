package com.number.validation.service;

import com.number.validation.model.Customer;
import com.number.validation.model.CustomerResponse;
import com.number.validation.repository.CustomerRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class CustomerResponseServiceImplTest {

    private final String MOROCCO = "Morocco";

    private List<Customer> customerList = new ArrayList<>();

    @InjectMocks
    private CustomerServiceImpl customerService;

    @MockBean
    private CustomerRepository customerRepository;


    @BeforeEach
    public void setUp() {
        customerList.add(new Customer(1, "Yosaf Karrouch", "(212) 698054317")); // Valid Number
        customerList.add(new Customer(2, "Jokkene Richard", "(256) 7734127498")); // Not Valid Number
        customerList.add(new Customer(3, "Florencio Samuel", "(258) 847602609")); // Valid Number
    }

    @Test
    void getCustomersWithoutFilters() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

        String country = null;
        Boolean valid = null;

        List<CustomerResponse> actual = customerService.getCustomers(country, valid);

        assertEquals(customerList.size(), actual.size());
        assertEquals(actual.get(0).getId(), customerList.get(0).getId());
        assertEquals(actual.get(1).getId(), customerList.get(1).getId());
        assertEquals(actual.get(2).getId(), customerList.get(2).getId());
    }

    @Test
    void getCustomersWithValidNumberFilter() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

        String country = null;
        Boolean valid = true;

        List<CustomerResponse> actual = customerService.getCustomers(country, valid);

        assertEquals(actual.size(), 2);

        assertTrue(actual.get(0).isValidNumber());
        assertTrue(actual.get(1).isValidNumber());
    }

    @Test
    void getCustomersWithCountryAndValidNumberFilter() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

        Boolean valid = true;

        List<CustomerResponse> actual = customerService.getCustomers(MOROCCO, valid);

        assertEquals(actual.size(), 1);
        assertEquals(actual.get(0).getCountry().toString(), MOROCCO);
        assertTrue(actual.get(0).isValidNumber());
    }

    @Test
    void getCustomersWithCountryNotExist() {
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);

        String country = "Egypt";
        Boolean valid = null;

        List<CustomerResponse> actual = customerService.getCustomers(country, valid);

        assertEquals(actual.size(), 0);
    }

}
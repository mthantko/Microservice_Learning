package com.mtk.accounts.service.Impl;

import com.mtk.accounts.service.IAccountsService;
import com.mtk.accounts.constants.AccountsConstants;
import com.mtk.accounts.dto.AccountDto;
import com.mtk.accounts.dto.CustomersDto;
import com.mtk.accounts.entity.Accounts;
import com.mtk.accounts.entity.Customer;
import com.mtk.accounts.exception.CustomerAlreadyExistsException;
import com.mtk.accounts.exception.ResourceNotFoundException;
import com.mtk.accounts.mapper.AccountsMapper;
import com.mtk.accounts.mapper.CustomerMapper;
import com.mtk.accounts.repository.AccountsRepository;
import com.mtk.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomersDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy("anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        //newAccount.setCreatedAt(LocalDateTime.now());
        //newAccount.setCreatedBy("anonymous");
        return newAccount;
    }

    @Override
    public CustomersDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomersDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomersDto());
        customerDto.setAccountDto(AccountsMapper.mapToAccountDto(accounts, new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomersDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null) {
            Accounts accounts = accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountDto, accounts);
            accounts =  accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}

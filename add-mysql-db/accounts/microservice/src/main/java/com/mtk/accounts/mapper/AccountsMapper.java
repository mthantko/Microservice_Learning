package com.mtk.accounts.mapper;

import com.mtk.accounts.dto.AccountDto;
import com.mtk.accounts.entity.Accounts;

public class AccountsMapper {

    // Entity → DTO
    public static AccountDto mapToAccountDto(Accounts accounts, AccountDto accountDto) {
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    // DTO → Entity
    public static Accounts mapToAccounts(AccountDto accountDto, Accounts accounts) {
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}

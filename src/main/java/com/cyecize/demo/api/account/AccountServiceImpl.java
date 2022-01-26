package com.cyecize.demo.api.account;

import com.cyecize.demo.config.db.DataSourceType;
import com.cyecize.demo.config.routing.WithDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }
}

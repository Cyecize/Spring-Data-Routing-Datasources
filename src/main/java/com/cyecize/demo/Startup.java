package com.cyecize.demo;

import com.cyecize.demo.api.account.AccountRepository;
import com.cyecize.demo.api.account.AccountService;
import com.cyecize.demo.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Startup {

    private final UserService userService;

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void startUp() {
        System.out.println("App Started!");

//        System.out.println(this.userService.findAllDS1());
//        System.out.println(this.userService.findAllDS2());
//        System.out.println(this.userService.findAllDS3());

        // System.out.println(this.userService.findAllNestedTransactions());

        System.out.println(this.accountService.findAll());
        System.out.println(this.accountRepository.findAll());

        System.exit(0);
    }
}

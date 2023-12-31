package dev.bernouy.cms.feature.account.service;

import dev.bernouy.cms.common.BasicException;
import dev.bernouy.cms.common.RegexComponent;
import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.AccountExceptionMessages;
import dev.bernouy.cms.feature.account.AccountRepository;
import dev.bernouy.cms.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountBusinessLogicService {

    private AccountRepository accountRepository;
    private RegexComponent regexComponent;
    private PasswordEncoder   passwordEncoder;

    @Autowired
    public AccountBusinessLogicService(AccountRepository accountRepository, RegexComponent regexVerification, PasswordEncoder passwordEncoder){
        this.accountRepository = accountRepository;
        this.regexComponent = regexVerification;
        this.passwordEncoder   = passwordEncoder;
    }

    public Account registerAccount(String email, String password ){
        Account account = new Account();
        regexComponent.isEmail(email).isPasswordValid(password);
        if ( accountRepository.existsAccountByEmail (email) ) throw new BasicException(AccountExceptionMessages.EMAIL_ALREADY_EXIST);
        account.setPassword(passwordEncoder.encode(password));
        account.setEmail(email);
        accountRepository.save(account);
        return account;
    }

    public String con ( String email, String password ){
        Account account = accountRepository.getAccountByEmail(email);
        if ( account == null || !passwordEncoder.matches(password, account.getPassword()))
            throw new BasicException(AccountExceptionMessages.ACCOUNT_OR_PASSWORD_DOES_NOT_EXIST);
        return JwtUtil.generateJwt(account.getId());
    }

    public Account conByJwt( String jwt ){
        String subject = JwtUtil.getSubjectJwt(jwt);
        if ( subject == null ) throw new BasicException(AccountExceptionMessages.JWT_ERROR);
        return accountRepository.findById(subject).orElseThrow();
    }
}

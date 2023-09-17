package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.feature.account.dto.request.RegisterConDTO;
import dev.bernouy.cms.feature.account.dto.response.ResponseConDTO;
import dev.bernouy.cms.security.Public;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private AccountService accountService;
    private HttpServletResponse response;
    private HttpServletRequest  request;

    @Autowired
    public AccountController( AccountService accountService, HttpServletResponse response, HttpServletRequest request){
        this.accountService = accountService;
        this.response = response;
        this.request = request;
    }

    @Public
    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody RegisterConDTO dto ){
        accountService.registerAccount(dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @Public
    @PostMapping("/login")
    public ResponseEntity<String> con( @RequestBody RegisterConDTO dto ){
        String token = accountService.con(dto.getEmail(), dto.getPassword());
        return new ResponseConDTO(response, token);
    }

}

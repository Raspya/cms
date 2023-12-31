package dev.bernouy.cms.feature.account;

import dev.bernouy.cms.feature.account.dto.request.ReqRegisterConDTO;
import dev.bernouy.cms.feature.account.dto.response.ResponseConDTO;
import dev.bernouy.cms.feature.account.service.AccountBusinessLogicService;
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

    private AccountBusinessLogicService accountService;
    private HttpServletResponse response;
    private HttpServletRequest  request;

    @Autowired
    public AccountController(AccountBusinessLogicService accountService, HttpServletResponse response, HttpServletRequest request){
        this.accountService = accountService;
        this.response = response;
        this.request = request;
    }

    @Public
    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody ReqRegisterConDTO dto ){
        Account account = accountService.registerAccount(dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>(account.getId(), HttpStatus.CREATED);
    }

    @Public
    @PostMapping("/login")
    public ResponseEntity<String> con( @RequestBody ReqRegisterConDTO dto ){
        String token = accountService.con(dto.getEmail(), dto.getPassword());
        return new ResponseConDTO(response, token);
    }

    @GetMapping("/isValidToken")
    public ResponseEntity<String> isValidToken(){
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}

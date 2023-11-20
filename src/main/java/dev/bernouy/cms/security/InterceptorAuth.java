package dev.bernouy.cms.security;

import dev.bernouy.cms.feature.account.Account;
import dev.bernouy.cms.feature.account.service.AccountBusinessLogicService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class InterceptorAuth implements HandlerInterceptor {

    private final AccountBusinessLogicService accountService;

    @Autowired
    public InterceptorAuth( AccountBusinessLogicService accountService ){
        this.accountService = accountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String cookieValue = null;
        Cookie[] cookies;

        if (handler instanceof HandlerMethod handlerMethod) {

            if (handlerMethod.hasMethodAnnotation(Public.class)) return true;

            cookies = request.getCookies();
            if ( cookies == null ) return abortRequest(response);
            for (Cookie cookie : cookies) {
                if ("SecureToken".equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
            if ( cookieValue == null ) return abortRequest(response);

            Account account = accountService.conByJwt(cookieValue);
            if ( account == null ) return abortRequest(response);

            request.setAttribute("account", account);
            return true;
        }
        return false;
    }

    private boolean abortRequest( HttpServletResponse response ){
        try {
            response.sendError(403);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}

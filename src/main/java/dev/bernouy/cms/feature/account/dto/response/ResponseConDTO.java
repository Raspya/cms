package dev.bernouy.cms.feature.account.dto.response;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseConDTO extends ResponseEntity<String> {

    public ResponseConDTO( HttpServletResponse response, String token ) {
        super("", HttpStatusCode.valueOf(200));
        Cookie cookie = new Cookie("SecureToken", token);
        cookie.setMaxAge(360000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

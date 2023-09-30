package dev.bernouy.cms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ReqTDB {

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();
    private Object dto;
    private MethodEnum method = MethodEnum.POST;

    public ReqTDB withDto( Object dto ){
        this.dto = dto;
        return this;
    }

    public ReqTDB withAuth( String authValue ){
        this.headers.add("Cookie", "SecureToken" + "=" + authValue);
        return this;
    }

    public ReqTDB withMethod( MethodEnum method ){
        this.method = method;
        return this;
    }

    public ResponseEntity<String> send(String url ){
        HttpEntity<Object> httpEntity = new HttpEntity<>(this.dto, this.headers);
        HttpMethod httpMethod = switch ( method ){
            case POST   -> HttpMethod.POST;
            case GET    -> HttpMethod.GET;
            case DELETE -> HttpMethod.DELETE;
        };
        this.headers = new HttpHeaders();
        this.method = MethodEnum.POST;
        this.dto = null;
        return restTemplate.exchange(
                "/api/v1/" + url,
                httpMethod,
                httpEntity,
                String.class
        );
    }
}

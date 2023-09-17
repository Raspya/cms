package dev.bernouy.cms.common;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexComponent {

    private Pattern emailPattern = Pattern.compile("^.+@.+\\..+$");
    private Pattern passwordPattern = Pattern.compile("^.{4,30}$");
    private Pattern domainPattern = Pattern.compile("^([a-z0-9]+\\.)+[a-z]{1,10}$");
    private Pattern namePattern = Pattern.compile("^[a-zA-Z -_]{5,40}$");

    public RegexComponent isEmail (String email ){
        Matcher matcher = emailPattern.matcher(email);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_EMAIL_FORMAT);
        return this;
    }

    public RegexComponent isPasswordValid (String password ){
        Matcher matcher = passwordPattern.matcher(password);
        if( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_PASSWORD_FORMAT);
        return this;
    }

    public RegexComponent isDomainValid (String domain ){
        Matcher matcher = passwordPattern.matcher(domain);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_DOMAIN_NAME_FORMAT);
        return this;
    }

    public RegexComponent isNameValid (String name ){
        Matcher matcher = namePattern.matcher(name);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_NAME_FORMAT);
        return this;
    }

}

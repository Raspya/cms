package dev.bernouy.cms.common;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegexComponent {

    private Pattern emailPattern = Pattern.compile("^.+@.+\\..+$");
    private Pattern passwordPattern = Pattern.compile("^.{4,30}$");
    private Pattern domainPattern = Pattern.compile("^(?!.*[-.]{2,}.*)[a-zA-Z0-9-.]+\\.[a-z]{1,8}$");
    private Pattern namePattern = Pattern.compile("^[a-zA-Z -_]{5,40}$");
    private Pattern keyPattern = Pattern.compile("^[a-z_]{3,40}$");
    private Pattern pathPattern = Pattern.compile("^[a-zA-Z0-9-_/]{3,40}$");

    public RegexComponent isEmail (String email ){
        Matcher matcher = emailPattern.matcher(email);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_EMAIL_FORMAT);
        return this;
    }

    public RegexComponent isPath (String path ){
        Matcher matcher = pathPattern.matcher(path);
        if ( !matcher.matches() ) throw new BasicException("Path Invalid");
        return this;
    }

    public RegexComponent isPasswordValid (String password ){
        Matcher matcher = passwordPattern.matcher(password);
        if( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_PASSWORD_FORMAT);
        return this;
    }

    public RegexComponent isDomainValid (String domain ){
        Matcher matcher = domainPattern.matcher(domain);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_DOMAIN_NAME_FORMAT);
        return this;
    }

    public RegexComponent isNameValid (String name ){
        Matcher matcher = namePattern.matcher(name);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_NAME_FORMAT);
        return this;
    }

    public RegexComponent isKeyValid (String key ){
        Matcher matcher = keyPattern.matcher(key);
        if ( !matcher.matches() ) throw new BasicException(RegexErrors.INVALID_KEY_FORMAT);
        return this;
    }

}

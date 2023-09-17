package dev.bernouy.cms.feature.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bernouy.cms.common.AbstractDocument;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Objects;

public class Account extends AbstractDocument {

    @Indexed(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}

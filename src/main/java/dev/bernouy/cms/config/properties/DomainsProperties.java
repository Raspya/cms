package dev.bernouy.cms.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="app")
public class DomainsProperties {
    private Map<String, String> domains = new HashMap<>();

    public String getDomain(String domain){
        return domains.get(domain);
    }

    public void setDomains(Map<String, String> domains) {
        this.domains = domains;
    }
}

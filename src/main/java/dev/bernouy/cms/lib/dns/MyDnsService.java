package dev.bernouy.cms.lib.dns;

import dev.bernouy.cms.config.properties.DomainsProperties;
import dev.bernouy.cms.config.properties.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.route53.Route53Client;
import software.amazon.awssdk.services.route53.model.*;

@Service
public class MyDnsService {

    private DomainsProperties domainsProperties;
    private ServerProperties serverProperties;

    private Route53Client route53Client;

    @Autowired
    public MyDnsService(DomainsProperties domainsProperties, ServerProperties serverProperties){
        this.domainsProperties = domainsProperties;
        this.serverProperties = serverProperties;
        route53Client = Route53Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.EU_WEST_3)
                .build();
    }

    public void createRegisterToAnotherDomain(String subDomain, String mainDomain, String anotherDomain){
        ChangeResourceRecordSetsRequest request = ChangeResourceRecordSetsRequest.builder()
            .hostedZoneId(getHostedZoneId(mainDomain))
            .changeBatch(ChangeBatch.builder()
                .changes(Change.builder()
                    .action("CREATE")
                    .resourceRecordSet(ResourceRecordSet.builder()
                        .name(subDomain + "." + mainDomain)
                        .type("CNAME")
                        .ttl(300L)
                        .resourceRecords(ResourceRecord.builder()
                                .value(anotherDomain)
                                .build())
                        .build())
                    .build())
                .build())
            .build();

        route53Client.changeResourceRecordSets(request);
    }

    public void createRegisterToIp(String subDomain, String mainDomain, String ip){
        ChangeResourceRecordSetsRequest req = ChangeResourceRecordSetsRequest.builder()
            .hostedZoneId(getHostedZoneId(mainDomain))
            .changeBatch(ChangeBatch.builder()
                .changes(Change.builder()
                    .action("CREATE")
                    .resourceRecordSet(ResourceRecordSet.builder()
                        .name(subDomain + "." + mainDomain)
                        .type("A")
                        .ttl(300L)
                        .resourceRecords(ResourceRecord.builder()
                                .value(ip)
                                .build())
                        .build())
                    .build())
                .build())
            .build();
        route53Client.changeResourceRecordSets(req);
    }

    public void deleteRegisterToIp(String subDomain, String mainDomain, String ip) {
        ChangeResourceRecordSetsRequest request = ChangeResourceRecordSetsRequest.builder()
            .hostedZoneId(getHostedZoneId(mainDomain))
            .changeBatch(ChangeBatch.builder()
                .changes(Change.builder()
                    .action("DELETE")
                    .resourceRecordSet(ResourceRecordSet.builder()
                        .name(subDomain + "." + mainDomain)
                        .type("A")
                        .ttl(86400L)
                        .resourceRecords(ResourceRecord.builder()
                                .value(ip)
                                .build())
                        .build())
                    .build())
                .build())
            .build();

        route53Client.changeResourceRecordSets(request);
    }

    public void deleteRegisterToAnotherDomain(String subDomain, String mainDomain, String anotherDomain) {
        ChangeResourceRecordSetsRequest request = ChangeResourceRecordSetsRequest.builder()
            .hostedZoneId(getHostedZoneId(mainDomain))
            .changeBatch(ChangeBatch.builder()
                .changes(Change.builder()
                    .action("DELETE")
                    .resourceRecordSet(ResourceRecordSet.builder()
                        .name(subDomain + "." + mainDomain)
                        .type("CNAME")
                        .ttl(300L)
                        .resourceRecords(ResourceRecord.builder()
                                .value(anotherDomain)
                                .build())
                        .build())
                    .build())
                .build())
            .build();

        route53Client.changeResourceRecordSets(request);
    }

    private String getHostedZoneId(String domain){
        String hostedZoneId = domainsProperties.getDomain(domain);
        if (hostedZoneId == null){
            throw new RuntimeException("Domain not found");
        }
        return hostedZoneId;
    }


}

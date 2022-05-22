package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import javax.naming.directory.DirContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    public LdapTemplate ldapTemplate() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://ldap.example.og:389");
        contextSource.setUserDn("cn=admin,dc=example,dc=org");
        contextSource.setPassword("admin");

        try {
            contextSource.afterPropertiesSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource);
        return template;
    }

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LdapContextSource ldapContextSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");
//        List<String> children = ldapTemplate.list("dc=example,dc=org");

        DirContext dirContext1 = ldapTemplate.getContextSource().getContext(
                "cn=marcuschiu,ou=Users,dc=example,dc=org",
                "password"
        );
        DirContext dirContext2 = ldapTemplate.getContextSource().getContext(
                "cn=admin,dc=example,dc=org",
        "admin");
    }
}

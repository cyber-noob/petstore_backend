package com.netrunner.petstore.service;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class OpenPolicyAgentAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private EntityManager entityManager;

    private String context = OpenPolicyAgentAuthorizationManager.class.getSimpleName() + "\t";

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        logger.info("details -> " + authentication.get().getDetails());
        logger.info("context -> " + context.getRequest().getHeader("x-session"));

        try{
            List result = entityManager.createNativeQuery("select * from user_session where id = ?;")
                    .setParameter(1, context.getRequest().getHeader("x-session"))
                    .getResultList();

            System.out.printf("res size -> " + result.size());
            if (result.size() == 1)
                return new AuthorizationDecision(true);

            return new AuthorizationDecision(false);
        }catch (Exception e) {
            e.printStackTrace();
            return new AuthorizationDecision(false);
        }
    }
}
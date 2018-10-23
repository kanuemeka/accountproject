package com.kanu.interviews.resource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by Chukwuemeka on 04/02/2017.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(AccountResource.class);
    }
}

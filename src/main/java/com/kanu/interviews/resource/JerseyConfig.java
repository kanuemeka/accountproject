package com.kanu.interviews.resource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Chukwuemeka on 04/02/2017.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        register(AccountResource.class);
    }
}

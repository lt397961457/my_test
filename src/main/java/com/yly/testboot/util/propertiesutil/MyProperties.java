package com.yly.testboot.util.propertiesutil;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.yly")
public class MyProperties {
    private String myname;
    private String myage;
    private String mylocation;
}

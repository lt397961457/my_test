package com.yly.testboot.util.propertiesutil;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyPropertise2 {
    @Value("${com.yly.myname}")
    private String myname;
    @Value("${com.yly.myage}")
    private String myage;
    @Value("${com.yly.mylocation}")
    private String mylocation;
}

package com.mycompany.config;

import com.mycompany.soundsystem.CDPlayer;
import com.mycompany.soundsystem.CompactDisc;
import com.mycompany.soundsystem.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//Removing @ComponentScan to learn explicit configuration
//@ComponentScan(basePackages = "com.mycompany.soundsystem")
public class CDPlayerConfig {

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

    /* Not preferred. It depends on other methods of this configuration class
    @Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }*/

    // Preferred. We can use beans declared in other configuration classes
    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }

}
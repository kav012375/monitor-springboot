package com.monitor.security;


import com.monitor.security.encryption.EncryptionService;
import com.monitor.security.encryption.impl.EncryptionImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
@Service
public class SecurityConfiguration {

    @Bean
    public EncryptionService encryptionService(){
        EncryptionService encryptionService = new EncryptionImpl();
        return encryptionService;
    }
}

package com.amos.shorturl;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

/**
 * 模块名称: filesystem
 * 模块描述: db config
 *
 * @author amos.wang
 * @date 2020/12/19 11:24
 */
public class JasyptMain {

    public static void main(String[] args) {
        StandardPBEStringEncryptor encrypt = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword("258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        encrypt.setConfig(config);
        String plainText = "root";
        String encryptedText = encrypt.encrypt(plainText);
        System.out.println(encryptedText);

        System.out.println(encrypt.decrypt(encryptedText));
    }

}

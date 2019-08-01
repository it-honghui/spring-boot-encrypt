package com.hcr.config.algorithm;


import com.hcr.utils.AesEncryptUtils;

/**
 * Aes加密算法实现
 */
public class AesEncryptAlgorithm implements EncryptAlgorithm {

    @Override
    public String encrypt(String content, String encryptKey) throws Exception {
        return AesEncryptUtils.aesEncrypt(content, encryptKey);
    }

    @Override
    public String decrypt(String encryptStr, String decryptKey) throws Exception {
        return AesEncryptUtils.aesDecrypt(encryptStr, decryptKey);
    }

}

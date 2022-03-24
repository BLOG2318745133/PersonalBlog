package com.kun98.encrypt.service;

/**
 * @author LIU JIANKUN
 * @create 2022-03-15 23:45
 */
public interface RsaService {

    /***
     * RSA解密
     *
     * @param encryptData
     * @return
     * @throws Exception
     */
    public String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception;

    /***
     * RSA解密
     *
     * @param encryptBytes
     * @return
     * @throws Exception
     */
    public String RSADecryptDataBytes(byte[] encryptBytes, String prvKey) throws Exception;

    /***
     * RSA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public String RSAEncryptDataPEM(String data, String pubKey) throws Exception;

    public String getRsaAlgorithm();
}

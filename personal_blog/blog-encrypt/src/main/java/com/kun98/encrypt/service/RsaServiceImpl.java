package com.kun98.encrypt.service;

import com.kun98.encrypt.rsa.Base64Utils;
import com.kun98.encrypt.rsa.RSA;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

/**
 * @author LIU JIANKUN
 * @create 2022-03-15 23:47
 */
@Service("RsaService")
public class RsaServiceImpl implements RsaService{


    @Override
    public String RSADecryptDataPEM(String encryptData, String prvKey) throws Exception {
//        byte[] encryptBytes = encryptData.getBytes();
        byte[] prvdata = RSA.decryptByPrivateKey(Base64Utils.decode(encryptData), prvKey);

        String outString = new String(prvdata, "UTF-8");
        return outString;
    }

    @Override
    public String RSADecryptDataBytes(byte[] encryptBytes, String prvKey) throws Exception {
        byte[] prvdata = RSA.decryptByPrivateKey(encryptBytes, prvKey);
        String outString = new String(prvdata, "utf-8");
        return outString;
    }

    @Override
    public String RSAEncryptDataPEM(String data, String pubKey) throws Exception {

        byte[] pubdata = RSA.encryptByPublicKey(data.getBytes("UTF-8"), pubKey);
        String outString = Base64Utils.encode(pubdata);

        return outString;
    }

    @Override
    public String getRsaAlgorithm() {
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyFactory.getAlgorithm();
    }
}

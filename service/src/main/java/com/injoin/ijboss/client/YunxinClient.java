package com.injoin.ijboss.client;

import com.alibaba.fastjson.JSON;
import com.injoin.ijboss.utils.Md5Util;
import com.injoin.ijboss.vo.YunxinAccRespVo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author figo
 */
@Repository
public class YunxinClient {
    private static Logger LOGGER = LoggerFactory.getLogger(YunxinClient.class);

    @Value(value = "${yunxin.createAccIdUrl}")
    private String createAccIdUrl;

    @Value(value = "${yunxin.appSecret}")
    private String appSecret;

    @Value(value = "${yunxin.appKey}")
    private String appKey;

    private static int accIdLimit = 32;

    /**
     * 创建云信id
     *
     * @param tid 应用的tid
     * @return YunxinAccRespVo
     */

    public YunxinAccRespVo createAccId(Long tid) {
        HttpPost post = null;
        long curTime = System.currentTimeMillis() / 1000;
        String accId = Md5Util.randomMD5(String.valueOf(tid));
        if (accId.length() > YunxinClient.accIdLimit) {
            accId = accId.substring(0, YunxinClient.accIdLimit);
        }
        YunxinAccRespVo yunxinAccVo = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();

            post = new HttpPost(createAccIdUrl);
            post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            post.setHeader("AppKey", appKey);
            Random r = new Random();
            String nonce = String.valueOf(r.nextInt(999999999)) + String.valueOf(r.nextInt(999999999));
            post.setHeader("Nonce", String.valueOf(nonce));
            post.setHeader("CurTime", String.valueOf(curTime));
            post.setHeader("CheckSum", getCheckSum(appSecret, String.valueOf(nonce), String.valueOf(curTime)));
            // 构建消息实体
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("accid", accId));
            post.setEntity(new UrlEncodedFormEntity(nvps));

            HttpResponse response = httpClient.execute(post);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                response.getEntity().getContent();
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), StandardCharsets.UTF_8));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                yunxinAccVo = JSON.parseObject(sb.toString(), YunxinAccRespVo.class);
            } else {
                yunxinAccVo = new YunxinAccRespVo();
                yunxinAccVo.setCode(statusCode);
            }
        } catch (Exception e) {
            LOGGER.error("error", e);
            yunxinAccVo = new YunxinAccRespVo();
            yunxinAccVo.setCode(500);
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return yunxinAccVo;
    }


    private String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     */

    private String getMD5(String requestBody) {
        return encode("md5", requestBody);
    }

    private String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

}

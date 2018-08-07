package com.okex.trande.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by zhangxin19 on 2016/2/24.
 */
public class HttpClientUtil {

//    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final int CONNTIMEOUT = 10000;
    public static final int READTIMEOUT = 10000;
    public static final String CHARSET = "UTF-8";

    private static HttpClient client = null;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * 发送一个 Post 请求，使用指定的字符集编码
     *
     * @param url
     * @param body        RequestBody
     * @param mimeType    例如 application/xml "application/x-www-form-urlencoded" a=1&b=2&c=3 application/json
     * @param CHARSET     编码
     * @param CONNTIMEOUT 建立链接超时时间（毫秒）
     * @param READTIMEOUT 响应超时时间（毫秒）
     * @return ResponseBody 使用指定的字符集编码
     * @throws ConnectTimeoutException 建立链接超时异常
     * @throws SocketTimeoutException  响应超时
     * @throws Exception
     */
    public static String post(String url, String body, String mimeType, String CHARSET, Integer CONNTIMEOUT, Integer READTIMEOUT) throws ConnectTimeoutException, SocketTimeoutException, Exception {
        System.out.println("发送HTTP(S) POST 请求：" +  url + " | " + mimeType + " | " + CHARSET + " | " + CONNTIMEOUT + " | " + READTIMEOUT);
        HttpClient client = null;
        HttpResponse res = null;
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, CHARSET));
                post.setEntity(entity);
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (CONNTIMEOUT != null) {
                customReqConf.setConnectTimeout(CONNTIMEOUT);
            }
            if (READTIMEOUT != null) {
                customReqConf.setSocketTimeout(READTIMEOUT);
            }
            post.setConfig(customReqConf.build());

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtil.client;
                res = client.execute(post);
            }
            if (res.getStatusLine().getStatusCode() == 200) {
                result = IOUtils.toString(res.getEntity().getContent(), CHARSET);
            } else {
                System.out.println("HTTP(S) POST 请求，状态异常：" + res.getStatusLine().getStatusCode() + " | " + url);
                result = null;
            }
        } finally {
            post.abort();
            if (null != res) {
                EntityUtils.consumeQuietly(res.getEntity());
            }
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 提交Form表单
     *
     * @param url
     * @param params
     * @param CONNTIMEOUT
     * @param READTIMEOUT
     * @return ResponseBody 使用指定的字符集编码
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     * @throws Exception
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer CONNTIMEOUT, Integer READTIMEOUT) throws ConnectTimeoutException, SocketTimeoutException, Exception {
        System.out.println("发送HTTP(S) POST 请求：" + url + " | " + CONNTIMEOUT + " | " + READTIMEOUT);
        HttpClient client = null;
        HttpResponse res = null;
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                post.setEntity(entity);
            }
            if (headers != null && !headers.isEmpty()) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (CONNTIMEOUT != null) {
                customReqConf.setConnectTimeout(CONNTIMEOUT);
            }
            if (READTIMEOUT != null) {
                customReqConf.setSocketTimeout(READTIMEOUT);
            }
            post.setConfig(customReqConf.build());

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(post);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtil.client;
                res = client.execute(post);
            }
            if (res.getStatusLine().getStatusCode() == 200) {
                result = IOUtils.toString(res.getEntity().getContent(), CHARSET);
            } else {
                System.out.println("HTTP(S) POST 请求，状态异常：" + res.getStatusLine().getStatusCode() + " | " + url);
                result = null;
            }
        } finally {
            post.abort();
            if (null != res) {
                EntityUtils.consumeQuietly(res.getEntity());
            }
            if (url.startsWith("https") && client != null
                    && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 发送一个 GET 请求
     *
     * @param url
     * @param CHARSET
     * @param CONNTIMEOUT 建立链接超时时间（毫秒）
     * @param READTIMEOUT 响应超时时间（毫秒）
     * @return
     * @throws ConnectTimeoutException 建立链接超时
     * @throws SocketTimeoutException  响应超时
     * @throws Exception
     */
    public static String get(String url, String CHARSET, Integer CONNTIMEOUT, Integer READTIMEOUT) throws ConnectTimeoutException, SocketTimeoutException, Exception {
        System.out.println("发送HTTP(S) GET 请求：" + url + " | " + CHARSET + " | " + CONNTIMEOUT + " | " + READTIMEOUT);
        HttpClient client = null;
        HttpResponse res = null;
        HttpGet get = new HttpGet(url);
        String result = null;
        try {
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (CONNTIMEOUT != null) {
                customReqConf.setConnectTimeout(CONNTIMEOUT);
            }
            if (READTIMEOUT != null) {
                customReqConf.setSocketTimeout(READTIMEOUT);
            }
            get.setConfig(customReqConf.build());

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(get);
            } else {
                // 执行 Http 请求.
                client = HttpClientUtil.client;
                res = client.execute(get);
            }
            if (res.getStatusLine().getStatusCode() == 200) {
                result = IOUtils.toString(res.getEntity().getContent(), CHARSET);
            } else {
                System.out.println("HTTP(S) GET 请求，状态异常：" + res.getStatusLine().getStatusCode() + " | " + url);
                result = null;
            }
        } finally {
            get.abort();
            if (null != res) {
                EntityUtils.consumeQuietly(res.getEntity());
            }
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 创建 SSL连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        Builder requestBuilder = RequestConfig.custom();
        requestBuilder = requestBuilder.setSocketTimeout(CONNTIMEOUT);
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                .setDefaultRequestConfig(requestBuilder.build())
                .setSSLHostnameVerifier(new DefaultHostnameVerifier()).build();
        return client;
    }
}

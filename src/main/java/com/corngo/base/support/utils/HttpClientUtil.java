package com.corngo.base.support.utils;

import jodd.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * httpClient工具类
 * <p/>
 * Created by kfc on 2016/11/2.
 */
public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    // 超时时间设置
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
            .setSocketTimeout(5000).build();
    // 编码
    private static String DEFAULT_CHARSET = "UTF-8";

    /**
     * pos请求，设置头信息，和超时时间
     *
     * @param url                      请求地址
     * @param paramMap                 参数信息
     * @param headerMap                头信息
     * @param connectTimeout           链接时间
     * @param socketTimeout
     * @param connectionRequestTimeout
     * @param charSet                  编码
     * @return
     * @throws IOException
     */
    public static String post(String url,
                              Map<String, String> paramMap,
                              Map<String, String> headerMap,
                              Integer connectTimeout,
                              Integer socketTimeout,
                              Integer connectionRequestTimeout,
                              String charSet) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        if (null != headerMap)
            for (String h : headerMap.keySet()) {
                httpPost.setHeader(h, headerMap.get(h));
            }
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charSet));
        result = request(result, httpClient, httpPost);
        return result;
    }

    /**
     * 设置超时时间的post请求
     *
     * @param url                      请求地址
     * @param paramMap                 请求参数
     * @param connectTimeout           链接时间
     * @param socketTimeout            读取响应时间
     * @param connectionRequestTimeout 链接请求时间
     * @return
     */
    public static String post(String url, Map<String, String> paramMap, Integer connectTimeout
            , Integer socketTimeout
            , Integer connectionRequestTimeout) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        result = request(result, httpClient, httpPost);
        return result;
    }

    /**
     * post请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @return
     */
    public static String post(String url, Map<String, String> paramMap) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        result = request(result, httpClient, httpPost);
        return result;
    }

    /**
     * post
     *
     * @param url  请求地址
     * @param data 请求数据（如 json串）
     * @return
     * @throws IOException
     */
    public static String post(String url, String data, String encode) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (StringUtil.isBlank(encode)) {
            httpPost.setEntity(new ByteArrayEntity(data.getBytes("UTF-8")));
        } else {
            httpPost.setHeader("Content-Type", "text/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(URLEncoder.encode(data, "UTF-8")));
        }
        result = request(result, httpClient, httpPost);
        return result;
    }


    /**
     * 提交请求
     *
     * @param result     请求结果
     * @param httpClient httpClient
     * @param request    HttpUriRequest
     * @return
     * @throws IOException
     */
    private static String request(String result, CloseableHttpClient httpClient, HttpUriRequest request) throws IOException {
        CloseableHttpResponse httpResp = httpClient.execute(request);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            log.info("http请求结果--->状态码：{}", statusCode);
            if (HttpStatus.SC_OK == statusCode) {
                result = getHttpEntityContent(httpResp);
            }
        } finally {
            httpResp.close();
            httpClient.close();
        }
        return result;
    }

    public static InputStream request(CloseableHttpClient httpClient, HttpUriRequest request) throws IOException {
        CloseableHttpResponse httpResp = httpClient.execute(request);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            log.info("http请求结果--->状态码：{}", statusCode);
            if (HttpStatus.SC_OK == statusCode) {
              return  httpResp.getEntity().getContent();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * get 请求
     *
     * @param url 请求地址
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        result = request(result, httpClient, httpGet);
        return result;
    }

    /**
     * get请求
     *
     * @param url      请求地址
     * @param paramMap 请求参数
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> paramMap) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        httpGet.setURI(URI.create(url + "?" + param));
        result = request(result, httpClient, httpGet);
        return result;
    }

    /**
     * put 请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String put(String url, Map<String, String> paramMap) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        result = request(result, httpClient, httpPut);
        return result;
    }

    /**
     * delete 请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String delete(String url) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete();
        httpDelete.setURI(URI.create(url));
        result = request(result, httpClient, httpDelete);
        return result;
    }

    /**
     * delete 请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String delete(String url, Map<String, String> paramMap) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete();
        List<NameValuePair> nameValuePairs = setHttpParams(paramMap);
        String param = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        httpDelete.setURI(URI.create(url + "?" + param));
        result = request(result, httpClient, httpDelete);
        return result;
    }

    /**
     * 设置请求参数
     *
     * @param paramMap
     * @return
     */
    private static List<NameValuePair> setHttpParams(
            Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        return formparams;
    }

    /**
     * https请求（不带参数）
     *
     * @param url
     * @return
     */
    public static String postHttps(String url) {
        String str = null;
        try {
            SSLContext sslcontext = getSslContext();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            // 创建HttpPost
            HttpGet httpPost = new HttpGet(url);
            httpPost.setHeader("content-type", "application/json");
            // 执行POST请求
            HttpResponse response = client.execute(httpPost);
            // 获取响应实体
            if (response.getStatusLine().getStatusCode() == 200) {
                str = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            log.info("报错了");
            e.printStackTrace();
        }
        return str;
    }

    /**
     * https post请求（传递参数）
     * @param post
     * @return
     */
    public static String postHttps(HttpPost post) {
        String str = null;
        try {
            SSLContext sslcontext = getSslContext();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 执行POST请求
            HttpResponse response = client.execute(post);
            // 获取响应实体
            if (response.getStatusLine().getStatusCode() == 200) {
                str = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            log.info("报错了");
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 分隔符
     *
     * @return
     */
    public static String getBoundary() {
        return "-----------" + System.currentTimeMillis();
    }


    /**
     * 建立一个信任任何的证书的sslcontext。
     *
     * @return
     * @throws Exception
     */
    public static SSLContext getSslContext() throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] arg0,
                                             String arg1) throws CertificateException {
                        return true;
                    }

                }).build();
        return sslcontext;
    }


    /**
     * 获得响应HTTP实体内容
     *
     * @param response
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response)
            throws IOException, UnsupportedEncodingException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }
}

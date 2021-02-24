package com.springboot.Repository.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.springboot.entity.AppPayConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PayAction {

    public static String payAction(double total_amount,String body, String out_trade_no) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AppPayConfig.gatewayUrl,
                AppPayConfig.app_id, AppPayConfig.merchant_private_key,"json",
                AppPayConfig.charset, AppPayConfig.alipay_public_key, AppPayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(AppPayConfig.return_url);
        request.setNotifyUrl(AppPayConfig.notify_url);

        String subject = new String("校园外卖");
//        String total_amount = "0.01";
//        String body = "商品描述";
//        String out_trade_no = "123";

        request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                +total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String res = alipayClient.pageExecute(request).getBody();
       return  res;
    }

    public static boolean check(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        boolean isok = false;
        AlipayClient alipayClient = new DefaultAlipayClient(AppPayConfig.gatewayUrl,
                AppPayConfig.app_id, AppPayConfig.merchant_private_key,"json",
                AppPayConfig.charset, AppPayConfig.alipay_public_key, AppPayConfig.sign_type);

        Map<String, String> paramsMap = convertRequestParamsToMap(request);  //将异步通知中收到的所有参数都存放到 map 中
        boolean  signVerified = AlipaySignature.rsaCheckV1(paramsMap, AppPayConfig.alipay_public_key, "utf-8", "RSA"); //调用SDK验证签名
        for (String st : paramsMap.keySet()){
            System.out.println(st + ":" +paramsMap.get(st) );
        }

        if (signVerified){
            System.out.println("验证成功");
            isok = true;
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        } else {
            isok = false;
        }

        return isok;
    }

    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }




}

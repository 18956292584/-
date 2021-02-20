package com.springboot.Repository.Impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.springboot.entity.AppPayConfig;

public class PayAction {

    public static String payAction(double total_amount,String body, String out_trade_no) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AppPayConfig.gatewayUrl,
                AppPayConfig.app_id,AppPayConfig.merchant_private_key,"json",
                AppPayConfig.charset,AppPayConfig.alipay_public_key,AppPayConfig.sign_type);
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


}

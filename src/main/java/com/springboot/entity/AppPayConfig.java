package com.springboot.entity;

public class AppPayConfig {
    //应用ID
    public static String app_id  = "2021000117611853";
    //应用私钥，
    public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJFGmv04+TWVvnJ78ryXRwWaGWLjoygf5g9Qalakndd3PM9dEa7ngi55JV/FrTH9JQvkdYImFry6ZexqUdfHp393UV4h8kDYsE3RfzagrUnKBtmOuqWnTCBJJN//OELZoSkQNnOolYynCYton34iZaKuJscIIa8fwXp1JS04uMRrAgMBAAECgYABO4QdGdqjZb3T4tFQYEgt9/y3Jg9AS+T7xEbirUrZhR1y9OstfLFSC50Rju/FOMyTqUCF9iTDPSGYO96t4fe9zdOi0rs8VO+8iWIoCZMBGlNMVkDk9mawZa6Hfd9EvdeLjVFU6WtMJbcey6JlrcHoilxytryN3pFsyxksuhqOQQJBAMIVIFFAhUG7aBiwDoadACB1fMlXtEkdiSMBmkDy+OTVf134paDcWr25P5k4DjaRlYXKemOaQktJ3LlFx2VCo+MCQQC/n2U+P/ohDgDVCXANnN464Swr9MzblHBNTu7/TUtJYgrpFhodulDBIgSOZP2pt14Rzf3S6MH0GftrxbGrRhPZAkBNRPP05WcoYGB1U8K0vVULlzrzPkO8yFGM6lpreVC2anmnBBONE63c0yAK9FJUT2nkNlS7yvKR3vpNmQZlvj5ZAkAz0ew0MGL5K2tIMVwvIjvq2lfyrynzO6o/DdiODB4PA0eGDycS4P7Tq9plk6QRkHgNApLgxXcmBxy54WWRtsrJAkEAkwdR7sBt4gpDN7x+yVh+v1A0Ztc0sxfzWQW3Jn/E8AzS0NLZ3lHDBsyxVp2mA8I3Bd1txhs2dZn1/4GejYOAjw==";
    //支付宝公钥
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    //支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do" ;


    public static String notify_url="http://470da1df.nat123.fun/index/";
    public static String return_url="http://470da1df.nat123.fun/index/";

    public static String sign_type = "RSA";

    public static String charset = "utf-8";

}

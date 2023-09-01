package com.aurora.constant;

public interface AuthConstant {

    /**
        默认值 20分钟重登一次？
     */
    int TWENTY_MINUTES = 240;

    int EXPIRE_TIME = 7 * 24 * 60 * 60;

    String TOKEN_HEADER = "Authorization";

    String TOKEN_PREFIX = "Bearer ";

    String SECRET = "huaweimian";

}

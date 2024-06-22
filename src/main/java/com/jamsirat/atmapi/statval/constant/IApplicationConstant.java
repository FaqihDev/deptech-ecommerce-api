package com.jamsirat.atmapi.statval.constant;

public interface IApplicationConstant {

    interface ContextPath{
        String ORDER = "/v1/order-service";
        String USER = "/v1/user-service";
        String AUTHENTICATION = "/api/v1/auth";
        String AUTHORIZATION = "/api/v1/grant";
        String USER_PROFILE = "/api/v1/user";
    }

    interface Path{

        interface Cart {
            String ADD_CART = "/cart/add-cart";
            String GET_CART = "/cart/get-cart";
        }

        interface User {
            String COMPLETE_PROFILE ="/complete-profile";
            String UPDATE_PROFILE ="/update-profile";

        }

        interface Authorization {
            String GRANT_ACCESS ="/grant-access";
        }

        interface Authentication {
            String REGISTER = "/register";
            String LOGIN = "/login";
            String REFRESH_TOKEN = "/refresh-token";
            String VERIFY_ACCOUNT = "/verifyEmail";
        }

    }

    interface  DefaultNumber {
        Long OUT_OF_STOCK = 0L;

    }


}
package com.jamsirat.atmapi.statval.constant;

public interface IApplicationConstant {

    interface ContextPath{
        String ORDER = "/v1/order-service";
        String USER = "/api/v1/user";
        String AUTHENTICATION = "/api/v1/auth";
        String AUTHORIZATION = "/api/v1/grant";
        String PRODUCT = "/api/v1/product";
        String CATEGORY_PRODUCT = "/api/v1/category";

    }

    interface Path{

        interface Cart {
            String ADD_CART = "/cart/add-cart";
            String GET_CART = "/cart/get-cart";
        }

        interface User {
            String USER_DETAIL = "/user-detail";
            String UPDATE_USER ="/update-user";
            String DELETE_USER = "/delete-user/{userId}";
            String LIST_USERS = "/fetch-users";
        }

        interface Product {
            String ADD_PRODUCT = "/add-product";
            String PRODUCT_DETAIL = "/product-detail/{productId}";
            String UPDATE_PRODUCT ="/update-product";
            String DELETE_PRODUCT = "/delete-product{productId}";
            String LIST_PRODUCT = "/fetch-product";
        }

        interface CategoryProduct {
            String ADD_CATEGORY = "/add-category";
            String CATEGORY_PRODUCT_DETAIL = "/detail-category{categoryId}";
            String UPDATE_CATEGORY_PRODUCT ="/update-category";
            String DELETE_CATEGORY_PRODUCT = "/delete-category/{categoryId}";
            String LIST_CATEGORY_PRODUCT = "/fetch-category";
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

    interface StaticDefaultMessage {

        interface User {

        }

    }


}
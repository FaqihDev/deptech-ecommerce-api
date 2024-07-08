package com.jamsirat.atmapi.statval.constant;

public interface IApplicationConstant {

    interface ContextPath{
        String ORDER = "/api/v1/order";
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
            String PRODUCT_DETAIL = "/detail-product/{productId}";
            String UPDATE_PRODUCT ="/update-product";
            String DELETE_PRODUCT = "/delete-product/{productId}";
            String LIST_PRODUCT = "/fetch-product";
        }

        interface CategoryProduct {
            String ADD_CATEGORY = "/add-category";
            String CATEGORY_PRODUCT_DETAIL = "/detail-category/{categoryId}";
            String UPDATE_CATEGORY_PRODUCT ="/update-category";
            String DELETE_CATEGORY_PRODUCT = "/delete-category/{categoryId}";
            String LIST_CATEGORY_PRODUCT = "/fetch-category";
        }

        interface Order {
            String PERFORM_TRANSACTION = "/transaction";
            String TRANSACTION_HISTORIES = "/transaction-histories";
        }

        interface Authorization {
            String GRANT_ACCESS ="/grant-access";
        }

        interface Authentication {
            String REGISTER = "/register";
            String LOGIN = "/login";
            String REFRESH_TOKEN = "/refresh-token";
            String LOGOUT = "logout";
            String VERIFY_ACCOUNT = "/verifyEmail";
        }


    }

    interface  DefaultNumber {
        Long OUT_OF_STOCK = 0L;

    }

    interface StaticDefaultMessage {

            interface ExceptionMessage {
                String USER_NOT_FOUND_EXCEPTION = "User with id %s is does not exists";
                String FAILED_DATA_USER = "Failed to get data user!";
                String OUT_OF_STOCK_EXCEPTION = "Sorry, we are out of stock";
                String PRODUCT_NOT_FOUND_EXCEPTION = "Product with id is not exists";
            }

            interface DeveloperExceptionMessage {
                String USER_NOT_FOUND_EXCEPTION = "Make sure user id is correct";
                String FAILED_DATA_USER = "Please check your token";
                String PRODUCT_NOT_FOUND_EXCEPTION = "Make sure product id is correct";
                String OUT_OF_STOCK_EXCEPTION = "Please contact your Administrator";
            }

            interface SuccessMessage {
                String TRANSACTION_SUCCESS = "Transaction created successfully";
                String TRANSACTION_HISTORY = "Transaction History fetch successfully";
                String LOGOUT_MSG_SUCCESS = "Logout successful";
            }

            interface DeveloperSuccessMessage {
                String TRANSACTION_SUCCESS = "Please do payment";
                String TRANSACTION_HISTORY = "Transaction histories";
                String LOGOUT_MSG_SUCCESS = "Please do login to continue";
            }

    }


}
package com.kyle.takeaway.entity;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class Constants {

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String PNG = ".png";
    public static final String DATA = "data";


    public static final String LOGIN_USER = "" + "/user_api/register_login/login";
    public static final String LOGIN_STORE = "" + "/store_api/store_center/login";

    public static final String GET_REGISTER_CODE = "" + "/user_api/register_login/get_register_code";
    public static final String GET_RESETPSW_CODE = "" + "/user_api/register_login/get_repassword_code";
    public static final String RESET_PSW = "" + "/user_api/register_login/reset_password";

    public static final String REGISTER = "" + "/user_api/register_login/register";

    public static final String USER_CHANGE_PSW = "" + "/user_api/register_login/update_password";
    public static final String STORE_CHANGE_PSW = "" + "/store_api/store_center/update_password";

    public static final String GET_USER_INFO = "" + "/user_api/user_center/user_info";

    public static final String EDIT_USER_INFO = "" + "/user_api/user_center/edit_user_info";

    public static final String UPLOAD_PIC = "" + "/user_api/upload";

    public static final String GET_ADDRESS_LIST = "" + "/user_api/user_center/get_address_list";
    public static final String ADD_ADDRESS_LIST = "" + "/user_api/user_center/add_address";
    public static final String DELETE_ADDRESS_LIST = "" + "/user_api/user_center/delete_address";
    public static final String GET_STORE = "" + "/store_api/store_center/get_store_list";
    public static final String GET_STORE_DETAIL = "" + "/food_api/food_center/get_food_list";

    public static final String POST_CART = "" + "/food_api/food_center/edit_car_food";
    public static final String GET_CART = "" + "/food_api/food_center/car_food_list";

    public static final String GET_ORDER = "" + "/user_api/order/get_order_list";
    public static final String POST_COMMENT = "" + "/user_api/user_center/add_evaluation";

    public static final String GET_STORE_INFO = "" + "/store_api/store_center/store_info";
    public static final String EDIT_STORE_INFO = "" + "/store_api/store_center/edit_store_info";

    public static final String DELETE_PRODUCT = "" + "/store_api/store_center/delete_food";
    public static final String ADD_PRODUCT = "" + "/store_api/store_center/add_food";
    public static final String EDIT_PRODUCT = "" + "/store_api/store_center/edit_food";

    public static final String STORE_ORDERS = "" + "/store_api/order/get_order_list";
    public static final String ORDER_SEND = "" + "/store_api/store_center/order_delivery";

    public static final String PRE_COMMIT_ORDER = "" + "/user_api/order/befor_add_order";
    public static final String COMMIT_ORDER = "" + "/user_api/order/add_order";

    public static final String GET_COMMENTS = "" + "/user_api/user_center/get_evaluation_list";

    public static final String STORE_SELL_HISTORY = "" + "/store_api/store_center/account_report";


}


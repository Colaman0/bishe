package com.kyle.takeaway.entity;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class Constants {
    public static final String BASE_URL = "http://xmy1996.oicp.io:42418";

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String PNG = ".png";
    public static final String DATA = "data";


    public static final String LOGIN_USER = BASE_URL + "/user_api/register_login/login";
    public static final String LOGIN_STORE = BASE_URL + "/store_api/store_center/login";

    public static final String GET_REGISTER_CODE = BASE_URL + "/user_api/register_login/get_register_code";
    public static final String GET_RESETPSW_CODE = BASE_URL + "/user_api/register_login/get_repassword_code";
    public static final String RESET_PSW = BASE_URL + "/user_api/register_login/reset_password";

    public static final String REGISTER = BASE_URL + "/user_api/register_login/register";

    public static final String USER_CHANGE_PSW = BASE_URL + "/user_api/register_login/update_password";
    public static final String STORE_CHANGE_PSW = BASE_URL + "/store_api/store_center/update_password";

    public static final String GET_USER_INFO = BASE_URL + "/user_api/user_center/user_info";

    public static final String EDIT_USER_INFO = BASE_URL + "/user_api/user_center/edit_user_info";

    public static final String UPLOAD_PIC = BASE_URL + "/user_api/upload";

    public static final String GET_ADDRESS_LIST = BASE_URL + "/user_api/user_center/get_address_list";
    public static final String ADD_ADDRESS_LIST = BASE_URL + "/user_api/user_center/add_address";
    public static final String DELETE_ADDRESS_LIST = BASE_URL + "/user_api/user_center/delete_address";
    public static final String GET_STORE = BASE_URL + "/store_api/store_center/get_store_list";
    public static final String GET_STORE_DETAIL = BASE_URL + "/food_api/food_center/get_food_list";


}


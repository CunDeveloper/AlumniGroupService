package com.nju.edu.cn.software.helper;

public interface Constant {

	int OK_CODE = 200;
	String OK_MSG = "OK";
	int CODE_202 = 202;
	
	int UNAUTHORIZED_CODE = 401;
	String UNAUTHORIZED_MEG = "You do not have the appropriate privileges to access this resource";
	
	int INTERNAL_SYSTEM_ERROR_CODE = 500;
	String INTERNAL_SYSTEM_ERROR_MSG = "Internal System error";
	
	int CREDENTIALS_INCORRECT = 401;
	String CREDENTIALS_INCORRECT_MSG = "Client Credentials were incorrect";
	
	int NOT_AUTHORIZED_CODE = 403;
	String NOT_AUTHORIZED_MSG = "Not authorized";
	
	int ALREADY_VERIRIED = 409;
	String ALREADY_VERIRIED_MSG = "Already verified";
	
	String AUTHORIZATION = "authorization";
	String BODY = "body";
	String CONTENT="content";
	String LABEL ="label";
	String CONTENT_TYPE_JSON = "application/json";
	
	int SERVICE_UNAVALIABLE_CODE = 503;
	String SERVICE_UNAVALIABLE_MSG = "service unavaliable";
	
	int INTERNAL_SERVICE_ERROR_CODE = 500;
	String INTERNAL_SERVICE_ERROR_MSG  = "internal server error";
	
	int UNKNOWN_ERROR_CODE = 7777;
	String UNKNOWN_ERROR_MSG = "unknown error";
	
	int ACCESS_ERROR_CODE = 9999;
	String ACCESS_ERROR_MSG = "param error";
	
	int REPEAT_OPERATE_ERROR_CODE = 6666;
	String REPEATE_OPERATE_MSG = "repeat operate";
	
	String FILE = "file";
	String MULTIPART_FORM_DATA="multipart/form-data";
	
    String OK = "ok";
	String ERROR = "error";
	String USERNAME_OR_PASS_ERROR = "用户名或者密码错误";
	String USER_ICON = "user_icon";
	String SCHOOL = "school";
	String UPLAOD_FIEL_FAILURE = "upload_file_failure";
	int SQL_EXE_OK = 1;
	int SQL_EXE_FALIURE = -1;
	String LABEL_ID = "label_id";
	String PARAMER_ERROR = "输入的参数错误";
	String PUBLISH_WEIBO_ERROR = "发布校友圈失败";
	String QUERY_ALL = "query_all";
	String QUERY_OWN = "query_own";
	String QUERY_ANOTHER = "query_another";
	String XUE_AUTH ="auth";
	String LABLE = "lable";
	String XUE_XIN_CAPTCHA ="captcha";
	String XUE_XIN_IT = "xue_xin_it";
    String XUE_XIN_BASE_URL = "http://my.chsi.com.cn";
	String XUE_XIN_LOGIN_URL = "https://account.chsi.com.cn/passport/login";
    String XUE_XIN_SERVICE = "?service=http://my.chsi.com.cn/archive/j_spring_cas_security_check";
	int HTTP_OK =200;
	String HTTP_ERROR = "内部服务器出错";
	String HTTP_URL_ERROR = "请求网关错误";
	String XUE_XIN_INFO = "xue_xin_info";
	String XUE_XIN_USERNAME_OR_PASS_ERROR = "xue_xin_user_or_pass_error";
	
	String BAD_REQUEST = "HTTP 400 bad request:";
	String CLIENT_ERROR = "HTTP 412 client error:";
	String FORBIDDEN_REQUEST_ERROR = "HTTP 403 server forbidden request error:";
	String INTERNAL_SERVER_ERROR = "HTTP 500 internal server error:";
	String NOT_ACCEPTABLE_ERROR = "HTTP 406 not acceptable error:";
	String METHOD_NOT_ALLOWED = "HTTP 405 not allowed  error:";
	String NOT_AUTHORIZED = "HTTP 401 not authorized  error:";
	String NOT_FOUND = "HTTP 404 not found error:";
	String NOT_SUPPORT = "HTTP 415 not support media type error:";
	String REDIRECTION_ERROR = "HTTP 313 redirection error:";
	String SERVER_ERROR = "HTTP 513 server error:";
	String SERVER_UNAAVILABEL_ERROR = "HTTP 503 service unavailable error:";
	String WEB_APPLICATION_ERROR ="HTTP 500 web application error:";
	
	String LEVEL_ALL ="所有";
	String LEVEL_UNDERGRADUATE = "本科";
	String LEVEL_MASTER = "硕士";
	String LEVEL_DOCTOR ="博士";
	String LABELS = "labels";
	String AUTHOR_ID = "authorId";
	String VALID_VALUE = "validValue";
	String LEVEL = "level";
	String JPG = "jpg";
	 
	 
	
}

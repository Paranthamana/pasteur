package com.example.util;

import android.graphics.Bitmap;

import com.example.api.Urls;
import com.example.poseestimationopencv.Camera2BasicFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {


    public static final int SDK = android.os.Build.VERSION.SDK_INT;

    public static String img_1;
    public static int pos_0 = 0;
    public static int pos_2 = 0;
    public static int pos_3 = 0;
    public static int pos_4 = 1;
    public static int pos_5 = 0;
    public static int pos_6 = 0;
    public static int pos_7 = 1;
    public static int pos_1 = 0;
    public static HashMap<Integer, float[][]> Pose = new HashMap<>();
    public static float Different = 30;
    public static ArrayList<String> textmessage = new ArrayList<String>(2000000);
    public static Camera2BasicFragment.IModelDataCallback dataCallback;

    //profile2
    public static String mname = "";
    public static String child_id = "";//127
    public static String order_id = "";
    public static String bmiuploadresult = "";
    public static String claiduploadresult = "";
    public static String claidlastinformation = "";
    public static String age = "";
    public static String login_api_key = "";//eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE1ODM3MTEzYzk5NDIxZDljNWU5MDI5Yzk5NDYyMDA0MDNkMDU1ZTk4M2MxMTlmNjNmMjZhNzFjMDM2YzNiMjEyN2ZlYmQ0OTA0ZjlkYThiIn0.eyJhdWQiOiIxIiwianRpIjoiMTU4MzcxMTNjOTk0MjFkOWM1ZTkwMjljOTk0NjIwMDQwM2QwNTVlOTgzYzExOWY2M2YyNmE3MWMwMzZjM2IyMTI3ZmViZDQ5MDRmOWRhOGIiLCJpYXQiOjE1NzMxOTU5MTksIm5iZiI6MTU3MzE5NTkxOSwiZXhwIjoxNjA0ODE4MzE5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.dt7lbt9VT-kUdaSzHrO8IvP3QlFHLEnsuP2KS6qMVcgDy11tLtkQvkCOyMk1IL9mH9WQJ5j-2mABFDRLChLhghV2H2dtlXOBICpEO0ufSPhVGKxDNkrY7KPmolL2th-7jOKSKsfrlS26m0m9NCmDetnq-HsvAPzWJZwEx0jP3xpbl3uA1g-YuU1yD8jqfeJCG9IxjEzGnnFq2uj-DErUOvfIygYlmHODgIqHwI6bhflt10_7Pe8bz5dKqeWfyVLG3bMBaxTg9g7J4woZPJF7iyThUIFwgOQ3XgoTFBbOViBG7-l78uY4mMKqZFbxe3Y7IXtzxu8CPp8uqhHTpETas6vv759ONNkjxuLuDYrQCElg1sloVPoD6svHi4F2AyoXDYHZrL5Xkch1UC6yYULtlF1seonV4oe-8_XoPRVFrFn8xn9tKpyoihbuvDNJZbdaF7hWMDf5UdK5dwAq_9OmKuZeIA_k_V4aHGBLUFglAhbz-NXkzym3SyngA7EJXG5zfB0jPcOa0nm_oclezJMrjUwrzSRTFtN96sQbVJgJO1oRpU1b_duh-PPOVgvEvH0G3c1Yk_TSwnfsDryEgqvby7Nvoy5WIXr7WJZJPESIYFYlOjHH-HVu4d9KDmBx2qLCJIID78Y0rm-OMEm_vU8KUM_eMJljX8oALXJ1l5SFYGM
    public static String user_id = "";
    public static String pemail = "";
    public static String pname = "";
    public static String pose_name = "";
    public static int pose_no = 3;
    public static int scannedcharlimit = 20;

    public static int pose_height = 0;
    public static String[] cro_pose;
    public static String fabric_order_id = "";
    public static int FIRST_PAGE_BMI = 0;
    public static int FIRST_PAGE_EIGHT = 0;
    public static String DefaultfrontUrl = "";

    public static String Defaultheight = "";
    public static String Defaultweight = "";
    public static String defaultDate = "";

    public static String[] childUrl;
    public static String fromhistory = "1"; // 1-From Photo Capture //2- From History
    public static int FIRST_PAGE_EIGHTs = 10;
    public static int FIRST_PAGE_MESSAGES = 10;
    public static int noofpostook = 0;
    public static Boolean isPoseDetected[] = {false, false, false, false};
    public static CameraCapturePoseDetection poseDetector = new CameraCapturePoseDetection();

    public static int noofpostooks = 8;
    public static String measurementtype = "";
    public static String oid = "";
    public static int poseees = 0;
    // public static int pagination=1;
    public static String Enteryourcomments = "Enter your comments";
    public static String tooltiptext = "I want to change chest size litle loose\\n Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
    public static String FirstLeftSidePoseMandatory = "First take left side pose is mandatory";
    public static String messagename = "";
    public static String messagefile = "";
    public static String messageid = "";
    public static Bitmap[] posImages = new Bitmap[4];

    /*class Setting{
        static Constants String headTitle = (APIConstants.lng==1)?"设定值":"SETTINGS";
        static Constants String eng_chinesh = (APIConstants.lng==1)?"1-中国语, 2-英语":"1-Chinesh, 2-English";
        static Constants String lngtext = (APIConstants.lng==1)?"语言":"Language";
        static Constants String dinnersessionstart = (APIConstants.lng==1)?"晚餐时段订单开始时间（HH：MM：SS）":"Dinner Session Order Start Time (HH:MM:SS)";
        static Constants String dinnersessionend = (APIConstants.lng==1)?"晚餐时段订单结束时间（HH：MM：SS）":"Dinner Session Order End Time (HH:MM:SS)";
        static Constants String lunchsessionstart = (APIConstants.lng==1)?"午餐会订单开始时间（HH：MM：SS）":"Lunch Session Order Start Time (HH:MM:SS)";
        static Constants String lunchsessionend = (APIConstants.lng==1)?"午餐会订单结束时间（HH：MM：SS）":"Lunch Session Order End Time (HH:MM:SS)";
        static Constants String breakfastsessionstart= (APIConstants.lng==1)?"早餐时段订单开始时间（HH：MM：SS）":"Breakfast Session Order Start Time (HH:MM:SS)";
        static Constants String breakfastsessionend = (APIConstants.lng==1)?"早餐时段订单结束时间（HH：MM：SS）":"Breakfast Session Order End Time (HH:MM:SS)";
        static Constants String changetext = (APIConstants.lng==1)?"更改":"Change";
        static Constants String updatetext = (APIConstants.lng==1)?"更改":"Update";
        static Constants String successtext = (APIConstants.lng==1)?"成功":"Success";
        static Constants String closetext = (APIConstants.lng==1)?"成功":"Close";

    }*/

    public static String d[] = {
            Urls.PHOTOPATHURL + "leftpose.png",
            Urls.PHOTOPATHURL + "neckleft.png",
            Urls.PHOTOPATHURL + "backside.png",
            Urls.PHOTOPATHURL + "croatch.png",
            Urls.PHOTOPATHURL + "rightpose.png",
            Urls.PHOTOPATHURL + "neckright.png",
            Urls.PHOTOPATHURL + "frontpose.png",
            Urls.PHOTOPATHURL + "neckfront.png"};


    public static String[] cro_poseTwo;


    public static String dTwo[] = {
            Urls.PHOTOPATHURL + "frontpose.png",
            Urls.PHOTOPATHURL + "neckright.png"
    };


    public static String img_path_left = "";
    public static String img_path_front = "";
    public static String img_path_back = "";
    public static String img_path_croauch = "";
    public static String img_path_right = "";
    public static String img_path_neck_right = "";
    public static String img_path_neck_left = "";
    public static String img_path_neck_front = "";
    public static float[] angle;

    public static String[] imagess = {

            Urls.PHOTOPATHURL + "leftpose.png",
            Urls.PHOTOPATHURL + "backside.png",
            Urls.PHOTOPATHURL + "rightpose.png",
            Urls.PHOTOPATHURL + "frontpose.png"
    };
    // Language text
    public static String accountID = "";
    public static String forgot = "";
    public static String password = "";
    public static String rememberMe = "";
    public static String dontHaveAccountCreate = "";
    public static String signUp = "";
    public static String pleaseEnterMobileNumberToContinue = "";
    public static String mobileNumber = "";
    public static String send = "";
    public static String alreadyHaveAccountLogin = "";
    public static String forgotPassword = "";
    public static String pleaseEnterYour6DigitOtpNumber = "";
    public static String Measurement = "";
    public static String scanId = "";
    public static String Name = "";
    public static String height = "";
    public static String weight = "";
    public static String previousMeasurement = "";
    public static String help = "";
    public static String customerSupport = "";
    public static String aiMeasurement = "";
    public static String bmi = "";
    public static String cancelled = "";
    public static String nameCannotEmpty = "";
    public static String ageFiledIsEmpty = "";
    public static String heightFieldIsEmpty = "";
    public static String weightFieldIsEmpty = "";
    public static String heightIsNotMatched = "";
    public static String weightIsNotMatched = "";
    public static String maxWeight150kg = "";
    public static String maxAge100 = "";
    public static String scanningFailedTryAgain = "";
    public static String scanningCharLengthShouldBe = "";
    public static String characterOnlyAccepted = "";
    public static String scanningSuccessFully = "";
    public static String filedMandatory = "";
    public static String date = "";
    public static String cm = "";
    public static String kg = "";
    public static String camera = "";
    public static String loginSuccessMessage = "";
    public static String messageMeasurementSuccess = "";
    public static String viewOrderId = "";
    public static String accuracyMeasurement = "";
    public static String profile = "";
    public static String termsAndCondition = "";
    public static String logout = "";
    public static String noDataFound = "";
    public static String ok = "";
    public static String uploadSuccess = "";
    public static String next = "";
    public static String home = "";
    public static String pm_OrderId = "";
    private static Constants commonLanguageInstance = new Constants();
    public static String Login = "";
    public static String weHaveSendAccessCode = "";
    public static String userNameNotEmpty = "";
    public static String userPwdCannotEmpty = "";
    public static String pleaseClickBackAgainToExit = "";
    public static String SomethingWentWrong = "";
    public static int SplashDisplayLength = 2000;
    public static String SignInToContinue = "";
    private static String Language = "2";

    public static Constants getInstance() {
        return commonLanguageInstance;
    }

    public void languageConstants() {

        home = ((Language.equals("1")) ? "家" : "Home");
        next = ((Language.equals("1")) ? "下一个" : "Next");
        ok = ((Language.equals("1")) ? "好" : "Ok");
        noDataFound = ((Language.equals("1")) ? "没有找到数据" : "No Data Found");
        logout = ((Language.equals("1")) ? "登出" : "Logout");
        termsAndCondition = ((Language.equals("1")) ? "条款和条件" : "Terms And Conditions");
        profile = ((Language.equals("1")) ? "轮廓" : "Profile");
        accuracyMeasurement = ((Language.equals("1")) ? "精度测量" : "Accuracy Measurement");
        viewOrderId = ((Language.equals("1")) ? "订单编号" : "Order Id");
        Login = ((Language.equals("1")) ? "登录" : "LogIn");
        SignInToContinue = ((Language.equals("1")) ? "请登录以继续" : "Please signin to continue");
        weHaveSendAccessCode = ((Language.equals("1")) ? "我们已通过短信发送访问代码以验证手机号码" : "we have send access code \n via sms for mobile number verification");
        userNameNotEmpty = ((Language.equals("1")) ? "帐户ID不能为空" : "Account id cannot empty");
        password = ((Language.equals("1")) ? "密码" : "Password");
        userPwdCannotEmpty = ((Language.equals("1")) ? "密码不能为空" : "Password cannot empty");
        pleaseClickBackAgainToExit = ((Language.equals("1")) ? "请再次点击返回退出" : "Please click back again to exit");
        SomethingWentWrong = ((Language.equals("1")) ? "有些不对劲" : "Something went wrong");
        accountID = ((Language.equals("1")) ? "帐户ID" : "Account ID");
        forgot = ((Language.equals("1")) ? "忘记？" : "Forgot?");
        rememberMe = ((Language.equals("1")) ? "记住账号" : "Remember me");
        dontHaveAccountCreate = ((Language.equals("1")) ? "还没有帐号？注册" : "Dont have an account ? Sign up");
        signUp = ((Language.equals("1")) ? "注册" : "Sign up");
        pleaseEnterMobileNumberToContinue = ((Language.equals("1")) ? "请输入您的手机号码以继续" : "Please enter your mobile number to continue");
        mobileNumber = ((Language.equals("1")) ? "手机号码" : "Mobile Number");
        send = ((Language.equals("1")) ? "发送" : "Send");
        alreadyHaveAccountLogin = ((Language.equals("1")) ? "已经有帐号了？登录" : "Already have account ? Login");
        forgotPassword = ((Language.equals("1")) ? "忘记密码" : "Forgot password");
        pleaseEnterYour6DigitOtpNumber = ((Language.equals("1")) ? "请输入您的6位数OTP号码" : "Please enter your 6 Digit OTP number");
        Measurement = ((Language.equals("1")) ? "测量" : "Measurement");
        scanId = ((Language.equals("1")) ? "扫描ID" : "Scan ID");
        age = ((Language.equals("1")) ? "年龄" : "Age");
        Name = ((Language.equals("1")) ? "名称" : "Name");
        height = ((Language.equals("1")) ? "高度" : "Height");
        weight = ((Language.equals("1")) ? "重量" : "Weight");
        previousMeasurement = ((Language.equals("1")) ? "先前的测量" : "Previous Measurement");
        help = ((Language.equals("1")) ? "救命" : "Help");
        customerSupport = ((Language.equals("1")) ? "客户支持" : "Customer Support");
        aiMeasurement = ((Language.equals("1")) ? "AI测量" : "AI Measurement");
        bmi = ((Language.equals("1")) ? "体重指数" : "BMI");
        cancelled = ((Language.equals("1")) ? "取消" : "Cancelled");
        nameCannotEmpty = ((Language.equals("1")) ? "名称不能为空" : "Name cannot empty");
        ageFiledIsEmpty = ((Language.equals("1")) ? "年龄字段为空" : "Age Field Is Empty");
        heightFieldIsEmpty = ((Language.equals("1")) ? "高度字段为空" : "Height Field Is Empty");
        weightFieldIsEmpty = ((Language.equals("1")) ? "重量字段为空" : "Weight Field Is Empty");
        heightIsNotMatched = ((Language.equals("1")) ? "高度不匹配" : "Height is not matched");
        weightIsNotMatched = ((Language.equals("1")) ? "重量不匹配" : "Weight is not matched");
        maxWeight150kg = ((Language.equals("1")) ? "最大重量150公斤" : "Max weight 150 Kg");
        maxAge100 = ((Language.equals("1")) ? "最高年龄100" : "Max age 100");
        scanningFailedTryAgain = ((Language.equals("1")) ? "扫描失败，请重试" : "Scanning failed, Try Again");
        scanningCharLengthShouldBe = ((Language.equals("1")) ? "扫描失败，请重试" : "Scanning character length should be");
        characterOnlyAccepted = ((Language.equals("1")) ? "只接受字符" : "Characters only accepted");
        scanningSuccessFully = ((Language.equals("1")) ? "扫描成功" : "Scanning successfully");
        filedMandatory = ((Language.equals("1")) ? "必填项" : "Field mandatory");
        date = ((Language.equals("1")) ? "日期 " : "Date ");
        cm = ((Language.equals("1")) ? "厘米 " : "cm ");
        kg = ((Language.equals("1")) ? "公斤 " : "KG ");
        camera = ((Language.equals("1")) ? "相机 " : "Camera");

    }

    private String[] boseUI = {"1.左姿势", "2.颈部左", "3.裆",
            "4.返回姿势", "5.右边", "6.右颈", "7.前姿势", "8.颈前"};

    public static String[] pose = {

            "右边", " 后姿势 ", "左姿势", "正确的姿势"
    };

    public static String[] pose2 = {

            "马上", "备份", "左上", "从前"
    };

    public static String[] poseTwo1 = {

            "前姿势"
    };

    public static String[] poseTwo2 = {

            "颈部左"
    };

    public static int viediostates = 0;
    public static int demo_viediostates = 0;
    public static int vid_cam = 0;
}

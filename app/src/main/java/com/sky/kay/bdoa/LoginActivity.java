package com.sky.kay.bdoa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.gdbds.update.UpdateManager;
import com.sky.kay.bdoa.model.App;
import com.sky.kay.bdoa.tool.LoginSaveTools;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class LoginActivity extends Activity {

    Button login;
    TextView register;
    TextView find;

    App app;

    EditText username;
    EditText password;
    LoginSaveTools tool;

    CheckBox type;
    String access_token="";
    String AuthKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_login);
        app = (App) getApplicationContext();

        // 缓存
        tool = new LoginSaveTools(this);

        initView();
        initShowProgressDialog();
        initFlag();
        initLogin();

 //       test();

//		if (tool.getPwd(flag) != null && !tool.getPwd(flag).get(0).isEmpty()) {
//			login();
//		}
    }

    private void test() {
        OkHttpUtils
                .post()
                .url("https://exmail.qq.com/cgi-bin/token")
                .addParams("grant_type","client_credentials")
                .addParams("client_id", "gdbds")
                .addParams("client_secret", "5d872d3c19d96b5bfdb9c44c7d73fd59")
                .build().execute(new StringCallback() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {


                try {
                    JSONTokener jsonParser = new JSONTokener(response);
                    JSONObject object = (JSONObject) jsonParser.nextValue();
                    access_token=object.getString("access_token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("AAA",response);
            }
        });
    }

    private void test1() {

//        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
//                .url("https://api.imgur.com/3/image")
//                .post(requestBody)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful())
//            throw new IOException("Unexpected code " + response);
//        System.out.println(response.body().string());
//    }
        OkHttpUtils
                .post()
                .url("http://openapi.exmail.qq.com:12211/openapi/mail/authkey")
                .addParams("Alias","chenguohui@gdbds.net")
                .addParams("access_token",access_token)
//                .addParams("Authorization","Bearer" +
//                        "GHUSH-4qIXPScxa_OY0CbPS31W1OM24L_Ys9FCc7LtJyxjHD5OZafLh3Y8gM7gzDtp-GdQEY4d" +
//                        "wFXk2qgnkwJA")
                .build().execute(new StringCallback() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                AuthKey=response;
                Log.e("AAA",response);
            }
        });
    }

    private void test2() {
        OkHttpUtils
                .get()
                .url("https://exmail.qq.com/cgi-bin/login?fun=bizopenssologin&method=bizauth")
                .addParams("agent","gdbds")
                .addParams("user","chenguohui@gdbds.net")
                .addParams("ticket","AuthKey")
                .build().execute(new StringCallback() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                String mailid=response;
                Log.e("AAA",response);
            }
        });
    }

    private void test3() {

    }

    private void test4() {

    }


    private void initFlag() {
        // TODO Auto-generated method stub
        flag=tool.getFlag();
        if(flag==0){
            type.setChecked(true);
        }else{
            type.setChecked(false);
        }
    }

    // 初始化视图
    public void initView() {

        // register = (TextView) findViewById(R.id.register);
        // register.setOnClickListener(new Listener());
        // find = (TextView) findViewById(R.id.find);
        // find.setOnClickListener(new Listener());
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new Listener());
        // username.setCursorVisible(false);
        // password.setCursorVisible(false);

        type = (CheckBox) findViewById(R.id.type);
        // 默认个人用户
        flag = 1;

        type.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    flag = 0;
                } else {
                    flag = 1;
                }
                initLogin();

            }
        });

        if (checkNetWorkInfo()) {
            UpdateManager manager = new UpdateManager(LoginActivity.this,
                    "PersonLoginActivity", null);
            // 检查软件更新
    //        manager.checkUpdate();
        }

    }

    ConnectivityManager conMan;

    private boolean checkNetWorkInfo() {
        // TODO Auto-generated method stub
        if (conMan == null) {
            conMan = (ConnectivityManager) LoginActivity.this
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo mNetworkInfo = conMan.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    // 如果有登录资料则直接登录,否则需要输入
    public void initLogin() {
        if (tool.getPwd(flag) != null) {
            username.setText(tool.getPwd(flag).get(0));
            password.setText(tool.getPwd(flag).get(1));
        } else {
            username.setText("");
            password.setText("");
        }
    }

    /**
     * 登录方法
     * */
    int flag = 1;

    public boolean login() {

        if (flag == 1) {
            // 设为车牌号登录
            app.isLoginByVeh = true;
        } else {
            // 设为公司账户登录
            app.isLoginByVeh = false;
        }

        app.sUserName = username.getText().toString().trim();
        app.sUserPassWord = password.getText().toString().trim();

//        if(app.sUserName==null || app.sUserName.equalsIgnoreCase("")){
//            Toast.makeText(LoginActivity.this,
//                    R.string.login_check_1, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(app.sUserPassWord==null || app.sUserPassWord.equalsIgnoreCase("")){
//            Toast.makeText(LoginActivity.this,
//                    R.string.login_check_2, Toast.LENGTH_SHORT).show();
//            return false;
//        }



        // TODO 连接服务器
        if (dialog_progress == null) {
            initShowProgressDialog();
        }
        if (dialog_progress != null && !dialog_progress.isShowing()) {
            dialog_text.setText(R.string.logining);
            dialog_progress.show();
        }
        tool.savePwd(flag, app.sUserName, app.sUserPassWord);
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

        // 关闭连接


        return false;
    }
    int i = 0;
    class Listener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // 登录
            switch (v.getId()) {
                case R.id.login:
                    login();
//                    if (i == 0) {
//                        i++;
//                        test();
//                    } else if (i == 1) {
//                        i++;
//                        test1();
//                    }else if (i == 2) {
//                        i++;
//                        test2();
//                    }else if (i == 3) {
//                        i++;
//                        test3();
//                    }else if (i == 4) {
//                        i++;
//                        test4();
//                    }
                    break;
                // 注册
                // case R.id.register:
                // Intent intent = new Intent(PersonLoginActivity.this,
                // RegisterActivity.class);
                // startActivity(intent);
                // overridePendingTransition(android.R.anim.slide_in_left,
                // android.R.anim.slide_out_right);
                // break;
                // 找回密码
                // case R.id.find:
                //
                // startActivity(new Intent(PersonLoginActivity.this,
                // PersonEditActivity.class));
                // overridePendingTransition(android.R.anim.slide_in_left,
                // android.R.anim.slide_out_right);
                // break;
                default:
                    break;
            }

        }

    }

    TextView dialog_text;
    Dialog dialog_progress=null;

    /**
     *
     * @Title: showProgressDialog
     * @Description: TODO(环形进度框)
     * @return void 返回类型
     */
    void initShowProgressDialog() {
//        MyDialog.Builder dialogBuilder = new MyDialog.Builder(
//                PersonLoginActivity.this);
//        View view = getLayoutInflater().inflate(R.layout.dialog_progress, null);
//        dialogBuilder.setContentView(view);
//        dialog_progress = dialogBuilder.create();
//        dialog_text = (TextView) view.findViewById(R.id.dialog_progress_text);
//        dialog_text.setText(R.string.logining);
//        dialog_progress
//                .setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        app.dismissNomalServer();
//                    }
//                });
    }


}

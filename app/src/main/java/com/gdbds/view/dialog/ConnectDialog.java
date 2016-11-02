//package com.gdbds.view.dialog;
//
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.sky.kay.bdoa.R;
//
//public class ConnectDialog extends Dialog {
//
//	Context context;
//
//	public ConnectDialog(Context context) {
//		super(context);
//		this.context = context;
//	}
//
//	public ConnectDialog(Context context, int mydialog) {
//		super(context, mydialog);
//		this.context = context;
//	}
//
//	EditText username;
//	EditText password;
//
//	Button connect;
//	Button disConnect;
//	Button cancel;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		this.setContentView(R.layout.person_input_vehcile);
//
//		// TODO 可以做成下拉框的形式
//		username = (EditText) findViewById(R.id.connUserName);
//		password = (EditText) findViewById(R.id.connPassword);
//
//		connect = (Button) findViewById(R.id.connect);
//		connect.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				connect();
//			}
//
//		});
//		disConnect = (Button) findViewById(R.id.disConnect);
//		disConnect.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				// 发送请求信息
//				disConnect();
//			}
//
//		});
//
//		cancel = (Button) findViewById(R.id.cancel);
//		cancel.setOnClickListener(new android.view.View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				username.setText("");
//				password.setText("");
//			}
//
//		});
//
//	}
//
//	// TODO 发送关联信息
//	public void connect() {
//		if (check()) {
//
//		}
//	}
//
//	// TODO 发送取消关联信息
//	public void disConnect() {
//
//		if (check()) {
//
//		}
//
//	}
//
//	public boolean check() {
//		if (username.getText().toString().isEmpty()) {
//			Toast.makeText(context, "请输入用户名...", Toast.LENGTH_SHORT).show();
//			return false;
//		}
//		if (password.getText().toString().isEmpty()) {
//			Toast.makeText(context, "请输入密码...", Toast.LENGTH_SHORT).show();
//			return false;
//		}
//		return true;
//	}
//
//}

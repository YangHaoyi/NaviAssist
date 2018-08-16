package com.yanghaoyi.naviassist;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yanghaoyi.navimain.IRouteInterface;
import com.yanghaoyi.navimain.ITaskCallback;
/**
 * @author : YangHaoYi on 2018/8/15.
 *         Email  :  yang.haoyi@qq.com
 *         Description :AIDL传输Demo客户端
 *         Change : YangHaoYi on 2018/8/15.
 *         Version : V 1.0
 */
public class MainActivity extends AppCompatActivity {

    private static final String SERVICE_ACTION = "com.yanghaoyi.AIDLService";
    private static final String SERVICE_PACKAGE = "com.yanghaoyi.navimain";

    private IRouteInterface mStub;
    private TextView tvMessage;
    private TextView tvGetMessage;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStub = IRouteInterface.Stub.asInterface(service);
            //注册回调
            try {
                mStub.registerCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try {
                mStub.unregisterCallback(callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mStub = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        connectService();
    }

    private void init(){
        initView();
        initEvent();
    }

    private void initView(){
        tvMessage = findViewById(R.id.tvMessage);
        tvGetMessage = findViewById(R.id.tvGetMessage);
    }

    private void initEvent(){
        tvGetMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null==mStub){
                    connectService();
                }else {
                    //主动获取主屏App的数据
                    try {
                        tvMessage.setText("获取消息为："+mStub.getRouteInfo().getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void connectService(){
        Intent intent = new Intent();
        intent.setAction(SERVICE_ACTION);
        intent.setPackage(SERVICE_PACKAGE);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    //响应主屏App的回调方法
    private ITaskCallback callback = new ITaskCallback.Stub() {
        @Override
        public void callback(int code) throws RemoteException {
            try {
                tvMessage.setText("获取消息为："+mStub.getRouteInfo().getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

}

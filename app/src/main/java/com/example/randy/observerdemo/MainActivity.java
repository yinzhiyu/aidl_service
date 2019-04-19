package com.example.randy.observerdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CalculateInterface.Stub binder = null;
    private static final String TAG = "aidl";
    private static final int POOL_SIZE = 6;
    private static final int[] sOwnedPool = new int[POOL_SIZE];
    private CalculateInterface mService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = CalculateInterface.Stub.asInterface(service);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sOwnedPool[2] = 1;
//        conn = new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//                binder = (CalculateInterface.Stub) iBinder;
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName componentName) {
//
//            }
//        };
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }


    public void CodeTest(View view) {
        obtain();
    }

    public static int obtain() {
        final int[] pool = sOwnedPool;
        synchronized (pool) {
            int p;
            for (int i = 0; i < POOL_SIZE; i++) {
                p = pool[i];
                if (p != 0) {
                    pool[i] = 0;
                    Log.d(TAG, "结果：" + p);
                    return p;
                }
            }
        }
        Log.d(TAG, "结果：" + 4);
        return 4;
    }

    public void jniDemo(View view) {

    }

}

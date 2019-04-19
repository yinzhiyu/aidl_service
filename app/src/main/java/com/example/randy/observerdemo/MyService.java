package com.example.randy.observerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //return MyBinder通过ServiceConnection在activity中拿到MyBinder
//        return new MyBinder();
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private final CalculateInterface.Stub mBinder = new CalculateInterface.Stub() {

        @Override
        public double doCalculate(double a, double b) throws RemoteException {
            // TODO Auto-generated method stub
            Log.e("Calculate", "远程计算中");
            Calculate calculate = new Calculate();
            double answer = calculate.calculateSum(a, b);
            return answer;
        }
    };
}

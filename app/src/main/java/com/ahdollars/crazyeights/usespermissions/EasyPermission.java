package com.ahdollars.crazyeights.usespermissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Shashank on 05-10-2016.
 */

public class EasyPermission {

    public static int REQUEST_CODE=101;
    private static Context context;
    private static OnRequestSimplePermission request;

    public interface OnRequestSimplePermission{
        void onSimplePermissionGranted();
        void OnSimplePermissionDenied();
    }

    public static void askSimplePermission(Activity activity,String permission,int REQUEST_CODE, OnRequestSimplePermission request){
        EasyPermission.request=request;
        EasyPermission.REQUEST_CODE=REQUEST_CODE;
        int p= ContextCompat.checkSelfPermission(activity, permission);
        // if we have permission in manifest then it simply return true or 1
        //but if permision not explicitly given in manifest then it returns false or 0
        //works on all android old or new
        if(p== PackageManager.PERMISSION_GRANTED){
            request.onSimplePermissionGranted();
        }else{
            //this check is running asyncronously
            //so check program
            //use onRequestPermissionResullt
            ActivityCompat.requestPermissions(activity,new String[]{permission},REQUEST_CODE);
        }
    }

    public  static  void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(REQUEST_CODE==requestCode && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            EasyPermission.request.onSimplePermissionGranted();
        }else{
            EasyPermission.request.OnSimplePermissionDenied();
        }
    }

}

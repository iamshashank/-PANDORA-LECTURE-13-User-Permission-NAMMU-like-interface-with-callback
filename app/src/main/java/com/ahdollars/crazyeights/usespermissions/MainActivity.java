package com.ahdollars.crazyeights.usespermissions;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import pl.tajchert.nammu.Nammu;

public class MainActivity extends AppCompatActivity {

    Button check;
    TextView status;
   // public static final int REQUEST_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Nammu.init(MainActivity.this);
        setContentView(R.layout.activity_main);
        check=(Button)findViewById(R.id.check);
        status=(TextView)findViewById(R.id.status);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
               // for greater than M we have to check for permission explicitly before doing somthing
                if (Build.VERSION.SDK_INT >= M) {
                //    checkSelfPermission();
                    // 23 and above can directly use the function
                }
                //check if the passed permission is available or not
                int p=ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                // if we have permission in manifest then it simply return true or 1
                //but if permision not explicitly given in manifest then it returns false or 0
                //works on all android old or new
                if(p== PackageManager.PERMISSION_GRANTED){
                  myTask();
                }else{
                    //this check is running asyncronously
                    //so check program
                    //use onRequestPermissionResullt
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
                }
*/

                //now NAMMU library
       /*         Nammu.askForPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                    @Override
                    public void permissionGranted() {
                        myTask();
                    }

                    @Override
                    public void permissionRefused() {
                        Toast.makeText(getApplicationContext(),"NO PERMISSION",Toast.LENGTH_LONG).show();
                    }
                });  */

                EasyPermission.askSimplePermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 10, new EasyPermission.OnRequestSimplePermission() {
                    @Override
                    public void onSimplePermissionGranted() {
                        myTask();
                    }

                    @Override
                    public void OnSimplePermissionDenied() {
                        Toast.makeText(MainActivity.this,"NO EASY PERMISSION",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    public void myTask(){
        File checkfile=new File(Environment.getExternalStorageDirectory(),"gtaforfun");
        try {
            checkfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        status.setText("Exists");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Nammu.onRequestPermissionsResult(requestCode,permissions,grantResults);
  /*      if(REQUEST_CODE==requestCode && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            myTask();
        }else{
            Toast.makeText(getApplicationContext(),"NO PERMISSION",Toast.LENGTH_LONG).show();
        }
        */
        //with nammu we do not line below
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);



        EasyPermission.onRequestPermissionResult(requestCode,permissions,grantResults);
    }
}

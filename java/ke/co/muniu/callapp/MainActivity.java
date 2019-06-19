package ke.co.muniu.callapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL=1;
    //A positive integer required to request for permission for call.

    private EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeACall();
            }

            private void makeACall() {
                button = (Button) findViewById(R.id.button);
                String number = editText.getText().toString();


                if (number.trim().length() > 0) {
                    //This will check if the number entered is not null

                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                        //This will check if the permission is requested or not.if not it will request permission

                    } else {
                        String dial = "tel:" + number;
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                        startActivity(intent);
                        //this intent will make the phone call with the dialed no.
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                    //if user calls without entering any number it will tell him/her to "enter number".
                }

            }

                      public void onRequestPermissionResult (int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
                           MainActivity.super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                           makeACall();
                }


        });
    }
}

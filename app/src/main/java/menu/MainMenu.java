package menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.access_control.ChangePassword;
import com.example.access_control.LoginMain;
import com.example.access_control.MyLoginModel;
import com.example.access_control.MySessionModel;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import measurements.Measurements;
import pl.grupa33inf.ssi.R;

public class MainMenu extends AppCompatActivity {
    private int userRole;
    private String id = "0";
    /*
    @Override
    protected void onResume() {
        super.onResume();
        readJsonFromFile();
        if(id == "1"){
            Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: ADMIN", Toast.LENGTH_SHORT).show();
            userRole = 1;
        }
        else if(id == "2"){
            Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: UŻYTKOWNIK", Toast.LENGTH_SHORT).show();
            userRole = 2;
        }
        else{
            // TODO: implement something to avoid this
            Toast.makeText(getApplicationContext(), "To nie powinno się nigdy stać", Toast.LENGTH_SHORT).show();
        }
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Configure action bar
        Toolbar actionbar = findViewById(R.id.main_menu_action_bar);
        actionbar.setTitle(getResources().getString(R.string.MAIN_MENU));
        setSupportActionBar(actionbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        // Get date from previous activity
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.userRole = extras.getInt("role");
            if(userRole == 1)
                Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: ADMIN", Toast.LENGTH_SHORT).show();
            else if(userRole == 2)
                Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: UŻYTKOWNIK", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: implement something to avoid this
            Toast.makeText(getApplicationContext(), "To nie powinno się nigdy stać", Toast.LENGTH_SHORT).show();
        }

        /*
        readJsonFromFile();
        if(id == "1"){
            Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: ADMIN", Toast.LENGTH_SHORT).show();
            userRole = 1;
        }
        else if(id == "2"){
            Toast.makeText(getApplicationContext(), "Zalogowałeś się jako: UŻYTKOWNIK", Toast.LENGTH_SHORT).show();
            userRole = 2;
        }
        else{
            // TODO: implement something to avoid this
            Toast.makeText(getApplicationContext(), "To nie powinno się nigdy stać", Toast.LENGTH_SHORT).show();
        }
        */
        Button measurements = findViewById(R.id.measurements_main_menu_button);
        Button authorization = findViewById(R.id.authorization_main_menu_button);
        Button updateFirmware = findViewById(R.id.update_firmware_main_menu_button);
        Button settings = findViewById(R.id.settings_main_menu_button);
        //Button logout = findViewById(R.id.log_out_1);

        measurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Measurements.class);
                startActivity(i);
            }
        });

        authorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChangePassword.class);
                intent.putExtra("USER_ID", String.valueOf(userRole));
                startActivity(intent);
            }
        });

        updateFirmware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO: activity for firmware update
                if(userRole == 1) {//if user is admin then allow him to update firmware
                    Toast.makeText(getApplicationContext(), "TODO: activity for firmware update", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(v.getContext(), Measurements.class);
                    //startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "User is not admin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Settings.class);
                startActivity(i);
            }
        });

        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginMain.class);
                EndSession();
                startActivity(intent);
            }
        });*/
    }

    public void readJsonFromFile(){
        //method to put json file into InputStream of json parser (public string inputStreamToString())
        File file = new File(this.getFilesDir(), "session_information.json");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String myJson = inputStreamToString(fileInputStream);//parsing json file using method inputStreamToString
        MySessionModel myModel = new Gson().fromJson(myJson, MySessionModel.class);//converting json string to model
        id = myModel.list.get(0).id_;//assigning first id value from model to variable
        //String cert = myModel.list.get(0).certificate_;
    }
    public String inputStreamToString(InputStream inputStream) {
        //method to read and parse json file and later save it to a model
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        }
        catch (IOException e) {
            return null;
        }
    }
    private void EndSession(){
        String certificate = "";
        File file = new File(this.getFilesDir(), "session_information.json");
        String filename = "session_information.json";
        String fileContents = "{\n" +
                "  \"Session_Info:\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"ID\": \""+0+"\",\n" +
                "      \"Certificate\": \"No_User\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

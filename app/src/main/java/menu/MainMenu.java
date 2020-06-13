package menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.access_control.ChangePassword;

import measurements.Measurements;
import pl.grupa33inf.ssi.R;

public class MainMenu extends AppCompatActivity {
    private int userRole;

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
        Bundle extras = getIntent().getExtras();
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

        Button measurements = findViewById(R.id.measurements_main_menu_button);
        Button authorization = findViewById(R.id.authorization_main_menu_button);
        Button updateFirmware = findViewById(R.id.update_firmware_main_menu_button);
        Button settings = findViewById(R.id.settings_main_menu_button);

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
                Toast.makeText(getApplicationContext(), "TODO: activity for firmware update", Toast.LENGTH_SHORT).show();
//                TODO: activity for firmware update
//                Intent i = new Intent(v.getContext(), Measurements.class);
//                startActivity(i);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "TODO: activity for settings", Toast.LENGTH_SHORT).show();
//                TODO: activity for settings
//                Intent i = new Intent(v.getContext(), Measurements.class);
//                startActivity(i);
            }
        });
    }
}

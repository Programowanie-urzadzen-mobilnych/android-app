package menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.representation.Utils;

import pl.grupa33inf.ssi.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Utils.isDark)
            setTheme(com.representation.R.style.DarkTheme);
        else
            setTheme(com.representation.R.style.LightTheme);
        setContentView(R.layout.activity_settings);

        Toolbar actionbar = findViewById(R.id.settings_action_bar);
        actionbar.setTitle(getResources().getString(R.string.SETTINGS_ACTION_BAR_TITLE));
        setSupportActionBar(actionbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Switch darkThemeSwitch = findViewById(R.id.dark_theme);
        darkThemeSwitch.setChecked(Utils.isDark);

        darkThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State", ""+isChecked);
                Utils.isDark = isChecked;
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(Utils.KEY, isChecked);
                editor.apply();
            }
        });
    }
}
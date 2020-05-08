package pl.grupa33inf.ssi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.access_control.LoginMain;

import measurements.Measurements;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = new Intent(this, LoginMain.class);
		startActivity(i);
	}
}

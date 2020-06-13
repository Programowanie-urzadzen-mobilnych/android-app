package pl.grupa33inf.ssi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.access_control.LoginMain;

import menu.MainMenu;

public class MainActivity extends AppCompatActivity {
	private final int CONNECTION_REQUEST_CODE = 0;
	private final int LOGIN_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView message = findViewById(R.id.message_text_view);
		message.setText(R.string.NO_CONNCTION_TO_THE_MEASURING_NODE);

		Button reconnect = findViewById(R.id.reconnect_button);
		reconnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				TODO: Bluetooth team should implement Activity for ConnectionValidation. Activity should
//				 check whether bluetooth is enabled and also check whether phone is connected to valid
//				 device. If it's valid activity should return result to this activity so this activity could
//				 recognise if everything is ok with connection
//				Intent i = new Intent(this, com.bluetooth.ConnectionValidation.class);
//				startActivityForResult(i, CONNECTION_REQUEST_CODE);

				// Following is temporary - until bluetooth team do the upper thing.
				Intent i = new Intent(v.getContext(), LoginMain.class);
				startActivityForResult(i, LOGIN_REQUEST_CODE);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
			case CONNECTION_REQUEST_CODE:
				if(resultCode == RESULT_OK){
					boolean valid = data.getBooleanExtra("valid", false);
					if(valid){
						Intent i = new Intent(this, LoginMain.class);
						startActivityForResult(i, LOGIN_REQUEST_CODE);
					} else {
						Toast.makeText(getApplicationContext(), "Wystąpił błąd w trakcie weryfikacji połączenia z węzłem pomiarowym", Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case LOGIN_REQUEST_CODE:
				if (resultCode == RESULT_OK) {
					int role = data.getIntExtra("role", 3);
					if(role == 3) {
						Toast.makeText(getApplicationContext(), "Wystąpił błąd w trakcie logowania", Toast.LENGTH_SHORT).show();
					} else {
						Intent i = new Intent(this, MainMenu.class);
						i.putExtra("role", role);
						startActivity(i);
					}
				}
				break;
			default:
				Toast.makeText(getApplicationContext(), "To nie powinno się nigdy stać", Toast.LENGTH_SHORT).show();
				break;
		}
	}
}

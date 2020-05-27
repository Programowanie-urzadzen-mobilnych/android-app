package pl.grupa33inf.ssi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Date;

import pl.grupa33inf.ssi.data_store.api.DataStoreApi;
import pl.grupa33inf.ssi.data_store.api.EntryType;
import pl.grupa33inf.ssi.data_store.api.ILogDatastore;
import pl.grupa33inf.ssi.data_store.api.InfoLogEntry;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}

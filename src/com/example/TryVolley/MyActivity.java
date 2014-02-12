package com.example.TryVolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.volley.*;

public class MyActivity extends Activity {

	private Button getDataBtn;
	private RequestQueue requestQueue;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Log.i(this.toString(), "in oncreate method");

		getDataBtn = (Button) findViewById(R.id.getDataBtn);
		getDataBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                if (1==1) {

                    Intent intent = new Intent(MyActivity.this, SecondActivity.class);
                    MyActivity.this.startActivity(intent);

                    return;
                }
			}
		});
	}
}

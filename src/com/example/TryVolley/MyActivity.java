package com.example.TryVolley;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends Activity {

    private Button getDataBtn;
    private RequestQueue requestQueue;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        Log.i(this.toString(), "in oncreate method");

        getDataBtn = (Button)findViewById(R.id.getDataBtn);
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (requestQueue == null)
                    requestQueue = Volley.newRequestQueue(getApplicationContext());


                requestQueue.add(new JsonObjectRequest(Method.GET,"xxxx",  null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        //System.out.println(" response = " + response);
                        JSONObject responseJson = (JSONObject)response;
                        JSONArray topicJson = responseJson.optJSONArray("topic");
                        if (topicJson != null) {
                            for (int i = 0; i < topicJson.length(); i++) {
                                try {
                                    JSONObject currentJsonOb = topicJson.getJSONObject(i);
                                    System.out.println(" topic title = " + currentJsonOb.optString("title"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(" on error response = " + error.toString());
                    }
                }));
                requestQueue.start();
            }
        });
	}
}

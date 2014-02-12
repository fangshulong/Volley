package com.example.TryVolley;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA. User: lingyfh Date: 14-2-12 Time: 上午11:53 To
 * change this template use File | Settings | File Templates.
 */
public class SecondActivity extends Activity {

	private RequestQueue requestQueue = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		requestQueue = Volley.newRequestQueue(SecondActivity.this);

		// request.setTag(SecondActivity.this);

		JsonObjectRequest request1 = createJsonObjectRequest(1);
		JsonObjectRequest request2 = createJsonObjectRequest(2);
		JsonObjectRequest request3 = createJsonObjectRequest(3);

		requestQueue.add(request1);
		requestQueue.add(request2);
		requestQueue.add(request3);

		System.out.println(" requeset tag === " + request1.getTag());
		System.out.println(" requeset tag === " + request2.getTag());
		System.out.println(" requeset tag === " + request3.getTag());

		try {
			// Thread.sleep(200);
			//SecondActivity.this.finish();
		} catch (Exception e) {

		}

		Button getDataBtn = (Button) findViewById(R.id.getDataBtn);
		getDataBtn.setText("The Seconed GetData");
		getDataBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	private JsonObjectRequest createJsonObjectRequest(int skip) {
		String requestURL = "xxxx"
				+ "?skip=" + skip + "&limit=10";
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
				requestURL, null, new Response.Listener() {
					@Override
					public void onResponse(Object response) {
						// System.out.println(" response = " +
						// response);
						JSONObject responseJson = (JSONObject) response;
						JSONArray topicJson = responseJson
								.optJSONArray("topic");
						if (topicJson != null) {
							for (int i = 0; i < topicJson.length(); i++) {
								try {
									JSONObject currentJsonOb = topicJson
											.getJSONObject(i);
									System.out.println(" topic title = "
											+ currentJsonOb.optString("title"));
								} catch (JSONException e) {
									e.printStackTrace();
								}

							}
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println(" on error response = "
								+ error.toString());
					}
				});
		return request;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
        /*requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                Object tag = request.getTag();
                if (tag != null && tag.equals(SecondActivity.this))
                    return true;
                System.out.println(" apply in on apply in on destroy  request = " + request);
                return true;
            }
        });*/
	}

	@Override
	protected void onStop() {
		super.onStop();
		requestQueue.cancelAll(new RequestQueue.RequestFilter() {
			@Override
			public boolean apply(Request<?> request) {
                System.out.println(" apply in on apply on stop");
                if (request.getTag().equals(SecondActivity.this))
                    return true;
				System.out.println(" apply in on apply ");
				return false;
			}
		});
	}
}

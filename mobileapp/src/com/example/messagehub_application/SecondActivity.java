package com.example.messagehub_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.messagehub_application.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends Activity {	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Button bt = (Button)findViewById(R.id.back);
		bt.setOnClickListener(listener);
		sendGetRequest();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}
	private OnClickListener listener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(SecondActivity.this, MainActivity.class);
			SecondActivity.this.startActivity(intent);
		}
	};
	private void sendGetRequest(){
		class SendGetReqAsyncTask extends AsyncTask<String, Void, String>{
		private ArrayList<String> messages = new ArrayList();
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet("http://10.0.2.2:3000/");
	        try {
				HttpResponse httpResponse = httpClient.execute(httpGet);

				InputStream inputStream = httpResponse.getEntity().getContent();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();

                String bufferedStrChunk = null;

                while((bufferedStrChunk = bufferedReader.readLine()) != null){
                    stringBuilder.append(bufferedStrChunk);
                }
                String data = stringBuilder.toString();
                Scanner sc = new Scanner(data);
                while(sc.hasNext()){
                	sc.useDelimiter("message_username");
                    sc.next();
                    sc.useDelimiter(">");
                    sc.next();
                    sc.useDelimiter("<");
                    String username_origin = sc.next();
                    String username = username_origin.substring(1, username_origin.length());
                    sc.useDelimiter(">");
                    sc.next();
                    sc.next();
                    sc.useDelimiter("<");
                    String content_origin = sc.next();
                    String content = content_origin.substring(1,content_origin.length());
                    sc.useDelimiter(">");
                    sc.next();
                    sc.next();
                    sc.useDelimiter("<");
                    String id_origin = sc.next();
                    String id = id_origin.substring(1, id_origin.length());
                    sc.useDelimiter(">");
                    sc.next();
                    sc.next();
                    sc.useDelimiter("<");
                    String app_id_origin = sc.next();
                    String app_id = app_id_origin.substring(1,app_id_origin.length());
                    sc.useDelimiter(">");
                    sc.next();
                    sc.next();
                    sc.useDelimiter("<");
                    String time_origin = sc.next();
                    String time = time_origin.substring(1,time_origin.length());
                    String message = username +" "+content+" "+id+" "+app_id+" "+time;
                    if(id.equals("1")){
                    	break;
                    }
                    messages.add(message);
                }
                return stringBuilder.toString();

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			for(int i = 0; i < messages.size() ; i++){
				TextView textview = new TextView(SecondActivity.this);
				textview.setText(messages.get(i));
				textview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
				System.out.println(i);
				View layout = findViewById(R.id.secondlayout);
				((LinearLayout)layout).addView(textview);
			}
		}
		}
		SendGetReqAsyncTask sendGetReqAsyncTask = new SendGetReqAsyncTask();
		sendGetReqAsyncTask.execute();
		
	}
	public LinearLayout findViewById(id id) {
		// TODO Auto-generated method stub
		return null;
	};
}

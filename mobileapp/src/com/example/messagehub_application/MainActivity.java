package com.example.messagehub_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et1;
	private EditText et2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1 = (EditText)findViewById(R.id.username);
		et2 = (EditText)findViewById(R.id.message);
		Button bt1 = (Button)findViewById(R.id.submit);
		bt1.setOnClickListener(listener);
		Button bt2 = (Button)findViewById(R.id.center);
		bt2.setOnClickListener(transfer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private OnClickListener transfer = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SecondActivity.class);
			MainActivity.this.startActivity(intent);

		}
		
	};
	private OnClickListener listener = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			sendPostRequest(et1.getText().toString(),et2.getText().toString());
		}
		
	};
	
	private void sendPostRequest(String username, String message){
		class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{
			@Override
	        protected String doInBackground(String... params) {

	            String username = params[0];
	            String message = params[1];

	            HttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost("http://10.0.2.2:3000/messages");

	            BasicNameValuePair usernameBasicNameValuePair = new BasicNameValuePair("message[username]", username);
	            BasicNameValuePair messageBasicNameValuePAir = new BasicNameValuePair("message[content]", message);
	            BasicNameValuePair appidBasicNameValuePAir = new BasicNameValuePair("message[app_id]", "1");

	            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
	            nameValuePairList.add(usernameBasicNameValuePair);
	            nameValuePairList.add(messageBasicNameValuePAir);
	            nameValuePairList.add(appidBasicNameValuePAir);

	            try {
	                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

	                httpPost.setEntity(urlEncodedFormEntity);

	                try {
	                    HttpResponse httpResponse = httpClient.execute(httpPost);

	                    InputStream inputStream = httpResponse.getEntity().getContent();

	                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

	                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	                    StringBuilder stringBuilder = new StringBuilder();

	                    String bufferedStrChunk = null;

	                    while((bufferedStrChunk = bufferedReader.readLine()) != null){
	                        stringBuilder.append(bufferedStrChunk);
	                    }
	                    //System.out.println(stringBuilder.toString());
	                    
	                    return stringBuilder.toString();

	                } catch (ClientProtocolException cpe) {
	                    System.out.println("First Exception caz of HttpResponese :" + cpe);
	                    cpe.printStackTrace();
	                } catch (IOException ioe) {
	                    System.out.println("Second Exception caz of HttpResponse :" + ioe);
	                    ioe.printStackTrace();
	                }

	            } catch (UnsupportedEncodingException uee) {
	                System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
	                uee.printStackTrace();
	            }

	            return null;
	        }
			protected void onPostExecute(String result) {
				if(!result.equals("")){
                  	Toast.makeText(getBaseContext(), "successfully submitted", Toast.LENGTH_LONG).show();
                }
		      }
        
	    }

	    SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
	    sendPostReqAsyncTask.execute(username,message);
	}

}

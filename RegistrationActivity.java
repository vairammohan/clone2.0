package com.kalvi.elaptopsale;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText input_firstname,input_secondname,input_email,input_password,input_passwordr,input_Mobile,input_address,input_city;
    Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        input_firstname=findViewById(R.id.input_firstname);
        input_secondname=findViewById(R.id.input_secondname);
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        input_passwordr=findViewById(R.id.input_passwordr);
        input_Mobile=findViewById(R.id.input_Mobile);
        input_address=findViewById(R.id.input_address);
        input_city=findViewById(R.id.input_City);
        btn_signup=findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname=input_firstname.getText().toString();
                String secondname=input_secondname.getText().toString();
                String email=input_email.getText().toString();
                String password=input_password.getText().toString();
                String password2=input_passwordr.getText().toString();
                String mobile=input_Mobile.getText().toString();
                String address=input_address.getText().toString();
                String city=input_city.getText().toString();

                if(firstname.isEmpty()||secondname.isEmpty()||email.isEmpty()||mobile.isEmpty()||password.isEmpty()||password2.isEmpty()||password2.isEmpty()||address.isEmpty()||city.isEmpty()){
                    Message.message(getBaseContext(),"please fill all data");
                }else if(!(password.equalsIgnoreCase(password2))){
                    Message.message(getBaseContext(),"Passwords are not match");
                }else{
                    new SendPostRequest().execute();
                        /*Message.message(getApplicationContext(),"Insertion Successful");
                        Intent intent=new Intent(RegistrationActivity.this,DpUpload.class);
                        //intent.putExtra("UID",helper.getUserName(username,password,usertype));
                       // intent.putExtra("PT",1);
                        startActivity(intent);
                        finish();*/
                }
            }

        });

    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(URLs.register); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("fname", input_firstname.getText().toString());
                postDataParams.put("lname", input_secondname.getText().toString());
                postDataParams.put("password", input_password.getText().toString());
                postDataParams.put("email", input_email.getText().toString());
                postDataParams.put("mobileno", input_Mobile.getText().toString());
                postDataParams.put("address", input_address.getText().toString());
                postDataParams.put("city", input_city.getText().toString());
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject=new JSONObject(result);
                if(jsonObject.getJSONObject("result").getBoolean("status")){
                    Toast.makeText(getApplicationContext(),jsonObject.getJSONObject("result").getString("message")+jsonObject.getString("regid").toString(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this,DpUpload.class);
                    intent.putExtra("UID",jsonObject.getString("regid").toString());
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(),jsonObject.getJSONObject("result").getString("message"),Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}

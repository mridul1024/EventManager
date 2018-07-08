package com.example.mridul.eventmanager;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Mridul on 1/15/2018.
 */
public class BackgroundActivity extends AsyncTask<String, Void, String> {

    Context context;

    public BackgroundActivity(Context c){
        this.context = c;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://192.168.43.2/testText/register.php";
        String cevent_url = "http://192.168.43.2/testText/createEvent.php";
        String revent_url = "http://192.168.43.2/testText/removeEvent.php";

        String method = params[0];
        if(method.equals("register")){

            String name = params[1];
            String contact = params[2];
            String email = params[3];
            String pass = params[4];


            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);

                OutputStream o = httpUrlConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(o,"UTF-8"));
                String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("cont","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"+
                        URLEncoder.encode("em","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                o.close();

                InputStream i = httpUrlConnection.getInputStream();
                i.close();

                return "Successful reg";



            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

             catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(method.equals("createEvent")){


            String event_name = params[1];
            String event_description = params[2];
            String event_venue = params[3];
            String event_category = params[4];
            String pic = params[5];
            String uid2 = params[6];



            try {
                URL url2 = new URL(cevent_url);

                HttpURLConnection httpUrlConnection2 = (HttpURLConnection) url2.openConnection();
                httpUrlConnection2.setRequestMethod("POST");
                httpUrlConnection2.setDoOutput(true);

                OutputStream o2 = httpUrlConnection2.getOutputStream();

                BufferedWriter bufferedWriter2 = new BufferedWriter (new OutputStreamWriter(o2,"UTF-8"));
                String data2 = URLEncoder.encode("ename","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8")+"&"+
                        URLEncoder.encode("edesc","UTF-8")+"="+URLEncoder.encode(event_description,"UTF-8")+"&"+
                        URLEncoder.encode("even","UTF-8")+"="+URLEncoder.encode(event_venue,"UTF-8")+"&"+
                        URLEncoder.encode("ecat","UTF-8")+"="+URLEncoder.encode(event_category,"UTF-8")+"&"+
                        URLEncoder.encode("epic","UTF-8")+"="+URLEncoder.encode(pic,"UTF-8")+"&"+
                        URLEncoder.encode("euid2","UTF-8")+"="+URLEncoder.encode(uid2,"UTF-8");
                bufferedWriter2.write(data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                o2.close();

                InputStream i2 = httpUrlConnection2.getInputStream();
                i2.close();

                return "Successful creation of event";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

//remove event block
        if(method.equals("removeEvent")){


            String event_name = params[1];
            String uid2 = params[2];



            try {
                URL url3 = new URL(revent_url);

                HttpURLConnection httpUrlConnection3 = (HttpURLConnection) url3.openConnection();
                httpUrlConnection3.setRequestMethod("POST");
                httpUrlConnection3.setDoOutput(true);

                OutputStream o3 = httpUrlConnection3.getOutputStream();

                BufferedWriter bufferedWriter3 = new BufferedWriter (new OutputStreamWriter(o3,"UTF-8"));
                String data3 = URLEncoder.encode("ename","UTF-8")+"="+URLEncoder.encode(event_name,"UTF-8")+"&"+
                        URLEncoder.encode("euid2","UTF-8")+"="+URLEncoder.encode(uid2,"UTF-8");
                bufferedWriter3.write(data3);
                bufferedWriter3.flush();
                bufferedWriter3.close();
                o3.close();

                InputStream i3 = httpUrlConnection3.getInputStream();
                i3.close();

                return "Successful removal of event";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        //remove block ends

        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
    }
}

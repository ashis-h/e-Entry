package com.example.user.e_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {

    RequestQueue requestQueue;
    Bundle b;
    TextView tid,tname,tdesi,tnum,te_id,te_name,te_desi,te_num;

    //Button button;
    String emp_id,etname,tdesignation,tmobileNo,url="http://192.168.0.106/detailes.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        b=getIntent().getExtras();


        te_id=findViewById(R.id.e_id);
        te_name=findViewById(R.id.e_name);
        te_desi=findViewById(R.id.e_desi);
        te_num=findViewById(R.id.e_num);
        tid=findViewById(R.id.id);
        tname=findViewById(R.id.name);
        tdesi=findViewById(R.id.designation);
        tnum=findViewById(R.id.mobileNo);
        //error=findViewById(R.id.err);
        //message=findViewById(R.id.et);
        //btn=findViewById(R.id.btn);
        //message.setText(null);
        requestQueue= Volley.newRequestQueue(this);
        sendJsonRequest();
    }


    public void sendJsonRequest(){
        emp_id=b.getString("id");
        StringRequest strRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                te_id.setText(emp_id);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getString("error").toString()!=null){
                        tname.setText(jsonObject.getString("error"));
                        tdesi.setText("");
                        tnum.setText("");
                        //btn.setEnabled(false);
                        //error.setText(jsonObject.getString("error").toString());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    etname = jsonObject.getString("Name").toString();
                    te_name.setText(etname);
                    tdesignation= jsonObject.getString("Designation").toString();
                    te_desi.setText(tdesignation);
                    tmobileNo = jsonObject.getString("Mobile No").toString();
                    te_num.setText(tmobileNo);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Details.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Details.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("tag", emp_id);
                return params;
            }
        };

        requestQueue.add(strRequest);
    }


}

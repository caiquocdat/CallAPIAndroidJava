package com.example.hocapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hocapi.adapter.UserAdapter;
import com.example.hocapi.api.ApiService;
import com.example.hocapi.model.Address;
import com.example.hocapi.model.Currence;
import com.example.hocapi.model.MyResponse;
import com.example.hocapi.model.MyResponseDynamic;
import com.example.hocapi.model.Post;
import com.example.hocapi.model.User;
import com.example.hocapi.model.jobs;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView termTv;
    private TextView usdToVndTv,post_resultTv;
    private Button callApiBtn;
    private RecyclerView userRv;
    private List<Post> mListUser,mListUserCopy;
    private SearchView searchView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        actionBar=getSupportActionBar();
        actionBar.setTitle("List users");
//        callApiBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                callAPI();
////                callAPI1();
//                callAPI2();
//                sendPosts();
//            }
//        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        userRv.setLayoutManager(linearLayoutManager);
        // Chỉnh khoảng cách giữa cách rv
        DividerItemDecoration itemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        userRv.addItemDecoration(itemDecoration);
        callAPIgetUser();
//        callAPI();
//        callAPIDynamic(1);
        jobs job1= new jobs("Now");
        jobs job2= new jobs("Grap");
        jobs job3= new jobs("Milk Tea");
        Address address = new Address(1,"GiaLai");
        ArrayList<jobs> jobs= new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        User user= new User(1,"qdat",address,jobs);
        // cách convert object sang JSON viết hàm
//        Log.e("caiquocdat",getStringJson(user));
        // cách conver object sang JSON bằng thư viện GSON
        Log.e("caiquocdat",user.toJSON());
    }
    private String getStringJson(User user){
        String result="";

        try {
            JSONObject jsonObjectTotal=new JSONObject();
            jsonObjectTotal.put("id",user.getId());
            jsonObjectTotal.put("name",user.getName());
            JSONObject jsonObjectAddress=new JSONObject();
            jsonObjectAddress.put("id",user.getAddress().getId());
            jsonObjectAddress.put("location",user.getAddress().getLocation());
            jsonObjectTotal.put("address",jsonObjectAddress);
            JSONArray jsonArrayJobs=new JSONArray();
            for (jobs jobs:user.getJobs()){
                JSONObject jsonObjectJob= new JSONObject();
                jsonObjectJob.put("jobName", jobs.getJobName());
                jsonArrayJobs.put(jsonObjectJob);
            }
            jsonObjectTotal.put("jobs",jsonArrayJobs);
            result =jsonObjectTotal.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    private void callAPIDynamic(int id){
        ApiService.apiService.callApiDynamic(id).enqueue(new Callback<MyResponseDynamic>() {
            @Override
            public void onResponse(Call<MyResponseDynamic> call, Response<MyResponseDynamic> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                MyResponseDynamic myResponse=response.body();
                Log.e("CaiQuocDat",myResponse.toString());
            }

            @Override
            public void onFailure(Call<MyResponseDynamic> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void callAPI(){
        ApiService.apiService.callApiExcample().enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                MyResponse myResponse=response.body();
                if (myResponse!=null){
//                    Toast.makeText(MainActivity.this, ""+myResponse.toString(), Toast.LENGTH_SHORT).show();
                    Log.e("CaiQuocDat",myResponse.toString());
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void callAPIgetUser(){
        ApiService.apiService.getListUser1(1).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                mListUser= new ArrayList<>();
                mListUser=response.body();
                UserAdapter userAdapter= new UserAdapter(mListUser);
                userRv.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPosts(){
        Post post= new Post(10,102,"CaiQuocDat","Dat ne");
        ApiService.apiService.sendPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                Post postResult=response.body();
                if (postResult!=null){
                    post_resultTv.setText(postResult.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void callAPI() {
//        //Link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
//        ApiService.apiService.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6"
//        ,"VND","USD",1).enqueue(new Callback<Currence>() {
//            @Override
//            public void onResponse(Call<Currence> call, Response<Currence> response) {
//                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
//                Currence currence=response.body();
//                if (currence!=null ){
//
//
//                    termTv.setText(currence.getTerms());
//                    usdToVndTv.setText(String.valueOf(currence.getQuotes().getUSDVND()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Currence> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    private void callAPI1() {
//        //Link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
//        ApiService.apiService.convertUsdToVnd1().enqueue(new Callback<Currence>() {
//            @Override
//            public void onResponse(Call<Currence> call, Response<Currence> response) {
//                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
//                Currence currence = response.body();
//                if (currence != null) {
//
//
//                    termTv.setText(currence.getTerms());
//                    usdToVndTv.setText(String.valueOf(currence.getQuotes().getUSDVND()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Currence> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
//            }
//    });
        private void callAPI2() {
            //Link api: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
            Map<String,String> options= new HashMap<>();
            options.put("access_key","843d4d34ae72b3882e3db642c51e28e6");
            options.put("currencies","VND");
            options.put("source","USD");
            options.put("format","1");

            ApiService.apiService.convertUsdToVnd2(options).enqueue(new Callback<Currence>() {
                @Override
                public void onResponse(Call<Currence> call, Response<Currence> response) {
                    Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                    Currence currence = response.body();
                    if (currence != null) {


                        termTv.setText(currence.getTerms());
                        usdToVndTv.setText(String.valueOf(currence.getQuotes().getUSDVND()));
                    }
                }

                @Override
                public void onFailure(Call<Currence> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
                }
            });


    }


    private void searchUsers(String query) {
        ApiService.apiService.getListUser1(1).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mListUser= new ArrayList<>();
                mListUserCopy=new ArrayList<>();
                mListUser=response.body();
                for (Post post:mListUser){
                    String id=String.valueOf(post.getId());
                    if (id.equals(query)){
                        mListUserCopy.add(post);
                    }

                }
                UserAdapter userAdapter= new UserAdapter(mListUserCopy);
                userRv.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item=menu.findItem(R.id.acciton_search);

        searchView= (SearchView) MenuItemCompat.getActionView(item);

        //search listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!TextUtils.isEmpty(s.trim())){
                    searchUsers(s);

                }else {
                    callAPIgetUser();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(s.trim())){

                    searchUsers(s);
                }else {
                    callAPIgetUser();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    private void anhXa() {
//        termTv=findViewById(R.id.termTv);
//        usdToVndTv=findViewById(R.id.usdToVNDTv);
//        callApiBtn=findViewById(R.id.callApiBtn);
//        post_resultTv=findViewById(R.id.post_resultTv);
        userRv=findViewById(R.id.userRv);

    }
}
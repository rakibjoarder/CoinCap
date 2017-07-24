package com.aust.rakib.coincap;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import com.badoo.mobile.util.WeakHandler;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//: Throwing OutOfMemoryError "Failed to allocate a 20 byte allocation with 0 free bytes and 3GB until OOM"
/*https://techblog.badoo.com/blog/2014/08/28/android-handler-memory-leaks/*/

public class MainActivity extends AppCompatActivity {
    private static String TAG="LOG";
    private static String URL="https://api.coinmarketcap.com/v1/ticker/";
    private static String BASE_URL="https://api.coinmarketcap.com/";
    private CoinService coinService;

    TextView t;
    static WeakHandler handler;
    private Runnable runnableCode;
    Retrofit retrofit;
    boolean flag=true;
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    AlertDialog alertDialog;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<CoinResponse> coinResponses=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"On");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking Internet Connection Permission.
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.INTERNET},1);
        }
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        coinService=retrofit.create(CoinService.class);

        //Showing progress Dialog.
        pDialog = new ProgressDialog(this,R.style.Custom);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //Calling Api Response
        coinResponseCall();

        //Will call the Api after every 2 sec
        handler = new WeakHandler();
            runnableCode = new Runnable() {
                @Override
                public void run() {
                    coinResponseCall();

                    handler.postDelayed(runnableCode,2000);
                    }
            };
        handler.post(runnableCode);

    }

    private void coinResponseCall() {
        Log.e("Log","Calling.....3..");

        Call<ArrayList<CoinResponse>>arrayListCall=coinService.coinListCall();

        arrayListCall.enqueue(new Callback<ArrayList<CoinResponse>>() {

            @Override
            public void onResponse(Call<ArrayList<CoinResponse>> call, Response<ArrayList<CoinResponse>> response) {

                if(response.code()==200)
                {
                    coinResponses=response.body();

                    pDialog.dismiss();
                    recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,coinResponses);
                    if(flag==true)
                    { LinearLayoutManager linearLayoutManager =new LinearLayoutManager(MainActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(recyclerViewAdapter);
                        flag=false;
                    }
                    recyclerView.swapAdapter(recyclerViewAdapter,false);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<CoinResponse>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"DES");
        System.exit(0);
    }
}

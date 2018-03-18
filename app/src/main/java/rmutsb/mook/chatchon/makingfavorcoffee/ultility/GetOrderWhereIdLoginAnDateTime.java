package rmutsb.mook.chatchon.makingfavorcoffee.ultility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Administrator on 15/2/2561.
 */

public class GetOrderWhereIdLoginAnDateTime extends AsyncTask<String, Void, String>{

    private Context context;

    public GetOrderWhereIdLoginAnDateTime(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("idLogin", strings[0])
                    .add("DateTimeOder", strings[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[2]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}

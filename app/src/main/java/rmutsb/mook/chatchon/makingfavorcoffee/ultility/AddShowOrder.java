package rmutsb.mook.chatchon.makingfavorcoffee.ultility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterung on 15/2/2018 AD.
 */

public class AddShowOrder extends AsyncTask<String, Void, String>{

    private Context context;

    public AddShowOrder(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("idLogin", strings[0])
                    .add("NameCoffee", strings[1])
                    .add("TypeCoffee", strings[2])
                    .add("Espresso", strings[3])
                    .add("CocoPowder", strings[4])
                    .add("Milk", strings[5])
                    .add("FrappePowder", strings[6])
                    .add("Item", strings[7])
                    .add("DateTimeOder", strings[8])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[9]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }   // doin

}   // Main Class
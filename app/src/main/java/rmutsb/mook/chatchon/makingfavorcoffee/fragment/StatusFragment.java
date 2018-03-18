package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import rmutsb.mook.chatchon.makingfavorcoffee.R;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyConstant;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyGetAllData;

/**
 * Created by masterung on 18/3/2018 AD.
 */

public class StatusFragment extends Fragment{

    private String orderDateString;

    public static StatusFragment statusInstant(String orderDateString) {

        StatusFragment statusFragment = new StatusFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Order", orderDateString);
        statusFragment.setArguments(bundle);

        return statusFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderDateString = getArguments().getString("Order");

        myLoop();

    }   // Main Method

    private void myLoop() {

//        To Do
        try {

            MyGetAllData myGetAllData = new MyGetAllData(getActivity());
            MyConstant myConstant = new MyConstant();
            String[] statusStrings = myConstant.getStatusStrings();
            myGetAllData.execute(myConstant.getUrlGetAllOrderStatus());

            String jsonString = myGetAllData.get();
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (orderDateString.equals(jsonObject.getString("Order_date"))) {

                    TextView textView = getView().findViewById(R.id.txtStatus);
                    String readedString = jsonObject.getString("Status");
                    int index = Integer.parseInt(readedString);
                    textView.setText(statusStrings[index]);

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myLoop();
            }
        }, 1000);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        return view;
    }
}

package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import rmutsb.mook.chatchon.makingfavorcoffee.CoffeeActivity;
import rmutsb.mook.chatchon.makingfavorcoffee.R;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyAlertDialog;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyConstant;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyGetAllData;

/**
 * Created by WIN10-CREATOR on 13/9/2560.
 */

public class MainFragment extends Fragment{

    private String emailString, passwordString;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    } //onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

//        SingIn Controller
        singInController();

    } //onActivityCreated

    private void singInController() {
        Button button = getView().findViewById(R.id.btnSingIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditText = getView().findViewById(R.id.edtEmail);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                emailString = emailEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (emailString.equals("") || passwordString.equals("")) {
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myDialog(getResources().getString(R.string.title_haveSpace),
                            getResources().getString(R.string.message_haveSpace));
                } else {

                    checkUserAndPass();

                }


            }   // onClick
        });
    }

    private void checkUserAndPass() {

        boolean b = true;
        MyConstant myConstant = new MyConstant();
        MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
        String[] columnStrings = myConstant.getColumUserStrings();
        String[] strings = new String[columnStrings.length];


        try {

            MyGetAllData myGetAllData = new MyGetAllData(getActivity());
            myGetAllData.execute(myConstant.getUrlGetUserString());
            String resultJSON = myGetAllData.get();
            Log.d("30octV1", "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (emailString.equals(jsonObject.getString(columnStrings[4]))) {

                    b = false;
                    for (int i1=0; i1<columnStrings.length; i1+=1) {
                        strings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d("30octV1", "strings[" + i1 + "] ==> " + strings[i1]);
                    }   // for IN

                }   // if

            }   // for OUT

            if (b) {
                myAlertDialog.myDialog(getResources().getString(R.string.title_userFalse),
                        getResources().getString(R.string.message_userFalse));
            } else if (passwordString.equals(strings[5])) {

                Toast.makeText(getActivity(), "Welcome " + strings[1], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), CoffeeActivity.class);
                intent.putExtra("Login", strings);
                getActivity().startActivity(intent);
                getActivity().finish();

            } else {
                myAlertDialog.myDialog(getResources().getString(R.string.title_passFalse),
                        getResources().getString(R.string.message_passFalse));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });


    }
}//Main Class
package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import rmutsb.mook.chatchon.makingfavorcoffee.MainActivity;
import rmutsb.mook.chatchon.makingfavorcoffee.R;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyAlertDialog;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyConstant;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyPostDataToServer;

/**
 * Created by nutchatchon 13/9/2560.
 */

public class RegisterFragment extends Fragment {

    //Explicit
    private String nameString, surnameString, phoneString, emailString, passwordString;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    } //onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ToolBar Controller
        toolBarController();

        //Save Controller
        saveController();


    } //onActivityCreated

    private void saveController() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Initial View
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText surNameEditText = getView().findViewById(R.id.edtSurname);
                EditText phoneEditText = getView().findViewById(R.id.edtphone);
                EditText eMailEditText = getView().findViewById(R.id.edtEmail);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                //Get Value From Edittext
                nameString = nameEditText.getText().toString().trim();
                surnameString = surNameEditText.getText().toString().trim();
                phoneString = phoneEditText.getText().toString().trim();
                emailString = eMailEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || surnameString.equals("") ||
                        phoneString.equals("") || emailString.equals("") ||
                        passwordString.equals("")) {
                    //Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog(getActivity());
                    myAlertDialog.myDialog(getResources().getString(R.string.title_haveSpace),
                            getResources().getString(R.string.message_haveSpace));
                } else {
                    uploadValueToServer();
                }






            }   // onClick
        });
    }

    private void uploadValueToServer() {

        try {

            MyConstant myConstant = new MyConstant();
            MyPostDataToServer myPostDataToServer = new MyPostDataToServer(getActivity());
            myPostDataToServer.execute(nameString, surnameString, phoneString, emailString, passwordString,
                    myConstant.getUrlPostUserString());

            if (Boolean.parseBoolean(myPostDataToServer.get())) {

                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void toolBarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


    }
} //Main Class

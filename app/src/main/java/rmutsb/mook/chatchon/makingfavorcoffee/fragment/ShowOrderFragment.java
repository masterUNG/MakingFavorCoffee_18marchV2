package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import rmutsb.mook.chatchon.makingfavorcoffee.R;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.AddOrderDate;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.DeleteOrder;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.GetOrderWhereIdLoginAnDateTime;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyConstant;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.ShowOrderAdapter;

/**
 * Created by Acer on 4/1/2561.
 */


public class ShowOrderFragment extends Fragment{

    private String[] loginString, idStrings;
    private String DateTimestring;
    private ListView listView;

    public static ShowOrderFragment showOrderInstance(String[] loginStrings,
                                                      String DateTimestring) {

        ShowOrderFragment showOrderFragment = new ShowOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginStrings);
        bundle.putString("DateTime", DateTimestring);
        showOrderFragment.setArguments(bundle);

        return showOrderFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        getValueFromArgument();

//        Show DateTime
        showDateTime();

//        create listview
        createlistview();

//        Confirm Controller
        confirmController();


    }//main method

    private void confirmController() {
        Button button = getView().findViewById(R.id.btnConfirmOrder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Order_date = loginString[0] + "," + DateTimestring;

                try {

                    MyConstant myConstant = new MyConstant();
                    AddOrderDate addOrderDate = new AddOrderDate(getActivity());
                    addOrderDate.execute(Order_date, myConstant.getUrlAddOrderDate());
                    String result = addOrderDate.get();
                    Log.d("18MarchV1", "Result ==> " + result);

                    if (Boolean.parseBoolean(result)) {

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentFragmentCoffee, StatusFragment.statusInstant(Order_date))
                                .commit();

                    } else {
                        Toast.makeText(getActivity(), "Error Cannot Save Order",
                                Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void createlistview() {

        listView = getView().findViewById(R.id.listViewOrder);
        MyConstant myConstant = new MyConstant();
        String tag = "15FebV2";

        try {

            GetOrderWhereIdLoginAnDateTime getOrderWhereIdLoginAnDateTime = new GetOrderWhereIdLoginAnDateTime(getActivity());
            getOrderWhereIdLoginAnDateTime.execute(loginString[0], DateTimestring,
                    myConstant.getUrlGetOrderrWhereIdLoginAnDataTime());

            String resultJSON = getOrderWhereIdLoginAnDateTime.get();
            Log.d(tag, "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);

            final String[] nameStrings = new String[jsonArray.length()];
            String[] typeStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];
            idStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("NameCoffee");
                typeStrings[i] = jsonObject.getString("TypeCoffee");
                priceStrings[i] = findPrice(jsonObject.getString("NameCoffee"));
                idStrings[i] = jsonObject.getString("id");

            }

            buildListView(nameStrings, typeStrings, priceStrings);



        } catch (Exception e) {
            e.printStackTrace();
        }



    }   // createListView

    private void buildListView(final String[] nameStrings,
                               final String[] typeStrings,
                               final String[] priceStrings) {

        TextView textView = getView().findViewById(R.id.txtTotelPrice);
        int totalInt = 0;
        for (int i=0; i<priceStrings.length; i+=1) {
            totalInt = totalInt + Integer.parseInt(priceStrings[i].trim());
        }
        textView.setText("Total: " + Integer.toString(totalInt) + " THB");


        ShowOrderAdapter showOrderAdapter = new ShowOrderAdapter(getActivity(), nameStrings,
                typeStrings, priceStrings);
        listView.setAdapter(showOrderAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("18MarchV1", "id delete ==> " + idStrings[position]);

                String[] newNameStrings = findNewName(nameStrings, nameStrings[position]);
                String[] newTypeString = findNewType(typeStrings, typeStrings[position]);
                String[] newPriceStrings = findNewPrice(priceStrings, priceStrings[position]);
                MyConstant myConstant = new MyConstant();

                try {

                    DeleteOrder deleteOrder = new DeleteOrder(getActivity());
                    deleteOrder.execute(idStrings[position], myConstant.getUrlDeleteString());


                } catch (Exception e) {
                    e.printStackTrace();
                }

                buildListView(newNameStrings, newTypeString, newPriceStrings);

            }
        });
    }

    private String[] findNewPrice(String[] priceStrings, String deleteString) {

        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i=0; i<priceStrings.length; i+=1) {

            stringArrayList.add(priceStrings[i]);

        }

        stringArrayList.remove(deleteString);

        String resultString = stringArrayList.toString();
        resultString = resultString.substring(1, resultString.length() - 1);

        return resultString.split(",");
    }

    private String[] findNewType(String[] typeStrings, String deleteString) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i=0; i<typeStrings.length; i+=1) {

            stringArrayList.add(typeStrings[i]);

        }

        stringArrayList.remove(deleteString);

        String resultString = stringArrayList.toString();
        resultString = resultString.substring(1, resultString.length() - 1);

        return resultString.split(",");
    }

    private String[] findNewName(String[] nameStrings, String deleteString) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i=0; i<nameStrings.length; i+=1) {

            stringArrayList.add(nameStrings[i]);

        }

        stringArrayList.remove(deleteString);

        String resultString = stringArrayList.toString();
        resultString = resultString.substring(1, resultString.length() - 1);

        return resultString.split(",");
    }

    private String findPrice(String nameCoffee) {
        return "25";
    }

    private void showDateTime() {

        TextView textView = getView().findViewById(R.id.txtDateTime);
        textView.setText(DateTimestring);

    }

    private void getValueFromArgument() {
        loginString = getArguments().getStringArray("Login");
        DateTimestring = getArguments().getString("DateTime");

        Log.d("15FebV1", "LoginString.leng" + loginString.length);
        Log.d("15FebV1", "DateTime ==> " + DateTimestring);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_order, container, false);
        return view;
    }//onCreateView



} //main class

package rmutsb.mook.chatchon.makingfavorcoffee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rmutsb.mook.chatchon.makingfavorcoffee.R;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.AddShowOrder;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyConstant;
import rmutsb.mook.chatchon.makingfavorcoffee.ultility.MyManager;

/**
 * Created by Acer on 6/12/2560.
 */

public class EspressoFragment extends Fragment{

    //    Explicit
    private String[] loginString;
    private RadioButton frappuccinoRadioButton, coldDrinnkRadioButton, hotDrinkRadioButton;
    private LinearLayout milkLinearLayout, frappePowderLinearLayout;
    private SeekBar espressoSeekBar, cocoSeekBar, milkSeekBar, frappeSeekBar;
    private TextView unitEspressoTextView, unitCocoTextView, unitMilkTextView, unitFrappeTextView;
    private String tag = "4JanV1";
    private String typeCoffeeString = "Cold Drink";
    private String espressoString = "10g", cocoString = "0.5g",
            milkString = "123g", frappeString = "456g";
    private String dateTimeString;

    public static EspressoFragment espressoInstance(String[] loginString){

        EspressoFragment EspressoFragment = new EspressoFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", loginString);
        EspressoFragment.setArguments(bundle);


        return EspressoFragment ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Arument
        getValueFromArument();

        //Initial View
        initialView();

//        Cold Defaule
        coldDefaule();

        //frappuccino Controller
        frappuccinoController();

        //clodDrink
        clodDrinkController();


        //hotDrinkController
        hotDrinkController();

        //Espressp seekbar
        espresspSeekbar();

        //Coco seekbar
        cocoSeekbar();

        //Milk seekbar
        milkSeekbar();

        //Frappe seekbar
        frappeSeekbar();


//        Order Controller
        orderController();

        // Get TimeDate
        getTimeDate();

    }//Main method

    private void getTimeDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateTimeString = dateFormat.format(calendar.getTime());
    }

    private void orderController() {

        final Button button = getView().findViewById(R.id.btnoder);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(tag, "idLogin ==> " + loginString[0]);
                Log.d(tag, "NameCoffee ==> " + "Espresso");
                Log.d(tag, "TypeCoffee ==> " + typeCoffeeString);
                Log.d(tag, "Espresso ==> " + espressoString);
                Log.d(tag, "CocoaPowder ==> " + cocoString);
                Log.d(tag, "Milk ==> " + milkString);
                Log.d(tag, "FrappePowder ==> " + frappeString);
                Log.d(tag, "Item ==> " + "1");
                Log.d(tag, "DateTimeOrder ==> " + dateTimeString);

                try {

                    AddShowOrder addShowOrder = new AddShowOrder(getActivity());
                    MyConstant myConstant = new MyConstant();

                    addShowOrder.execute(loginString[0], "Espresso", typeCoffeeString,
                            espressoString, cocoString, milkString,
                            frappeString, "1", dateTimeString,
                            myConstant.getUrlAddShowOrderString());

                    String result = addShowOrder.get();
                    Log.d(tag, "Result ==> " + result);

                    if (Boolean.parseBoolean(result)){


//                        Replace Fragment on Activity
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentFragmentCoffee, ShowOrderFragment.showOrderInstance(loginString, dateTimeString))
                                .addToBackStack(null)
                                .commit();

                    }else{

                        Toast.makeText(getActivity(),"Cannot Upload Order to Server",
                                Toast.LENGTH_SHORT).show();

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }   // onClick
        });
    }

    //@SuppressLint("NewApi")
    private void espresspSeekbar() {
        //espressoSeekBar.setMin(10);
        espressoSeekBar.setMax(20);
        espressoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress <= 10) {
                    progress = 10;
                }

                espressoString = Integer.toString(progress);
                unitEspressoTextView.setText(espressoString + " " + getString(R.string.shot));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //@SuppressLint("NewApi")
    private void frappeSeekbar() {
        //frappeSeekBar.setMin(10);
        frappeSeekBar.setMax(20);
        frappeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress <= 10){
                    progress = 10;
                }

                frappeString = Integer.toString(progress);
                unitFrappeTextView.setText(frappeString + " " + "g");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //@SuppressLint("NewApi")
    private void milkSeekbar() {
        //milkSeekBar.setMin(1);
        milkSeekBar.setMax(10);
        milkSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                double progressAdouble = (double) progress;
                double milkAdoudle = progressAdouble / 10.0;

                if (milkAdoudle < 0.5){
                    milkAdoudle = 0.5;
                }
                milkString = Double.toString(milkAdoudle);
                unitMilkTextView.setText(milkString + " " + getString(R.string.teaspoons));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //@SuppressLint("NewApi")
    private void cocoSeekbar() {
        //cocoSeekBar.setMin(1);
        cocoSeekBar.setMax(10);
        cocoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                double progressAdouble = (double) progress;
                double cocoAdouble = progressAdouble / 10.0;

                if (cocoAdouble < 0.5) {
                    cocoAdouble = 0.5;
                }

                cocoString = Double.toString(cocoAdouble);
                unitCocoTextView.setText(cocoString + " " + getString(R.string.teaspoons));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void coldDefaule() {
        coldDrinnkRadioButton.setChecked(true);
        unitEspressoTextView.setText("10" + getString(R.string.shot));
        unitCocoTextView.setText("1" + getString(R.string.teaspoons));
        unitMilkTextView.setText("1" + getString(R.string.teaspoons));
        unitFrappeTextView.setText("1" + getString(R.string.teaspoons));

    }

    private void hotDrinkController() {
        hotDrinkRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milkLinearLayout.setVisibility(View.INVISIBLE);
                frappePowderLinearLayout.setVisibility(View.INVISIBLE);
                typeCoffeeString = "Hot Drink";
                milkString = "0";
                frappeString = "0";
            }
        });
    }

    private void clodDrinkController() {
        coldDrinnkRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for divisble View
                milkLinearLayout.setVisibility(View.VISIBLE);
                frappePowderLinearLayout.setVisibility(View.VISIBLE);
                typeCoffeeString = "Cold Drink";
            }
        });
    }

    private void frappuccinoController() {
        frappuccinoRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milkLinearLayout.setVisibility(View.VISIBLE);
                frappePowderLinearLayout.setVisibility(View.VISIBLE);
                typeCoffeeString = "Frappucino";
            }
        });
    }

    private void initialView() {
        frappuccinoRadioButton = getView().findViewById(R.id.radfrappuccino);
        coldDrinnkRadioButton = getView().findViewById(R.id.radcolddrink);
        hotDrinkRadioButton = getView().findViewById(R.id.radhotdrink);
        milkLinearLayout = getView().findViewById(R.id.nMilk);
        frappePowderLinearLayout = getView().findViewById(R.id.linFrapp);

        espressoSeekBar = getView().findViewById(R.id.seekbarEspreeso);
        cocoSeekBar = getView().findViewById(R.id.seekCoco);
        milkSeekBar = getView().findViewById(R.id.seekBarMilk);
        frappeSeekBar = getView().findViewById(R.id.seekbarFrappe);

        unitEspressoTextView = getView().findViewById(R.id.txtUnitEspreeso);
        unitCocoTextView = getView().findViewById(R.id.txtUnitCoco);
        unitMilkTextView = getView().findViewById(R.id.txtUnitMilk);
        unitFrappeTextView = getView().findViewById(R.id.txtUnitFrappe);
    }

    private void getValueFromArument(){
        loginString = getArguments().getStringArray("Login");
        for (int i=0; i<loginString.length; i+=1){
            Log.d("6DecV1", "LoinString[" + i + "] ==>" + loginString[i]);
        }



    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_espresso, container, false);

        return view;
    }
}

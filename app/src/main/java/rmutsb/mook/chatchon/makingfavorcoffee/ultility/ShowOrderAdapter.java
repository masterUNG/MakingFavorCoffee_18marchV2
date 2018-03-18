package rmutsb.mook.chatchon.makingfavorcoffee.ultility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import rmutsb.mook.chatchon.makingfavorcoffee.R;

/**
 * Created by Acer on 4/1/2561.
 */

public class ShowOrderAdapter extends BaseAdapter {

    private Context context;
    private String[] nameStrigs, typeStrings, priceStrings;


    public ShowOrderAdapter(Context context,
                            String[] nameStrigs,
                            String[] typeStrings,
                            String[] priceStrings) {
        this.context = context;
        this.nameStrigs = nameStrigs;
        this.typeStrings = typeStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {
        return nameStrigs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.listview_order, viewGroup, false);

        TextView itemTextView = view1.findViewById(R.id.txtItem);
        TextView nameTextView = view1.findViewById(R.id.txtName);
        TextView typeTextView = view1.findViewById(R.id.txtType);
        TextView priceTextView = view1.findViewById(R.id.txtPrice);

        String itemString = Integer.toString(i+1);
        itemTextView.setText(itemString);
        nameTextView.setText(nameStrigs[i]);
        typeTextView.setText(typeStrings[i]);
        priceTextView.setText(priceStrings[i]);

        return view1;
    }
}

package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Ubigeo;

import java.util.ArrayList;

/**
 * Created by Administrador on 03/11/2017.
 */

public class ProvinciaUbigeoAdapter extends BaseAdapter {

    ArrayList<Ubigeo> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public ProvinciaUbigeoAdapter(ArrayList<Ubigeo> items, Context context) {
        this.items = items;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(items == null){
            return 0;
        }else {
            return items.size();
        }
    }

    @Override
    public Ubigeo getItem(int position) {
        if(items == null){
            return null;
        }else{
            return items.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= new ViewHolder();
        View view = layoutInflater.inflate(R.layout.row_provincia,null);

        viewHolder.tv_provincia = (TextView) view.findViewById(R.id.tv_provincia);

        viewHolder.tv_provincia.setText(String.valueOf(getItem(position).getProvincia()));

        Ubigeo data = getItem(position);

        if(data!=null){
            viewHolder.tv_provincia.setText(data.getProvincia());
        } else {
            viewHolder.tv_provincia.setText("");
        }

        return view;
    }

    public static final class ViewHolder{
        TextView tv_provincia;
    }

    public void setNewListProvinciaUbigeo(ArrayList<Ubigeo> listBeneficiario){
        items = listBeneficiario;
    }

}

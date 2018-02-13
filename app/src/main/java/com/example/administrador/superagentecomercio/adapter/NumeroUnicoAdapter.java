package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.entity.NumeroUnico;

import java.util.ArrayList;

/**
 * Created by Administrador on 08/02/2018.
 */

public class NumeroUnicoAdapter extends BaseAdapter {
    ArrayList<NumeroUnico> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public NumeroUnicoAdapter(ArrayList<NumeroUnico> items, Context context) {
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
    public NumeroUnico getItem(int position) {
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
        return null;
    }

    public static final class ViewHolder{
        TextView tv_deudas_tarjetas;
    }

    public void setNewListNumeroUnico(ArrayList<NumeroUnico> listNumeroUnico){
        items = listNumeroUnico;
    }
}

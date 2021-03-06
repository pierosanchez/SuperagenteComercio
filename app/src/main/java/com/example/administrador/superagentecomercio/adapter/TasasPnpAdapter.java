package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Banco;
import com.example.administrador.superagentecomercio.entity.TasasPnpEntity;

import java.util.ArrayList;

/**
 * Created by Administrador on 03/11/2017.
 */

public class TasasPnpAdapter extends BaseAdapter {

    ArrayList<TasasPnpEntity> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public TasasPnpAdapter(ArrayList<TasasPnpEntity> items, Context context) {
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
    public TasasPnpEntity getItem(int position) {
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
        View view = layoutInflater.inflate(R.layout.row_tasas_pnp,null);

        viewHolder.tv_bancos = (TextView) view.findViewById(R.id.tv_tasas);

        viewHolder.tv_bancos.setText(String.valueOf(getItem(position).getDescPnpTasa()));

        TasasPnpEntity data = getItem(position);

        if(data!=null){
            viewHolder.tv_bancos.setText(data.getDescPnpTasa());
        } else {
            viewHolder.tv_bancos.setText("");
        }

        return view;
    }

    public static final class ViewHolder{
        TextView tv_bancos;
    }

    public void setNewListTasasPnp(ArrayList<TasasPnpEntity> listBeneficiario){
        items = listBeneficiario;
    }

}

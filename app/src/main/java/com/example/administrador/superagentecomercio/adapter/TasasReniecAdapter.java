package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Distrito;
import com.example.administrador.superagentecomercio.entity.TasasReniecEntity;

import java.util.ArrayList;

/**
 * Created by Administrador on 03/11/2017.
 */

public class TasasReniecAdapter extends BaseAdapter {

    ArrayList<TasasReniecEntity> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public TasasReniecAdapter(ArrayList<TasasReniecEntity> items, Context context) {
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
    public TasasReniecEntity getItem(int position) {
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
        View view = layoutInflater.inflate(R.layout.row_tasas_reniec,null);

        viewHolder.tv_distritos = (TextView) view.findViewById(R.id.tv_tasas);

        viewHolder.tv_distritos.setText(String.valueOf(getItem(position).getDescReniecTasa()));

        TasasReniecEntity data = getItem(position);

        if(data!=null){
            viewHolder.tv_distritos.setText(data.getDescReniecTasa());
        } else {
            viewHolder.tv_distritos.setText("");
        }

        return view;
    }

    public static final class ViewHolder{
        TextView tv_distritos;
    }

    public void setNewListTasasReniec(ArrayList<TasasReniecEntity> listBeneficiario){
        items = listBeneficiario;
    }

}

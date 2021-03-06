package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Departamento;
import com.example.administrador.superagentecomercio.entity.Distrito;

import java.util.ArrayList;

/**
 * Created by Administrador on 03/11/2017.
 */

public class DistritoAdapter extends BaseAdapter {

    ArrayList<Distrito> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public DistritoAdapter(ArrayList<Distrito> items, Context context) {
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
    public Distrito getItem(int position) {
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
        View view = layoutInflater.inflate(R.layout.row_distritos,null);

        viewHolder.tv_distritos = (TextView) view.findViewById(R.id.tv_distritos);

        viewHolder.tv_distritos.setText(String.valueOf(getItem(position).getDescripcionDistrito()));

        Distrito data = getItem(position);

        if(data!=null){
            viewHolder.tv_distritos.setText(data.getDescripcionDistrito());
        } else {
            viewHolder.tv_distritos.setText("");
        }

        return view;
    }

    public static final class ViewHolder{
        TextView tv_distritos;
    }

    public void setNewListDistrito(ArrayList<Distrito> listBeneficiario){
        items = listBeneficiario;
    }

}

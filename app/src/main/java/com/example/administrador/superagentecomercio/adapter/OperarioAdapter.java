package com.example.administrador.superagentecomercio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrador.superagentecomercio.R;
import com.example.administrador.superagentecomercio.entity.Comercio;
import com.example.administrador.superagentecomercio.entity.Operario;

import java.util.ArrayList;

/**
 * Created by abarrera on 05/01/2018.
 */

public class OperarioAdapter extends BaseAdapter {

    ArrayList<Operario> items;
    Context context;
    LayoutInflater layoutInflater = null;

    public OperarioAdapter(ArrayList<Operario> items, Context context) {
        this.items = items;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (items == null){
            return 0;
        } else {
            return items.size();
        }
    }

    @Override
    public Operario getItem(int position) {
        if (items == null) {
            return null;
        } else {
            return items.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = layoutInflater.inflate(R.layout.row_listar_operario, null);

        viewHolder.row_nombre = (TextView) view.findViewById(R.id.row_nombre);
        viewHolder.row_paterno = (TextView) view.findViewById(R.id.row_paterno);
        viewHolder.row_materno = (TextView) view.findViewById(R.id.row_materno);
        viewHolder.row_dni = (TextView) view.findViewById(R.id.row_dni);

        final Operario data = getItem(position);

        if (data != null) {
            viewHolder.row_nombre.setText(data.getNom_ope());
            viewHolder.row_paterno.setText(data.getPater_ope());
            viewHolder.row_materno.setText(data.getMater_ope());
            viewHolder.row_dni.setText(data.getDni_ope());
        } else {
            viewHolder.row_nombre.setText("");
            viewHolder.row_paterno.setText("");
            viewHolder.row_materno.setText("");
            viewHolder.row_dni.setText("");
        }

        return view;
    }

    public static final class ViewHolder{
        TextView row_nombre,row_paterno,row_materno,row_dni;

    }

    public void setNewListOperario(ArrayList<Operario> operarios){
        items = operarios;
    }



}

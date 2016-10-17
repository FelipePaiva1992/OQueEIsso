package com.br.oqueeisso.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.oqueeisso.R;
import com.br.oqueeisso.model.Duvida;
import com.br.oqueeisso.util.Utils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xxnickfuryxx on 29/07/15.
 */
public class DuvidasAdapter extends ArrayAdapter<Duvida>{

    private LayoutInflater inflater;
    private int resource;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public DuvidasAdapter(Context context, int resource, List<Duvida> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Duvida getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        v = inflater.inflate(resource, null);

        Duvida duvida = this.getItem(position);

        TextView txt_duvida = (TextView) v.findViewById(R.id.txt_duvida);
        txt_duvida.setText(duvida.getDuvida());

        TextView txt_usuario = (TextView) v.findViewById(R.id.txt_usuario);
        txt_usuario.setText(duvida.getUsrCad());

        TextView txt_date_duvida = (TextView) v.findViewById(R.id.txt_date_duvida);
        txt_date_duvida.setText(sdf.format(duvida.getDataDuvida()));

        ImageView img_resource = (ImageView) v.findViewById(R.id.img_resource);

        //Animação
        View root = v.findViewById(R.id.root);
        View root_sombra = v.findViewById(R.id.root_sombra);
        Utils.createAnimation(new View[]{root, root_sombra}, R.anim.zoom_in);

        return v;
    }
}

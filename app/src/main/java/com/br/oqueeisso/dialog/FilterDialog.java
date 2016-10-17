package com.br.oqueeisso.dialog;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.br.oqueeisso.R;

public class FilterDialog extends Dialog implements View.OnClickListener,
                                                    ListView.OnItemClickListener{

    public FilterDialog(Context context){
        super(context, R.style.AppThemeDialogFullScreen);
        setContentView(R.layout.filter_layout);

        initialize();


    }

    private void initialize() {
        TextView btn_cancelar = (TextView)this.findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getContext().getResources().getStringArray(R.array.categorias));
        adapter.notifyDataSetChanged();
        ListView lv_categoria = (ListView)this.findViewById(R.id.lv_categoria);
        lv_categoria.setAdapter(adapter);
        lv_categoria.setOnItemClickListener(this);

    }

    @Override
    public void show() {

        super.show();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_cancelar){
            this.dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cat = parent.getItemAtPosition(position).toString();
        Log.e("*****TESTE", cat);
    }
}

package com.br.oqueeisso.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.br.oqueeisso.R;

public class PerfilDialog extends Dialog implements View.OnClickListener{

    public PerfilDialog(Context context){
        super(context, R.style.AppThemeDialogFullScreen);
        setContentView(R.layout.perfil_layout);


        initialize();


    }

    private void initialize() {
        TextView btn_ok = (TextView)this.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

        TextView btn_cancelar = (TextView)this.findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(this);
    }

    @Override
    public void show() {

        super.show();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ok){
            this.dismiss();
        }else if(v.getId() == R.id.btn_cancelar) {
            this.dismiss();
        }
    }

}

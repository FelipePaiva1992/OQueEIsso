package com.br.oqueeisso.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.br.oqueeisso.R;
import com.br.oqueeisso.activity.DetalheActivity;

public class RespostaDialog extends Dialog implements View.OnClickListener{

    private Context context;

    public RespostaDialog(Context context){
        super(context, R.style.AppThemeDialogFullScreen80);
        setContentView(R.layout.resposta_layout);
        this.context = context;

        initialize();


    }

    private void initialize() {
        TextView btn_cancelar = (TextView)this.findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(this);
        TextView btn_responder = (TextView)this.findViewById(R.id.btn_responder);
        btn_responder.setOnClickListener(this);
    }

    @Override
    public void show() {

        super.show();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_cancelar){
            this.dismiss();
        }else if(v.getId() == R.id.btn_responder){
            this.dismiss();
            WaitDialog waitDialog = new WaitDialog(context);
            waitDialog.show();
        }
    }

}

package com.br.oqueeisso.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.br.oqueeisso.R;
import com.br.oqueeisso.dialog.RespostaDialog;
import com.br.oqueeisso.model.Duvida;
import com.br.oqueeisso.util.Utils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.text.SimpleDateFormat;

/**
 * Created by xxnickfuryxx on 07/08/15.
 */
public class DetalheActivity extends Activity implements SlidingMenu.OnOpenListener,
                                                        SlidingMenu.OnCloseListener,
                                                        View.OnClickListener{


    private SlidingMenu menu;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Duvida duvida;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_pergunta);

        initialize();
        Utils.menuLateral(this, this);
    }


    //MENU
    private void initialize() {
        menu = Utils.createMenuLateral(this);
        menu.setOnOpenListener(this);
        menu.setOnCloseListener(this);

        ImageView btn_menu = (ImageView) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

        ImageView btn_filter = (ImageView)findViewById(R.id.btn_filter);
        btn_filter.setVisibility(ImageView.INVISIBLE);

        duvida = (Duvida)this.getIntent().getSerializableExtra("duvida");

        TextView txt_duvida = (TextView)findViewById(R.id.txt_duvida);
        txt_duvida.setText(duvida.getDuvida());

        TextView txt_usuario = (TextView)findViewById(R.id.txt_usuario);
        txt_usuario.setText(duvida.getUsrCad());

        TextView txt_date_duvida = (TextView)findViewById(R.id.txt_date_duvida);
        txt_date_duvida.setText(sdf.format(duvida.getDataDuvida()));

        ImageButton btn_responder = (ImageButton)findViewById(R.id.btn_responder);
        btn_responder.setOnClickListener(this);
        btn_responder.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_right_left_appear));


    }

    @Override
    public void onOpen() {
        ImageView btn_menu = (ImageView) findViewById(R.id.btn_menu);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, -50, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);

        btn_menu.startAnimation(translateAnimation);
    }

    @Override
    public void onClose() {
        ImageView btn_menu = (ImageView) findViewById(R.id.btn_menu);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(500);

        btn_menu.startAnimation(translateAnimation);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_menu) {
            menu.showMenu(true);

        }else if (view.getId() == R.id.btn_responder) {
            RespostaDialog respostaDialog = new RespostaDialog(this);
            respostaDialog.show();
        }

        Utils.genericOnClick(this, view);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

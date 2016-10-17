package com.br.oqueeisso.util;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.br.oqueeisso.R;
import com.br.oqueeisso.activity.DashboardActivity;
import com.br.oqueeisso.activity.PerguntaActivity;
import com.br.oqueeisso.dialog.PerfilDialog;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xxnickfuryxx on 24/07/15.
 */
public class Utils {
    public static SlidingMenu createMenuLateral(Activity activity){
        SlidingMenu slidingPaneLayout = new SlidingMenu(activity);

        //Agora para abrir o menu, o dedo tem que partir da argin
        slidingPaneLayout.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        slidingPaneLayout.setShadowWidthRes(R.dimen.shadow_width);
        slidingPaneLayout.setShadowDrawable(R.drawable.shadow);
        slidingPaneLayout.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingPaneLayout.setFadeDegree(0.35f);
        slidingPaneLayout.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        slidingPaneLayout.setMenu(R.layout.menu_frame);

        return slidingPaneLayout;
    }

    public static void menuLateral(Activity activity, View.OnClickListener onClickListener){

        TextView txt_perfil = (TextView)activity.findViewById(R.id.txt_perfil);
        txt_perfil.setOnClickListener(onClickListener);

        TextView txt_menu_duvida = (TextView)activity.findViewById(R.id.txt_menu_duvida);
        txt_menu_duvida.setOnClickListener(onClickListener);

        TextView txt_nova_duvida = (TextView)activity.findViewById(R.id.txt_nova_duvida);
        txt_nova_duvida.setOnClickListener(onClickListener);

        TextView txt_respostas = (TextView)activity.findViewById(R.id.txt_respostas);
        txt_respostas.setOnClickListener(onClickListener);

        TextView txt_config = (TextView)activity.findViewById(R.id.txt_config);
        txt_config.setOnClickListener(onClickListener);

        TextView txt_sobre = (TextView)activity.findViewById(R.id.txt_sobre);
        txt_sobre.setOnClickListener(onClickListener);
    }

    public static void genericOnClick(Activity activity, View view){

        if(view.getId() == R.id.txt_perfil){

            PerfilDialog perfilDialog = new PerfilDialog(activity);
            perfilDialog.show();

        }else if (view.getId() == R.id.txt_menu_duvida) {

            Intent i = new Intent(activity, DashboardActivity.class);
            activity.startActivity(i);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }else if(view.getId() == R.id.txt_sobre){

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(activity.getResources().getString(R.string.url_sobre)));
            activity.startActivity(i);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }else if(view.getId() == R.id.txt_nova_duvida){

            Intent i = new Intent(activity, PerguntaActivity.class);
            activity.startActivity(i);
            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }
    }


    public static boolean isConnected(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo netInfos[] = cm.getAllNetworkInfo();
            if(netInfos != null){
                for(NetworkInfo networkInfo:netInfos){
                    if(networkInfo.getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }

        }
        return false;
    }


    public static void createAnimation(View[] views, int id){
        for(View view:views){
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), id);
            view.startAnimation(animation);
        }
    }

    public static void playCameraCapture(Context context){
        MediaPlayer mp = MediaPlayer.create(context, R.raw.camera_sound);
        mp.start();
    }


}

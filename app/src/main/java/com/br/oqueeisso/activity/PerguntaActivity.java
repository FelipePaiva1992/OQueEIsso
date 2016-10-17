package com.br.oqueeisso.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.br.oqueeisso.R;
import com.br.oqueeisso.model.Duvida;
import com.br.oqueeisso.util.Utils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;

/**
 * Created by xxnickfuryxx on 07/08/15.
 */
public class PerguntaActivity extends Activity implements SlidingMenu.OnOpenListener,
                                                        SlidingMenu.OnCloseListener,
                                                        View.OnClickListener,
                                                        SurfaceHolder.Callback{


    private SlidingMenu menu;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Duvida duvida;
    private Camera camera;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private byte[] picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pergunta_layout);

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

        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surf_resource);
        surfaceView.setOnClickListener(this);

        SurfaceHolder sh = surfaceView.getHolder();
        sh.addCallback(this);

        ImageView btn_filter = (ImageView)findViewById(R.id.btn_filter);
        btn_filter.setVisibility(ImageView.INVISIBLE);


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
        }else if(view.getId() == R.id.surf_resource){

            Utils.playCameraCapture(this);
            camera.takePicture(null, null, new CameraCallback());

            RelativeLayout rel_resource = (RelativeLayout)findViewById(R.id.rel_resource);
            rel_resource.setVisibility(RelativeLayout.GONE);

            TextView txt_capturar = (TextView)findViewById(R.id.txt_capturar);
            txt_capturar.setVisibility(SurfaceView.GONE);

            ImageView img_resource = (ImageView)findViewById(R.id.img_resource);
            img_resource.setVisibility(ImageView.VISIBLE);

            ImageView btn_remove = (ImageView)findViewById(R.id.btn_remove);
            btn_remove.setVisibility(ImageView.VISIBLE);
            btn_remove.setOnClickListener(PerguntaActivity.this);

            Utils.createAnimation(new View[]{rel_resource}, R.anim.abc_fade_out);
            Utils.createAnimation(new View[]{img_resource}, R.anim.abc_fade_in);


        }else if(view.getId() == R.id.btn_remove){

            ImageView img_resource = (ImageView)findViewById(R.id.img_resource);
            img_resource.setImageBitmap(null);
            img_resource.setVisibility(ImageView.GONE);

            ImageView btn_remove = (ImageView)findViewById(R.id.btn_remove);
            btn_remove.setVisibility(ImageView.GONE);
            btn_remove.setOnClickListener(PerguntaActivity.this);

            RelativeLayout rel_resource = (RelativeLayout)findViewById(R.id.rel_resource);
            rel_resource.setVisibility(RelativeLayout.VISIBLE);

            TextView txt_capturar = (TextView)findViewById(R.id.txt_capturar);
            txt_capturar.setVisibility(SurfaceView.VISIBLE);
            camera.startPreview();

            Utils.createAnimation(new View[]{img_resource}, R.anim.abc_fade_out);
            Utils.createAnimation(new View[]{rel_resource}, R.anim.abc_fade_in);

            System.gc();

        }

        Utils.genericOnClick(this, view);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.gc();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(camera == null){
            camera = Camera.open();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(camera == null){
            camera = Camera.open();
        }
        Parameters p = camera.getParameters();
        p.setFocusMode(Parameters.FOCUS_MODE_AUTO);
        p.setFlashMode(Parameters.FLASH_MODE_AUTO);
        p.setJpegQuality(25);

        camera.setParameters(p);

        try {

            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception e) {
            Log.e(PerguntaActivity.class.getName(), "" + e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
        // finish();

    }


    public class CameraCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            camera.stopPreview();


            ImageView img_resource = (ImageView)findViewById(R.id.img_resource);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            Matrix mat = new Matrix();
            mat.postRotate(90);
            mat.postScale(.3f, .3f);

            Bitmap correctBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), mat, true);

            img_resource.setImageBitmap(correctBmp);

            Utils.createAnimation(new View[]{img_resource}, R.anim.abc_fade_in);


//            camera.startPreview();

        }



    }

}

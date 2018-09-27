package com.metaio.hackfix;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

public class HackUtils {

    public static boolean hasRegister;
    private static  void hackCameraImpl(){

        ViewGroup viewGroup = ((ViewGroup) UnityPlayer.currentActivity.findViewById(android.R.id.content));
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof com.metaio.sdk.CameraView) {
                SurfaceView surfaceView = (SurfaceView) childView;
                ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.height = 0;
                surfaceView.setLayoutParams(layoutParams);

            }
        }
    }

    //fixed that > android7.0 cameraview repeat
    public static void hackCamera() {

        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {


                hackCameraImpl();

                if(!hasRegister){


                    UnityPlayer.currentActivity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                        @Override
                        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                        }

                        @Override
                        public void onActivityStarted(Activity activity) {

                        }

                        @Override
                        public void onActivityResumed(Activity activity) {

                            hackCameraImpl();
                        }

                        @Override
                        public void onActivityPaused(Activity activity) {

                        }

                        @Override
                        public void onActivityStopped(Activity activity) {

                        }

                        @Override
                        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                        }

                        @Override
                        public void onActivityDestroyed(Activity activity) {

                        }
                    });



                    hasRegister=true;
                }




            }
        });


    }

}

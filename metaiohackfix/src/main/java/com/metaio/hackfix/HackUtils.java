package com.metaio.hackfix;

import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.unity3d.player.UnityPlayer;

public class HackUtils {
    //fixed that > android7.0 cameraview repeat
    public static void hackCamera() {

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

}

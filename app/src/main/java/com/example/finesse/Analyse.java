package com.example.finesse;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.*;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Analyse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Analyse extends Fragment {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private Camera mCamera;
    private CameraPreview mPreview;

    public Analyse() {
    }

    // TODO: Rename and change types and number of parameters
    public static Analyse newInstance(String param1, String param2) {
        Analyse fragment = new Analyse();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analyse, container, false);
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(requireActivity(), mCamera);
        FrameLayout preview = (FrameLayout) view.findViewById(R.id.preview_container);
        preview.addView(mPreview);
        return view;
    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
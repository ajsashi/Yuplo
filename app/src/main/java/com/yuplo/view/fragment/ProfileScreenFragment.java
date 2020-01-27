package com.yuplo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.yuplo.R;
import com.yuplo.base.BaseFragment;
import com.yuplo.support.Constants;
import com.yuplo.support.MyPreferenceManager;
import com.yuplo.support.Utils;
import com.yuplo.support.fragmentmanager.manager.IFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static com.yuplo.support.Constants.IMAGE_PICKER_REQUEST_CODE;

public class ProfileScreenFragment extends BaseFragment implements IFragment {

    @Inject
    Utils utils;
    @Inject
    MyPreferenceManager preferenceManager;
    private boolean isfirst = true;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;


    public static IFragment newInstance() {
        return new ProfileScreenFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_screen;
    }

    @Override
    public String getFragmentName() {
        return "ProfileScreenFragment";
    }

    @Override
    public void setTitle() {
        yuploFragmentChannel.setToolbarTitle("Profile");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector().inject(this);
        String prefData = preferenceManager.getData(Constants.getStoragePermission());
        if (prefData != null) {
            isfirst = Boolean.parseBoolean(prefData);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yuploFragmentChannel.setToolbarTitle("Profile");
    }

    @OnClick(R.id.profile_image)
    public void selectImage() {
        if (utils.hasPermission(getContext(), READ_EXTERNAL_STORAGE)) {
            startIntent();
        } else {
            askPermission(getContext(), getActivity());
        }
    }

    private void askPermission(Context context, FragmentActivity activity) {

        if (ActivityCompat.checkSelfPermission(activity,
                READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, READ_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions((Activity) context, new String[]{READ_EXTERNAL_STORAGE}, Constants.getStorageRequestCode());
            } else {
                if (isfirst) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{READ_EXTERNAL_STORAGE}, Constants.getStorageRequestCode());


                    preferenceManager.storeData(Constants.getStoragePermission(), "false");
                    isfirst = false;
                } else {
                    Toast.makeText(context, getResources().getString(R.string.enable_location), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + activity.getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            }
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    private void startIntent() {

        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Constants.getImagePickerRequestCode());
    }

    public void updateProfileImage(Uri uri){
        profileImage.setImageURI(uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            switch (requestCode) {

                case IMAGE_PICKER_REQUEST_CODE:

                    Uri saveUri = data.getData();
                   updateProfileImage(saveUri);
                    Log.d("Image Picker", "Picker");
                    break;
            }
        }
    }
}

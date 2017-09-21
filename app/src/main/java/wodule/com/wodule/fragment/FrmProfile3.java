package wodule.com.wodule.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSConstrains;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.User;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

/**
 * Created by MyPC on 13/09/2017.
 */
public class FrmProfile3 extends Fragment implements View.OnClickListener {
    private ImageView ivBack, ivAvatar;
    private TextView btnSubmit, btnReset;
    private APIService mAPIService;
    private ProgressDialog mProgressDialog;
    private CountDownTimer mCountDownTimer1;
    private boolean checktimeout = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_profile3, container, false);
        btnSubmit = (TextView) view.findViewById(R.id.btnSubmit);
        btnReset = (TextView) view.findViewById(R.id.btnReset);
        ivBack = (ImageView) view.findViewById(R.id.ivBack);
        ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
        btnSubmit.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        mAPIService = APIUtils.getAPIService();
        if (QTSConstrains.bmAvatar != null) {
            ivAvatar.setImageBitmap(QTSHelp.getRoundedCornerBitmap(QTSConstrains.bmAvatar, 15));
        }
        ivAvatar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Log.e("Object",
                               "City             "+FrmProfile.newUser.getCity().toString()+"\n"+
                               "Country          "+FrmProfile.newUser.getCountry().toString()+"\n"+
                               "telephone        "+FrmProfile.newUser.getTelephone().toString()+"\n"+
                               "nationality      "+FrmProfile.newUser.getNationality().toString()+"\n"+
                               "status           "+FrmProfile.newUser.getStatus().toString()+"\n"+
                               "gender           "+FrmProfile.newUser.getGender().toString()+"\n"+
                               "username         "+FrmProfile.newUser.getUserName().toString()+"\n"+
                               "email            "+FrmProfile.newUser.getEmail().toString()+"\n"+
                               "password         "+FrmProfile.newUser.getPassword().toString()+"\n"+
                               "code             "+FrmProfile.newUser.getCode().toString()+"\n"+
                               "fist name        "+FrmProfile.newUser.getFirstName().toString()+"\n"+
                               "middle name      "+FrmProfile.newUser.getMiddleName().toString()+"\n"+
                               "last name        "+FrmProfile.newUser.getLastName().toString()+"\n"+
                               "date_of_birth    "+FrmProfile.newUser.getDateOfBirth().toString()+"\n"+
                               "country_of_birth "+FrmProfile.newUser.getCountryOfBirth().toString()+"\n"+
                               "native name      "+FrmProfile.newUser.getNativeName().toString()+"\n"+
                               "Suffx            "+FrmProfile.newUser.getSuffix().toString()+"\n"+
                               "In_first         "+FrmProfile.newUser.getLnFirst().toString()+"\n"+
                               "address          "+FrmProfile.newUser.getAddress().toString()+ FrmProfile.newUser.getAddress1().toString() +  FrmProfile.newUser.getAddress2().toString()+"\n"+
                               "ethnicity        "+FrmProfile.newUser.getEthnicity().toString()+"\n"+
                               "religion         "+FrmProfile.newUser.getReligion().toString());
                new GetData().execute();
                mCountDownTimer1 = new CountDownTimer(20000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (checktimeout == true) {
                            mProgressDialog.cancel();
                            QTSHelp.showToast(getActivity(), "Time out");
                        }
                    }
                };
                mCountDownTimer1.start();
                break;
            case R.id.ivBack:
                FrmProfile2 fragment1 = new FrmProfile2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.anim_enter1, R.anim.anim_exit1);
                fragmentTransaction.replace(R.id.fragmentHolder, fragment1);
                fragmentTransaction.commit();
                QTSHelp.setIsEdit(getActivity(), true);
                break;
        }
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("User Register...");
            mProgressDialog.show();
            mProgressDialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            final String status = "";
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), QTSConstrains.pictureFile);

            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("uploaded_file", QTSConstrains.pictureFile.getName(), requestFile);
            mAPIService.post(
                    FrmProfile.newUser.getCity().toString(),
                    FrmProfile.newUser.getCountry().toString(),
                    FrmProfile.newUser.getTelephone().toString(),
                    FrmProfile.newUser.getNationality().toString(),
                    FrmProfile.newUser.getStatus().toString(),
                    FrmProfile.newUser.getGender().toString(),
                    FrmProfile.newUser.getUserName().toString(),
                    FrmProfile.newUser.getEmail().toString(),
                    FrmProfile.newUser.getPassword().toString(),
                    FrmProfile.newUser.getCode().toString(),
                    FrmProfile.newUser.getFirstName().toString(),
                    FrmProfile.newUser.getMiddleName().toString(),
                    FrmProfile.newUser.getLastName().toString(),
                    FrmProfile.newUser.getDateOfBirth().toString(),
                    FrmProfile.newUser.getCountryOfBirth().toString(),
                    FrmProfile.newUser.getNativeName().toString(),
                    FrmProfile.newUser.getSuffix().toString(),
                    FrmProfile.newUser.getLnFirst().toString(),
                    FrmProfile.newUser.getAddress().toString()+" "+ FrmProfile.newUser.getAddress1().toString() +" "+  FrmProfile.newUser.getAddress2().toString(),
                    FrmProfile.newUser.getEthnicity().toString(),
                    FrmProfile.newUser.getReligion().toString(),
                    body
            ).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("error",response.toString());
                    if (response.isSuccessful())
                    {
                        QTSHelp.ShowpopupMessage(getActivity(),"Success");
                        getActivity().finish();
                        mProgressDialog.cancel();
                        checktimeout = false;
                    }
                    else
                    {
                        QTSHelp.ShowpopupMessage(getActivity(),"No Success");
                        mProgressDialog.cancel();
                        checktimeout = false;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            return status;
        }
    }
}

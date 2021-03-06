package wodule.com.wodule.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wodule.com.wodule.R;
import wodule.com.wodule.helper.QTSHelp;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.utils.APIService;
import wodule.com.wodule.utils.APIUtils;

public class ActExaminer extends AppCompatActivity implements View.OnClickListener {
    private TextView lbLogout,lbName,lbId,tvIdExam,lbSchool,lbSex,lbAge,lbExamhistory,lbCalender,lbStartExam;
    private RatingBar ratingBar2;
    private ImageView iconBag,iconCalendar,iconStart,btnEdit,iconAvatar;
    private APIService mAPIService;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_examiner);
        token=getIntent().getStringExtra("token");
        initUI();
        mAPIService = APIUtils.getAPIService();
        getProfile();
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    private void initUI() {
        lbLogout = (TextView) findViewById(R.id.lbLogout);
        lbName = (TextView) findViewById(R.id.lbName);
        lbId = (TextView) findViewById(R.id.lbId);
        tvIdExam = (TextView) findViewById(R.id.tvIdExam);
        lbSchool = (TextView) findViewById(R.id.lbSchool);
        lbSex = (TextView) findViewById(R.id.lbSex);
        lbAge = (TextView) findViewById(R.id.lbAge);
        lbExamhistory = (TextView) findViewById(R.id.lbExamhistory);
        lbCalender = (TextView) findViewById(R.id.lbCalender);
        lbStartExam = (TextView) findViewById(R.id.lbStartExam);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        iconBag = (ImageView) findViewById(R.id.iconBag);
        iconCalendar = (ImageView) findViewById(R.id.iconCalendar);
        iconStart = (ImageView) findViewById(R.id.iconStart);
        btnEdit = (ImageView) findViewById(R.id.btnEdit);
        iconAvatar = (ImageView) findViewById(R.id.iconAvatar);

        iconBag.setOnClickListener(this);
        lbExamhistory.setOnClickListener(this);
        iconCalendar.setOnClickListener(this);
        lbCalender.setOnClickListener(this);
        iconStart.setOnClickListener(this);
        lbStartExam.setOnClickListener(this);
        lbLogout.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iconBag:
//                Log.e("onlickexminar","onclick");
                startActivity(new Intent(ActExaminer.this,ActAssessmentHistoryE.class));
                break;
            case R.id.lbExamhistory:
                startActivity(new Intent(ActExaminer.this, ActAssessmentHistoryE.class));
                break;
            case R.id.iconCalendar:
                startActivity(new Intent(ActExaminer.this, ActCalendarE.class));
                break;
            case R.id.lbCalender:
                startActivity(new Intent(ActExaminer.this, ActCalendarE.class));
                break;
            case R.id.iconStart:
                startActivity(new Intent(ActExaminer.this, ActAssessmentStartE.class));
                break;
            case R.id.lbStartExam:
                startActivity(new Intent(ActExaminer.this, ActAssessmentStartE.class));
                break;
            case R.id.lbLogout:
                startActivity(new Intent(ActExaminer.this, ActLogin.class));
                finish();
                QTSHelp.setIsLogin(ActExaminer.this,false);
                break;
            case R.id.btnEdit:
                break;

        }
    }

    public void getProfile(){
        mAPIService.getAnswers("Bearer "+token).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    Log.e("getProfile",response.message().toString());
                    lbName.setText(response.body().getUser().getFirstName());
//                    tvIdExam.setText(response.body().getUser().getRoleId());
                    lbSchool.setText(response.body().getUser().getStudentClass());
                    lbSex.setText(response.body().getUser().getGender());

                    String[] strdate =response.body().getUser().getDateOfBirth().split("-");
                    lbAge.setText("Age: " + QTSHelp.getAge(Integer.parseInt(strdate[0]), Integer.parseInt(strdate[1]), Integer.parseInt(strdate[2])));
                    Picasso.with(ActExaminer.this).load(response.body().getUser().getPicture()).into(iconAvatar);
                }
                else
                    Log.e("getProfile",response.message().toString());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}

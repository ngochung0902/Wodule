package wodule.com.wodule.utils;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.User;
import wodule.com.wodule.object.UserExaminer;

/**
 * Created by MyPC on 14/09/2017.
 */
public interface APIService {
    @POST("api/user_login")
    @FormUrlEncoded
    Call<UserExaminer> savePost(@Field("user_name") String user_name,
                                @Field("password") String password,
                                @Field("social") boolean social);

    @GET("api/profile")
    Call <Example> getAnswers(@Header("Authorization")String auth);

    @Multipart
    @POST("api/user_register")
//    @FormUrlEncoded
    Call<User> post(
            @Part("city") String city,
            @Part("country") String country,
            @Part("telephone") String telephone,
            @Part("nationality") String nationality,
            @Part("status") String status,
            @Part("gender") String gender,
            @Part("user_name") String user_name,
            @Part("email") String email,
            @Part("password") String password,
            @Part("code") String code,
            @Part("first_name") String first_name,
            @Part("middle_name") String middle_name,
            @Part("last_name") String last_name,
            @Part("date_of_birth") String date_of_birth,
            @Part("country_of_birth") String country_of_birth,
            @Part("native_name") String native_name,
            @Part("Suffx") String Suffx,
            @Part("Ln_first") String ln_first,
            @Part("address") String address,
            @Part("ethnicity") String ethnicity,
            @Part("religion") String religion,
            @Part MultipartBody.Part picture
    );
}

package com.example.sleepmood;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroService {
  //  @GET("/cafe/{path}/")
  //Call<ComCafeInfo> requestCafe(@Path("path") int id);

    @GET("diary/{email}/{date}")
    Call<List<DiaryData>> getDiaryToday(@Path("email") String diary_id, @Path("date") String date, @Header("Authorization") String auth);

    @GET("diary/{email}/all")
    Call<List<DiaryData>> getDiaryAll(@Path("email") String email, @Header("Authorization") String auth);

    @GET("report/{email}/{date}/selection")
    Call<ReportData> getReportDate(@Path("email") String email, @Path("date") String date, @Header("Authorization") String auth);

    @GET("report/{email}/all")
    Call<List<ReportData>> getReportAll(@Path("email") String email, @Header("Authorization") String auth);

    @GET("report/{email}/today")
    Call<ReportData> getReportToday(@Path("email") String email, @Header("Authorization") String auth);

    @GET("calendar/{email}/list/")
    Call<List<CalendarData>> getCalendarAll(@Path("email") String email, @Header("Authorization") String auth);

    @GET("weather/now")
    Call<WeatherData> getWeatherNow(@Header("Authorization") String auth);

    @GET("weather/forecast")
    Call<List<WeatherData>> getWeatherForecast (@Header("Authorization") String auth);
/*
    @FormUrlEncoded
    @POST("diary/")
    Call<DiaryData2> provideDiaryDay(
            @Query("format") String json,
            @Field("email") String email,
            @Field("date") String date,
            @Field("description") String description
            );
*/
    @POST("diary/")
    Call<DiaryData> provideDiaryDay(
            @Body DiaryData diary, @Header("Authorization") String auth
    );

    @POST("status")
    Call<SleepData> provideSleepData(
            @Body SleepData sd, @Header("Authorization") String auth
    );

    @POST("report/")
    Call<ReportData> provideReportData(
            @Body ReportData rd, @Header("Authorization") String auth
    );


    @POST("authenticate")
    Call<String> tryLogin(
            @Body LoginData ld
    );

    @POST("sleep")
    Call<Integer> provideSleepTime(
            @Body SleepTime st, @Header("Authorization") String auth
    );

    @POST("sleep/update/")
    Call<SleepTimeUpdate> provideSleepTimeUpdate(
            @Body SleepTimeUpdate st, @Header("Authorization") String auth
    );

    @POST("calendar/")
    Call<CalendarData> provideCalendarData(
            @Body CalendarData cd, @Header("Authorization") String auth
    );
    @POST("signup")
    Call<UserData> provideUserData(
            @Body UserData ud
    );




    /*
    @FormUrlEncoded
    @POST("/diary/")
    Call<DiaryData> provideCafe(
            @Query("format") String json,
            @Field("cafe_name") String cafe_name,
            @Field("x") float x,
            @Field("y") float y,
            @Field("start_time") String start_time,
            @Field("end_time") String end_time,
            @Field("phone") String phone,
            @Field("notice") String notice,
            @Field("star") float star,
            @Field("com_num") String com_num,
            @Field("business") boolean business,
            @Field("close") boolean close,
            @Field("seat_total") int seat_total,
            @Field("seat_curr") int seat_curr,
            @Field("seat_max") int seat_max);

    @FormUrlEncoded
    @POST("/review/")
            Call<Review> provideReview(
                    @Query("format") String json,
            @Field("uid") String uid,
            @Field("review") String review
            );

    @GET("/review/")
    Call<List<Review>> requestReview(@Query("format") String json,@Query("uid") String uid);

    @FormUrlEncoded
    @POST("/timesale/")
    Call<Time_sale> provideTimeSale(
            @Query("format") String json,
            @Field("name") String name,
            @Field("cno") int cno,
            @Field("rprice") int rprice,
            @Field("sprice") int sprice
    );

    @FormUrlEncoded
    @PUT("/cafe/{id}/")
    Call<ComCafeInfo> putCafe( @Path("id") int id,
                           @Query("format") String json,
                           @Field("cafe_name") String cafe_name,
                           @Field("x") float x,
                           @Field("y") float y,
                           @Field("start_time") String start_time,
                           @Field("end_time") String end_time,
                           @Field("phone") String phone,
                           @Field("notice") String notice,
                           @Field("star") double star,
                           @Field("com_num") String com_num,
                           @Field("business") boolean business,
                           @Field("close") boolean close,
                           @Field("seat_total") int seat_total,
                           @Field("seat_curr") int seat_curr,
                               @Field("seat_max") int seat_max,
                               @Field("tag1") String tag1,
                               @Field("tag2") String tag2,
                               @Field("event") String event,
                               @Field("sun") boolean sun,
                               @Field("mon") boolean mon,
                               @Field("tue") boolean tue,
                               @Field("wed") boolean wed,
                               @Field("thu") boolean thu,
                               @Field("fri") boolean fri,
                               @Field("sat") boolean sat
    );

    @GET("/timesale")
    Call<List<Time_sale>> getTimeSale(
            @Query("cno") int cno
    );



    @PUT("/time_sale")
    Call<List<Time_sale>> requestTimeSale(@Path("c_no") int c_no);

    @GET("/booking1/booking1/")
    Call<List<Booking1>> getBook1(
    @Query("cno") int cno
    );

    @GET("/booking2/booking2/")
    Call<List<Booking2>> getBook2(
            @Query("cno") int cno
    );

    @GET("/booking3/booking3/")
    Call<List<Booking3>> getBook3(
            @Query("cno") int cno
    );

    @GET("/check/check/")
    Call<List<Booking>> getBook(
            @Query("start_date") String start_date
    );

    @DELETE("/check/{id}/")
    Call<Void> deleteBook(@Path("id")int id);

    @FormUrlEncoded
    @POST("/app_loginm/")
    Call<Login> requestLogin(
            @Field("userid") String userid,
            @Field("userpw") String userpw
    );

    @GET("/age/{id}")
    Call<Age> getAge(
            @Path("id") int id
    );
    @GET("/gender/{id}")
    Call<Gender> getGender(
            @Path("id") int id
    );
    @DELETE("/timesale/{id}/")
    Call<Void> deleteEvent(@Path("id")int id);

    @DELETE("/booking1/{id}/")
    Call<Void> deleteBook1(
            @Path("id") int id
    );
    @DELETE("/booking2/{id}/")
    Call<Void> deleteBook2(
            @Path("id") int id
    );
    @DELETE("/booking3/{id}/")
    Call<Void> deleteBook3(
            @Path("id") int id
    );
    @GET("/booking1/booking1/")
    Call<List<Booking1>> getBook1Id(
            @Query("uid") int uid
    );

    @GET("/booking2/booking2/")
    Call<List<Booking2>> getBook2Id(
            @Query("uid") int uid
    );

    @GET("/booking3/booking3/")
    Call<List<Booking3>> getBook3Id(
            @Query("uid") int uid
    );

     */
}

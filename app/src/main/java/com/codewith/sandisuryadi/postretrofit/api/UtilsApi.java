package com.codewith.sandisuryadi.postretrofit.api;

public class UtilsApi {
    // Ini adalah localhost.
    public static final String BASE_URL_API = "http://192.168.1.11/mahasiswa/index.php/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}

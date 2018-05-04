package example.com.searchview.web

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {

    @GET("android/jsonandroid")
    fun getAllAndroidVersion() : Observable<Response>

    companion object {

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.learn2crack.com")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
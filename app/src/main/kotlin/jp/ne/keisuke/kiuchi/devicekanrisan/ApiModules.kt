package jp.ne.keisuke.kiuchi.devicekanrisan

import dagger.Module
import dagger.Provides
import io.swagger.client.ApiClient
import io.swagger.client.api.DefaultApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/* module for API connection(e.g retrofit2, http3, etc..) */
@Module
class ApiModules {
    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
    }

    private fun createRetrofitBuilder(endPoint: String): Retrofit.Builder =
            Retrofit.Builder()
                    .baseUrl(endPoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return createRetrofitBuilder("https://script.google.com/macros/s/XXXXXXXXXXXXXXXXXXX/")
    }

    private fun createApiClient(retrofitBuilder: Retrofit.Builder,
                                builder: OkHttpClient.Builder): ApiClient {
        val client = ApiClient()
//        client.createDefaultAdapter()
        client.adapterBuilder = retrofitBuilder
        client.configureFromOkclient(builder.build())
        return client
    }

    @Provides
    fun provideApiClient(retrofitBuilder: Retrofit.Builder,
                         builder: OkHttpClient.Builder): ApiClient {
        return createApiClient(retrofitBuilder, builder)
    }

    /*FIXME if you add some Swagger ApiService, add provide method. Like this */
    // @Provides fun provideSomeApi(client: ApiClient): SomeApiService {
    //    return client.createService(SomeApiService::class.java)
    // }

    @Provides
    fun provideDefaultApi(client: ApiClient): DefaultApi {
        return client.createService(DefaultApi::class.java)
    }


}

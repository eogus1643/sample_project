package com.skt.mydata.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skt.mydata.common.util.RestApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RestApiConfig {

	@Value("${api.url.host}")
    private String baseUri;

	@Bean(name="okHttpClient")
	public OkHttpClient okHttpClient() {
		HttpLoggingInterceptor httpInterceptor = new HttpLoggingInterceptor();
		httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.addInterceptor(httpInterceptor)
				.addInterceptor(chain ->{
					Request request = chain.request()
							.newBuilder()
							.addHeader("Content-Type", "application/json;charset=UTF-8")
							.build();
					return chain.proceed(request);
				}).build();
	}

	@Bean(name="commonRetrofit")
	public Retrofit retrofit(@Qualifier("okHttpClient") OkHttpClient client) {
		return new Retrofit.Builder()
				.baseUrl(baseUri)
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
	}

	@Bean(name="restApiService")
	public RestApiInterface restApiService(@Qualifier("commonRetrofit") Retrofit retrofit) {
		return retrofit.create(RestApiInterface.class);
	}

}

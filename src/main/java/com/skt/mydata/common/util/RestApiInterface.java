package com.skt.mydata.common.util;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RestApiInterface {

	@POST("{uri}")
	Call<Object> callPost(@Path(value="uri", encoded=true) String uri, @Body Object body);

	@POST
	Call<Object> callPost_fullUrl(@Url String url, @Body Object body);

	@POST("{uri}")
	Call<Object> callPost_addHeader(
			@Path(value="uri", encoded=true) String uri
			,@HeaderMap Map<String, String> headers
			, @Body Object body);

	@GET
	Call<Object> callGet_fullUrl_addHeader(
			@Url String url
			,@HeaderMap Map<String, String> headers);

	@POST
	Call<Object> callPost_fullUrl_addHeader(
			@Url String url
			,@HeaderMap Map<String, String> headers
			, @Body Object body);

	@FormUrlEncoded
	@POST
	Call<Object> callPost_fullUrl_addHeader_formBody(
			@Url String url
			,@HeaderMap Map<String, String> headers
			, @FieldMap Map<String, Object> map);


}

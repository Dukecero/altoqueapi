package com.example.consumirapirest.interfaces;

import com.example.consumirapirest.model.Insumo;
import com.example.consumirapirest.model.Receta;
import com.example.consumirapirest.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("insumos")
    Call<List<Insumo>> getPost();

    @GET("recetas")
    Call<List<Receta>> getReceta();

    @FormUrlEncoded
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("usuario/validate")
    Call<Usuario> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("usuario/add")
    Call<Usuario> createuser(
            @Field("usu_codigo") int usu_codigo,
            @Field("usu_nombre") String usu_nombre,
            @Field("usu_clave") String usu_clave,
            @Field("cli_codigo") String cli_codigo
    );
}

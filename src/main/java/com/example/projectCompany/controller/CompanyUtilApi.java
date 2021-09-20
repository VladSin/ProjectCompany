package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.CompanyRequestDto;
import com.example.projectCompany.dto.response.CompanyResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CompanyUtilApi {

    @POST("/company/save")
    Call<CompanyResponseDto> saveCompany(@Body CompanyRequestDto company);

    @GET("/company/get/id/{id}")
    Call<CompanyResponseDto> getCompanyById(@Path("id") Long id);

    @GET("/company/get/name/{name}")
    Call<CompanyResponseDto> getCompanyByName(@Path("name") String name);

    @GET("/company/get/all/location/{locations}")
    Call<List<CompanyResponseDto>> getAllCompanyByLocation(@Path("location") String location);

    @GET("/company/get/all")
    Call<List<CompanyResponseDto>> getAllCompany();

    @PATCH("/company/update/{id}")
    Call<CompanyResponseDto> updateCompanyData(@Path("id") Long id, @Body CompanyRequestDto company);

    @DELETE("/company/delete/{id}")
    Call<String> deleteCompany(@Path("id") Long id);
}

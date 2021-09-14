package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.CompanyRequestDto;
import com.example.projectCompany.dto.response.CompanyResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CompanyUtilApi {

    @POST("/company/save")
    Call<CompanyResponseDto> saveCompany(@Body CompanyRequestDto company);

    @GET("/company/get/id/")
    Call<CompanyResponseDto> getCompanyById(@Query("id") Long id);

    @GET("/company/get/name/")
    Call<CompanyResponseDto> getCompanyByName(@Query("name") String name);

    @GET("/company/get/all/location")
    Call<List<CompanyResponseDto>> getAllCompanyByLocation(@Query("location") String location);

    @GET("/company/get/all")
    Call<List<CompanyResponseDto>> getAllCompany();

    @POST("/company/update")
    void updateCompanyData(@Query("id") Long id, @Body CompanyRequestDto company);

    @DELETE("/company/delete")
    void deleteCompany(@Query("id") Long id);
}

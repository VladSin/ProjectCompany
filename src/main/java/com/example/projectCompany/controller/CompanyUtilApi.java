package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.CompanyRequestDto;
import com.example.projectCompany.entity.Company;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CompanyUtilApi {

    @POST("/save")
    Call<Company> saveCompany(@Body CompanyRequestDto company);

    @GET("/get/id/")
    Call<Company> getCompanyById(@Query("id") Long id);

    @GET("/get/name/")
    Call<Company> getCompanyByName(@Query("name") String name);

    @GET("/get/all/location")
    Call<List<Company>> getAllCompanyByLocation(@Query("location") String location);

    @GET("/get/all")
    Call<List<Company>> getAllCompany();

    @POST("/update")
    void updateCompanyData(@Body CompanyRequestDto company);

    @DELETE("/delete")
    void deleteCompany(@Query("id") Long id);
}

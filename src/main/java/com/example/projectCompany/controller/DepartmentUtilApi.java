package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DepartmentUtilApi {

    @POST("/department/save")
    Call<DepartmentResponseDto> saveDepartment(@Body DepartmentRequestDto department);

    @GET("/department/get/id/")
    Call<DepartmentResponseDto> getDepartmentById(@Query("id") Long id);

    @GET("/department/get/name/")
    Call<DepartmentResponseDto> getDepartmentByName(@Query("name") String name);

    @GET("/department/get/all/location")
    Call<List<DepartmentResponseDto>> getAllDepartmentByLocation(@Query("location") String location);

    @GET("/department/get/all")
    Call<List<DepartmentResponseDto>> getAllDepartment();

    @POST("/department/update")
    void updateDepartmentData(@Query("id") Long id, @Body DepartmentRequestDto department);

    @DELETE("/department/delete")
    void deleteDepartment(@Query("id") Long id);
}

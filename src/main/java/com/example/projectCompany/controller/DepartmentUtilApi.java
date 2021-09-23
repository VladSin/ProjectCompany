package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DepartmentUtilApi {

    @POST("/department/save")
    Call<DepartmentResponseDto> saveDepartment(@Body DepartmentRequestDto department);

    @GET("/department/get/id/{id}")
    Call<DepartmentResponseDto> getDepartmentById(@Path("id") Long id);

    @GET("/department/get/name/{name}")
    Call<DepartmentResponseDto> getDepartmentByName(@Path("name") String name);

    @GET("/department/get/location/{location}")
    Call<List<DepartmentResponseDto>> getAllDepartmentByLocation(@Path("location") String location);

    @GET("/department/get/all")
    Call<List<DepartmentResponseDto>> getAllDepartment();

    @PATCH("/department/update/{id}")
    Call<DepartmentResponseDto> updateDepartmentData(@Path("id") Long id, @Body DepartmentRequestDto department);

    @DELETE("/department/delete/{id}")
    Call<String> deleteDepartment(@Path("id") Long id);
}

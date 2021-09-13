package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.entity.Department;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DepartmentUtilApi {

    @POST("/department/save")
    Call<Department> saveDepartment(@Body DepartmentRequestDto department);

    @GET("/department/get/id/")
    Call<Department> getDepartmentById(@Query("id") Long id);

    @GET("/department/get/name/")
    Call<Department> getDepartmentByName(@Query("name") String name);

    @GET("/department/get/all/location")
    Call<List<Department>> getAllDepartmentByLocation(@Query("location") String location);

    @GET("/department/get/all")
    Call<List<Department>> getAllDepartment();

    @POST("/department/update")
    void updateDepartmentData(@Body DepartmentRequestDto department);

    @DELETE("/department/delete")
    void deleteDepartment(@Query("id") Long id);
}

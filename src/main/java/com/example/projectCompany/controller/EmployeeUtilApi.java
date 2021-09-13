package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EmployeeUtilApi {

    @POST("/save")
    Call<EmployeeResponseDto> saveEmployee(@Body EmployeeRequestDto employee);

    @GET("/get/id/")
    Call<EmployeeResponseDto> getEmployeeById(@Query("id") Long id);

    @GET("/get/username/")
    Call<EmployeeResponseDto> getEmployeeByUsername(@Query("username") String username);

    @GET("/get/email/")
    Call<EmployeeResponseDto> getEmployeeByEmail(@Query("email") String email);

    @GET("/get/all")
    Call<List<EmployeeResponseDto>> getAll();

    @GET("/get/all/married")
    Call<List<EmployeeResponseDto>> getAllByMarried(@Query("married") boolean married);

    @GET("/get/all/salary/after")
    Call<List<EmployeeResponseDto>> getAllBySalaryAfter(@Query("salary") double salary);

    @GET("/get/all/salary/before")
    Call<List<EmployeeResponseDto>> getAllBySalaryBefore(@Query("salary") double salary);

    @GET("/get/all/salary/between")
    Call<List<EmployeeResponseDto>> getAllBySalaryBetween(@Query("minSalary") double minSalary,
                                                          @Query("maxSalary") double maxSalary);

    @GET("/get/all/department")
    Call<List<EmployeeResponseDto>> getAllByDepartment(@Query("department") String department);

    @POST("/update")
    void updateEmployeeData(@Body EmployeeRequestDto employee);

    @DELETE("/delete")
    void deleteEmployee(@Query("id") Long id);
}

package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EmployeeUtilApi {

    @POST("/employee/save")
    Call<EmployeeResponseDto> saveEmployee(@Body EmployeeRequestDto employee);

    @GET("/employee/get/id/")
    Call<EmployeeResponseDto> getEmployeeById(@Query("id") Long id);

    @GET("/employee/get/username/")
    Call<EmployeeResponseDto> getEmployeeByUsername(@Query("username") String username);

    @GET("/employee/get/email/")
    Call<EmployeeResponseDto> getEmployeeByEmail(@Query("email") String email);

    @GET("/employee/get/all")
    Call<List<EmployeeResponseDto>> getAllEmployee();

    @GET("/employee/get/all/married")
    Call<List<EmployeeResponseDto>> getAllByMarried(@Query("married") boolean married);

    @GET("/employee/get/all/salary/after")
    Call<List<EmployeeResponseDto>> getAllBySalaryAfter(@Query("salary") double salary);

    @GET("/employee/get/all/salary/before")
    Call<List<EmployeeResponseDto>> getAllBySalaryBefore(@Query("salary") double salary);

    @GET("/employee/get/all/salary/between")
    Call<List<EmployeeResponseDto>> getAllBySalaryBetween(@Query("minSalary") double minSalary,
                                                          @Query("maxSalary") double maxSalary);

    @GET("/employee/get/all/department")
    Call<List<EmployeeResponseDto>> getAllByDepartment(@Query("department") String department);

    @POST("/employee/update")
    void updateEmployeeData(@Query("id") Long id, @Body EmployeeRequestDto employee);

    @DELETE("/employee/delete")
    void deleteEmployee(@Query("id") Long id);
}

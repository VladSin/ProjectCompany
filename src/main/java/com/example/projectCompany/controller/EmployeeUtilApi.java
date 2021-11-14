package com.example.projectCompany.controller;

import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EmployeeUtilApi {

    @POST("/employee/save")
    Call<EmployeeResponseDto> saveEmployee(@Body EmployeeRequestDto employee);

    @GET("/employee/get/id/{id}")
    Call<EmployeeResponseDto> getEmployeeById(@Path("id") Long id);

    @GET("/employee/get/username/{firstName}/{lastName}")
    Call<EmployeeResponseDto> getEmployeeByUsername(@Path("firstName") String firstName, @Path("lastName") String lastName);

    @GET("/employee/get/email/{email}")
    Call<EmployeeResponseDto> getEmployeeByEmail(@Path("email") String email);

    @GET("/employee/get/all")
    Call<List<EmployeeResponseDto>> getAllEmployee();

    @GET("/employee/get/all/married/{married}")
    Call<List<EmployeeResponseDto>> getAllByMarried(@Path("married") boolean married);

    @GET("/employee/get/all/salary/after/{salary}")
    Call<List<EmployeeResponseDto>> getAllBySalaryAfter(@Path("salary") double salary);

    @GET("/employee/get/all/salary/before/{salary}")
    Call<List<EmployeeResponseDto>> getAllBySalaryBefore(@Path("salary") double salary);

    @GET("/employee/get/all/salary/between/{minSalary}/{maxSalary}")
    Call<List<EmployeeResponseDto>> getAllBySalaryBetween(@Path("minSalary") double minSalary,
                                                          @Path("maxSalary") double maxSalary);

    @GET("/employee/get/all/department/{department}")
    Call<List<EmployeeResponseDto>> getAllByDepartment(@Path("department") String department);

    @PATCH("/employee/update/{id}")
    Call<EmployeeResponseDto> updateEmployeeData(@Path("id") Long id, @Body EmployeeRequestDto employee);

    @DELETE("/employee/delete/{id}")
    Call<String> deleteEmployee(@Path("id") Long id);
}

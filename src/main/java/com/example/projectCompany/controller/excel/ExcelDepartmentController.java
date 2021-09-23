package com.example.projectCompany.controller.excel;

import com.example.projectCompany.dto.response.DepartmentResponseDto;
import com.example.projectCompany.exporter.ExcelDepartmentExporter;
import com.example.projectCompany.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExcelDepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public ExcelDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/export/departments/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Departments_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DepartmentResponseDto> departmentResponse = departmentService.getAllDepartment().stream()
                .map(DepartmentResponseDto::fromDepartment)
                .collect(Collectors.toList());

        ExcelDepartmentExporter excelExporter = new ExcelDepartmentExporter(departmentResponse);
        excelExporter.export(response);
    }
}

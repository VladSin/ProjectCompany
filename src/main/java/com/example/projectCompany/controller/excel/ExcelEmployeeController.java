package com.example.projectCompany.controller.excel;

import com.example.projectCompany.dto.response.EmployeeResponseDto;
import com.example.projectCompany.exporter.ExcelEmployeeExporter;
import com.example.projectCompany.service.EmployeeService;
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
public class ExcelEmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public ExcelEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/export/employees/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employees_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<EmployeeResponseDto> employeeResponse = employeeService.getAll().stream()
                .map(EmployeeResponseDto::fromEmployee)
                .collect(Collectors.toList());

        ExcelEmployeeExporter excelExporter = new ExcelEmployeeExporter(employeeResponse);
        excelExporter.export(response);
    }
}

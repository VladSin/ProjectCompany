package com.example.projectCompany.controller.excel;

import com.example.projectCompany.dto.ReportDto;
import com.example.projectCompany.exporter.ExcelReportExporter;
import com.example.projectCompany.service.CompanyService;
import com.example.projectCompany.service.DepartmentService;
import com.example.projectCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ExcelReportController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public ExcelReportController(CompanyService companyService, EmployeeService employeeService, DepartmentService departmentService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/export/report/excel")
    public ResponseEntity<String> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Report_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ReportDto> reportDto = ReportDto.toReportDto(
                employeeService.getAllEmployee(),
                departmentService.getAllDepartment(),
                companyService.getAllCompany());

        Map<String, String> resultDto = ReportDto.getReportResult(
                employeeService.getAllEmployee(),
                departmentService.getAllDepartment(),
                companyService.getAllCompany());

        ExcelReportExporter excelExporter = new ExcelReportExporter(reportDto, resultDto);
        excelExporter.export(response);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

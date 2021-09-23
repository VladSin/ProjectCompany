package com.example.projectCompany.controller.excel;

import com.example.projectCompany.dto.response.CompanyResponseDto;
import com.example.projectCompany.exporter.ExcelCompanyExporter;
import com.example.projectCompany.service.CompanyService;
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
public class ExcelCompanyController {

    private final CompanyService companyService;

    @Autowired
    public ExcelCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/export/companies/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Companies_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<CompanyResponseDto> companyResponse = companyService.getAllCompany().stream()
                .map(CompanyResponseDto::fromCompany)
                .collect(Collectors.toList());

        ExcelCompanyExporter excelExporter = new ExcelCompanyExporter(companyResponse);
        excelExporter.export(response);
    }
}

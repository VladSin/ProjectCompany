package com.example.projectCompany.exporter;

import com.example.projectCompany.dto.ReportDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelReportExporter {

    private XSSFSheet sheet;
    private XSSFSheet resultSheet;
    private final XSSFWorkbook workbook;
    private final List<ReportDto> report;
    private final Map<String, String> result;

    public ExcelReportExporter(List<ReportDto> report, Map<String, String> result) {
        this.report = report;
        this.result = result;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Report");
        resultSheet = workbook.createSheet("Result");

        Row row = sheet.createRow(0);
        Row resultSheetRowow = resultSheet.createRow(0);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        font.setFontHeight(15);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        createCell(row, 0, "Company Name", style);
        createCell(row, 1, "Department Number", style);
        createCell(row, 2, "Employee Number", style);
        createCell(row, 3, "Department Name", style);
        createCell(row, 4, "Employee Number", style);
        createCell(row, 5, "Employee Information", style);

        createCell(resultSheetRowow, 0, "Total companies", style);
        createCell(resultSheetRowow, 1, "Total departments", style);
        createCell(resultSheetRowow, 2, "Total employees", style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ReportDto reportDto : report) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, reportDto.getCompanyName(), style);
            createCell(row, columnCount++, reportDto.getDepartmentNumberInCompany(), style);
            createCell(row, columnCount++, reportDto.getEmployeeNumberInCompany(), style);
            createCell(row, columnCount++, reportDto.getDepartmentName(), style);
            createCell(row, columnCount++, reportDto.getEmployeeNumberInDepartment(), style);
            createCell(row, columnCount++, reportDto.getEmployeeInformation(), style);
        }

        Row row = resultSheet.createRow(1);
        int columnCount = 0;
        createCell(row, columnCount++, result.get("Total companies"), style);
        createCell(row, columnCount++, result.get("Total departments"), style);
        createCell(row, columnCount++, result.get("Total employees"), style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}

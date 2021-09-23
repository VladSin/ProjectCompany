package com.example.projectCompany.exporter;

import com.example.projectCompany.dto.response.EmployeeResponseDto;
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

public class ExcelEmployeeExporter {

    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<EmployeeResponseDto> employees;

    public ExcelEmployeeExporter(List<EmployeeResponseDto> employees) {
        this.employees = employees;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employees");
        Row row = sheet.createRow(0);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        font.setFontHeight(15);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Username", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Salary", style);
        createCell(row, 4, "Married", style);
        createCell(row, 5, "Department", style);

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

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (EmployeeResponseDto e : employees) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, e.getId(), style);
            createCell(row, columnCount++, e.getUsername(), style);
            createCell(row, columnCount++, e.getEmail(), style);
            createCell(row, columnCount++, e.getSalary(), style);
            createCell(row, columnCount++, e.isMarried(), style);
            createCell(row, columnCount++, e.getDepartment(), style);
        }
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

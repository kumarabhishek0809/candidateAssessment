package com.assessment.candidate.service;

import com.assessment.candidate.response.CandidatesSearchResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CandidateAssessmentExcelGenerator {

    public static ByteArrayInputStream customersToExcel(List<CandidatesSearchResponse.CandidateProfile>
                                                                candidateProfiles) throws IOException {
        String[] COLUMNs = {"ID",
                "FIRSTNAME",
                "LASTNAME",
                "EMAILADDRESS",
                //"COUNTRYCODE",
                "DATEOFBIRTH",
                "MOBILENO",
                "INVITEDATE",
                "ATTEMPTEDDATE",
                "RESULT",
                "PERCENTAGE",
                //"STATUS",
                "ASSESSMENT-NAME"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("CandidateAssessment");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }


            // CellStyle for Age
            CellStyle dateFormatStyle = workbook.createCellStyle();
            dateFormatStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyy"));

            int rowIdx = 1;
            for (CandidatesSearchResponse.CandidateProfile candidateProfile : candidateProfiles) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(candidateProfile.getId());
                row.createCell(1).setCellValue(candidateProfile.getFirstName());
                row.createCell(2).setCellValue(candidateProfile.getLastName());
                row.createCell(3).setCellValue(candidateProfile.getEmailAddress());

                Cell dob = row.createCell(4);
                dob.setCellValue(candidateProfile.getDateOfBirth());
                dob.setCellStyle(dateFormatStyle);

                row.createCell(5).setCellValue(candidateProfile.getMobileNo());

                Cell inviteDate = row.createCell(6);
                inviteDate.setCellValue(candidateProfile.getInviteDate());
                inviteDate.setCellStyle(dateFormatStyle);

                Cell attemptDate = row.createCell(7);
                attemptDate.setCellValue(candidateProfile.getAttemptedDate());
                attemptDate.setCellStyle(dateFormatStyle);

                row.createCell(8).setCellValue(candidateProfile.getResult());
                row.createCell(9).setCellValue(candidateProfile.getPercentage());

                row.createCell(10).setCellValue(candidateProfile.getAssessmentName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
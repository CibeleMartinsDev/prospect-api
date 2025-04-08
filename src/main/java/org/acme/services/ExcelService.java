package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.acme.dto.UQCustomerDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class ExcelService {

    private  Integer initialRow = 0;

    @ConfigProperty(name = "my.property.directory-spreadsheet")
    private String directorySpreadSheetSaved;

    private static final Logger log = Logger.getLogger(ExcelService.class);

    public String generateExcel(List<UQCustomerDTO> uqs, String spreadsheetName) throws IOException {
        // Cria uma nova planilha
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(spreadsheetName);

        // Cria a primeira linha para o cabeçalho das colunas
        Row headerRow = sheet.createRow(initialRow);
        headerRow.createCell(0).setCellValue("Nome UQ");
        headerRow.createCell(1).setCellValue("Website URL");
        headerRow.createCell(2).setCellValue("Contato");

        // Adiciona UQs na planilha
        for(UQCustomerDTO uq : uqs){
            Row row1 = sheet.createRow(initialRow+1);
            initialRow++;
            row1.createCell(0).setCellValue(uq.getName());
            row1.createCell(1).setCellValue(uq.getWebsiteUrl());
            row1.createCell(2).setCellValue("");
        }

        // Define o diretório onde o arquivo será salvo
        String directoryPath = directorySpreadSheetSaved; // Caminho do diretório
        Path dirPath = Paths.get(directoryPath);

        // Cria o diretório caso não exista
        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        // Define o caminho completo do arquivo
        String filePath = directoryPath + "/planilha.xlsx";

        // Salva o arquivo no diretório
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close(); // Lembre-se de fechar o workbook
        log.info("Planilha de UQs salva em: " + filePath);
        // Retorna uma resposta informando que o arquivo foi salvo com sucesso
        return "Planilha de UQs gerada com sucesso! " + filePath;
    }
}

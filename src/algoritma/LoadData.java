/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import struktur.Point;

/**
 *
 * @author Anavel
 */
public class LoadData {

    //membaca file (.csv)
    public void read(String filePath, int confi) throws FileNotFoundException, IOException {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        CSVFormat csvFileFormat = CSVFormat.EXCEL.withFirstRecordAsHeader();
        fileReader = new FileReader(filePath);
        csvFileParser = new CSVParser(fileReader, csvFileFormat);
        List csvRecords = csvFileParser.getRecords();

        for (int i = 0; i < csvRecords.size(); i++) {
            CSVRecord record = (CSVRecord) csvRecords.get(i);
            double confidence = Double.parseDouble(record.get("confidence"));
            if (confidence >= confi) {
                Point p = new Point();
                p.setBrightness(Double.parseDouble(record.get("brightness")));
                p.setBright_t31(Double.parseDouble(record.get("bright_t31")));
                p.setFrp(Double.parseDouble(record.get("frp")));
                p.add(p);
            }
        }
    }
}

package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pi.enset.settings.LoadFormExcelToDb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/data")
@AllArgsConstructor
public class ImportDataFromExcelController {
    private LoadFormExcelToDb loadFormExcelToDb;

    @PostMapping("/import")
    public Boolean importData(@RequestParam("file") MultipartFile file) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            return loadFormExcelToDb.PutDataToDb(convertedFile.getPath());
        } catch (IOException e) {
            return false;
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + "/" + Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (var inputStream = multipartFile.getInputStream()) {
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return file;
    }
}

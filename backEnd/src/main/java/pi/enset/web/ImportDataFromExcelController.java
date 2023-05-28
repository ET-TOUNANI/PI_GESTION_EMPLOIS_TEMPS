package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.settings.LoadFormExcelToDb;

import java.io.IOException;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/data")
@AllArgsConstructor
public class ImportDataFromExcelController {
    private LoadFormExcelToDb loadFormExcelToDb;
    @GetMapping("/import?{path}")
    public String importData(@RequestParam String path) {
        try {
            loadFormExcelToDb.PutDataToDb(path);
            return "les données sont importées avec succès";
        } catch (IOException e) {
            return "les données ne sont pas importées";
        }
    }

}

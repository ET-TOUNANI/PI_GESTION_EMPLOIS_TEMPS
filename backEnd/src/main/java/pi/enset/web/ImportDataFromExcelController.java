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
    @GetMapping("/import")
    public String importData(@RequestParam(name = "path") String path) {
        try {
           boolean isImport= loadFormExcelToDb.PutDataToDb(path);
              if(isImport)
                return "les données sont importées avec succès";
                else
                    return "les données ne sont pas importées";
        } catch (IOException e) {
            return "les données ne sont pas importées";
        }
    }

}

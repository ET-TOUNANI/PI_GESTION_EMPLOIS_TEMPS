package pi.enset.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.enset.settings.PdfExportService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@RequestMapping("/api/pdf")
@AllArgsConstructor

public class PDFExportController {


    private final PdfExportService pdfGeneratorService;

    @GetMapping("/classes")
    public void generateAll(HttpServletResponse response) throws IOException {
        this.pdfGeneratorService.ClassesPDF(response);
    }
    @GetMapping("/departements/{id}")
    public void generateDepartement(HttpServletResponse response,@PathVariable int id) throws IOException {
        this.pdfGeneratorService.DepartementsPDF(response,id);
    }
    @GetMapping("/classes/{id}")
    public void generatePDFbyClass(HttpServletResponse response,@PathVariable int id) {

        this.pdfGeneratorService.OneClassePDF(response,id);

    }
}
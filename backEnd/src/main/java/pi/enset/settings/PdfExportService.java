package pi.enset.settings;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Classe;
import pi.enset.entities.Departement;
import pi.enset.entities.ElementDeModule;
import pi.enset.entities.Filiere;
import pi.enset.entities.enums.Periode;
import pi.enset.repository.ClasseRepository;
import pi.enset.repository.ElementModuleRepository;

import java.awt.*;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.*;
import java.util.List;

@Service
@AllArgsConstructor
public class PdfExportService {

    private final DataFromDb dataFromDb;

    private ElementModuleRepository elementModuleRepository;
    private ClasseRepository classeRepository;



    Periode[] timeslots;
    List<DayOfWeek> days;

    public void DepartementsPDF(HttpServletResponse response, int id) throws IOException {
        dataFromDb.loadDataFromDatabase();


        Document myPDFDoc = new Document(PageSize.A4,
                40f,   // left
                40f,   // right
                70f,  // top
                70f); // down

        final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());
        Departement departement = DataFromDb.departements.get(id);
        for(Filiere filiere:departement.getFilieres()){
            for(Classe classe:filiere.getClasses()){

                // Set TimePeriods in Timetable
                timeslots = Periode.values();
                days = new ArrayList<>();
                days.add(DayOfWeek.MONDAY);
                days.add(DayOfWeek.TUESDAY);
                days.add(DayOfWeek.WEDNESDAY);
                days.add(DayOfWeek.THURSDAY);
                days.add(DayOfWeek.FRIDAY);



                //////////////////////////////////////////////////////////////////////////




                // Define a string as title
                String title = classe.getLibelle();

                //1) Let's create a Table object
                Table myTable = new Table(5); // 3 columns


                FontFactory.register("backEnd/src/main/resources/Fonts/QuattrocentoSans-Italic.ttf");


                FontFactory.register("backEnd/src/main/resources/Fonts/Calibri Regular.ttf");

                Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

                Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

                Font Calibri3 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10);

                Font Quatt = FontFactory.getFont("Quattrocento Sans Italic", BaseFont.WINANSI, 9,Font.ITALIC, Color.BLUE);



                float[] columnWidths = { 25f, 40f, 40f, 40f, 40f }; // Adjust the values as needed
                myTable.setWidths(columnWidths);
                myTable.setPadding(2f);
                myTable.setWidth(100f);

                //2) Create the header of the table
                ArrayList<String> headerTable = new ArrayList<>();
                headerTable.add("");
                headerTable.add("08h:30 - 10h:30");
                headerTable.add("10h:30 - 12h:30");
                headerTable.add("14h - 16h");
                headerTable.add("16h - 18h");

                headerTable.forEach(e -> {
                    Paragraph paragraph = new Paragraph(e,Calibri1);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    Cell current = new Cell(paragraph);
                    current.setHeader(true);
                    current.setHorizontalAlignment(Element.ALIGN_CENTER);
                    current.setBackgroundColor(Color.LIGHT_GRAY);
                    myTable.addCell(current);
                });





                // 3) Then create a list of rows and add them to the table
                LinkedHashMap<Integer, List<List<Paragraph>>> listRows = new LinkedHashMap<>();
                int rowNumber = 1;


                for(DayOfWeek day:days){
                    List<Paragraph> CellList = new ArrayList<>();
                    List<List<Paragraph>> dayList = new ArrayList<>();
                    Paragraph Day;
                    if(rowNumber==1){
                        Day = new Paragraph("\n\nLundi\n\n\n",Calibri2);
                    } else if (rowNumber==2) {
                        Day = new Paragraph("\n\nMardi\n\n\n",Calibri2);
                    } else if (rowNumber==3) {
                        Day = new Paragraph("\n\nMercredi\n\n\n",Calibri2);
                    } else if (rowNumber==4) {
                        Day = new Paragraph("\n\nJeudi\n\n\n",Calibri2);
                    } else{
                        Day = new Paragraph("\n\nVendredi\n\n\n",Calibri2);
                    }
                    Day.setAlignment(Element.ALIGN_CENTER);
                    ArrayList<Paragraph> dayCell = new ArrayList<>();
                    dayCell.add(Day);
                    dayList.add(dayCell);

                    for(Periode p :timeslots){
                        CellList = new ArrayList<>();


                        List<ElementDeModule> elms = elementModuleRepository.findByDayOfWeekAndPeriodeAndClasse(day,p, classe.getId());
                        System.out.println("Elements "+elms.size());
                        if(elms.size()>0){
                            Paragraph Libele = new Paragraph(elms.get(0).getLibelle()+" ("+elms.get(0).getVolumeHoraire()+")",Calibri1);
                            Libele.setAlignment(Element.ALIGN_CENTER);
                            Paragraph Prof = new Paragraph(elms.get(0).getEnseignant().getNom()+" "+elms.get(0).getEnseignant().getPrenom(),Calibri3);
                            Prof.setAlignment(Element.ALIGN_CENTER);
                            Paragraph Salle = new Paragraph("Salle : "+elms.get(0).getSalle().getTypeSalle()+" "+elms.get(0).getSalle().getNumSalle(),Quatt);
                            Salle.setAlignment(Element.ALIGN_CENTER);
                            CellList.add(Libele);
                            CellList.add(Prof);
                            CellList.add(Salle);

                        }
                        else{
                            CellList.add(new Paragraph(""));
                        }
                        dayList.add(CellList);



                    }
                    System.out.println(dayList);
                    listRows.put(rowNumber, dayList);
                    rowNumber++;


                }

                Paragraph samedi = new Paragraph("\n\nSamedi\n\n\n",Calibri2);
                List<Paragraph> Cellist = new ArrayList<>();
                Cellist.add(samedi);


                List<List<Paragraph>> DayList = new ArrayList<>();
                DayList.add(Cellist);

                Cellist = new ArrayList<>();

                Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
                DayList.add(Cellist);
                Cellist = new ArrayList<>();
                Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
                DayList.add(Cellist);
                Cellist = new ArrayList<>();
                Cellist.add(new Paragraph(""));
                DayList.add(Cellist);
                listRows.put(6, DayList);

                listRows.forEach((index,userDetailRow) -> {
                    userDetailRow.forEach(paragraphs -> {
                        Cell cell = new Cell();
                        paragraphs.forEach(paragraph ->{
                            cell.add(paragraph);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);

                        });
                        myTable.addCell(cell);


                    });
                });

                myPDFDoc.open();  // Open the Document

                /* Here we add some metadata to the generated pdf */
                myPDFDoc.addTitle("Emplois du temps "+classe.getLibelle());
                myPDFDoc.addSubject("Generation emplois du temps pour ENSET");
                myPDFDoc.addKeywords("ENSET, "+classe.getLibelle());
                myPDFDoc.addCreator("ENSET");
                myPDFDoc.addAuthor("ENSET");
                /* End of the adding metadata section */

                // Create a Font object


                FontFactory.register("backEnd/src/main/resources/Fonts/COMIC.ttf");
                FontFactory.register("backEnd/src/main/resources/Fonts/times new roman.ttf");

                Font font1 = FontFactory.getFont("Comic Sans MS", 14, Font.UNDERLINE);
                Font font2 = FontFactory.getFont("Times New Roman", 14);
                Font font3 = FontFactory.getFont("Times New Roman", 12);
                Font font4 = FontFactory.getFont("Calibri", 20,Font.BOLD);
                Font font5 = FontFactory.getFont("Calibri", 18,Font.BOLD);
                Font font6 = FontFactory.getFont("Calibri", 16, Font.BOLD,Color.RED);
                int year = new Date().getYear();
                String text1 = "Département : "+classe.getFiliere().getDepartement().getLibelle();
                String text2 = "EMPLOI DU TEMPS";
                String text3 = Integer.toString(year+1900)+" / "+Integer.toString(year+1+1900);
                String text4 = "FI "+classe.getFiliere().getLibelle()+" - "+classe.getLibelle();
                String text5 = "Semestre "+classe.getLibelle().split(" ")[1];
                String text6 = "Provisoire";

                // Create a paragraph with the new font
                Paragraph paragraph1 = new Paragraph(text1,font1);
                paragraph1.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragraph2 = new Paragraph(text2,font2);
                paragraph2.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragraph3 = new Paragraph(text3,font3);
                paragraph3.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragraph4 = new Paragraph(text4,font4);
                paragraph4.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragraph5 = new Paragraph(text5,font5);
                paragraph5.setAlignment(Element.ALIGN_CENTER);
                Paragraph paragraph6 = new Paragraph(text6,font6);
                paragraph6.setAlignment(Element.ALIGN_CENTER);

                Image headerImage = Image.getInstance("backEnd/src/main/resources/header.jpg");
                Image footerImage = Image.getInstance("backEnd/src/main/resources/footer.jpg");
                float headerWidth = PageSize.A4.getWidth();
                float headerHeight = 50f;  // Adjust the height as needed
                float footerWidth = PageSize.A4.getWidth();
                float footerHeight = 50f;  // Adjust the height as needed
                Rectangle headerRect = new Rectangle(headerWidth, headerHeight);
                Rectangle footerRect = new Rectangle(footerWidth, footerHeight);
                headerImage.setAbsolutePosition(0, PageSize.A4.getHeight() - headerHeight - 10f);
                headerImage.scaleToFit(headerWidth, headerHeight);
                myPDFDoc.add(headerImage);

                // Add footer image to the bottom of each page
                footerImage.setAbsolutePosition(0, 10f);
                footerImage.scaleToFit(footerWidth, footerHeight);footerImage.scaleToFit(footerWidth, footerHeight);
                myPDFDoc.add(footerImage);



                myPDFDoc.add(paragraph1);
                myPDFDoc.add(paragraph2);
                myPDFDoc.add(paragraph3);
                myPDFDoc.add(paragraph4);
                myPDFDoc.add(paragraph5);
                myPDFDoc.add(paragraph6);

                // Adding an empty line
                myPDFDoc.add(new Paragraph(Chunk.NEWLINE));



                // 4)Finally add the table to the document
                myPDFDoc.add(myTable);
                myPDFDoc.add(new Paragraph(Chunk.NEXTPAGE));


            }
        }

        myPDFDoc.close();
        pdfWriter.close();


    }







    public void OneClassePDF(HttpServletResponse response, int id){
        dataFromDb.loadDataFromDatabase();
        // Retrieve all classes


        Classe classe = DataFromDb.classes.get(id);
        // Set TimePeriods in Timetable
        timeslots = Periode.values();
        days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);



        //////////////////////////////////////////////////////////////////////////

        Document myPDFDoc = new Document(PageSize.A4,
                40f,   // left
                40f,   // right
                70f,  // top
                70f); // down


        // Define a string as title
        String title = classe.getLibelle();

        //1) Let's create a Table object
        Table myTable = new Table(5); // 3 columns


        FontFactory.register("backEnd/src/main/resources/Fonts/QuattrocentoSans-Italic.ttf");


        FontFactory.register("backEnd/src/main/resources/Fonts/Calibri Regular.ttf");

        Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

        Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

        Font Calibri3 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10);

        Font Quatt = FontFactory.getFont("Quattrocento Sans Italic", BaseFont.WINANSI, 9,Font.ITALIC, Color.BLUE);



        float[] columnWidths = { 25f, 40f, 40f, 40f, 40f }; // Adjust the values as needed
        myTable.setWidths(columnWidths);
        myTable.setPadding(2f);
        myTable.setWidth(100f);

        //2) Create the header of the table
        ArrayList<String> headerTable = new ArrayList<>();
        headerTable.add("");
        headerTable.add("08h:30 - 10h:30");
        headerTable.add("10h:30 - 12h:30");
        headerTable.add("14h - 16h");
        headerTable.add("16h - 18h");

        headerTable.forEach(e -> {
            Paragraph paragraph = new Paragraph(e,Calibri1);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            Cell current = new Cell(paragraph);
            current.setHeader(true);
            current.setHorizontalAlignment(Element.ALIGN_CENTER);
            current.setBackgroundColor(Color.LIGHT_GRAY);
            myTable.addCell(current);
        });





        // 3) Then create a list of rows and add them to the table
        LinkedHashMap<Integer, List<List<Paragraph>>> listRows = new LinkedHashMap<>();
        int rowNumber = 1;


        for(DayOfWeek day:days){
            List<Paragraph> CellList = new ArrayList<>();
            List<List<Paragraph>> dayList = new ArrayList<>();
            Paragraph Day;
            if(rowNumber==1){
                Day = new Paragraph("\n\nLundi\n\n\n",Calibri2);
            } else if (rowNumber==2) {
                Day = new Paragraph("\n\nMardi\n\n\n",Calibri2);
            } else if (rowNumber==3) {
                Day = new Paragraph("\n\nMercredi\n\n\n",Calibri2);
            } else if (rowNumber==4) {
                Day = new Paragraph("\n\nJeudi\n\n\n",Calibri2);
            } else{
                Day = new Paragraph("\n\nVendredi\n\n\n",Calibri2);
            }
            Day.setAlignment(Element.ALIGN_CENTER);
            ArrayList<Paragraph> dayCell = new ArrayList<>();
            dayCell.add(Day);
            dayList.add(dayCell);

            for(Periode p :timeslots){
                CellList = new ArrayList<>();


                List<ElementDeModule> elms = elementModuleRepository.findByDayOfWeekAndPeriodeAndClasse(day,p, classe.getId());
                System.out.println("Elements "+elms.size());
                if(elms.size()>0){
                    Paragraph Libele = new Paragraph(elms.get(0).getLibelle()+" ("+elms.get(0).getVolumeHoraire()+")",Calibri1);
                    Libele.setAlignment(Element.ALIGN_CENTER);
                    Paragraph Prof = new Paragraph(elms.get(0).getEnseignant().getNom()+" "+elms.get(0).getEnseignant().getPrenom(),Calibri3);
                    Prof.setAlignment(Element.ALIGN_CENTER);
                    Paragraph Salle = new Paragraph("Salle : "+elms.get(0).getSalle().getTypeSalle()+" "+elms.get(0).getSalle().getNumSalle(),Quatt);
                    Salle.setAlignment(Element.ALIGN_CENTER);
                    CellList.add(Libele);
                    CellList.add(Prof);
                    CellList.add(Salle);

                }
                else{
                    CellList.add(new Paragraph(""));
                }
                dayList.add(CellList);



            }
            System.out.println(dayList);
            listRows.put(rowNumber, dayList);
            rowNumber++;


        }

        Paragraph samedi = new Paragraph("\n\nSamedi\n\n\n",Calibri2);
        List<Paragraph> Cellist = new ArrayList<>();
        Cellist.add(samedi);


        List<List<Paragraph>> DayList = new ArrayList<>();
        DayList.add(Cellist);

        Cellist = new ArrayList<>();

        Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
        DayList.add(Cellist);
        Cellist = new ArrayList<>();
        Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
        DayList.add(Cellist);
        Cellist = new ArrayList<>();
        Cellist.add(new Paragraph(""));
        DayList.add(Cellist);
        listRows.put(6, DayList);

        listRows.forEach((index,userDetailRow) -> {
            userDetailRow.forEach(paragraphs -> {
                Cell cell = new Cell();
                paragraphs.forEach(paragraph ->{
                    cell.add(paragraph);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);

                });
                myTable.addCell(cell);


            });
        });
        try {


            final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());


            myPDFDoc.open();  // Open the Document

            /* Here we add some metadata to the generated pdf */
            myPDFDoc.addTitle("Emplois du temps "+classe.getLibelle());
            myPDFDoc.addSubject("Generation emplois du temps pour ENSET");
            myPDFDoc.addKeywords("ENSET, "+classe.getLibelle());
            myPDFDoc.addCreator("ENSET");
            myPDFDoc.addAuthor("ENSET");
            /* End of the adding metadata section */

            // Create a Font object


            FontFactory.register("backEnd/src/main/resources/Fonts/COMIC.ttf");
            FontFactory.register("backEnd/src/main/resources/Fonts/times new roman.ttf");

            Font font1 = FontFactory.getFont("Comic Sans MS", 14, Font.UNDERLINE);
            Font font2 = FontFactory.getFont("Times New Roman", 14);
            Font font3 = FontFactory.getFont("Times New Roman", 12);
            Font font4 = FontFactory.getFont("Calibri", 20,Font.BOLD);
            Font font5 = FontFactory.getFont("Calibri", 18,Font.BOLD);
            Font font6 = FontFactory.getFont("Calibri", 16, Font.BOLD,Color.RED);
            int year = new Date().getYear();
            String text1 = "Département : "+classe.getFiliere().getDepartement().getLibelle();
            String text2 = "EMPLOI DU TEMPS";
            String text3 = Integer.toString(year+1900)+" / "+Integer.toString(year+1+1900);
            String text4 = "FI "+classe.getFiliere().getLibelle()+" - "+classe.getLibelle();
            String text5 = "Semestre "+classe.getLibelle().split(" ")[1];
            String text6 = "Provisoire";

            // Create a paragraph with the new font
            Paragraph paragraph1 = new Paragraph(text1,font1);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph2 = new Paragraph(text2,font2);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph3 = new Paragraph(text3,font3);
            paragraph3.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph4 = new Paragraph(text4,font4);
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph5 = new Paragraph(text5,font5);
            paragraph5.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph6 = new Paragraph(text6,font6);
            paragraph6.setAlignment(Element.ALIGN_CENTER);

            Image headerImage = Image.getInstance("backEnd/src/main/resources/header.jpg");
            Image footerImage = Image.getInstance("backEnd/src/main/resources/footer.jpg");
            float headerWidth = PageSize.A4.getWidth();
            float headerHeight = 50f;  // Adjust the height as needed
            float footerWidth = PageSize.A4.getWidth();
            float footerHeight = 50f;  // Adjust the height as needed
            Rectangle headerRect = new Rectangle(headerWidth, headerHeight);
            Rectangle footerRect = new Rectangle(footerWidth, footerHeight);
            headerImage.setAbsolutePosition(0, PageSize.A4.getHeight() - headerHeight - 10f);
            headerImage.scaleToFit(headerWidth, headerHeight);
            myPDFDoc.add(headerImage);

            // Add footer image to the bottom of each page
            footerImage.setAbsolutePosition(0, 10f);
            footerImage.scaleToFit(footerWidth, footerHeight);footerImage.scaleToFit(footerWidth, footerHeight);
            myPDFDoc.add(footerImage);



            myPDFDoc.add(paragraph1);
            myPDFDoc.add(paragraph2);
            myPDFDoc.add(paragraph3);
            myPDFDoc.add(paragraph4);
            myPDFDoc.add(paragraph5);
            myPDFDoc.add(paragraph6);

            // Adding an empty line
            myPDFDoc.add(new Paragraph(Chunk.NEWLINE));


            // 4)Finally add the table to the document
            myPDFDoc.add(myTable);

            myPDFDoc.close();
            pdfWriter.close();
        } catch (IOException de) {
            System.err.println(de.getMessage());
        }
    }


    public void ClassesPDF(HttpServletResponse response) throws IOException {
        dataFromDb.loadDataFromDatabase();


        Document myPDFDoc = new Document(PageSize.A4,
                40f,   // left
                40f,   // right
                70f,  // top
                70f); // down

        final PdfWriter pdfWriter = PdfWriter.getInstance(myPDFDoc, response.getOutputStream());
        for(Classe classe:DataFromDb.classes){

            // Set TimePeriods in Timetable
            timeslots = Periode.values();
            days = new ArrayList<>();
            days.add(DayOfWeek.MONDAY);
            days.add(DayOfWeek.TUESDAY);
            days.add(DayOfWeek.WEDNESDAY);
            days.add(DayOfWeek.THURSDAY);
            days.add(DayOfWeek.FRIDAY);



            //////////////////////////////////////////////////////////////////////////




            // Define a string as title
            String title = classe.getLibelle();

            //1) Let's create a Table object
            Table myTable = new Table(5); // 3 columns

            FontFactory.register("backEnd/src/main/resources/Fonts/QuattrocentoSans-Italic.ttf");


            FontFactory.register("backEnd/src/main/resources/Fonts/Calibri Regular.ttf");

            Font Calibri1 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

            Font Calibri2 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10,Font.BOLD);

            Font Calibri3 = FontFactory.getFont("Calibri", BaseFont.WINANSI, 10);

            Font Quatt = FontFactory.getFont("Quattrocento Sans Italic", BaseFont.WINANSI, 9,Font.ITALIC, Color.BLUE);



            float[] columnWidths = { 25f, 40f, 40f, 40f, 40f }; // Adjust the values as needed
            myTable.setWidths(columnWidths);
            myTable.setPadding(2f);
            myTable.setWidth(100f);

            //2) Create the header of the table
            ArrayList<String> headerTable = new ArrayList<>();
            headerTable.add("");
            headerTable.add("08h:30 - 10h:30");
            headerTable.add("10h:30 - 12h:30");
            headerTable.add("14h - 16h");
            headerTable.add("16h - 18h");

            headerTable.forEach(e -> {
                Paragraph paragraph = new Paragraph(e,Calibri1);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                Cell current = new Cell(paragraph);
                current.setHeader(true);
                current.setHorizontalAlignment(Element.ALIGN_CENTER);
                current.setBackgroundColor(Color.LIGHT_GRAY);
                myTable.addCell(current);
            });





            // 3) Then create a list of rows and add them to the table
            LinkedHashMap<Integer, List<List<Paragraph>>> listRows = new LinkedHashMap<>();
            int rowNumber = 1;


            for(DayOfWeek day:days){
                List<Paragraph> CellList = new ArrayList<>();
                List<List<Paragraph>> dayList = new ArrayList<>();
                Paragraph Day;
                if(rowNumber==1){
                    Day = new Paragraph("\n\nLundi\n\n\n",Calibri2);
                } else if (rowNumber==2) {
                    Day = new Paragraph("\n\nMardi\n\n\n",Calibri2);
                } else if (rowNumber==3) {
                    Day = new Paragraph("\n\nMercredi\n\n\n",Calibri2);
                } else if (rowNumber==4) {
                    Day = new Paragraph("\n\nJeudi\n\n\n",Calibri2);
                } else{
                    Day = new Paragraph("\n\nVendredi\n\n\n",Calibri2);
                }
                Day.setAlignment(Element.ALIGN_CENTER);
                ArrayList<Paragraph> dayCell = new ArrayList<>();
                dayCell.add(Day);
                dayList.add(dayCell);

                for(Periode p :timeslots){
                    CellList = new ArrayList<>();


                    List<ElementDeModule> elms = elementModuleRepository.findByDayOfWeekAndPeriodeAndClasse(day,p, classe.getId());
                    System.out.println("Elements "+elms.size());
                    if(elms.size()>0){
                        Paragraph Libele = new Paragraph(elms.get(0).getLibelle()+" ("+elms.get(0).getVolumeHoraire()+")",Calibri1);
                        Libele.setAlignment(Element.ALIGN_CENTER);
                        Paragraph Prof = new Paragraph(elms.get(0).getEnseignant().getNom()+" "+elms.get(0).getEnseignant().getPrenom(),Calibri3);
                        Prof.setAlignment(Element.ALIGN_CENTER);
                        Paragraph Salle = new Paragraph("Salle : "+elms.get(0).getSalle().getTypeSalle()+" "+elms.get(0).getSalle().getNumSalle(),Quatt);
                        Salle.setAlignment(Element.ALIGN_CENTER);
                        CellList.add(Libele);
                        CellList.add(Prof);
                        CellList.add(Salle);

                    }
                    else{
                        CellList.add(new Paragraph(""));
                    }
                    dayList.add(CellList);



                }
                System.out.println(dayList);
                listRows.put(rowNumber, dayList);
                rowNumber++;


            }

            Paragraph samedi = new Paragraph("\n\nSamedi\n\n\n",Calibri2);
            List<Paragraph> Cellist = new ArrayList<>();
            Cellist.add(samedi);


            List<List<Paragraph>> DayList = new ArrayList<>();
            DayList.add(Cellist);

            Cellist = new ArrayList<>();

            Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
            DayList.add(Cellist);
            Cellist = new ArrayList<>();
            Cellist.add(new Paragraph("\nControles \net ratrapages",Calibri1));
            DayList.add(Cellist);
            Cellist = new ArrayList<>();
            Cellist.add(new Paragraph(""));
            DayList.add(Cellist);
            listRows.put(6, DayList);

            listRows.forEach((index,userDetailRow) -> {
                userDetailRow.forEach(paragraphs -> {
                    Cell cell = new Cell();
                    paragraphs.forEach(paragraph ->{
                        cell.add(paragraph);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(Element.ALIGN_CENTER);

                    });
                    myTable.addCell(cell);


                });
            });

            myPDFDoc.open();  // Open the Document

            /* Here we add some metadata to the generated pdf */
            myPDFDoc.addTitle("Emplois du temps "+classe.getLibelle());
            myPDFDoc.addSubject("Generation emplois du temps pour ENSET");
            myPDFDoc.addKeywords("ENSET, "+classe.getLibelle());
            myPDFDoc.addCreator("ENSET");
            myPDFDoc.addAuthor("ENSET");
            /* End of the adding metadata section */

            // Create a Font object


            FontFactory.register("backEnd/src/main/resources/Fonts/COMIC.ttf");
            FontFactory.register("backEnd/src/main/resources/Fonts/times new roman.ttf");

            Font font1 = FontFactory.getFont("Comic Sans MS", 14, Font.UNDERLINE);
            Font font2 = FontFactory.getFont("Times New Roman", 14);
            Font font3 = FontFactory.getFont("Times New Roman", 12);
            Font font4 = FontFactory.getFont("Calibri", 20,Font.BOLD);
            Font font5 = FontFactory.getFont("Calibri", 18,Font.BOLD);
            Font font6 = FontFactory.getFont("Calibri", 16, Font.BOLD,Color.RED);
            int year = new Date().getYear();
            String text1 = "Département : "+classe.getFiliere().getDepartement().getLibelle();
            String text2 = "EMPLOI DU TEMPS";
            String text3 = Integer.toString(year+1900)+" / "+Integer.toString(year+1+1900);
            String text4 = "FI "+classe.getFiliere().getLibelle()+" - "+classe.getLibelle();
            String text5 = "Semestre "+classe.getLibelle().split(" ")[1];
            String text6 = "Provisoire";

            // Create a paragraph with the new font
            Paragraph paragraph1 = new Paragraph(text1,font1);
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph2 = new Paragraph(text2,font2);
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph3 = new Paragraph(text3,font3);
            paragraph3.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph4 = new Paragraph(text4,font4);
            paragraph4.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph5 = new Paragraph(text5,font5);
            paragraph5.setAlignment(Element.ALIGN_CENTER);
            Paragraph paragraph6 = new Paragraph(text6,font6);
            paragraph6.setAlignment(Element.ALIGN_CENTER);
            
            Image headerImage = Image.getInstance("backEnd/src/main/resources/header.jpg");
            Image footerImage = Image.getInstance("backEnd/src/main/resources/footer.jpg");
            float headerWidth = PageSize.A4.getWidth();
            float headerHeight = 50f;  // Adjust the height as needed
            float footerWidth = PageSize.A4.getWidth();
            float footerHeight = 50f;  // Adjust the height as needed
            Rectangle headerRect = new Rectangle(headerWidth, headerHeight);
            Rectangle footerRect = new Rectangle(footerWidth, footerHeight);
            headerImage.setAbsolutePosition(0, PageSize.A4.getHeight() - headerHeight - 10f);
            headerImage.scaleToFit(headerWidth, headerHeight);
            myPDFDoc.add(headerImage);

            // Add footer image to the bottom of each page
            footerImage.setAbsolutePosition(0, 10f);
            footerImage.scaleToFit(footerWidth, footerHeight);footerImage.scaleToFit(footerWidth, footerHeight);
            myPDFDoc.add(footerImage);



            myPDFDoc.add(paragraph1);
            myPDFDoc.add(paragraph2);
            myPDFDoc.add(paragraph3);
            myPDFDoc.add(paragraph4);
            myPDFDoc.add(paragraph5);
            myPDFDoc.add(paragraph6);

            // Adding an empty line
            myPDFDoc.add(new Paragraph(Chunk.NEWLINE));



            // 4)Finally add the table to the document
            myPDFDoc.add(myTable);
            myPDFDoc.add(new Paragraph(Chunk.NEXTPAGE));


        }

        myPDFDoc.close();
        pdfWriter.close();


    }


}

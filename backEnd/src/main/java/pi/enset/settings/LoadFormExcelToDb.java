package pi.enset.settings;

import lombok.AllArgsConstructor;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import pi.enset.entities.Module;
import pi.enset.entities.*;
import pi.enset.entities.enums.NumeroSemester;
import pi.enset.entities.enums.TypeSalle;
import pi.enset.services.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LoadFormExcelToDb {
    private ISalleService iSalleService;
    private IFiliereService iFiliereService;
    private IClasseService iClasseService;
    private IEnseignantService iEnseignantService;
    private IElementDeModuleService iElementDeModuleService;
    private IModuleService iModuleService;
    private ISemestreService iSemestreService;
    private IDepartementService iDepartementService;


    public boolean PutDataToDb(String path) throws IOException {
        boolean isImported = true;
        try {
            Workbook workbook = WorkbookFactory.create(new File(path));
            // Retrieving the number of sheets in the Workbook
            int numberOfSheets = workbook.getNumberOfSheets();
            // Getting the Sheet at index zero
            List<Semestre> semestres = new ArrayList<>();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet.getSheetName().contains("SALLES")) {
                    Salle salle = null;
                    for (Row row : sheet) {
                        if (row.getRowNum() >= 4) {
                            for (Cell cell : row) {

                                int salleNm = -1;
                                if (cell.getColumnIndex() == 2) {
                                    salle = new Salle();
                                    if (!cell.getStringCellValue().equals("")) {
                                        salleNm = Integer.parseInt(cell.getStringCellValue().split("_")[1]);
                                        salle.setNumSalle(salleNm);
                                    }
                                }
                                if (cell.getColumnIndex() == 3) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        assert salle != null;
                                        salle.setTypeSalle(TypeSalle.valueOf(cell.getStringCellValue()));
                                        salle.setCapacite((int) cell.getRow().getCell(4).getNumericCellValue());
                                    }
                                    iSalleService.addSalle(salle);
                                }
                            }
                        }
                    }
                }
                if (sheet.getSheetName().contains("ENSEIGNANTS")) {
                    Enseignant e = null;
                    for (Row row : sheet) {
                        if (row.getRowNum() >= 4) {
                            for (Cell cell : row) {
                                if (cell.getColumnIndex() == 2) {
                                    e = new Enseignant();
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setNom(cell.getStringCellValue().split("_")[0]);
                                        e.setPrenom(cell.getStringCellValue().split("_")[1]);
                                    }
                                }
                                if (cell.getColumnIndex() == 3) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setSpecialite(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 4) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setTel(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 5) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setEmail(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 6) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setLogin(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 7) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setCne(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 8) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setCivilite(cell.getStringCellValue());
                                    }
                                }
                                if (cell.getColumnIndex() == 9) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        e.setPassword(cell.getStringCellValue());
                                    }
                                }
                                if (iEnseignantService.findEnseignantByNom(e.getNom()).size() == 0) {
                                    iEnseignantService.addEnseignant(e);
                                }
                            }
                        }
                    }
                }
                if (!sheet.getSheetName().contains("SALLES") && !sheet.getSheetName().contains("ENSEIGNANTS")) {
                    Departement departement = new Departement();
                    Filiere filiere = new Filiere();
                    filiere.setClasses(new ArrayList<>());
                    filiere.setLibelle(sheet.getRow(2).getCell(2).getStringCellValue());
                    filiere.setChefFiliere(sheet.getRow(2).getCell(4).getStringCellValue());
                    departement.setLibelle(sheet.getRow(2).getCell(5).getStringCellValue());
                    // Create a DataFormatter to format and get each cell's value as String
                    DataFormatter dataFormatter = new DataFormatter();
                    Semestre semestre = null;
                    Classe classe = null;
                    Module module = null;
                    ElementDeModule element;
                    int j=5;
                    for (Row row : sheet) {
                        if (row.getRowNum() >= 6) {
                            j++;
                            for (Cell cell : row) {
                                /*if (cell.getColumnIndex() == 6){
                                     int Nbeleve=(int) cell.getRow().getCell(7).getNumericCellValue();
                                }*/
                                if (cell.getColumnIndex() == 1) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        semestre = new Semestre();
                                        semestre.setNum(NumeroSemester.valueOf(cell.getStringCellValue()));
                                        semestre.setAnneeUniv(sheet.getRow(0).getCell(2).getStringCellValue());
                                        classe = new Classe();
                                        classe.setNbrEleves((int) sheet.getRow(j).getCell(7).getNumericCellValue());
                                        classe.setLibelle(filiere.getLibelle() + " " + semestre.getNum().toString().charAt(1));
                                        if (iSemestreService.findSemestreByNum(semestre.getNum()).size()==0) {
                                            iSemestreService.addSemestre(semestre);
                                            classe.setSemestre(semestre);
                                        }else {
                                            classe.setSemestre(iSemestreService.findSemestreByNum(semestre.getNum()).get(0));
                                        }

                                        //(int) cell.getRow().getCell(4).getNumericCellValue();
                                        filiere.getClasses().add(classe);
                                        if (iDepartementService.findDepartementByNom(departement.getLibelle()).size() == 0) {
                                            filiere.setDepartement(departement);
                                            iDepartementService.addDepartement(departement);
                                        } else {
                                            filiere.setDepartement(iDepartementService.findDepartementByNom(departement.getLibelle()).get(0));
                                        }
                                        iFiliereService.addFiliere(filiere);
                                    }
                                }
                                if (cell.getColumnIndex() == 2) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        module = new Module();
                                        module.setElementDeModules(new ArrayList<>());
                                        module.setLibelle(cell.getStringCellValue());
                                        //module.setSemestre(semestre);
                                        assert classe != null;
                                        classe.getModules().add(module);
                                    }
                                    assert classe != null;
                                    classe.setFiliere(filiere);
                                    iClasseService.addClasse(classe, filiere.getId());
                                }
                                if (cell.getColumnIndex() == 4) {
                                    if (!cell.getStringCellValue().equals("")) {
                                        element = new ElementDeModule();
                                        element.setLibelle(cell.getStringCellValue());
                                        Enseignant e = null;
                                        if (!cell.getRow().getCell(5).getStringCellValue().equals("")) {
                                            e = new Enseignant();
                                            e.setNom(cell.getRow().getCell(5).getStringCellValue().split("_")[0]);
                                            e.setPrenom(cell.getRow().getCell(5).getStringCellValue().split("_")[1]);
                                            if (iEnseignantService.findEnseignantByNom(e.getNom()).size() != 0) {
                                                element.setEnseignant(iEnseignantService.findEnseignantByNom(e.getNom()).get(0));
                                            } else {
                                                isImported = false;
                                                System.out.println("le prof n'existe pas");
                                            }
                                        }
                                        iModuleService.addModule(module);
                                        element.setVolumeHoraire((int) cell.getRow().getCell(6).getNumericCellValue());
                                        element.setModule(module);
                                        iElementDeModuleService.addElementDeModule(element);
                                        assert module != null;
                                        module.getElementDeModules().add(element);
                                        module.setClasse(classe);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (EncryptedDocumentException | IOException e) {
            isImported = false;
            e.printStackTrace();
        }
        return isImported;
    }
}
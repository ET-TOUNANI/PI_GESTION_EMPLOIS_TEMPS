
import { ElementDeModule } from './../../models/elementModule.models';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Classe } from 'src/app/models/classes.models';
import { Departement } from 'src/app/models/departement.models';
import { Filiere } from 'src/app/models/filieres.models';
import { Semestre } from 'src/app/models/semestre.models';
import { ActionsService } from 'src/app/services/actions.service';
import { ClasseService } from 'src/app/services/classe.service';
import { DepartmentService } from 'src/app/services/department.service';
import { EmploiDeTempsService } from 'src/app/services/emploi-de-temps.service';
import { FiliereService } from 'src/app/services/filiere.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  prof!: boolean;
  public departements:Departement[] = [];
  public filieres:Filiere[] = [];
  public semsters:Semestre[] = [];
  public elementDeModule:ElementDeModule[] = [];
  selectedDepartement: Departement|undefined;
  selectedFiliere: Filiere|undefined;
   selectedSemster: Semestre|undefined;
   classe!:Classe ;
  spinnerExport:boolean=false;
  ready = false;
  admin:boolean = false;
  constructor(private actons:ActionsService,private cookieService: CookieService,private departmentService: DepartmentService,private filiereService: FiliereService,private emploiService:EmploiDeTempsService,private classeService:ClasseService) {

  }
  ngOnInit() {
    this.prof = (this.cookieService.get('role') == 'Ensignant')? true : false; 
    if(this.prof){
       this.ready = true;
      this.emploiService.getEmploiByProf(parseFloat(this.cookieService.get("userId"))).subscribe(
        data=>{
          console.log(data);
          
          this.elementDeModule = data;
          console.log(data);
        }
      )

    }
    else{this.getDepartements();
    this.admin = this.cookieService.check('role');


  }
    
  }

hasModule(days: string, timeSlot: string): boolean {

  let prd= this.getPeriode(timeSlot );
let day = this.changeDay(days).toUpperCase();

  return this.elementDeModule.some(module => module.jour === day && module.periode === prd);
}
getPeriode(timeSlot: string): string {
  let prd = "";
  switch (timeSlot) {
    case "8h30-10h30":
      prd = "P1";
      break;
    case "10h30-12h30":
      prd = "P2";
      break;
    case "14h-16h":
      prd = "P3";
      break;
    case "16h-18h":
      prd = "P4";
      break;
    default:
      break;
  }
  return prd;
}
changeDay(day:string){
  let prd = "";
  switch (day) {
    case "Lundi":
      prd = "Monday";
      break;
    case "Mardi":
      prd = "Tuesday";
      break;
    case "Mercredi":
      prd = "Wednesday";
      break;
    case "Jeudi":
      prd = "Thursday";
      break;
    case "Vendredi":
      prd = "Friday";
      break;
    default:
      break;
}
return prd;
}

getModuleTitle(days: string, timeSlot: string): string {

  let day = this.changeDay(days).toUpperCase();
  let prd= this.getPeriode(timeSlot );
  const module = this.elementDeModule.find(module => module.jour === day && module.periode === prd);
  return module ? module.libelle : '';
}

getModuleRoom(days: string, timeSlot: string): string {
  let day = this.changeDay(days).toUpperCase();
  let prd= this.getPeriode(timeSlot );
  const module = this.elementDeModule.find(module => module.jour === day && module.periode === prd);
  return module ? module.salle.typeSalle+" "+module.salle.numSalle : '';
}

getModuleTeacher(days: string, timeSlot: string): string {
 let day = this.changeDay(days).toUpperCase();
  let prd= this.getPeriode(timeSlot );
  const module = this.elementDeModule.find(module => module.jour === day && module.periode === prd);
  if(!this.prof)
  {
  return module ? module.enseignant.civilite+". "+module.enseignant.prenom+" "+module.enseignant.nom : '';

}else{
return module ? module.module.classe.libelle : '';
}
}
  handleDownloadEmploi(){
     
    Swal.fire({
      title: 'Voulez-vous vraiment Exporter les données ?',
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

      confirmButtonText: 'Oui, exporter !',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
         this.spinnerExport=true;
        if(this.prof)
      {
         this.actons.exportFileProf(parseFloat(this.cookieService.get("userId"))).subscribe(
          data=>{
            this.spinnerExport=false;
            console.log(data)
            // if done, then show a success message
            downloadFile(data, "application/pdf");
          },
          error=>{
            this.spinnerExport=false;
            console.log(error)
            // if done, then show a success message
        Swal.fire(
          'Erreur !',
          'Une erreur est survenue lors de l\'exportation.',
          'error'
        )
          }
        )
      }
      else{
        this.actons.exportFileClasse(this.selectedFiliere!.id).subscribe(
          data=>{
            this.spinnerExport=false;
            console.log(data)
            // if done, then show a success message
            downloadFile(data, "application/pdf");
          },
          error=>{
            this.spinnerExport=false;
            console.log(error)
            // if done, then show a success message
        Swal.fire(
          'Erreur !',
          'Une erreur est survenue lors de l\'exportation.',
          'error'
        )
          }
        )
      }
       

       


        // show a loading spinner
        
        

        
      }
    }
    )
    
  }
  getDepartements(){
    this.departmentService
      .searchDepartments("", 0,20)
      .subscribe(
        (data) => {
          this.departements = data.content;
        }
      );
  }
handleDepartmentChange(target: EventTarget | null) {
  if (target instanceof HTMLSelectElement) {
    const departmentId = parseFloat(target.value);
    
    console.log("departmentId");
    
    console.log(departmentId);
    
    this.selectedDepartement = this.departements.find(
      (department) => department.id === departmentId
    );

    // Call the getFilieres method to update the filieres based on the selected department
    this.getFilieres();
  }
}

handleFiliereChange(target: EventTarget | null) {
  if (target instanceof HTMLSelectElement) {
    const filiereId = parseFloat(target.value);
    
    
    this.selectedFiliere = this.filieres.find(
      (f) => f.id === filiereId
    );

    // Call the getFilieres method to update the filieres based on the selected department
    this.getSemsters();
  }
}
handleSemsterChange(target: EventTarget | null) {
  if (target instanceof HTMLSelectElement) {
    const semsterId = parseFloat(target.value);
    
    
    this.selectedSemster = this.semsters.find(
      (s) => s.id === semsterId
    );

    this.ready = true;
    this.getEmplois(semsterId, this.selectedFiliere!.id, this.selectedDepartement!.id);
  }
}

  getEmplois(semsterId: number, idFiliere: number , idDepartement: number ) {
 
    this.classeService.searchClassesSem(this.selectedFiliere!.libelle,semsterId,0,1).subscribe(
      (data) => {
        let classeId =1;
        this.classe = data.content[0];
    
        classeId = this.classe.id;
     
    
  this.emploiService.getEmploisByClasse(classeId).subscribe(
          data=>{
            this.elementDeModule = data;
            console.log(data);
          }
        )
      }
    );
   
  }


  getFilieres(){
    if(this.selectedDepartement)
    this.departmentService.getFilieres(this.selectedDepartement.id).subscribe(
      (data) => {
        this.filieres = data;
      }
    );

    
    
  }
  getSemsters(){
    if(this.selectedFiliere)
    this.filiereService.getSemsterByFiliere(this.selectedFiliere.id).subscribe(
      (data) => {
        this.semsters = data;
      }
    );

    
    
  }


}
 function downloadFile(data: Blob, arg1: string) {
  const blob = new Blob([data], { type: arg1 });
  const url = window.URL.createObjectURL(blob);
  window.open(url);
}



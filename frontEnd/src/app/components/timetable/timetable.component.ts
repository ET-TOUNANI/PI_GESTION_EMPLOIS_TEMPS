import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Departement } from 'src/app/models/departement.models';
import { Filiere } from 'src/app/models/filieres.models';
import { Semestre } from 'src/app/models/semestre.models';
import { ActionsService } from 'src/app/services/actions.service';
import { DepartmentService } from 'src/app/services/department.service';
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
  selectedDepartement: Departement|undefined;
  selectedFiliere: Filiere|undefined;
   selectedSemster: Semestre|undefined;
  spinnerExport:boolean=false;
  ready = false;
  admin:boolean = false;
  constructor(private actons:ActionsService,private cookieService: CookieService,private departmentService: DepartmentService,private filiereService: FiliereService) {
  }
  ngOnInit() {
    this.prof = (this.cookieService.get('role') == 'Ensignant')? true : false; 
    this.getDepartements();
    this.admin = this.cookieService.check('role');
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
         this.actons.exportFileProf(parseFloat(this.cookieService.get("idUser"))).subscribe(
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



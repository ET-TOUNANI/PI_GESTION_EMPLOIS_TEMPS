import { SalleService } from 'src/app/services/salle.service';
import { DepartmentService } from 'src/app/services/department.service';
import { ProfServiceService } from 'src/app/services/prof-service.service';
import { Component, OnInit } from '@angular/core';
import { ClasseService } from 'src/app/services/classe.service';

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent  implements OnInit
{
  nombreDepartements:number=0;
  nombreProfs:number=0;
  nombreClasses:number=0;
  nombreSalles:number=0;

  
    constructor(private dpService:DepartmentService,private prfService:ProfServiceService,private clsService:ClasseService,private salleService: SalleService) { }
  
    ngOnInit(): void {
      this.getNbDepartements();
      this.getNbProfs();
      this.getNbClasses();
      this.getNbSalles();
    }
    getNbDepartements() {
       this.dpService.searchDepartments("",0,6).subscribe(
      (data) => {
        this.nombreDepartements= data.totalElements;
      }
    );
    }
    getNbProfs() {
      this.prfService.getProfs(0,6).subscribe(
      (data) => {
        this.nombreProfs= data.totalElements;
      }
    );
    }
    getNbClasses() {
      this.clsService.getClasses(0,6).subscribe(
      (data) => {
        this.nombreClasses= data.totalElements;
      }
    );
    }
    getNbSalles() {
      this.salleService.getSalles(0,6).subscribe(
      (data) => {
        this.nombreSalles= data.totalElements;
      }
    );
    }


}

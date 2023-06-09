import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Departement} from "../../../models/departement.models";
import Swal from "sweetalert2";
import {Classe} from "../../../models/classes.models";
import {DepartmentService} from "../../../services/department.service";
import {Router} from "@angular/router";
import {ClasseService} from "../../../services/classe.service";

@Component({
  selector: 'app-add-new-classe',
  templateUrl: './add-new-classe.component.html',
  styleUrls: ['./add-new-classe.component.css']
})
export class AddNewClasseComponent implements OnInit{
  newClassFormGroup!:FormGroup;
  constructor(private fb: FormBuilder,private clService:ClasseService,
              private router: Router) {}
  ngOnInit(): void {
   this.newClassFormGroup=this.fb.group({
    libelle:this.fb.control(null,[Validators.required]),
    nbrEleves:this.fb.control(null,[Validators.required]),
    filiere:this.fb.control(null,[Validators.required]),
   });
  }
  handleAddClasse() {if (this.newClassFormGroup.valid) {
    const newClasse: Classe = this.newClassFormGroup.value;
    this.clService.saveClasse(newClasse).subscribe({
      next: data => {
        Swal.fire('Succès', 'Classe ajouté avec succès', 'success');
        this.router.navigateByUrl('/classes');
      },
      error: err => {
        console.log(err);
      }
    });
  } else {
    Swal.fire('Erreur', 'Veuillez remplir correctement tous les champs du formulaire', 'error');
  }}
}







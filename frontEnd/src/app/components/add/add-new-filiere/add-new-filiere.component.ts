import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {FiliereService} from "../../../services/filiere.service";
import {Filiere} from "../../../models/filieres.models";

@Component({
  selector: 'app-add-new-filiere',
  templateUrl: './add-new-filiere.component.html',
  styleUrls: ['./add-new-filiere.component.css']
})
export class AddNewFiliereComponent{
newFiliereFormGroup!: FormGroup;

constructor(private fb: FormBuilder,private filiereservice : FiliereService, private router:Router) {}

ngOnInit(): void {
  this.newFiliereFormGroup= this.fb.group({

    libelle: this.fb.control(null, [Validators.required]),
    nombreSem: this.fb.control(null, [Validators.required]),
    chefFiliere: this.fb.control(null, [Validators.required]),
    departement: this.fb.control(null, [Validators.required])
  });
}

handleAddFiliere() {
  if (this.newFiliereFormGroup.valid) {
    const newFiliere: Filiere = this.newFiliereFormGroup.value;
    this.filiereservice.saveFiliere(newFiliere).subscribe({
      next: data => {
        Swal.fire('Succès', 'Filiéres ajouté avec succès', 'success');
        this.router.navigateByUrl('/filieres');
      },
      error: err => {
        console.log(err);
      }
    });
  } else {
    Swal.fire('Erreur', 'Veuillez remplir correctement tous les champs du formulaire', 'error');
  }
}
}

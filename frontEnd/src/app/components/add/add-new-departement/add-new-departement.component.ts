import { DepartmentService } from 'src/app/services/department.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Departement } from 'src/app/models/departement.models';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-new-departement',
  templateUrl: './add-new-departement.component.html',
  styleUrls: ['./add-new-departement.component.css']
})
export class AddNewDepartementComponent implements OnInit {
  newDepartementFormGroup!: FormGroup;

  constructor(private fb: FormBuilder,private dpService:DepartmentService,
    private router: Router) {}

  ngOnInit(): void {
    this.newDepartementFormGroup = this.fb.group({
      libelle: this.fb.control(null, [Validators.required]),
      chefDepartement: this.fb.control(null, [Validators.required])
    });
  }

  handleAddDepartement() {
    if (this.newDepartementFormGroup.valid) {
    const newDepart: Departement = this.newDepartementFormGroup.value;
    this.dpService.saveDepartment(newDepart).subscribe({
      next: data => {
        Swal.fire('Succès', 'Département ajouté avec succès', 'success');
        this.router.navigateByUrl('/departements');
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

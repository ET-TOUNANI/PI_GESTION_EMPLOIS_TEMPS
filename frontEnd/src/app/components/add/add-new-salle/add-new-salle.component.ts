import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Salle } from 'src/app/models/salles.models';
import { SalleService } from 'src/app/services/salle.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-new-salle',
  templateUrl: './add-new-salle.component.html',
  styleUrls: ['./add-new-salle.component.css']
})
export class AddNewSalleComponent {
  newSalleFormGroup!: FormGroup;

  constructor(private fb: FormBuilder,private dpService:SalleService,
    private router: Router) {}

  ngOnInit(): void {
    this.newSalleFormGroup = this.fb.group({
      capacite: this.fb.control(null, [Validators.required]),
      typeSalle: this.fb.control(null, [Validators.required]),
      numSalle: this.fb.control(null, [Validators.required])
    });
  }

  handleAddDepartement() {
    if (this.newSalleFormGroup.valid) {
    const newSalle: Salle = this.newSalleFormGroup.value;
    this.dpService.saveSalle(newSalle).subscribe({
      next: data => {
        Swal.fire('Succès', 'Salle ajouté avec succès', 'success');
        this.router.navigateByUrl('/salles');
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

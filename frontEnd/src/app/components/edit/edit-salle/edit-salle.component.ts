import { FormBuilder, FormGroup } from '@angular/forms';
import { Salle } from './../../../models/salles.models';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { SalleService } from 'src/app/services/salle.service';

@Component({
  selector: 'app-edit-salle',
  templateUrl: './edit-salle.component.html',
  styleUrls: ['./edit-salle.component.css']
})
export class EditSalleComponent {
 editSalleFormGroup!: FormGroup;
  salle!: Salle;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private salleService: SalleService
  ) {
    this.salle = this.router.getCurrentNavigation()?.extras.state as Salle;
  }

  ngOnInit(): void {
    this.editSalleFormGroup = this.fb.group({
      capacite: [''],
      numSalle: [''],
      typeSalle: [''],
    });
    this.setFormValues();
  }
  setFormValues() {
    if (this.salle) {
      this.editSalleFormGroup.patchValue({
        capacite: this.salle.capacite,
        numSalle: this.salle.numSalle,
        typeSalle: this.salle.typeSalle,
      });
    }
  }


  handleUpdateSalle() {

    if (this.editSalleFormGroup.valid) {
      this.salleService
        .updateSalle(this.salle.id, this.editSalleFormGroup.value)
        .subscribe((data) => {
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Salle modifiée avec succès',
            showConfirmButton: false,
            timer: 1500,
          });
          this.router.navigateByUrl('/salles');
        });
    }
    }
  
}

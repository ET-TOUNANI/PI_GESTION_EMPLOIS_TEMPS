import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Departement } from 'src/app/models/departement.models';
import { DepartmentService } from 'src/app/services/department.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-departement',
  templateUrl: './edit-departement.component.html',
  styleUrls: ['./edit-departement.component.css']
})
export class EditDepartementComponent implements OnInit {
  editDepartFormGroup!: FormGroup;
  depart!: Departement;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private dpService: DepartmentService
  ) {
    this.depart = this.router.getCurrentNavigation()?.extras.state as Departement;
  }

  ngOnInit(): void {
    this.editDepartFormGroup = this.fb.group({
      libelle: [''],
      chefDepartement: ['']
    });
    this.setFormValues();
  }
  setFormValues() {
    if (this.depart) {
      this.editDepartFormGroup.patchValue({
        libelle: this.depart.libelle,
        chefDepartement: this.depart.chefDepartement
      });
    }
  }


  handleUpdateDepart() {
    
    if (this.editDepartFormGroup.valid && this.depart) {
      const updatedDepart: Departement = {
        ...this.depart,
        ...this.editDepartFormGroup.value
      };
      this.dpService.updateDepartment(updatedDepart.id,updatedDepart).subscribe((data) => {
          Swal.fire( 'Succès', 'Département modifié avec succès','success');
          this.router.navigateByUrl('/departements');
        }
      
      );

    }
  }
}

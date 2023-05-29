import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Prof } from 'src/app/models/prof.models';
import { ProfServiceService } from 'src/app/services/prof-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-prof',
  templateUrl: './edit-prof.component.html',
  styleUrls: ['./edit-prof.component.css']
})
export class EditProfComponent implements OnInit {
  editProfFormGroup!: FormGroup;
  prof!: Prof;

  
  constructor(private profService: ProfServiceService,
    private fb: FormBuilder,
    private router: Router,private route : ActivatedRoute) {
    this.prof=this.router.getCurrentNavigation()?.extras.state as Prof;
  }

  ngOnInit(): void {
    this.editProfFormGroup = this.fb.group({
      prenom: [''],
      nom: [''],
      civilite: [''],
      cne: [''],
      email: [''],
      specialite: [''],
      tel: [''],
      login: [''],
      password: ['']
    });

    this.setFormValues();
  }

  setFormValues() {
    if (this.prof) {
      this.editProfFormGroup.patchValue({
        prenom: this.prof.prenom,
        nom: this.prof.nom,
        civilite: this.prof.civilite,
        cne: this.prof.cne,
        email: this.prof.email,
        specialite: this.prof.specialite,
        tel: this.prof.tel,
        login: this.prof.login,
        password: this.prof.password
      });
    }
  }

  handleUpdateProf() {
    if (this.editProfFormGroup.valid && this.prof) {
      const updatedProf: Prof = {
        ...this.prof,
        ...this.editProfFormGroup.value
      };

      this.profService.updateProf(updatedProf.id,updatedProf).subscribe((data) => {
         Swal.fire('Succès', 'Professeur modifié avec succès', 'success');
        this.router.navigateByUrl('/profs');
      });
    }
  }
}

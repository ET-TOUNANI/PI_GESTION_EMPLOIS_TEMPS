import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Prof } from 'src/app/models/prof.models';
import { ProfServiceService } from 'src/app/services/prof-service.service';

@Component({
  selector: 'app-add-new-prof',
  templateUrl: './add-new-prof.component.html',
  styleUrls: ['./add-new-prof.component.css']
})
export class AddNewProfComponent {
  newProfFormGroup!: FormGroup;

  constructor(private fb: FormBuilder,private profService : ProfServiceService, private router:Router) {}

  ngOnInit(): void {
    this.newProfFormGroup = this.fb.group({
     
      nom: this.fb.control(null, [Validators.required]),
       cne: this.fb.control(null, [Validators.required]),
      prenom: this.fb.control(null, [Validators.required]),
      tel: this.fb.control(null, [Validators.required]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
       civilite: this.fb.control(null, [Validators.required]),
      login: this.fb.control(null, [Validators.required]),
      password: this.fb.control(null, [Validators.required]),
      specialite: this.fb.control(null, [Validators.required])
    });
  }

  handleAddProf() {
    if (this.newProfFormGroup.valid) {
      const newProf: Prof = this.newProfFormGroup.value;
      this.profService.saveProf(newProf).subscribe({
        next : data=>{
          alert("Professeur has been successfully saved!");
          //this.newCustomerFormGroup.reset();
          this.router.navigateByUrl("/enseignants")
        },
        error : err => {
          console.log(err);

      }})
      
      
      
    }
  }
}

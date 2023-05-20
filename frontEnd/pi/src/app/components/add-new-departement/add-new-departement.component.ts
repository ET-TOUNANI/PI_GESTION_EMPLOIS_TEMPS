import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-new-departement',
  templateUrl: './add-new-departement.component.html',
  styleUrls: ['./add-new-departement.component.css']
})
export class AddNewDepartementComponent implements OnInit {
handleAddDepartement() {


}
  newDepartementFormGroup!:FormGroup;
  constructor(private fb:FormBuilder){

  }
  ngOnInit(): void {
    this.newDepartementFormGroup=this.fb.group({
      libelle:this.fb.control(null,[Validators.required]),
      chefDepartement:this.fb.control(null,[Validators.required])
    });
    
  }
  

}

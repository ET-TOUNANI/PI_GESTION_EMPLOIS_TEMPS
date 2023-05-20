import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-new-classe',
  templateUrl: './add-new-classe.component.html',
  styleUrls: ['./add-new-classe.component.css']
})
export class AddNewClasseComponent implements OnInit{
handleAddClasse() {
throw new Error('Method not implemented.');
}
  newClassFormGroup!:FormGroup;
  constructor(private fb:FormBuilder){}
  ngOnInit(): void {
   this.newClassFormGroup=this.fb.group({
    libelle:this.fb.control(null,[Validators.required]),
    nbrEleves:this.fb.control(null,[Validators.required]),
    filiere:this.fb.control(null,[Validators.required]),
   
   });
  }
 // handleAddClasse() {}
}

  


  
  

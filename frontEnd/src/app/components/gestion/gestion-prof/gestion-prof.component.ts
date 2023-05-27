import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Prof } from 'src/app/models/prof.models';
import { ProfServiceService } from 'src/app/services/prof-service.service';

@Component({
  selector: 'app-gestion-prof',
  templateUrl: './gestion-prof.component.html',
  styleUrls: ['./gestion-prof.component.css']
})
export class GestionProfComponent implements OnInit {
handleEditeProf(_t78: Prof) {
throw new Error('Method not implemented.');
}

  profs!: Prof[] ;
  errorMessage!: string;
  searchFormGroup! : FormGroup ;


  constructor(private profService : ProfServiceService,private fb : FormBuilder) { }

  ngOnInit(): void {
     this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    this.handleSearchCustomers();
  }
   handleSearchCustomers() {
    let kw=this.searchFormGroup?.value.keyword;
    this.profService.searchProfs(kw).subscribe({
        next : (data)=>{
          this.profs=data;
        },
        error : (err)=>{
          this.errorMessage=err;
          console.log(err);
        }
      });
  }
  handleDeleteProf(prof: Prof) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.profService.deleteProf(prof.id).subscribe({   next : (data) => {
      let index=this.profs.indexOf(prof);
      this.profs.slice(index,1);
      return data;
    } ,error : err => {
      this.errorMessage=err;
    }
    })
}
}

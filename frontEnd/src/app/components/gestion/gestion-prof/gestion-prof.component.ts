import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Prof } from 'src/app/models/prof.models';
import { ProfServiceService } from 'src/app/services/prof-service.service';

@Component({
  selector: 'app-gestion-prof',
  templateUrl: './gestion-prof.component.html',
  styleUrls: ['./gestion-prof.component.css']
})
export class GestionProfComponent implements OnInit {


  profs!: Prof[] ;
  errorMessage!: string;
  searchFormGroup! : FormGroup ;
  page:number=0;
size:number=10;
totalPages:number=0;
  currentPage:number=0;
  constructor(private profService : ProfServiceService,private fb : FormBuilder,private router:Router) { }

  ngOnInit(): void {
     this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    this.handleSearchCustomers();
  }
  handleEditeProf(profedit: Prof) {
    ///
    this.router.navigateByUrl("/profs/edit")
  }
   handleSearchCustomers() {
    let kw=this.searchFormGroup?.value.keyword;
    this.profService.searchProfs(kw,this.page,this.size).subscribe({
        next : (data)=>{
          this.profs=data.content;
           this.totalPages=data.totalPages;
           this.currentPage=data.number;
           console.log(data);
           
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

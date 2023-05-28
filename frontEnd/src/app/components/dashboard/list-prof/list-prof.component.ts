import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Prof } from 'src/app/models/prof.models';
import { ProfServiceService } from 'src/app/services/prof-service.service';

@Component({
  selector: 'app-list-prof',
  templateUrl: './list-prof.component.html',
  styleUrls: ['./list-prof.component.css']
})
export class ListProfComponent  implements OnInit{
  profs!: Prof[] ;

  errorMessage!: string;
  searchFormGroup! : FormGroup ;
  page:number=0;
  size:number=5;
  totalPages:number=0;
  currentPage:number=0;
  constructor(private profService : ProfServiceService,private fb : FormBuilder){}
  ngOnInit(): void {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    
     this.handleSearchCustomers();
   }
    handleSearchCustomers() {
     let kw=this.searchFormGroup?.value.keyword;
     this.profService.getProfs(this.page,this.size).subscribe({
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

}

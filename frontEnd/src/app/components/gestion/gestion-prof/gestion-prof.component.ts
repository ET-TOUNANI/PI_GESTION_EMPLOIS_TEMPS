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
  profs!: Prof[];
  errorMessage!: string;
  searchFormGroup!: FormGroup;
  page: number = 0;
  size: number = 3;
  totalPages: number = 0;
  currentPage: number = 0;
  totalelements:number=0;
  displayedPages: number[] = [];
  option1:number=0;
  option2:number=0;
  option3:number=0;
  option4:number=0;

  constructor(
    private profService: ProfServiceService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });
    this.handleSearchCustomers();
    this.option1=Math.floor(this.totalelements/4)
    this.option2=Math.floor(this.totalelements/2)
    this.option3=Math.floor(this.totalelements/4)*3
    this.option4=Math.floor(this.totalelements)
  }

  handleEditeProf(profedit: Prof) {
    this.router.navigateByUrl('/profs/edit');
  }

  handleSearchCustomers() {
    const kw = this.searchFormGroup?.value.keyword;
    this.profService.searchProfs(kw, this.page, this.size).subscribe({
      next: (data) => {
        this.profs = data.content;
        this.totalPages = data.totalPages;
        this.currentPage = data.number;
        this.totalelements=data.numberOfElements;
        this.setDisplayedPages();
        console.log(data);
      },
      error: (err) => {
        this.errorMessage = err;
        console.log(err);
      }
    });
  }

  handleDeleteProf(prof: Prof) {
    const conf = confirm('Are you sure?');
    if (!conf) return;
    this.profService.deleteProf(prof.id).subscribe({
      next: (data) => {
        const index = this.profs.indexOf(prof);
        this.profs.splice(index, 1); // Corrected the method name to splice instead of slice
        return data;
      },
      error: (err) => {
        this.errorMessage = err;
      }
    });
  }

  setDisplayedPages() {
    this.displayedPages = [];
    const startPage = Math.floor(this.currentPage / 3) * 3;
    for (let i = startPage; i < startPage + 3 && i < this.totalPages; i++) {
      this.displayedPages.push(i);
    }
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.page = page; // Update the page parameter
    this.handleSearchCustomers();
  }

  goToPreviousSet() {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage - 3 >= 0) {
      this.currentPage = startPage - 3;
      this.page = this.currentPage; // Update the page parameter
      this.handleSearchCustomers();
    }
  }

  goToNextSet() {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage + 3 < this.totalPages) {
      this.currentPage = startPage + 3;
      this.page = this.currentPage; // Update the page parameter
      this.handleSearchCustomers();
    }
  }
}

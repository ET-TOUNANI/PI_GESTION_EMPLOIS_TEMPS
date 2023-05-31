import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Salle } from 'src/app/models/salles.models';
import { SalleService } from 'src/app/services/salle.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-gestion-salles',
  templateUrl: './gestion-salles.component.html',
  styleUrls: ['./gestion-salles.component.css']
})
export class GestionSallesComponent implements OnInit {
  salles: Salle[] = [];
  errorMessage: string = '';
  searchFormGroup!: FormGroup;
  page: number = 0;
  size: number = 6;
  totalPages: number = 0;
  currentPage: number = 0;
  totalElements: number = 0;
  displayedPages: number[] = [];

  constructor(
    private salleService: SalleService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control(''),
    });
    this.handleSearchSalles();
  }



  handleEditSalle(salle: Salle) {
    this.router.navigateByUrl('/salles/edit', { state: salle });
  }

  handleDeleteSalle(salle: Salle) {
    Swal.fire({
      title: 'Êtes-vous sûr(e) ?',
      text: "Cette action est irréversible !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimez-la !'
    }).then((result) => {
      if (result.isConfirmed) {
        this.salleService.deleteSalle(salle.id).subscribe();;
        this.salles.splice(this.salles.indexOf(salle), 1);
      }
    });
  }

  handleChangeSize(event: Event) {
    this.size = parseInt((event.target as HTMLInputElement).value);
    this.handleSearchSalles();
  }

  handleSearchSalles() {
    const keyword = this.searchFormGroup?.value.keyword;
    
    this.salleService.searchSalles(keyword, this.page, this.size).subscribe({
      next: (data) => {
        this.salles = data.content;
        this.totalPages = data.totalPages;
        this.currentPage = data.number;
        this.totalElements = data.totalElements;
        this.setDisplayedPages();
      },
      error: (err) => {
        this.errorMessage = err;
        console.log(err);
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

  goToPage(page: number) {
    this.currentPage = page;
    this.page = page;
    this.handleSearchSalles();
  }

  goToPreviousSet() {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage - 3 >= 0) {
      this.currentPage = startPage - 3;
      this.page = this.currentPage;
      this.handleSearchSalles();
    }
  }

  goToNextSet() {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage + 3 < this.totalPages) {
      this.currentPage = startPage + 3;
      this.page = this.currentPage;
      this.handleSearchSalles();
    }
  }
}

import {Component, OnInit} from '@angular/core';
import {Departement} from "../../../models/departement.models";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DepartmentService} from "../../../services/department.service";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {Filiere} from "../../../models/filieres.models";
import {FiliereService} from "../../../services/filiere.service";

@Component({
  selector: 'app-gestion-filiere',
  templateUrl: './gestion-filiere.component.html',
  styleUrls: ['./gestion-filiere.component.css']
})
export class GestionFiliereComponent implements OnInit{
  filieres: Filiere [] = [];
  errorMessage!: string;
  searchFormGroup!: FormGroup;
  page: number = 0;
  size: number = 6;
  totalPages: number = 0;
  currentPage: number = 0;
  totalelements:number=0;
  displayedPages: number[] = [];
  constructor(
    private filiereService: FiliereService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });
    this.handleSearchFilieres();
  }
  handleEditeFiliere(filiereEdit: Filiere) {
    this.router.navigateByUrl('/filieres/edit',{state :filiereEdit});
  }
  handleChangeSize($event: Event) {
    this.size = parseInt((<HTMLInputElement>$event.target).value);
    this.handleSearchFilieres();
  }
  handleSearchFilieres(): void {
    this.filiereService
      .searchFilieres(this.searchFormGroup.value.keyword, this.page, this.size)
      .subscribe(
        (data) => {
          this.filieres = data.content;
          this.totalPages = data.totalPages;
          this.currentPage = data.number;
          this.setDisplayedPages();
        },
        (error) => {
          this.errorMessage = error;
          console.log(error);
        }
      );
  }

  handleDeleteFiliere(filiere: Filiere): void {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: "Vous ne pourrez pas revenir en arrière !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui, supprimez-le !'
    }).then((result) => {
      if (result.isConfirmed) {
        this.filiereService.deleteFiliere(filiere.id).subscribe();
        this.filieres = this.filieres.filter((f) => f.id !== filiere.id);

      }
    });
  }

  setDisplayedPages(): void {
    this.displayedPages = [];
    const startPage = Math.floor(this.currentPage / 3) * 3;
    for (
      let i = startPage;
      i < startPage + 3 && i < this.totalPages;
      i++
    ) {
      this.displayedPages.push(i);
    }
  }

  gotoPage(page: number): void {
    this.currentPage = page;
    this.page = page;
    this.handleSearchFilieres();
  }

  goToPreviousSet(): void {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage - 3 >= 0) {
      this.currentPage = startPage - 3;
      this.page = this.currentPage;
      this.handleSearchFilieres();
    }
  }

  goToNextSet(): void {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage + 3 < this.totalPages) {
      this.currentPage = startPage + 3;
      this.page = this.currentPage;
      this.handleSearchFilieres();
    }
  }

}

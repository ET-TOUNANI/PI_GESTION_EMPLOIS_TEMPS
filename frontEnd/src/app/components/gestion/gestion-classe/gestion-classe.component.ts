import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Classe} from "../../../models/classes.models";
import {Router} from "@angular/router";
import Swal from "sweetalert2";
import {ClasseService} from "../../../services/classe.service";

@Component({
  selector: 'app-gestion-classe',
  templateUrl: './gestion-classe.component.html',
  styleUrls: ['./gestion-classe.component.css']
})
export class GestionClasseComponent implements OnInit {
  classes: Classe [] = [];
  errorMessage!: string;
  searchFormGroup!: FormGroup;
  page: number = 0;
  size: number = 6;
  totalPages: number = 0;
  currentPage: number = 0;
  totalelements:number=0;
  displayedPages: number[] = [];
  constructor(
    private classeService: ClasseService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });
    this.handleSearchClasses();
  }
  handleEditeClasse(classeEdit: Classe) {
    this.router.navigateByUrl('/classes/edit',{state :classeEdit});
  }

  handleChangeSize($event: Event) {
    this.size = parseInt((<HTMLInputElement>$event.target).value);
    this.handleSearchClasses();
  }
  handleSearchClasses(): void {
    this.classeService
      .searchClasses(this.searchFormGroup.value.keyword, this.page, this.size)
      .subscribe(
        (data) => {
          this.classes = data.content;
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

  handleDeleteClasse(classe: Classe): void {
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
        this.classeService.deleteClasse(classe.id).subscribe();
        this.classes= this.classes.filter((c) => c.id !== classe.id);

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
    this.handleSearchClasses();
  }

  goToPreviousSet(): void {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage - 3 >= 0) {
      this.currentPage = startPage - 3;
      this.page = this.currentPage;
      this.handleSearchClasses();
    }
  }

  goToNextSet(): void {
    const startPage = Math.floor(this.currentPage / 3) * 3;
    if (startPage + 3 < this.totalPages) {
      this.currentPage = startPage + 3;
      this.page = this.currentPage;
      this.handleSearchClasses();
    }
  }
}

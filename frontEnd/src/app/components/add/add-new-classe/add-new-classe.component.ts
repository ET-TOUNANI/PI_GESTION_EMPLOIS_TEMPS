import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Classe } from '../../../models/classes.models';
import { ClasseService } from '../../../services/classe.service';
import { Filiere } from '../../../models/filieres.models';
import { FiliereService } from '../../../services/filiere.service';

@Component({
  selector: 'app-add-new-classe',
  templateUrl: './add-new-classe.component.html',
  styleUrls: ['./add-new-classe.component.css']
})
export class AddNewClasseComponent implements OnInit {
  newClassFormGroup!: FormGroup;
  filieres: string[] = [];

  constructor(
    private fb: FormBuilder,
    private clService: ClasseService,
    private filiereService: FiliereService
  ) {}

  ngOnInit(): void {
    this.newClassFormGroup = this.fb.group({
      libelle: [null, Validators.required],
      nbrEleves: [null, Validators.required],
      filiere: [null, Validators.required],
    });

    this.fetchFilieres();
  }

  fetchFilieres() {
    this.filiereService.getAllFilieres().subscribe(
      (filieres: Filiere[]) => {
        this.filieres = filieres.map(filiere => filiere.libelle);
        console.log(this.filieres);
        if (this.filieres.length > 0) {
          this.newClassFormGroup.patchValue({ filiere: this.filieres[0] });
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  handleAddClasse() {
    if (this.newClassFormGroup.valid) {
      const newClasse: Classe = this.newClassFormGroup.value;
      this.clService.saveClasse(newClasse).subscribe(
        (data) => {
          Swal.fire('Succès', 'Classe ajoutée avec succès', 'success');
          // Reset the form
          this.newClassFormGroup.reset();
        },
        (error) => {
          console.log(error.status); // 400
          console.log(error.error); // {timestamp: '2023-06-11T11:25:16.216+00:00', status: 400, error: 'Bad Request', path: '/api/classes'}
          console.log(error.message); // "Http failure response for http://localhost:8082/api/classes: 400 OK"
        }
      );
    } else {
      Swal.fire(
        'Erreur',
        'Veuillez remplir correctement tous les champs du formulaire',
        'error'
      );
    }
  }
}

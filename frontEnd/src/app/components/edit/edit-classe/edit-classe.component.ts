import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from "sweetalert2";
import {ClasseService} from "../../../services/classe.service";
import {Classe} from "../../../models/classes.models";

@Component({
  selector: 'app-edit-classe',
  templateUrl: './edit-classe.component.html',
  styleUrls: ['./edit-classe.component.css']
})
export class EditClasseComponent implements OnInit {
  editClasseFormGroup!: FormGroup;
  classe!: Classe;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private clService: ClasseService
  ) {
    this.classe = this.router.getCurrentNavigation()?.extras.state as Classe;
  }

  ngOnInit(): void {
    this.editClasseFormGroup = this.fb.group({
      libelle: [''],
      nbrEleves: [''],
      filiere: ['']
    });
    this.setFormValues();
  }
  setFormValues() {
    if (this.classe) {
      this.editClasseFormGroup.patchValue({
        libelle: this.classe.libelle,
        nbrEleves: this.classe.nbrEleves,
        filiere: this.classe.filiere.libelle
      });
    }
  }
  handleUpdateClasse() {

    if (this.editClasseFormGroup.valid && this.classe) {
      const updatedClasse: Classe = {
        ...this.classe,
        ...this.editClasseFormGroup.value
      };
      this.clService.updateClasse(updatedClasse.id,updatedClasse).subscribe((data) => {
          Swal.fire( 'Succès', 'Classe modifié avec succès','success');
          this.router.navigateByUrl('/classes');
        }

      );

    }
  }

}

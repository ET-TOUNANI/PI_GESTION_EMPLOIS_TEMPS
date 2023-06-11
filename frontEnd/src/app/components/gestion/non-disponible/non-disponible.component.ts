
import { Component, OnInit } from '@angular/core';
import { NonDesponibilitie } from 'src/app/models/nonDisponibilites.models';


@Component({
  selector: 'app-non-disponible',
  templateUrl: './non-disponible.component.html',
  styleUrls: ['./non-disponible.component.css']
})
export class NonDisponibleComponent {

  nonDispos: NonDesponibilitie[] = [
    {
      id: 1,
      jour: 'Lundi',
      enseignant: {
        id: 1, nom: 'Ahmed', prenom: 'Ahmed',
        civilite: '',
        tel: '',
        cne: '',
        email: '',
        login: '',
        password: '',
        specialite: ''
      },
      periode: '8h-10h'
    },
    {
      id: 2,
      jour: 'Mardi',
       enseignant: {
        id: 1, nom: 'Ahmed', prenom: 'Ahmed',
        civilite: '',
        tel: '',
        cne: '',
        email: '',
        login: '',
        password: '',
        specialite: ''
      },
      periode: '8h-10h'
    },
    {
      id: 3,
      jour: 'Mercredi',
      enseignant: {
        id: 1, nom: 'Ahmed', prenom: 'Ahmed',
        civilite: '',
        tel: '',
        cne: '',
        email: '',
        login: '',
        password: '',
        specialite: ''
      },
      periode: '8h-10h'
    },
    {
      id: 4,
      jour: 'Jeudi',
      enseignant: {
        id: 1, nom: 'Ahmed', prenom: 'Ahmed',
        civilite: '',
        tel: '',
        cne: '',
        email: '',
        login: '',
        password: '',
        specialite: ''
      },
      periode: '8h-10h'
    },
  ];

   gotoPage(page: number): void {
   
  }

  goToPreviousSet(): void {
   
  }

  goToNextSet(): void {
   }
}

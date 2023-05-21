import { Component, OnInit } from '@angular/core';
import { Prof } from 'src/app/models/prof.models';

@Component({
  selector: 'app-gestion-prof',
  templateUrl: './gestion-prof.component.html',
  styleUrls: ['./gestion-prof.component.css']
})
export class GestionProfComponent implements OnInit {
  profs: Prof[] = [
    { id: 1, prenom: 'John', cne:'ggg',nom: 'Doe', tel: '123456789', email: 'john@example.com', login: 'johndoe', specialite: 'Mathematics' ,civilite:'M', password:'123456'},
    { id: 2, prenom: 'Jane',cne:'ddd', nom: 'Smith', tel: '987654321', email: 'jane@example.com', login: 'janesmith', specialite: 'Physics',civilite:'M', password:'454545' }
    // Add more Prof objects as needed
  ];

  constructor() { }

  ngOnInit(): void {
  }
}

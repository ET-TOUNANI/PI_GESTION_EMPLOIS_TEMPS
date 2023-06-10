import { Prof } from './../../models/prof.models';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  prof!: boolean;

  constructor(private cookieService: CookieService) {
  }
  ngOnInit() {
    this.prof = (this.cookieService.get('role') == 'Ensignant')? true : false; 

  }
}
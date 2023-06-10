import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Gestion Emploi du temps';
  isAuthenticated!: boolean;

  constructor(private cookieService: CookieService) {
  }

  ngOnInit() {
    this.isAuthenticated = this.cookieService.check('username');
  }
}

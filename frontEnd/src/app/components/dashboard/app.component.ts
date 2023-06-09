import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Gestion Emploi du temps';
  isAuthenticated!: boolean;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
   this.isAuthenticated = this.authService.isAuthenticated();
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage!: string;
  formGroup!: FormGroup;

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private cookieService: CookieService
  ) {}

  ngOnInit() {
    this.formGroup = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get username() {
    return this.formGroup.get('username');
  }

  get password() {
    return this.formGroup.get('password');
  }

  login() {
    if (this.formGroup.invalid) {
      // Mark form controls as touched to display validation errors
      this.formGroup.markAllAsTouched();
      return;
    }

    const username = this.username?.value;
    const password = this.password?.value;

    this.authService.login(username, password).subscribe(response => {
      if(response.authenticated==true){
        console.log(response);

        this.authService.loggedIn = response.authenticated;
        this.authService.isAdmin = response.admin;
        this.authService.isProf = response.enseignant;
        this.authService.name = response.nom + " " + response.prenom;
        this.authService.token = response.token;
        this.authService.id = response.id;


        this.cookieService.set('username', this.authService.name);
        this.cookieService.set('userId', this.authService.id.toString());
        let role = response.admin ? 'Administrateur' : 'Ensignant';
        this.cookieService.set('role', role);
        // refresh page
        window.location.reload();
        this.router.navigateByUrl('/home');

      }else {
        Swal.fire('Echec', 'Nom d\'utilisateur ou mot de passe incorrect', 'error');

      }
        },
        error => {
           Swal.fire('Echec', 'Nom d\'utilisateur ou mot de passe incorrect', 'error');});

  }
}

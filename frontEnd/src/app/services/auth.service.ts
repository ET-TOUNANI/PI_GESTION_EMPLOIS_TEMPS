import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn: boolean = true;
  private isAdmin: boolean = false;
  private isProf: boolean = false;

  constructor(private http: HttpClient) { }

  isAuthenticated(): boolean {
    return this.loggedIn;
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isProfUser(): boolean {
    return this.isProf;
  }

  login(username: string, password: string): Observable<any> {
    const loginData = { username, password };

    return this.http.post<any>('/api/login', loginData)
      .pipe(
        tap(response => {
          this.loggedIn = response.isAuthenticated;
          this.isAdmin = response.isAdmin;
          this.isProf = response.isProf;
        })
      );
  }

  logout(): Observable<any> {
    return this.http.post<any>('/api/logout', null)
      .pipe(
        tap(() => {
          this.loggedIn = false;
          this.isAdmin = false;
          this.isProf = false;
        })
      );
  }
}

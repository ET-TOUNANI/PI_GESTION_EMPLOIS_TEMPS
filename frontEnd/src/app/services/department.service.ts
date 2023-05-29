import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environment";
import { PageDepartment } from '../models/profPage.models';
import { Departement } from '../models/departement.models';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  constructor(private http: HttpClient) { }

  public searchDepartments(keyword: string, page: number, size: number): Observable<PageDepartment> {
    return this.http.get<PageDepartment>(`${environment.backendHost}/departements/search?keyword=${keyword}&page=${page}&size=${size}`);
  }

  public saveDepartment(department: Departement): Observable<Departement> {
    return this.http.post<Departement>(`${environment.backendHost}/departements`, department);
  }

  public updateDepartment(id: number, department: Departement): Observable<Departement> {
    return this.http.put<Departement>(`${environment.backendHost}/departements/${id}`, department);
  }

  public getDepartment(id: number): Observable<Departement> {
    return this.http.get<Departement>(`${environment.backendHost}/departements/${id}`);
  }

  public deleteDepartment(id: number): Observable<any> {
    return this.http.delete(`${environment.backendHost}/departements/${id}`);
  }
}

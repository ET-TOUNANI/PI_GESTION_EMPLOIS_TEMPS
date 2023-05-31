import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Salle } from '../models/salles.models';
import { PageSalle } from '../models/profPage.models';

@Injectable({
  providedIn: 'root'
})
export class SalleService {
  constructor(private http: HttpClient) { }

  public getSalles(page: number, size: number): Observable<PageSalle> {
    return this.http.get<PageSalle>(environment.backendHost + "/salles?page=" + page + "&size=" + size);
  }

  public searchSalles(keyword: string, page: number, size: number): Observable<PageSalle> {
    return this.http.get<PageSalle>(environment.backendHost + "/salles/search?keyword=" + keyword + "&page=" + page + "&size=" + size);
  }

  public saveSalle(salle: Salle): Observable<Salle> {
    return this.http.post<Salle>(environment.backendHost + "/salles", salle);
  }

  public updateSalle(id: number, salle: Salle): Observable<Salle> {
    return this.http.put<Salle>(environment.backendHost + "/salles/" + id, salle);
  }

  public getSalle(id: number): Observable<Salle> {
    return this.http.get<Salle>(environment.backendHost + "/salles/" + id);
  }

   public deleteSalle(id: number): Observable<any> {
    return this.http.delete(`${environment.backendHost}/salles/${id}`);
  }
}

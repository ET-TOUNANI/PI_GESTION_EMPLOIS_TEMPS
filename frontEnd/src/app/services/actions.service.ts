import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActionsService {

  constructor( private http:HttpClient) { }

   public importFile(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    console.log(formData.get('file'));
    
    
    return this.http.post(environment.backendHost + '/data/import', formData);
  }
  public exportFile(): Observable<Blob> {
    // Set the response type to 'blob' to receive binary data
    return this.http.get(environment.backendHost + "/pdf/classes", { responseType: 'blob' });
  }
  public generateEmploi(): Observable<any> {
    return this.http.get(environment.backendHost + "/emploisDeTemps/generate");
  }
public exportFileProf(id:number): Observable<Blob> {
    // Set the response type to 'blob' to receive binary data
    return this.http.get(environment.backendHost + "/pdf/enseignants/"+id, { responseType: 'blob' });
  }
  public exportFileClasse(id:number): Observable<Blob> {
    // Set the response type to 'blob' to receive binary data
    return this.http.get(environment.backendHost + "/pdf/classes/"+id, { responseType: 'blob' });
  }
}

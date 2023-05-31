import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ActionsService {

  constructor( private http:HttpClient) { }

  public importFile(pathFile: String): Observable<any> {
    return this.http.get(environment.backendHost + "/data/import?path=" + pathFile);
  }
  public exportFile(): Observable<any> {
    return this.http.get(environment.backendHost + "/pdf/classes");
  }
  public generateEmploi(): Observable<any> {
    return this.http.get(environment.backendHost + "/emploisDeTemps/generate");
  }

}

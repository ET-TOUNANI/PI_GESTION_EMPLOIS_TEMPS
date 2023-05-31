import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ElementDeModule } from '../models/elementModule.models';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmploiDeTempsService {

  constructor(private http:HttpClient) { }

  public getEmplois(): Observable<ElementDeModule[]> {
    return this.http.get<ElementDeModule[]>(environment.backendHost + "/elementModules");
  }

}

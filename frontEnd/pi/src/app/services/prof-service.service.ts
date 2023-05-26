import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prof } from '../models/prof.models';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProfServiceService {

   constructor(private http:HttpClient) { }

  public getProfs():Observable<Array<Prof>>{
    return this.http.get<Array<Prof>>(environment.backendHost+"/enseignants")
  }
  public searchProfs(keyword : string):Observable<Array<Prof>>{
    return this.http.get<Array<Prof>>(environment.backendHost+"/enseignants/search?keyword="+keyword)
  }
  public saveProf(Prof: Prof):Observable<Prof>{
    return this.http.post<Prof>(environment.backendHost+"/enseignants",Prof);
  }
  public updateProf(id: number,Prof: Prof):Observable<Prof>{
    return this.http.put<Prof>(environment.backendHost+"/enseignants/"+id,Prof);
  }
  public getProf(id: number):Observable<Prof>{
    return this.http.get<Prof>(environment.backendHost+"/enseignants/"+id);
  }
  public deleteProf(id: number){
    return this.http.delete(environment.backendHost+"/enseignants/"+id);
  }
}

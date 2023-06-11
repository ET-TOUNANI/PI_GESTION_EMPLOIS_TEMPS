import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NonDesponibilitieService {
  searchNonDispo(keyword: any, page: number, size: number) {
    throw new Error('Method not implemented.');
  }

  constructor() { }

}

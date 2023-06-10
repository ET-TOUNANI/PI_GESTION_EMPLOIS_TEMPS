import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  prof!: boolean;

  constructor(private cookieService: CookieService) {
  }
  ngOnInit() {
    this.prof = (this.cookieService.get('role') == 'Ensignant')? true : false; 

  }

}

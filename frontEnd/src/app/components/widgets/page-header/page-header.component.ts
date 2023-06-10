import { Component, Input, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.css']
})
export class PageHeaderComponent implements OnInit{
    name!: string;
  @Input() link!: string;
  @Input() prev!: string;
  @Input() current!: string;
  
  constructor(private cookieService: CookieService) {}

ngOnInit() {
   this.name = this.cookieService.get('username');
  // Use the stored values as needed
}

}

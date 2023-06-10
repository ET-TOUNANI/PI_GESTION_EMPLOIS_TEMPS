import { Component } from '@angular/core';

@Component({
  selector: 'app-index-page',
  templateUrl: './index-page.component.html',
  styleUrls: ['./index-page.component.css']
})
export class IndexPageComponent {
  login:boolean = false;

  constructor() { }
  handleConnected() {
    this.login = true;
  }
}

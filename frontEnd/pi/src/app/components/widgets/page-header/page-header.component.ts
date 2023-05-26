import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-page-header',
  templateUrl: './page-header.component.html',
  styleUrls: ['./page-header.component.css']
})
export class PageHeaderComponent {
    @Input() name!: string;
  @Input() link!: string;
  @Input() prev!: string;
  @Input() current!: string;


}

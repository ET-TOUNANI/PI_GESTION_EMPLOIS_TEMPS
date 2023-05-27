import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  active:number=0;
  sidebarItems = [
  {
    link: "/home",
    title: "Home",
    icon: "fas fa-home"
  },
  {
    link: "/emploiDuTemps",
    title: "Emploi du temps",
    icon: "fas fa-clipboard-list"
  },
  {
    link: "/classes",
    title: "Classes",
    icon: "fas fa-graduation-cap"
  },
  {
    link: "/profs",
    title: "Professeurs",
    icon: "fas fa-chalkboard-teacher"
  },
  {
    link: "/departements",
    title: "Départments",
    icon: "fas fa-building"
  },
  {
    link: "/filieres",
    title: "Filières",
    icon: "fas fa-book-reader"
  },
  {
    link: "/salles",
    title: "Salles",
    icon: "fas fa-clipboard"
  }
];
  constructor() { }
  handleChangeBars(index: number): void {
  this.active = index;
}

}

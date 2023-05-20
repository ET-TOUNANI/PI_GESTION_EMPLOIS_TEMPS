import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/dashboard/app.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { PageHeaderComponent } from './components/page-header/page-header.component';
import { StatistiqueComponent } from './components/statistique/statistique.component';
import { ListProfComponent } from './components/dashboard/list-prof/list-prof.component';
import { ListDepartementsComponent } from './components/dashboard/list-departements/list-departements.component';
import { AddNewProfComponent } from './components/add-new-prof/add-new-prof.component';
import { GestionProfComponent } from './components/gestion-prof/gestion-prof.component';
import { HomeComponent } from './components/home/home.component';
import { GestionFiliereComponent } from './components/gestion-filiere/gestion-filiere.component';
import { AddNewFiliereComponent } from './components/add-new-filiere/add-new-filiere.component';
import { GestionDepartementComponent } from './components/gestion-departement/gestion-departement.component';
import { AddNewDepartementComponent } from './components/add-new-departement/add-new-departement.component';
import { GestionClasseComponent } from './components/gestion-classe/gestion-classe.component';
import { AddNewClasseComponent } from './components/add-new-classe/add-new-classe.component';
import { GestionSallesComponent } from './components/gestion-salles/gestion-salles.component';
import { AddNewSalleComponent } from './components/add-new-salle/add-new-salle.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    PageHeaderComponent,
    StatistiqueComponent,
    ListProfComponent,
    ListDepartementsComponent,
    AddNewProfComponent,
    GestionProfComponent,
    HomeComponent,
    GestionFiliereComponent,
    AddNewFiliereComponent,
    GestionDepartementComponent,
    AddNewDepartementComponent,
    GestionClasseComponent,
    AddNewClasseComponent,
    GestionSallesComponent,
    AddNewSalleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

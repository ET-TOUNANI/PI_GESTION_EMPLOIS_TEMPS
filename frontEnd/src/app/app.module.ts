import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/dashboard/app.component';
import { SidebarComponent } from './components/widgets/sidebar/sidebar.component';
import { NavbarComponent } from './components/widgets/navbar/navbar.component';
import { FooterComponent } from './components/widgets/footer/footer.component';
import { PageHeaderComponent } from './components/widgets/page-header/page-header.component';
import { StatistiqueComponent } from './components/widgets/statistique/statistique.component';
import { ListProfComponent } from './components/dashboard/list-prof/list-prof.component';
import { ListDepartementsComponent } from './components/dashboard/list-departements/list-departements.component';
import { AddNewProfComponent } from './components/add/add-new-prof/add-new-prof.component';
import { GestionProfComponent } from './components/gestion/gestion-prof/gestion-prof.component';
import { HomeComponent } from './components/home/home.component';
import { GestionFiliereComponent } from './components/gestion/gestion-filiere/gestion-filiere.component';
import { AddNewFiliereComponent } from './components/add/add-new-filiere/add-new-filiere.component';
import { GestionDepartementComponent } from './components/gestion/gestion-departement/gestion-departement.component';
import { AddNewDepartementComponent } from './components/add/add-new-departement/add-new-departement.component';
import { GestionClasseComponent } from './components/gestion/gestion-classe/gestion-classe.component';
import { AddNewClasseComponent } from './components/add/add-new-classe/add-new-classe.component';
import { GestionSallesComponent } from './components/gestion/gestion-salles/gestion-salles.component';
import { AddNewSalleComponent } from './components/add/add-new-salle/add-new-salle.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EditProfComponent } from './components/edit/edit-prof/edit-prof.component';

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
    AddNewSalleComponent,
    EditProfComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
     ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

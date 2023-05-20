import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionProfComponent } from './components/gestion-prof/gestion-prof.component';
import { AddNewProfComponent } from './components/add-new-prof/add-new-prof.component';
import { HomeComponent } from './components/home/home.component';
import { GestionFiliereComponent } from './components/gestion-filiere/gestion-filiere.component';
import { AddNewFiliereComponent } from './components/add-new-filiere/add-new-filiere.component';

const routes: Routes = [
    { path :'home' , component: HomeComponent},
    { path :'profs' , component: GestionProfComponent},
    { path :'profs/add' , component: AddNewProfComponent},
    { path :'filieres' , component: GestionFiliereComponent},
    { path :'filieres/add' , component: AddNewFiliereComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

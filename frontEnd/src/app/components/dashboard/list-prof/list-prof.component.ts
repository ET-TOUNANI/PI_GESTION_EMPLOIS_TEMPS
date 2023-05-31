import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Prof } from 'src/app/models/prof.models';
import { ActionsService } from 'src/app/services/actions.service';
import { ProfServiceService } from 'src/app/services/prof-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-prof',
  templateUrl: './list-prof.component.html',
  styleUrls: ['./list-prof.component.css']
})
export class ListProfComponent{
  constructor(private actons:ActionsService) { }

  handleImport(){
    console.log("import")
     // ask if he really wants to import the file
     Swal.fire({
      title: 'Voulez-vous vraiment importer les données ?',
      text: "Fichier doit être au format .xlsx",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

      confirmButtonText: 'Oui, importer !',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
        // if he confirms, then import the file
        // upload  file
        


        // show a loading spinner
        


        // if done, then show a success message
        Swal.fire(
          'Importé !',
          'Le fichier a été importé avec succès.',
          'success'
        )
      }
    }
    )

  }
  handleExport(){
    console.log("export")
     // ask if he really wants to import the file
     Swal.fire({
      title: 'Voulez-vous vraiment Exporter les données ?',
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

      confirmButtonText: 'Oui, exporter !',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
        // if he confirms, then import the file
        // upload  file
        
        this.actons.exportFile().subscribe(
          data=>{
            console.log(data)
          }
        )


        // show a loading spinner
        
        

        // if done, then show a success message
        Swal.fire(
          'Exporter !',
          'Les données ont été exporté avec succès.',
          'success'
        )
      }
    }
    )
  }
  handleGenerate(){
     // ask if he really wants to import the file
     Swal.fire({
      title: 'Voulez-vous vraiment générer les emplois de temps ?',
      text: "Cette opération peut prendre du temps",
      icon: 'info',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',

      confirmButtonText: 'Oui, générer !',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
        // if he confirms, then import the file
        // upload  file
        
       this.actons.generateEmploi().subscribe(
          data=>{
            console.log(data)
          }
        )

        // show a loading spinner
        
        

        // if done, then show a success message
        Swal.fire(
          'Géneré !',
          'Les emplois de temps ont été générés avec succès.',
          'success'
        )
      }
    }
    )
  }
  

}

import { Module } from "./modules.models";
import { Prof } from "./prof.models";
import { Salle } from "./salles.models";

export interface ElementDeModule {
    id:            number;
    volumeHoraire: number;
    libelle:       string;
    module:        Module ;
    enseignant:    Prof;
    jour:            string;
    periode:     string;
    salle:           Salle;
}
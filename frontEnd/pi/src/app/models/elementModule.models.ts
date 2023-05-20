import { Module } from "./modules.models";
import { Prof } from "./prof.models";

export interface ElementDeModule {
    id:            number;
    volumeHoraire: number;
    libelle:       string;
    module:        Module | null;
    enseignant:    Prof | null;
}
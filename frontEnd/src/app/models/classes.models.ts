import { Filiere } from "./filieres.models";
import { Module } from "./modules.models";
import { Semestre } from "./semestre.models";

export interface Classe {
    id:        number;
    libelle:   string;
    nbrEleves: number;
    filiere:   Filiere ;
    semseter:Semestre;
}
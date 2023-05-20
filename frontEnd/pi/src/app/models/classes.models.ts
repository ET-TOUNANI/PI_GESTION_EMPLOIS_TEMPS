import { Filiere } from "./filieres.models";

export interface Classe {
    id:        number;
    libelle:   string;
    nbrEleves: number;
    filiere:   Filiere | null;
}
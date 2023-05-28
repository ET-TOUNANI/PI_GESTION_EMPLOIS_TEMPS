import { Departement } from "./departement.models";

export interface Filiere {
    id:          number;
    libelle:     string;
    nombreSem:   number;
    chefFiliere: string;
    departement: Departement;
}

import { Filiere } from "./filieres.models";

export interface Departement {
    id:      number;
    libelle: string;
    chefDepartement: string;
    filieres: Filiere[];
}

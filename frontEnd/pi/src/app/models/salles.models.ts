import { TypeSalle } from "./typeSalle.models";

export interface Salle {
    id:        number;
    numSalle:  number;
    typeSalle: TypeSalle;
    capacite:number;
}
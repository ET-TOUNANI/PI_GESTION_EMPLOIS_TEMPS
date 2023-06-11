import { Classe } from "./classes.models";
import { Semestre } from "./semestre.models";

export interface Module {
    id:        number;
    volumeHoraire:  number;
    libelle:   string;
     isSeperated : boolean;
     isMetuale: boolean;
     classe:Classe;
}
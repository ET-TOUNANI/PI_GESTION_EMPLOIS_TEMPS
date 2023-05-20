import { ElementDeModule } from "./elementModule.models";
import { Salle } from "./salles.models";

export interface Seance {
    id:              number;
    jour:            string;
    heureSeance:     string;
    salle:           Salle;
    elementDeModule: ElementDeModule;
}
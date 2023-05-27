import { Prof } from "./prof.models";

export interface NonDesponibilitie {
    id:        number;
    jour:      string;
    enseignant: Prof;
    periode:   string;
}
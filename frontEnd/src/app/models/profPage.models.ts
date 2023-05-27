import { Prof } from "./prof.models";

export interface PageProf {
    content:          Prof[];
    totalPages:       number;
    totalElements:    number;
    size:             number;
    number:           number;
    numberOfElements: number;
    
}

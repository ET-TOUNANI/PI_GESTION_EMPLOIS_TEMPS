import { Departement } from "./departement.models";
import { Prof } from "./prof.models";

export interface PageProf {
    content:          Prof[];
    totalPages:       number;
    totalElements:    number;
    size:             number;
    number:           number;
    numberOfElements: number;
    
}
export interface PageDepartment {
    content:          Departement[];
    totalPages:       number;
    totalElements:    number;
    size:             number;
    number:           number;
    numberOfElements: number;
    
}

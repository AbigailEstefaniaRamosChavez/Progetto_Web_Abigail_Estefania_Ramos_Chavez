import { SafeUrl } from "@angular/platform-browser";

export interface Disegno {
    id: number;
    nome: string;
    data: Date;
    immagine: string;
    descrizione: string;
    path: string;
    url: SafeUrl
}
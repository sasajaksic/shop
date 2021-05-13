import { Proizvod } from './proizvod';
import { NumberValueAccessor } from "@angular/forms/src/directives";
import { Racun } from './racun';

export class StavkaRacuna {
  id: number;
  cena: number;
  jedinicaMere: number;
  kolicina: number;
  redniBroj: number;
  proizvod: Proizvod;
  racun: Racun;
}

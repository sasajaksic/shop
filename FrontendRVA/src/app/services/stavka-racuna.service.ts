import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { StavkaRacuna } from './../models/stavkaRacuna';
import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StavkaRacunaService {

  private readonly API_URL = "http://localhost:8083/stavkaRacuna/";
  private readonly API_URL_BTID = "http://localhost:8083/stavkeRacunaByProizvodId/";

  dataChange: BehaviorSubject<StavkaRacuna[]> = new BehaviorSubject<StavkaRacuna[]>([]);

  constructor(private httpClient: HttpClient) { }

  public getStavkeRacunaByProizvodId(idProizvoda): Observable<StavkaRacuna[]> {
    this.httpClient.get<StavkaRacuna[]>(this.API_URL_BTID + idProizvoda).subscribe(data => {
      this.dataChange.next(data);
    },
    (error: HttpErrorResponse) => {
      console.log(error.name + ' ' + error.message);
    });

    return this.dataChange.asObservable();
  }

  public addStavkaRacuna(stavkaRacuna: StavkaRacuna): void {
    this.httpClient.post(this.API_URL, stavkaRacuna).subscribe();
  }

  public updateStavkaRacuna(stavkaRacuna: StavkaRacuna): void {
    this.httpClient.put(this.API_URL, stavkaRacuna).subscribe();
  }

  public deleteStavkaRacuna(id: number): void {
    this.httpClient.delete(this.API_URL + id).subscribe();
  }
}

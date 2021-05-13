import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Racun } from '../models/racun';

@Injectable({
  providedIn: 'root'
})
export class RacunService {

  private readonly API_URL = "http://localhost:8083/racun/";

  dataChange: BehaviorSubject<Racun[]> = new BehaviorSubject<Racun[]>([]);

  constructor(private httpClient: HttpClient) { }

  public getAllRacun(): Observable<Racun[]> {
    this.httpClient.get<Racun[]>(this.API_URL).subscribe(data => {
      this.dataChange.next(data);
    });
    (error: HttpErrorResponse) => {
      console.log(error.name + ' ' + error.message);
    };

    return this.dataChange.asObservable();
  }

  public addRacun(racun: Racun): void {
    this.httpClient.post(this.API_URL, racun);
  }

  public updateRacun(racun: Racun): void {
    this.httpClient.put(this.API_URL, racun);
  }

  public deleteRacun(id: number): void {
    console.log(this.API_URL + id);
    this.httpClient.delete(this.API_URL + id);
  }
}

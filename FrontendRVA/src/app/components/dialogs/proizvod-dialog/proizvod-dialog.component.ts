import { Proizvodjac } from './../../../models/proizvodjac';
import { ProizvodjacService } from './../../../services/proizvodjac.service';
import { Proizvod } from './../../../models/proizvod';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ProizvodService } from './../../../services/proizvod.service';
import { Component, OnInit, Inject } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-proizvod-dialog',
  templateUrl: './proizvod-dialog.component.html',
  styleUrls: ['./proizvod-dialog.component.css']
})
export class ProizvodDialogComponent implements OnInit {

  proizvodjaci: Proizvodjac[];
  public flag: number;

  constructor(public proizvodService: ProizvodService,
              public proizvodjacService: ProizvodjacService,
              public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<ProizvodDialogComponent>,
              @Inject (MAT_DIALOG_DATA) public data: Proizvod) { }

  ngOnInit() {
    this.proizvodjacService.getAllProizvodjac().subscribe(proizvodjaci =>
      this.proizvodjaci = proizvodjaci);
  }

  compareTo(a, b) {
    return a.id = b.id;
  }

  public add(): void {
    this.data.id = -1;
    this.proizvodService.addProizvod(this.data);
    this.snackBar.open('Uspešno dodat proizvod.', 'U redu', {
      duration: 2500
    });
  }

  public update(): void {
    this.proizvodService.updateProizvod(this.data);
    this.snackBar.open('Uspešno modifikovan proizvod: ' + this.data.id, 'U redu', {
      duration: 2500
    });
  }

  public delete(id: number): void {
    this.proizvodService.deleteProizvod(this.data.id);
    this.snackBar.open('Uspešno obrisan proizvod: ' + this.data.id, 'U redu', {
      duration: 2500
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste', 'U redu', {
      duration: 500
    });
  }
}

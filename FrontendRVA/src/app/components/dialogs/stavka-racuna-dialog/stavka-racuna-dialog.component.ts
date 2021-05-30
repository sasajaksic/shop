import { StavkaRacunaService } from './../../../services/stavka-racuna.service';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { StavkaRacuna } from './../../../models/stavkaRacuna';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-stavka-racuna-dialog',
  templateUrl: './stavka-racuna-dialog.component.html',
  styleUrls: ['./stavka-racuna-dialog.component.css']
})
export class StavkaRacunaDialogComponent implements OnInit {

  public flag: number;

  constructor(public stavkaRacunaService: StavkaRacunaService,
              public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<StavkaRacunaDialogComponent>,
              @Inject (MAT_DIALOG_DATA) public data: StavkaRacuna) { }

  ngOnInit() {
  }

  public add(): void {
    this.data.id = -1;
    this.stavkaRacunaService.addStavkaRacuna(this.data);
    this.snackBar.open('Uspešno dodata stavka.', 'U redu', {
      duration: 2500
    });
  }

  public update(): void {
    this.stavkaRacunaService.updateStavkaRacuna(this.data);
    this.snackBar.open('Uspešno modifikovana stavka računa: ' + this.data.id, 'U redu', {
      duration: 2500
    });
  }

  public delete(id: number): void {
    this.stavkaRacunaService.deleteStavkaRacuna(this.data.id);
    this.snackBar.open('Uspešno obrisana stavka računa: ' + this.data.id, 'U redu', {
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

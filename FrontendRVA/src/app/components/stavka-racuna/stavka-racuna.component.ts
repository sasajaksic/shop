import { StavkaRacunaDialogComponent } from './../dialogs/stavka-racuna-dialog/stavka-racuna-dialog.component';
import { Proizvod } from './../../models/proizvod';
import { StavkaRacunaService } from './../../services/stavka-racuna.service';
import { Racun } from './../../models/racun';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog } from '@angular/material';
import { Component, Input, OnChanges, OnInit, ViewChild } from '@angular/core';
import { StavkaRacuna } from 'src/app/models/stavkaRacuna';

@Component({
  selector: 'app-stavka-racuna',
  templateUrl: './stavka-racuna.component.html',
  styleUrls: ['./stavka-racuna.component.css']
})
export class StavkaRacunaComponent implements OnInit, OnChanges {

  displayedColumns = ['id', 'cena', 'jedinicaMere', 'kolicina', 'redniBroj', 'proizvod', 'racun', 'actions'];
  dataSource: MatTableDataSource<StavkaRacuna>;

  @Input() selektovanProizvod: Proizvod;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public stavkaRacunaService: StavkaRacunaService,
              private dialog: MatDialog) { }

  ngOnInit() {
  }

  ngOnChanges() {
    if(this.selektovanProizvod.id) {
      this.loadData();
    }
  }

  public loadData(){
    this.stavkaRacunaService.getStavkeRacunaByProizvodId(this.selektovanProizvod.id).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'racun' ? currentTerm + data.racun.datum : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'proizvod' ? currentTerm + data.proizvod.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

        //sortiranje po nazivu ugnježdenog objekta
        this.dataSource.sortingDataAccessor = (data, property) => {
        switch(property) {
          case 'proizvod': return data.proizvod.naziv.toLocaleLowerCase();
          case 'racun': return data.racun.datum;
          default: return data[property];
        }
    }
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
  });
  }

  public openDialog(flag: number, id?: number, redniBroj?: number, kolicina?: number, jedinicaMere?: string, cena?: number, racun?: Racun, proizvod?: Proizvod){
    const dialogRef = this.dialog.open(StavkaRacunaDialogComponent, {data: {id, redniBroj, kolicina, jedinicaMere, cena, racun, proizvod}});

    dialogRef.componentInstance.flag = flag;

    // dialogRef.afterClosed().subscribe(result => {
    //   if(result === 1) {
    //     this.loadData();
    //   }
    // });

    if(flag===1) {
      dialogRef.componentInstance.data.proizvod = this.selektovanProizvod;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if(result === 1) {
          this.loadData();
        }
      })
  }

  applayFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }

}

import { Subscription } from 'rxjs';
import { RacunDialogComponent } from './../dialogs/racun-dialog/racun-dialog.component';
import { Racun } from './../../models/racun';
import { RacunService } from './../../services/racun.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-racun',
  templateUrl: './racun.component.html',
  styleUrls: ['./racun.component.css']
})
export class RacunComponent implements OnInit {

  displayedColumns = ['id', 'datum', 'nacinPlacanja', 'actions'];
  dataSource: MatTableDataSource<Racun>;
  selektovanRacun: Racun;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private racunService: RacunService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.racunService.getAllRacun().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  public openDialog(flag: number, id?: number, datum?: Date, nacinPlacanja?: string){
    const dialogRef = this.dialog.open(RacunDialogComponent, {data: {id, datum, nacinPlacanja}});
    dialogRef.componentInstance.flag = flag;

    dialogRef.afterClosed().subscribe(result => {
      if(result === 1) {
        this.loadData();
      }
    });
  }

  selectRow(row: any) {
    this.selektovanRacun = row;
    console.log(this.selektovanRacun);
  }

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }
}

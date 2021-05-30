import { ProizvodjacDialogComponent } from './../dialogs/proizvodjac-dialog/proizvodjac-dialog.component';
import { ProizvodjacService } from './../../services/proizvodjac.service';
import { Proizvodjac } from './../../models/proizvodjac';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource, MatDialog } from '@angular/material';

@Component({
  selector: 'app-proizvodjac',
  templateUrl: './proizvodjac.component.html',
  styleUrls: ['./proizvodjac.component.css']
})
export class ProizvodjacComponent implements OnInit {

  displayedColumns = ['id', 'adresa', 'kontakt', 'naziv', 'actions'];
  dataSource: MatTableDataSource<Proizvodjac>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private proizvodjacService: ProizvodjacService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.proizvodjacService.getAllProizvodjac().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  public openDialog(flag: number, id?: number, naziv?: string, adresa?: string, kontakt?: string){
    const dialogRef = this.dialog.open(ProizvodjacDialogComponent, {data: {id, naziv, adresa, kontakt}});
    dialogRef.componentInstance.flag = flag;

    dialogRef.afterClosed().subscribe(result => {
      if(result === 1) {
        this.loadData();
      }
    });
  }

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }
}

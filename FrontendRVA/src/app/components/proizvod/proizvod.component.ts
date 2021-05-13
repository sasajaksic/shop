import { ProizvodService } from './../../services/proizvod.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Proizvod } from 'src/app/models/proizvod';

@Component({
  selector: 'app-proizvod',
  templateUrl: './proizvod.component.html',
  styleUrls: ['./proizvod.component.css']
})
export class ProizvodComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'proizvodjac'];
  dataSource: MatTableDataSource<Proizvod>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private proizvodService: ProizvodService) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.proizvodService.getAllProizvod().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}

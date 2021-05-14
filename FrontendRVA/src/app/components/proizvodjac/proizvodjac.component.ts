import { ProizvodjacService } from './../../services/proizvodjac.service';
import { Proizvodjac } from './../../models/proizvodjac';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';

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

  constructor(private proizvodjacService: ProizvodjacService) { }

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

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}

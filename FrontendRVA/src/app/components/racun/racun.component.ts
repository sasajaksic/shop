import { Racun } from './../../models/racun';
import { RacunService } from './../../services/racun.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-racun',
  templateUrl: './racun.component.html',
  styleUrls: ['./racun.component.css']
})
export class RacunComponent implements OnInit {

  displayedColumns = ['id', 'datum', 'nacinPlacanja', 'actions'];
  dataSource: MatTableDataSource<Racun>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private racunService: RacunService) { }

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

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }
}

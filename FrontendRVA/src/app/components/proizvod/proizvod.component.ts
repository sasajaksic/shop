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

  displayedColumns = ['id', 'naziv', 'proizvodjac', 'actions'];
  dataSource: MatTableDataSource<Proizvod>;
  selektovanProizvod: Proizvod;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private proizvodService: ProizvodService) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.proizvodService.getAllProizvod().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'proizvodjac' ? currentTerm + data.proizvodjac.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

       //sortiranje po nazivu ugnježdenog objekta
       this.dataSource.sortingDataAccessor = (data, property) => {
        switch(property) {
          case 'proizvodjac': return data.proizvodjac.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  selectRow(row: any) {
    this.selektovanProizvod = row;
  }

  applayFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

}

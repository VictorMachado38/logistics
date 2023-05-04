import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {

  constructor(private _http: HttpClient) {
  }

  ngOnInit(): void {
    this.getClientes(" http://localhost:8080/clientes3");
  }

  getClientes(url: any) {
    this._http.get(`${url}`)
      .subscribe({
        next: (v) => {
          console.log(v);
        },
        error: (err) => {
          // this.erros.push(nome);
        },
      });

  }

}

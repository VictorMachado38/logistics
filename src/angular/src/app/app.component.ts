import {Component, OnInit} from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'angular';


  items: MenuItem[] = [];

  ngOnInit() {

    // this.items = [
    //   {label: 'Lista de Clientes', icon: 'pi pi-server', routerLink: 'list'},
    //   {label: 'Cadatrar Cliente', icon: 'pi pi-server', routerLink: 'form'},
    //   {label: 'Versões desenvolvimento', icon: 'pi pi-server', routerLink: 'develop'},
    // ];


    this.items = [
      {
        label: 'Cadastrar Cliente',
        routerLink: 'form'
      },
      {
        label: 'Listar/Editar de Clientes',
        routerLink: 'list'
      },
      {
        label: 'Requisitos da Aplicação',
        routerLink: 'init'
      }
    ];
  }
}

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
        label: 'Clientes',
        items: [
          {label: 'Cadatrar',routerLink: 'form'},
          {label: 'Listar' ,routerLink: 'list'}
        ]
      },
      {
        label: 'Edit',
        icon: 'pi pi-fw pi-pencil',
        items: [
          {label: 'Delete', icon: 'pi pi-fw pi-trash'},
          {label: 'Refresh', icon: 'pi pi-fw pi-refresh'}
        ]
      }
    ];
  }
}

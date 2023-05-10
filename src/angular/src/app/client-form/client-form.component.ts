import {AfterViewChecked, AfterViewInit, Component, NgZone, OnInit} from '@angular/core';
import {ClientDTO} from "../../model/dto/client.dto";
import {HttpClient} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {MessageService} from "primeng/api";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import validator, { cnpj } from 'cpf-cnpj-validator';
import { isValidCNPJ } from 'js-cnpj-validation'
@Component({
    selector: 'app-client-form',
    templateUrl: './client-form.component.html',
    styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit, AfterViewInit, AfterViewChecked {

    constructor(private ngZone: NgZone, private _http: HttpClient,
                private _messageService: MessageService,
                private _route: ActivatedRoute,
                private _location: Location) {
        this.iniciarTimer();
        this.apiLoaded = _http.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyAySnmKfLixyCP1PYecI2VATPrG7kUMseM', 'callback')
            .pipe(
                map(() => true),
                catchError(() => of(false)),
            );
    }


    iniciarTimer() {
        setTimeout(() => {
            if (this.cliete.longitude && this.cliete.latitude) {
                // window.alert("Cliente cadastrado com sucesso!");
                // console.log(parseFloat(this.cliete.longitude))
                // console.log(parseFloat(this.cliete.latitude))
                this.overlays.push(new google.maps.Marker({
                    position: {
                        lat: parseFloat(this.cliete.latitude),
                        lng: parseFloat(this.cliete.longitude)
                    }, draggable: false
                }));
            }
            this.markerTitle = null;
            this.dialogVisible = false;

            // this.overlays.push(new google.maps.Marker({
            //     position: {
            //         lat:-16.703515449819243,
            //         lng: -49.25256829507094
            //     }, title: "Bora", draggable: false
            // }));

        }, 1000); // Tempo em milissegundos (3000 ms = 3 segundos)
    }


    apiLoaded: Observable<boolean>;
    cliete = new ClientDTO();
    invalName: boolean = false;
    invalCnpj: boolean = false;
    invalCEP: boolean = false;
    dialogVisible: boolean = false
    markerTitle: any = null;
    selectedPosition: any;
    infoWindow: any;
    draggable: boolean = true;
    options: any;
    overlays: any[] = [];
    marcarNoMapa: boolean = false;
    titlePage: string = 'Cadastro de Cliente';
    manualAddMarker: boolean = false;
    manualLat: any;
    manualLng: any;
    label1: string = '';
    label2: string = '';
    label3: string = '';

    ngOnInit(): void {
        const url = this._location.path();
        if (url !== '/form') {
            this.cliete = Object.assign({}, this._route.snapshot.queryParams);
            this.titlePage = 'Edição de Cliente';
            if (this.cliete.longitude && this.cliete.latitude) {
                this.options = {
                    center: {
                        lat: parseFloat(this.cliete.latitude),
                        lng: parseFloat(this.cliete.longitude)
                    },
                    zoom: 13
                };
            } else {
                this.titlePage = 'Cadastro de Cliente';
                this.options = {
                    center: {lat: -16.688059517919058, lng: -49.26408132103648},
                    zoom: 12
                };
            }
        } else {
            this.titlePage = 'Cadastro de Cliente';
            this.options = {
                center: {lat: -16.688059517919058, lng: -49.26408132103648},
                zoom: 12
            };
        }


        // this.overlays = [
        //     new google.maps.Marker({position: {lat: 36.879466, lng: 30.667648}, title:"Konyaalti"}),
        //     new google.maps.Marker({position: {lat: 36.883707, lng: 30.689216}, title:"Ataturk Park"}),
        //     new google.maps.Marker({position: {lat: 36.885233, lng: 30.702323}, title:"Oldtown"}),
        //     new google.maps.Polygon({paths: [
        //             {lat: 36.9177, lng: 30.7854},{lat: 36.8851, lng: 30.7802},{lat: 36.8829, lng: 30.8111},{lat: 36.9177, lng: 30.8159}
        //         ], strokeOpacity: 0.5, strokeWeight: 1,fillColor: '#1976D2', fillOpacity: 0.35
        //     }),
        //     new google.maps.Circle({center: {lat: 36.90707, lng: 30.56533}, fillColor: '#1976D2', fillOpacity: 0.35, strokeWeight: 1, radius: 1500}),
        //     new google.maps.Polyline({path: [{lat: 36.86149, lng: 30.63743},{lat: 36.86341, lng: 30.72463}], geodesic: true, strokeColor: '#FF0000', strokeOpacity: 0.5, strokeWeight: 2})
        // ];

        // this.overlays = [
        //     new google.maps.Marker({position: {lat: -16.688059517919058, lng: -49.26408132103648}, title: "Konyaalti"}),
        //     new google.maps.Marker({position: {lat: 36.883707, lng: 30.689216}, title: "Ataturk Park"})]

        // this.getCEP("74440-590")
    }

    ngAfterViewInit(): void {
        // this.overlays = [
        //     new google.maps.Marker({position: {lat: -16.695013166810583, lng: -49.30380482606966}, title:"Konyaalti"}),
        // ];


        // this.selectedPosition = {lat: -16.688059517919058, lng: -49.26408132103648}
        // this.overlays.push(new google.maps.Marker({position: {lat: -16.703515449819243, lng: -49.25256829507094}, title:"Konyaalti"}));


    }

    ngAfterViewChecked(): void {
        // this.overlays = [
        //     new google.maps.Marker({position: {lat: -16.703515449819243, lng: -49.25256829507094}, title:"Konyaalti"}),
        // ];
        // this.overlays.push(new google.maps.Marker({position: {lat: -16.703515449819243, lng: -49.25256829507094}, title:"Konyaalti"}));

        // this.overlays.push(new google.maps.Marker({
        //     position: {
        //         lat:-16.703515449819243,
        //         lng: -49.25256829507094
        //     }, title: this.markerTitle, draggable: this.draggable
        // }));
        // this.markerTitle = null;
        // this.dialogVisible = false;

    }

     formatarCNPJ(cnpj: string): string {
        cnpj = cnpj.replace(/\D/g, '');
        return cnpj.substr(0, 2) + '.' + cnpj.substr(2, 3) + '.' + cnpj.substr(5, 3) + '/' + cnpj.substr(8, 4) + '-' + cnpj.substr(12);
    }
    // cnpj: cnpj = new cnpj();
    sendForm() {
        const _cnpj = require('js-cnpj-validation');
        if (this.cliete.cnpj === undefined || this.cliete.cnpj === null || this.cliete.cnpj.length !== 14 ||  !_cnpj.isValidCNPJ(this.formatarCNPJ(this.cliete.cnpj))) {
            this.invalCnpj = true;
            this.label1 = 'CNPJ, ';
        }else this.label1 = '';
        if (this.cliete.nome === undefined || this.cliete.nome === null || this.cliete.nome.length <= 0) {
            this.invalName = true;
            this.label2 = 'NOME, ';
        } else this.label2 = '';
        if (this.cliete.cep === undefined || this.cliete.cep === null || this.cliete.cep.length !== 8) {
            this.invalCEP = true;
            this.label3 = 'CEP, ';
            this.cliete.cep = '';
        } else this.label3 = '';
        if (this.invalCnpj || this.invalName || this.invalCEP){
            this._messageService.add({
                severity: 'error',
                summary: 'Erro ao cadastrar cliente',
                detail:   this.label1 + this.label2 + this.label3 + ' inválido(s)'
            });

        }

        if (!this.invalCnpj && !this.invalName && !this.invalCEP) {
            this._http.post(`http://localhost:8081/client/save`, this.cliete).subscribe({
                next: (retorno: any) => {
                    this.cliete = retorno.data;
                    if (retorno.status === 200) {
                        this._messageService.add({
                            severity: 'success',
                            summary: retorno.message,
                            detail: retorno.description
                        });

                    } else {
                        this._messageService.add({
                            severity: 'error',
                            summary: 'Erro ao cadastrar cliente'
                        });
                    }
                    const data = retorno.data;

                },
                error: (err) => {
                    this._messageService.add({
                        severity: 'error',
                        summary: 'Erro ao cadastrar cliente'
                    });
                },
            });
        }
    }

    getCEP(cep: string) {
        // if()


        this._http.get(`https://cdn.apicep.com/file/apicep/` + cep + `.json`).subscribe({
            next: (v: any) => {
                const a = v.address;
                this.cliete.endereco = v.address;
                this.cliete.estado = v.state;
                this.cliete.cidade = v.city;
                this.cliete.setor = v.district;
            },
            error: (err) => {
                // this.erros.push(nome);
            },
        });


    }


    handleMapClick(event: any) {
        this.dialogVisible = true;
        this.selectedPosition = event.latLng;
    }

    handleOverlayClick(event: any) {
        let isMarker = event.overlay.getTitle != undefined;

        if (isMarker) {
            let title = event.overlay.getTitle();
            this.infoWindow.setContent('' + title + '');
            this.infoWindow.open(event.map, event.overlay);
            event.map.setCenter(event.overlay.getPosition());

            this._messageService.add({severity: 'info', summary: 'Marker Selected', detail: title});
        } else {
            this._messageService.add({severity: 'info', summary: 'Shape Selected', detail: ''});
        }
    }

    addMarker() {
        console.log(this.overlays, "Antes", this.overlays.length)
        // window.alert(this.selectedPosition === undefined)
        if (this.overlays.length === 0) {

            this.overlays.push(new google.maps.Marker({
                position: {
                    lat: this.selectedPosition !== undefined ? this.selectedPosition.lat() : parseFloat(this.manualLat),
                    lng: this.selectedPosition !== undefined ? this.selectedPosition.lng() : parseFloat(this.manualLng)
                }, title: "this.markerTitle", draggable: this.draggable
            }));

            this.cliete.latitude = this.selectedPosition !== undefined ? this.selectedPosition.lat() : this.manualLat;
            this.cliete.longitude = this.selectedPosition !== undefined ? this.selectedPosition.lng() : this.manualLng;
            this.manualAddMarker = false;
            this.markerTitle = null;
            this.dialogVisible = false;
        } else {
            this._messageService.add({
                severity: 'warn',
                summary: "Limpe o marcador",
                detail: "O sistema so aceita 1 marcador"
            })
            this.dialogVisible = false;
        }
        console.log(this.overlays, "Depois")
    }

    handleDragEnd(event: any) {
        this._messageService.add({severity: 'info', summary: 'Marker Dragged', detail: event.overlay.getTitle()});
    }

    initOverlays() {
        if (!this.overlays || !this.overlays.length) {
            this.overlays = [
                new google.maps.Marker({position: {lat: 36.879466, lng: 30.667648}, title: "Konyaalti"}),
                new google.maps.Marker({position: {lat: 36.883707, lng: 30.689216}, title: "Ataturk Park"}),
                new google.maps.Marker({position: {lat: 36.885233, lng: 30.702323}, title: "Oldtown"}),
                new google.maps.Polygon({
                    paths: [
                        {lat: 36.9177, lng: 30.7854}, {lat: 36.8851, lng: 30.7802}, {
                            lat: 36.8829,
                            lng: 30.8111
                        }, {lat: 36.9177, lng: 30.8159}
                    ], strokeOpacity: 0.5, strokeWeight: 1, fillColor: '#1976D2', fillOpacity: 0.35
                }),
                new google.maps.Circle({
                    center: {lat: 36.90707, lng: 30.56533},
                    fillColor: '#1976D2',
                    fillOpacity: 0.35,
                    strokeWeight: 1,
                    radius: 1500
                }),
                new google.maps.Polyline({
                    path: [{lat: 36.86149, lng: 30.63743}, {lat: 36.86341, lng: 30.72463}],
                    geodesic: true,
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.5,
                    strokeWeight: 2
                })
            ];
        }
    }

    zoomIn(map: any) {
        map.setZoom(map.getZoom() + 1);
    }

    zoomOut(map: any) {
        map.setZoom(map.getZoom() - 1);
    }

    clear() {
        this.overlays = [];
        this.cliete.longitude = '';
        this.cliete.latitude = '';
    }

    getEndereco($event: any) {
        if (this.cliete.cep?.length === 8 || this.cliete.cep?.length === 7) {
            const cepComHifen = this.cliete.cep.slice(0, 5) + "-" + this.cliete.cep.slice(5);
            cepComHifen.length === 9 && this.getCEP(cepComHifen);
            this.invalCEP = false;
        }
    }

    showSuccess() {
        this._messageService.add({life: 5000, severity: 'custom', summary: 'Success', detail: 'Message Content'});
    }

    addLongAndLatManual() {
        if (this.overlays.length === 0) {
            this.dialogVisible = true;
            this.manualAddMarker = true;
        } else {
            this._messageService.add({
                severity: 'warn',
                summary: "Limpe o marcador",
                detail: "O sistema so aceita 1 marcador"
            })
            this.dialogVisible = false;
        }
        // this.selectedPosition = event.latLng;
    }

    validName() {
        this.invalName = false;
    }

    validCnpj() {
        const _cnpj = require('js-cnpj-validation');
        _cnpj.isValidCNPJ(this.formatarCNPJ(this.cliete.cnpj)) ? this.invalCnpj = false : this.invalCnpj = true;
    }
}

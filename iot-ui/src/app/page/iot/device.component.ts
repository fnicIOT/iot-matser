import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { DeviceService } from '../../services/device.service';
import { ModalDirective } from 'ngx-bootstrap/modal/modal.component';
import { BaseComponent } from '../base.component'; 
import { ToastrService } from 'ngx-toastr';  
import { Headers, RequestOptions,Http, Response  } from '@angular/http';

@Component({
	templateUrl:'./device.component.html'
})

export class DeviceComponent extends BaseComponent implements OnInit {

	public deviceList : Array<any> = [];

	public pageParams : any = {"pageBean" : {"pageNum":1,"pageSize":20,"count":80 } };

	public device : any = {};		

	@ViewChild('confirmModal') 
    public confirmModal:ModalDirective;	

	constructor(
    	public router: Router,
    	public deviceServ : DeviceService,
    	public toastrService: ToastrService) {
    	super(toastrService,router);
    }

	public ngOnInit() {
		 // this.route.params.switchMap((params: Params) => this.get(params['id']))

		this.queryDeviceList();
	}

	public queryDeviceList() {
		// .subscribe(res => this.qosList = res.qosList,error =>  this.errorMessage = <any>error);
		this.deviceServ.queryDeviceList(this.pageParams).subscribe(res => this.deviceList = res.deviceList,
                       error =>  this.errorMessage = <any>error);
	}

	public showConfirm(item) {
		this.device = item.objectData;
		this.confirmModal.show();
	}

	public confirmSend(item) {

		this.deviceServ.sendCommand(this.device).subscribe(res => {this.dealRsp(res,null);this.confirmModal.hide();},
                       error =>  this.errorMessage = <any>error);
	}
}
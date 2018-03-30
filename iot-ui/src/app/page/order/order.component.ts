import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/modal.component';
import { OrderService } from '../../services/order.service';  
import { BaseComponent } from '../base.component';  
import { ToastrService } from 'ngx-toastr';  

@Component({
	templateUrl:'./order.component.html'
})

export class OrderComponent extends BaseComponent implements OnInit { 

	public pageParams : any = { "pageNum":1,"pageSize":20,"count":80 };

	public productList : any =[{"productName":"虚拟路由器","price":1000,"desc":"多种运营商，多种公网IP，多种带宽可供选择","icon":"flaticon-worldwide-protection"},
		{"productName":"防火墙","price":1000,"desc":"多种运营商，多种公网IP，多种带宽可供选择","icon":"flaticon-firewall"},
		{"productName":"QoS","price":1000,"desc":"多种运营商，多种公网IP，多种带宽可供选择","icon":"flaticon-sort-by-attributes-interface-button-option"},
		{"productName":"流量监控","price":1000,"desc":"多种运营商，多种公网IP，多种带宽可供选择","icon":"flaticon-line-graphic-on-checkered-background"},
		{"productName":"带宽","price":1000,"desc":"多种运营商，多种公网IP，多种带宽可供选择","icon":"flaticon-speedometer-simple-symbol"}		
	];

	public productTypeList : Array<any> = [];

	public orderList : Array<any> = [];

	public product : any = {};

	@ViewChild('productModal') 
    public productModal:ModalDirective;

    public orderSelect : Array<any> = [
        {"id":1,"text":"订单号"}, 
        {"id":2,"text":"产品名称"}];

    constructor(private orderServ : OrderService, public router : Router,public toastrService: ToastrService) {
    	super(toastrService,router);
    }

	public ngOnInit() {

		this.orderServ.queryProductType().subscribe(res => this.productTypeList = res,error =>  this.errorMessage = <any>error);
		this.orderServ.queryOrderList({}).subscribe(res => this.orderList = res.orderList,error =>  this.errorMessage = <any>error);
	}

	public showProductModel(product : any) {

		this.productModal.show();
		this.product = product;
	}

	public itemClick(itemId,packageId) {
		let packageList = this.product.productPackageList;

		packageList.forEach( pk => {
			if(pk.productPackageId == packageId ){
				pk.productItemList.forEach(item => {
					if(item.productItemId == itemId){
						item.checked = true;
					}else {
						item.checked = false;
					}	
				});
			}
		});
	}

	public submitCart() {

		console.log(this.product);
		let product = this.product;
		let param = {};
		param['goodsId'] = product.productId;
		param['goodsNum'] = 1;
		param['goodsName'] = product.productName;

		let packageList = this.product.productPackageList;
		let submitList = new Array();

		packageList.forEach( pk => {
			let itemList = new Array();			
			pk.productItemList.forEach(item => {
				if(item.checked){
					itemList.push(item.productItemId);
				}	
			});
			pk['itemList'] = itemList;
			submitList.push(pk);			
		});

		param['packageList'] = submitList;

		this.orderServ.submitCart(param).subscribe(res => {this.dealRsp(res,null);this.productModal.hide();},error =>  this.errorMessage = <any>error);
	}
}
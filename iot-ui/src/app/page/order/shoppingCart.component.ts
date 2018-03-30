import { Component, OnInit, ViewChild } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { ToastrService } from 'ngx-toastr';  
import { BaseComponent } from '../base.component';  
import { Router } from '@angular/router';

@Component({
	templateUrl:'./shoppingCart.component.html'
})

export class ShoppingCartComponent extends BaseComponent implements OnInit {

	public cartList : Array<any> = [];

	constructor(private orderServ : OrderService, public router : Router,public toastrService: ToastrService) {
		super(toastrService,router);
    }

	public ngOnInit() {
		this.orderServ.queryCartList().subscribe(res => this.cartList = res.cartList,error =>  this.errorMessage = <any>error);
	}

	public submitOrder() {
		let itemList = new Array<any>();
		let param = {};

		this.cartList.forEach(item => {
			itemList.push(item.id);
		});
		param["idList"] = itemList;
		this.orderServ.submitOrder(param).subscribe(res => this.dealRsp(res,'order/order'),error =>  this.errorMessage = <any>error);
	}

}
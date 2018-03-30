import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpService } from './http.service';

@Injectable()
export class OrderService extends HttpService {

	public orderQueryProductTypeUrl : string = '/order/queryProductType';

	public orderSubmitCartUrl : string = '/order/submitShoppingCart';

	public orderQueryCartListUrl : string = '/order/queryCartList';

	public orderSubmitOrderUrl : string = '/order/submitOrder';
	
	public orderQueryOrderListUrl : string = '/order/queryOrderList';

	constructor (public http: Http) { 
		super(http);
	}

	public queryProductType() {
		return this.postReq(this.orderQueryProductTypeUrl,{});
	}

	public submitCart(param : any) {
		return this.postReq(this.orderSubmitCartUrl,param);
	}

	public queryCartList() {
		return this.postReq(this.orderQueryCartListUrl,{});
	}

	public submitOrder(param : any){
		return this.postReq(this.orderSubmitOrderUrl,param);
	}

	public queryOrderList(param : any) {
		return this.postReq(this.orderQueryOrderListUrl,param);
	}
}
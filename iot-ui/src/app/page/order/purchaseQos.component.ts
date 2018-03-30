import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
	templateUrl:'./purchaseQos.component.html'
})

export class PurchaseQosComponent implements OnInit {

	public product : any = {"productName":"弹性带宽"};

	public bandSelect : Array<any> = [
        {"id":1,"text":"10M"}, 
        {"id":2,"text":"20M"},
        {"id":3,"text":"30M"},
        {"id":4,"text":"40M"},
        {"id":5,"text":"50M"}];

	constructor(
		private route: ActivatedRoute,
    	private router: Router) {

	}

	public ngOnInit() {
		 // this.route.params.switchMap((params: Params) => this.get(params['id']))

		this.route.params.subscribe(params=> {
			console.log(params['id']);
		})
	}

	// public get(id) {
	// 	console.log(id);
	// }
}
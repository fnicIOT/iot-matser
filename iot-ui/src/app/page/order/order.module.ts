import { NgModule } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { EchartsNg2Module } from 'echarts-ng2';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

import { OrderRoutingModule } from './order-routing.module';
import { OrderComponent } from './order.component';
import { ShoppingCartComponent } from './shoppingCart.component';
import { PurchaseQosComponent } from './purchaseQos.component';

import { WebgetsModule } from '../../widgets/webgets.module';
import { SelectModule } from 'ng2-select';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';

@NgModule({
	imports:[
		OrderRoutingModule,
    	BsDropdownModule,
    	EchartsNg2Module,
    	TabsModule,
    	NgxPaginationModule,
    	CommonModule,
    	ModalModule.forRoot(),
    	FormsModule,
    	SelectModule,
    	NgxMyDatePickerModule,
        WebgetsModule
	],
  	declarations: [ 
	  	OrderComponent,
        ShoppingCartComponent,
        PurchaseQosComponent
  	]
})

export class OrderModule { }
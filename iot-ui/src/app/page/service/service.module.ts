import { NgModule } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { EchartsNg2Module } from 'echarts-ng2';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

import { ServiceRoutingModule } from './service-routing.module';
import { UserComponent } from './user.component';
import { StrategyComponent } from './strategy.component';
import { SelectModule } from 'ng2-select';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { WebgetsModule } from '../../widgets/webgets.module';

@NgModule({
	imports:[
		ServiceRoutingModule,
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
	  	UserComponent,
	  	StrategyComponent
  	]
})

export class ServiceModule { }
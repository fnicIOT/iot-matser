import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { EchartsNg2Module } from 'echarts-ng2';
import { AbmModule } from 'angular-baidu-maps';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';

@NgModule({
	imports:[
		DashboardRoutingModule,
   		ChartsModule,
    	BsDropdownModule,
    	EchartsNg2Module,
    	AbmModule
	],
  	declarations: [ DashboardComponent ]
})

export class DashboardModule { }
import { NgModule } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { EchartsNg2Module } from 'echarts-ng2';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

import { IotRoutingModule } from './iot-routing.module';
import { DeviceComponent } from './device.component';
import { EnvMonitorComponent } from './envMonitor.component';

import { WebgetsModule } from '../../widgets/webgets.module';
import { SelectModule } from 'ng2-select';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { ToastrModule } from 'ngx-toastr';  

@NgModule({
	imports:[
		IotRoutingModule,
    	BsDropdownModule,
    	EchartsNg2Module,
    	TabsModule,
    	NgxPaginationModule,
    	CommonModule,
    	ModalModule.forRoot(),
    	FormsModule,
    	SelectModule,
    	NgxMyDatePickerModule,
        WebgetsModule,
        ToastrModule
	],
  	declarations: [ 
	  	DeviceComponent,
        EnvMonitorComponent
  	]
})

export class IotModule { }
import { NgModule } from '@angular/core';

import { ButtonsComponent } from './buttons.component';
import { CardsComponent } from './cards.component';
import { FormsComponent } from './forms.component';
import { SocialButtonsComponent } from './social-buttons.component';
import { SwitchesComponent } from './switches.component';
import { TablesComponent } from './tables.component';
import { UserComponent } from '../page/service/user.component';


import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { EchartsNg2Module } from 'echarts-ng2';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


// Modal Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { ModalsComponent } from './modals.component';

// Tabs Component
import { TabsModule } from 'ngx-bootstrap/tabs';
import { TabsComponent } from './tabs.component';

// Components Routing
import { ComponentsRoutingModule } from './components-routing.module';
import { ServiceRoutingModule } from './service-routing.module';
import { StrategyComponent } from './strategy.component';
import { SelectModule } from 'ng2-select';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';

@NgModule({
  imports: [
    ComponentsRoutingModule,
    ModalModule.forRoot(),
    TabsModule,
    BsDropdownModule,
    EchartsNg2Module,
    NgxPaginationModule,
    CommonModule,
    FormsModule,
    SelectModule,
    NgxMyDatePickerModule
  ],
  declarations: [
    ButtonsComponent,
    CardsComponent,
    FormsComponent,
    ModalsComponent,
    SocialButtonsComponent,
    SwitchesComponent,
    TablesComponent,
    TabsComponent,
    UserComponent
  ]
})
export class ComponentsModule { }

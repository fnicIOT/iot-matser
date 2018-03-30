import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { FormsModule } from '@angular/forms';
import { EchartsNg2Module } from 'echarts-ng2';
import { HttpModule, JsonpModule } from '@angular/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgxMyDatePickerModule } from 'ngx-mydatepicker';
import { BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import { AbmModule } from 'angular-baidu-maps';
import { ToastrModule } from 'ngx-toastr';  

let modules = [
    BrowserModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    ChartsModule,
    EchartsNg2Module,
    HttpModule,
    JsonpModule,
    NgxPaginationModule,
    CommonModule,
    ModalModule,
    BrowserAnimationsModule,
    NgxMyDatePickerModule,
    ToastrModule.forRoot(),
    AbmModule.forRoot({
            apiKey: '7UbbqwX8Sl9HRYD41tULMZ8uIceU3069' // app key为必选项
        })
];
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { BreadcrumbsComponent } from './widgets/breadcrumb.component';
import { NAV_DROPDOWN_DIRECTIVES } from './widgets/nav-dropdown.directive';

let widgets = [
    AppComponent,
    BreadcrumbsComponent,
    NAV_DROPDOWN_DIRECTIVES
];

import { LoginComponent } from './page/login/login.component';
import { FullLayoutComponent } from './layouts/full-layout.component';

let pages = [
    LoginComponent,
    FullLayoutComponent
];

// Routing Module
import { AppRoutingModule } from './app.routes';

import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { CanActivateGuard } from './services/guard.service';
import { UserManageService } from './services/userManage.service';
import { OrderService } from './services/order.service';
import { StrategyService } from './services/strategy.service';
import { DeviceService } from './services/device.service';

let services = [
    UserService,
    LoginService,
    CanActivateGuard,
    UserManageService,
    OrderService,
    StrategyService,
    DeviceService
];
import '../scss/style.scss';
import '../styles/styles.scss';
import '../assets/css/styles.css';
import '../../node_modules/chart.js/src/chart.js';
// import '../../node_modules/echarts/src/echarts.js';

@NgModule({
  imports: [
    ...modules,
    AppRoutingModule
  ],
  declarations: [
    ...widgets,
    ...pages
  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  },
    ...services
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }

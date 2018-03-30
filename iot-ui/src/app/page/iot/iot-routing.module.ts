import { NgModule } from '@angular/core';
import { Routes,RouterModule } from '@angular/router';

import { DeviceComponent } from './device.component';
import { EnvMonitorComponent } from './envMonitor.component';

const routes: Routes = [
  {
    path: 'device',
    component: DeviceComponent,
    data: {
      title: 'iot'
    }
  },
  {
    path: 'envMonitor',
    component: EnvMonitorComponent,
    data: {
      title: 'envMonitor'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IotRoutingModule {}

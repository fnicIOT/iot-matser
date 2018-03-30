import { Injectable } from '@angular/core';
import { Headers, RequestOptions,Http, Response  } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { HttpService } from './http.service';

@Injectable()
export class DeviceService extends HttpService {

	private deviceUrl = '/device/queryDevicesGC';

	private envStatUrl = '/iot/queryMonitorParam';

	constructor (public http: Http) { 
		super(http);
	}

	public queryDeviceList(pageParams) {
    	return this.postReq(this.deviceUrl, pageParams);

	}

	public sendCommand(item) {
    	return this.postReq(this.deviceUrl, { "deviceId":item.deviceId});

	}

	public queryEnvStat() {
    	return this.postReq(this.envStatUrl, { "floor":18});

	}
}
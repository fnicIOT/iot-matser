import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { HttpService } from './http.service';

@Injectable()
export class StrategyService extends HttpService {

	public queryOosListUrl = "/strategy/queryQosList";

	constructor (public http: Http) { 
		super(http);
	}

	public queryQosList(req : any) {
		return this.postReq(this.queryOosListUrl ,req);
	}

}
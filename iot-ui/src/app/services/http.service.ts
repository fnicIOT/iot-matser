import { Injectable } from '@angular/core';
import { Headers, RequestOptions,Http, Response  } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

export class HttpService {

	//public context : string =  'boot';

	public context : string =  '/boot';

	// public context : string =  'http://192.168.33.167:9090/boot';

	constructor (public http: Http) {}

	public postReq(url : string ,req : any)  {
		let headers = new Headers({ 'Content-Type':'application/json'});		
    	let options = new RequestOptions({ headers: headers });

    	headers.append('Access-Control-Allow-Origin','*');
		headers.append('Access-Control-Allow-Methods','POST');
		headers.append('Access-Control-Allow-Headers','x-requested-with,content-type');

    	return this.http.post(this.context + url,req, options)
                    .map(this.extractData)
                    .catch(this.handleError);
	}

	private extractData(res: Response) {
	    let body = res.json();
	    console.log(res);
	    return body || { };
  	}

  	private handleError (error: Response | any) {
		// In a real world app, we might use a remote logging infrastructure
	    let errMsg: string;
	    if (error instanceof Response) {
	      const body = error.json() || '';
	      const err = body.error || JSON.stringify(body);
	      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
	    } else {
	      errMsg = error.message ? error.message : error.toString();
	    }
	    console.error(errMsg);
	    return Observable.throw(errMsg);
	}
}
import { Injectable } from '@angular/core';
import { Headers, RequestOptions,Http, Response  } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class UserManageService {

	private userUrl = 'boot/queryUser';

	constructor (private http: Http) {}

	public queryUserList(pageParams) {
		let headers = new Headers({ 'Content-Type': 'application/json' });
    	let options = new RequestOptions({ headers: headers });

    	return this.http.post(this.userUrl, pageParams, options)
                    .map(this.extractData)
                    .catch(this.handleError);

	}

	private extractData(res: Response) {
		console.log( res);
	    let body = res.json();
		console.log(body);
		console.log(body.data);
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
	    console.log(errMsg);
	    return Observable.throw(errMsg);
	}
}
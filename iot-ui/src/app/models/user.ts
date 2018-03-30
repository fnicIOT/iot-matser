export class User {
	public userName: string;
	public password: string;
	public email: string;
	public connected: boolean;
	public token: string;

	public constructor( data: any = {}) {
	    this.userName = data.userName || '';
	    this.password = data.password || '';
	    this.email = data.email || '';
	    this.connected = data.connected || false;
	    this.token = data.token || '';
	}
}
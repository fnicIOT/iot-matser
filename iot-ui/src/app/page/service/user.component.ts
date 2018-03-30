import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/modal.component';

@Component({
	templateUrl:'./user.component.html'
})

export class UserComponent implements OnInit {

    public pageParams : any = { "pageNum":1,"pageSize":20,"count":80 };

    public modalTitle : string;

    public user : any = {};

    public userList : Array<any> = [{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            },{
                "id": 1,
                "userName": "test",
                "fullName":"192.168.1.1",
                "email":"test",
                "groupName":"test"
            }];

    public groupList : Array<any> = [{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            },{
                "id": 1,
                "groupName": "test",
                "ip":"192.168.1.1",
                "desc":"test"
            }];

    public deviceList : Array<any> = [{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            },{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            },{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            },{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            },{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            },{
                "id": 1,
                "mac": "1312dsadas",
                "userName": "test",
                "typeName":"个人用户",
                "hostName":"1111",
                "domainName":"2222",
                "ipAddress":"192.168.1.1",
                "groupName":"test"
            }];;

    private errorMessage: string;

    @ViewChild('groupUpdateModal') 
    public groupUpdateModal:ModalDirective;

    @ViewChild('userUpdateModal') 
    public userUpdateModal:ModalDirective;

    @ViewChild('deviceUpdateModal') 
    public deviceUpdateModal:ModalDirective;

    private userQueryType : string;

    constructor(){
    	
    }

    public userSelect:Array<any> = [
        {"id":1,"text":"用户名"}, 
        {"id":2,"text":"全名"}, 
        {"id":3,"text":"邮箱"},
        {"id":4,"text":"组"}];

	public ngOnInit() {
		// this.queryUserList();
		// this.updateModal.config = {backdrop: 'static'};
		// this.updateModal.show();
	}

	public queryUserList() {
		// this.userManageServ.queryUserList(this.pageParams).subscribe(res => this.userList = res,
  //                      error =>  this.errorMessage = <any>error);
	}

	public getPageData(pageNum) {
		// let vm = this;
	 //    vm.pageNum = pageNum;
	    console.log('触发', pageNum);
	}

	public showUserUpdateModal(isUpdate,item) 
	{
		if(isUpdate) {
			this.modalTitle = '编辑用户';
            this.user = item;
		}else {
			this.modalTitle = '新增用户';
            this.user = {};
		}

		this.userUpdateModal.show();
	}

    public showGroupUpdateModal(isUpdate,item) 
    {
        if(isUpdate) {
            this.modalTitle = '编辑组';
            this.user = item;
        }else {
            this.modalTitle = '新增组';
            this.user = {};
        }

        this.groupUpdateModal.show();
    }

    public showDeviceUpdateModal(isUpdate,item) 
    {
        if(isUpdate) {
            this.modalTitle = '编辑MAC';
            this.user = item;
        }else {
            this.modalTitle = '新增MAC';
            this.user = {};
        }

        this.deviceUpdateModal.show();
    }

	  private value:any = {};
	  private _disabledV:string = '0';
	  private disabled:boolean = false;

	  private get disabledV():string {
	    return this._disabledV;
	  }

	  private set disabledV(value:string) {
	    this._disabledV = value;
	    this.disabled = this._disabledV === '1';
	  }

	  public selected(value:any):void {
	    console.log('Selected value is: ', value);
        console.log('Selected: ', this.value);
	  }

      public selected2(value:any):void {
        console.log('Selected2 value is: ', value);
        console.log('Selected2: ', this.value);
      }

	  public removed(value:any):void {
	    console.log('Removed value is: ', value);
	  }

	  public typed(value:any):void {
	    console.log('New search input: ', value);
	  }

	  public refreshValue(value:any):void {
	    this.value = value;
	  }
}
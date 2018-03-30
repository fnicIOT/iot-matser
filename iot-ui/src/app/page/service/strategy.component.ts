import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'ngx-bootstrap/modal/modal.component';
import {IMyOptions, IMyDateModel} from 'ngx-mydatepicker';
import { StrategyService } from '../../services/strategy.service';  
import { BaseComponent } from '../base.component'; 
import { ToastrService } from 'ngx-toastr';

@Component({
	templateUrl:'./strategy.component.html'
})

export class StrategyComponent extends BaseComponent implements OnInit {

	public pageParams : any = { "pageNum":1,"pageSize":20,"count":80 };

	public internetRateOption : any = {
      title: {
          text: '上网速率'
      },
      tooltip: {
          trigger: 'axis'
      },
      legend: {
          data:['外网上行速率','外网下行速率']
      },
      grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
      },
      toolbox: {

      },
      xAxis: {
          type: 'category',
          boundaryGap: false,
          splitLine: {
            show: false
          },
          data: ['周一','周二','周三','周四','周五','周六','周日']
      },
      yAxis: {
          type: 'value',
          splitLine: {
            show: false
          }
      },
      series: [
          {
              name:'外网上行速率',
              type:'line',
              stack: '总量',
              data:[120, 132, 101, 134, 90, 230, 210]
          },
          {
              name:'外网下行速率',
              type:'line',
              stack: '总量',
              data:[220, 182, 191, 234, 290, 330, 310]
          }
      ]
  	};

	public onlineUserOption = {
	    color: ['#3398DB'],
	    title : {
          text: '在线用户'
      	},
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'直接访问',
	            type:'bar',
	            barWidth: '60%',
	            data:[10, 52, 200, 334, 390, 330, 220]
	        }
	    ]
	};

	public top5UserOption: any = {
      title : {
          text: '前5名用户流量'
      },
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
          x : 'center',
          y : 'bottom',
          data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
      },
      calculable : true,
      series : [
          {
              name:'面积模式',
              type:'pie',
              radius : [30, 110],
              roseType : 'area',
              data:[
                  {value:10, name:'rose1'},
                  {value:5, name:'rose2'},
                  {value:15, name:'rose3'},
                  {value:25, name:'rose4'},
                  {value:20, name:'rose5'},
                  {value:35, name:'rose6'},
                  {value:30, name:'rose7'},
                  {value:40, name:'rose8'}
              ]
          }
      ]
  };

  public top5AddressFlowOption: any = {
      title : {
          text: '前5名目标地址流量'
      },
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
          x : 'center',
          y : 'bottom',
          data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
      },
      calculable : true,
      series : [
          {
              name:'面积模式',
              type:'pie',
              radius : [30, 110],
              roseType : 'area',
              data:[
                  {value:10, name:'rose1'},
                  {value:5, name:'rose2'},
                  {value:15, name:'rose3'},
                  {value:65, name:'rose4'},
                  {value:20, name:'rose5'},
                  {value:35, name:'rose6'},
                  {value:20, name:'rose7'},
                  {value:40, name:'rose8'}
              ]
          }
      ]
  };

  public top5AppFlowOption: any = {
      title : {
          text: '前5名应用流量'
      },
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
          x : 'center',
          y : 'bottom',
          data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
      },
      calculable : true,
      series : [
          {
              name:'面积模式',
              type:'pie',
              radius : [30, 110],
              roseType : 'area',
              data:[
                  {value:10, name:'rose1'},
                  {value:5, name:'rose2'},
                  {value:15, name:'rose3'},
                  {value:35, name:'rose4'},
                  {value:20, name:'rose5'},
                  {value:5, name:'rose6'},
                  {value:30, name:'rose7'},
                  {value:40, name:'rose8'}
              ]
          }
      ]
  };

	public netHistorySelect:Array<any> = [
      {"id":1,"text":"用户"}, 
      {"id":2,"text":"域名"}, 
      {"id":3,"text":"IP"}];

  private dateOptions: IMyOptions = {
      // other options...
      dateFormat: 'yyyy.mm.dd',
  };

  // Initialized to specific date (09.10.2018)
  private startTime: Object = {};

  private modalTitle: string;

  @ViewChild('strategyUpdateModal') 
  public strategyUpdateModal : ModalDirective;

  private strategy : any = {};

  public qosList : Array<any> = [];

  private qosEdit : boolean = false;

  public errorMessage: string;

  constructor(private strategyServ : StrategyService, public router : Router,public toastrService: ToastrService) {
      super(toastrService,router);
  }

	public ngOnInit() {

      this.strategyServ.queryQosList({}).subscribe(res => this.qosList = res.qosList,error =>  this.errorMessage = <any>error);

	}

	public onDateChanged(event: IMyDateModel): void {
        // date selected
  }

  public showQosEdit(isShow : boolean) {
      this.qosEdit = isShow;
  }

  public editQos() {

  }

  public changeDefaultRate() {
    
  }

  public showStratgyUpdateModal(item:any,isUpdate) {
  	
  	if(isUpdate) {
  		this.modalTitle = '编辑云安全策略';
  	}else {
  		this.modalTitle = '添加云安全策略';
  	}

  	this.strategyUpdateModal.show();
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
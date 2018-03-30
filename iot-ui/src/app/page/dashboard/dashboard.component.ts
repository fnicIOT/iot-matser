import { Component, OnInit, ViewChild, ElementRef, NgZone, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AbmComponent } from 'angular-baidu-maps';
declare var echarts : any;
declare const BMap: any;

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  // constructor( ) { }

  public brandPrimary: string =  '#20a8d8';
  public brandSuccess: string =  '#4dbd74';
  public brandInfo: string =   '#63c2de';
  public brandWarning: string =  '#f8cb00';
  public brandDanger: string =   '#f86c6b';

  options: any = {}
  state: string = '';

  //百度地图
  private _map: any;
  onReady(map: any) {
      this._map = map;
      map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
      map.addControl(new BMap.MapTypeControl());
      map.setCurrentCity("北京");
      map.enableScrollWheelZoom(true);
      this.state = '加载完成';
      //添加监听事件
      map.addEventListener('tilesloaded', () => {
          this.state = '地图加载完毕';
      });
      map.addEventListener('click', this._click.bind(this));
  }

  _click(e: any) {
        this.state = `${e.point.lng}, ${e.point.lat}, ${+new Date}`;
  }

  // dropdown buttons
  public status: { isopen: boolean } = { isopen: false };
  public toggleDropdown($event: MouseEvent): void {
    $event.preventDefault();
    $event.stopPropagation();
    this.status.isopen = !this.status.isopen;
  }

  // social box charts

  public socialChartData1: Array<any> = [
    {
      data: [65, 59, 84, 84, 51, 55, 40],
      label: 'Facebook'
    }
  ];
  public socialChartData2: Array<any> = [
    {
      data: [1, 13, 9, 17, 34, 41, 38],
      label: 'Twitter'
    }
  ];
  public socialChartData3: Array<any> = [
    {
      data: [78, 81, 80, 45, 34, 12, 40],
      label: 'LinkedIn'
    }
  ];
  public socialChartData4: Array<any> = [
    {
      data: [35, 23, 56, 22, 97, 23, 64],
      label: 'Google+'
    }
  ];

  public socialChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public socialChartOptions: any = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      xAxes: [{
        display: false,
      }],
      yAxes: [{
        display: false,
      }]
    },
    elements: {
      line: {
        borderWidth: 2
      },
      point: {
        radius: 0,
        hitRadius: 10,
        hoverRadius: 4,
        hoverBorderWidth: 3,
      }
    },
    legend: {
      display: false
    }
  };
  public socialChartColours: Array<any> = [
    {
      backgroundColor: 'rgba(255,255,255,.1)',
      borderColor: 'rgba(255,255,255,.55)',
      pointHoverBackgroundColor: '#fff'
    }
  ];
  public socialChartLegend: boolean = false;
  public socialChartType: string = 'line';

  public onlineUserOption: any =  {
	  title: {
	    text: '在线用户'
	  },
	  tooltip: {},
	  xAxis: {
	    data: ["2017-4-10", "2017-4-11", "2017-4-12", "2017-4-13", "2017-4-14"],
      splitLine: {
            show: false
      }
	  },
	  yAxis: {
      splitLine: {
            show: false
      }},
	  series: [{
	    name: '人数',
	    type: 'bar',
	    data: [5, 20, 36, 10, 10]
	  }]
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

  public netStatusOption: any = {
      title: {
          text: '网络状态'
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

  public netSpeedOption: any = {
      title: {
          text: '网络加速状态'
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

  ngOnInit(): void {
    // generate random values for mainChart
    // for (let i = 0; i <= this.mainChartElements; i++) {
    //   this.mainChartData1.push(this.random(50, 200));
    //   this.mainChartData2.push(this.random(80, 100));
    //   this.mainChartData3.push(65);
    // }

    // var myChart = echarts.init(document.getElementById('main'));

    //     // 指定图表的配置项和数据
    //     var option = {
    //         title: {
    //             text: 'ECharts 入门示例'
    //         },
    //         tooltip: {},
    //         legend: {
    //             data:['销量']
    //         },
    //         xAxis: {
    //             data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    //         },
    //         yAxis: {},
    //         series: [{
    //             name: '销量',
    //             type: 'bar',
    //             data: [5, 20, 36, 10, 10, 20]
    //         }]
    //     };

    //     // 使用刚指定的配置项和数据显示图表。
    //     myChart.setOption(option);
  }
}

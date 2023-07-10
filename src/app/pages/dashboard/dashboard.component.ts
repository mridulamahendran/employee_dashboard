import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import Chart from 'chart.js';

@Component({
    selector: 'dashboard-cmp',
    moduleId: module.id,
    templateUrl: 'dashboard.component.html'
})

export class DashboardComponent implements OnInit{

  public canvas : any;
  public ctx;
  public chartColor;
  public chartEmail;
  public chartEmails;
  public chartHours;
  public responseData;
  public lengths: number[] = [];
  public datas: any[] = [];
    apiUrls = [
      'http://localhost:8080/api/employee/department/sol',
      'http://localhost:8080/api/employee/department/fcpq',
      'http://localhost:8080/api/employee/department/mas',
      'http://localhost:8080/api/employee/department/oe'
    ];

      constructor(private http: HttpClient) { }

      getYearsOfExperience(department) {
        return this.http.get<{ [key: string]: number }>('http://localhost:8080/api/employee/exp/'+department);
      }

      getGender(department) {
        return this.http.get<{ [key: string]: number }>('http://localhost:8080/api/employee/gen/'+department);
      }

      getDesignation(department) {
        return this.http.get<{ [key: string]: number }>('http://localhost:8080/api/employee/design/'+department);
      }

      loadCharts(department, id) {
        debugger
        document.getElementById('1').style.backgroundColor = 'white';
        document.getElementById('2').style.backgroundColor = 'white';
        document.getElementById('3').style.backgroundColor = 'white';
        document.getElementById('4').style.backgroundColor = 'white';
        document.getElementById(id).style.backgroundColor = '#cefeff';

        let color;
        if(department === 'oe'){
          color = "#fbc658";
        }else if(department === 'mas'){
          color = "#ef8157";
        }else if(department === 'sol'){
          color = "#6bd098";
        }else{
          color = "#51cbce";
        }


        if (this.chartEmail) {
          this.chartEmail.destroy();
        }
        if (this.chartHours) {
          this.chartHours.destroy();
        }
        if (this.chartEmails) {
        this.chartEmails.destroy();
        }
        this.getYearsOfExperience(department).subscribe(data => {
            console.log(data);
            this.chartColor = "#FFFFFF";

            this.canvas = document.getElementById("chartHours");
            this.ctx = this.canvas.getContext("2d");

            this.chartHours = new Chart(this.ctx, {
              type: 'bar',

              data: {
                labels: ["0-3", "4-6", "7-10", "11-15", "15+"],
                datasets: [{
                    borderColor: color,
                    backgroundColor: color,
                    borderWidth: 1,
                    data: [data["0-3"], data["4-6"], data["7-10"], data["11-15"], data["15+"]]
                  }
                ]
              },
              options: {
                legend: {
                  display: false
                },

                tooltips: {
                  enabled: true
                },
                scales: {
                  y: {
                      beginAtZero: true
                  },
                  yAxes: [{

                    ticks: {
                      fontColor: "#9f9f9f",
                      beginAtZero: true,
                      maxTicksLimit: 5,
                    },
                    gridLines: {
                      drawBorder: false,
                      zeroLineColor: "#ccc",
                      color: 'rgba(255,255,255,0.05)'
                    }

                  }],

                },
              }
            });

          }, error => {
            console.error(error);
          });

          this.getGender(department).subscribe(data => {
              console.log(data);
              this.canvas = document.getElementById("chartEmail");
              this.ctx = this.canvas.getContext("2d");
              this.chartEmail = new Chart(this.ctx, {
                type: 'pie',
                data: {
                  labels: Object.keys(data),
                  datasets: [{
                    label: "Genders",
                    pointRadius: 0,
                    pointHoverRadius: 0,
                    backgroundColor: [
                      '#ef8157',
                      '#4acccd',
                      '#e3e3e3'
                    ],
                    borderWidth: 0,
                    data: [data["female"], data["male"], data["others"]]
                  }]
                },

                options: {

                  legend: {
                    display: false
                  },

                  pieceLabel: {
                    render: 'percentage',
                    fontColor: ['white'],
                    precision: 2
                  },

                  tooltips: {
                    enabled: true
                  },

                  scales: {
                    yAxes: [{

                      ticks: {
                        display: false
                      },
                      gridLines: {
                        drawBorder: false,
                        zeroLineColor: "transparent",
                        color: 'rgba(255,255,255,0.05)'
                      }

                    }],

                    xAxes: [{
                      barPercentage: 1.6,
                      gridLines: {
                        drawBorder: false,
                        color: 'rgba(255,255,255,0.1)',
                        zeroLineColor: "transparent"
                      },
                      ticks: {
                        display: false,
                      }
                    }]
                  },
                }
              });
          }, error => {
              console.error(error)
          });


        this.getDesignation(department).subscribe(data => {
            console.log(data);
            this.canvas = document.getElementById("speedChart");
            this.ctx = this.canvas.getContext("2d");
            this.chartEmails = new Chart(this.ctx, {
              type: 'doughnut',
              data: {
                labels: ["Technical Leads","Senior Managers","Reporting Managers","Individual Contributors"],
                datasets: [{
                  label: "Genders",
                  pointRadius: 0,
                  pointHoverRadius: 0,
                  backgroundColor: [
                    '#e3e3e3',
                    '#fbc658',
                    '#4acccd',
                    '#ef8157'
                  ],
                  borderWidth: 0,
                  data: [data["Technical Leads"], data["Senior Managers"], data["Reporting Managers"], data["Individual Contributors"]]
                }]
              },

              options: {

                legend: {
                  display: false
                },

                pieceLabel: {
                  render: 'percentage',
                  fontColor: ['white'],
                  precision: 2
                },

                tooltips: {
                  enabled: true
                },
              }
            });

        }, error => {
            console.error(error)
        });
      }

    ngOnInit(){
      const observables = this.apiUrls.map(url => this.http.get(url));
      let data;
      forkJoin(observables).subscribe(responses => {
        data = responses;
        for(let i=0; i<data.length;i++) {
          this.lengths[i] = data[i].length;
          this.datas[i] = data[i];
        }
        console.log(data);
      });

      this.loadCharts('oe','1');

    }
}

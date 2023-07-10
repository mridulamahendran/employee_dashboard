import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';
import { map } from 'rxjs/operators';
import { ToastrService } from "ngx-toastr";
import { Location } from '@angular/common';


@Component({
    selector: 'table-cmp',
    moduleId: module.id,
    templateUrl: 'table.component.html'
})

export class TableComponent implements OnInit{
    @ViewChild('tableRef') tableRef: ElementRef;
    public datas;

    deleteEmp(id) {
      return this.http.delete<{ [key: string]: number }>('http://localhost:8080/api/employee/'+id);
    }

    constructor(private http: HttpClient, private toastr: ToastrService, private location: Location) {}

    removeEmployee(id) {
      this.deleteEmp(id).subscribe(
        response => {
          console.log(response.message);
          window.location.reload();
          this.showNotification('top', 'center', 1);
        },
        error => {
          console.error(error);
          this.showNotification('top', 'center', 2);
        }
      );
    }

    showNotification(from, align, color) {
      switch (color) {
        case 1:
          this.toastr.success(
            '<span data-notify="icon" class="nc-icon nc-single-02"></span><span data-notify="message">Employee removed successfully!</span>',
            "",
            {
              timeOut: 4000,
              closeButton: true,
              enableHtml: true,
              toastClass: "alert alert-success alert-with-icon",
              positionClass: "toast-" + from + "-" + align
            }
          );
          break;
        case 2:
          this.toastr.error(
          '<span data-notify="icon" class="nc-icon nc-single-02"></span><span data-notify="message">There\'s a problem removing the profile!</span>',
            "",
            {
              timeOut: 4000,
              enableHtml: true,
              closeButton: true,
              toastClass: "alert alert-danger alert-with-icon",
              positionClass: "toast-" + from + "-" + align
            }
          );
          break;
        default:
          break;
      }
    }

    loadTable(department) {debugger;
      let url = 'http://localhost:8080/api/employee';
      if(department !== 'all'){
        url = url + '/department/' + department;
      }

      const apicall = this.http.get(url);
      apicall.subscribe(data => {
        console.log(data);
        this.datas = data;
        this.sortDataByParameters('null', 'id');
        this.loadData();
      }, error => {
        console.error(error);
      });
    }

    loadData() {
      const table = this.tableRef.nativeElement;
      table.innerHTML = '';

      this.datas.forEach((d, index) => {
      const row = table.insertRow(index);
      row.insertCell(0).textContent = String(index + 1);
      row.insertCell(1).textContent = d.firstName;
      row.insertCell(2).textContent = d.lastName;
      row.insertCell(3).textContent = d.department;
      row.insertCell(4).textContent = d.designation;
      row.insertCell(5).textContent = d.gender;
      row.insertCell(6).textContent = d.yearsOfExperience;
      row.insertCell(7).textContent = d.primarySkills.join(', ');
      row.insertCell(8).textContent = d.secondarySkills.join(', ');

      const removeCell = row.insertCell(9);
      const removeIcon = document.createElement('i');
      removeIcon.classList.add('nc-icon', 'nc-simple-remove', 'text-danger');
      removeIcon.style.cursor = 'pointer';
      removeIcon.addEventListener('click', () => {
        this.removeEmployee(d.id);
        });
      removeCell.appendChild(removeIcon);
      });
    }

    sortDataByParameters(id, key){
      this.datas.sort((a, b) => {
          const valueA = key === 'yearsOfExperience'  ? Number(a[key]) : String(a[key]).toLowerCase();
          const valueB = key === 'yearsOfExperience' ? Number(b[key]) : String(b[key]).toLowerCase();

          if (valueA < valueB) {
            return -1;
          }
          if (valueA > valueB) {
            return 1;
          }

          return 0;
      });
      document.getElementById('th1').style.display = 'none';
      document.getElementById('th2').style.display = 'none';
      document.getElementById('th3').style.display = 'none';
      document.getElementById('th4').style.display = 'none';
      document.getElementById('th5').style.display = 'none';
      document.getElementById('th6').style.display = 'none';
      document.getElementById(id).style.display = 'inline';
      this.loadData();
    }

    ngOnInit(){
      this.loadTable('all');
    }
}
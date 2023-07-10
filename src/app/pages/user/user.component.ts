import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from "ngx-toastr";
import { Location } from '@angular/common';


@Component({
    selector: 'user-cmp',
    moduleId: module.id,
    templateUrl: 'user.component.html'
})

export class UserComponent implements OnInit{

    public selectedOption = '';
    public selectedSkills = [];
    public scrollHeight = 0;
    public selectedOption2 = '';
    public selectedSkills2 = [];
    public scrollHeight2 = 0;

    addSkill() {
      if (this.selectedOption !== '') {
        debugger
        const index = this.selectedSkills.indexOf(this.selectedOption);
        if(index == -1){
          this.selectedSkills.push(this.selectedOption);
          this.scrollHeight += 50;
        }else{
          this.selectedSkills.splice(index, 1);
          this.scrollHeight -= 50;
        }
        this.selectedOption = '';
      }
    }

    addSkill2() {
      if (this.selectedOption2 !== '') {
        const index = this.selectedSkills2.indexOf(this.selectedOption2);
        if(index == -1){
          this.selectedSkills2.push(this.selectedOption2);
          this.scrollHeight2 += 50;
        }else{
          this.selectedSkills2.splice(index, 1);
          this.scrollHeight2 -= 50;
        }
        this.selectedOption2 = '';
      }
    }

    onSubmit(form: NgForm) {



      if(!this.validate(form)){
        return ;
      }

      const url = 'http://localhost:8080/api/employee';
      const body = {
        "firstName": form.value.firstname,
        "lastName": form.value.lastname,
        "department": form.value.department,
        "designation": form.value.designation,
        "gender": form.value.gender,
        "yearsOfExperience": form.value.experience,
        "primarySkills": this.selectedSkills,
        "secondarySkills": this.selectedSkills2
      };



      this.http.post(url, body).subscribe(
        (response) => {

          window.location.reload();
          this.showNotification('top','center',1);
        },
        (error) => {

          this.showNotification('top','center',2);
        }
      );
    }

    showNotification(from, align, color) {
      switch (color) {
        case 1:
          this.toastr.success(
            '<span data-notify="icon" class="nc-icon nc-single-02"></span><span data-notify="message">Employee added successfully!</span>',
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
          '<span data-notify="icon" class="nc-icon nc-single-02"></span><span data-notify="message">There\'s a problem creating the profile!</span>',
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
        case 3:
          this.toastr.warning(
          '<span data-notify="icon" class="nc-icon nc-single-02"></span><span data-notify="message">Please fill the form to create the profile!</span>',
            "",
            {
              timeOut: 4000,
              enableHtml: true,
              closeButton: true,
              toastClass: "alert alert-warning alert-with-icon",
              positionClass: "toast-" + from + "-" + align
            }
          );
          break;
        default:
          break;
      }
    }

    
    validate(form) {
      if(form.value.firstname === "" ||  form.value.lastname === "" ||  form.value.department === "" ||  form.value.designation === ""
          ||  form.value.experience === "" ||  form.value.gender === "" ||  this.selectedSkills.length == 0 || this.selectedSkills2.length == 0){
            this.showNotification('top','center',3);
            return false;
          }
        return true;
    }



    constructor(private http: HttpClient, private toastr: ToastrService, private location: Location) {}


    ngOnInit(){
    }
}

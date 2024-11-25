import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrls: ['./update-income.component.scss']
})
export class UpdateIncomeComponent {

  id:number = this.activatedRoute.snapshot.params['id'];
  incomeForm!: FormGroup;
  listOfCategory: any[] = [
    "Salary",
    "Freelancing",
    "Investment",
    "Bank Transfer",
    "Business",
    "Others"
  ]

  incomes: any;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router,
    private incomeService : IncomeService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.incomeForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    });
    this.getIncomeById();
  }

  getIncomeById(){
    this.incomeService.getIncomeById(this.id).subscribe(res => {
      this.incomeForm.patchValue(res);
    }, err => {
      this.message.error("Error while getting Income", { nzDuration: 5000 });
    })
  }

  submitForm(){
    this.incomeService.updateIncome(this.id, this.incomeForm.value).subscribe(res => {
      this.message.success("Income updated successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/income");
    }, err => {
      this.message.error("Error while updating Income", { nzDuration: 5000 });
    })
  }
}

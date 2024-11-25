import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss']
})
export class IncomeComponent {

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
    private incomeService : IncomeService
  ) { }

  ngOnInit() {
    this.getAllIncomes();

    this.incomeForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, Validators.required],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: [null, Validators.required],
    });
  }

  submitForm(){
    this.incomeService.postIncome(this.incomeForm.value).subscribe(res=>{
      this.message.success("Income posted successfully", { nzDuration: 5000 });
      this.getAllIncomes();
    }, err =>{
      this.message.error("Error while posting Income", { nzDuration: 5000 });
    })
  }

  getAllIncomes(){
    this.incomeService.getAllIncomes().subscribe(res=>{
      this.incomes = res;
    }, err=>{
      console.error(err);
    })
  }

  updateIncome(id: number){
    this.router.navigateByUrl(`/income/${id}/edit`);
  }

  deleteIncome(id: number){
    this.incomeService.deleteIncome(id).subscribe(res => {
      this.message.success("Income deleted successfully", { nzDuration: 5000 });
      this.getAllIncomes();
    }, err => {
      this.message.error("Error while deleting Income", { nzDuration: 5000 });
    })
  }
}

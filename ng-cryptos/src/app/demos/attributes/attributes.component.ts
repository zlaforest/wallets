import {Component, OnInit} from '@angular/core';
import {BurgerService} from "../burger.service";

@Component({
  selector: 'app-attributes',
  templateUrl: './attributes.component.html',
  styleUrls: ['./attributes.component.css']
})
export class AttributesComponent implements OnInit {

  isDisabled = true;
  height = 0;

  constructor(public burgerService:BurgerService) {
      burgerService.bunService.getHeight()
        .then(result => this.height = result);
  }

  ngOnInit() {
  }

  toggle() {
    this.isDisabled = !this.isDisabled;
  }

  nop() {

  }

}

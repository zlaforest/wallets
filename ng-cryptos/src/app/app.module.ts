import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AttributesComponent } from './demos/attributes/attributes.component';
import { WalletViewComponent } from './wallet-view/wallet-view.component';
import { TemplateComponent } from './demos/template/template.component';
import {BurgerService} from "./demos/burger.service";
import {SteakService} from "./demos/steak.service";
import {BunService} from "./demos/bun.service";
import {HttpClientModule} from "@angular/common/http";
import {PricingService} from "./pricing.service";
import { FormComponent } from './demos/form/form.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    AttributesComponent,
    WalletViewComponent,
    TemplateComponent,
    FormComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule
  ],
  providers: [BurgerService, SteakService, BunService, PricingService],
  bootstrap: [AppComponent]
})
export class AppModule { }

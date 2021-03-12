/**
 * @license
 * Copyright (c) 2019 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt
 * The complete set of authors may be found at
 * http://polymer.github.io/AUTHORS.txt
 * The complete set of contributors may be found at
 * http://polymer.github.io/CONTRIBUTORS.txt
 * Code distributed by Google as part of the polymer project is also
 * subject to an additional IP rights grant found at
 * http://polymer.github.io/PATENTS.txt
 */

import {LitElement, html, css, unsafeCSS} from 'lit-element';
import { css_gwt, css_v8addon, miapi } from 'spreadsheet-poc';

/**
 * An example element.
 *
 * @slot - This element has a slot
 * @csspart button - The button
 */
export class MyElement extends LitElement {


  static get styles() {
    return css`    
    
      #mislot {
        border: 1px solid green;
      }
    
      ${unsafeCSS(css_gwt)}

      ${unsafeCSS(css_v8addon)}
    `;
  }

  static get properties() {
    return {
      /**
       * The name to say "Hello" to.
       */
      name: {type: String},

      api: {type: Object},

      received: {type: String},

      /**
       * The number of times the button has been clicked.
       */
      count: {type: Number},
    };
  }

  updateSharedState(newVal) {
    this.api.setState(newVal);
  }

  alert(newVal) {
    this.api.alert(newVal);
  }

  constructor() {
    super();
    this.name = 'World';
    this.count = 0;
    this.received = '';
  }

  render() {
    return html`
      <h1>This is a webcomponent</h1>
      <h1 style="color: blue;">&lt;my-element name="${this.name}"&gt;&lt;/my-element&gt;</h1>
      <button @click=${this._onClick} part="button">
        Click me to update the widget's shared state from the web component
      </button>
      <p>Received from widget: ${this.received}</p>
      <p>Below is the GWT widget:</p>
      <div id="mislot"></div>
      
      <slot></slot>
    `;
  }

  _onClick() {
    this.count++;
    this.api.setState(this.name + ' - ' + this.count);
    console.log('Estado fijado a ' + this.name)
  }

  connectedCallback() {
    super.connectedCallback()
    console.log('connected')
  }

  updated(_changedProperties) {
    super.updated(_changedProperties);
    console.log(this.shadowRoot.querySelector('#mislot'));
    if (!this.api) {
      this.api = new miapi(this.shadowRoot.getElementById('mislot'));
      this.api.registerClicked(e => {
        this.received = '' + e;
        let event = new CustomEvent('my-event', {
          detail: {
            message: '' + e
          }
        });
        this.dispatchEvent(event);
      }); //this.api.alert('webcomponent callback called for ' + e));
      this.api.alert('Hello from web component');
    }
  }

  attributeChangedCallback(name, oldVal, newVal) {
    console.log('attribute change: ', name, newVal);
    if ('name' == name) {
      this.api.setState(newVal);
    }
    super.attributeChangedCallback(name, oldVal, newVal);
  }

}

window.customElements.define('my-element', MyElement);

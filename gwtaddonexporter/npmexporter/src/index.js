
//import style_gwt from '../../target/gwtaddonexporter-1.0-SNAPSHOT/GwtAddonExporter/gwt/standard/standard.css';
//import style_v8addon from '../../target/gwtaddonexporter-1.0-SNAPSHOT/GwtAddonExporter/v8addon/styles.css';
import style_gwt from './gwt/standard/standard.css';
import style_v8addon from './v8addon/styles.css';
import {} from '../../target/gwtaddonexporter-1.0-SNAPSHOT/GwtAddonExporter/GwtAddonExporter.cache.js';

//let MiApi = miguel.gwtaddonexporter.client.MiApi;

/*
console.log(style_gwt);
console.log(style_v8addon);
 */

let css_gwt = '' + style_gwt;
let css_v8addon = '' + style_v8addon;
let miapi = miguel.gwtaddonexporter.client.MiApi;

export { css_gwt, css_v8addon, miapi }

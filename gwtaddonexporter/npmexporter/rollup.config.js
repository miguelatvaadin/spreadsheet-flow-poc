import { string } from "rollup-plugin-string";
import copy from 'rollup-plugin-copy'

export default {
    input: 'src/index.js',
    output: {
        file: 'dist/main.js',
        name: 'milib',
        format: 'esm'
    },
    plugins: [
        string({
            // Required to be specified
            include: "**/*.css",

            // Undefined by default
            exclude: ["**/index.html"]
        }),
        copy({
            targets: [
                { src: "../target/gwtaddonexporter-1.0-SNAPSHOT/GwtAddonExporter/gwt", dest: "src" },
                { src: "../target/gwtaddonexporter-1.0-SNAPSHOT/GwtAddonExporter/v8addon", dest: "src" },
            ],
            hook: 'buildStart'
        }),
    ]
};
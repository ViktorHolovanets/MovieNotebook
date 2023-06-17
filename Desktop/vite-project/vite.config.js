import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from 'path';

export default defineConfig({
  base: resolve(__dirname, "./dist/"),
  plugins: [vue()],
  build: {
    assetsDir: "assets"
  },
  server: {
    port: 8080, // Змініть на потрібний вам порт
  },
});

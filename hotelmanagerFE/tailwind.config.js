/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./components/**/*.{js,vue,ts}",
    "./layouts/**/*.vue",
    "./pages/**/*.vue",
    "./plugins/**/*.{js,ts}",
    "./error.vue",
  ],
  theme: {
    extend: {
      backgroundColor: {
        dark: '#0F172A',
      },
      textColor: {
        customGreen: 'rgba(0, 200, 130, 1)',
        customBlue: 'rgb(148, 163, 184, 1)',
      },
    },
  },
  plugins: [],
}


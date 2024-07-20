/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["src/main/resources/templates/**/*.{html,js}"],
  theme: {
    extend: {
      colors:{
        customColors:'#123456',
      }
    },
  },
  plugins: [
  ],
}


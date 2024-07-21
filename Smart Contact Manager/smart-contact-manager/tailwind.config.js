/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/**/*.{html,js}",
     "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')
  ],
}


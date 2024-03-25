import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import axios from 'axios';

function App() {
  axios.get('http://localhost:3000/api/rangkuman')
  .then(response => {
    console.log('Response:', response); // Log the entire response
    console.log('Data:', response.data); // Log just the data
    // Further processing of the data
  })
  .catch(error => {
    console.error('Error fetching data:', error);
  });

  return (
    <div>
      Halo
    </div>
  )
}

export default App

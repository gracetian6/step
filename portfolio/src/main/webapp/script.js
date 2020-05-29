// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// number of total images for moments.html  
const img_total = 11;

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * Adds a random quote to the page.
 */
 function addRandomQuote() {
    // quotes will be stored in data.js
    const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];
}

/**
 * Constructs random image for randomizeImage fn
 */
function constructImage(imageIndex){
  const imgUrl = `images/grace-${imageIndex}.jpg`;
  const imgElement = document.createElement('img');
  imgElement.src = imgUrl;
  imgElement.width = '300';
  return imgElement;
}

/**
 * Adds a random image to the moments.html
 */
 function randomizeImage() {
  const imageContainer = document.getElementById('random-image-container');
  // Remove the previous image.
  imageContainer.innerHTML = '';

    // The images directory contains img_total images, so generate a random index between
  // 0 and img_total-1.
  const imageIndex = Math.floor(Math.random() * img_total);
  const newImage = constructImage(imageIndex);
  imageContainer.appendChild(newImage);

  // Add Caption
  const txtElement = document.createElement('p');
  console.log(window.moments[imageIndex]);
  txtElement.innerText = window.moments[imageIndex]; 
  const txtContainer = document.getElementById('random-caption-container');
  txtContainer.innerHTML = '';
  txtContainer.appendChild(txtElement);
} 

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
  txtElement.innerText = window.moments[imageIndex]; 
  const txtContainer = document.getElementById('random-caption-container');
  txtContainer.innerHTML = '';
  txtContainer.appendChild(txtElement);
} 

function getMaxComment() {
  console.log("getMaxComment");
  const numComments = document.getElementById('maxComments').value ;
  console.log(numComments);
  return numComments || 5; // return maxComment
}

/**
 * Fetches comments from the \data server and adds them to index.html
 */
function getComment() {
  const maxComment = getMaxComment();
  console.log("getComment:");
  console.log(maxComment);
  fetch(`/data?numComments=${maxComment}`).then(response => response.json()).then((comment) => {
    // Build the list of history entries.
    const historyEl = document.getElementById('history');
    // clear html before I append stuff
    historyEl.innerHTML = '';
    comment.forEach((line) => {
      historyEl.appendChild(createListElement(line.content));
    });
  });
}

function knit() {
  // finding numComments will work once URL prints correctly
  var url = new URL(document.URL);
  console.log(url);
  const numComments = url.searchParams.get("numComments");
  console.log("knit");
  console.log(numComments);
  // The issue is that I don't know how to pass down numComments above to getComment() function
  getComment();
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

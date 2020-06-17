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

/**
 * Extracts max comment from input, otherwise returns 5 
 */
function getMaxComments() {
  numComments = document.getElementById('maxComments').value;
  return numComments || 5; 
}

/**
 * Fetches comments from the \data server and adds them to index.html
 */
function getComments() {
  const maxComment = getMaxComments();
  fetch(`/comment?numComments=${maxComment}`).then(response => response.json()).then((comment) => {
    // Build the list of history entries.
    const commentBlock = document.getElementById('commentBlock');
    // clear html before appending comments
    commentBlock.innerHTML = '';
    comment.forEach((line) => {
      commentBlock.appendChild(createListElement(line.email + ": " + line.content));
    });
  });
}

/**
 * initializes comments on body load
 * hides comments if user not logged in, otherwise 
 * extracts numComment from URL and then displays comment
 */
function initComments() {
  fetch(`/loginStatus`).then(response => response.json()).then((msg) => {
    if (msg.loginStatus === false) {
      document.getElementById("commentForm").style.display = "none";
    } else {
      document.getElementById("loginLink").innerHTML = 
        `<a href="login">Logout here</a> to hide comments:`;
      document.getElementById("commentForm").style.display = "block";
      // extract numComment from URL 
      var url = new URL(document.URL);
      const numComments = url.searchParams.get('numComments') || 5;
      // set input for maxComment
      maxCommentInput = document.getElementById('maxComments');
      maxCommentInput.value = numComments;
      getComments();
    }
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

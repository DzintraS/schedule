//TODO implement methods calling REST endpoints for buttons in html

function callCompleteTask(task) {
     var xhttp = new XMLHttpRequest();

     xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);
               location.reload();
          }
     };

     xhttp.open("PATCH", "/rest/task/setCompleted?id=" + task.id, true);
     xhttp.setRequestHeader("Content-type", "application/json");
     console.log(JSON.stringify(task));
     xhttp.send();
}

function callUncompleteTask(task) {
     var xhttp = new XMLHttpRequest();

     xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);
               location.reload();
          }
     };

     xhttp.open("PATCH", "/rest/task/setNotCompleted?id=" + task.id, true);
     xhttp.setRequestHeader("Content-type", "application/json");
     console.log(JSON.stringify(task));
     xhttp.send();
}

function callDeleteTask(task) {
     var xhttp = new XMLHttpRequest();

     xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);
               location.reload();
          }
     };

     xhttp.open("DELETE", "/rest/task?id=" + task.id, true);
     xhttp.setRequestHeader("Content-type", "application/json");
     console.log(JSON.stringify(task));
     xhttp.send();
}

function callAddTask(task) {

     var data = JSON.stringify(task);
     console.log(data);

     var xhttp = new XMLHttpRequest();

     xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
               console.log(this.responseText);
               location.reload();
          }
     };

     xhttp.open("POST", "/rest/task", true);
     xhttp.setRequestHeader("Content-type", "application/json");

     xhttp.send(data);
}


const formToJSON_deconstructed = elements => {
     const reducerFunction = (data, element) => {
          if (isValidElement(element)) {
               data[element.name] = element.value;
          }
          console.log(JSON.stringify(data));
          return data;
     };
     const reducerInitialValue = {};
     console.log('Initial `data` value:', JSON.stringify(reducerInitialValue));
     const formData = [].reduce.call(elements, reducerFunction, reducerInitialValue);
     return formData;
};

const handleFormSubmit = event => {
     event.preventDefault();
     const data = formToJSON_deconstructed(form.elements);
     callAddTask(data);
};

const isValidElement = element => {
     return element.name && element.value;
};

const form = document.getElementsByClassName('form')[0];
form.addEventListener('submit', handleFormSubmit);

const reducerFunction = (data, element) => {
     data[element.name] = element.value;
     console.log(JSON.stringify(data));
     return data;
};


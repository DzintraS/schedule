//TODO implement methods calling REST endpoints for buttons in html

      function callCompleteTask(task) {
            var xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function() {
                 if (this.readyState == 4 && this.status == 200) {
                 console.log(this.responseText);
                 location.reload();
                 }
            };

            xhttp.open("PATCH", "/rest/task/setCompleted?id="+task.id , true);
            xhttp.setRequestHeader("Content-type", "application/json");
            console.log(JSON.stringify(task));
            xhttp.send();
      }
      function callUncompleteTask(task) {
            var xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function() {
                 if (this.readyState == 4 && this.status == 200) {
                 console.log(this.responseText);
                 location.reload();
                 }
            };

            xhttp.open("PATCH", "/rest/task/setNotCompleted?id="+task.id , true);
            xhttp.setRequestHeader("Content-type", "application/json");
            console.log(JSON.stringify(task));
            xhttp.send();
      }
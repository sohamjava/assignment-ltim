<h1>Department Page</h1>
<input type="text" name="tb_deptName" id= "tb_deptName" placeholder="Department Name ..." required><a href="javascript:void(0)" onclick="createDepartment(document.getElementById('tb_deptName').value)">Save Department</a>
<table >
<thead> <tr> <th>ID</th> <th>Name</th>  <th>Delete</th></tr> </thead> 

<tbody id="tbl_department"> </tbody>

</table>
<script>
function createDepartment(deptName){
  var url="../api/departments";
  var xhr=new XMLHttpRequest();
  
xhr.open("POST",url,true);
xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

const payload={
"name": deptName
};
xhr.onreadystatechange = function() {
if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
          alert("Department Created");
          fetchDataAndPopulateTbl();
      }else{
         alert(xhr.responseText);
      }
}
};
xhr.send(JSON.stringify( payload));
}


function deleteDepartment(deptId){
  var url="../api/departments/"+deptId;
  var xhr=new XMLHttpRequest();
xhr.open("DELETE",url,true);
xhr.onreadystatechange = function() {
if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
          alert("Department Deleted");
          fetchDataAndPopulateTbl();
      }else{
         alert(xhr.responseText);
      }
}
};
xhr.send();
}


function fetchDataAndPopulateTbl(){
var target=document.getElementById("tbl_department");
target.innerHTML="";
var xhr=new XMLHttpRequest();
xhr.open("GET","http://localhost:8080/api/departments",true);
xhr.onreadystatechange = function() {
 if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
         resObj=JSON.parse(xhr.responseText);
         alert(resObj);
         
         resObj.forEach(item => { 
             const row = document.createElement("tr"); 
             const idCell = document.createElement("td"); 
             idCell.textContent = item.id; 
             row.appendChild(idCell);
             
             const nameCell = document.createElement("td"); 
             nameCell.textContent = item.name; 
             row.appendChild(nameCell); 
             
             const deleteCell = document.createElement("td"); 
             deleteCell.innerHTML = "<a href='javascript:void(0)' onClick='deleteDepartment("+item.id+")'>Delete</a>"; 
             row.appendChild(deleteCell); 
            
              target.appendChild(row); 
         });
         
      } else { 
        console.error('Request failed. Returned status of ' + xhr.status); 
      } 
      
   } 
 };
  xhr.send();
}

fetchDataAndPopulateTbl();
</script>
<h1>Employee Page</h1>
<pre>
<form id="empForm">
<input type="text" name="name" id= "tb_empName" placeholder="Employee Name ..." required>
<input type="password" name="password" id= "tb_empPwd" placeholder="Employee Pwd ..." required>
<input type="text" name="email" id= "tb_empEmail" placeholder="Employee Email ..." required>
<input type="text" name="role" id= "tb_empRole" placeholder="Employee Role ..." required>
<select name="department" id= "tb_empDept" placeholder="Select Department" required>
</select>


<a href="javascript:void(0)" onclick="createEmp()">Save Department</a>

</form>
</pre>
<table >
<thead> <tr> <th>ID</th> <th>Name</th>  <th>Email</th> <th>Department</th> <th>Role</th>  <th>Delete</th></tr> </thead> 

<tbody id="tbl_Emp"> </tbody>

</table>
<script>
function createEmp(){
var f=document.getElementById("empForm");
// alert(JSON.stringify(getFormJSON(f)));
  var url="../api/employees";
  var xhr=new XMLHttpRequest();
  
xhr.open("POST",url,true);
xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
var payloadObj=getFormJSON(f);
payloadObj.department={id: empForm.department.options[ empForm.department.selectedIndex].value,name:empForm.department.options[ empForm.department.selectedIndex].text};
const payload=JSON.stringify(payloadObj);
 
xhr.onreadystatechange = function() {
if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
          alert("Emp Created");
          fetchDataAndPopulateTbl();
      }else{
         alert(xhr.responseText);
      }
}
};
xhr.send(payload);
}


function deleteEmployeet(empId){
  var url="../api/employees/"+empId;
  var xhr=new XMLHttpRequest();
xhr.open("DELETE",url,true);
xhr.onreadystatechange = function() {
if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
          alert("Emp Deleted");
          fetchDataAndPopulateTbl();
      }else{
          alert(xhr.responseText);
      }
}
};
xhr.send();
}


function fetchDataAndPopulateTbl(){
var target=document.getElementById("tbl_Emp");
target.innerHTML="";
var xhr=new XMLHttpRequest();
xhr.open("GET","../api/employees",true);
xhr.onreadystatechange = function() {
 if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
         resObj=JSON.parse(xhr.responseText);
         // alert(resObj);
         
         resObj.forEach(item => { 
             const row = document.createElement("tr"); 
             const idCell = document.createElement("td"); 
             idCell.textContent = item.id; 
             row.appendChild(idCell);
             
             const nameCell = document.createElement("td"); 
             nameCell.textContent = item.name; 
             row.appendChild(nameCell); 
             
             const emailCell = document.createElement("td"); 
             emailCell.textContent = item.email; 
             row.appendChild(emailCell); 
             
             const deptCell = document.createElement("td"); 
             deptCell.textContent = item.department['name']; 
             row.appendChild(deptCell); 
             
             const roleCell = document.createElement("td"); 
             roleCell.textContent = item.role; 
             row.appendChild(roleCell); 
             
             const deleteCell = document.createElement("td"); 
             deleteCell.innerHTML = "<a href='javascript:void(0)' onClick='deleteEmployeet("+item.id+")'>Delete</a>"; 
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


function fetchDataAndPopulateDepartmentDrodown(){
var target=document.getElementById("tb_empDept");
target.innerHTML="";
var xhr=new XMLHttpRequest();
xhr.open("GET","../api/departments",true);
xhr.onreadystatechange = function() {
 if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) { 
         resObj=JSON.parse(xhr.responseText);
        // // alert(resObj);
         
         resObj.forEach(item => { 
            const opt = document.createElement("option");
             opt.value =item.id;
             opt.textContent = item.name; 
             target.appendChild(opt);
         });
         
      } else { 
        console.error('Request failed. Returned status of ' + xhr.status); 
      } 
      
   } 
 };
  xhr.send();
}

const getFormJSON = (form) => {
  const data = new FormData(form);
  return Array.from(data.keys()).reduce((result, key) => {
    if (result[key]) {
      result[key] = data.getAll(key)
      return result
    }
    result[key] = data.get(key);
    return result;
  }, {});
};
fetchDataAndPopulateTbl();
fetchDataAndPopulateDepartmentDrodown();
</script>
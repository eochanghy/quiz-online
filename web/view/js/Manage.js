function renderPager(id, pageIndex, totalPage, gap){
    var container = document.getElementById(id);
    if(pageIndex > gap + 1){
        container.innerHTML += "<a href='Manage?page=1'>First</a>";
        container.innerHTML += "<p style='display: inline-block;font-size: 18px;font-weight: bold;margin-left: 2px;margin-right: 2px;'>..</p>";
    }
    for (var i = pageIndex-gap; i < pageIndex; i++) {
        if(i>=1){
            container.innerHTML += "<a href='Manage?page="+i+"'>"+i+"</a>";
        }
    }
    container.innerHTML +="<span>"+pageIndex+"</span>";
    for (var i = pageIndex + 1; i <= pageIndex+gap; i++) {
        if(i<= totalPage){
            container.innerHTML += "<a href='Manage?page="+i+"'>"+i+"</a>";
        }
    }
    
    if(pageIndex < totalPage - gap){
        container.innerHTML += "<p style='display: inline-block;font-size: 18px;font-weight: bold;margin-left: 2px;margin-right: 2px;'>..</p>";
        container.innerHTML += "<a href='Manage?page="+totalPage+"'>Last</a>";
    }
    console.log("Gap: "+gap);
}
function confirmDelete(id){
    if(confirm("Do you want to delete?")==true){
        document.location = 'Delete?id='+id;
    }
}

